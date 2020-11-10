package com.concordia.soen.hci.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.concordia.soen.hci.pojo.User;

public class UserDAO 
{
	String filePath = "src//main//java//com//concordia//soen//hci//dao//db.xml";
    File xmlFile = new File(filePath);
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder;
    
    public boolean createUser(User user) throws SAXException, IOException
    {
    	try 
    	{
    		
	        boolean newUser = isNewUser(user.getUserName());

    		if (newUser==true) {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
	        doc.getDocumentElement().normalize();
	        Element root = doc.getDocumentElement();
	        
	        Element new_user = doc.createElement("user");
	        Element user_name = doc.createElement("username");
	        user_name.appendChild(doc.createTextNode(user.getUserName()));
	        Element password = doc.createElement("password");
	        password.appendChild(doc.createTextNode(user.getPassword()));
	        Element usertype = doc.createElement("usertype");
	        usertype.appendChild(doc.createTextNode(user.getUserType()));
	        Element freqoptions = doc.createElement("freqoptions");
	        
	        
	        
	        new_user.appendChild(user_name);
	        new_user.appendChild(password);
	        new_user.appendChild(usertype);
	        new_user.appendChild(freqoptions);
	        
	        root.appendChild(new_user);
	        
	        doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src//main//java//com//concordia//soen//hci//dao//db.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
            System.out.println("XML file updated successfully");   
            return true;
    		}
	        
		} 
    	catch (ParserConfigurationException e) 
    	{
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return false;
    	
    }
    
    public boolean isNewUser(String username) throws ParserConfigurationException, SAXException, IOException {
    	
		System.out.println("checking for new user");
    	dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();
        Element root = doc.getDocumentElement();
        NodeList users = doc.getElementsByTagName("user");
        Element this_user = null;
        for(int i=0; i<users.getLength();i++)
        {
        	this_user = (Element)users.item(i);
        	if(this_user.getElementsByTagName("username").item(0).getFirstChild().getNodeValue().equals(username))
        	{
        		System.out.println("Match found for "+username);
        		return false;
        	}
        }
    	
    	return true;
    }
    
    public boolean check_credentials(String uname,String password) throws SAXException, IOException
    {
    	try {
    		System.out.println(uname);
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
	        doc.getDocumentElement().normalize();
	        Element root = doc.getDocumentElement();
	        NodeList users = doc.getElementsByTagName("user");
	        Element this_user = null;
	        for(int i=0; i<users.getLength();i++)
	        {
	        	this_user = (Element)users.item(i);
	        	System.out.println(this_user.getElementsByTagName("username").item(0).getFirstChild().getNodeValue());
	        	if(this_user.getElementsByTagName("username").item(0).getFirstChild().getNodeValue().equals(uname))
	        	{
	        	String this_pwd = this_user.getElementsByTagName("password").item(0).getFirstChild().getNodeValue();
	        	if(this_pwd.equals(password))
	        		return true;
	        	}
	        }
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return false;
    }
    
    public String find_user(String uname) throws SAXException, IOException
    {
    	String usertype="";
    	try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
	        doc.getDocumentElement().normalize();
	        Element root = doc.getDocumentElement();
	        NodeList users = doc.getElementsByTagName("user");
	        Element this_user = null;
	        for(int i=0; i<users.getLength();i++)
	        {
	        	this_user = (Element)users.item(i);
	        	if(this_user.getElementsByTagName("username").item(0).getFirstChild().getNodeValue().equals(uname))
	        	{
	        		usertype = this_user.getElementsByTagName("usertype").item(0).getFirstChild().getNodeValue();
	        	}
	        }
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return usertype;
    }
    
    public boolean editFrequentOptions(String username, String option)
    {
    	try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
	        doc.getDocumentElement().normalize();
	        Element root = doc.getDocumentElement();
	        NodeList users = doc.getElementsByTagName("user");
	        Element this_user = null;
	        for(int i=0; i<users.getLength();i++)
	        {
	        	this_user = (Element)users.item(i);
	        	if(this_user.getElementsByTagName("username").item(0).getFirstChild().getNodeValue().equals(username))
	        	{
	        		Node freq_options = this_user.getElementsByTagName("freqoptions").item(0);
	        		Element curr_option = doc.createElement("option");
	        		curr_option.appendChild(doc.createTextNode(option));
	        		freq_options.appendChild(curr_option);
	        		break;
	        	}
	        }
	        
	        doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src//main//java//com//concordia//soen//hci//dao//db.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
            System.out.println("XML file updated successfully");
	        
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	
    	
    	return true;
    }
    
    public ArrayList<String> getFrequentOptions(String username) throws ParserConfigurationException, SAXException, IOException
    {
    	ArrayList<String> arr = new ArrayList<String>();
    	//arr.add("Quick_option1");
		//arr.add("Quick_option2");
		//arr.add("Quick_option3");
    	dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();
        Element root = doc.getDocumentElement();
        NodeList users = doc.getElementsByTagName("user");
        for(int i=0; i<users.getLength();i++)
        {
        	Element this_user = (Element)users.item(i);
        	System.out.println(this_user.getElementsByTagName("username").item(0).getFirstChild().getNodeValue());
        	if(this_user.getElementsByTagName("username").item(0).getFirstChild().getNodeValue().equals(username))
        	{
        		NodeList options = this_user.getElementsByTagName("option");
        		if(options.getLength()>0)
        		{
        		System.out.println(options.getLength());
        		int index=0;
        		
        		for(int j=options.getLength()-1;j>=0;j--)
        		{
        			Node option = options.item(j);
        			System.out.println(option.getFirstChild().getNodeValue());
        			String op = option.getFirstChild().getNodeValue();
        			if((!arr.contains(op)) && arr.size()<3)
        			{
        				arr.add(index, op);
        				index=index+1;
        			}
        		}
        		
        		}
        		
        	}
        }
        while(arr.size()<3)
        {
        	arr.add("quick_option");
        }
        System.out.println(arr);
        return arr;
    }
    
    public static void main(String args[]) throws SAXException, IOException, ParserConfigurationException
    {
    	//User user = new User();
    	
    	//user.setUserName("divya1");
    	//user.setPassword("hello1");
    	//user.setUserType("novice1");
    	
    	UserDAO dao = new UserDAO();
    	
    	//dao.createUser(user);
    	//dao.editFrequentOptions("admin", "Compile");
    	//dao.editFrequentOptions("admin", "ConfigurationInfo");
    	//dao.editFrequentOptions("admin", "SuppressWarnings");
    	dao.getFrequentOptions("priya");
    }

}
