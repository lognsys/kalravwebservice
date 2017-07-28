package com.lognsys.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.model.Groups;
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
	 */
	@RequestMapping(value = "/groupdetails", method = RequestMethod.GET)
	public String showGroupDetail(Model model, HttpServletRequest request) {
		System.out.println("Going in groupdetails controller");
		Groups groups = new Groups();

		model.addAttribute("groups", groups);
		return "groupdetails";
	}

	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/group", method = RequestMethod.GET)
	public String showGroupsList(Model model, HttpServletRequest request) throws IOException {
		return "group";
	}

	/**
	 * 
	 * @param groups
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/groupedit" }, method = RequestMethod.POST)
	public String editUsers(@ModelAttribute("groupedit") Groups groups, Model model) {

		System.out.println("Going in editUsers controller edit ");
		groups = new Groups();

		model.addAttribute("groups", groups);
		return "groupedit";
	}

	/**
	 * 
	 * @param model
	 * @param groups
	 * @param result
	 * @param groupAction
	 * @return
	 */
	@RequestMapping(value = "/groupdetails", method = RequestMethod.POST)
	public String manageGroup(Model model, @ModelAttribute("groups") Groups groups, BindingResult result,
			@RequestParam String groupAction) {
		System.out.println("Going in manageGroup controller add groupAction " + groupAction);
		System.out.println("Going in manageGroup controller add groups " + groups);

		switch (groupAction) {
		case "add":
			try {

				System.out.println("Going in editUsers controller add ");

				if (groups != null && groups.toString() != null && groups.getGroup_name() != null) {
					System.out.println("Adding groups - " + groups.toString());
					groupService.addGroups(groups);
				}

				return "dashboard";

			} catch (Exception e) {
				e.printStackTrace();
			}
		case "edit":
			try {
				System.out.println("Going in manageGroup controller edit ");

				// CALL database to get roles & groups
				List<GroupsDTO> listOfGroupsDTO = groupService.getAllGroups();
				Groups groupobj = new Groups();

				// Adding data to list from GroupsDTO
				List<String> groupsList = new ArrayList<String>();
				for (GroupsDTO group : listOfGroupsDTO) {
					groupsList.add(group.getGroup_name());
				}

				model.addAttribute("groupsList", groupsList);
				model.addAttribute("groups", groupobj);
				return "groupedit";
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Going in manageGroup controller edit Exception " + e);

			}
			return "dashboard";

		}

		return "dashboard";

	}

	/**
	 * 
	 * @return
	 */
	public String manageGroups() {
		return null;

	}
}
