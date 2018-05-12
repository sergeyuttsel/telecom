package com.telecom.servlets;

import com.telecom.dao.model.Contract;
import com.telecom.dao.model.Option;
import com.telecom.dao.model.Plan;
import com.telecom.dao.model.User;
import com.telecom.exception.DaoException;
import com.telecom.exception.InputException;
import com.telecom.services.api.ContractService;
import com.telecom.services.api.OptionService;
import com.telecom.services.api.PlanService;
import com.telecom.services.api.UserService;
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
//@WebServlet({ "/createcontract", "/editcontract", "/changeplan", "/changeplanhandler", "/changeoptions", "/changeoptionshandler", "/createcontracthandler", "/removecontracthandler" })
public class ContractController extends HttpServlet {
    @Autowired
    ContractService contractService;
    @Autowired
    UserService userService;
    @Autowired
    PlanService planService;
    @Autowired
    OptionService optionService;

    @RequestMapping(value = "/createcontracthandler", method = RequestMethod.POST)
    protected String createContractHandler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String stringIdUser = (String) request.getParameter("idUser");
            int idUser = Integer.parseInt(stringIdUser);
            User user = userService.findById(idUser);
            Contract contract = new Contract();
            //request.setAttribute("user", contract);
            String phoneNumber = (String) request.getParameter("phoneNumber");
            Iterable<Plan> planIterable = planService.findAll();
            if (contractService.findByPhoneNumber(phoneNumber).isEmpty() != true) throw new Exception();
            contract.setPhoneNumber(phoneNumber);
            contract.setPlan(planIterable.iterator().next());
            contract.setClientBlock(false);
            contract.setEmployeeBlock(false);
            //user.getContracts().add(contract);
            contract.setUser(user);
            contractService.save(contract);
            user = userService.findById(idUser);
            request.setAttribute("user", user);
            return "edituser";
        }
        catch (Throwable ex) {
            //throw new RuntimeException(ex);
            Iterable<User> userIterable = userService.findAll();
            request.setAttribute("userList", userIterable);
            return "allclients";
        }
    }

    @RequestMapping(value = "/removecontracthandler", method = RequestMethod.POST)
    protected String removeContractHandler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String stringIdContract = (String) request.getParameter("idContract");
            int idcontract = Integer.parseInt(stringIdContract);
            Contract contract = contractService.findById(idcontract);
            int idUser = contract.getUser().getId();
            contractService.delete(contract);
            User user = userService.findById(idUser);
            request.setAttribute("user", user);
            return "edituser";
        }
        catch (Throwable ex) {
            //throw new RuntimeException(ex);
            Iterable<User> userIterable = userService.findAllClients();
            request.setAttribute("userList", userIterable);
            return "allclients";
        }
    }

    @RequestMapping(value = "/changeplan", method = RequestMethod.GET)
    protected String changePlan(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String stringIdContract = (String) request.getParameter("idContract");
        int idContract = Integer.parseInt(stringIdContract);
        Contract contract = contractService.findById(idContract);
        request.setAttribute("contract", contract);
        List<Plan> planList = planService.getNotArchival();
        request.setAttribute("planList", planList);
        return "changeplan";
    }

    @RequestMapping(value = "/changeoptions", method = RequestMethod.GET)
    protected String changeOptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String stringIdContract = (String) request.getParameter("idContract");
        int idContract = Integer.parseInt(stringIdContract);
        Contract contract = contractService.findById(idContract);
        request.setAttribute("contract", contract);
        List<Option> optionList = contractService.getAvailableOptions(contract);
        request.setAttribute("optionList", optionList);
        return "changeoptions";
    }

    @RequestMapping(value = "/changeplanhandler", method = RequestMethod.POST)
    protected String changePlanHandler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String stringIdContract = (String) request.getParameter("idContract");
            int idContract = Integer.parseInt(stringIdContract);
            Contract contract = contractService.findById(idContract);
            String stringIdPlan = (String) request.getParameter("idPlan");
            int idPlan = Integer.parseInt(stringIdPlan);
            Plan plan = planService.findById(idPlan);
            contract.setPlan(plan);
            contractService.clearContractOptions(contract);

            //request.setAttribute("contract", contract);

            contractService.save(contract);

            //List<Plan> planList = planService.getNotArchival();
            //request.setAttribute("planList", planList);
            User user = contract.getUser();
            request.setAttribute("user", user);
            return "edituser";
        } catch (Throwable ex) {
                //throw new RuntimeException(ex);
                Iterable<User> userIterable = userService.findAllClients();
                request.setAttribute("userList", userIterable);
                return "allclients";
            }
    }

    @RequestMapping(value = "/changeoptionshandler", method = RequestMethod.POST)
    protected String changeOptionsHandler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String stringIdContract = (String) request.getParameter("idContract");
            int idContract = Integer.parseInt(stringIdContract);
            Contract contract = contractService.findById(idContract);

            List<Option> listConnectedOptions = new ArrayList<Option>();
            String[] stringConnectedIdOptions = request.getParameterValues("connected");
            if (stringConnectedIdOptions != null) {
                for (String strIdConnectedOption : stringConnectedIdOptions) {
                    int idConnectedOption = Integer.parseInt(strIdConnectedOption);
                    Option connectedOption = optionService.findById(idConnectedOption);
                    listConnectedOptions.add(connectedOption);
                }
            }


                if (planService.isOptionsCompatible(listConnectedOptions) == false) throw new Exception();
                contract.setContractOptions(listConnectedOptions);
                contractService.save(contract);

            User user = contract.getUser();
            request.setAttribute("user", user);
            return "edituser";
        } catch (Throwable ex) {
            //throw new RuntimeException(ex);
            Iterable<User> userIterable = userService.findAllClients();
            request.setAttribute("userList", userIterable);
            return "allclients";
        }
    }

}