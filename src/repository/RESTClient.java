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
	
	// URL as static string for ease of use and more readable code.
	/** The  URL used to Search for users in userdblab2. */
	private static String REST_SERVICE_URL = "http://localhost:8081/RESTDbLab2Part2/rest/UserService/users"; 
	
	
	/**
	 * Instantiates a new API client.
	 */
	// constructor
	public RESTClient() {
		client = ClientBuilder.newClient();
	}
	
	
	/**
	 * Query API for all users.
	 *
	 * @param query the query
	 * @return the string
	 */
	protected String getAllUsers() {
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
	
	/**
	 * Query API for selected user.
	 *
	 * @param query the query
	 * @return the string
	 */

	protected String getSelectedUser(int userId) {
		RESTClient rc = new RESTClient();
		GenericType<String> string = new GenericType<String>() {};
		String s = rc.client
				.target(REST_SERVICE_URL)
			    .path("/{userid}")
		        .resolveTemplate("userid", Integer.toString(userId))
				.request(MediaType.APPLICATION_XML)
				.get(string); // get the XML representation
		//print the XML representation
		System.out.println(s); // Kept for reference only
		return s;
	}
	
	/**
	 * Call API to add user.
	 *
	 * @param user the User
	 * @return the string callresult
	 */
	protected String addUser(User user) {
		Form form = new Form();
	    form.param("id", Integer.toString(user.getId()));
	    form.param("name", user.getName());
	    form.param("profession", user.getProfession());
	    RESTClient rc = new RESTClient();
	    String callResult = rc.client
	       .target(REST_SERVICE_URL)
	       .request(MediaType.APPLICATION_XML)
	       .post(Entity.entity(form,
	          MediaType.APPLICATION_FORM_URLENCODED_TYPE),
	          String.class);
	    String returnMessage = "Add user request returned: \n" + callResult;
		
	    System.out.println(returnMessage);
	    return returnMessage;
	}
	
	/**
	 * Call API to update user.
	 *
	 * @param user the User
	 * @return the string
	 */
	protected String updateUser(User user) {
		Form form = new Form();
	    form.param("id", Integer.toString(user.getId()));
	    form.param("name", user.getName());
	    form.param("profession", user.getProfession());
	    RESTClient rc = new RESTClient();
	    String callResult = rc.client
	       .target(REST_SERVICE_URL)
	       .request(MediaType.APPLICATION_XML)
	       .put(Entity.entity(form,
	          MediaType.APPLICATION_FORM_URLENCODED_TYPE),
	          String.class);
	    String returnMessage = "Update user request returned: \n" + callResult;
	    System.out.println(returnMessage);	    
	return returnMessage;
	}
	
	/**
	 * Call API to delete user.
	 *
	 * @param user the User
	 * @return the string returnMessage
	 */
	protected String deleteUser(User user) {
	 RESTClient rc = new RESTClient();
	 String callResult = rc.client
	         .target(REST_SERVICE_URL)
	         .path("/{userid}")
	         .resolveTemplate("userid", Integer.toString(user.getId()))
	         .request(MediaType.APPLICATION_XML)
	         .delete(String.class);
	 String returnMessage = "Delete user request returned: \n" + callResult;
	 System.out.println(returnMessage);	
	return returnMessage;
	}
	

}
