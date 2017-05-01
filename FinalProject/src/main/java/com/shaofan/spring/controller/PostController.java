package com.shaofan.spring.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.runner.Request;
//import com.shaofan.spirng.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.shaofan.spring.dao.*;
import com.shaofan.spring.exception.WholeException;
import com.shaofan.spring.pojo.*;

import com.shaofan.spring.validator.*;
@Controller
public class PostController {
	
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("postValidator")
	PostValidator postValidator;
	
	@Autowired
	@Qualifier("actualPersonValidator")
	ActualPersonValidator actualPersonValidator;
	
	@Autowired
	@Qualifier("roleDao")
	RoleDAO roleDao;

	@Autowired
	@Qualifier("animeDao")
	AnimeDAO animeDao;
	

	
	@Autowired
	@Qualifier("actualPersonDao")
	ActualPersonDAO actualPersonDao;
	
	@Autowired
	@Qualifier("commentDao")
	CommentDAO commentDao;
	
	@Autowired
	@Qualifier("groupDao")
	GroupDAO groupDao;
	
	@Autowired
	@Qualifier("userRateDao")
	UserRateDAO userRateDao;
	
	@Autowired
	@Qualifier("virtualPersonDao")
	VirtualPersonDAO virtualPersonDao;
	
	@Autowired
	@Qualifier("postDao")
	PostDAO postDao;
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(postValidator);
		
	}

	@Autowired
	ServletContext servletContext;
	@RequestMapping(value="postComment.htm",method=RequestMethod.GET)
	private ModelAndView showComments(HttpServletRequest request) throws WholeException{
		long postId=Long.parseLong(request.getParameter("postId"));
		Post post=postDao.getPostById(postId);
		Set<PostComment>comments=post.getComments();
		
		
		return new ModelAndView("comments","post",post);
	}
	
	@RequestMapping(value="postComment.htm",method=RequestMethod.POST)
	private ModelAndView addComment(HttpServletRequest request ) throws WholeException{
		String comment=request.getParameter("comment");
		long postId=Long.parseLong(request.getParameter("postId"));
		Post post=postDao.getPostById(postId);
		Set<PostComment>comments=post.getComments();
		
		PostComment postComment=new PostComment();
		User user=(User)request.getSession().getAttribute("user");
		if(user==null){
			return new ModelAndView("error");
		}
		else{
			Date date=new Date();
			postComment.setComments(comment);
			postComment.setPost(post);
			postComment.setPostUser(user);
			postComment.setCommentTime(date);
			comments.add(postComment);
			post.setComments(comments);
			postDao.update(post);
			
			
			return new ModelAndView("comments","post",post);
		}
		
	}
}
