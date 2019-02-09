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
    
    
    /**
     * Initialize the controller class.
     */
    //This method is automatically called after the fxml file has been loaded.
      @FXML
      private void initialize () {
      	
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