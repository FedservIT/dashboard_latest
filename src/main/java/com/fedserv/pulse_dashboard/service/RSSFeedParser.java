package com.fedserv.pulse_dashboard.service;

//import com.federal.pulseNews.Model.*;
import com.fedserv.pulse_dashboard.model.*;
import com.fedserv.pulse_dashboard.model.Feed;
import com.fedserv.pulse_dashboard.model.FeedMessage;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class RSSFeedParser {

	static final String TITLE = "title";
	static final String DESCRIPTION = "description";
	static final String CHANNEL = "channel";
	static final String LANGUAGE = "language";
	static final String COPYRIGHT = "copyright";
	static final String LINK = "link";
	static final String AUTHOR = "author";
	static final String ITEM = "item";
	static final String PUB_DATE = "pubDate";
	static final String GUID = "guid";
	static final String MEDIA = "mediacontent";
	static final String MEDIA_TITLE = "{https://search.yahoo.com/mrss/}title";
	static final String MEDIA_CREDIT = "{https://search.yahoo.com/mrss/}credit";
	static final String MEDIA_THUMBNAIL = "mediathumbnail";
	static final String MEDIA_DESCRIPTION = "{https://search.yahoo.com/mrss/}description";
	static final String MEDIA_GROUP = "{https://search.yahoo.com/mrss/}group";
	static final String IMAGETHUMB = "imageThumb";

	// static final String IMAGE = "image";
	static final String LASTBUILDDATE = "lastBuildDate";
	static final String GENERATOR = "generator";
	static final String URL = "url";

	String filename;

	public RSSFeedParser(String filename) {
		this.filename = filename;
	}

	public InputStream getXML() throws Exception {
		File initialFile = new File(filename);
		InputStream targetStream = new FileInputStream(initialFile);
		return targetStream;
	}

	public Feed readFeed() throws Exception {
		Feed feed = null;
		try {
			boolean isFeedHeader = true;
			// Set header values intial to the empty string
			String description = "";
			String title = "";
			String link = "";
			String language = "";
			String copyright = "";
			String author = "";
			String pubdate = "";
			String guid = "";
			String media_content = "";
			String lastBuildDate = "";
			String generator = "";
			String imageThumb = "";
			String mediadescription = "";
			String media_thumbnail = "";
			String mediatitle = "";
			String media_credit = "";
			String media_group = "";

			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// Setup a new eventReader

			InputStream in = getXML();

			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			// read the XML document
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();

				/*
				 * if(event.toString().contains("media:content")) { System.out.println(
				 * "_________________________________________________" );
				 * System.out.println(event.toString() );
				 * 
				 * System.out.println( "_________________________________________________" ); }
				 */

				if (event.isStartElement()) {
					String localPart = event.asStartElement().getName().getLocalPart();
					String mainlocalPart = event.asStartElement().getName().toString();
					switch (mainlocalPart) {
					case ITEM:
						if (isFeedHeader) {
							isFeedHeader = false;
							feed = new Feed(title, link, guid, pubdate, imageThumb, description, mediadescription,
									media_content, mediatitle, media_thumbnail, media_credit);
						}
						event = eventReader.nextEvent();
						break;

					case MEDIA_CREDIT:
						media_credit = getCharacterData(event, eventReader);
						break;
					case MEDIA_DESCRIPTION:
						mediadescription = getCharacterData(event, eventReader);
						break;
					case MEDIA_THUMBNAIL:
						media_thumbnail = getCharacterData(event, eventReader);
						break;
					case MEDIA_TITLE:
						mediatitle = getCharacterData(event, eventReader);
						break;
					case TITLE:
						title = getCharacterData(event, eventReader);
						break;
					case DESCRIPTION:
						description = getCharacterData(event, eventReader);
						break;

					case LINK:
						link = getCharacterData(event, eventReader);
						break;
					case MEDIA:
						media_content = getCharacterData(event, eventReader);
						break;
					case GUID:
						guid = getCharacterData(event, eventReader);
						break;
					case LANGUAGE:
						language = getCharacterData(event, eventReader);
						break;
					case AUTHOR:
						author = getCharacterData(event, eventReader);
						break;
					case PUB_DATE:
						pubdate = getCharacterData(event, eventReader);
						break;
					case COPYRIGHT:
						copyright = getCharacterData(event, eventReader);
						break;
					case LASTBUILDDATE:
						lastBuildDate = getCharacterData(event, eventReader);
						break;
					case GENERATOR:
						generator = getCharacterData(event, eventReader);
						break;
					case IMAGETHUMB:
						imageThumb = getCharacterData(event, eventReader);
						break;

					}
				} else if (event.isEndElement()) {
					// if
					// (event.asEndElement().getName().getLocalPart().contains(MEDIA_GROUP))
					// {
					// FeedMessage message = new FeedMessage();
					// message.setMedia_content(media_content);
					// message.setMedia_credit(media_credit);
					// message.setMedia_decription(media_description);
					// message.setMedia_thumbnail(media_thumbnail);
					// message.setMedia_title(media_title);
					// feed.getMessages().add(message);
					// System.out.println("media
					// group+++++++++++++++++++"+feed.getMessages().add(message));
					// event = eventReader.nextEvent();
					// continue;
					// }
					if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
						// System.out.println("Title+++" + title);
						FeedMessage message = new FeedMessage();
						message.setAuthor(author);
						message.setDescription(description);
						message.setImage(imageThumb);
						message.setGuid(guid);
						message.setLink(link);
						message.setTitle(title);
						message.setPubDate(pubdate);
						message.setLastbuilddate(lastBuildDate);
						message.setGenerator(generator);
						message.setMedia_content(media_content);
						message.setMedia_credit(media_credit);
						message.setMedia_decription(mediadescription);
						message.setMedia_thumbnail(media_thumbnail);
						message.setMedia_title(mediatitle);
						feed.getMessages().add(message);
						event = eventReader.nextEvent();
						continue;
					}

				}
			}
		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		}
		return feed;
	}

	private String getCharacterData(XMLEvent event, XMLEventReader eventReader) throws XMLStreamException {
		String result = "";
		event = eventReader.nextEvent();
		if (event instanceof Characters) {
			result = event.asCharacters().getData();
		}
		return result;
	}

}
