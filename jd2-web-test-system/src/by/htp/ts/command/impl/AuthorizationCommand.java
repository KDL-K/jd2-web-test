package by.htp.ts.command.impl;

import java.io.IOException;
import by.htp.ts.service.ClientService;
import by.htp.ts.service.ServiceException;
import by.htp.ts.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shevlik.bd.pool.ConnectionPool;

import by.htp.ts.bean.User;
import by.htp.ts.command.Command;

public class AuthorizationCommand implements Command {
	public void execute(HttpServletRequest request, HttpServletResponse response, ConnectionPool connectionPool) throws ServletException, IOException {
		User user;
		String login;
		String password;
		
		login=request.getParameter(CommandImplParameters.LOGIN);
		password=request.getParameter(CommandImplParameters.PASSWORD);
		
		String goToPage;
		try {
			ServiceFactory service=ServiceFactory.getInstance();
			ClientService clientS=service.getClientS();
			user=clientS.signIn(login, password, connectionPool);
			if(user!=null) {
				request.setAttribute("user", user);
				goToPage="/WEB-INF/jsp/welcome.jsp";
			}else {
				request.setAttribute("errorMessage", "Incorrect login or password");
				goToPage="/WEB-INF/jsp/registration.jsp";
			}
		}catch(ServiceException e) {
			//log
			request.setAttribute("errorMessage", "Error of authorization");
			goToPage="/WEB-INF/jsp/registration.jsp";
		}
		
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(goToPage);
		requestDispatcher.forward(request, response);
		
	}

}
