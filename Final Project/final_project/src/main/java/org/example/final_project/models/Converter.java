package org.example.final_project.models;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public abstract class Converter implements Processor {

    /*
    @Override
    public void convertImage(File inputFile, String outputFormat, File outputDirectory) throws Exception{
        BufferedImage image = ImageIO.read(inputFile);
        File outputFile = new File()

    }
    */

    public void convertImage(File inputFile, File outputFile) throws Exception {
        try {
            // Read the image
            BufferedImage colorImage = ImageIO.read(inputFile);

            // Get image dimensions
            int width = colorImage.getWidth();
            int height = colorImage.getHeight();

            // Create a new grayscale image
            BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

            // Convert each pixel to grayscale
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = colorImage.getRGB(x, y);

                    // Extract RGB components
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;

                    // Calculate grayscale value
                    int gray = (int) (0.3 * red + 0.59 * green + 0.11 * blue);

                    // Pack grayscale value into RGB format
                    int grayRgb = (gray << 16) | (gray << 8) | gray;

                    // Set the pixel in the grayscale image
                    grayImage.setRGB(x, y, grayRgb);
                }
            }

            // Save the grayscale image
            ImageIO.write(grayImage, "jpg", outputFile);

            System.out.println("Grayscale conversion completed. Image saved as grayscale_image.jpg");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
