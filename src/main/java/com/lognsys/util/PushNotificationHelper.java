package com.lognsys.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.lognsys.model.Notifications;

public class PushNotificationHelper {
public final static String AUTH_KEY_FCM = "AIzaSyCbsadSh43AQq8IChS4CQkGhAy1QeuaVDE";
public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

public static String sendPushNotification(String deviceToken,Notifications notifications)
        throws IOException {
    String result = "";
    URL url = new URL(API_URL_FCM);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    conn.setUseCaches(false);
    conn.setDoInput(true);
    conn.setDoOutput(true);

    conn.setRequestMethod("POST");
    conn.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);
    conn.setRequestProperty("Content-Type", "application/json");

    JSONObject json = new JSONObject();

    json.put("to", deviceToken.trim());
 
    JSONObject info = new JSONObject();
	
    JSONArray jsonArray=new JSONArray();
    
    JSONObject jsonObject=new JSONObject();
    System.out.println("\n json  notifications toString.... \n"+notifications.toString());
    System.out.println("\n json  notifications obj checking.... \n"+notifications);
    
    
    if(notifications!=null){
		jsonObject.put("dramaId", notifications.getDramaId());

		jsonObject.put("dramaTitle", notifications.getDramaTitle());

		jsonObject.put("userId", notifications.getUserId());

		jsonObject.put("realname", notifications.getRealnamee());

		jsonObject.put("message", notifications.getMessage());
		jsonArray.add(jsonObject);
    } 
	info.put("title", "Notification"); 
    info.put("body", jsonArray);
    json.put("notification", info);
    System.out.println("\n json  .... \n"+json.toString());
    
    try {
        OutputStreamWriter wr = new OutputStreamWriter(
                conn.getOutputStream());
        wr.write(json.toString());
        wr.flush();

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output;
        while ((output = br.readLine()) != null) {
//        	  System.out.println("Output from Server sendPushNotification .... \n"+output);
              
        }
        result = "SUCCESS";
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("FAILURE from Server Exception "+e);
        
        result = "FAILURE";
    }
    System.out.println("GCM Notification is sent successfully");

    return result;
}
}