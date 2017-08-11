package com.lognsys.util;

public class Constants {

	/**
	 * enum contains keys of queries defined in sql.properties.
	 * 
	 * Note: All queries should be added in sql.properties and all keys should
	 * be added to USER_QUERIES enum
	 */
	public enum USER_QUERIES {
		insert_users, select_users, delete_users, select_users_exists, select_users_id, delete_users_email, update_users, select_users_username
	}

	public enum ROLES_QUERIES {
		insert_users_roles, insert_users_roleid, select_roles_all, select_role_byuserid, update_roles_byuser
	}

	public enum DRAMA_QUERIES {
		insert_dramas, select_dramas, select_all_dramas, delete_dramas, select_dramas_exists, select_dramas_id, delete_dramas_title, select_dramas_title, update_dramas
	}

	public enum AUDITORIUM_QUERIES {
		select_auditorium_id_byname, select_new_audi_price_time, select_new_name_id_auditorium_by_dramaId, insert_auditoriums, select_auditoriums, insert_dramas_auditoriums, select_auditorium_name_bydramaid, select_dramabyauditorium, select_dramasauditoriums_all

	}

	public enum NOTIFICATION_QUERIES {
		insert_notification, select_notification, delete_notification, select_notification_message,delete_notification_by_id,
	}
	public enum DEVICE_QUERIES {
		insert_user_devices,select_devices_exists,update_devices,select_all_devices,select_device_by_id,delete_device
		}
	public enum BOOKING_QUERIES {
		insert_booking, select_booking_all,delete_booking_by_Id,select_booking_by_users_id
	}
	public enum BOOKEDSEATS_QUERIES {
		insert_bookedseats,select_booked_seats,delete_bookedseats_by_Id
	}
	/**
	 * enum contains keys of queries defined in sql.properties.
	 * 
	 * Note: All queries should be added in sql.properties and all keys should
	 * be added to GROUP_QUERIES enum
	 */
	public enum GROUP_QUERIES {

		insert_user_groups, insert_groups, insert_dramas_groups, insert_drama_auditoriums, select_groups_all, insert_subgroups_groups, select_groupname_byuserid, select_id_bygroupname, select_usersbygroups, select_usersgroups_all, select_groupname_bydramaid, select_dramasbygroups, select_dramasgroups_all, select_groups_exists, update_group_byuser, select_subgroup_bygroup

	}

	public enum ROWSEAT_QUERIES {

		insert_rowseat,select_rowseat_all,delete_by_id,select_rowseat_by_auditoriums_id,get_select_id
	}

	public enum RATING_QUERIES {
		insert_ratings, select_ratings_exists, update_ratings, select_users_id_and_dramas_id,select_all_ratings
	}

	/**
	 * enum contains keys of json files and their directory path defined in
	 * application.properties.
	 * 
	 * Note: All the files should be specified in application.properties.
	 */
	public enum JSON_FILES {
		user_filename, drama_filename, adverts_filename, booking_filename, notification_filename
	}

	/**
	 * Fields defined here are exactly same as in database. This is to identify
	 * fieldname in sql query and protect from any misnomers of the fieldname
	 * with database
	 * 
	 * Caveat : Any change in the database fields adding or dropping requires to
	 * change field names here
	 * 
	 */
	public enum USER_FIELD_NAMES {
		usersId, realname, username, auth_id, phone, location, provenance, birthdate, enabled, notification, device, address, city, state, zipcode, company_name, title, role, group
	}

	public enum DRAMA_FIELD_NAMES {
		dramasId, title, imageurl, drama_length, date, genre, star_cast, description, director, writer, music, avg_rating, group_name, auditorium_name, group_id, auditorium_id, dramas_language
	}

	public enum GROUPS_FIELDNAME {
		groupsId, group_name
	}

	public enum AUDITORIUMS_FIELDNAME {
		auditoriumsId, auditorium_name, address, latitude, longitude
	}
	public enum BOOKING_FIELD_NAMES {
		id, booking_date, confirmation_no, users_id, dramas_id, auditoriums_id,price,status
	}
	public enum BOOKEDSEATS_FIELD_NAMES {
		id, booking_id, row_seats_id, seat_status
	}
	public enum DEVICE_FIELD_NAMES {
		id, users_id, deviceToken
	}
	public enum ROWSEAT_FIELD_NAMES {
		id, row_num, row_name, seat_num, auditoriums_id
	}
	// REST URL constants
	public static final String DRAMA_LIST_URL = "/dramalist";

	/**
	 * REST CONTROLLER APPLICATION PROPERTIES STATUS MESSAGES Note: Please make
	 * sure any changes made to Enum REST_MSGS needs corresponding changes to
	 * application.properties. Failure to do will create a bug
	 */
	public enum REST_MSGS {
		response_userempty, response_userinvalid, response_userexists,
		response_dramaexists,response_dramaempty,
		response_auditoriumempty,
		response_ratingsuccess,response_bookingmempty
	}

	/**
	 * Exception Messages
	 */

	public enum EXCEPTIONS_MSG {
		exception_userinvalid, exception_userempty, exception_allgroups
	}

	public enum DEFAULT_GROUP {
		NONE
	}
	
	public enum DEFAULT_ROLE {
		GUEST
	}

}
