package com.shaofan.spring.pojo;
import java.util.Date;
import javax.persistence.*;
@Entity
@Table(name="user_rateAnime",catalog="anime")
@AssociationOverrides({
	@AssociationOverride(name = "pk.user",
		joinColumns = @JoinColumn(name ="USER_ID")),
	@AssociationOverride(name = "pk.anime",
		joinColumns = @JoinColumn(name = "ANIME_ID")) })
public class UserRateAnime implements java.io.Serializable {
	private UserAnimeId pk=new UserAnimeId();
	private Date rateTime;
	private float ratePoint;
	private String comment;
	
	@EmbeddedId
	public UserAnimeId getUserAnimeId() {
		return pk;
	}
	public void setUserAnimeId(UserAnimeId pk) {
		this.pk = pk;
	}
	@Transient
	public User getUser(){
		return getPk().getUser();
	}
	public void setUser(User user){
		getPk().setUser(user);
	}
	@Transient
	public Anime getAnime(){
		return getPk().getAnime();
	}
	public void setAnime(Anime anime){
		getPk().setAnime(anime);
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="rate_time",nullable=false)
	public Date getRateTime() {
		return rateTime;
	}
	public void setRateTime(Date rateTime) {
		this.rateTime = rateTime;
	}
	@Column(name="ratepoint")
	public float getRatePoint() {
		return ratePoint;
	}
	public void setRatePoint(float ratePoint) {
		this.ratePoint = ratePoint;
	}
	@Column(name="comment")
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public UserAnimeId getPk() {
		return pk;
	}
	public void setPk(UserAnimeId pk) {
		this.pk = pk;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		UserRateAnime that = (UserRateAnime) o;

		if (getPk() != null ? !getPk().equals(that.getPk())
				: that.getPk() != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
	

}
