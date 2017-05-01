package com.shaofan.spring.dao;
import java.util.List;

import org.hibernate.*;
import com.shaofan.spring.exception.WholeException;
import com.shaofan.spring.pojo.*;
public class VirtualPersonDAO extends DAO{
	public VirtualPersonDAO(){}
	public List<VirtualPerson> getAllVirtualPerson() throws WholeException{
		try{
			begin();
			Query q=getSession().createQuery("from VirtualPerson");
			List<VirtualPerson>virtualPersons=q.list();
			commit();
			return virtualPersons;
			
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
	public VirtualPerson addVirtualPerson(VirtualPerson v) throws WholeException{
		try{
			begin();
			getSession().save(v);
			commit();
			return v;
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
	
	public void deleteVirtualPerson(VirtualPerson v)throws WholeException{
		try{
			begin();
			getSession().delete(v);
			commit();
			
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
	public VirtualPerson getPersonById(long id)throws WholeException{
		try{
			begin();
			Query q=getSession().createQuery("from VirtualPerson where virtualPersonId=:virtualPersonId");
			q.setLong("virtualPersonId", id);
			VirtualPerson VirtualPerson=(VirtualPerson)q.uniqueResult();
			commit();
			return VirtualPerson;
			
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
	
	public VirtualPerson updateVirtualPerson(VirtualPerson a,VirtualPerson b)throws WholeException{
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
	
	public void update(VirtualPerson a)throws WholeException{
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
