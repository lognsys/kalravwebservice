package com.lognsys.web.controller;


//TODO 1: PUT AOP FOR LOGGING
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.lognsys.service.GroupService;

@Controller
@RequestMapping("/group")

public class GroupController {

	@Autowired
	private GroupService groupService;

	
	/**
	 * 
	 * Returns all groups and subgroups from cached map
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 * 
	 *  NOTE: value="" which indicates that it will take global request mapping.
	 *  
	 *  
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getGroupsAndSubgroup(Model model, HttpServletRequest request) {

		Map<String, List<String>> mapOfGroup = groupService.getAllGroupAndSubGroup();
		model.addAttribute("mapOfGroup", mapOfGroup);
		return "group";
	}
	
	/**
	 * Add Group or subgroups based on JSON Key
	 * 
	 * @param GroupsDTO
	 * 
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addGroup(@RequestBody String groups) {
		System.out.println("ADD Groups - "+groups);
		//groupService.addGroupOrSubGroup(groupsDTO);
		return "group";
	}

	/**
	 * Remove Group or Subgroup based on 
	 * @param groups
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteGroup(@RequestBody String groups) {
		System.out.println("DEL Groups - "+groups);
		//groupService.addGroupOrSubGroup(groupsDTO);
		return "group";
	}
}
