package ImageProcessorBuild;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class ImageLoader {
    
    BufferedImage image;
    File file;
    List<File> files;
    ArrayList<BufferedImage> images;
    
    public ImageLoader() {
	this.image = null;
	this.file = null;
    }
    
    public BufferedImage getBufferedImageFromFile(String filePath) {
	// clean previous files out in case of exception 
	this.file = null;
	this.image = null;
	try {
	    file = new File(filePath);
	    image = ImageIO.read(file);
	} catch(IOException e) {
		 System.out.println("getBufferedImageFromFile has thrown an IOException"
			 + "\nCheck for valid filePath, check that file exists");
		 //implement cases for missing file or invalid file
	} 
	return image;
    }
    
    public void loadFilesFromUser(ActionEvent actionEvent) {
	//call filechooser op, load to array of file names
	FileChooser fileChooser = new FileChooser();
	FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("JPEG files (*.jpg)", "*.jpg");
	fileChooser.getExtensionFilters().add(jpgFilter);
	Node source = (Node) actionEvent.getSource();
	Window stage = source.getScene().getWindow();
	files = fileChooser.showOpenMultipleDialog(stage);
	loadImagesFromFile();
	
    }
    
    private void loadImagesFromFile() {
	if(files != null) {
	    for(File file: files) {
		System.out.println(file);
	    }
	}
    }
    
    public ArrayList<BufferedImage> getArrayListOfImages() {
	return this.images;
    }

}
