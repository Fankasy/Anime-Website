package com.shaofan.spring.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.shaofan.spring.exception.*;
import com.shaofan.spring.dao.*;
import com.shaofan.spring.pojo.ActualPerson;
import com.shaofan.spring.validator.*;
@Controller
public class AdminManageController {

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("userValidator")
	UserValidator uservalidator;
	
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
		binder.setValidator(uservalidator);
		
	}

	@Autowired
	ServletContext servletContext;
	@RequestMapping(value="adminManage.htm",method=RequestMethod.GET)
	private ModelAndView toAdminPage(HttpServletRequest request){
		
		return new ModelAndView("adminManagement");
	}
	@RequestMapping(value="manageAnime.htm",method=RequestMethod.GET)
	private ModelAndView showAnimePage(){
		
		return new ModelAndView ("manageAnime");
	}
	

	@RequestMapping(value="managePerson.htm",method=RequestMethod.GET)
	private ModelAndView showPersonPage(){
		return new ModelAndView("managePerson");
	}
	

	@RequestMapping(value="manageLinks.htm",method=RequestMethod.GET)
	private ModelAndView showLinksPage(){
		return new ModelAndView("manageLinks");
	}
	


	


}
