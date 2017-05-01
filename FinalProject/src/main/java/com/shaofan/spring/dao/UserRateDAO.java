package com.shaofan.spring.dao;
import java.util.List;

import org.hibernate.*;
import com.shaofan.spring.exception.WholeException;
import com.shaofan.spring.pojo.Anime;
import com.shaofan.spring.pojo.*;
public class UserRateDAO extends DAO{
	public UserRateDAO(){}
	public List<UserRateAnime> getAllRates()throws WholeException{
		try{
			begin();
			Query q=getSession().createQuery("from UserRateAnime");
			List<UserRateAnime>rates =q.list();
			commit();
			return rates;
			
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
	public List<UserRateAnime>getRatesByUser(User u)throws WholeException{
		try{
			begin();
			Query q=getSession().createQuery("from UserRateAnime where userId=:userId");
			q.setLong("userId", u.getUserId());
			List<UserRateAnime>rates=q.list();
			commit();
			return rates;
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
	
	public List<UserRateAnime>getRatesByAnime(Anime a)throws WholeException{
		try{
			begin();
			Query q=getSession().createQuery("from UserRateAnime where animeId=:animeId");
			q.setLong("animeId", a.getAnimeID());
			List<UserRateAnime>rates=q.list();
			commit();
			return rates;
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
	
	public UserRateAnime addRate(UserRateAnime u)throws WholeException{
		try{
			begin();
			getSession().save(u);
			commit();
			return u;
			
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
	public void deleteRate(UserRateAnime u)throws WholeException{
		try{
			begin();
			getSession().delete(u);
			commit();
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
}
