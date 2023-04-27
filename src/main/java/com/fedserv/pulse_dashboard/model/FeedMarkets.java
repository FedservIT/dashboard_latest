package com.fedserv.pulse_dashboard.model;

import java.util.ArrayList;
import java.util.List;

/*
 * Stores an RSS feed
 */
public class FeedMarkets {

	final String title;
	final String url;

	public String getUrl() {
		return url;
	}

	public String getImage() {
		return image;
	}

	public String getGuid() {
		return guid;
	}

	final String link;
	final String description;
	final String image;
	final String guid;
	final String pubDate;
	final String coverImages;
	final String src;

	

	public String getSrc() {
		return src;
	}

	public String getCoverImages() {
		return coverImages;
	}

	public List<com.fedserv.pulse_dashboard.model.FeedMessage> getEntries() {
		return entries;
	}

	//final String media_group;

	


	final List<com.fedserv.pulse_dashboard.model.FeedMessage> entries = new ArrayList<com.fedserv.pulse_dashboard.model.FeedMessage>();

	public FeedMarkets(String title, String description, String link, String image,String guid, String pubDate,String url,String coverImages,String src) {
		this.title = title;
		this.link = link;
		this.description = description;
		this.pubDate = pubDate;
		this.image=image;
		this.guid=guid;
        this.url=url;
        this.coverImages=coverImages;
        this.src=src;
        
	}

	public List<com.fedserv.pulse_dashboard.model.FeedMessage> getMessages() {
		return entries;
	}

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}

	public String getDescription() {
		return description;
	}

//	public String getLanguage() {
//		return language;
//	}
//
//	public String getCopyright() {
//		return copyright;
//	}

	public String getPubDate() {
		return pubDate;
	}

	
}