package com.lognsys.util;

import java.io.IOException;

/**
 * @author pdoshi
 * 
 * Description : 
 * ObjectMapper class is used to map POJO from model to Data Transfer Object DTO
 * POJO in model in directory is used with frontend and validation which needs to be mapped to corresponding DTO
 * where fields are identical to the mysql database. 
 * 
 * CHANGE LOG:
 * PJD - 23/05/17 : Changing the setting of fields using constructor to setter method.
 * 
 */

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.json.simple.JSONArray;

import com.lognsys.dao.dto.AuditoriumsDTO;
import com.lognsys.dao.dto.BookedRowSeatsDTO;
import com.lognsys.dao.dto.BookedSeatsDTO;
import com.lognsys.dao.dto.BookingDTO;
import com.lognsys.dao.dto.DeviceDTO;
import com.lognsys.dao.dto.DramasAuditoriumsDTO;
import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.DramasGroupsDTO;
import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.dao.dto.NotificationsDTO;
import com.lognsys.dao.dto.RatingsDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.dto.UsersGroupsDTO;
import com.lognsys.model.BookedRowSeatTable;
import com.lognsys.model.BookedSeats;
import com.lognsys.model.Booking;
import com.lognsys.model.Device;
import com.lognsys.model.Drama;
import com.lognsys.model.DramasTable;
import com.lognsys.model.Groups;
import com.lognsys.model.Notifications;
import com.lognsys.model.NotificationsTable;
import com.lognsys.model.Ratings;
import com.lognsys.model.Users;
import com.lognsys.model.UsersTable;

public class ObjectMapper {

	/**
	 * Users Table Object requires fields to be mapped from Group and Users
	 * Object
	 * 
	 * @param users
	 *            - the Users Object and group object is added to Users Tabled
	 * @return
	 */
	public static UsersTable mapToUserTable(UsersDTO users, GroupsDTO groups) {

		return new UsersTable(users.getId(), users.getRealname(), users.getUsername(), groups.getGroup_name(),
				users.isEnabled());

	}

	/**
	 * Based on the group map all the usersDTO object to Users Tables object
	 * 
	 * @param groups
	 * @return
	 */
	public static List<UsersTable> mapToUserTable(List<UsersGroupsDTO> userGroups) {
		List<UsersTable> list = new ArrayList<>();
		for (UsersGroupsDTO g : userGroups) {
			list.add(new UsersTable(g.getUser().getId(), g.getUser().getRealname(), g.getUser().getUsername(),
					g.getGroups().getGroup_name(), g.getUser().isEnabled()));
		}
		return list;
	}
	public static List<BookedRowSeatTable> mapToBookedRowSeatTable(List<BookedRowSeatsDTO> listbookedRowDto) {
		List<BookedRowSeatTable> list = new ArrayList<>();
		for (BookedRowSeatsDTO g : listbookedRowDto) {
			list.add(new BookedRowSeatTable(g.getRowSeat().getRow_num(), g.getRowSeat().getSeat_num(), g.getBooking().getAuditoriums_id(),
					g.getBooking().getStatus()));
		}
		return list;
	}
	/**
	 * 
	 * Map POJO Users Object to Users DTO Object
	 * 
	 * @param users
	 * @return
	 */
	public static UsersDTO mapToUsersDTO(Users users) {

		UsersDTO uDTO = new UsersDTO();

		uDTO.setId(users.getId());
		uDTO.setRealname(users.getFirstname(), users.getLastname());
		uDTO.setUsername(users.getUsername());
		uDTO.setAuth_id(users.getAuth_id());
		uDTO.setPhone(users.getPhone());
		uDTO.setProvenance(users.getProvenance());
		uDTO.setBirthdate(users.getBirthdate());
		uDTO.setEnabled(users.isEnabled());
		uDTO.setNotification(users.isNotification());
		uDTO.setDevice(users.getDevice());
		uDTO.setAddress(users.getAddress());
		uDTO.setCity(users.getCity());
		uDTO.setState(users.getState());
		uDTO.setZipcode(users.getZipcode());

		return uDTO;

	}

	/**
	 * Map UsersDTO with Users object in model directory
	 * 
	 * NOTE: Sync Users.java fields with this method
	 * 
	 * @param users
	 * @return
	 */
	public static Users mapToUsers(UsersDTO users) {
		// TODO: Current setting of group to null, but need to change to value

		String[] splited = null;
		String firstname = "", lastname = "";

		splited = CommonUtilities.splitByDelemeter(users.getRealname(), " ");

		if (splited == null) {
			firstname = "";
			lastname = "";
		}

		if (splited.length == 1) {
			firstname = splited[0];
			lastname = "";
		}

		if (splited.length == 2) {
			firstname = splited[0];
			lastname = splited[1];
		}

		Users newusers = new Users(users.getId(), users.getAuth_id(), users.getUsername(), users.getRealname(),
				users.getPhone(), users.getProvenance(), users.getBirthdate(), users.isEnabled(),
				users.isNotification(), users.getDevice(), users.getAddress(), users.getCity(), users.getState(),
				users.getZipcode(), firstname, lastname);

		return newusers;

	}

	/**
	 * 
	 * @param dramas
	 * @return
	 */
	public static Drama mapToDramas(DramasDTO dramas) {
		// TODO: Current setting of group to null, but need to change to value
		return new Drama(dramas.getId(), dramas.getTitle(), dramas.getImageurl(), dramas.getDrama_length(),
				dramas.getDate(), dramas.getGenre(), dramas.getStar_cast(), dramas.getDescription(),
				dramas.getDirector(), dramas.getWriter(), dramas.getMusic(), dramas.getAvg_rating(),
				dramas.getDrama_language());

	}

	/**
	 * 
	 * @param dramas
	 * @return
	 */
	public static DramasDTO mapToDramasDTO(Drama dramas) {
		// TODO: Current setting of group to null, but need to change to value

		return new DramasDTO(dramas.getId(), dramas.getTitle(), dramas.getImageurl(), dramas.getDrama_length(),
				dramas.getDate(), dramas.getGenre(), dramas.getStar_cast(), dramas.getDescription(),
				dramas.getDirector(), dramas.getWriter(), dramas.getMusic(), dramas.getAvg_rating(),
				dramas.getDrama_language());

	}

	/**
	 * 
	 * @param dramaGroups
	 * @return
	 */
	public static List<DramasTable> mapToDramasTable(List<DramasGroupsDTO> dramaGroups) {
		List<DramasTable> list = new ArrayList<>();
		for (DramasGroupsDTO g : dramaGroups) {
			list.add(new DramasTable(g.getDrama().getId(), g.getDrama().getTitle(), g.getGroups().getGroup_name(), null,
					null));
		}
		return list;
	}

	/**
	 * 
	 * @param dramaAuditorium
	 * @return
	 */
	public static List<DramasTable> mapToDramasAuditoriumTable(List<DramasAuditoriumsDTO> dramaAuditorium) {
		List<DramasTable> list = new ArrayList<>();
		for (DramasAuditoriumsDTO a : dramaAuditorium) {
			list.add(new DramasTable(a.getDrama().getId(), a.getDrama().getTitle(),
					a.getAuditoriums().getAuditorium_name(), null, null));
		}
		return list;
	}

	// mapToGroupsDTO
	public static GroupsDTO mapToGroupsDTO(Groups groups) {
		return new GroupsDTO(groups.getId(), groups.getGroup_name());
	}

	/**
	 * 
	 * @param ratings
	 * @return
	 */
	public static RatingsDTO mapToRatingsDTO(Ratings ratings) {

		return new RatingsDTO(ratings.getId(), ratings.getRating(), ratings.getRating_date(), ratings.getDramas_id(),
				ratings.getUsers_id());

	}

	/**
	 * 
	 * @param notification
	 * @return
	 */
	public static NotificationsDTO mapToNotificationsDTO(Notifications notifications) {
		NotificationsDTO notificationsDTO = new NotificationsDTO();
		notificationsDTO.setId(notifications.getId());
		notificationsDTO.setNotify(notifications.isNotify());
		notificationsDTO.setMessage(notifications.getMessage());
		notificationsDTO.setUserId(notifications.getUserId());
		notificationsDTO.setDramaId(notifications.getDramaId());
		notificationsDTO.setRealname(notifications.getRealnamee());
		notificationsDTO.setDramaTitle(notifications.getDramaTitle());
		return notificationsDTO;

	}

	public static List<NotificationsTable> mapToNotificationsDTO(List<NotificationsDTO> notificationsDTOs) {

		List<NotificationsTable> list = new ArrayList<>();
		for (NotificationsDTO ndto : notificationsDTOs) {
			list.add(new NotificationsTable((ndto).getId(), (ndto).isNotify(), (ndto).getMessage(),
					(ndto).getRealname(), (ndto).getDramaTitle()));
		}
		return list;

	}

	public static List<Notifications> mapToNotificationsDTOTable(List<NotificationsTable> notificationstables) {

		List<Notifications> list = new ArrayList<>();
		for (NotificationsTable ndto : notificationstables) {
			list.add(new Notifications((ndto).getId(), (ndto).getNotify(), (ndto).getMessage(),
					(ndto).getRealname(), (ndto).getDramaTitle()));
		}
		return list;

	}
	public static Notifications mapToNotifications(NotificationsDTO notificationsDTO) {

		Notifications newNotifications = new Notifications(notificationsDTO.getId(), notificationsDTO.isNotify(),
				notificationsDTO.getMessage(), notificationsDTO.getUserId(), notificationsDTO.getDramaId(),
				notificationsDTO.getRealname(), notificationsDTO.getDramaTitle());

		return newNotifications;

	}

	public static JSONArray mapToAuditoriumDTO(List<AuditoriumsDTO> auditoriumsDTOs) {
		Hashtable<String, String[]> hashtable = null;
		JSONArray array = new JSONArray();

		return array;

	}

	/**
	 * 
	 * @param dramas
	 * @return
	 */
	public static DeviceDTO mapToDeviceDTO(Device device) {
		// TODO: Current setting of group to null, but need to change to value
		DeviceDTO deviceDTO =new DeviceDTO();
		deviceDTO.setUsers_id(device.getUsers_id());
		deviceDTO.setDeviceToken(device.getDeviceToken());
		return deviceDTO;
	}

	/**
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static Users convertJSONtoObject(String jsonStr) {
		com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
		Users users = null;
		try {
			users = mapper.readValue(jsonStr, Users.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	
	
	public static BookingDTO mapToBookingDTO(Booking booking) {

		BookingDTO bookingDTO = new BookingDTO();

		bookingDTO.setBooking_date(booking.getBooking_date());
		bookingDTO.setConfirmation_no(booking.getConfirmation_no());
		bookingDTO.setUsers_id(booking.getUsers_id());
		bookingDTO.setDramas_id(booking.getDramas_id());
		bookingDTO.setAuditoriums_id(booking.getAuditoriums_id());
		bookingDTO.setPrice(booking.getPrice());
		bookingDTO.setStatus(booking.getStatus());

		return bookingDTO;

	}
	
	public static BookedSeatsDTO mapToBookedSeatsDTO(BookedSeats bookedSeats) {

		BookedSeatsDTO bookedSeatsDTO = new BookedSeatsDTO();

		bookedSeatsDTO.setBooking_id(bookedSeats.getBooking_id());
		bookedSeatsDTO.setRow_seats_id(bookedSeats.getRow_seats_id());
		bookedSeatsDTO.setSeat_status(bookedSeats.getSeat_status());

		return bookedSeatsDTO;

	}
}
