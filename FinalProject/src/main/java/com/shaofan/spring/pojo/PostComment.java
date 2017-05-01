package com.shaofan.spring.pojo;

import java.util.Date;
import javax.persistence.*;
@Entity
@Table(name="post_comment")
public class PostComment {
	
	private long postCommentId;
	

	private User postUser;
	
	private String comments;
	private Date commentTime;
	
	
	private Post post;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="postCommentId")
	public long getPostCommentId() {
		return postCommentId;
	}

	public void setPostCommentId(long postCommentId) {
		this.postCommentId = postCommentId;
	}
	@ManyToOne()
	@JoinColumn(name="USER_ID")
	public User getPostUser() {
		return postUser;
	}

	public void setPostUser(User postUser) {
		this.postUser = postUser;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="comment_time")
	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	@ManyToOne()
	@JoinColumn(name="POST_ID")
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@Column(name="comment")
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}


	
	

}
