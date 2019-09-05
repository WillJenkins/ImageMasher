package ImageProcessorBuild;

import java.awt.image.BufferedImage;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.EventHandler;

// MAIN JAVA FILE (Don't really need to add stuff here other than comments)
//need: 
//	quit, 
//	save, 
//	add one image, 
//	add mult
//      crop to smallest, 
//	undo crop (warn once image is cropped any changes made after will mean
//      the crop cannot be undone (or we just preview the image as 
//      cropped and don't actually alter it))

//possible:
//	remove a specific image
//	when images sizes do not line up have background color choice:
//	Alpha-zero, black, white, middle grey, custom color(color picker)
//	Zoom in/out

//  Create option or separate app to match image sizes

/**
 * Image Mash is a program that reads a stack of jpeg images and
 * calculates the average value of each overlapping pixel of each image in the stack
 * and then generates a jpeg image from the resultant pixels
 * @author William Jenkins 2019
 * github repo: 
 *
 */
public class ImageMash extends Application{
    
    

    @Override
    public void start(Stage stage) throws Exception {
	
	int stageHeight = 800;
	int stageWidth = 1000;
	
	stage.setTitle("Unnamed Image Processor");
	ImageMashGUI mainGUIPane = new ImageMashGUI();
	
	//String css = ImageMash.class.getResource("style.css").toExternalForm();
	Scene scene = new Scene(mainGUIPane.getRoot(), stageWidth, stageHeight);

	
	stage.setScene(scene);
	scene.getStylesheets().add(ImageMash.class.getResource("style.css").toExternalForm());
	stage.show();
    }
    
    public static void main(String[] args) {
	Application.launch(args);
    }
    
    

}
