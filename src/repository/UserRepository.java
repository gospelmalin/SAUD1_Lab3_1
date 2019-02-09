package repository;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import model.User;

public class UserRepository {
	
	public String getAllUsers() {
		RESTClient rc = new RESTClient();
		String xmlResult = rc.getAllUsers();
		return xmlResult;	
	}

	public String add(User u1) {
		RESTClient rc = new RESTClient();
		String message = rc.addUser(u1);
		return message;	
	}
	
	public String update(User u1) {
		RESTClient rc = new RESTClient();
		String message = rc.updateUser(u1);
		return message;	
	}
	
	public String delete(User u1) {
		RESTClient rc = new RESTClient();
		String message = rc.deleteUser(u1);
		return message;	
	}
	
	
	
public ArrayList<User> jaxbXmlStringToObject(String xmlString) { 
	
			ArrayList<User> usersList =  new ArrayList<User>();
	
		//	File inputFile = new File("c:/temp/users.xml"); // läs fil  
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); 
			try { 
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); 
			Document doc = dBuilder.parse(new InputSource(new StringReader(xmlString)));
			NodeList nUsers = doc.getElementsByTagName("user"); // extrahera en lista av element ur taggstrukturen 
			System.out.println("length nUsers: " + nUsers.getLength());
			for (int temp = 0; temp < nUsers.getLength(); temp++) { // loopa igenom elementen 
				Element element = (Element)nUsers.item(temp); 
				int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
				String name = element.getElementsByTagName("name").item(0).getTextContent(); 
				String profession = element.getElementsByTagName("profession").item(0).getTextContent(); 
				User user = new User(id, name, profession); // skapa ett objekt 
				System.out.println("Skriver ut en user: " + user); // anropa objektets toString-metod och skriv ut 
				usersList.add(user);
			} 
			} catch (Exception e) { 
				e.printStackTrace(); 
				} 
			System.out.println("Färdig!"); 
			return usersList;		
	}

}
