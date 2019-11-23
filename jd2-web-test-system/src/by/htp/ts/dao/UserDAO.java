package by.htp.ts.dao;

import com.shevlik.bd.pool.ConnectionPool;

import by.htp.ts.bean.User;

public interface UserDAO {
	User authorization(String login, String password, ConnectionPool connectionPool) throws DAOException;
	boolean registration(User user, ConnectionPool connectionPool) throws DAOException;
}
