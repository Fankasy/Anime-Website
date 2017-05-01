package com.shaofan.spring.dao;
import java.util.List;

import org.hibernate.*;
import com.shaofan.spring.exception.WholeException;
import com.shaofan.spring.pojo.*;
public class CommentDAO extends DAO{
	public CommentDAO(){
		
	}
	public List<PostComment> getAllPostComments()throws WholeException{
		try{
			begin();
			Query q=getSession().createQuery("from PostComment");
			List<PostComment>postComments=q.list();
			commit();
			return postComments;
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
	
	public List<VirtualPeopleComment> getAllVirtualPeopleComments()throws WholeException{
		try{
			begin();
			Query q=getSession().createQuery("from VirtualPeopleComment");
			List<VirtualPeopleComment>virtualPeopleComments=q.list();
			commit();
			return virtualPeopleComments;
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
	
	
	
	public PostComment addPostComment(PostComment p)throws WholeException{
		try{
			begin();
			getSession().save(p);
			commit();
			return p;
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage()	);
		}
	}
	public VirtualPeopleComment addVirtualPeopleComment(VirtualPeopleComment v)throws WholeException{
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
	public void deletePostComment(PostComment p)throws WholeException{
		try{
			begin();
			getSession().delete(p);
			commit();
			
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
	
	public void deleteVirtualPeopleComment(VirtualPeopleComment v)throws WholeException{
		try{
			begin();
			getSession().delete(v);
			commit();
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
}
