package com.telecom.servlets;

import com.telecom.dao.model.Option;
import com.telecom.exception.DaoException;
import com.telecom.exception.InputException;
import com.telecom.services.api.OptionService;
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
//@WebServlet({ "/alloptions", "/option", "/editoption", "/updateoption", "/createoption", "/createoptionhandler" })
public class OptionController {

    @Autowired
    OptionService optionService;

    @RequestMapping(value = "/alloptions", method = RequestMethod.GET)
    protected String displayAllOptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Iterable<Option> optionIterable = optionService.findAll();
        request.setAttribute("optionList", optionIterable);
        return "alloptions";
    }

    @RequestMapping(value = "/option", method = RequestMethod.GET)
    protected String displayOption(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String stringIdOption = (String) request.getParameter("idOption");
        int idOption = Integer.parseInt(stringIdOption);
        Option option = optionService.findById(idOption);
        request.setAttribute("option", option);
        return "option";

    }

    @RequestMapping(value = "/editoption", method = RequestMethod.GET)
    protected String editOption(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String stringIdOption = (String) request.getParameter("idOption");
        int idOption = Integer.parseInt(stringIdOption);
        Option option = optionService.findById(idOption);
        request.setAttribute("option", option);
        Iterable<Option> optionIterable = optionService.findAll();
        request.setAttribute("optionList", optionIterable);
        return "editoption";

    }

    @RequestMapping(value = "/createoption", method = RequestMethod.GET)
    protected String createPlan(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Iterable<Option> optionIterable = optionService.findAll();
        request.setAttribute("optionList", optionIterable);
        return "createoption";
    }

    @RequestMapping(value = "/updateoption", method = RequestMethod.POST)
    protected String updateOption(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                // Find and update that need update.
                String stringIdOption = (String) request.getParameter("idOption");
                int idOption = Integer.parseInt(stringIdOption);
                Option option = optionService.findById(idOption);
                String name = (String) request.getParameter("name");
                option.setName(name);
                float price = Float.parseFloat(request.getParameter("price"));
                option.setPrice(price);
                float priceConnect = Float.parseFloat(request.getParameter("priceConnect"));
                option.setPriceConnect(priceConnect);
                boolean archival = Boolean.parseBoolean(request.getParameter("archival"));
                option.setArchival(archival);

                // Create list of new required options.
                List<Option> listRequiredOptions = new ArrayList<Option>();
                String[] requiredStringIdOptions = request.getParameterValues("required");
                if (requiredStringIdOptions != null) {
                    for (String strIdRequiredOption : requiredStringIdOptions) {
                        int idRequiredOption = Integer.parseInt(strIdRequiredOption);
                        Option requiredOption = optionService.findById(idRequiredOption);
                        listRequiredOptions.add(requiredOption);
                    }
                }
                // Create list of new incompatible options.
                List<Option> listIncompatibleOptions = new ArrayList<Option>();
                String[] incompatibleStringIdOptions = request.getParameterValues("incompatible");
                if (incompatibleStringIdOptions != null) {
                    for (String strIdIncompatibleOption : incompatibleStringIdOptions) {
                        int idIncompatibleOption = Integer.parseInt(strIdIncompatibleOption);
                        Option incompatibleOption = optionService.findById(idIncompatibleOption);
                        listIncompatibleOptions.add(incompatibleOption);
                    }
                }

                    option.setRequiredOptions(listRequiredOptions);
                    option.setIncompatibleOptions(listIncompatibleOptions);
                    optionService.update(option);

                request.setAttribute("option", option);
                return ("option");
            }
            // If error occur redirect on page with all options list.
            catch (Throwable ex) {
                // throw new RuntimeException(ex);
                Iterable<Option> optionIterable = optionService.findAll();
                request.setAttribute("optionList", optionIterable);
                return ("/alloptions");
            }

        }

    @RequestMapping(value = "/createoptionhandler", method = RequestMethod.POST)
    protected String createOptionHandler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Option option = new Option();
        try {
            String name = (String) request.getParameter("name");
            float price = Float.parseFloat(request.getParameter("price"));
            float priceConnect = Float.parseFloat(request.getParameter("priceConnect"));
            boolean archival = Boolean.parseBoolean(request.getParameter("archival"));

            option.setName(name);
            option.setPrice(price);
            option.setPriceConnect(priceConnect);
            option.setArchival(archival);

        List<Option> listRequiredOptions = new ArrayList<Option>();
        List<Option> listIncompatibleOptions = new ArrayList<Option>();

        String[] requiredStringIdOptions = request.getParameterValues("required");
        if (requiredStringIdOptions != null) {
            for (String strIdRequiredOption : requiredStringIdOptions) {
                int idRequiredOption = Integer.parseInt(strIdRequiredOption);
                Option requiredOption = optionService.findById(idRequiredOption);
                listRequiredOptions.add(requiredOption);
            }
        }
        String[] incompatibleStringIdOptions = request.getParameterValues("incompatible");
        if (incompatibleStringIdOptions != null) {
            for (String strIdIncompatibleOption : incompatibleStringIdOptions) {
                int idIncompatibleOption = Integer.parseInt(strIdIncompatibleOption);
                Option incompatibleOption = optionService.findById(idIncompatibleOption);
                listIncompatibleOptions.add(incompatibleOption);
            }
        }

            option.setRequiredOptions(listRequiredOptions);
            option.setIncompatibleOptions(listIncompatibleOptions);
            optionService.create(option);
        } catch (Throwable ex) {
            Iterable<Option> optionIterable = optionService.findAll();
            request.setAttribute("optionList", optionIterable);
            return "alloptions";
        }

        request.setAttribute("option", option);
        return "option";
    }

}