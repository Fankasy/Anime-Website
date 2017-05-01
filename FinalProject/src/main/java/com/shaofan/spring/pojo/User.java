package com.shaofan.spring.pojo;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.*;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import sun.misc.*;
@Entity
@Table(name="user",catalog="anime")
public class User extends Person implements java.io.Serializable{
	
	private long userId;
	
	
	private String userName;
	
	
	private String password;
	
	private String email;
	private String fileName;

	
	private Set<Role> roles=new HashSet<Role>(0);
	
	
	private Set<Group>partInGroups=new HashSet<Group>(0);
	
	
	private Set<Group>foundroups=new HashSet<Group>(0);
	
	
	private Set<Post>posts=new HashSet<Post>(0);
	
	
	private Set<UserRateAnime>rateAnimes=new HashSet<UserRateAnime>(0);
	
	
	private Set<VirtualPeopleComment>virtualPeopleComments=new HashSet<VirtualPeopleComment>(0);
	
	
	private CommonsMultipartFile photo; 
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="commentUser")
	public Set<VirtualPeopleComment> getVirtualPeopleComments() {
		return virtualPeopleComments;
	}
	public void setVirtualPeopleComments(Set<VirtualPeopleComment> virtualPeopleComments) {
		this.virtualPeopleComments = virtualPeopleComments;
	}
	
	
	@Transient
	public CommonsMultipartFile getPhoto() {
		return photo;
	}
	public void setPhoto(CommonsMultipartFile photo) {
		this.photo = photo;
	}
	@Column(name="email",nullable=false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@PrimaryKeyJoinColumn(name="USER_ID")
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	@Column(name="userName",nullable=false,unique=true)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name="password",nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinTable(name="user_role",catalog="anime",joinColumns={@JoinColumn(name="USER_ID",nullable=false)}
	,inverseJoinColumns={@JoinColumn(name="ROLE_ID",nullable=false)})
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	private String encode(String str) {
        BASE64Encoder encoder = new BASE64Encoder();
        str = new String(encoder.encodeBuffer(str.getBytes()));
        return str;
    }
	private String decode(String str){
		BASE64Decoder decoder=new BASE64Decoder();
		try {
            str = new String(decoder.decodeBuffer(str));
        } catch (IOException e) {
            e.printStackTrace();
        }    
		return str;
	}

	@OneToMany(fetch=FetchType.LAZY,mappedBy="postUser")
	public Set<Post> getPosts() {
		return posts;
	}
	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
	@ManyToMany(fetch=FetchType.LAZY,mappedBy="groupMember" ,cascade=CascadeType.ALL)
	public Set<Group> getPartInGroups() {
		return partInGroups;
	}
	public void setPartInGroups(Set<Group> partInGroups) {
		this.partInGroups = partInGroups;
	}
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="initiator",cascade=CascadeType.ALL)
	public Set<Group> getFoundroups() {
		return foundroups;
	}
	public void setFoundroups(Set<Group> foundroups) {
		this.foundroups = foundroups;
	}
	@OneToMany(fetch=FetchType.LAZY,mappedBy="pk.user",cascade=CascadeType.ALL)
	public Set<UserRateAnime> getRateAnimes() {
		return rateAnimes;
	}
	public void setRateAnimes(Set<UserRateAnime> rateAnimes) {
		this.rateAnimes = rateAnimes;
	}

	
	@Transient
	public boolean isAdmin(){
		Set<Role>roles=this.getRoles();
		Iterator<Role> it=roles.iterator();
		while(it.hasNext()){
			Role role= it.next();
			if(role.getRoleName().equals("ADMIN_ROLE")){
				return true;
			}
		}
		return false;
	}
	@Column(name="fileName")
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
}
