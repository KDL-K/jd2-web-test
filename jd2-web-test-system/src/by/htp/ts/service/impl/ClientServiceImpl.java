package by.htp.ts.service.impl;

import by.htp.ts.service.ClientService;
import by.htp.ts.service.ServiceException;

import com.shevlik.bd.pool.ConnectionPool;

import by.htp.ts.bean.User;
import by.htp.ts.dao.*;

public class ClientServiceImpl implements ClientService{

	@Override
	public User signIn(String login, String password,ConnectionPool connectionPool) throws ServiceException {
		checkNull(login, password);
		User user;
		try {
			DAOFactory daoFactory=DAOFactory.getInstance();
			UserDAO userDao=daoFactory.getUserDao();
			user=userDao.authorization(login, password, connectionPool);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		return user;
	}

	@Override
	public boolean register(User user, ConnectionPool connectionPool) throws ServiceException{
		checkNull(user.getLogin(), user.getPassword());
		boolean isRegistered;
		try {
			DAOFactory daoFactory=DAOFactory.getInstance();
			UserDAO userDao=daoFactory.getUserDao();
			isRegistered=userDao.registration(user, connectionPool);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		if(isRegistered) return true;
		else return false;
	}
	
	private void checkNull(String login, String password) throws ServiceException {
		if(login==null || login.isEmpty()) {
			throw new ServiceException("Login isn't specified.");
		}
		if(password==null || password.isEmpty()) {
			throw new ServiceException("Password isn't specified.");
		}
	}
	
	
	

}
