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
                if (name == null)
                    throw new InputException();
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
                try {
                    option.setRequiredOptions(listRequiredOptions);
                    option.setIncompatibleOptions(listIncompatibleOptions);
                    optionService.update(option);
                } /*catch (DaoException | InputException ex) {

                }*/ catch (Exception ex) {

                }
                request.setAttribute("option", option);
                return ("option");
            }
            // If error occur redirect on page with all options list.
            catch (InputException ex) {
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
        } catch (Exception ex) {
            throw new Error("TELECOM_EXCEPTION Incorrect input");

        }
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

        try {
            option.setRequiredOptions(listRequiredOptions);
            option.setIncompatibleOptions(listIncompatibleOptions);
            optionService.create(option);
        } /*catch (DaoException | InputException ex) {
            // throw new RuntimeException(ex);

        }*/ catch (Exception ex) {
            Iterable<Option> optionIterable = optionService.findAll();
            request.setAttribute("optionList", optionIterable);
            return "alloptions";
        }

        request.setAttribute("option", option);
        return "option";
    }

}
    /*private static final long serialVersionUID = 1L;
    OptionService optionService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        // Handler for display list of all options
        if (servletPath.equals("/alloptions")) {
            displayAllOptions(request, response);
        } else
        // Handler for display the option's parameters. Including required and
        // incompatible options.
        if (servletPath.equals("/option")) {
            displayOption(request, response);
        } else
        // Handler for edit option's parameters. Including required and
        // incompatible options.
        if (servletPath.equals("/editoption")) {
            editOption(request, response);
        } else
        // Handler for create option. Send to user page for create new option.
        if (servletPath.equals("/createoption")) {
            createOption(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        // doPost(request, response);
        // Handler for update option's parameters. Including new required and
        // incompatible options.
        if (servletPath.equals("/updateoption")) {
            updateOption(request, response);
        } else
        // Handler for create option. Get from user parameters for create new
        // option.
        if (servletPath.equals("/createoptionhandler")) {
            createOptionHandler(request, response);
        }

    }

    protected void displayAllOptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        String jspPath;
        List<Option> optionList = optionService.getAllOptions();
        request.setAttribute("optionList", optionList);
        jspPath = "/WEB-INF/jsp/alloptions.jsp";
        //if (optionList.size()>8) throw (new RuntimeException());
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
        requestDispatcher.forward(request, response);
    }

    protected void displayOption(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        String jspPath;
        String stringIdOption = (String) request.getParameter("idOption");
        int idOption = Integer.parseInt(stringIdOption);
        Option option = optionService.getOption(idOption);
        request.setAttribute("option", option);
        jspPath = "/WEB-INF/jsp/option.jsp";
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
        requestDispatcher.forward(request, response);
    }

    protected void editOption(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        String jspPath;
        String stringIdOption = (String) request.getParameter("idOption");
        int idOption = Integer.parseInt(stringIdOption);
        Option option = optionService.getOption(idOption);
        request.setAttribute("option", option);
        List<Option> optionList = optionService.getAllOptions();
        request.setAttribute("optionList", optionList);
        jspPath = "/WEB-INF/jsp/editoption.jsp";
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
        requestDispatcher.forward(request, response);
    }

    protected void updateOption(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        String jspPath;
        try {
            // Find and update that need update.
            String stringIdOption = (String) request.getParameter("idOption");
            int idOption = Integer.parseInt(stringIdOption);
            Option option = optionService.getOption(idOption);
            String name = (String) request.getParameter("name");
            if (name == null)
                throw new InputException();
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
                    Option requiredOption = optionService.getOption(idRequiredOption);
                    listRequiredOptions.add(requiredOption);
                }
            }
            // Create list of new incompatible options.
            List<Option> listIncompatibleOptions = new ArrayList<Option>();
            String[] incompatibleStringIdOptions = request.getParameterValues("incompatible");
            if (incompatibleStringIdOptions != null) {
                for (String strIdIncompatibleOption : incompatibleStringIdOptions) {
                    int idIncompatibleOption = Integer.parseInt(strIdIncompatibleOption);
                    Option incompatibleOption = optionService.getOption(idIncompatibleOption);
                    listIncompatibleOptions.add(incompatibleOption);
                }
            }
            try {
                optionService.updateOption(option, listRequiredOptions, listIncompatibleOptions);
            } catch (DaoException | InputException ex) {

            } catch (Exception ex) {

            }
            request.setAttribute("option", option);
            jspPath = "/WEB-INF/jsp/option.jsp";
        }
        // If error occur redirect on page with all options list.
        catch (InputException ex) {
            // throw new RuntimeException(ex);
            List<Option> optionList = optionService.getAllOptions();
            request.setAttribute("optionList", optionList);
            jspPath = "/WEB-INF/jsp/alloptions.jsp";
        }
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
        requestDispatcher.forward(request, response);
    }

    protected void createOption(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        String jspPath;
        List<Option> optionList = optionService.getAllOptions();
        request.setAttribute("optionList", optionList);
        jspPath = "/WEB-INF/jsp/createoption.jsp";
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
        requestDispatcher.forward(request, response);
    }

    protected void createOptionHandler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        String jspPath;
        Option option = new Option();
        String errorParameters = null;
        String errorListsOptions = null;
        try {
            String name = (String) request.getParameter("name");
            float price = Float.parseFloat(request.getParameter("price"));
            float priceConnect = Float.parseFloat(request.getParameter("priceConnect"));
            boolean archival = Boolean.parseBoolean(request.getParameter("archival"));
            
            option.setName(name);
            option.setPrice(price);
            option.setPriceConnect(priceConnect);
            option.setArchival(archival);
        } catch (Exception ex) {
            errorParameters = "Invalid name or price.";
            request.setAttribute("option", option);
            jspPath = "/WEB-INF/jsp/option.jsp";
        }
        List<Option> listRequiredOptions = new ArrayList<Option>();
        List<Option> listIncompatibleOptions = new ArrayList<Option>();
        
            String[] requiredStringIdOptions = request.getParameterValues("required");
            if (requiredStringIdOptions != null) {
                for (String strIdRequiredOption : requiredStringIdOptions) {
                    int idRequiredOption = Integer.parseInt(strIdRequiredOption);
                    Option requiredOption = optionService.getOption(idRequiredOption);
                    listRequiredOptions.add(requiredOption);
                }
            }
            String[] incompatibleStringIdOptions = request.getParameterValues("incompatible");
            if (incompatibleStringIdOptions != null) {
                for (String strIdIncompatibleOption : incompatibleStringIdOptions) {
                    int idIncompatibleOption = Integer.parseInt(strIdIncompatibleOption);
                    Option incompatibleOption = optionService.getOption(idIncompatibleOption);
                    listIncompatibleOptions.add(incompatibleOption);
                }
            }
            
            request.setAttribute("option", option);
            jspPath = "/WEB-INF/jsp/option.jsp";
        
     
        try {
            optionService.createOption(option, listRequiredOptions, listIncompatibleOptions);
        } catch (DaoException | InputException ex) {
         // throw new RuntimeException(ex);
            List<Option> optionList = optionService.getAllOptions();
            request.setAttribute("optionList", optionList);
            jspPath = "/WEB-INF/jsp/alloptions.jsp";
        } catch (Exception ex) {

        }
        
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
        requestDispatcher.forward(request, response);
    }

    */