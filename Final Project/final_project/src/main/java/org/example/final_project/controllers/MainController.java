package org.example.final_project.controllers;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
public class MainController {

    @FXML
    private Button uploadButton;

    @FXML
    private Button downloadButton;

    @FXML
    private Button showProperties;

    @FXML
    private ImageView imageView;

    @FXML
    private Image nowImage;

    @FXML
    private ComboBox<String> formatComboBox;




    //Handles the "Upload Image" button action.
    //Opens a file chooser and displays the selected image in the ImageView.
    private static InputStream convertJavaFXImageToInputStream(Image fxImage) throws Exception {
        // Use ByteArrayOutputStream to write image bytes
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Load the image from its URL or source into an InputStream
        URL imageUrl = new URL(fxImage.getUrl());
        try (InputStream inputStream = imageUrl.openStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }

        // Convert the ByteArrayOutputStream to an InputStream
        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    @FXML
    private void uploadImage() throws Exception {
        FileChooser fileChooser= new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(
                "Image Files",
                "*.jpg", "*.png", "*.bmp"));
        File file = fileChooser.showOpenDialog(
                uploadButton.getScene().getWindow()
        );


        if(file != null){
            nowImage = new Image(file.toURI().toString());
            imageView.setImage(nowImage);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            imageView.setPreserveRatio(true);


        }
    }
    
    @FXML
    public void initialize() {
        //initialize the dropdown list and add available formats
        formatComboBox.setItems(FXCollections.observableArrayList("png", "jpg", "gif", "tiff"));
        //Set the default value
        formatComboBox.setValue("png");
    }


    public void downloadImageToFile(Image fxImage, String destinationPath, String format) throws IOException {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(fxImage, null);

        if (format.equalsIgnoreCase("jpg") || format.equalsIgnoreCase("jpeg")) {
            BufferedImage rgbImage = new BufferedImage(
                    bufferedImage.getWidth(),
                    bufferedImage.getHeight(),
                    BufferedImage.TYPE_INT_RGB
            );
            rgbImage.createGraphics().drawImage(bufferedImage, 0, 0, null);
            bufferedImage = rgbImage;
        }

        //define the file to be saved
        File outputFile = new File(destinationPath + "/saved_image." + format);

        //save the image in the specified format
        boolean result = ImageIO.write(bufferedImage, format, outputFile);
        if (result) {
            System.out.println("Image saved successfully to: " + outputFile.getAbsolutePath());
        } else {
            System.out.println("Failed to save the image. Format may not be supported.");
        }
    }

    @FXML
    private void downloadImage() {
        downloadButton.setOnAction(event -> {
            //open the folder selector
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Select Folder to Save Image");

            Stage chooserStage = new Stage();
            var selectedDirectory = directoryChooser.showDialog(chooserStage);

            if (selectedDirectory != null) {
                String destinationPath = selectedDirectory.getAbsolutePath();

                //get the format selected by the user
                String selectedFormat = formatComboBox.getValue();

                //download the image
                try {
                    downloadImageToFile(this.nowImage, destinationPath, selectedFormat);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("No directory selected.");
            }
        });
    }



    @FXML
    private void showProperties() throws Exception {
        //Convert JavaFX Image to InputStream
        InputStream imageStream = convertJavaFXImageToInputStream(nowImage);
        Metadata metadata = ImageMetadataReader.readMetadata(imageStream);


        //Print the extracted metadata
        StringBuilder tagsString = new StringBuilder();

        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {

                //System.out.println(tag);
                tagsString.append(tag.toString()).append(System.lineSeparator());
            }
        }


        //print in the Console
        System.out.println(tagsString.toString());



        Label text = new Label(tagsString.toString());
        text.setWrapText(true);
        text.setMaxWidth(300);
        text.setTranslateX(30);
        text.setTranslateY(30);
        Group text_root = new Group();
        text_root.getChildren().add(text);
        Scene scene = new Scene(text_root, 595, 450, Color.BEIGE);
        Stage stage = new Stage();
        stage.setTitle("Show Image Properties");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void openWindow(){
        if(nowImage == null) return;

        try {
            //load filter.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/final_project/filter.fxml"));
            BorderPane root = loader.load();

            //send the image to the filter controller
            FilterController controller = loader.getController();
            controller.setImage(nowImage);

            //a new stage
            Stage stage = new Stage();
            stage.setTitle("Apply Filters");
            stage.setScene(new Scene(root,800,600));
            stage.show();

        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
