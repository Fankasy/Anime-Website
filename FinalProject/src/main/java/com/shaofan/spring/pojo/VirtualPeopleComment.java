package com.shaofan.spring.pojo;
import java.util.Date;
import javax.persistence.*;
@Entity
@Table(name="virtualpeople_comment")
public class VirtualPeopleComment {
	
	private long commentId;
	
	
	private String commentContent;
	
	
	private Date postTime;
	

	
	private User commentUser;
	
	
	private VirtualPerson charecter;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="VIRTUALPEOPLECOMMENT_ID")
	public long getCommentId() {
		return commentId;
	}
	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}
	
	@Column(name="content")
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="POST_TIME")
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	public User getCommentUser() {
		return commentUser;
	}
	public void setCommentUser(User commentUser) {
		this.commentUser = commentUser;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="VIRTUALPERSON_ID")
	public VirtualPerson getCharecter() {
		return charecter;
	}
	public void setCharecter(VirtualPerson charecter) {
		this.charecter = charecter;
	}
	

}
