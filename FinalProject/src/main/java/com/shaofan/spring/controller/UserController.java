package com.shaofan.spring.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.*;
import org.hibernate.Query;
import org.hibernate.Session;
//import com.shaofan.spirng.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shaofan.spring.exception.WholeException;
import com.shaofan.spring.dao.*;
import com.shaofan.spring.pojo.Role;
import com.shaofan.spring.pojo.Role.RoleType;
import com.shaofan.spring.pojo.User;
import com.shaofan.spring.validator.UserValidator;

@Controller
public class UserController {
	
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;
	@Autowired
	@Qualifier("roleDao")
	RoleDAO roleDao;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
		
	}

	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value="/begin.htm" ,method=RequestMethod.GET)
	private ModelAndView showIndexPage(){
		Role adminRole=new Role();
		adminRole.setRoleType(RoleType.Admin);
		adminRole.setRoleName("ADMIN_ROLE");
		Role userRole=new Role();
		userRole.setRoleType(RoleType.User);
		userRole.setRoleName("USER_ROLE");
		Role groupRole=new Role();
		groupRole.setRoleType(RoleType.GroupManager);
		groupRole.setRoleName("GROUPMANAGER_ROLE");
		User adminUser=new User();
		adminUser.setUserName("Fankasy");
		adminUser.setPassword("9527");
		adminUser.setEmail("Fankasy@hotmail.com");
		adminUser.setAge(22);
		adminUser.setFname("Shaofan");
		adminUser.setLname("Yu");
		adminUser.setGender("Male");
		Set<Role>roles=new HashSet<Role>();
		roles.add(adminRole);
		roles.add(userRole);
		adminUser.setRoles(roles);
		try{
			roleDao.addRole(adminRole);
			
		}
		 catch (IllegalStateException e) {
				System.out.println("*** IllegalStateException: " + e.getMessage());
		 } catch (WholeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		try{
			roleDao.addRole(groupRole);
			
		}
		 catch (IllegalStateException e) {
				System.out.println("*** IllegalStateException: " + e.getMessage());
		 } catch (WholeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		try{
			userDao.register(adminUser);
		}catch(IllegalStateException e){
			System.out.println(e.getMessage());
		}catch(WholeException e){
			System.out.println(e.getMessage());
		}
		
		return new ModelAndView("index");
	}
	@RequestMapping(value="index.htm",method=RequestMethod.GET)
	private ModelAndView goToIndexPage(){
		return new ModelAndView("index");
	}
	@RequestMapping(value="/login.htm",method=RequestMethod.GET)
	private ModelAndView showLoginPage(){
		User user=new User();
		return new ModelAndView("login","user",user);
	}
		
	@RequestMapping(value="/login.htm",method=RequestMethod.POST)
	private ModelAndView checkLogin(HttpServletRequest request)throws WholeException{
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
			
		UserDAO userdao=new UserDAO();
		Session session=DAO.getSession();
		HttpSession httpsession=request.getSession();
		Query q=session.createQuery("from User where userName= :userName");
		q.setString("userName", userName);
		User u=(User)q.uniqueResult();
		if(u==null){
			return new ModelAndView ("loginError");
		}
		
		
		if(password.equals(u.getPassword())){
			httpsession.setAttribute("user", u);
			return new ModelAndView("index");
		}
		
		else{
			return new ModelAndView("loginError");
		}
		
		
	}
	
	
	@RequestMapping(value="logout.htm",method=RequestMethod.GET)
	private ModelAndView logoutUser(HttpServletRequest request){
		HttpSession session =request.getSession();
		session.setAttribute("user", null);
		
		return new ModelAndView("index");
	}
	@RequestMapping(value="register.htm",method=RequestMethod.GET)
	private String showRegisterPage(ModelMap model){
		User u=new User();
		model.addAttribute("user",u);
		return "register";
	}
	
	@RequestMapping(value="register.htm",method=RequestMethod.POST)
	private String register(@ModelAttribute ("user") @Valid User user,BindingResult result, ModelMap model) throws WholeException{
		if(result.hasErrors()){
			return "registerError";
					
		}
		Role userRole=roleDao.getUserRole();
		Set<Role>roles=new HashSet(0);
		roles.add(userRole);
		user.setRoles(roles);
		
		userDao.register(user);
		
		
		return "index";
	}
	

}
