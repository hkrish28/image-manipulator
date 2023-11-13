package ime.controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ime.model.ImageHandler;

public class BufferedImageHandler implements ImageHandler<BufferedImage> {

  private static BufferedImage getBufferedImage(float[][][] pixelValues) {
    int width = pixelValues[0].length;
    int height = pixelValues.length;
    BufferedImage bufferedImageResult = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        float[] pixel = pixelValues[y][x];
        int red = (int) pixel[0];
        int green = (int) pixel[1];
        int blue = (int) pixel[2];
        Color color = new Color(red, green, blue);

        // Set the pixel in the BufferedImage
        bufferedImageResult.setRGB(x, y, color.getRGB());
      }
    }
    return bufferedImageResult;
  }

  @Override
  public float[][][] getImagePixels(BufferedImage image) {
    ColorModel colorModel = image.getColorModel();
    int height = image.getHeight();
    int width = image.getWidth();
    int channelCount = colorModel.getNumColorComponents();
    float[][][] resultPixels = new float[height][width][channelCount];
    Raster raster = image.getData();
    //Using numComponents as the pixel could contain alpha value, which we ignore
    int[] pixelValues = new int[colorModel.getNumComponents()];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        raster.getPixel(j, i, pixelValues);

        for (int k = 0; k < channelCount; k++) {
          resultPixels[i][j][k] = pixelValues[k];
        }
      }
    }
    return resultPixels;
  }

  @Override
  public BufferedImage convertIntoImage(float[][][] pixelValues, List<Color> colorChannels) {
    BufferedImageType imageType = BufferedImageType.getImageTypeFromChannels(colorChannels);
    if (imageType == null) {
      throw new IllegalArgumentException("Invalid type for supported buffered images");
    }
    switch (imageType) {
      case RGB:
        return getBufferedImage(pixelValues);
      default:
        throw new IllegalArgumentException("Invalid type for supported buffered images");
    }

  }

  private enum BufferedImageType {
    RGB(Arrays.asList(Color.RED, Color.GREEN, Color.BLUE), BufferedImage.TYPE_INT_RGB);
    int bufferedImageType;
    List<Color> colorChannels;

    BufferedImageType(List<Color> colorChannels, int bufferedImageType) {
      this.colorChannels = colorChannels;
      this.bufferedImageType = bufferedImageType;
    }

    private static BufferedImageType getImageTypeFromChannels(List<Color> colors) {
      List<BufferedImageType> colorChannelImageType =
              Arrays.stream(BufferedImageType.values()).filter(imageType -> {
                if (imageType.colorChannels.size() != colors.size()) {
                  return false;
                }
                return Arrays.deepEquals(colors.toArray(), imageType.colorChannels.toArray());
              }).collect(Collectors.toList());
      if (colorChannelImageType.isEmpty()) {
        return null;
      } else {
        return colorChannelImageType.get(0);
      }
    }
  }
}
