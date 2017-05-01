package com.shaofan.spring.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
public class VirtualPersonController {
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	
	
	@Autowired
	@Qualifier("virtualPersonValidator")
	VirtualPersonValidator virtualPersonValidator;
	

	@Autowired
	@Qualifier("roleDao")
	RoleDAO roleDao;

	@Autowired
	@Qualifier("animeDao")
	AnimeDAO animeDao;
	


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
		binder.setValidator(virtualPersonValidator);
		
	}

	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value="addVirtualPerson.htm",method=RequestMethod.GET)
	public String showForm(ModelMap model) {
		VirtualPerson virtualPerson = new VirtualPerson(); // should be AutoWired

		// command object
		model.addAttribute("virtualPerson", virtualPerson);

		// return form view
		return "addVirtualPerson";


	}
	
	@RequestMapping(value="addVirtualPerson.htm",method=RequestMethod.POST)
	public String addVirtualPersonToDatabase(@ModelAttribute("virtualPerson")VirtualPerson virtualPerson){
		
		virtualPerson.setFileName(virtualPerson.getFname()+virtualPerson.getLname());
		try {
			if (virtualPerson.getFileName().trim() != "" || virtualPerson.getFileName() != null) {
				File directory;
				String check = File.separator; // Checking if system is linux
												// based or windows based by
												// checking seprator used.
				String path = null;
				if (check.equalsIgnoreCase("\\")) {
					path = servletContext.getRealPath("").replace("build\\", ""); // gives real path as Lab9/build/web/
																				  // so we need to replace build in the path
																						}

				if (check.equalsIgnoreCase("/")) {
					path = servletContext.getRealPath("").replace("build/", "");
					path += "/"; // Adding trailing slash for Mac systems.
				}
				directory = new File(path + "//" + virtualPerson.getFileName());
				boolean temp = directory.exists();
				if (!temp) {
					temp = directory.mkdir();
				}
				if (temp) {
					// We need to transfer to a file
					CommonsMultipartFile photoInMemory = virtualPerson.getPhoto();

					String filename = photoInMemory.getOriginalFilename();
					// could generate file names as well

					File localFile = new File(directory.getPath(), filename);

					// move the file from memory to the file

					photoInMemory.transferTo(localFile);
					virtualPerson.setFileName(localFile.getPath());
					System.out.println("File is stored at" + localFile.getPath());
					System.out.print("registerNewUser");
					VirtualPerson a=virtualPersonDao.addVirtualPerson(virtualPerson);

				} else {
					System.out.println("Failed to create directory!");
				}
			}

		} catch (IllegalStateException e) {
			System.out.println("*** IllegalStateException: " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("*** IOException: " + e.getMessage());
		} catch (WholeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		return "managePerson";
	}
	
	@RequestMapping(value="manageVirtualPerson.htm",method=RequestMethod.GET)
	private ModelAndView showManageForm() throws WholeException{
		List<VirtualPerson>virtualPersons=virtualPersonDao.getAllVirtualPerson();
		return new ModelAndView("manageVirtualPerson","virtualPersons",virtualPersons);
	}
	
	@RequestMapping(value="manageVirtualPersonDetail.htm",method=RequestMethod.GET)
	private String showDetail(ModelMap model,HttpServletRequest request) throws WholeException{
		long id=Long.parseLong(request.getParameter("virtualPersonId"));
		System.out.println(id);
		VirtualPerson virtualPerson=virtualPersonDao.getPersonById(id);
		model.addAttribute("virtualPerson",virtualPerson);
		HttpSession session=request.getSession();
		session.setAttribute("virtualpersonId", id);
		System.out.println(virtualPerson.getVirtualPersonId());
		return "virtualPersonDetailAdmin";
		
		
	}
	@RequestMapping(value="manageVirtualPersonDetail.htm",method=RequestMethod.POST)
	private String updateVirtualPerson(@ModelAttribute("virtualPerson")VirtualPerson virtualPerson,HttpServletRequest request) throws WholeException{
		virtualPerson.setFileName(virtualPerson.getFname()+virtualPerson.getLname());
		
		long id=(Long)request.getSession().getAttribute("virtualpersonId");
		System.out.println(id);
		VirtualPerson a=virtualPersonDao.getPersonById(id);
		
		try {
			if (virtualPerson.getFileName().trim() != "" || virtualPerson.getFileName() != null) {
				File directory;
				String check = File.separator; // Checking if system is linux
												// based or windows based by
												// checking seprator used.
				String path = null;
				if (check.equalsIgnoreCase("\\")) {
					path = servletContext.getRealPath("").replace("build\\", ""); // gives real path as Lab9/build/web/
																				  // so we need to replace build in the path
																						}

				if (check.equalsIgnoreCase("/")) {
					path = servletContext.getRealPath("").replace("build/", "");
					path += "/"; // Adding trailing slash for Mac systems.
				}
				directory = new File(path + "//" + virtualPerson.getFileName());
				boolean temp = directory.exists();
				if (!temp) {
					temp = directory.mkdir();
				}
				if (temp) {
					// We need to transfer to a file
					CommonsMultipartFile photoInMemory = virtualPerson.getPhoto();

					String filename = photoInMemory.getOriginalFilename();
					// could generate file names as well

					File localFile = new File(directory.getPath(), filename);

					// move the file from memory to the file

					photoInMemory.transferTo(localFile);
					virtualPerson.setFileName(localFile.getPath());
					System.out.println("File is stored at" + localFile.getPath());
					System.out.print("registerNewUser");
					virtualPersonDao.updateVirtualPerson(a,virtualPerson);

				} else {
					System.out.println("Failed to create directory!");
				}
			}

		} catch (IllegalStateException e) {
			System.out.println("*** IllegalStateException: " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("*** IOException: " + e.getMessage());
		} catch (WholeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		return "managePerson";
		
	}
	
	@RequestMapping(value="deleteVirtualPerson.htm",method=RequestMethod.POST)
	private ModelAndView deleteVirtualPerson(HttpServletRequest request) throws WholeException{
		long id=Long.parseLong(request.getParameter("virtualPersonId"));
		VirtualPerson virtualPerson=virtualPersonDao.getPersonById(id);
		virtualPersonDao.deleteVirtualPerson(virtualPerson);
		
		
		List<VirtualPerson>virtualPersons=virtualPersonDao.getAllVirtualPerson();
		
				
		return new ModelAndView("manageVirtualPerson","virtualPersons",virtualPersons);
	}
	
	
	
	@RequestMapping(value="animeCharacters.htm",method=RequestMethod.GET)
	private ModelAndView showCharacterPage() throws WholeException{
		List<Anime>animes=animeDao.getALLAnimes();
		return new ModelAndView("animeCharacters","animes",animes);
	}
	@RequestMapping(value="addCharacters.htm",method=RequestMethod.GET)
	private ModelAndView ShowDirectorPage(ModelMap model,HttpServletRequest request) throws WholeException{
		long id =Long.parseLong(request.getParameter("animeID"));
		Anime anime=animeDao.getAnimeById(id);
		Set<VirtualPerson>characters=anime.getAnimeCharacter();
		List<VirtualPerson>restCharacters= virtualPersonDao.getAllVirtualPerson();
		for(Iterator<VirtualPerson>iterator=restCharacters.iterator();iterator.hasNext(); ){
			VirtualPerson a=iterator.next();
			if(characters.contains(a)){
				iterator.remove();
			}
		}
		
		model.addAttribute("characters",characters);
		model.addAttribute("restCharacters",restCharacters);
		model.addAttribute("animeId",id);
		return new ModelAndView("addCharacters");
	}
	@RequestMapping(value="addCharacters.htm",method=RequestMethod.POST)
	private ModelAndView addCharacterToDatabase(ModelMap model,HttpServletRequest request) throws WholeException{
		long animeId=Long.parseLong(request.getParameter("animeId"));
		long characterId=Long.parseLong(request.getParameter("characterId"));
		Anime anime=animeDao.getAnimeById(animeId);
		VirtualPerson character=virtualPersonDao.getPersonById(characterId);
		Set<VirtualPerson> characters= anime.getAnimeCharacter();
		characters.add(character);
		Set<Anime>animes=character.getAnimeCharacters();
		animes.add(anime);
		anime.setAnimeCharacter(characters);
		
		
		animeDao.update(anime);
		virtualPersonDao.update(character);
		
		List<VirtualPerson>restCharacters= virtualPersonDao.getAllVirtualPerson();
		for(Iterator<VirtualPerson>iterator=restCharacters.iterator();iterator.hasNext(); ){
			VirtualPerson a=iterator.next();
			if(characters.contains(a)){
				iterator.remove();
			}
		}
		
		model.addAttribute("characters",characters);
		model.addAttribute("restCharacters",restCharacters);
		model.addAttribute("animeId",animeId);
		return new ModelAndView("addCharacters");
	}
	@RequestMapping (value="deleteCharacter.htm",method=RequestMethod.POST)
	private ModelAndView deleteDirector(ModelMap model,HttpServletRequest request) throws WholeException{
		long animeId=Long.parseLong(request.getParameter("animeId"));
		long characterId=Long.parseLong(request.getParameter("characterId"));
		Anime anime=animeDao.getAnimeById(animeId);
		VirtualPerson character=virtualPersonDao.getPersonById(characterId);
		Set<VirtualPerson> characters= anime.getAnimeCharacter();
		characters.remove(character);
		Set<Anime>animes=character.getAnimeCharacters();
		animes.remove(anime);
		animeDao.update(anime);
		virtualPersonDao.update(character);
		
		List<VirtualPerson>restCharacters= virtualPersonDao.getAllVirtualPerson();
		for(Iterator<VirtualPerson>iterator=restCharacters.iterator();iterator.hasNext(); ){
			VirtualPerson a=iterator.next();
			if(characters.contains(a)){
				iterator.remove();
			}
		}
		
		model.addAttribute("characters",characters);
		model.addAttribute("restCharacters",restCharacters);
		model.addAttribute("animeId",animeId);
		return new ModelAndView("addCharacters");
	}
	
	@RequestMapping(value="virtualPersonDetail.htm",method=RequestMethod.GET)
	private ModelAndView showVirtualPersonDetail(HttpServletRequest request) throws WholeException{
		long virtualPersonId=Long.parseLong(request.getParameter("virtualPersonId"));
		VirtualPerson virtualPerson=virtualPersonDao.getPersonById(virtualPersonId);
		
		return new ModelAndView("virtualPersonDetail","virtualPerson",virtualPerson);
	}
}
