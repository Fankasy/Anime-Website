package com.shaofan.spring.pojo;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
@Entity
@Table(name="virtualperson")
public class VirtualPerson extends Person{
	
	
	private long virtualPersonId;
	
	
	private Set<Anime>animeCharacters=new HashSet<Anime>();
	
	
	
	private CommonsMultipartFile photo; 
	
	private String fileName;
	private Set<VirtualPeopleComment>comments=new HashSet<VirtualPeopleComment>();
	


	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="Anime_character",catalog="anime",joinColumns={
			@JoinColumn(name="VIRTUALPERSON_ID",nullable=false)},inverseJoinColumns={@JoinColumn(name="ANIME_ID",nullable=false)})
	public Set<Anime> getAnimeCharacters() {
		return animeCharacters;
	}
	public void setAnimeCharacters(Set<Anime> animeCharacters) {
		this.animeCharacters = animeCharacters;
	}
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="charecter")
	public Set<VirtualPeopleComment> getComments() {
		return comments;
	}
	public void setComments(Set<VirtualPeopleComment> comments) {
		this.comments = comments;
	}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@PrimaryKeyJoinColumn(name="PERSON_ID")
	public long getVirtualPersonId() {
		return virtualPersonId;
	}
	public void setVirtualPersonId(long virtualPersonId) {
		this.virtualPersonId = virtualPersonId;
	}
	
	

	public String getFileName() {
		return fileName;
	}
	
	
	@Transient
	public CommonsMultipartFile getPhoto() {
		return photo;
	}
	
	
	public void setPhoto(CommonsMultipartFile photo) {
		this.photo = photo;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
}
