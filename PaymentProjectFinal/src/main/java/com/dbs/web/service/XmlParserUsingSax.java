package com.dbs.web.service;
import java.util.Arrays;
import java.util.HashSet;

import java.util.Set;
import java.util.StringJoiner;

import org.springframework.stereotype.Service;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

@Service
public class XmlParserUsingSax extends DefaultHandler{

	private StringBuilder currentValue = new StringBuilder();
	private Set<String> sdnNames = new HashSet<String>();
	private String sdnFirstName = "";
	private	String sdnLastName = "";
	private String sdnCompleteName = "";
	private	String akaFirstName = "";
	private	String akaLastName = "";
	private	String akaCompleteName = "";
	private String[] arrOfStr;
	boolean akaEntry = false;

	public Set<String> getSdnNames() {
		return sdnNames;
	}

	@Override
	public void startDocument() {
		//System.out.println("Start Document");
	}

	@Override
	public void endDocument() {
		//  System.out.println("End Document");
		//System.out.println(sdnNames.size());  //36404

	}

	@Override
	public void startElement(
			String uri,
			String localName,
			String qName,
			Attributes attributes) {

		currentValue.setLength(0);
		
		
		if (qName.equalsIgnoreCase("sdnEntry")) { // 9124
			sdnFirstName = "";
			sdnLastName = "";
		}
		
		if (qName.equalsIgnoreCase("aka")) { // 14719
			akaEntry = true;
			akaFirstName = "";
			akaLastName = "";
		}

	}

	@Override
	public void endElement(String uri,
			String localName,
			String qName) {

		if (qName.equalsIgnoreCase("firstName")) {  // 11089
			if(akaEntry) {
				akaFirstName = currentValue.toString();
				sdnNames.add(formatString(akaFirstName));
			}
			else {
				sdnFirstName = currentValue.toString();
				sdnNames.add(formatString(sdnFirstName));
			}
		}
		if (qName.equalsIgnoreCase("lastName")) {
			
			if(akaEntry) {
				akaLastName = currentValue.toString();
				sdnNames.add(formatString(akaLastName));
			}
			else {
				sdnLastName = currentValue.toString();
				sdnNames.add(formatString(sdnLastName));
			}
		}

		if (qName.equalsIgnoreCase("sdnEntry")) {
			if(sdnFirstName!="" && sdnLastName!="") {
				sdnCompleteName = sdnFirstName + " " +sdnLastName;
				sdnNames.add(formatString(sdnCompleteName));
				sdnFirstName="";
				sdnLastName="";
			}
		}
		if (qName.equalsIgnoreCase("aka")) {
			akaEntry = false;
			if(akaFirstName!="" && akaLastName!="") {
				akaCompleteName = akaFirstName + " " +akaLastName;
				sdnNames.add(formatString(akaCompleteName));
				akaFirstName="";
				akaLastName="";
			}

		}

	}

	// SAX parsers may return all contiguous character data in a single chunk,
	// or they may split it into several chunks
	@Override
	public void characters(char ch[], int start, int length) {

		currentValue.append(ch, start, length);

	}

	public String formatString(String name) { 
		arrOfStr = name.trim().toUpperCase().split(" ");
		Arrays.sort(arrOfStr);
		StringJoiner joiner = new StringJoiner(" ");
		for(int i = 0; i < arrOfStr.length; i++) {
			joiner.add(arrOfStr[i]);
		}
		return joiner.toString().trim();
	}
}


