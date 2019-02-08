package controller;

import java.util.HashMap;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * The Class ViewController.
 */
public class ViewController {

	/** The screen holder. */
	private static HashMap<String, Pane> screenHolder = new HashMap<>(); //Hashmap holding all layouts with name and location of each resource
    
    /** The main scene Rootlayout. */
    private static Scene main; 

    /**
     * Instantiates a new view controller.
     *
     * @param main the main
     */
    //constructor
    public ViewController(Scene main) {
        this.main = main;
    }

    /**
     * Adds the screen.
     *
     * @param name the name
     * @param pane the pane
     */
    //Add to list
    public void addScreen(String name, Pane pane){
        screenHolder.put(name, pane);
    }

    /**
     * Activate layout.
     *
     * @param name the name
     */
    public static void activate(String name){
        main.setRoot( screenHolder.get(name) );
    }
	
}
