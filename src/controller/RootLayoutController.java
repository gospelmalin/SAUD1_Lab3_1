package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

/**
 * The Class RootLayoutController.
 */
public class RootLayoutController {
	
	 /**
 	 * Handle exit.
 	 *
 	 * @param actionEvent the action event
 	 */
 	//Exit the program
    public void handleExit(ActionEvent actionEvent) {
        System.exit(0);
    }
 
    /**
     * Handle help.
     *
     * @param actionEvent the action event
     */
    //Help Menu button behavior
    public void handleHelp(ActionEvent actionEvent) {
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("Program Information");
        alert.setHeaderText("SAUD1 - Show, Add, Update and Delete user data in userdblab2 made by Malin");
        alert.setContentText("A JavaFX application to show, add, edit and delete user data in userdblab2 made by Malin");
        alert.show();
    }
}

