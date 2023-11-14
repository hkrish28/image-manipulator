package ime.controller;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageDrawerImpl implements ImageDrawer {

  private final int channelCount = 3;
  private BufferedImage image;
  private BufferedImageHandler imageHandler;
  private int height;
  private int width;

  private Graphics graphics;

  public ImageDrawerImpl() {
    imageHandler = new BufferedImageHandler();
  }

  @Override
  public float[][][] getImageDrawing() {
//    float[][][] imagePixels = new float[height][width][];
//    for (int i = 0; i < height; i++) {
//      for (int j = 0; j < width; j++) {
//        Color color = new Color(image.getRGB(j, i));
//        float red = color.getRed();
//        float green = color.getGreen();
//        float blue = color.getBlue();
//        imagePixels[i][j] = new float[]{red, green, blue};
//      }
//    }
//    return imagePixels;
    return imageHandler.getImagePixels(image);
  }

  @Override
  public void drawLine(int x1, int y1, int x2, int y2) {
    //check if part of image width and height
    graphics.drawLine(x1, y1, x2, y2);
  }

  @Override
  public void setColor(int[] colorPalette) {
    if (colorPalette.length != channelCount) {
      throw new IllegalArgumentException("Color Palette should contain three values corresponding" +
              "to Red, Green, and Blue values.");
    }
    graphics.setColor(new Color(colorPalette[0], colorPalette[1], colorPalette[2]));
  }

  @Override
  public void setUpCanvas(int width, int height) {
    image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    graphics = image.getGraphics();
    this.width = width;
    this.height = height;
    graphics.setColor(Color.WHITE);
    graphics.fillRect(0, 0, width, height);
  }
}
