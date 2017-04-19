package com.lognsys.util;

import java.util.List;
import java.util.stream.Stream;

import com.google.gson.Gson;

public class CommonUtilities {

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

}
