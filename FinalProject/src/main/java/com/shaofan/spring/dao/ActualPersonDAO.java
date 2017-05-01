package com.shaofan.spring.dao;
import java.util.List;

import org.hibernate.*;
import com.shaofan.spring.exception.WholeException;
import com.shaofan.spring.pojo.*;

public class ActualPersonDAO extends DAO {
	public ActualPersonDAO(){}
	
	public List<ActualPerson> getAllActualPerson() throws WholeException{
		try{
			begin();
			Query q=getSession().createQuery("from ActualPerson");
			List<ActualPerson>actualPersons=q.list();
			commit();
			return actualPersons;
		}
		catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
	public ActualPerson addActualPerson(ActualPerson a)throws WholeException{
		try{
			begin();
			getSession().save(a);
			commit();
			return a;
		}catch(HibernateException e){
			rollback();
			throw new WholeException("Exception while creating user: " + e.getMessage());
		}
	}
	
	public void deleteActualPerson(ActualPerson a)throws WholeException{
		try{
			begin();
			getSession().delete(a);
			commit();
			
		}catch(HibernateException e){
			
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
	public ActualPerson getPersonById(long id)throws WholeException{
		try{
			begin();
			Query q=getSession().createQuery("from ActualPerson where actualPersonId=:actualPersonId");
			q.setLong("actualPersonId", id);
			ActualPerson actualPerson=(ActualPerson)q.uniqueResult();
			commit();
			return actualPerson;
			
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
	
	public ActualPerson updateActualPerson(ActualPerson a,ActualPerson b)throws WholeException{
		try{
			begin();
			a.setFname(b.getFname());
			a.setLname(b.getLname());
			a.setAge(b.getAge());
			a.setBirthday(b.getBirthday());
			a.setBornplace(b.getBornplace());
			a.setGender(b.getGender());
			a.setFileName(b.getFileName());
			a.setHeight(b.getHeight());
			a.setWeight(b.getWeight());
			a.setPhoto(b.getPhoto());
			
			getSession().update(a);
			commit();
			return a;
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
	public void update(ActualPerson a)throws WholeException{
		try{
			begin();
			getSession().update(a);
			commit();
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
	

}
