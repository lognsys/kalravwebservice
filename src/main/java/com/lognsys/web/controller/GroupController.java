package com.lognsys.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.model.Drama;
import com.lognsys.service.GroupService;

@Controller
public class GroupController {

	@Autowired
	private GroupService groupService;

	
	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/group", method = RequestMethod.GET)
	public String getGroupsAndSubgroup(Model model, HttpServletRequest request) {

		Map<String, List<String>> mapOfGroup = groupService.getAllGroupAndSubGroup();
		model.addAttribute("mapOfGroup", mapOfGroup);

		return "group";
	}
	
	@RequestMapping(value = "/addgroup", method = RequestMethod.POST)
	public void addGroup(@RequestBody GroupsDTO groupsDTO) {
		groupService.addGroup(groupsDTO);
	}

	@RequestMapping(value = "/deletegroup", method = RequestMethod.POST)
	public void deleteGroup() {
		groupService.
	}
}
