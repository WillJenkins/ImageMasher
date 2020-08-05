package ImageProcessorBuild;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class ImageMashGUI {
    private int toolBarHeight;
    private int buttonWidth;
    private BorderPane root = null;
    private HBox toolBar = null;
    private Button save, quit, add, cropOn, undo;
    private MashProcessor masher;
    
    public ImageMashGUI() {
	//Default dimensions 
	this.toolBarHeight = 50;
	this.buttonWidth = 140;
	this.root = new BorderPane();
	this.toolBar = new HBox(); //main toolbar pane
	
	//run main gui funtion
	this.buildMainGUI();
	
	this.masher = new MashProcessor();
    }
    
    
    /**
     * Builds the main GUI in a BorderPane
     */
    private void buildMainGUI() {
	
	this.root.setPadding(new Insets(5,5,5,5));
	
	//Buttons
	this.save = new Button();
	this.quit = new Button();
	this.add = new Button();
	this.cropOn = new Button();
	this.undo = new Button();
	
	//Button adjustments
	String buttonID = "buttonText";
	save.setPrefSize(buttonWidth, toolBarHeight);
	quit.setPrefSize(buttonWidth, toolBarHeight);
	add.setPrefSize(buttonWidth, toolBarHeight);
	cropOn.setPrefSize(buttonWidth, toolBarHeight);
	undo.setPrefSize(buttonWidth, toolBarHeight);
	
	save.setId("save");
	quit.setId("quit");
	add.setId("add");
	cropOn.setId("crop");
	undo.setId("undo");
	
	Label saveLabel = new Label("Save JPEG");
	saveLabel.setId(buttonID);
	save.setGraphic(saveLabel);
	
	Label quitLabel = new Label("Quit");
	quitLabel.setId(buttonID);
	quit.setGraphic(quitLabel);
	
	
	Label addLbl = new Label("Add Images");
	addLbl.setId(buttonID);
	add.setGraphic(addLbl);
	
	Label cropOnLabel = new Label("Crop To Smallest");
	cropOnLabel.setId(buttonID);
	cropOn.setGraphic(cropOnLabel);
	
	Label undoLabel = new Label("Undo");
	undoLabel.setId(buttonID);
	undo.setGraphic(undoLabel);
	
	toolBar.setSpacing(5);
	toolBar.setAlignment(Pos.CENTER);
	toolBar.getStyleClass().add("toolBar");
	
	quit.setOnAction(e -> Platform.exit());              // Quit button action handler
	
	save.setOnAction(new EventHandler<ActionEvent>(){    // Save button action handler
	    @Override
	    public void handle(ActionEvent event) {
		System.out.println("Save clicked!");
		// TODO run save action
	    }
	});
	
	
	//Add Images
	add.setOnAction(new EventHandler<ActionEvent>(){     // Add button action handler
	    @Override
	    public void handle(ActionEvent event) {
		System.out.println("Add clicked!");
		// TODO run add action
		/* 
		 * create a function in the proc that returns a final display image.
		 * Be sure to keep the image stored in proc for save action
		 * add-button simply calls function and gets a final image to load in GUI
		 * create function to load image to GUI so add-button is just middleman 
		 * that passes the image on. Basically add-button should have maybe 2 LOC
		 * Image image = imgProc.getImageToDisplay()
		 * this.loadImageToGUI(image)
		 */
		ImageLoader loader = new ImageLoader();
		loader.loadFilesFromUser(event);
	    }
	});
	
	cropOn.setOnAction(new EventHandler<ActionEvent>(){   // Crop button action handler
	    @Override
	    public void handle(ActionEvent event) {
		System.out.println("Crop clicked!");
		// TODO run crop action
	    }
	});
	
	undo.setOnAction(new EventHandler<ActionEvent>(){    // Undo button action handler
	    @Override
	    public void handle(ActionEvent event) {
		System.out.println("Undo clicked!");
		// TODO run undo action
	    }
	});
	
	//add buttons to toolbar
	toolBar.getChildren().addAll(quit, save, add, cropOn, undo);
	
	toolBar.setPrefHeight(toolBarHeight); //toolBar height
	toolBar.setPadding(new Insets(5,5,5,5));

	root.setTop(toolBar); //Add toolBar to top of BorderPane (root)
	
	
	//MESSY AREA THAT NEEDS CLEANING 
	//------------------------------
	//Pane to display image
	Pane imagePane = new Pane();
	imagePane.setId("imagePane");
	imagePane.setPadding(new Insets(10,10,10,10));
	
	BorderPane imageFrame = new BorderPane();
	BorderPane.setAlignment(imageFrame, Pos.CENTER);
	imagePane.getChildren().add(imageFrame);
	
	//TEST IMAGE ONLY ====================
	BufferedImage testImage = this.getBufferedImageFromFile(
		"\\Users\\Will\\Pictures\\Pictures\\My Art\\out2.jpg");
	Image image = SwingFXUtils.toFXImage(testImage, null);
	ImageView imgView = new ImageView();
	imgView.setImage(image);
	imgView.setPreserveRatio(true);
	imgView.fitWidthProperty().bind(imagePane.widthProperty());
	imgView.fitHeightProperty().bind(imagePane.heightProperty());
	
	imageFrame.setCenter(imgView);; //DEBUG add test image to imagePane
	
	root.setCenter(imagePane); //add image pane to root
    }
    
    public BorderPane getRoot() {
	return this.root;
    }
    
    public BufferedImage getBufferedImageFromFile(String filePath) {
	// clean previous files out in case of exception 
	File file = null;
	BufferedImage image = null;
	try {
	    file = new File(filePath);
	    image = ImageIO.read(file);
	} catch(IOException e) {
		 System.out.println("getBufferedImageFromFile has thrown and IOException"
			 + "\nCheck for valid filePath, check that file exists");
		 //implement cases for missing file or invalid file
	} 
	return image;
    }
    
    
}
