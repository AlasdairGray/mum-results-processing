package uk.ac.manchester.mum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ResultFileProcessor {
	
	public Collection<QueryResult> readXML(String filename, ExperimentType experimentType) throws ResultProcessorException {
        Document dom;
        // Make an  instance of the DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
        	Collection<QueryResult> results = new ArrayList<QueryResult>();
            // use the factory to take an instance of the document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // parse using the builder to get the DOM mapping of the    
            // XML file
            dom = db.parse(filename);

            Element doc = dom.getDocumentElement();
            NodeList nl = doc.getElementsByTagName("query");
            for(int i = 0; i < nl.getLength(); i++) {
            	QueryResult queryResult = processQuery((Element) nl.item(i), experimentType);
            	results.add(queryResult);
            }
            return results;
        } catch (ParserConfigurationException pce) {
            System.out.println(pce.getMessage());
        } catch (SAXException se) {
            System.out.println(se.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        throw new ResultProcessorException("Unable to process file " + filename);
	}

	private QueryResult processQuery(Element doc, ExperimentType experimentType) {
		QueryResult queryResult = new QueryResult(experimentType);
		int queryNumber = getAttributeIntValue(doc, "nr");
		queryResult.setQueryNumber(queryNumber);
		String query = getTextValue(doc, "querystring");
		int startIndex = query.indexOf("http://www.conceptwiki.org/concept/", 0);
		int endIndex = query.indexOf("> skos:prefLabel");
		queryResult.setSeedValue(query.substring(startIndex, endIndex));
		double averageQueryExecutionTime = getDoubleValue(doc, "aqet");
		queryResult.setAverageExecutionTime(averageQueryExecutionTime);
		double averageNumberResults = getDoubleValue(doc, "avgresults");
		queryResult.setAverageNumberResults(averageNumberResults);
		int timeoutCount = getIntValue(doc, "timeoutcount");
		queryResult.setTimeoutCount(timeoutCount);
		return queryResult;
	}
	
	private int getAttributeIntValue(Element doc, String attr) {
		String value = getAttributeValue(doc, attr);
		return new Integer(value).intValue();
	}
	
	private String getAttributeValue(Element doc, String attr) {
		String value = null;
		NamedNodeMap attributes = doc.getAttributes();
		value = attributes.getNamedItem(attr).getNodeValue();
		return value;
	}
	
	private double getDoubleValue(Element doc, String tag) {
		String value = getTextValue(doc, tag);
		return new Double(value).doubleValue();
	}
	
	private int getIntValue(Element doc, String tag) {
		String value = getTextValue(doc, tag);
		return new Integer(value).intValue();
	}
	
	private String getTextValue(Element doc, String tag) {
	    String value = null;
	    NodeList nl;
	    nl = doc.getElementsByTagName(tag);
	    if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
	        value = nl.item(0).getFirstChild().getNodeValue();
	    }
	    return value;
	}
}
