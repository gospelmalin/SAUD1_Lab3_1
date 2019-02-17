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
import repository.UserRepository;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;


/**
 * The Class ShowUsersController handles what is related to the Show Users view.
 */
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
  	
	    	// mouseclick eventhandler
	    	userTable.setOnMouseClicked(this::TableClicked);
	    	// Match column with property
	    	idCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
	    	nameCol.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
	    	professionCol.setCellValueFactory(new PropertyValueFactory<User, String>("profession"));
	    	updateTable();
	    }

	    
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
	    	String message = null;
            //Text fields cannot be empty
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
            boolean isInteger = isInteger(userIdTxt.getText());
        	if (!isInteger) {
        		message = "Id must be an integer.";
            	messageTextArea.setText(message);
                return;
        	}
        	boolean userExist = checkUserExistance(Integer.parseInt(userIdTxt.getText()));
			if (userExist) {
				message = "The user with that id already exists.\n";
				messageTextArea.setText(message);
                return;
			}
			// the expression should allow any kind of letter from any language as well as those special characters common in names.
			// Name should not start with .
			if (!userNameTxt.getText().matches("^[\\p{L} .'-]+$") || userNameTxt.getText().startsWith(".")) {
				message = "Name should only contain letters and those special characters used in names. \nName should not start with .\n";
				messageTextArea.setText(message);
				return;
			}			
			else {
	            //New user instance			
				User u1 = new User();
				u1.setId(Integer.parseInt(userIdTxt.getText()));
				u1.setName(userNameTxt.getText());
				u1.setProfession(userProfessionTxt.getText());
				message = userRepo.add(u1); //TODO
	            messageTextArea.setText(message); //TODO
	            //update table
	            updateTable();
			}
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
	    	boolean isInteger = isInteger(userIdTxt.getText());
	        	if (!isInteger) {
	        		message = "Id must be an integer.";
	            	messageTextArea.setText(message);
	                return;
	        	}
        	boolean userExist = checkUserExistance(Integer.parseInt(userIdTxt.getText()));
			if (!userExist) {
				message = "The user with that id does not exist.\n";
				messageTextArea.setText(message);
                return;
			}
			else {
		    	u1.setId(Integer.parseInt(userIdTxt.getText()));
				u1.setName(userNameTxt.getText());
				u1.setProfession(userProfessionTxt.getText());
	            message = userRepo.delete(u1);
	            messageTextArea.setText(message); //TODO
	            //update table
	            updateTable();
			}
	    }

	    @FXML
	    void selectUpdateUserView(ActionEvent event) {
	    	User u1 = new User();
	    	String message = null;
	    	//Text fields should not be empty
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
            boolean isInteger = isInteger(userIdTxt.getText());
        	if (!isInteger) {
        		message = "Id must be an integer.";
            	messageTextArea.setText(message);
                return;
        	}
        	boolean userExist = checkUserExistance(Integer.parseInt(userIdTxt.getText()));
			if (!userExist) {
				message = "The user with that id does not exist.\n";
				messageTextArea.setText(message);
                return;
			}
			// the expression should allow any kind of letter from any language as well as those special characters common in names.
			// Name should not start with .
			if (!userNameTxt.getText().matches("^[\\p{L} .'-]+$") || userNameTxt.getText().startsWith(".")) {
				message = "Name should only contain letters and those special characters used in names. \nName should not start with .\n";
				messageTextArea.setText(message);
				return;
			}		
			else {
		    	u1.setId(Integer.parseInt(userIdTxt.getText()));
				u1.setName(userNameTxt.getText());
				u1.setProfession(userProfessionTxt.getText());
		    	message = userRepo.update(u1);
	            messageTextArea.setText(message);
		    	updateTable();
			}
	    }

	    @FXML
	    void showSelectedUser(ActionEvent event) {
	    	String message = null;
	    	//Text fields should not be empty
	    	 if(!(userIdTxt.getText().length() > 0)) {
	            	message = "Enter id of user to show.";
	            	messageTextArea.setText(message);
	                return;
            }
	    	boolean isInteger = isInteger(userIdTxt.getText());
	        	if (!isInteger) {
	        		message = "Id must be an integer.";
	            	messageTextArea.setText(message);
	                return;
	        	}
        	boolean userExist = checkUserExistance(Integer.parseInt(userIdTxt.getText()));
			if (!userExist) {
				message = "The user with that id does not exist.\n";
				messageTextArea.setText(message);
                return;
			}
			else {
		    	int id = Integer.parseInt(userIdTxt.getText());
		    	User user = new User();
		    	user = userRepo.getSelectedUser(id);
		    	int userId = user.getId();
		    	String name = user.getName();
		    	String profession = user.getProfession();
		    	messageTextArea.setText("Selected user: \n");
		    	messageTextArea.appendText("Id: " + userId + "\n");
		    	messageTextArea.appendText("Name: " + name + "\n");
		    	messageTextArea.appendText("Profession: " + profession);    
			}
	    }
	
	 /**
	  * Update table.
	  */
	 // Updating table with result from Db search
		private void updateTable() {
	    	usersList = new ArrayList<User>();
	    	usersList = userRepo.getAllUsers();
			ObservableList<User> list = FXCollections.observableArrayList(usersList);
			userTable.setItems((ObservableList<User>) list);
		}
		
		private static boolean isInteger(String s) {
		      boolean isValidInteger = false;
		      try {
		         Integer.parseInt(s);	 
		         // s is a valid integer	 
		         isValidInteger = true;
		      }
		      catch (NumberFormatException ex) {
		         // s is not an integer
			      }	 
			  return isValidInteger;
			   }
		
		/**
		 * The check user existance method.
		 *
		 * @param userId the user id
		 * @return the boolean
		 */
		private boolean checkUserExistance(int userId) {
			ArrayList<User> allUsers = userRepo.getAllUsers();
			boolean userExist = false;
			for (User user : allUsers) {
				if (user.getId() == userId) {
					userExist = true;
					break;
				}
			}
			return userExist;
		}
		
		
}
