package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * The Class StartViewController handles the start view where the user can choose
 * what to do.
 */
public class StartViewController {
	
	 /** The show users view button. */
    @FXML
    private Button showUsersViewBtn;
    
    /** The add user view button. */
    @FXML
    private Button addUserViewBtn;

    /** The update user view button. */
    @FXML
    private Button updateUserViewBtn;

    /** The delete user view button. */
    @FXML
    private Button deleteUserViewBtn;

    
    /**
     * Initialize the controller class.
     */
    //This method is automatically called after the fxml file has been loaded.
      @FXML
      private void initialize () {
      	
      }

    /**
     * Select add user view opens the add user view.
     *
     * @param event the event
     */
    @FXML
    void selectAddUserView(ActionEvent event) {
    	//ViewController.activate("AddUserView");
    	System.out.println("AddUserView should be shown.");
    }

    /**
     * Select delete user view opens the delete user view.
     *
     * @param event the event
     */
    @FXML
    void selectDeleteUserView(ActionEvent event) {
    //	ViewController.activate("DeleteUserView");
    	System.out.println("DeleteUserView should be shown.");
    }

    /**
     * Select update user view opens the update user view.
     *
     * @param event the event
     */
    @FXML
    void selectUpdateUserView(ActionEvent event) {
    	//ViewController.activate("UpdateUserView");
    	System.out.println("UpdateUserView should be shown.");
    }

    /**
     * Select show user view opens the show user view.
     *
     * @param event the event
     */
    @FXML
    void selectShowUsersView(ActionEvent event) {
    	ViewController.activate("ShowUsersView");
    	System.out.println("ShowUsersView should be shown.");
    }



   

}