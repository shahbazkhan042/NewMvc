package com.lti;
import java.sql.*;

public class UserDAO {

	DatabaseConnector db = new DatabaseConnector();
	
	public User authenticateUser(User u)
	{
		User user = null;
		String email=u.getEmail();
		String emailD="";
		String password=u.getPassword();
		String passwordD="";
		
		try{
			String query="select email,password from zuser where email='"+email+"'";
			ResultSet rs = db.getResultSet(query);
			
			if(rs.next())
			{
				emailD=rs.getString("email");
				passwordD=rs.getString("password");
				
			}
			if(emailD.equals(email) && passwordD.equals(password))
			{
				user=getUserByEmail(email);
			}
			else{
				user=null;
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			db.closeConnection();
		}
		return user;
	}
	
	public String addUser(User u)
	{
		String result="";
		
		try{
			String query = "insert into zuser values(?,?,?,?)";
			PreparedStatement pstmt = db.getPreparedStatement(query);
			pstmt.setInt(1,u.getId());
			pstmt.setString(2, u.getName());
			pstmt.setString(3, u.getEmail());
			pstmt.setString(4, u.getPassword());
			
			int i = pstmt.executeUpdate();
			
			if(i==1)
			{
				result=Results.SUCCESS;
			}
			else
			{
				result = Results.FAILURE;
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			result=Results.PROBLEM;
		}finally
		{
			db.closeConnection();
		}
		return result;
	}
	
	public User getUserByEmail(String email)
	{
		User u = null;
		try{
			String query="select * from zuser where email='"+email+"'";
			ResultSet rs = db.getResultSet(query);
			if(rs.next())
			{
				u=new User();
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("name"));
				u.setEmail(rs.getString("email"));
				u.setPassword(rs.getString("password"));
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}finally
		{
			db.closeConnection();
		}
		return u;
		
	}
	
	
	
	public User getUserById(int id)
	{
		User u = null;
		try{
			String query="select * from zuser where id='"+id+"'";
			ResultSet rs = db.getResultSet(query);
			if(rs.next())
			{
				u=new User();
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("name"));
				u.setEmail(rs.getString("email"));
				u.setPassword(rs.getString("password"));
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}finally
		{
			db.closeConnection();
		}
		return u;
		
	}
}
