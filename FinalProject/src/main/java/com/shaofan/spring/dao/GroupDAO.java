package com.shaofan.spring.dao;
import java.util.List;

import org.hibernate.*;
import com.shaofan.spring.exception.WholeException;
import com.shaofan.spring.pojo.*;
public class GroupDAO extends DAO{
	public GroupDAO(){}
	public List<Group> getAllGroups() throws WholeException{
		try{
			begin();
			Query q=getSession().createQuery("from Group");
			List<Group>groups=q.list();
			commit();
			return groups;
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
	public Group addGroup(Group g) throws WholeException{
		try{
			begin();
			getSession().save(g);
			commit();
			return g;
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
	public void deleteGroup(Group g)throws WholeException{
		try{
			begin();
			getSession().delete(g);
			commit();
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
	public Group getGroupById(long id)throws WholeException{
		try{
			begin();
			Query q=getSession().createQuery("from Group where groupId=:groupId");
			q.setLong("groupId", id);
			Group group=(Group)q.uniqueResult();
			commit();
			return group;
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
}
