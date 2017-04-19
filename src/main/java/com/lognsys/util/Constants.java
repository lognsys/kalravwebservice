package com.lognsys.util;

public class Constants {

	/**
	 * enum contains keys of queries defined in sql.properties.
	 * 
	 * Note: All queries should be added in sql.properties and all keys should
	 * be added to USER_QUERIES enum
	 */
	public enum USER_QUERIES {
		insert_users, select_users, delete_users, select_users_exists, select_users_id
	}

	/**
	 * enum contains keys of json files and their directory path defined in application.properties.
	 * 
	 * Note: All the files should be specified in application.properties. 
	 */
	public enum JSON_FILES {
		user_filename, drama_filename, adverts_filename, booking_filename,
	}

}
