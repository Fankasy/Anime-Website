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
public class GroupController {
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("groupValidator")
	GroupValidator groupValidator;
	
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
		binder.setValidator(groupValidator);
		
	}

	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value="allGroup.htm",method=RequestMethod.GET)
	private ModelAndView showAllGroup() throws WholeException{
		List<Group>groups=groupDao.getAllGroups();
		
		return new ModelAndView("allgroups","groups",groups);
	}
	@RequestMapping(value="addGroup.htm",method=RequestMethod.GET)
	private ModelAndView addGroupPage() throws WholeException{
		List<Group>groups=groupDao.getAllGroups();
		return new ModelAndView("addGroup","groups",groups);
	}
	@RequestMapping(value="addGroup.htm",method=RequestMethod.POST)
	private ModelAndView addGroup(HttpServletRequest request) throws WholeException{
		String groupName=request.getParameter("groupName");
		String groupDescription=request.getParameter("description");
		User u=(User)request.getSession().getAttribute("user");
		if(u==null){
			return new ModelAndView("error");
		}
		else{
			Group group=new Group();
			group.setGroupName(groupName);
			group.setDescription(groupDescription);
			Date date=new Date();
			group.setStartTime(date);
			group.setInitiator(u);
			Set<User> users=new HashSet();
			users.add(u);
			group.setGroupMember(users);
			
			groupDao.addGroup(group);
			List<Group>g=groupDao.getAllGroups();
			return new ModelAndView("allgroups","groups",g);
		}
		
	}
	@RequestMapping(value="groupDetail.htm",method=RequestMethod.GET)
	private ModelAndView groupDetail(HttpServletRequest request) throws WholeException{
		
		long groupId=Long.parseLong(request.getParameter("groupId"));
		Group group=groupDao.getGroupById(groupId);
		
		return new ModelAndView("groupPage","group",group);
	}
	@RequestMapping(value="addPost.htm",method=RequestMethod.POST)
	private ModelAndView addPost(HttpServletRequest request) throws WholeException{
		long groupId=Long.parseLong(request.getParameter("groupId"));
		User user=(User)request.getSession().getAttribute("user");
		String title=request.getParameter("title");
		String post=request.getParameter("post");
		Post p=new Post();
		Date date=new Date();
		Group group=groupDao.getGroupById(groupId);
		if(user==null){
			return new ModelAndView("error");
		}
		else{
			p.setGroup(group);
			p.setTitle(title);
			p.setPostContent(post);
			p.setPostUser(user);
			p.setPostTime(date);
			
			postDao.addPost(p);
			return new ModelAndView("groupPage","group",group);
		}
		
	}
	
}
