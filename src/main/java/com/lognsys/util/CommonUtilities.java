package com.lognsys.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.google.gson.Gson;
import com.lognsys.model.Users;

public class CommonUtilities {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * Convert string arr to int arr
	 * 
	 * @param arr
	 * @return
	 */
	public static int[] parseIntArray(String[] arr) {
		return Stream.of(arr).mapToInt(Integer::parseInt).toArray();
	}

	/**
	 * Map to json
	 */
	public static <T> String convertToJSON(List<T> list) {
		Gson gson = new Gson();
		String str = gson.toJson(list);
		return str;
	}

	/**
	 * Splits the String by Delimiter
	 * 
	 * Test Cases: 1) "Priyank Doshi" 2) "Priyank " 3) "" 4) null
	 * 
	 * @param str
	 * @param delimeter
	 * @return array with 1, 2 values or null
	 */
	static String[] splitByDelemeter(String str, String delimeter) {

		if (str != null) {
			String[] vals = str.trim().split(delimeter);

			return vals;
		}
		return null;
	}

	/**
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isValidEmail(String email) {

		boolean isValid = false;

		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		
		if (matcher.matches()) {
			isValid = true;
		}
		
		return isValid;
	}

}
