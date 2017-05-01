package com.shaofan.spring.dao;
import org.hibernate.*;


import com.shaofan.spring.exception.WholeException;
import com.shaofan.spring.pojo.Role;
public class RoleDAO extends DAO{
	public RoleDAO(){
		
	}
	public Role addRole(Role role) throws WholeException{
		try{
			begin();
			getSession().save(role);
			commit();
			return role;
		}
		catch(HibernateException h){
			rollback();
			throw new WholeException("d"+h.getMessage());
		}
		
	}
	
	public void delete(Role role) throws WholeException {
		try {
			begin();
			getSession().delete(role);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new WholeException("Could not delete user " );
		}
	}
	public Role getAdminRole()throws WholeException{
		try{
			begin();
			Query q=getSession().createQuery("from Role where roleName=:roleName");
			q.setString("roleName", "ADMIN_ROLE");
			Role role=(Role) q.uniqueResult();
			commit();
			return role;
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
	public Role getUserRole()throws WholeException{
		try{
			begin();
			Query q=getSession().createQuery("from Role where roleName=:roleName");
			q.setString("roleName", "USER_ROLE");
			Role role=(Role) q.uniqueResult();
			commit();
			return role;
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
	public Role getGroupRole()throws WholeException{
		try{
			begin();
			Query q=getSession().createQuery("from Role where roleName=:roleName");
			q.setString("roleName", "GROUPMANAGER_ROLE");
			Role role=(Role) q.uniqueResult();
			commit();
			return role;
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
}
