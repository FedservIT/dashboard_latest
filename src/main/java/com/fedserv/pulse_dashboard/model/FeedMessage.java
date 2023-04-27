package com.fedserv.pulse_dashboard.model;

public class FeedMessage {
	@Override
	public String toString() {
		return "FeedMessage [title=" + title + ", description=" + description + ", link=" + link + ", author=" + author
				+ ", guid=" + guid + ", pubDate=" + pubDate + ", media_content=" + media_content + ", url=" + url
				+ ", image=" + image + ", lastbuilddate=" + lastbuilddate + ", generator=" + generator
				+ ", media_decription=" + media_decription + ", media_title=" + media_title + ", media_thumbnail="
				+ media_thumbnail + ", media_credit=" + media_credit + ", media_group=" + media_group + ", language="
				+ language + ", docs=" + docs + "]";
	}

	String title;
	String description;
	String link;
	String author;
	String guid;
	String pubDate;
	String media_content;
	String url;
	String image;
	String lastbuilddate;
	String coverImages;
	String category;
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getEnclosure() {
		return enclosure;
	}

	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}

	String enclosure ;
	public String getCoverImages() {
		return coverImages;
	}

	public void setCoverImages(String coverImages) {
		this.coverImages = coverImages;
	}

	String generator;
	String media_decription;
	String media_title;
	String media_thumbnail;
	String media_credit;
	String media_group;
	String copyright;
	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDocs() {
		return docs;
	}

	public void setDocs(String docs) {
		this.docs = docs;
	}

	String language;
	String docs;
	public String getMedia_group() {
		return media_group;
	}

	public void setMedia_group(String media_group) {
		this.media_group = media_group;
	}

	public String getMedia_decription() {
		return media_decription;
	}

	public void setMedia_decription(String media_decription) {
		this.media_decription = media_decription;
	}

	public String getMedia_title() {
		return media_title;
	}

	public void setMedia_title(String media_title) {
		this.media_title = media_title;
	}

	public String getMedia_thumbnail() {
		return media_thumbnail;
	}

	public void setMedia_thumbnail(String media_thumbnail) {
		this.media_thumbnail = media_thumbnail;
	}

	public String getMedia_credit() {
		return media_credit;
	}

	public void setMedia_credit(String media_credit) {
		this.media_credit = media_credit;
	}


	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLastbuilddate() {
		return lastbuilddate;
	}

	public void setLastbuilddate(String lastbuilddate) {
		this.lastbuilddate = lastbuilddate;
	}

	public String getGenerator() {
		return generator;
	}

	public void setGenerator(String generator) {
		this.generator = generator;
	}

	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	public String getMedia_content() {
		return media_content;
	}

	public void setMedia_content(String media_content) {
		this.media_content = media_content;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	

}
