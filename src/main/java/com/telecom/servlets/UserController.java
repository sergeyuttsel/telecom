package com.telecom.servlets;

import com.telecom.dao.model.User;
import com.telecom.exception.DaoException;
import com.telecom.exception.InputException;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
@Component
//@WebServlet({ "/login", "/loginhandler", "/user", "/clients", "/allcontracts", "/allclients", "/client", "/createclient",
//		"/createuserhandler", "/edituser", "/updateuser", "/createuser", "/adminpanel" })
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/allclients", method = RequestMethod.GET)
	protected String displayAllClients(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Iterable<User> userIterable = userService.findAllClients();
		request.setAttribute("userList", userIterable);
		return "allclients";

	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	protected String displayUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String stringIdUser = (String) request.getParameter("idUser");
		int idUser = Integer.parseInt(stringIdUser);
		User user = userService.findById(idUser);
		request.setAttribute("user", user);
		return "user";
	}

	@RequestMapping(value = "/edituser", method = RequestMethod.GET)
	protected String editUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String stringIdUser = (String) request.getParameter("idUser");
		int idUser = Integer.parseInt(stringIdUser);
		User user = userService.findById(idUser);
		request.setAttribute("user", user);
		return "edituser";
	}

	@RequestMapping(value = "/createuser", method = RequestMethod.GET)
	protected String createUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "createuser";
	}

	@RequestMapping(value = "/createuserhandler", method = RequestMethod.POST)
	protected String createUserHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		{
			try {
				User user = new User();
				String firstName = (String) request.getParameter("firstName");
				if (firstName == null)
					throw new InputException();
				user.setFirstName(firstName);
				String lastName = (String) request.getParameter("lastName");
				if (lastName == null)
					throw new InputException();
				user.setLastName(lastName);
				String email = (String) request.getParameter("email");
				if (email == null)
					throw new InputException();
				user.setEmail(email);
				String passport = (String) request.getParameter("passport");
				if (passport == null)
					throw new InputException();
				user.setPassport(passport);
				String adress = (String) request.getParameter("adress");
				if (adress == null)
					throw new InputException();
				user.setRole(User.Role.CLIENT);
				user.setAdress(adress);
				String birthday = (String) request.getParameter("birthday");
				Calendar calendar = Calendar.getInstance();
				try {
					calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
				} catch (Exception ex) {
				}
				user.setBirthday(calendar);
				String password = (String) request.getParameter("password");
				if (password == null)
					throw new InputException();
				user.setPassword(password);
				try {
					userService.save(user);
				} catch (Exception ex) {
					throw new Error("TELECOM_EXCEPTION Incorrect new user");
				}
				request.setAttribute("user", user);
				return "user";
			}
			// If error occur redirect on page with all plans list.
			catch (InputException ex) {
				//throw new RuntimeException(ex);
				Iterable<User> userIterable = userService.findAllClients();
				request.setAttribute("userList", userIterable);
				return "allclients";
			}

		}

	}

	@RequestMapping(value = "/updateuser", method = RequestMethod.POST)
	protected String updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Find and update that need update.
			//if Validate.isInteger(s, messageList)
			List<String> messageList = new ArrayList<String>();
			String stringIdUser = (String) request.getParameter("idUser");
			int idUser = Integer.parseInt(stringIdUser);
			User user = userService.findById(idUser);
			String firstName = (String) request.getParameter("firstName");
			if (firstName == null || firstName.isEmpty())
				throw new InputException();
			user.setFirstName(firstName);
			String lastName = (String) request.getParameter("lastName");
			if (lastName == null || lastName.isEmpty())
				throw new InputException();
			user.setLastName(lastName);
			String email = (String) request.getParameter("email");
			if (email == null || email.isEmpty())
				throw new InputException();
			user.setEmail(email);
			String passport = (String) request.getParameter("passport");
			if (passport == null || passport.isEmpty())
				throw new InputException();
			user.setPassport(passport);
			String adress = (String) request.getParameter("adress");
			if (adress == null || adress.isEmpty())
				throw new InputException();
			user.setAdress(adress);
			user.setRole(User.Role.CLIENT);
			String birthday = (String) request.getParameter("birthday");
			Calendar calendar = Calendar.getInstance();
			try {
				calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
			} catch (Exception ex) {
				throw new Error("TELECOM_EXCEPTION Incorrect update data birthday user");
			}
			user.setBirthday(calendar);
			try { userService.save(user); }  catch (Exception ex) {
				throw new Error("TELECOM_EXCEPTION Incorrect update user");
			}
			request.setAttribute("user", user);
			return "user";
		}
		// If error occur redirect on page with all plans list.
		catch (InputException ex) {
			//throw new RuntimeException(ex);
			Iterable<User> userIterable = userService.findAllClients();
			request.setAttribute("userList", userIterable);
			return "allclients";
		}
	}

}
	/*private static final long serialVersionUID = 1L;
	PlanService planService;
	OptionService optionService;
	UserService userService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servletPath = request.getServletPath();
		// Handler for display list of all clients
		if (servletPath.equals("/allclients")) {
			displayAllClients(request, response);
		} else if (servletPath.equals("/user")) {
			displayUser(request, response);
		} else if (servletPath.equals("/edituser")) {
			editUser(request, response);
		} else if (servletPath.equals("/createuser")) {
			createUser(request, response);
		} else if (servletPath.equals("/login")) {
			login(request, response);
		} else if (servletPath.equals("/adminpanel")) {
			adminPanel(request, response);
		}
		// doPost(request, response);

	}

	protected void displayUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext servletContext = getServletContext();
		String stringIdUser = (String) request.getParameter("idUser");
		int idUser = Integer.parseInt(stringIdUser);
		User user = userService.getUser(idUser);
		request.setAttribute("user", user);
		String jspPath = "/WEB-INF/jsp/user.jsp";
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jsp = null;
		String servletPath = request.getServletPath();

		// Handler for update user's parameters.
		if (servletPath.equals("/updateuser")) {
			updateUser(request, response);
		} else
		// Handler for create user.
		if (servletPath.equals("/createuserhandler")) {
			createUserHandler(request, response);
		} else if (servletPath.equals("/loginhandler")) {
			loginHandler(request, response);
		}

	}

	protected void createUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        String jspPath = "/WEB-INF/jsp/createuser.jsp";
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
        requestDispatcher.forward(request, response);
    }
	
	protected void displayAllClients(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext servletContext = getServletContext();
		String jspPath;
		List<User> userList = userService.getAllClients();
		request.setAttribute("userList", userList);
		jspPath = "/WEB-INF/jsp/allclients.jsp";
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
		requestDispatcher.forward(request, response);

	}

	protected void editUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext servletContext = getServletContext();
		String stringIdUser = (String) request.getParameter("idUser");
		int idUser = Integer.parseInt(stringIdUser);
		User user = userService.getUser(idUser);
		request.setAttribute("user", user);
		String jspPath = "/WEB-INF/jsp/edituser.jsp";
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
		requestDispatcher.forward(request, response);
	}

	protected void createUserHandler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		//float price = Float.parseFloat("wefar");
        ServletContext servletContext = getServletContext();
        String jspPath;
        try {
            User user = new User();
            String firstName = (String) request.getParameter("firstName");
			if (firstName == null)
				throw new InputException();
			user.setFirstName(firstName);
			String lastName = (String) request.getParameter("lastName");
			if (lastName == null)
				throw new InputException();
			user.setLastName(lastName);
			String email = (String) request.getParameter("email");
			if (email == null)
				throw new InputException();
			user.setEmail(email);
			String passport = (String) request.getParameter("passport");
			if (passport == null)
				throw new InputException();
			user.setPassport(passport);
			String adress = (String) request.getParameter("adress");
			if (adress == null)
				throw new InputException();
			user.setRole(User.Role.CLIENT);
			user.setAdress(adress);
			String birthday = (String) request.getParameter("birthday");
			Calendar calendar = Calendar.getInstance();
			try {
				calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
				} catch (Exception ex) {
				}
			user.setBirthday(calendar);
			String password = (String) request.getParameter("password");
			if (password == null)
				throw new InputException();
			user.setPassword(password);
            try {
                userService.createUser(user);
            } catch (DaoException | InputException ex) {
            }
            request.setAttribute("user", user);
            jspPath = "/WEB-INF/jsp/user.jsp";
        }
        // If error occur redirect on page with all plans list.
        catch (InputException ex) {
            //throw new RuntimeException(ex);
            List<User> userList = userService.getAllClients();
            request.setAttribute("userList", userList);
            jspPath = "/WEB-INF/jsp/allclients.jsp";
        }
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
        requestDispatcher.forward(request, response);
    }
	
	protected void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext servletContext = getServletContext();
		String jspPath;
		try {
			// Find and update that need update.
			//if Validate.isInteger(s, messageList)
			List<String> messageList = new ArrayList<String>();
			String stringIdUser = (String) request.getParameter("idUser");
			int idUser = Integer.parseInt(stringIdUser);
			User user = userService.getUser(idUser);
			String firstName = (String) request.getParameter("firstName");
			if (firstName == null || firstName.isEmpty())
				throw new InputException();
			user.setFirstName(firstName);
			String lastName = (String) request.getParameter("lastName");
			if (lastName == null || lastName.isEmpty())
				throw new InputException();
			user.setLastName(lastName);
			String email = (String) request.getParameter("email");
			if (email == null || email.isEmpty())
				throw new InputException();
			user.setEmail(email);
			String passport = (String) request.getParameter("passport");
			if (passport == null || passport.isEmpty())
				throw new InputException();
			user.setPassport(passport);
			String adress = (String) request.getParameter("adress");
			if (adress == null || adress.isEmpty())
				throw new InputException();
			user.setAdress(adress);
			user.setRole(User.Role.CLIENT);
			String birthday = (String) request.getParameter("birthday");
			Calendar calendar = Calendar.getInstance();
			try {
			calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
			} catch (Exception ex) {
				
			}
			user.setBirthday(calendar);
			try { userService.updateUser(user); } catch (DaoException | InputException
			ex) {
			
			} catch (Exception ex) {
			
			}
			
			request.setAttribute("user", user);
			jspPath = "/WEB-INF/jsp/user.jsp";
		}
		// If error occur redirect on page with all plans list.
		catch (InputException ex) {
			//throw new RuntimeException(ex);
			List<User> userList = userService.getAllClients();
			request.setAttribute("userList", userList);
			jspPath = "/WEB-INF/jsp/allclients.jsp";
		}
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
		requestDispatcher.forward(request, response);
	}

	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext servletContext = getServletContext();
		String jspPath = "/WEB-INF/jsp/login.jsp";
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
		requestDispatcher.forward(request, response);
	}
	
	protected void loginHandler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		//if (1==1) throw new Error("333");
		//float price = Float.parseFloat("wefar");
        ServletContext servletContext = getServletContext();
        String jspPath;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        try {
        	
        	if (email == null || password == null) throw new InputException();
        	User user = userService.getByEmail(email);
        	if (userService.checkPassword(user, password)) {
        		//if (1==1) throw new Error("111");
        		request.getSession().setAttribute("user", user);
        	}
        	
        	request.setAttribute("user", user);
            jspPath = "/WEB-INF/jsp/user.jsp";
        }
        // If error occur redirect on page with all plans list.
        catch (InputException ex) {
        	//if (1==1) throw new Error("222");
            //throw new RuntimeException(ex);
            List<User> userList = userService.getAllClients();
            request.setAttribute("userList", userList);
            jspPath = "/WEB-INF/jsp/allclients.jsp";
        }
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
        requestDispatcher.forward(request, response);
    }
	
	protected void adminPanel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext servletContext = getServletContext();
		String jspPath = "/WEB-INF/jsp/adminpanel.jsp";
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
		requestDispatcher.forward(request, response);
	}*/