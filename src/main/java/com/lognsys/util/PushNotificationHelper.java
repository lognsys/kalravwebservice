package com.lognsys.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

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

	if(notifications!=null && notifications.getMessage()!=null){
		if(notifications.getUserId()!=0 && notifications.getRealnamee()!=null){
//			 urlParameters = "realname="+notifications.getRealnamee()+"&message="+notifications.getMessage();
			 info.put("title",notifications.getRealnamee()); // Notification title
			 info.put("body", notifications.getMessage()); // Notification
			    
		}
		else if(notifications.getDramaId()!=0 && notifications.getDramaTitle()!=null){
//			 urlParameters = "dramaTitle="+notifications.getDramaTitle()+"&message="+notifications.getMessage();
			 info.put("title",notifications.getDramaTitle()); // Notification title
			 info.put("body", notifications.getMessage()); // Notification
			 	
		}else if(notifications.getDramaId()>0 && notifications.getUserId()>0 && notifications.getDramaTitle()!=null && notifications.getRealnamee()!=null)
				{
//			 urlParameters =  "realname="+notifications.getRealnamee()+"&dramaTitle="+notifications.getDramaTitle()+"&message="+notifications.getMessage();
			 info.put("title",notifications.getRealnamee() + "Your Drama :"+notifications.getDramaTitle()); // Notification title
			 info.put("body", notifications.getMessage()); // Notification
			
		}
		else{
//			 urlParameters =  "message="+notifications.getMessage();
			 info.put("title","Kalrav"); // Notification title
			 info.put("body", notifications.getMessage()); // Notification
						
		}
	}
    
//    info.put("title", "notification title"); // Notification title
//    info.put("body", "message body"); // Notification
                                                            // body
    json.put("notification", info);
    try {
        OutputStreamWriter wr = new OutputStreamWriter(
                conn.getOutputStream());
        wr.write(json.toString());
        wr.flush();

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output;
        System.out.println("Output from Server sendPushNotification .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
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