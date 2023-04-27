package com.fedserv.pulse_dashboard.model;
import java.util.ArrayList;
import java.util.List;

public class Feed {
      final String title;
	@Override
	public String toString() {
		return "Feed [title=" + title + ", link=" + link + ", description=" + description + ", pubDate=" + pubDate
				+ ", media_title=" + media_title + ", media_thumbnail=" + media_thumbnail + ", media_credit="
				+ media_credit + ", media_decription=" + media_decription + ", imageThumb=" + imageThumb + ", entries="
				+ entries + "]";
	}

	final String link;
	final String description;
	//final String language;
	//final String copyright;
	final String pubDate;
	//final String lastBuildDate;

	final String media_title;
	final String media_thumbnail;
	final String media_credit;
	final String media_decription;
//	public String getLastBuildDate() {
//		return lastBuildDate;
//	}

	public String getMedia_decription() {
		return media_decription;
	}

	public String getMedia_title() {
		return media_title;
	}

	public String getMedia_thumbnail() {
		return media_thumbnail;
	}

	public String getMedia_credit() {
		return media_credit;
	}

	

	public String getImageThumb() {
		return imageThumb;
	}

	public List<com.fedserv.pulse_dashboard.model.FeedMessage> getEntries() {
		return entries;
	}

	//final String media_group;

	final String imageThumb;


	final List<com.fedserv.pulse_dashboard.model.FeedMessage> entries = new ArrayList<com.fedserv.pulse_dashboard.model.FeedMessage>();

	public Feed(String title, String link, String guid,String pubDate,String imageThumb,String description, String mediadesc, String mediacontent, String mediatitle,
			String mediathumbnail,String mediacredit ) {
		this.title = title;
		this.link = link;
		this.description = description;
		this.pubDate = pubDate;
		this.media_credit=mediacredit;
		this.media_decription=mediadesc;
		this.media_thumbnail=mediathumbnail;
		this.media_title=mediatitle;
		this.imageThumb = imageThumb;	

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
	public String getPubDate() {
		return pubDate;
	}
 
}
