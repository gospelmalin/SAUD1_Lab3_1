package application;
	
import java.io.IOException;
import controller.ViewController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


/**
 * The Class Main.
 */
public class Main extends Application {
	
	/** The primary stage. */
	//PrimaryStage
    private Stage primaryStage;
 
    /** The root layout. */
    //BorderPane of RootLayout
    private BorderPane rootLayout;
    
    /** The scene. */
    public static Scene scene;
    
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) {
		//Declare a primary stage (will be used for everything)
        this.primaryStage = primaryStage;
 
        //Set primary stage title
        this.primaryStage.setTitle("SAUD1 - Show, Add, Update and Delete user data in userdblab2 - by Malin");
 
        //Initialize RootLayout
        initRootLayout();
 
        //Display the Start View
        showStartView();
        
	}
	
	/**
	 * Initializes the root layout.
	 */
    public void initRootLayout() {
        try {
            //Load root layout from RootLayout.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load(); 

            //Show the scene holding the root layout.
            scene = new Scene(rootLayout); //rootLayout sent to scene.
            primaryStage.setScene(scene); //Set the scene in primary stage.
 
            //Show the primary stage
            primaryStage.show(); //Display the primary stage
        } catch (IOException e) {
        	System.err.println("An IO exception occured when initilizing root layout: " + e.getMessage());
        }
    }
    
  /**
   * Show start view inside the root layout.
   */
    public void showStartView() {
        try {
            //Load StartView from StartView.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/StartView.fxml"));
            AnchorPane startView = (AnchorPane) loader.load();

            //Create an instance of ViewController to hold the different views. We need Scene which is the "foundation" of the view
            ViewController viewcontroller = new ViewController(scene);
            //Add all the views for the project here (name + location)
            viewcontroller.addScreen("StartView", FXMLLoader.load(getClass().getResource("/view/StartView.fxml")));
            viewcontroller.addScreen("ShowUsersView", FXMLLoader.load(getClass().getResource("/view/ShowUsersLayout.fxml")));

            //ViewControllers(scene);
            // Set Start view into the center of root layout.
            rootLayout.setCenter(startView);
     
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
