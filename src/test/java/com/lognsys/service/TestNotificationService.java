package com.lognsys.service;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lognsys.model.Notifications;
import com.lognsys.model.Users;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml", "classpath:datasource-context.xml",
		"classpath:mongo-context.xml" })
public class TestNotificationService {

	@Autowired
	private NotificationService notificationService;

	public void setUp() {

	}

	@Test
	public void testRefreshList() throws IOException {
		notificationService.refreshNotificationList();
	}

	@Test
	public void testAddNotification() throws IOException {
		Notifications notifications = new Notifications();

		notifications.setNotify(true);

		notifications.setMessage("Hello");
		notificationService.addNotification(notifications);
	}
	
	@Test
	public void testNotificationService(){
		String message = "Hello";
		
		Notifications notifications = notificationService.getNotificationByMessage(message);
		System.out.println("notifications - "+notifications);
		
		
	}

}
