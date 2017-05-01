package com.shaofan.spring.dao;
import java.util.List;

import org.hibernate.*;
import com.shaofan.spring.exception.WholeException;
import com.shaofan.spring.pojo.*;
public class PostDAO extends DAO {
	public PostDAO(){}
	public List<Post>getAllPost() throws WholeException{
		try{
			begin();
			Query q=getSession().createQuery("from Post");
			List<Post >posts =q.list();
			commit();
			return posts;
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
	
	public List<Post> getPostByUser(User u) throws WholeException{
		try{
			begin();
			Query q=getSession().createQuery("from Post where userId =:userId");
			q.setLong("userId", u.getUserId());
			List<Post>posts=q.list();
			commit();
			return posts;
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
			
		}
	}
	
	public List<Post>getPostByGroup(Group g)throws WholeException{
		try{
			begin();
			Query q=getSession().createQuery("from Post where groupId =:groupId");
			q.setLong("groupId", g.getGroupId());
			List<Post>posts=q.list();
			commit();
			return posts;
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
	public Post addPost(Post p)throws WholeException{
		try{
			begin();
			getSession().save(p);
			commit();
			return p;
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
	public void deletePost(Post p)throws WholeException{
		try{
			begin();
			getSession().delete(p);
			commit();
			
		}catch(HibernateException e){
			throw new WholeException(e.getMessage());
		}
	}
	public Post getPostById(long id)throws WholeException{
		try{
			begin();
			Query q=getSession().createQuery("from Post where postId=:postId");
			q.setLong("postId", id);
			Post post=(Post)q.uniqueResult();
			commit();
			return post;
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
	public void update(Post post)throws WholeException{
		try{
			begin();
			getSession().update(post);
			commit();
			
		}catch(HibernateException e){
			throw new WholeException(e.getMessage());
		}
	}
 }
