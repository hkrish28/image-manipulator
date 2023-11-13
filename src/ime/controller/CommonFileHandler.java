package ime.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This file handler supports loading and saving of RGB files in JPG and PNG formats.
 */
public class CommonFileHandler implements FileHandler {

  @Override
  public void saveImage(BufferedImage image, String filename) throws IOException {
    ImageIO.write(image, filename.split("\\.")[1], new File(filename));
  }

  @Override
  public BufferedImage loadImage(String filename) throws IOException {
    BufferedImage bufferedImage = ImageIO.read(new File(filename));
    return bufferedImage;
  }
}
