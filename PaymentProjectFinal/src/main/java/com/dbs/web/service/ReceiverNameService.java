package com.dbs.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

@Service
public class ReceiverNameService {

	@Autowired
	private XmlParserUsingSax handler;
	
	Set<String> sdnNames;

	private final String FILENAME = "sdn.xml";

	public boolean searchNameInFile(String receiverName) {

		SAXParserFactory factory = SAXParserFactory.newInstance();

		try {
			SAXParser saxParser = factory.newSAXParser();
			//PrintNameHandlerSax handler = new PrintNameHandlerSax();
			saxParser.parse(FILENAME, this.handler);
			sdnNames = this.handler.getSdnNames();
			//System.out.println(sdnNames.size());
			receiverName = handler.formatString(receiverName);
			if(sdnNames.contains(receiverName))
				return true;

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
			return false;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}


		return false;
	}

}