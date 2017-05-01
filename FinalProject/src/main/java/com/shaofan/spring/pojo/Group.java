package com.shaofan.spring.pojo;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import javax.persistence.*;
@Entity
@Table(name="animegroup")
public class Group {
	
	private long groupId;
	
	
	private String groupName;
	
	
	private Date startTime;
	
	
	private User initiator;
	private String description;
	
	private Set<User>groupMember=new HashSet<User>(0);
	
	
	private Set<Post>posts=new HashSet<Post>(0);

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="GROUP_ID",nullable=false,unique=true)
	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	@Column(name="group_name")
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}



	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	
	public User getInitiator() {
		return initiator;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_time")
	public Date getStartTime() {
		return startTime;
	}

	

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setInitiator(User initiator) {
		this.initiator = initiator;
	}

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="group_user",catalog="anime",joinColumns={
			@JoinColumn(name="GROUP_ID",nullable=false)
	},inverseJoinColumns={@JoinColumn(name="USER_ID",nullable=false)})
	public Set<User> getGroupMember() {
		return groupMember;
	}

	public void setGroupMember(Set<User> groupMember) {
		this.groupMember = groupMember;
	}

	@OneToMany(fetch=FetchType.LAZY,mappedBy="group")
	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	@Column(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
