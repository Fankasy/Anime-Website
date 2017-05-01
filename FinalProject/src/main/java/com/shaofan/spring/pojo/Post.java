package com.shaofan.spring.pojo;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="post")

public class Post {
	
	private long postId;
	
	private String title;
	private String postContent;
	
	private User postUser;
	
	private Group group;
	
	
	private Date postTime;
	
	
	private Set<PostComment>comments=new HashSet<PostComment>();

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="POST_ID")
	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	@Column(name="content")
	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId",nullable=false)
	public User getPostUser() {
		return postUser;
	}

	public void setPostUser(User postUser) {
		this.postUser = postUser;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="groupId",nullable=false)
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="post_time")
	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	@OneToMany(fetch=FetchType.LAZY,mappedBy="post",cascade=CascadeType.ALL)
	public Set<PostComment> getComments() {
		return comments;
	}

	public void setComments(Set<PostComment> comments) {
		this.comments = comments;
	}

	@Column(name="title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	

}
