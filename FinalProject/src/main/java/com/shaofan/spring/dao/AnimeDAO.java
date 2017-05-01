package com.shaofan.spring.dao;


import java.util.List;

import org.hibernate.*;
import com.shaofan.spring.exception.WholeException;
import com.shaofan.spring.pojo.Anime;

public class AnimeDAO extends DAO{
	
	public AnimeDAO(){
		
	}
	public List<Anime> getALLAnimes()throws WholeException{
		try{
			begin();
			Query q= getSession().createQuery("from Anime");
			List<Anime> animes=q.list();
			commit();
			return animes;
		}
		catch (HibernateException e) {
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
	public Anime addAnime(Anime anime) throws WholeException{
		try{
			begin();
			getSession().save(anime);
			commit();
			return anime;
		}catch (HibernateException e) {
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
	public void deleteAnime(Anime anime)throws WholeException{
		try{
			begin();
			getSession().delete(anime);
			commit();
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
	public Anime getAnimeById(long id)throws WholeException{
		try{
			begin();
			Query q=getSession().createQuery("from Anime where animeID=:animeID");
			q.setLong("animeID", id);
			Anime anime=(Anime)q.uniqueResult();
			return anime;
			
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}

	public Anime updateAnime(Anime a,Anime b)throws WholeException{
		try{
			
			begin();
			a.setTitle(b.getTitle());
			a.setArtSupervising(b.getArtSupervising());
			a.setBeginDate(b.getBeginDate());
			a.setEndDate(b.getEndDate());
			a.setDescription(b.getDescription());
			a.setFileName(b.getFileName());
			a.setNumberOfEpisodes(b.getNumberOfEpisodes());
			a.setPlayTV(b.getPlayTV());
			a.setProduction(b.getProduction());
			a.setAnimePhoto(b.getAnimePhoto());

			
			getSession().update(a);
			
			commit();
			return a;
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
	public void update(Anime anime)throws WholeException{
		try{
			begin();
			getSession().update(anime);
			commit();
			
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
	public List<Anime> search(String key,String searchType)throws WholeException{
		try{
			begin();
			Query q=getSession().createQuery("from Anime AS m where m."+searchType+" LIKE ?");
			q.setString(0, "%"+key+"%");
			List<Anime>animes=q.list();
			commit();
			return animes;
		}catch(HibernateException e){
			rollback();
			throw new WholeException(e.getMessage());
		}
	}
}
