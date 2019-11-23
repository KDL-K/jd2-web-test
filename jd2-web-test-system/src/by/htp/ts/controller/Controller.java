package by.htp.ts.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shevlik.bd.pool.ConnectionPool;

import by.htp.ts.command.CommandProvider;



public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String PARAMETER_NAME="command";
    private CommandProvider provider=new CommandProvider();  
    private ConnectionPool connectionPool;

    public Controller() {
        super();

    }
    
    @Override
	public void init(){
    	connectionPool=new ConnectionPool();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String commandStr=request.getParameter(PARAMETER_NAME);
		provider.getCommand(commandStr).execute(request, response, connectionPool);
	}
	
	@Override
	public void destroy(){
    	connectionPool.closeConnectionPool();
    }

}
