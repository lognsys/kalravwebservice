package com.lognsys.web.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
<<<<<<< HEAD
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
=======
>>>>>>> 641827bf4adbbb2a861e3e85937526c44e78467a
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
<<<<<<< HEAD
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

=======
>>>>>>> 641827bf4adbbb2a861e3e85937526c44e78467a
import javax.imageio.ImageIO;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
=======
>>>>>>> 641827bf4adbbb2a861e3e85937526c44e78467a
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
<<<<<<< HEAD
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

=======
>>>>>>> 641827bf4adbbb2a861e3e85937526c44e78467a
import com.lognsys.dao.dto.AuditoriumsDTO;
import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.model.Drama;
<<<<<<< HEAD
import com.lognsys.model.Users;
=======
import com.lognsys.service.DramaService;
>>>>>>> 641827bf4adbbb2a861e3e85937526c44e78467a
import com.lognsys.util.FormValidator;
import com.lognsys.util.ObjectMapper;

import com.lognsys.service.*;


@WebServlet("/dramadetail")
@MultipartConfig(maxFileSize = 16177215) 
@Controller
public class DramaController {
	
	@Autowired
	DramaService dramaService;
	  private final Path rootLocation;

	 private final StorageService storageService;

	    @Autowired
	    public DramaController(StorageService storageService,StorageProperties properties) {
	        this.storageService = storageService;
	        this.rootLocation = Paths.get(properties.getLocation());
	    }
	
	@RequestMapping(value = "/dramalist", method = RequestMethod.GET)
	public String showDramas(Model model, HttpServletRequest request) {
		List<DramasDTO> listOfDramas = dramaService.getDramas();
		model.addAttribute("listOfDramas", listOfDramas);
//		System.out.println("showDramas listOfDramas.size()  "+listOfDramas.size());
		
		return "dramalist";
	}
	@RequestMapping(value = "/dramadetail", method = RequestMethod.GET)
	public String showDramaDetail(Model model, HttpServletRequest request) {
		System.out.println("Going in dramadetail controller");
		Drama drama = new Drama();
		storageService.deleteAll();
        storageService.init();
		// CALL database to get roles & groups
		List<AuditoriumsDTO> listofAuditorium = dramaService.getAllAuditoriums();
		List<GroupsDTO> listOfGroupsDTO = dramaService.getAllGroups();

		// Adding data to list from RolesDTO
		List<String> auditoriumList = new ArrayList<String>();
		for (AuditoriumsDTO auditoriumsDTO : listofAuditorium) {
			auditoriumList.add(auditoriumsDTO.getAuditorium_name());
		}

		// Adding data to list from GroupsDTO
		List<String> groupsList = new ArrayList<String>();
		for (GroupsDTO group : listOfGroupsDTO) {
			groupsList.add(group.getGroup_name());
		}

		model.addAttribute("drama", drama);
		model.addAttribute("auditoriumList", auditoriumList);
		model.addAttribute("groupsList", groupsList);
		return "dramadetail";
	}
	
	 @GetMapping("/files/{filename:.+}")
	    @ResponseBody
	    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

	        System.out.println("\n\n\n serveFile======================= filename "+filename);
	        
	        Resource file = storageService.loadAsResource(filename);
	       System.out.println("serveFile ======================= file getDescription "+file.getDescription());
	       
	        
	        System.out.println("======================= file.getFilename() "+file.getFilename());
	        String ssss=ResponseEntity.ok().header(HttpHeaders.LINK,
	                "attachment; filename=\"" + file.getFilename() + "\"").body(file).toString();
	        System.out.println("======================= ssss "+ssss);
	        
	        return ResponseEntity.ok().header(HttpHeaders.LINK,
	                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	    }
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/dramadetail", method = RequestMethod.POST)
	public String saveDramaDetail(@RequestParam("name") String name,HttpServletRequest servletRequest,@ModelAttribute("drama") Drama dramas,
			BindingResult result, ModelMap model,@RequestParam("file")MultipartFile file) throws IOException {
		
//		REFER EXAMPLE -- https://spring.io/guides/gs/uploading-files/
		
//		System.out.println(" saveDramaDetail file.isEmpty()- " + file.isEmpty());
//		System.out.println(" saveDramaDetail file.getOriginalFilename()- " + file.getOriginalFilename());

		/*
		if (!file.isEmpty()) {
			  BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
			  System.out.println(" saveDramaDetail src- " + src);
				
			  File destination = new File(file.getOriginalFilename()); // something like C:/Users/tom/Documents/nameBasedOnSomeId.png
			  System.out.println(" saveDramaDetail destination- " + destination);
			  System.out.println(" saveDramaDetail destination getAbsolutePath - " + destination.getAbsolutePath());
			  System.out.println(" saveDramaDetail destination getName - " + destination.getName());
			  System.out.println(" saveDramaDetail destination getPath - " + destination.getPath());
			
			  dramas.setImageurl(destination.getAbsolutePath());		
			 
			  ImageIO.write(src, ".png/.jpeg/.jpg", destination);
			  
			  //Save the id you have used to create the file name in the DB. You can retrieve the image in future with the ID.
			  } */


//        System.out.println("======================= file "+file);
        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(DramaController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));
      storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(DramaController.class,
                        "serveFile",  path.getFileName().toString()).build().toString())
                .collect(Collectors.toList());
        System.out.println("\n ======================= Drama Controler model files "+model.get("files"));
       
     
        	 
         System.out.println("\n ======================= dramas.getImageurl() "+dramas.getImageurl());
        
        storageService.store(file);
    
		
		
		
		
		System.out.println(" saveDramaDetail dramas.getDate().contains(,)- " + dramas.getDate().contains(","));
		if(dramas.getDate().contains(",")){
			String dramaDateTime=dramas.getDate().replace(",","");
			System.out.println(" saveDramaDetail dramaDateTime- " +dramaDateTime );
			 try { 
				 String[] splitString=dramaDateTime.split(" ");
					String date=splitString[0];
					String time=splitString[2];
					
		           SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
		           SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
		           java.util.Date _24HourDt = _24HourSDF.parse(time);
//		           System.out.println(_24HourDt);
		           System.out.println(_12HourSDF.format(_24HourDt));
		           dramaDateTime=date+" "+_12HourSDF.format(_24HourDt);
		           dramas.setDate(dramaDateTime);
		       } catch (Exception e) {
		           e.printStackTrace();
		       }
			
		}
		FormValidator formValidator = new FormValidator();
		formValidator.validate(dramas, result);
		// CALL database to get roles & groups
				List<AuditoriumsDTO> listofAuditorium = dramaService.getAllAuditoriums();

				  System.out.println(" saveDramaDetail listofAuditorium size - " +listofAuditorium.size());
				  
				List<GroupsDTO> listOfGroupsDTO = dramaService.getAllGroups();

				 System.out.println(" saveDramaDetail listOfGroupsDTO size - " +listOfGroupsDTO.size());
				    
				// Adding data to list from RolesDTO
				List<String> auditoriumList = new ArrayList<String>();
				for (AuditoriumsDTO auditoriumsDTO : listofAuditorium) {
					auditoriumList.add(auditoriumsDTO.getAuditorium_name());
				}
				 System.out.println(" saveDramaDetail auditoriumList size - " +auditoriumList.size());
					
				// Adding data to list from GroupsDTO
				List<String> groupsList = new ArrayList<String>();
				for (GroupsDTO group : listOfGroupsDTO) {
					groupsList.add(group.getGroup_name());
				}
				 System.out.println(" saveDramaDetail groupsList size - " +groupsList.size());
					
				model.addAttribute("auditoriumList", auditoriumList);
				model.addAttribute("groupsList", groupsList);

			System.out.println(" saveDramaDetail result.hasErrors() - " +result.hasErrors());
		if (result.hasErrors()) {
			System.out.println("\n saveDramaDetail  dramas toString  down- " + dramas.toString());
				
			return "dramadetail";
		} else {

			System.out.println("\n saveDramaDetail  dramas toString  else  ==== - " + dramas.toString());
			
		dramaService.addDrama(dramas);
		}
		
		
		return "dramadetail";
	}	
	/**
	 * 
	 * @param model
	 * @param dramaIds
	 * @param dramaAction
	 * @return
	 */
	@RequestMapping(value = "/dramalist", method = RequestMethod.POST)
	public String manageDrama(Model model, @RequestParam(value = "dramaIds", required = false) String dramaIds,
			@RequestParam String dramaAction) {
//		System.out.println("manageDrama ");
		System.out.println("manageDrama dramaAction "+dramaAction);
		System.out.println("manageDrama dramaIds "+dramaIds);
		
		switch (dramaAction) {

		case "delete":
			JSONParser parser = new JSONParser();
			try {
				
				Object obj = parser.parse(dramaIds);
				
				JSONArray arr = (JSONArray) obj;
				
				Integer[] dramaIDs = new Integer[arr.size()];
				
				for (int i = 0; i < arr.size(); i++) {
					JSONObject jsonObject = (JSONObject) arr.get(i);
					
					int id = Integer.parseInt(jsonObject.get("id").toString());
					System.out.println("manageDrama arr id "+id);
					
					dramaIDs[i] = id;
					System.out.println("manageDrama arr dramaIDs[i] "+dramaIDs[i]);
					
				}
				System.out.println("manageDrama arr dramaIDs length "+dramaIDs.length);
				
				dramaService.deleteDramas(dramaIDs);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return "dramalist";

		case "edit":

			JSONParser p = new JSONParser();
			try {
				Object obj = p.parse(dramaIds);
				JSONArray arr = (JSONArray) obj;
				System.out.println("manageDrama arr toJSONString "+arr.toJSONString());
					
				String id = "";
				for (int i = 0; i < arr.size(); i++) {

					JSONObject jsonObject = (JSONObject) arr.get(i);
					id = jsonObject.get("id").toString();
					System.out.println("manageDrama arr id "+id);
					
				}
				int dramaid=Integer.parseInt(id);

				DramasDTO dramasDTO = dramaService.findByDrama(dramaid);
				System.out.println("manageDrama dramasDTO "+dramasDTO);
				System.out.println("manageDrama dramasDTO.getTitle() "+dramasDTO.getTitle());
				
				Drama newDramas = ObjectMapper.mapToDramas(dramasDTO);
				System.out.println("manageDrama newDramas "+newDramas);
				System.out.println("manageDrama newDramas.getTitle() "+newDramas.getTitle());
				
				model.addAttribute("drama", newDramas);
				return "dramadetail";
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return "dramalist";

		}

		return "dashboard";

	}
	
}
