package ime.controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * This file handler supports loading and saving of RGB files in PPM formats.
 */
public class PpmFileHandler implements FileHandler {

  @Override
  public BufferedImage loadImage(String filename) throws FileNotFoundException {

    Scanner sc = this.getScanner(filename);
    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int red = sc.nextInt();
        int green = sc.nextInt();
        int blue = sc.nextInt();
        Color color = new Color(red, green, blue);
        bufferedImage.setRGB(i, j, color.getRGB());
      }
    }
    return bufferedImage;
  }


  @Override
  public void saveImage(BufferedImage image, String filename) throws IOException {
    StringBuilder builder = new StringBuilder();
    builder.append("P3").append(System.lineSeparator())
            .append(image.getWidth()).append(" ")
            .append(image.getHeight()).append(System.lineSeparator())
            .append(findMaxValue(image)).append(System.lineSeparator());
    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        Color color = new Color(image.getRGB(x, y), true);
        builder.append(color.getRed()).append(" ")
                .append(color.getGreen()).append(" ")
                .append(color.getBlue()).append(" ");
      }
      builder.append(System.lineSeparator());
    }

    FileOutputStream fos = new FileOutputStream(filename);
    fos.write(builder.toString().getBytes());
    fos.close();

  }

  private int[] getPixelValues(int pixel) {
    return new int[]{(pixel >> 16) & 0xFF, (pixel >> 8) & 0xFF, pixel & 0xFF};
  }

  private int findMaxValue(BufferedImage image) {
    int max = 0;
    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        Color color = new Color(image.getRGB(i, j), true);
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        int pixelValue = Math.max(Math.max(red, green), blue);
        if (pixelValue == 255) {
          return pixelValue;
        } else if (pixelValue > max) {
          max = pixelValue;
        }
      }
    }
    return max;
  }

  private Scanner getScanner(String filename) throws FileNotFoundException {
    Scanner sc = new Scanner(new FileInputStream(filename));
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());
    return sc;
  }
}
