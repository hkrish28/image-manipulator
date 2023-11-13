package ime.model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HistogramImpl implements Histogram {

  private final int[][] hist;
  private final int channelCount;
  private final int height;
  private final int width;
  private final ImageType imageType;
  private final int maxCount;

  public HistogramImpl(Image image, int height) {
    this.height = height;
    channelCount = image.getChannelCount();
    imageType = image.getImageType();
    width = 256;
    hist = new int[image.getChannelCount()][256];
    int max = 0;
    for (int x = 0; x < image.getHeight(); x++) {
      for (int y = 0; y < image.getWidth(); y++) {

        float[] pixel = image.getPixelValues(x, y);
        for (int i = 0; i < channelCount; i++) {
          hist[i][(int) (pixel[i])]++;
          if (hist[i][(int) (pixel[i])] > max) {
            max = hist[i][(int) (pixel[i])];
          }
        }
      }
    }
    maxCount = max;
  }


  @Override
  public float[][][] createHistogram() {

    BufferedImage histogramImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics g = histogramImage.getGraphics();

    setUpHistogram(g);
    for (int i = 0; i < channelCount; i++) {
      g.setColor(imageType.colorChannels.get(i));
      for (int j = 0; j < width - 1; j++) {
        int normalizedValueStart = height * hist[i][j] / maxCount;
        int normalizedValueEnd = height * hist[i][j + 1] / maxCount;
        g.drawLine(j, height - normalizedValueStart, j + 1, height - normalizedValueEnd);
      }
    }
    float[][][] imagePixels = new float[height][width][];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Color color = new Color(histogramImage.getRGB(j, i));
        float red = color.getRed();
        float green = color.getGreen();
        float blue = color.getBlue();
        imagePixels[i][j] = new float[]{red, green, blue};
      }
    }
    return imagePixels;
  }

  private void setUpHistogram(Graphics g) {
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, width, height);
    g.setColor(Color.LIGHT_GRAY);
    int columnLineAdder = width / 16;
    int rowLineAdder = height / 16;
    for (int i = 0; i < width; i += columnLineAdder) {
      g.drawLine(i, 0, i, height);
    }
    for (int i = 0; i < height; i += rowLineAdder) {
      g.drawLine(0, i, width, i);
    }
  }

  @Override
  public int getChannelCount() {
    return channelCount;
  }

  // y coordinate of peak
  @Override
  public int getPeakValue(int channelIndex) throws IllegalArgumentException {
    if (channelIndex < 0 || channelIndex >= channelCount) {
      throw new IllegalArgumentException("Invalid channel index");
    }

    int maxFrequency = 0;

    for (int pixelValue = 10; pixelValue < 245; pixelValue++) {
      if (hist[channelIndex][pixelValue] > maxFrequency) {
        maxFrequency = hist[channelIndex][pixelValue];

      }
    }

    return maxFrequency;
  }

  // for the given channel the x coordinate of the peak
  @Override
  public int getMostFrequentValue(int channelIndex) throws IllegalArgumentException {
    if (channelIndex < 0 || channelIndex >= channelCount) {
      throw new IllegalArgumentException("Invalid channel index");
    }

    int peakPixelValue = 0;
    int maxFrequency = 0;

    for (int pixelValue = 10; pixelValue < 245; pixelValue++) {
      if (hist[channelIndex][pixelValue] > maxFrequency) {
        maxFrequency = hist[channelIndex][pixelValue];
        peakPixelValue = pixelValue;
      }
    }

    return peakPixelValue;
  }


}
