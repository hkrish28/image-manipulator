package ime.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import ime.model.Image;

/**
 * A File Handler is used for loading and saving images of certain file formats and image types
 * (implementation specific).
 */
public interface FileHandler {

  /**
   * This method loads the file path passed as argument and returns the values of image pixels in
   * a 2D array. The values of pixels is a float array for storing each of its color channels.
   *
   * @param filename the filename to be loaded
   * @return the pixel values of the images
   * @throws IOException if any error occurs during file loading or if the file does not exist
   */
  BufferedImage loadImage(String filename) throws IOException;

  /**
   * This method saves the given image into the file path passed as parameter.
   *
   * @param image    the image to be saved
   * @param filename the filename (or path) that the image will be saved in
   * @throws IOException if any error occurs during file saving
   */
  void saveImage(BufferedImage image, String filename) throws IOException;

}
