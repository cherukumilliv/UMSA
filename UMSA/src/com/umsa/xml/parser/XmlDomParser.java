package com.umsa.xml.parser;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.xerces.parsers.DOMParser;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

public final class XmlDomParser extends DOMParser {

	/** This is the deafault constuctor for the class.This sets the 
	 * error handler. */
	public XmlDomParser() {
		xmlDoc = null;
	}

	/** This is the default constuctor for the class.This sets the 
	 * error handler and the Document. */

	public XmlDomParser(Document doc) {
		xmlDoc = doc;
	}
	/** This Constructor takes input String for the source */
	public XmlDomParser(String str) throws SAXException, IOException {
		try{
			inputSource = new InputSource(str);
			super.parse(inputSource);
			xmlDoc = super.getDocument();
		} catch (SAXException snre) {
			throw snre;
		} catch (IOException snse) {
			throw snse;
		}
	}
	/** This is the constructor, which is setting the error handler 
	 * and sets the paramter to true for validating the XML. */
	public XmlDomParser(InputSource is) {
		xmlDoc = null;
		inputSource = is;
	}
	/** This is an overloaded constructor, which is setting the error handler 
	 * and sets the validating and namespace parameters to the XML. */

	public XmlDomParser(
		StringBuffer xmlStream,
		boolean bNSAware,
		boolean bDTDValid,
		boolean bSchemaValid)
		throws java.io.IOException, SAXException {
		xmlDoc = null;
		try {
			setNameSpaceAware(bNSAware);
			setDTDValidation(bDTDValid);
			setSchemaValidation(bSchemaValid);
			StringReader strReader = new StringReader(xmlStream.toString());
			inputSource = new InputSource(strReader);
			super.parse(inputSource);
			xmlDoc = super.getDocument();
		} catch (SAXNotRecognizedException snre) {
			throw snre;
		} catch (SAXNotSupportedException snse) {
			throw snse;
		}
	}	/** This method will return the node reference to the
	 * first element of the XML document */
	public Node getStartElement(Document document)
		throws SAXException, java.io.IOException {
		if (document != null) {
			return document.getDocumentElement();
		} else {
			return null;
		}
	} //////////////////////////////////////////////////////////////////////////

	public Document createNewDocument(Node currentNode, String rootElementName)
		throws ParserConfigurationException, IOException, SAXException {
		Document newXMLDoc = null;

		if (currentNode != null) {
			try {
				DocumentBuilderFactory docFactory =
					DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				newXMLDoc = docBuilder.newDocument();
				Element elmnt = newXMLDoc.createElement(rootElementName);
				newXMLDoc.appendChild(elmnt);
				currentNode = newXMLDoc.importNode(currentNode, true);
				AppendAtEndOfDocument(newXMLDoc, currentNode);

			} catch (ParserConfigurationException pce) {
				throw pce;
			}
		}

		return newXMLDoc;
	}

	protected void AppendAtEndOfDocument(Document document, Node newNode)
		throws SAXException, java.io.IOException {
		try {
			Node startElement = getStartElement(document);
			//System.out.println(getName(startElement));
			startElement.appendChild(newNode);
		} catch (java.io.IOException ie) {
			System.out.println(
				"Caught IOException (AppendAtEnd) -  " + ie.getMessage());
			throw ie;
		} catch (SAXException se) {
			System.out.println(
				"Caught SAXException (AppendAtEnd) -  " + se.getMessage());
			throw se;
		} catch (org.w3c.dom.DOMException de) {
			System.out.println("caught DOMException de" + de.getMessage());
			System.out.println("importing Node");
			AppendAtEnd(document.importNode(newNode, true));
		}
	}






	/*
	 * 
	 */
	public void setValidation(
		boolean bNSAware,
		boolean bDTDValid,
		boolean bSchemaValid)
		throws SAXNotRecognizedException, SAXNotSupportedException {
		try {
			setNameSpaceAware(bNSAware);
			setDTDValidation(bDTDValid);
			setSchemaValidation(bSchemaValid);
		} catch (SAXNotRecognizedException snre) {
			throw snre;
		} catch (SAXNotSupportedException snse) {
			throw snse;
		}
	}

	/** This method sets the inputource */
	public void setInputSource(InputSource is) {
		inputSource = is;
	}

	/** This method sets the inputource */
	public void setInputSource(String str) {
		inputSource = new InputSource(str);
		;
	}

	/** This Method if set to true will make the parser aware
	 *  of the XML namespaces.*/
	public void setNameSpaceAware(boolean bNSAware)
		throws SAXNotRecognizedException, SAXNotSupportedException {
		try {
			// This line tells the XML processor to recognize the namespaces.
			setFeature("http://xml.org/sax/features/namespaces", bNSAware);
		} catch (SAXNotRecognizedException snre) {
			throw snre;
		} catch (SAXNotSupportedException snse) {
			throw snse;
		}
	}

	/** This method if set to true will let the parser know that 
	 * it has to look for DTD (if any) to validate the XML doc 
	 * against the DTD defined for it */
	public void setDTDValidation(boolean bDTDValid)
		throws SAXNotRecognizedException, SAXNotSupportedException {
		try {
			// This line tells the XML processor to validate the document.
			setFeature("http://xml.org/sax/features/validation", bDTDValid);
		} catch (SAXNotRecognizedException snre) {
			throw snre;
		} catch (SAXNotSupportedException snse) {
			throw snse;
		}
	}

	/** This method if set to true will let the parser know that
	 * it has to look for a schema (if any) to validate the XML doc
	 * against the schema defined for it */
	public void setSchemaValidation(boolean bSchemaValid)
		throws SAXNotRecognizedException, SAXNotSupportedException {
		try {
			// This line tells the XML processor to recognize the schema validation.
			setFeature(
				"http://apache.org/xml/features/validation/schema",
				bSchemaValid);
		} catch (SAXNotRecognizedException snre) {
			throw snre;
		} catch (SAXNotSupportedException snse) {
			throw snse;
		}
	}

	/**  This method will return a pointer to the document.*/

	public Document getDocument(InputSource source)
		throws SAXException, java.io.IOException {
		try {
			if (source == null) {
				super.parse(inputSource);
				xmlDoc = super.getDocument();
				return xmlDoc;
			} else {
				super.parse(source);
				xmlDoc = super.getDocument();
				return xmlDoc;
			}
		} catch (java.io.IOException ie) {
			throw ie;
		} catch (SAXException se) {
			throw se;
		}
	}

	/**
	 * Sets the document to the current parser.
	 */
	public void setDocument(Document doc) {
		xmlDoc = doc;
	}

	/** 
	 *  This method will return the document for the current parser.
	 */
	public Document getDocument() {
		try {
			if (xmlDoc == null) {
				return getDocument(null);
			} else {
				return xmlDoc;
			}
		} catch (java.io.IOException ie) {
			System.out.println(ie.getMessage());
			return null;
		} catch (SAXException se) {
			System.out.println(se.getMessage());
			return null;
		}
	}

	/** This method creates and sets the root element */
	public void createRootElement(String szRootName, String szValue)
		throws ParserConfigurationException {
		try {
			DocumentBuilderFactory docFactory =
				DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			xmlDoc = docBuilder.newDocument();
			Element elmnt = xmlDoc.createElement(szRootName);
			elmnt.appendChild(xmlDoc.createTextNode(szValue));
			xmlDoc.appendChild(elmnt);
		} catch (ParserConfigurationException pce) {
			throw pce;
		}
	}

	/** This method will return the node reference to the
	 * first element of the XML document */
	public Node getStartElement() throws SAXException, java.io.IOException {
		try {
			if (xmlDoc == null) {
				xmlDoc = getDocument(null);
			}
		} catch (java.io.IOException ie) {
			throw ie;
		} catch (SAXException se) {
			throw se;
		}
		if (xmlDoc != null) {
			return xmlDoc.getDocumentElement();
		} else {
			return null;
		}
	}

	//////////////////////////////////////////////////////////////////////////
	//			Node Level Methods.
	//////////////////////////////////////////////////////////////////////////

	/** Given a node this method will return the value of that node */
	public String getValue(Node node) {
		// This check is to make sure that this element is not an empty element.
		if (hasChildElement(node)) {
			Node textNode;
			textNode = node.getFirstChild();
			if (textNode.getNodeType() == Node.TEXT_NODE) {
				if (!((org.apache.xerces.dom.TextImpl) textNode)
					.isIgnorableWhitespace()) {
					return textNode.getNodeValue();
				}
			}
		}
		return null;
	}

	/** given a node and a String this method will update the value of the node */
	public void updateNodeValue(Node nTarget, String newValue) {
		if (hasChildElement(nTarget)) {
			Node textNode;
			textNode = nTarget.getFirstChild();
			if (textNode.getNodeType() == Node.TEXT_NODE) {
				textNode.setNodeValue(newValue);
			}
		}
	}
	
	public void updateChildTree(Node parentNode,Node newChildNode){
		Node currentChildNode = getFirstChildNode(parentNode);
		try{
			parentNode.replaceChild(newChildNode,currentChildNode);
		}catch(DOMException e){
			Node importedNode = xmlDoc.importNode(newChildNode,true);
			parentNode.replaceChild(importedNode,currentChildNode);
		}
	}

	/** given a node and a String this method will update the value of the node */
	public void updateNode(Node nTarget, Node newNode) {
		Node parentOfTarget = nTarget.getParentNode();
		parentOfTarget.removeChild(nTarget);
		try{
			AppendChildNodeTo(parentOfTarget,newNode);
		}catch(DOMException e){
			Node importedNode = xmlDoc.importNode(newNode,true);
			AppendChildNodeTo(parentOfTarget,importedNode);
		}
	}


	/** Given a node this method will return a true/false based on whether or not 
	 * this node has child nodes */
	public boolean hasChildElement(Node node) {
		if (node != null) {
			return node.hasChildNodes();
		}
		return false;
	}

	/** Given a node This method will return a true/false based on whether or not 
	 * this node has any attributes */
	public boolean hasAttributes(Node node) {
		if (node != null) {
			return node.hasAttributes();
		}
		return false;
	}

	/** Given a node this method will return the next sibling */
	public Node getNextNode(Node node) {
		if (node == null) {
			return null;
		}
		while ((node.getNextSibling() != null)
			&& (node.getNextSibling().getNodeType() == Node.TEXT_NODE)) {
			node = node.getNextSibling();
			if (node.getNextSibling() == null) {
				break;
			}
		}
		return node.getNextSibling();
	}

	/** Given a node this methos will return the Previous Sibling*/
	public Node getPreviousNode(Node node) {
		while (node.getNextSibling().getNodeType() == Node.TEXT_NODE) {
			node = node.getNextSibling();
		}
		return node.getNextSibling();
	}
	/** Given a node this method will return the name (the tag name) */
	public String getName(Node node) {
		if (node != null) {
			return node.getNodeName();
		} else {
			return null;
		}
	}

	/** Given a node returns the First Child Node */
	public Node getFirstChildNode(Node node) {
		Node FirstChild;
		FirstChild = node.getFirstChild();
		while ((FirstChild != null)
			&& (FirstChild.getNodeType() == Node.TEXT_NODE)) {
			if (((org.apache.xerces.dom.TextImpl) FirstChild)
				.isIgnorableWhitespace()) {
				FirstChild = FirstChild.getNextSibling();
			} else {
				if (FirstChild.getNodeType() == Node.TEXT_NODE) {
					String tmpval = getValue(FirstChild);
					if (tmpval == null) {
						FirstChild = FirstChild.getNextSibling();
					} else {
						if ((((getValue(FirstChild)).trim()).length()) == 0) {
							FirstChild = FirstChild.getNextSibling();
						}
					}
				} else {
					break;
				}
			}
		}
		return FirstChild;
	}

	/** This method will return the child node given, the zero based index(position in the order
	 * in which it is defined.) of the child node. */
	public Node getChildNodeAtPosition(Node parentNode, int position) {
		int i = 0;
		Node node;
		if (getNumberOfChildNodes(parentNode) > 0) {
			for (i = 0, node = parentNode.getFirstChild();
				i < getNumberOfChildNodes(parentNode);
				i++, node = node.getNextSibling()) {
				if (i == position) {
					return node;
				}
			}
			return null;
		} else {
			return null;
		}
	}

	/** This method will return the value of the child node, given the zero based index(position in the order
	 * in which it is defined.) of the child node. */
	public String getValueOfChildNodeAtPosition(
		Node parentNode,
		int position) {
		return getValue(getChildNodeAtPosition(parentNode, position));
	}

	/**
	 * 
	 */
	public String getValueofChildByName(Node parentNode, String szchildName) {
		NodeList lstChildNode = parentNode.getChildNodes();
		int position = 0;
		if ((lstChildNode == null) || (lstChildNode.getLength() == 0)) {
			return null;
		}
		while (position < lstChildNode.getLength()) {
			Node currentNode = lstChildNode.item(position);
			String szNameofCurrentNode = getName(currentNode);
			if (szNameofCurrentNode.equalsIgnoreCase(szchildName)) {
				return getValue(currentNode);
			}
			position++;
		}
		return null;
	}

	/** Given a node returns the Last Child Node */
	public Node getLastChildNode(Node node) {
		Node lastChild;
		lastChild = node.getLastChild();
		while (lastChild.getNodeType() == Node.TEXT_NODE) {
			if (((org.apache.xerces.dom.TextImpl) lastChild)
				.isIgnorableWhitespace()) {
				lastChild = lastChild.getPreviousSibling();
			}
		}
		return lastChild;
	}

	/** Given a node returns the Number of ChildNodes */
	public int getNumberOfChildNodes(Node node) {
		int i = 0;
		if (node == null) {
			return 0;
		}
		if (node.hasChildNodes()) {
			Node fcnode;
			fcnode = getFirstChildNode(node);
			i++;
			while (getNextNode(fcnode) != null) {
				i++;
				fcnode = getNextNode(fcnode);
			}
		}
		return i;
	}

	/** Given a node and the name of its child element this method will 
	 * return the value of the child node.*/
	public String getValue(Node node, String childnodename) {
		boolean bfound = false;
		Node fcnode;
		if (node.hasChildNodes()) {
			fcnode = node.getFirstChild();
			while (fcnode.getNextSibling() != null) {
				String tmpStr = fcnode.getNodeName();
				if (tmpStr.equalsIgnoreCase(childnodename)) {
					bfound = true;
					break;
				}
				fcnode = fcnode.getNextSibling();
			}

			// the following "if" is for the last node which was not processed.

			if ((fcnode != null) && !bfound) {
				if (fcnode.getNodeName().equalsIgnoreCase(childnodename)) {
					bfound = true;
				}
			}

			if (bfound) {
				return getValue(fcnode);
			}
		}
		return null;
	}

	/** Given an ID this method will return the reference of the node. */
	public Node getElementByID(String strID) {
		return xmlDoc.getElementById(strID);
	}

	/** Returns a NodeList of all the Elements with a given tag name in the order in
	 * which they are encountered in a preorder traversal of the Document tree.*/
	public NodeList getNodesByTagName(String nodeName) {
		return xmlDoc.getElementsByTagName(nodeName);
	}

	/** Returns a NodeList of all the Child Elements with a given tag name in the order in
	 * which they are encountered in a preorder traversal of the Document tree.*/
	public NodeList getChildNodes(String nodeName) {
		Node currentNode = getNodeByTagName(nodeName);
		return currentNode.getChildNodes();
	}

	/** Returns the value of the Node if the NodeList has only one Node 
	 * with a given tag name in the order in
	 * which they are encountered in a preorder traversal of the Document tree.
	 * Returns a null if the nodelist ahs more than one element */
	public String getValueByTagName(String nodeName) {
		if (nodeName == null) {
			return null;
		}
		NodeList tmplist = xmlDoc.getElementsByTagName(nodeName);
		if (tmplist.getLength() == 1) {
			return getValue(tmplist.item(0));
		}
		return null;
	}

	/** Returns the  Node if the NodeList has only one Node 
	 * with a given tag name in the order in
	 * which they are encountered in a preorder traversal of the Document tree.
	 * Returns a null if the nodelist ahs more than one element */
	public Node getNodeByTagName(String nodeName) {
		if (nodeName == null) {
			return null;
		}
		NodeList tmplist = xmlDoc.getElementsByTagName(nodeName);
		if (tmplist.getLength() == 1) {
			return tmplist.item(0);
		}
		return null;
	}

	/**
	 * Returns the ith element of the NodeList.
	 */
	public Node getElementofNodeList(NodeList nList, int i) {
		return nList.item(i);
	}

	/** Given a name value pair this method will append the node to the XML Doc */
	public void AppendAtEnd(String Name, String value)
		throws SAXException, java.io.IOException {
		try {
			Element elmnt = xmlDoc.createElement(Name);
			elmnt.appendChild(xmlDoc.createTextNode(value));
			getStartElement().appendChild(elmnt);
		} catch (java.io.IOException ie) {
			throw ie;
		} catch (SAXException se) {
			throw se;
		} catch (org.w3c.dom.DOMException de) {
			System.out.println("caught DOMException de" + de.getMessage());
			System.out.println("importing Node");
			Element elmnt = xmlDoc.createElement(Name);
			elmnt.appendChild(xmlDoc.createTextNode(value));
			getStartElement().appendChild(elmnt);

		}

	}

	/** Given a node this method will append the node to the existing XML Doc */
	public void AppendAtEnd(Node newNode)
		throws SAXException, java.io.IOException {
		try {
			getStartElement().appendChild(newNode);
		} catch (java.io.IOException ie) {
			System.out.println(
				"Caught IOException (AppendAtEnd) -  " + ie.getMessage());
			throw ie;
		} catch (SAXException se) {
			System.out.println(
				"Caught SAXException (AppendAtEnd) -  " + se.getMessage());
			throw se;
		} catch (org.w3c.dom.DOMException de) {
			System.out.println("caught DOMException de" + de.getMessage());
			System.out.println("importing Node");
			AppendAtEnd(xmlDoc.importNode(newNode, true));
		}
	}

	/**
	 * 
	 */
	public void AppendChildNodeTo(
		Node ntarget,
		String szName,
		String szValue) {
		Element elmnt = xmlDoc.createElement(szName);
		elmnt.appendChild(xmlDoc.createTextNode(szValue));
		ntarget.appendChild(elmnt);
	}

	/**
	 * 
	 */
	public Node AppendChildNodenReturnRef(
		Node ntarget,
		String szName,
		String szValue) {
		Element elmnt = xmlDoc.createElement(szName);
		elmnt.appendChild(xmlDoc.createTextNode(szValue));
		ntarget.appendChild(elmnt);
		return elmnt;
	}

	public void AppendChildNodeTo(
		Node ntarget,
		String szName,
		String szValue,
		String NameofAttribute,
		String ValueofAttribute) {
		Element elmnt = xmlDoc.createElement(szName);
		elmnt.appendChild(xmlDoc.createTextNode(szValue));
		elmnt.setAttribute(NameofAttribute, ValueofAttribute);
		ntarget.appendChild(elmnt);
	}

	public void AppendChildNodeTo(
		Node ntarget,
		String szName,
		String szValue,
		String NameofAttribute,
		String ValueofAttribute,
		String NameofAttribute1,
		String ValueofAttribute1) {
		Element elmnt = xmlDoc.createElement(szName);
		elmnt.appendChild(xmlDoc.createTextNode(szValue));
		elmnt.setAttribute(NameofAttribute, ValueofAttribute);
		elmnt.setAttribute(NameofAttribute1, ValueofAttribute1);
		ntarget.appendChild(elmnt);
	}

	/**
	 * 
	 */
	public void AppendChildNodeTo(Node ntarget, Node nChild) {
		try{
			ntarget.appendChild(nChild);
		}catch(org.w3c.dom.DOMException e){
			ntarget.appendChild(xmlDoc.importNode(nChild,true));
		}
	}

	/**
	 * 
	 */
	public Node createNode(String Name, String value) {
		Element elmnt = xmlDoc.createElement(Name);
		elmnt.appendChild(xmlDoc.createTextNode(value));
		return (Node) elmnt;
	}

	/** This method takes two parameters of Node type. The first Node parameter is 
	 * to indicate the position Before which the new node has to be inserted. The Second 
	 * node parameter will be the new node.*/
	public void InsertBefore(Node location, Node newNode) {
		xmlDoc.insertBefore(location, newNode);
	}

	/** This method takes two parameters of Node type. The first Node parameter is 
	 * to indicate the position Before which the new node has to be inserted. The Second 
	 * node parameter will be the new node.*/
	public void InsertBefore(Node location, String Name, String value)
		throws SAXException, java.io.IOException {
		Element elmnt;
		elmnt = xmlDoc.createElement(Name);
		elmnt.appendChild(xmlDoc.createTextNode(value));
		xmlDoc.insertBefore(location, elmnt);
	}

	/** This method takes two parameters of Node type. The first Node parameter is 
	 * to indicate the position after which the new node has to be inserted. The Second 
	 * node parameter will be the new node.*/
	public void InsertAfter(Node location, Node newNode) {
		if (location.getNextSibling() != null) {
			xmlDoc.insertBefore(location.getNextSibling(), newNode);
		} else {
			location.getParentNode().appendChild(newNode);
		}
	}

	/** This method takes two parameters of Node type. The first Node parameter is 
	 * to indicate the position after which the new node has to be inserted. The Second 
	 * node parameter will be the new node.*/
	public void InsertAfter(Node location, String Name, String value)
		throws SAXException, java.io.IOException {
		Element elmnt;
		elmnt = xmlDoc.createElement(Name);
		elmnt.appendChild(xmlDoc.createTextNode(value));
		InsertAfter(location, elmnt);
	}

	//////////////////////////////////////////////////////////////////////////////////
	// attribute related methods.
	//////////////////////////////////////////////////////////////////////////////////

	/** Given a Node, this method will return the Attribute 
	 * Node Map */
	public NamedNodeMap getAttributeNodeMap(Node ntarget) {
		return ntarget.getAttributes();
	}

	/** Given a Attribute Node Map and name of the attribute 
	 * this method will return the value of that attribute.*/
	public String getAttribute(NamedNodeMap nmap, String strAttrName) {
		NamedNodeMap nodeMap = nmap;
		Node tmp;
		while (nodeMap.getLength() > 0) {
			tmp = nodeMap.getNamedItem(strAttrName);
			if (getName(tmp).equalsIgnoreCase(strAttrName)) {
				return getValue(tmp);
			}
		}
		return null;
	}

	/** Given a Node and name of the attribute will return the value of that attribute.*/
	public String getAttribute(Node ntarget, String strAttrName) {
		NamedNodeMap nodeMap = ntarget.getAttributes();
		Node tmp;
		if (nodeMap.getLength() > 0) {
			tmp = nodeMap.getNamedItem(strAttrName);
			if (tmp != null && getName(tmp).equalsIgnoreCase(strAttrName)) {
				return getValue(tmp);
			}
		}
		return null;
	}
	/** Given a node,the name of its child element,name of child element attribute
	 *  this method will return the attribute value of the child node.*/
	public String getAttribute(
		Node node,
		String childNodeName,
		String strAttrName) {
		boolean bfound = false;
		Node fcnode;
		if (node.hasChildNodes()) {
			fcnode = node.getFirstChild();
			while (fcnode.getNextSibling() != null) {
				String tmpStr = fcnode.getNodeName();
				if (tmpStr.equalsIgnoreCase(childNodeName)) {
					bfound = true;
					break;
				}
				fcnode = fcnode.getNextSibling();
			}
			if (fcnode != null) {
				if (fcnode.getNodeName().equalsIgnoreCase(childNodeName)) {
					bfound = true;
				}
			}
			if (bfound) {
				return getAttribute(fcnode, strAttrName);
			}
		}
		return null;
	}

	/** This method takes a Zero based index of an attribute of a node
	 * and returns the value of that attribute.
	 * */
	public String getAttributeAtIndex(int index, Node ntarget) {
		NamedNodeMap nodeMap = ntarget.getAttributes();
		Node tmp;
		if (index < nodeMap.getLength()) {
			tmp = nodeMap.item(index);
			return getValue(tmp);
		}
		return null;
	}

	/** This method returns the no. of attributes of a given a node*/
	public int getAttributesLength(Node ntarget) {
		NamedNodeMap nodeMap = ntarget.getAttributes();
		return nodeMap.getLength();
	}

	/** This Method adds an attribute given the name,value pair for 
	 * the attribute and the node.
	 */
	public void setAttribute(
		Node ntarget,
		String NameofAttribute,
		String ValueofAttribute) {
		Element temp = (Element) ntarget;
		temp.setAttribute(NameofAttribute, ValueofAttribute);
	}

	/** This Method tells whether a node is Element Type */
	public boolean isNodeTypeElement(Node ntarget) {
		return (xmlDoc.getNodeType() == xmlDoc.ELEMENT_NODE);
	}

	/** This Method tells whether a node is Text Type */
	public boolean isNodeTypeText(Node ntarget) {
		return (ntarget.getNodeType() == Node.TEXT_NODE);
	}

	/** This Method tells whether the current node is end node */
	public boolean isNodeEndNode(Node ntarget) {
		if (isNodeTypeText(ntarget) || ntarget == null) {
			return false;
		}
		Node firstChild = ntarget.getFirstChild();
		return isNodeTypeText(firstChild);

	}

	public String toString() {
		try {
			XMLSerializer Serializer;
			OutputFormat formatter = new OutputFormat();
			StringWriter serializedString = new StringWriter();
			Serializer = new XMLSerializer(serializedString, formatter);
			formatter.setPreserveSpace(true);
			Serializer.serialize(xmlDoc);
			return serializedString.getBuffer().toString();
		} catch (java.io.IOException ie) {
			System.out.println(
				"Caught IOException while parsing the XML to String- "
					+ ie.getMessage());
		}
		return null;
	}


	/** This variable holds the reference to the document */
	private Document xmlDoc;

	/** This variable holds the Input Source */
	private InputSource inputSource;
}