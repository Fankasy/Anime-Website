package com.shaofan.spring.pojo;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
@Entity
@Table(name="actualperson")
public class ActualPerson extends Person implements Serializable{
//	private String PersonType="ActualPerson";
	//performer
	
	
	private long actualPersonId;
	
	
	private Set<Anime> performAnime=new HashSet<Anime>(0);
	
	//director
	
	private Set<Anime> directorAnime=new HashSet<Anime>(0);
	
	
	//storyboard
	
	private Set<Anime> stortyBoardAnime=new HashSet<Anime>(0);
	
	//screenplay
	
	private Set<Anime>screenplayAnime=new HashSet<Anime>(0);
	
	//Music
	
	private Set<Anime>musicianAnime=new HashSet<Anime>(0);
	
	//supervising
	
	private Set<Anime>supervisorAnime=new HashSet<Anime>();
	
	//originalpainting
	
	private Set<Anime>painterAnime=new HashSet<Anime>();
	private String fileName;
	

	
	private CommonsMultipartFile photo; 
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@PrimaryKeyJoinColumn(name="ACTUALPERSON_ID")
	public long getActualPersonId() {
		return actualPersonId;
	}


	public void setActualPersonId(long actualPersonId) {
		this.actualPersonId = actualPersonId;
	}


	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinTable(name="performer_anime",catalog="anime",joinColumns={
			@JoinColumn(name="ACTUALPERSON_ID",nullable=false)},inverseJoinColumns={@JoinColumn(name="animeId",nullable=false)})
	public Set<Anime> getPerformAnime() {
		return performAnime;
	}


	public void setPerformAnime(Set<Anime> performAnime) {
		this.performAnime = performAnime;
	}


	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinTable(name="director_anime",catalog="anime",joinColumns={
			@JoinColumn(name="ACTUALPERSON_ID",nullable=false)
	},inverseJoinColumns={@JoinColumn(name="animeId",nullable=false)})
	public Set<Anime> getDirectorAnime() {
		return directorAnime;
	}


	public void setDirectorAnime(Set<Anime> directorAnime) {
		this.directorAnime = directorAnime;
	}


	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinTable(name="storyboard_anime",catalog="anime",joinColumns={
			@JoinColumn(name="ACTUALPERSON_ID",nullable=false)},inverseJoinColumns={@JoinColumn(name="animeId",nullable=false)})
	public Set<Anime> getStortyBoardAnime() {
		return stortyBoardAnime;
	}


	public void setStortyBoardAnime(Set<Anime> stortyBoardAnime) {
		this.stortyBoardAnime = stortyBoardAnime;
	}


	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinTable(name="screenplay_anime",catalog="anime",joinColumns={
			@JoinColumn(name="ACTUALPERSON_ID",nullable=false)},inverseJoinColumns={@JoinColumn(name="animeId",nullable=false)})
	public Set<Anime> getScreenplayAnime() {
		return screenplayAnime;
	}


	public void setScreenplayAnime(Set<Anime> screenplayAnime) {
		this.screenplayAnime = screenplayAnime;
	}


	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinTable(name="musician_anime",catalog="anime",joinColumns={
			@JoinColumn(name="ACTUALPERSON_ID",nullable=false)},inverseJoinColumns={@JoinColumn(name="animeId",nullable=false)})
	public Set<Anime> getMusicianAnime() {
		return musicianAnime;
	}


	public void setMusicianAnime(Set<Anime> musicianAnime) {
		this.musicianAnime = musicianAnime;
	}


	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinTable(name="supervisor_anime",catalog="anime",joinColumns={
			@JoinColumn(name="ACTUALPERSON_ID",nullable=false)},inverseJoinColumns={@JoinColumn(name="animeId",nullable=false)})
	public Set<Anime> getSupervisorAnime() {
		return supervisorAnime;
	}


	public void setSupervisorAnime(Set<Anime> supervisorAnime) {
		this.supervisorAnime = supervisorAnime;
	}

	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinTable(name="painter_anime",catalog="anime",joinColumns={
			@JoinColumn(name="ACTUALPERSON_ID",nullable=false)},inverseJoinColumns={@JoinColumn(name="animeId",nullable=false)})
	public Set<Anime> getPainterAnime() {
		return painterAnime;
	}


	public void setPainterAnime(Set<Anime> painterAnime) {
		this.painterAnime = painterAnime;
	}


	@Column(name="fileName")
	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	@Transient
	public CommonsMultipartFile getPhoto() {
		return photo;
	}


	public void setPhoto(CommonsMultipartFile photo) {
		this.photo = photo;
	}







	
	
	
	
}
