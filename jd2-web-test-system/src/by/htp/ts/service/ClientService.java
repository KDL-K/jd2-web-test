package by.htp.ts.service;

import com.shevlik.bd.pool.ConnectionPool;

import by.htp.ts.bean.User;

public interface ClientService {
	User signIn(String login, String password, ConnectionPool connectionPool) throws ServiceException;
	boolean register(User user, ConnectionPool connectionPool)throws ServiceException;
}
