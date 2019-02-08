package repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory; 
import org.w3c.dom.Document; 
import org.w3c.dom.Element; 
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import model.User;
import model.Users;

/**
 * The Class RESTClient handles the communication with the API.
 */
public class RESTClient {
	
	/** The client. */
	private Client client;
	
	// URL (parts) as static strings for ease of use and more readable code.
	/** The  URL used to Search for users in userdblab2. */
	private static String REST_SERVICE_URL = "http://localhost:8081/RESTDbLab2Part2/rest/UserService/users"; 
	
	
	/** The start part of the get person details url. */
//	private static String urlPersonDetailsStart = "https://api.themoviedb.org/3/person/"; 
	
	/** The ending part of the search details url. */
//	private static String searchDetailsUrlEnd = "?api_key=" + apiKey;

	
	/**
	 * Instantiates a new API client.
	 */
	// constructor
	public RESTClient() {
		client = ClientBuilder.newClient();
	}
	
	
	/**
	 * Query API for users.
	 *
	 * @param query the query
	 * @return the string
	 */
	public String queryGetUsers() {
		RESTClient rc = new RESTClient();
		GenericType<String> string = new GenericType<String>() {};
		String s = rc.client
				.target(REST_SERVICE_URL)
				.request(MediaType.APPLICATION_XML)
				.get(string); // get the XML representation
		//print the XML representation
		System.out.println(s); // Kept for reference only
		return s;
	}
	
	public String addUser(User user) { //TODO ändra privacy
		Form form = new Form();
	    form.param("id", Integer.toString(user.getId()));
	    form.param("name", user.getName());
	    form.param("profession", user.getProfession());
	
	    String callResult = client
	       .target(REST_SERVICE_URL)
	       .request(MediaType.APPLICATION_XML)
	       .post(Entity.entity(form,
	          MediaType.APPLICATION_FORM_URLENCODED_TYPE),
	          String.class);
	    System.out.println("Add user request returned: \n" + callResult);
	return callResult;
	}
	
	public String updateUser(User user) { //TODO ändra privacy
		Form form = new Form();
	    form.param("id", Integer.toString(user.getId()));
	    form.param("name", user.getName());
	    form.param("profession", user.getProfession());
	
	    String callResult = client
	       .target(REST_SERVICE_URL)
	       .request(MediaType.APPLICATION_XML)
	       .put(Entity.entity(form,
	          MediaType.APPLICATION_FORM_URLENCODED_TYPE),
	          String.class);
	    String returnMessage = "Update user request returned: \n" + callResult;
	    System.out.println(returnMessage);	    
	return returnMessage;
	}
	
	
	public String deleteUser(User user) { //TODO ändra privacy
	 String callResult = client
	         .target(REST_SERVICE_URL)
	         .path("/{userid}")
	         .resolveTemplate("userid", Integer.toString(user.getId()))
	         .request(MediaType.APPLICATION_XML)
	         .delete(String.class);
	return callResult;
	}
	
	
	 //public static void jaxbXmlStringToObject(String xmlString) { //TODO privacy
	public Users jaxbXmlStringToObjectOLD(String xmlString) { //TODO privacy
		Users users = null;
	//	List<User> usersList =  null;
		//Create Unmarshaller
	try {
		JAXBContext jaxbContext = JAXBContext.newInstance(Users.class );
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		 
		// methods to unmarshal 
		//Users users = (Users) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
		users = (Users) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
	//	usersList = users.getUsers();
	} catch (JAXBException e) {
		System.err.println("A JAXB exception occured: "+ e.getMessage());
		e.printStackTrace(); //TODO TEMP
	}
	//return usersList;
	return users;
	 }
	 
	//public List<User> jaxbXmlStringToObject(String xmlString) { 
	public ArrayList<User> jaxbXmlStringToObject(String xmlString) { 
		
		//List<User> usersList =  null;
			ArrayList<User> usersList =  new ArrayList<User>();
			/* File file = new File("c:/temp/users.xml");
	      //  if (!file.exists()) {
	        	 saveXmlString(xmlString);	
	        // }
		*/
		//	File inputFile = new File("c:/temp/users.xml"); // läs fil  
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); 
			try { 
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); 
			//Document doc = dBuilder.parse(inputFile); 
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
	
	
	private void saveXmlString(String xmlString){
	      try {
	    	 File file = new File("c:/temp/users.xml");
	         FileOutputStream fos;

	         fos = new FileOutputStream(file);

	         ObjectOutputStream oos = new ObjectOutputStream(fos);		
	         oos.writeObject(xmlString);
	         oos.close();
	      } catch (FileNotFoundException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	   }
			
//TEMP
	private void saveUserList(List<User> userList){
	      try {
	       //  File file = new File("c:/temp/Users3.dat");
	    	 File file = new File("c:/temp/Users.dat");
	         FileOutputStream fos;

	         fos = new FileOutputStream(file);

	         ObjectOutputStream oos = new ObjectOutputStream(fos);		
	         oos.writeObject(userList);
	         oos.close();
	      } catch (FileNotFoundException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	   }
	
	public List<User> getAllUsers(){
	      List<User> userList = null;
	      try {
	         //File file = new File("c:/temp/Users3.dat");
	         File file = new File("c:/temp/Users.dat");
	         if (!file.exists()) {
	            User user = new User(1, "Mahesh", "Teacher");
	            userList = new ArrayList<User>();
	            userList.add(user);
	            saveUserList(userList);		
	         }
	         else{
	            FileInputStream fis = new FileInputStream(file);
	            ObjectInputStream ois = new ObjectInputStream(fis);
	            userList = (List<User>) ois.readObject();
	            ois.close();
	         }
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      }		
	      return userList;
	   }
	
	//TEMP HIT
	
}
