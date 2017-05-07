package com.lognsys.util;

public class Constants {

	/**
	 * enum contains keys of queries defined in sql.properties.
	 * 
	 * Note: All queries should be added in sql.properties and all keys should
	 * be added to USER_QUERIES enum
	 */
	public enum USER_QUERIES {
		insert_users, select_users, delete_users, select_users_exists, select_users_id, delete_users_email,update_users
	}
	public enum ROLES_QUERIES {
		insert_users_roles, insert_users_roleid, select_roles_all
	}
	public enum DRAMA_QUERIES {
		insert_dramas, select_dramas, delete_dramas, select_dramas_exists, select_dramas_id,
		delete_dramas_title,select_dramas_title
	}
	public enum AUDITORIUM_QUERIES {
		insert_auditoriums,
		select_auditoriums,
		select_auditorium_name_bydramaid,
		select_dramabyauditorium,
		select_dramasauditoriums_all
		
	}

	
	/**
	 * enum contains keys of queries defined in sql.properties.
	 * 
	 * Note: All queries should be added in sql.properties and all keys should
	 * be added to GROUP_QUERIES enum
	 */
	public enum GROUP_QUERIES {

		insert_user_groups,
		select_groups_all,
		select_groupname_byuserid,
		select_id_bygroupname,
		select_usersbygroups,
		select_usersgroups_all,
		select_groupname_bydramaid,
		select_dramasbygroups,
		select_dramasgroups_all

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
	
	public enum USER_FIELD_NAMES {
		usersId, realname, username, auth_id, phone, location, provenance, birthdate, enabled, notification, device, address, city, state, zipcode, company_name, title
	}
	public enum DRAMA_FIELD_NAMES {
		dramasId, title,imageurl, drama_length, date, genre, star_cast, description, director, writer, music, avg_rating
	}
	public enum GROUPS_FIELDNAME {
		groupsId, group_name
	}
	public enum AUDITORIUMS_FIELDNAME {
		auditoriumsId, auditorium_name,address,lat_lon
	}
}

