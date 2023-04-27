package com.fedserv.pulse_dashboard.model;

import java.util.ArrayList;
import java.util.List;

public class HinduTopnewsModel {

	String link;
	public HinduTopnewsModel(String link, String title, String pubDate, String description, String category,String author) {
		//super();
		this.link = link;
		this.title = title;
		this.pubDate = pubDate;
		this.description = description;
		this.category = category;
		this.author = author;
	}
	String title;
	String pubDate;
	String description;
	String category;
	String author;
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	List<com.fedserv.pulse_dashboard.model.FeedMessage> entries = new ArrayList<com.fedserv.pulse_dashboard.model.FeedMessage>();
	public String getLink() {
		return link;
	}
	public List<com.fedserv.pulse_dashboard.model.FeedMessage> getMessages() {
		return entries;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	

	
}
