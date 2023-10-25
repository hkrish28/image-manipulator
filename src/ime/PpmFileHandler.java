package ime;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PpmFileHandler extends AbstractFileHandler {

  @Override
  public List<ColorChannel> loadFile(String filename) throws FileNotFoundException {

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
    float[][] red = new float[height][width];
    float[][] green = new float[height][width];
    float[][] blue = new float[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        red[i][j] = sc.nextInt();
        green[i][j] = sc.nextInt();
        blue[i][j] = sc.nextInt();
      }
    }
    List<ColorChannel> result = new ArrayList<>();
    result.add(new ArrayColorChannel(red));
    result.add(new ArrayColorChannel(green));
    result.add(new ArrayColorChannel(blue));
    return result;
  }

  @Override
  public void saveFile(Image image, String filename) throws IOException {
    StringBuilder builder = new StringBuilder();
    builder.append("P3" + System.lineSeparator());
    builder.append(image.getWidth() + " " + image.getHeight() + System.lineSeparator());
    builder.append(findMaxValue(image) + System.lineSeparator());
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        for (int k = 0; k < image.getColorChannelCount(); k++) {
          int channelValue = (int) image.getPixelValue(k, i, j);
          builder.append(channelValue + " ");
        }
        builder.append(" ");
      }
      builder.append(System.lineSeparator());
    }
    FileOutputStream fos = new FileOutputStream("./src/out.ppm");
    fos.write(builder.toString().getBytes());
    fos.close();

  }

  private int findMaxValue(Image image) {
    int max = 0;
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int pixelValue = 0;
        for (int k = 0; k < image.getColorChannelCount(); k++) {
          int channelValue = (int) image.getPixelValue(k, i, j);
          if (channelValue > pixelValue) {
            pixelValue = channelValue;
          }
        }
        if (pixelValue == 255) {
          return pixelValue;
        } else if (pixelValue > 255) {
          max = pixelValue;
        }
      }
    }
    return max;
  }
}
