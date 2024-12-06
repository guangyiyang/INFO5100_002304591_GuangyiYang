package org.example.final_project.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.awt.image.ImageFilter;

import org.example.final_project.models.Filter;


public class FilterController {

    @FXML
    private ImageView viewOriginal;

    @FXML
    private ImageView viewFiltered;

    @FXML
    private ComboBox<String> filterComboBox;

    private Image nowImage;



    public void setImage(Image image) {
        this.nowImage = image;
        viewOriginal.setImage(image);
    }

    
    @FXML
    private void applyFilter() {
        String selected = filterComboBox.getValue();
        if (selected==null||nowImage==null){
            System.out.println("No filter selected or image not loaded!");
        return;
        }

        Image filteredImage = switch(selected) {
            case "Black and White"->Filter.blackWhiteFilter(nowImage);
            case "Red Tint"->Filter.colorTint(nowImage, Color.RED);
            case "Blue Tint"->Filter.colorTint(nowImage, Color.BLUE);
            default->nowImage;
        };

        viewFiltered.setImage(filteredImage);
    }
    


}
