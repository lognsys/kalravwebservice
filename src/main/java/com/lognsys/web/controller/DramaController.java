package com.lognsys.web.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lognsys.dao.dto.AuditoriumsDTO;
import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.model.Drama;
import com.lognsys.model.Users;
import com.lognsys.service.DramaService;
import com.lognsys.service.UserService;
import com.lognsys.util.FormValidator;
import com.lognsys.util.ObjectMapper;

@WebServlet("/dramadetail")
@MultipartConfig(maxFileSize = 16177215) 
//@Controller
public class DramaController {
	@Autowired
	private DramaService dramaService;

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
	
	
	@RequestMapping(value = "/dramadetail", method = RequestMethod.POST)
	public String saveDramaDetail(@ModelAttribute("drama") Drama dramas,BindingResult result, ModelMap model,@RequestParam("file") MultipartFile file) throws IOException {
		System.out.println(" saveDramaDetail file.isEmpty()- " + file.isEmpty());
		System.out.println(" saveDramaDetail file.getName()- " + file.getName());
		System.out.println(" saveDramaDetail file.getOriginalFilename()- " + file.getOriginalFilename());
		if(dramas.getDate().contains(",")){
			dramas.setDate(dramas.getDate().replace(",",""));
		}
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
			  }  
		
		FormValidator formValidator = new FormValidator();
		formValidator.validate(dramas, result);
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

				model.addAttribute("auditoriumList", auditoriumList);
				model.addAttribute("groupsList", groupsList);
		if (result.hasErrors()) {
			System.out.println("Adding dramas - " + dramas.toString());
				
			return "dramadetail";
		} else {

		System.out.println("Adding dramas - " + dramas.toString());
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
