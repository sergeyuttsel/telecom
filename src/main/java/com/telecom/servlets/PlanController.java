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
}
