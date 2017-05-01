package com.shaofan.spring.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
@Entity
@Table(name="anime",catalog="anime")
public class Anime implements java.io.Serializable{
	public Anime(){
		super();
	}
	
	private long animeID;
	
	
	private String title;
	
	private CommonsMultipartFile animePhoto; 
	
	
	private String fileName;

	private Set<UserRateAnime> rates=new HashSet<UserRateAnime>();
	private String artSupervising;
	
	
	private String production;
	
	
	
	private Date beginDate;
	
	
	private Date endDate;
	private String description;
	
	private int numberOfEpisodes;
	
	
	private String playTV;
	
	private Set<ActualPerson>performers=new HashSet<ActualPerson>();
	
	private Set<ActualPerson>directors=new HashSet<ActualPerson>();
	
	private Set<ActualPerson>screenplayer=new HashSet<ActualPerson>();
	
	private Set<ActualPerson>storyBoarder=new HashSet<ActualPerson>();
	private Set<VirtualPerson>animeCharacter=new HashSet<VirtualPerson>();
	
	private Set<ActualPerson>musician=new HashSet<ActualPerson>();
	private Set<ActualPerson>painter=new HashSet<ActualPerson>();
	private Set<ActualPerson>supervisor=new HashSet<ActualPerson>();
	
	@ManyToMany(fetch=FetchType.EAGER,mappedBy="performAnime",cascade=CascadeType.ALL)

	public Set<ActualPerson> getPerformers() {
		return performers;
	}

	public void setPerformers(Set<ActualPerson> performers) {
		this.performers = performers;
	}
	@ManyToMany(fetch=FetchType.EAGER,mappedBy="directorAnime",cascade=CascadeType.ALL)
	public Set<ActualPerson> getDirectors() {
		return directors;
	}

	
	public void setDirectors(Set<ActualPerson> directors) {
		this.directors = directors;
	}
	
	@ManyToMany(fetch=FetchType.EAGER,mappedBy="screenplayAnime",cascade=CascadeType.ALL)
	public Set<ActualPerson> getScreenplayer() {
		return screenplayer;
	}

	public void setScreenplayer(Set<ActualPerson> screenplayer) {
		this.screenplayer = screenplayer;
	}

	@ManyToMany(fetch=FetchType.EAGER,mappedBy="stortyBoardAnime",cascade=CascadeType.ALL)
	public Set<ActualPerson> getStoryBoarder() {
		return storyBoarder;
	}

	public void setStoryBoarder(Set<ActualPerson> storyBoarder) {
		this.storyBoarder = storyBoarder;
	}

	@ManyToMany(fetch=FetchType.EAGER,mappedBy="musicianAnime",cascade=CascadeType.ALL)
	public Set<ActualPerson> getMusician() {
		return musician;
	}

	public void setMusician(Set<ActualPerson> musician) {
		this.musician = musician;
	}
	
	

	
	
	@ManyToMany(fetch=FetchType.EAGER,mappedBy="supervisorAnime",cascade=CascadeType.ALL)
	public Set<ActualPerson> getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Set<ActualPerson> supervisor) {
		this.supervisor = supervisor;
	}

	@ManyToMany(fetch=FetchType.EAGER,mappedBy="painterAnime",cascade=CascadeType.ALL)

	public Set<ActualPerson> getPainter() {
		return painter;
	}

	public void setPainter(Set<ActualPerson> painter) {
		this.painter = painter;
	}

	
	@Column(name="title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	@Column (name="art_supervising")
	public String getArtSupervising() {
		return artSupervising;
	}

	public void setArtSupervising(String artSupervising) {
		this.artSupervising = artSupervising;
	}

	@Column(name="production")
	public String getProduction() {
		return production;
	}

	public void setProduction(String production) {
		this.production = production;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="begindate")

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="enddate")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name="number_of_episodes")
	public int getNumberOfEpisodes() {
		return numberOfEpisodes;
	}

	public void setNumberOfEpisodes(int numberOfEpisodes) {
		this.numberOfEpisodes = numberOfEpisodes;
	}

	
	@Column(name="play_TV")
	public String getPlayTV() {
		return playTV;
	}

	public void setPlayTV(String playTV) {
		this.playTV = playTV;
	}
	
	
	
	
	
	
	
	
	@ManyToMany(fetch=FetchType.LAZY,mappedBy="animeCharacters")
	public Set<VirtualPerson> getAnimeCharacter() {
		return animeCharacter;
	}
	

	
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="pk.anime")
	public Set<UserRateAnime> getRates() {
		return rates;
	}

	public void setRates(Set<UserRateAnime> rates) {
		this.rates = rates;
	}

	public void setAnimeCharacter(Set<VirtualPerson> animeCharacter) {
		this.animeCharacter = animeCharacter;
	}

	@Transient
	public CommonsMultipartFile getAnimePhoto() {
		return animePhoto;
	}

	public void setAnimePhoto(CommonsMultipartFile animePhoto) {
		this.animePhoto = animePhoto;
	}

	
	@Column(name="filename")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ANIME_ID",unique=true,nullable=false)

	public long getAnimeID() {
		return animeID;
	}

	public void setAnimeID(long animeID) {
		this.animeID = animeID;
	}
	@Column(name="description")

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	

}
