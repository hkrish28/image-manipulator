package ime.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BufferedImageHandler implements ImageHandler<BufferedImage> {

  private static BufferedImage getBufferedImage(float[][][] pixelValues) {
    int width = pixelValues[0].length;
    int height = pixelValues.length;
    BufferedImage bufferedImageResult = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        float[] pixel = pixelValues[y][x];
        float red = pixel[0];
        float green = pixel[1];
        float blue = pixel[2];

        // Convert the floating-point values back to RGB integers
        int rgb = (int) red << 16 | (int) green << 8 | (int) blue;

        // Set the pixel in the BufferedImage
        bufferedImageResult.setRGB(x, y, rgb);
      }
    }
    return bufferedImageResult;
  }

  @Override
  public float[][][] loadImage(BufferedImage image) {
    ColorModel colorModel = image.getColorModel();
    int height = image.getHeight();
    int width = image.getWidth();
    int channelCount = colorModel.getNumComponents();
    float[][][] resultPixels = new float[height][width][channelCount];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int[] colorChannelValues = new int[channelCount];
        colorModel.getComponents(image.getRGB(i, j), colorChannelValues, 0);
        for (int k = 0; k < channelCount; k++) {
          resultPixels[i][j][k] = colorChannelValues[k];
        }
      }
    }
    return resultPixels;
  }

  @Override
  public BufferedImage saveImage(float[][][] pixelValues, List<Color> colorChannels) {
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
