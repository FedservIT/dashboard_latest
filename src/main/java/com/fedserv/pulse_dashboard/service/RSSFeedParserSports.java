package com.fedserv.pulse_dashboard.service;

//import com.federal.pulseNews.Model.*;
import com.fedserv.pulse_dashboard.model.*;
import com.fedserv.pulse_dashboard.model.FeedMarkets;
import com.fedserv.pulse_dashboard.model.FeedMessage;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class RSSFeedParserSports {

	static final String TITLE = "title";
	static final String LINK = "link";
	static final String DESCRIPTION = "description";
	static final String LANGUAGE = "language";
	static final String CHANNEL = "channel";
	static final String LASTBUILDDATE = "lastBuildDate";
	static final String COPYRIGHT = "copyright";
	static final String DOCS = "docs";
	static final String IMAGE = "image";
	static final String URL = "url";
	static final String ITEM = "item";
	static final String PUB_DATE = "pubDate";
	static final String GUID = "guid";
	static final String CoverImage = "coverImages";

	// static final String IMAGE = "image";

	String filename;

	public RSSFeedParserSports(String filename) {
		this.filename = filename;
	}

	public InputStream getXML() throws Exception {
		File initialFile = new File(filename);
		InputStream targetStream = new FileInputStream(initialFile);
		return targetStream;
	}

	public FeedMarkets readFeed() throws Exception {
		System.out.println("Inside sports ");
		FeedMarkets feed = null;
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
			String lastBuildDate = "";
			String image = "";
			String url = "";
			String docs = "";
			String coverImages = "";
			String src = "";
			String maindesc = "";

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
					// System.out.println("local part of desc" +
					// event.asStartElement().getName().getLocalPart().getBytes("description"));
					String localPart = event.asStartElement().getName().getLocalPart();
					String mainlocalPart = event.asStartElement().getName().toString();
					System.out.println("Main local part" + mainlocalPart);
					switch (mainlocalPart) {
					case ITEM:
						if (isFeedHeader) {
							isFeedHeader = false;
							feed = new FeedMarkets(title, description, link, image, guid, pubdate, url, coverImages,
									src);
						}
						event = eventReader.nextEvent();
						break;

					case TITLE:
						title = getCharacterData(event, eventReader);
						break;
					case DESCRIPTION:
						System.out.println("description data of sports are++++" + getCharacterData(event, eventReader));
						maindesc = getCharacterData(event, eventReader);
						if (isFeedHeader) {
							isFeedHeader = false;
							feed = new FeedMarkets(title, description, link, image, guid, pubdate, url, coverImages,
									src);
						}
						event = eventReader.nextEvent();
						description = getCharacterData(event, eventReader);
						String[] subdesc = description.split("src=");

						for (int i = 1; i < subdesc.length; i++) {
							System.out.println("1" + subdesc[i]);
							description = subdesc[i];
							// String result = description.replaceAll("^\"+|\"+$", "");
							System.out.println("res" + description.replaceAll("\"", ""));

							if (description != null && description.length() > 0
									&& description.charAt(description.length() - 1) == '/') {
								description = description.substring(0, description.length() - 1);
								description.split("");
							}
							// System.out.println("res+++++" + description.replaceAll("\"", ""));
							description.replaceAll("\"", "");

						}
						break;
					case LINK:
						link = getCharacterData(event, eventReader);
						break;
					case GUID:
						guid = getCharacterData(event, eventReader);
						break;
					case LANGUAGE:
						language = getCharacterData(event, eventReader);
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
					case DOCS:
						docs = getCharacterData(event, eventReader);
						break;
					case URL:
						url = getCharacterData(event, eventReader);
						break;
					case CoverImage:
						coverImages = getCharacterData(event, eventReader);
						break;

					case IMAGE:
						image = getCharacterData(event, eventReader);
						break;

					}
				} else if (event.isEndElement()) {
//					if (event.asEndElement().getName().getLocalPart().contains(IMAGE)) {
//						System.out.println("Inside image started subpart");
//						FeedMessage message = new FeedMessage();
//						message.setTitle(title);
//						message.setUrl(url);
//						message.setLink(link);
//						feed.getMessages().add(message);
//						event = eventReader.nextEvent();
//						continue;
//					}
					if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
						// System.out.println("Title+++" + title);
						FeedMessage message = new FeedMessage();
						System.out.println("Inside ITEM started subpart" + description);
						// message.setAuthor(author);
						message.setTitle(title);
						message.setDescription(maindesc);
						message.setLink(link);
						message.setImage(description.replaceAll("\"", ""));
						message.setGuid(guid);
						message.setPubDate(pubdate);
						message.setLastbuilddate(lastBuildDate);
						message.setDocs(docs);
						message.setLanguage(language);
						message.setUrl(url);
						message.setCoverImages(coverImages);
						System.out.println("messages are" + message);
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
