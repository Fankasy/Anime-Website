package com.shaofan.spring.pojo;
import javax.persistence.*;
@Embeddable
public class UserAnimeId implements java.io.Serializable {
	public User user;
	public Anime anime;
	@ManyToOne()
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne()
	public Anime getAnime() {
		return anime;
	}
	public void setAnime(Anime anime) {
		this.anime = anime;
	}
	
	
	
	
	@Override
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAnimeId that = (UserAnimeId) o;

        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (anime != null ? !anime.equals(that.anime) : that.anime != null)
            return false;

        return true;
    }

    @Override
	public int hashCode() {
        int result;
        result = (user != null ? user.hashCode() : 0);
        result = 31 * result + (anime != null ? anime.hashCode() : 0);
        return result;
    }

}
