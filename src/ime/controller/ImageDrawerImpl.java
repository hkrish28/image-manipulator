package ime.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageDrawerImpl implements ImageDrawer {

  private final int channelCount = 3;
  private BufferedImage image;
  private final BufferedImageHandler imageHandler;
  private int height;
  private int width;

  private Graphics graphics;

  public ImageDrawerImpl() {
    imageHandler = new BufferedImageHandler();
  }

  /**
   * Gets the drawing content of the image as a 3D array of floating-point pixel values.
   *
   * @return A 3D array of floating-point pixel values representing the drawing content of the
   * image.
   */
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

  /**
   * Draws a line on the canvas between two specified points.
   *
   * @param x1 The x-coordinate of the starting point.
   * @param y1 The y-coordinate of the starting point.
   * @param x2 The x-coordinate of the ending point.
   * @param y2 The y-coordinate of the ending point.
   */
  @Override
  public void drawLine(int x1, int y1, int x2, int y2) {
    //check if part of image width and height
    graphics.drawLine(x1, y1, x2, y2);
  }

  /**
   * Sets the drawing color based on a color palette.
   *
   * @param colorPalette An array of three values representing the Red, Green, and Blue color
   *                     components.
   * @throws IllegalArgumentException If the colorPalette does not contain three values.
   */
  @Override
  public void setColor(int[] colorPalette) {
    if (colorPalette.length != channelCount) {
      throw new IllegalArgumentException("Color Palette should contain three values corresponding" +
          "to Red, Green, and Blue values.");
    }
    graphics.setColor(new Color(colorPalette[0], colorPalette[1], colorPalette[2]));
  }

  /**
   * Sets up a drawing canvas with the specified width and height.
   *
   * @param width  The width of the canvas.
   * @param height The height of the canvas.
   */
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
