package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.User;
import model.Users;
import repository.RESTClient;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class ShowUsersController {
	
	  @FXML
	    private Button showUser;

	    @FXML
	    private Button addUserViewBtn;

	    @FXML
	    private Button updateUserViewBtn;

	    @FXML
	    private Button deleteUserViewBtn;

	    @FXML
	    private Button homeBtn;
	    
	    @FXML
	    private TableView<User> userTable;

	    @FXML
	    private TableColumn<User, Integer> idCol;

	    @FXML
	    private TableColumn<User, String> nameCol;

	    @FXML
	    private TableColumn<User, String> professionCol;

	    @FXML
	    private TextField userIdTxt;

	    @FXML
	    private TextField userNameTxt;

	    @FXML
	    private TextField userProfessionTxt;
	    
	    @FXML
	    private TextArea messageTextArea;
	    
	    ArrayList<User> usersList;
	    User u;
	    UserRepository userRepo = new UserRepository();
	
	    
	    /**
	     * Initializing the controller class.
	     */
	    //This method is automatically called after the fxml file has been loaded.
	    @FXML
	    private void initialize () {
	    	System.out.println("ShowUsersController initiated!");
	    	//addData();
	    	
	    	  	
	    	// mouseclick eventhandler
	    	userTable.setOnMouseClicked(this::TableClicked);
	    	// Match column with property
	    	idCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
	    	nameCol.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
	    	professionCol.setCellValueFactory(new PropertyValueFactory<User, String>("profession"));
	    	updateTable();
	    }
	    /*
	    //TEST only
	    private void addData() {
	    	RESTClient rc = new RESTClient();
	    	Users users = new Users();
	    	//List<User> usersList = null;
	    	String xmlString = rc.queryGetUsers();
	    	//RESTClient.jaxbXmlStringToObject(xmlString);
	    	usersList = new ArrayList<User>();
	    //	usersList = (ArrayList<User>) rc.jaxbXmlStringToObject(xmlString);
	    	usersList = rc.jaxbXmlStringToObject(xmlString);
	   // 	users = rc.jaxbXmlStringToObject(xmlString); //TODO OLD
	    	//System.out.println(users.getUsers());
	    	//System.out.println("First user: " + usersList.get(1).getName());
	    	//usersList = new ArrayList<User>();
	    	//usersList = (ArrayList<User>) users.getUsers();
	    	System.out.println("first record in usersList: " + usersList.get(0)); //TODO TEMP
	    	ObservableList<User> list = FXCollections.observableArrayList(usersList);
			userTable.setItems((ObservableList<User>) list);
	    	/*
	    	  //For test only from here
	    	usersList = new ArrayList<User>();
	    	User user1 = new User(1, "Anna", "Teacher");
	    	usersList.add(user1);
	    	User user2 = new User(2, "Martin", "Author");
	    	usersList.add(user2);
	    	//until here
	    
		//	updateTable();
	    }
	    */
	    
	    /**
	     * Table clicked.
	     *
	     * @param event the event
	     */
	    @FXML
	    private void TableClicked(MouseEvent event) {
	       u = userTable.getSelectionModel().getSelectedItem();
	       userIdTxt.setText(String.valueOf(u.getId())); // Convert to String.
	       userNameTxt.setText(u.getName());
	       userProfessionTxt.setText(u.getProfession());
	    }
	    

	    @FXML
	    void openStartView(ActionEvent event) {
	    	//System.out.println("Start view should open");
	    	ViewController.activate("StartView");
	    }

	    @FXML
	    void selectAddUserView(ActionEvent event) {
	    	
	    	//mer riktigt härifrån
	    	
	    	String message = null;
            //Textfield cannot be empty
            if(!(userNameTxt.getText().length() > 0)) {
            	message = "Enter name before trying to add a user.";
            	messageTextArea.setText(message);
                return;
            }
            if(!(userProfessionTxt.getText().length() > 0)) {
            	message = "Enter profession before trying to add a user. \nIf no profession, enter None.";
            	messageTextArea.setText(message);
                return;
            }
            if(!(userIdTxt.getText().length() > 0)) {
            	message = "Enter id before trying to add a user.";
            	messageTextArea.setText(message);
                return;
            }
            
            //New user instance			
			User u1 = new User();
			u1.setId(Integer.parseInt(userIdTxt.getText()));
			u1.setName(userNameTxt.getText());
			u1.setProfession(userProfessionTxt.getText());
           // userRepo.add(u1); //TODO
			RESTClient rc = new RESTClient(); //TODO temp for test
			message = rc.addUser(u1); //TODO temp for test
            messageTextArea.setText(message); //TODO
            //update table
            updateTable();

	     //   }
	    	
	    	//till hit
          //Test härifrån
	    /*
	    	int id = Integer.parseInt(userIdTxt.getText());
	    	String name = userNameTxt.getText();
	    	String profession = userProfessionTxt.getText();
	    	User u1 = new User(id, name, profession);
	    	userRepo.add(u1);
	    	resetTextFields();
	    	updateTable();
	    	messageTextArea.setText("User added: " + "TODO add user info");
	    	*/
	    	//hit
	    }
	    
	    private void resetTextFields() {
	    	userIdTxt.setText("");
	    	userNameTxt.setText("");
	    	userProfessionTxt.setText("");
	    	messageTextArea.setText("");
	    }

	    @FXML
	    void selectDeleteUserView(ActionEvent event) {
	    	User u1 = new User();
	    	String message = null;
	    	if(!(userIdTxt.getText().length() > 0)) {
            	message = "Enter id of user to delete.";
            	messageTextArea.setText(message);
                return;
            }
	    	u1.setId(Integer.parseInt(userIdTxt.getText()));
			u1.setName(userNameTxt.getText());
			u1.setProfession(userProfessionTxt.getText());
           // userRepo.delete(u1); //TODO
			RESTClient rc = new RESTClient(); //TODO temp for test
			message = rc.deleteUser(u1); //TODO temp for test
            messageTextArea.setText(message); //TODO
            //update table
            updateTable();
	    }

	    @FXML
	    void selectUpdateUserView(ActionEvent event) {
	    	User u1 = new User();
	    	String message = null;
	    	if(!(userNameTxt.getText().length() > 0)) {
            	message = "Enter the name of the user to update.";
            	messageTextArea.setText(message);
                return;
            }
            if(!(userProfessionTxt.getText().length() > 0)) {
            	message = "Enter the profession of the user to update. \nIf no profession, enter None.";
            	messageTextArea.setText(message);
                return;
            }
            if(!(userIdTxt.getText().length() > 0)) {
            	message = "Enter id of user to update.";
            	messageTextArea.setText(message);
                return;
            }
	    	u1.setId(Integer.parseInt(userIdTxt.getText()));
			u1.setName(userNameTxt.getText());
			u1.setProfession(userProfessionTxt.getText());
	    	// userRepo.delete(u1); //TODO
			RESTClient rc = new RESTClient(); //TODO temp for test
			message = rc.updateUser(u1); //TODO temp for test
            messageTextArea.setText(message); //TODO
	    	/*
	    	for (User user : usersList) {
	    		if (user.getId() == id) {
	    			user.setName(name);
	    			user.setProfession(profession);
	    			
	    		}
	    		
	    	//	usersList = getUsersList();
	    	}*/
	    	updateTable();
	    }

	    @FXML
	    void showSelectedUser(ActionEvent event) {

	    }
	
	 /**
	  * Update table.
	  */
	 // Updating table with result from Db search
		private void updateTable() {
			RESTClient rc = new RESTClient();
			String xmlString = rc.queryGetUsers();
	    	usersList = new ArrayList<User>();
	    	usersList = rc.jaxbXmlStringToObject(xmlString);
	    	System.out.println("first record in usersList: " + usersList.get(0)); //TODO TEMP
		//	rc.queryGetUsers();
	    	//jH = new JsonHandler();
		//	resultingMovies = jH.createPopularMoviesArrayFromJsonString();
			ObservableList<User> list = FXCollections.observableArrayList(usersList);
			userTable.setItems((ObservableList<User>) list);
		}
}
