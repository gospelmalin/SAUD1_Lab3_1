package repository;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import model.User;

/**
 * The Class UserRepository separates the Controller from the RESTClient and handles the
 * parsing of the xmlString the client receives from the server.
 */
public class UserRepository {
	
	/**
	 * Get all users call to the client.
	 *
	 * @return the array list of users
	 */
	public ArrayList<User> getAllUsers() {
		RESTClient rc = new RESTClient();
		String xmlString = rc.getAllUsers();
		ArrayList<User> usersList =  new ArrayList<User>();
		usersList = jaxbXmlStringToObject(xmlString);	
		return usersList;
	}

	/**
	 * add user call to the client.
	 *
	 * @return string message
	 */
	public String add(User u1) {
		RESTClient rc = new RESTClient();
		String message = rc.addUser(u1);
		return message;	
	}
	
	/**
	 * Update user call to the client.
	 *
	 * @return string message
	 */
	public String update(User u1) {
		RESTClient rc = new RESTClient();
		String message = rc.updateUser(u1);
		return message;	
	}
	
	/**
	 * Delete user call to the client.
	 *
	 * @return string message
	 */
	public String delete(User u1) {
		RESTClient rc = new RESTClient();
		String message = rc.deleteUser(u1);
		return message;	
	}
	
	/**
	 * Creating an arraylist of user objects from the xml string.
	 *
	 * @param xmlString
	 * @return the array list of users
	 */
	private ArrayList<User> jaxbXmlStringToObject(String xmlString) { 
		ArrayList<User> usersList =  new ArrayList<User>();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); 
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); 
			Document doc = dBuilder.parse(new InputSource(new StringReader(xmlString)));
			NodeList nUsers = doc.getElementsByTagName("user"); // extract a list of element from the tag structure 
			// System.out.println("length nUsers: " + nUsers.getLength()); // Printing lenght of nodelist
			for (int temp = 0; temp < nUsers.getLength(); temp++) { // loop through the elements 
				Element element = (Element)nUsers.item(temp); 
				int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
				String name = element.getElementsByTagName("name").item(0).getTextContent(); 
				String profession = element.getElementsByTagName("profession").item(0).getTextContent(); 
				User user = new User(id, name, profession); // Create a User object 
				// System.out.println("Printing a user: " + user); // call the User object's toString-method and print 
				usersList.add(user);
			}
		} catch (NumberFormatException e1) {
			System.err.println("A number format exception occured: " + e1.getMessage());
		} catch (DOMException e2) {
			System.err.println("Parsing problem. A DOM exception occured: " + e2.getMessage());
		} catch (ParserConfigurationException e3) {
			System.err.println("Parsing problem. A parser configuration exception occured: " + e3.getMessage());
		} catch (SAXException e4) {
			System.err.println("Parsing problem. A SAX exception occured: " + e4.getMessage());
		} catch (IOException e5) {
			System.err.println("A IO exception occured: " + e5.getMessage());
		} 
		System.out.println("Done!"); 
		return usersList;		
	}

}
