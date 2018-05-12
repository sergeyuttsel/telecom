package com.telecom.servlets;

import com.telecom.dao.model.Option;
import com.telecom.dao.model.Plan;
import com.telecom.exception.DaoException;
import com.telecom.exception.InputException;
import com.telecom.services.api.OptionService;
import com.telecom.services.api.PlanService;
import com.telecom.util.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Component
//@RequestMapping(
        //value = { "/allplans", "/plan", "/editplan", "/updateplan", "/createplan", "/createplanhandler" })
public class PlanController {
    @Autowired
    PlanService planService;

    @Autowired
    OptionService optionService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }

    @RequestMapping(value = "/allplans", method = RequestMethod.GET)
    protected String displayAllPlans(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Iterable<Plan> planIterable = planService.findAll();
        request.setAttribute("planIterable", planIterable);
        return "allplans";

    }

    @RequestMapping(value = "/plan", method = RequestMethod.GET)
    protected String displayPlan(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String stringIdPlan = (String) request.getParameter("idPlan");
        int idPlan = Integer.parseInt(stringIdPlan);
        Plan plan = planService.findById(idPlan);
        request.setAttribute("plan", plan);
        return "plan";
    }

    @RequestMapping(value = "/editplan", method = RequestMethod.GET)
    protected String editPlan(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String stringIdPlan = (String) request.getParameter("idPlan");
        int idPlan = Integer.parseInt(stringIdPlan);
        Plan plan = planService.findById(idPlan);
        request.setAttribute("plan", plan);
        Iterable<Option> optionIterable = optionService.findAll();
        request.setAttribute("optionList", optionIterable);
        return "editplan";
    }

    @RequestMapping(value = "/createplan", method = RequestMethod.GET)
    protected String createPlan(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Iterable<Option> optionIterable = optionService.findAll();
        request.setAttribute("optionList", optionIterable);
        return "createplan";
    }

    @RequestMapping(value = "/updateplan", method = RequestMethod.POST)
    protected String updatePlan(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String stringIdPlan = (String) request.getParameter("idPlan");
            int idPlan = Integer.parseInt(stringIdPlan);
            Plan plan = planService.findById(idPlan);
            String name = (String) request.getParameter("name");
            plan.setName(name);
            float price = Float.parseFloat(request.getParameter("price"));
            plan.setPrice(price);
            boolean archival = Boolean.parseBoolean(request.getParameter("archival"));
            plan.setArchival(archival);

            // Create list of new available options.
            List<Option> listAvailableOptions = new ArrayList<Option>();
            String[] availableStringIdOptions = request.getParameterValues("available");
            if (availableStringIdOptions != null) {
                for (String strIdAvailableOption : availableStringIdOptions) {
                    int idAvailableOption = Integer.parseInt(strIdAvailableOption);
                    Option availableOption = optionService.findById(idAvailableOption);
                    listAvailableOptions.add(availableOption);
                }
            }

                plan.setAvailableOptions(listAvailableOptions);
                planService.update(plan);

            request.setAttribute("plan", plan);
            return "plan";
        }
        // If error occur redirect on page with all plans list.
        catch (Throwable ex) {
            // throw new RuntimeException(ex);
            Iterable<Plan> planIterable = planService.findAll();
            request.setAttribute("planList", planIterable);
            return "allplans";
        }

        //return "allplans";
    }

    @RequestMapping(value = "/createplanhandler", method = RequestMethod.POST)
    protected String createPlanHandler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Plan plan = new Plan();
            String name = (String) request.getParameter("name");
            plan.setName(name);
            float price = Float.parseFloat(request.getParameter("price"));
            plan.setPrice(price);
            boolean archival = Boolean.parseBoolean(request.getParameter("archival"));
            plan.setArchival(archival);

            // Create list of available options.
            List<Option> listAvailableOptions = new ArrayList<Option>();
            String[] availableStringIdOptions = request.getParameterValues("available");
            if (availableStringIdOptions != null) {
                for (String strIdAvailableOption : availableStringIdOptions) {
                    int idAvailableOption = Integer.parseInt(strIdAvailableOption);
                    Option availableOption = optionService.findById(idAvailableOption);
                    listAvailableOptions.add(availableOption);
                }
            }

                plan.setAvailableOptions(listAvailableOptions);
                planService.create(plan);

            request.setAttribute("plan", plan);
            return "plan";
        }
        // If error occur redirect on page with all plans list.
        catch (Throwable ex) {
            // throw new RuntimeException(ex);
            Iterable<Plan> planIterable = planService.findAll();
            request.setAttribute("planList", planIterable);
            return "allplans";
        }
    }
}
