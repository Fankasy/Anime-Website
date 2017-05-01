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

import com.shaofan.spring.validator.ActualPersonValidator;
import com.shaofan.spring.validator.UserValidator;


@Controller
public class ActualPersonController {
	
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
		binder.setValidator(actualPersonValidator);
		
	}

	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value="addActualPerson.htm",method=RequestMethod.GET)
	public String showForm(ModelMap model) {
		ActualPerson actualPerson = new ActualPerson(); // should be AutoWired

		// command object
		model.addAttribute("actualPerson", actualPerson);

		// return form view
		return "addActualPerson";


	}
	
	@RequestMapping(value="addActualPerson.htm",method=RequestMethod.POST)
	public String addActualPersonToDatabase(@ModelAttribute("actualPerson")ActualPerson actualPerson){
		
		actualPerson.setFileName(actualPerson.getFname()+actualPerson.getLname());
		try {
			if (actualPerson.getFileName().trim() != "" || actualPerson.getFileName() != null) {
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
				directory = new File(path + "//" + actualPerson.getFileName());
				boolean temp = directory.exists();
				if (!temp) {
					temp = directory.mkdir();
				}
				if (temp) {
					// We need to transfer to a file
					CommonsMultipartFile photoInMemory = actualPerson.getPhoto();

					String filename = photoInMemory.getOriginalFilename();
					// could generate file names as well

					File localFile = new File(directory.getPath(), filename);

					// move the file from memory to the file

					photoInMemory.transferTo(localFile);
					actualPerson.setFileName(localFile.getPath());
					System.out.println("File is stored at" + localFile.getPath());
					System.out.print("registerNewUser");
					ActualPerson a=actualPersonDao.addActualPerson(actualPerson);

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
	
	@RequestMapping(value="manageActualPerson.htm",method=RequestMethod.GET)
	private ModelAndView showManageForm() throws WholeException{
		List<ActualPerson>actualPersons=actualPersonDao.getAllActualPerson();
		return new ModelAndView("manageActualPerson","actualPersons",actualPersons);
	}
	
	@RequestMapping(value="manageActualPersonDetail.htm",method=RequestMethod.GET)
	private String showDetail(ModelMap model,HttpServletRequest request) throws WholeException{
		long id=Long.parseLong(request.getParameter("actualPersonId"));
		System.out.println(id);
		ActualPerson actualPerson=actualPersonDao.getPersonById(id);
		model.addAttribute("actualPerson",actualPerson);
		HttpSession session=request.getSession();
		session.setAttribute("ActualpersonId", id);
		System.out.println(actualPerson.getActualPersonId());
		return "actualPersonDetailAdmin";
		
		
	}
	@RequestMapping(value="manageActualPersonDetail.htm",method=RequestMethod.POST)
	private String updateActualPerson(@ModelAttribute("actualPerson")ActualPerson actualPerson,HttpServletRequest request) throws WholeException{
		actualPerson.setFileName(actualPerson.getFname()+actualPerson.getLname());
		
		long id=(Long)request.getSession().getAttribute("ActualpersonId");
		System.out.println(id);
		ActualPerson a=actualPersonDao.getPersonById(id);
		
		try {
			if (actualPerson.getFileName().trim() != "" || actualPerson.getFileName() != null) {
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
				directory = new File(path + "//" + actualPerson.getFileName());
				boolean temp = directory.exists();
				if (!temp) {
					temp = directory.mkdir();
				}
				if (temp) {
					// We need to transfer to a file
					CommonsMultipartFile photoInMemory = actualPerson.getPhoto();

					String filename = photoInMemory.getOriginalFilename();
					// could generate file names as well

					File localFile = new File(directory.getPath(), filename);

					// move the file from memory to the file

					photoInMemory.transferTo(localFile);
					actualPerson.setFileName(localFile.getPath());
					System.out.println("File is stored at" + localFile.getPath());
					System.out.print("registerNewUser");
					actualPersonDao.updateActualPerson(a,actualPerson);

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
	
	@RequestMapping(value="deleteActualPerson.htm",method=RequestMethod.POST)
	private ModelAndView deleteActualPerson(HttpServletRequest request) throws WholeException{
		long id=Long.parseLong(request.getParameter("actualPersonId"));
		ActualPerson actualPerson=actualPersonDao.getPersonById(id);
		actualPersonDao.deleteActualPerson(actualPerson);
		
		
		List<ActualPerson>actualPersons=actualPersonDao.getAllActualPerson();
		
				
		return new ModelAndView("manageActualPerson","actualPersons",actualPersons);
	}
	
	
	@RequestMapping(value="animePerformers.htm",method=RequestMethod.GET)
	private ModelAndView showPerformerPage() throws WholeException{
		List<Anime>animes=animeDao.getALLAnimes();
		return new ModelAndView("animePerformer","animes",animes);
	}
	@RequestMapping(value="addPerformers.htm",method=RequestMethod.GET)
	private ModelAndView ShowPerformerPage(ModelMap model,HttpServletRequest request) throws WholeException{
		long id =Long.parseLong(request.getParameter("animeID"));
		Anime anime=animeDao.getAnimeById(id);
		Set<ActualPerson>performers=anime.getPerformers();
		List<ActualPerson>restPerformers= actualPersonDao.getAllActualPerson();
		for(Iterator<ActualPerson>iterator=restPerformers.iterator();iterator.hasNext(); ){
			ActualPerson a=iterator.next();
			if(performers.contains(a)){
				iterator.remove();
			}
		}
		
		model.addAttribute("performers",performers);
		model.addAttribute("restPerformers",restPerformers);
		model.addAttribute("animeId",id);
		return new ModelAndView("addPerformers");
	}
	@RequestMapping(value="addPerformers.htm",method=RequestMethod.POST)
	private ModelAndView addPerformerToDatabase(ModelMap model,HttpServletRequest request) throws WholeException{
		long animeId=Long.parseLong(request.getParameter("animeId"));
		long performerId=Long.parseLong(request.getParameter("performerId"));
		Anime anime=animeDao.getAnimeById(animeId);
		ActualPerson performer=actualPersonDao.getPersonById(performerId);
		Set<ActualPerson> performers= anime.getPerformers();
		performers.add(performer);
		Set<Anime>animes=performer.getPerformAnime();
		animes.add(anime);
		anime.setPerformers(performers);
		
		animeDao.update(anime);
		actualPersonDao.update(performer);
		
		List<ActualPerson>restPerformers= actualPersonDao.getAllActualPerson();
		for(Iterator<ActualPerson>iterator=restPerformers.iterator();iterator.hasNext(); ){
			ActualPerson a=iterator.next();
			if(performers.contains(a)){
				iterator.remove();
			}
		}
		
		model.addAttribute("performers",performers);
		model.addAttribute("restPerformers",restPerformers);
		model.addAttribute("animeId",animeId);
		return new ModelAndView("addPerformers");
	}
	@RequestMapping (value="deletePerformer.htm",method=RequestMethod.POST)
	private ModelAndView deletePerformer(ModelMap model,HttpServletRequest request) throws WholeException{
		long animeId=Long.parseLong(request.getParameter("animeId"));
		long performerId=Long.parseLong(request.getParameter("performerId"));
		Anime anime=animeDao.getAnimeById(animeId);
		ActualPerson performer=actualPersonDao.getPersonById(performerId);
		Set<ActualPerson> performers= anime.getPerformers();
		performers.remove(performer);
		Set<Anime>animes=performer.getPerformAnime();
		animes.remove(anime);
		animeDao.update(anime);
		actualPersonDao.update(performer);
		
		List<ActualPerson>restPerformers= actualPersonDao.getAllActualPerson();
		for(Iterator<ActualPerson>iterator=restPerformers.iterator();iterator.hasNext(); ){
			ActualPerson a=iterator.next();
			if(performers.contains(a)){
				iterator.remove();
			}
		}
		
		model.addAttribute("performers",performers);
		model.addAttribute("restPerformers",restPerformers);
		model.addAttribute("animeId",animeId);
		return new ModelAndView("addPerformers");
	}
	
	
	
	@RequestMapping(value="animeMusicians.htm",method=RequestMethod.GET)
	private ModelAndView showMusicianPage() throws WholeException{
		List<Anime>animes=animeDao.getALLAnimes();
		return new ModelAndView("animeMusician","animes",animes);
	}
	@RequestMapping(value="addMusicians.htm",method=RequestMethod.GET)
	private ModelAndView ShowMusicianPage(ModelMap model,HttpServletRequest request) throws WholeException{
		long id =Long.parseLong(request.getParameter("animeID"));
		Anime anime=animeDao.getAnimeById(id);
		Set<ActualPerson>musicians=anime.getMusician();
		List<ActualPerson>restMusicians= actualPersonDao.getAllActualPerson();
		for(Iterator<ActualPerson>iterator=restMusicians.iterator();iterator.hasNext(); ){
			ActualPerson a=iterator.next();
			if(musicians.contains(a)){
				iterator.remove();
			}
		}
		
		model.addAttribute("musicians",musicians);
		model.addAttribute("restMusicians",restMusicians);
		model.addAttribute("animeId",id);
		return new ModelAndView("addMusicians");
	}
	@RequestMapping(value="addMusicians.htm",method=RequestMethod.POST)
	private ModelAndView addMusicianToDatabase(ModelMap model,HttpServletRequest request) throws WholeException{
		long animeId=Long.parseLong(request.getParameter("animeId"));
		long musicianId=Long.parseLong(request.getParameter("musicianId"));
		Anime anime=animeDao.getAnimeById(animeId);
		ActualPerson musician=actualPersonDao.getPersonById(musicianId);
		Set<ActualPerson> musicians= anime.getMusician();
		musicians.add(musician);
		Set<Anime>animes=musician.getMusicianAnime();
		animes.add(anime);
		anime.setMusician(musicians);
		
		animeDao.update(anime);
		actualPersonDao.update(musician);
		
		List<ActualPerson>restMusicians= actualPersonDao.getAllActualPerson();
		for(Iterator<ActualPerson>iterator=restMusicians.iterator();iterator.hasNext(); ){
			ActualPerson a=iterator.next();
			if(musicians.contains(a)){
				iterator.remove();
			}
		}
		
		model.addAttribute("musicians",musicians);
		model.addAttribute("restMusicians",restMusicians);
		model.addAttribute("animeId",animeId);
		return new ModelAndView("addMusicians");
	}
	@RequestMapping (value="deleteMusician.htm",method=RequestMethod.POST)
	private ModelAndView deleteMusician(ModelMap model,HttpServletRequest request) throws WholeException{
		long animeId=Long.parseLong(request.getParameter("animeId"));
		long musicianId=Long.parseLong(request.getParameter("musicianId"));
		Anime anime=animeDao.getAnimeById(animeId);
		ActualPerson musician=actualPersonDao.getPersonById(musicianId);
		Set<ActualPerson> musicians= anime.getMusician();
		musicians.remove(musician);
		Set<Anime>animes=musician.getMusicianAnime();
		animes.remove(anime);
		animeDao.update(anime);
		actualPersonDao.update(musician);
		
		List<ActualPerson>restMusicians= actualPersonDao.getAllActualPerson();
		for(Iterator<ActualPerson>iterator=restMusicians.iterator();iterator.hasNext(); ){
			ActualPerson a=iterator.next();
			if(musicians.contains(a)){
				iterator.remove();
			}
		}
		
		model.addAttribute("musicians",musicians);
		model.addAttribute("restMusicians",restMusicians);
		model.addAttribute("animeId",animeId);
		return new ModelAndView("addMusicians");
	}
	
	
	@RequestMapping(value="animeDirectors.htm",method=RequestMethod.GET)
	private ModelAndView showDirectorPage() throws WholeException{
		List<Anime>animes=animeDao.getALLAnimes();
		return new ModelAndView("animeDirectors","animes",animes);
	}
	@RequestMapping(value="addDirectors.htm",method=RequestMethod.GET)
	private ModelAndView ShowDirectorPage(ModelMap model,HttpServletRequest request) throws WholeException{
		long id =Long.parseLong(request.getParameter("animeID"));
		Anime anime=animeDao.getAnimeById(id);
		Set<ActualPerson>directors=anime.getDirectors();
		List<ActualPerson>restDirectors= actualPersonDao.getAllActualPerson();
		for(Iterator<ActualPerson>iterator=restDirectors.iterator();iterator.hasNext(); ){
			ActualPerson a=iterator.next();
			if(directors.contains(a)){
				iterator.remove();
			}
		}
		
		model.addAttribute("directors",directors);
		model.addAttribute("restDirectors",restDirectors);
		model.addAttribute("animeId",id);
		return new ModelAndView("addDirectors");
	}
	@RequestMapping(value="addDirectors.htm",method=RequestMethod.POST)
	private ModelAndView addDirectorToDatabase(ModelMap model,HttpServletRequest request) throws WholeException{
		long animeId=Long.parseLong(request.getParameter("animeId"));
		long directorId=Long.parseLong(request.getParameter("directorId"));
		Anime anime=animeDao.getAnimeById(animeId);
		ActualPerson director=actualPersonDao.getPersonById(directorId);
		Set<ActualPerson> directors= anime.getDirectors();
		directors.add(director);
		Set<Anime>animes=director.getDirectorAnime();
		animes.add(anime);
		anime.setDirectors(directors);
		
		
		animeDao.update(anime);
		actualPersonDao.update(director);
		
		List<ActualPerson>restDirectors= actualPersonDao.getAllActualPerson();
		for(Iterator<ActualPerson>iterator=restDirectors.iterator();iterator.hasNext(); ){
			ActualPerson a=iterator.next();
			if(directors.contains(a)){
				iterator.remove();
			}
		}
		
		model.addAttribute("directors",directors);
		model.addAttribute("restDirectors",restDirectors);
		model.addAttribute("animeId",animeId);
		return new ModelAndView("addDirectors");
	}
	@RequestMapping (value="deleteDirector.htm",method=RequestMethod.POST)
	private ModelAndView deleteDirector(ModelMap model,HttpServletRequest request) throws WholeException{
		long animeId=Long.parseLong(request.getParameter("animeId"));
		long directorId=Long.parseLong(request.getParameter("directorId"));
		Anime anime=animeDao.getAnimeById(animeId);
		ActualPerson director=actualPersonDao.getPersonById(directorId);
		Set<ActualPerson> directors= anime.getDirectors();
		directors.remove(director);
		Set<Anime>animes=director.getDirectorAnime();
		animes.remove(anime);
		animeDao.update(anime);
		actualPersonDao.update(director);
		
		List<ActualPerson>restDirectors= actualPersonDao.getAllActualPerson();
		for(Iterator<ActualPerson>iterator=restDirectors.iterator();iterator.hasNext(); ){
			ActualPerson a=iterator.next();
			if(directors.contains(a)){
				iterator.remove();
			}
		}
		
		model.addAttribute("directors",directors);
		model.addAttribute("restDirectors",restDirectors);
		model.addAttribute("animeId",animeId);
		return new ModelAndView("addDirectors");
	}
	
	@RequestMapping(value="actualPersonDetail.htm",method=RequestMethod.GET)
	private ModelAndView showActualPersonDetail(HttpServletRequest request) throws WholeException{
		long actualPersonId=Long.parseLong(request.getParameter("actualPersonId"));
		ActualPerson actualPerson=actualPersonDao.getPersonById(actualPersonId);
		
		return new ModelAndView("actualPersonDetail","actualPerson",actualPerson);
	}
}
