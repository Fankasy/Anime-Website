package com.shaofan.spring.dao;

import java.util.List;

import org.hibernate.*;
import com.shaofan.spring.exception.WholeException;
import com.shaofan.spring.pojo.User;

public class UserDAO extends DAO {

	public UserDAO() {
	}

	 
	public List<User> get(String key,String flag) throws WholeException {
		try {
			begin();
			String q="";
			if (flag.equalsIgnoreCase("first")) {
                q = "from User where fname= :fname";
            } else if (flag.equalsIgnoreCase("last")) {
                q = "from User where lname= :fname";
            } else if (flag.equalsIgnoreCase("email")) {
                q = "from User where email= :fname";
            }
			Query q1 = getSession().createQuery(q);
			q1.setString("fname", key);		
			List<User> user =q1.list();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new WholeException("Could not get user with " + key, e);
		}
	}

	public User register(User u)
			throws WholeException {
		try {
			begin();
			System.out.println("inside DAO");			
			getSession().save(u);
			commit();
			return u;

		} catch (HibernateException e) {
			rollback();
			throw new WholeException("Exception while creating user: " + e.getMessage());
		}
	}

	public void delete(User user) throws WholeException {
		try {
			begin();
			getSession().delete(user);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new WholeException("Could not delete user " + user.getFname(), e);
		}
	}
}