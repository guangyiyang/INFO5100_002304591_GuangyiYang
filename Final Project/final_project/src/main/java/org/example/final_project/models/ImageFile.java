package org.example.final_project.models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class ImageFile {

    private final File file;
    private ImageView thumb;
    private int w; //width
    private int h; //height


    public ImageFile(File file){
        this.file = file;
        createThumb();
    }

        private void createThumb(){
            Image image = new Image(file.toURI().toString(),100,100,true,true);
            thumb = new ImageView(image);
            w=(int)image.getWidth();
            h=(int)image.getHeight();
        }


        public String getFileName(){
            return file.getName();
        }

        public File getFile(){
            return file;
        }

        public int getW() {
            return w;
        }


        public int setH() {
            return h;
        }

}


