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
		insert_dramas, select_dramas, delete_dramas, select_dramas_exists, select_dramas_id, delete_dramas_title, select_dramas_title, update_dramas
	}

	public enum AUDITORIUM_QUERIES {
		insert_auditoriums, select_auditoriums, insert_dramas_auditoriums, select_auditorium_name_bydramaid, select_dramabyauditorium, select_dramasauditoriums_all
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

	/**
	 * enum contains keys of json files and their directory path defined in
	 * application.properties.
	 * 
	 * Note: All the files should be specified in application.properties.
	 */
	public enum JSON_FILES {
		user_filename, drama_filename, adverts_filename, booking_filename,
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

	// REST URL constants
	public static final String DRAMA_LIST_URL = "/dramalist";

	/**
	 * REST CONTROLLER APPLICATION PROPERTIES STATUS MESSAGES Note: Please make
	 * sure any changes made to Enum REST_MSGS needs corresponding changes to
	 * application.properties. Failure to do will create a bug
	 */
	public enum REST_MSGS {
		response_userempty, response_userinvalid
	}

	/**
	 * Exception Messages
	 */

	public enum EXCEPTIONS_MSG {
		exception_userinvalid, exception_userempty
	}

	public enum DEFAULT_SUBGROUP {
		None
	}

}
