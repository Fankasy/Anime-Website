package com.shaofan.spring.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import java.io.File;
import java.io.IOException;
import java.util.List;
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
public class AnimeController {

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("animeValidator")
	AnimeValidator animeValidator;
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
		binder.setValidator(animeValidator);
	}

	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value="allanime.htm",method=RequestMethod.GET)
	private ModelAndView showAllAnimes() throws WholeException{
		try{
			List<Anime> animes=animeDao.getALLAnimes();
			return new ModelAndView("allAnime","animes",animes);
		}catch (WholeException e) {
			// TODO Auto-generated catch block
			throw new WholeException(e.getMessage());
		}
		
		
	}
	
	@RequestMapping(value="addAnime.htm",method=RequestMethod.GET)
	private String showAddPage(ModelMap model){
		Anime anime=new Anime();
		model.addAttribute("anime",anime);
		return "addAnime";
	}
	
	@RequestMapping(value="addAnime.htm",method=RequestMethod.POST)
	public String addAnimeToDatabase(@ModelAttribute("anime")Anime anime){
		
		anime.setFileName(anime.getTitle());
		try {
			if (anime.getFileName().trim() != "" || anime.getFileName() != null) {
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
				directory = new File(path + "//" + anime.getFileName());
				boolean temp = directory.exists();
				if (!temp) {
					temp = directory.mkdir();
				}
				if (temp) {
					// We need to transfer to a file
					CommonsMultipartFile photoInMemory = anime.getAnimePhoto();

					String filename = photoInMemory.getOriginalFilename();
					// could generate file names as well

					File localFile = new File(directory.getPath(), filename);

					// move the file from memory to the file

					photoInMemory.transferTo(localFile);
					anime.setFileName(localFile.getPath());
					System.out.println("File is stored at" + localFile.getPath());
					System.out.print("registerNewUser");
					Anime a=animeDao.addAnime(anime);

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

		return "manageAnime";
	}
	@RequestMapping(value="updateAnime.htm",method=RequestMethod.GET)
	private String showUpdatePage(ModelMap model) throws WholeException{
		List<Anime> animes=animeDao.getALLAnimes();
		model.addAttribute("animes",animes);
		
		return "adminAllAnime";
	}
	
	@RequestMapping(value="manageAnimeDetail",method=RequestMethod.GET)
	private String showManagePage(ModelMap model,HttpServletRequest request) throws WholeException{
		HttpSession session=request.getSession();
		long animeID=Long.parseLong(request.getParameter("animeID"));
		Anime anime=animeDao.getAnimeById(animeID);
		session.setAttribute("animeID", animeID);
		model.addAttribute("anime",anime);
		return "manageAnimeDetail";
	}
	
	@RequestMapping(value="manageAnimeDetail.htm",method=RequestMethod.POST)
	public String updateAnime(@ModelAttribute("anime")Anime anime,HttpServletRequest request) throws WholeException{
		
		anime.setFileName(anime.getTitle());
		Long animeID=(Long)(request.getSession().getAttribute("animeID"));
		System.out.println(animeID);
		Anime ani=animeDao.getAnimeById(animeID);
		try {
			if (anime.getFileName().trim() != "" || anime.getFileName() != null) {
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
				directory = new File(path + "//" + anime.getFileName());
				boolean temp = directory.exists();
				if (!temp) {
					temp = directory.mkdir();
				}
				if (temp) {
					// We need to transfer to a file
					CommonsMultipartFile photoInMemory = anime.getAnimePhoto();

					String filename = photoInMemory.getOriginalFilename();
					// could generate file names as well

					File localFile = new File(directory.getPath(), filename);

					// move the file from memory to the file

					photoInMemory.transferTo(localFile);
					anime.setFileName(localFile.getPath());
					System.out.println("File is stored at" + localFile.getPath());
					System.out.print("registerNewUser");
					Anime a=animeDao.updateAnime(ani, anime);

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

		return "manageAnime";
	}
	@RequestMapping(value="deleteAnime.htm",method=RequestMethod.POST)
	private ModelAndView deleteAnime(HttpServletRequest request) throws WholeException{
		Long id=Long.parseLong(request.getParameter("animeID"));
		Anime anime=animeDao.getAnimeById(id);
		animeDao.deleteAnime(anime);
		List<Anime>animes=animeDao.getALLAnimes();
		
		return new ModelAndView("adminAllAnime","animes",animes);
	}
	
	@RequestMapping(value="animeDetail.htm",method=RequestMethod.GET)
	private ModelAndView showAnimeDetail(HttpServletRequest request) throws WholeException{
		
		long animeID=Long.parseLong(request.getParameter("animeId"));
		Anime anime=animeDao.getAnimeById(animeID);
		
		return new ModelAndView("animeDetail","anime",anime);
	}
	@RequestMapping(value="searchAnime.htm",method=RequestMethod.GET)
	private ModelAndView search(HttpServletRequest request) throws WholeException{
		String key=request.getParameter("key");
		String searchtype=request.getParameter("searchType");
		System.out.println(key);
		System.out.println(searchtype);
		List<Anime>animes=animeDao.search(key, searchtype);
		
		return new ModelAndView("allAnime","animes",animes);
	}
}
