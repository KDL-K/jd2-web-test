package by.htp.ts.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shevlik.bd.pool.ConnectionPool;

public interface Command {
	void execute(HttpServletRequest request, HttpServletResponse response, ConnectionPool connectionPool)throws ServletException, IOException;
}
