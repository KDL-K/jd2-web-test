package by.htp.ts.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shevlik.bd.pool.ConnectionPool;

import by.htp.ts.bean.User;
import by.htp.ts.command.Command;
import by.htp.ts.service.ClientService;
import by.htp.ts.service.ServiceException;
import by.htp.ts.service.ServiceFactory;

public class RegistrationCommand implements Command {
	public void execute(HttpServletRequest request, HttpServletResponse response, ConnectionPool connectionPool)throws ServletException, IOException {
		User user=new User();
		user.setLogin(request.getParameter(CommandImplParameters.LOGIN));
		user.setPassword(request.getParameter(CommandImplParameters.PASSWORD));
		user.setEmail(request.getParameter(CommandImplParameters.EMAIL));
		user.setName(request.getParameter(CommandImplParameters.NAME));
		user.setSurname(request.getParameter(CommandImplParameters.SURNAME));
		/*user.setRole(request.getParameter(CommandImplParameters.ROLE));*/
		user.setSex(request.getParameter(CommandImplParameters.SEX));
		user.setAge(Integer.parseInt(request.getParameter(CommandImplParameters.AGE)));
		
		String goToPage;
		boolean isRegistered;
		try {
			ServiceFactory service=ServiceFactory.getInstance();
			ClientService clientS=service.getClientS();
			isRegistered=clientS.register(user, connectionPool);
			if(isRegistered) {
				request.setAttribute("registered", "Congratulations! You have been registered. Please log in");
				goToPage="index.jsp";
			}else {
				request.setAttribute("unregistered", "Entered login is exist. Please try again.");
				goToPage="/WEB-INF/jsp/registration.jsp";
			}
		}catch(ServiceException e) {
			//log
			request.setAttribute("errorMessage", "Error of registration");
			goToPage="/WEB-INF/jsp/registration.jsp";
		}
		
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(goToPage);
		requestDispatcher.forward(request, response);
		
	}

}
