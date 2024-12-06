package org.example.final_project.models;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.image.PixelWriter;

public class Filter {

    public static Image blackWhiteFilter(Image image){
        int width=(int) image.getWidth();
        int height=(int) image.getHeight();
        WritableImage blackWhiteImage= new WritableImage(width,height);
        //use PixelReader read
        PixelReader reader= image.getPixelReader();
        //use PixelReader write
        PixelWriter writer= blackWhiteImage.getPixelWriter();

        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                Color color = reader.getColor(x,y);
                double gray=(color.getRed()+color.getGreen()+color.getBlue())/3.0;
                Color blackWhiteColor = new Color(gray, gray,gray,color.getOpacity());
                writer.setColor(x,y,blackWhiteColor);

            }
        }
        return blackWhiteImage;

    }

    public static Image colorTint(Image image, Color tintColor){
        int w=(int)image.getWidth();
        int h=(int)image.getHeight();
        WritableImage tinted= new WritableImage(w,h);

        //use PixelReader read
        PixelReader reader= image.getPixelReader();
        //use PixelReader write
        PixelWriter writer= tinted.getPixelWriter();


        for(int y=0; y<h; y++){
            for(int x=0; x<w; x++) {
                Color originalColor=reader.getColor(x,y);
                Color blendColor=originalColor.interpolate(tintColor,0.5);
                writer.setColor(x,y,blendColor);

            }
        }
        return tinted;

    }

}
