package ime.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This file handler supports loading and saving of RGB files in JPG and PNG formats.
 */
public class CommonFileHandler implements FileHandler {
  ImageHandler<BufferedImage> bufferedImageHandler;

  public CommonFileHandler() {
    bufferedImageHandler = new BufferedImageHandler();
  }

  @Override
  public void saveImage(float[][][] image, String filename) throws IOException {
    BufferedImage bufferedImage = bufferedImageHandler.convertIntoImage(image);
    ImageIO.write(bufferedImage, filename.split("\\.")[1], new File(filename));
  }

  @Override
  public float[][][] loadImage(String filename) throws IOException {
    BufferedImage bufferedImage = ImageIO.read(new File(filename));
    return bufferedImageHandler.getImagePixels(bufferedImage);
  }
}
