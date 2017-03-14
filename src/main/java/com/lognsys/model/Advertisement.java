package com.lognsys.model;

import java.net.URL;

public class Advertisement {
	
	private boolean isAdEnable;
	private String typeOfAd;
	private String textAd_headline; //25 chars
	private String textAd_textLine1; //max 35 chars
	private String textAd_textLine2; //max 35 chars
	private URL destinationURL;
	
	/**
	 * 320 x 50: Mobile leaderboard  (HD)
	 * 480 x 32: Mobile banner (landscape) (HD)
	 * 468 x 60: Banner (HD)
	 * 728 x 90: Leaderboard (HD)
	 * 300 x 250: Inline rectangle (HD)
	 * 320 x 480: Smartphone interstitial (portrait)
	 * 480 x 320: Smartphone interstitial (landscape)
 	 * 768 x 1024: Tablet interstitial (portrait)
	 * 1024 x 768: Tablet interstitial (landscape)
	 * 
	 * TODO 1 : Need to work on image size for image ad campaigns
	 */
	

}
