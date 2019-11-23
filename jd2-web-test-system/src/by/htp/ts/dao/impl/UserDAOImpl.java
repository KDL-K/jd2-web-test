package by.htp.ts.dao.impl;

import by.htp.ts.bean.User;
import by.htp.ts.dao.DAOException;
import by.htp.ts.dao.UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.shevlik.bd.pool.ConnectionPool;

public class UserDAOImpl implements UserDAO{
	public User authorization(String login, String password, ConnectionPool connectionPool) throws DAOException{
		Connection connection=connectionPool.takeConnection();
		User user=new User();
		Statement st=null;
		ResultSet rs=null;
		try {
			st=connection.createStatement();
			rs=st.executeQuery("SELECT*FROM user JOIN user_details ON user.user_details_id=user_details.id ");
			while(rs.next()) {
				System.out.println("In while");
				if(login.equals(rs.getString(UserParameters.LOGIN)) && password.equals(rs.getString(UserParameters.PASSWORD))) {
					user.setLogin(login);
					user.setPassword(password);
					user.setEmail(rs.getString(UserParameters.EMAIL));
					user.setName(rs.getString(UserParameters.NAME));
					user.setSurname(rs.getString(UserParameters.SURNAME));
					/*System.out.println("before role");
					user.setRole(rs.getString(UserParameters.ROLE));*/
					user.setAge(rs.getInt(UserParameters.AGE));
					return user;
				}
			}
		}catch(SQLException e) {
			throw new DAOException(e);
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
        return null;
	}
	
	public boolean registration(User user, ConnectionPool connectionPool) throws DAOException{
		Connection connection=connectionPool.takeConnection();
		String requestSelect="SELECT login FROM user";
		String requestPST1="INSERT INTO user_details(name,surname,sex,age) VALUE(?,?,?,?)";
		String requestPST2="INSERT INTO user(login,password,email,user_details_id,roles_id)"+
		                   " VALUE(?,?,?,LAST_INSERT_ID(),(SELECT id FROM roles WHERE role_title='admin'))";
		Statement st=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			st=connection.createStatement();
			rs=st.executeQuery(requestSelect);
			while(rs.next()) {
				if(user.getLogin().equals(rs.getString(UserParameters.LOGIN))){
					return false;
				}
			}
			pst=connection.prepareStatement(requestPST1);
			pst.setString(1, user.getName());
			pst.setString(2, user.getSurname());
			pst.setString(3, user.getSex());
			pst.setInt(4,user.getAge());
			pst.executeUpdate();
			pst=connection.prepareStatement(requestPST2);
			pst.setString(1, user.getLogin());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getEmail());
			pst.executeUpdate();
		}catch(SQLException e) {
			throw new DAOException(e);
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch(SQLException e) {
					//log
					throw new DAOException(e);
				}
			}
			if(st!=null) {
				try {
					st.close();
				}catch(SQLException e) {
					//log
					throw new DAOException(e);
				}
			}
		}
		return true;
	}

}
