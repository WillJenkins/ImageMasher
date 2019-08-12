package ImageProcessorBuild;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class MashProcessor {
    
    List<File> files;
    ArrayList<BufferedImage> images;
    BufferedImage result;
    
    
    /**
     * Default Constructor
     */
    public MashProcessor() {
	this.result = null;
    }
    
    
    public BufferedImage getResultImage() {
	return result;
    }
    
    public void addImages(ArrayList<BufferedImage> inputImages) {
	for(BufferedImage newImage:inputImages) {
	    this.images.add(newImage);
	}
    }
    
    public void mashImages() {
	//function where main process happens
	
	/*
	 * IMPORTANT NOTE: when processing again after new images have been added
	 * the entire process must be repeated in order to achieve a true result.
	 * For instance if the user added one picture at a time, the resulting image
	 * should be exactly the same as if they added them all in a stack. It does mean
	 * longer processing though. This is due to the consequences of integer division. 
	 */
    }
    
    public void loadFilesFromUser(ActionEvent actionEvent) {
	//call filechooser op, load to array of file names
	boolean foundNewFile = false;
	FileChooser fileChooser = new FileChooser();
	FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("JPEG files (*.jpg)", "*.jpg");
	fileChooser.getExtensionFilters().add(jpgFilter);
	Node source = (Node) actionEvent.getSource();
	Window stage = source.getScene().getWindow();
	List<File> newFiles;
	newFiles = fileChooser.showOpenMultipleDialog(stage);
	
	if(files != null) {
	    for(File newFile : newFiles) {
		if(!files.contains(newFile)) {
		    files.add(newFile);
		    foundNewFile = true;
		}
	    }
	}
	if(foundNewFile)
	    loadImagesFromFile();
	
    }
    
    private void loadImagesFromFile() {
	if(files != null) {
	    for(File file: files) {
		System.out.println(file);
	    }
	}
    }

}
