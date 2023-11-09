package ime.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class HistogramImpl implements Histogram {

  public HistogramImpl(Image image, int height) {
    this.height = height;
    channelCount = image.getChannelCount();
    imageType = image.getImageType();
    width = 256;
    hist = new int[image.getChannelCount()][256];
    for (int x = 0; x < image.getHeight(); x++) {
      for (int y = 0; y < image.getWidth(); y++) {

        float[] pixel = image.getPixelValues(x, y);
        for (int i = 0; i < channelCount; i++) {
          if ((hist[i][(int) (pixel[i])]) < height) {
            hist[i][(int) (pixel[i])]++;
          }
        }
      }
    }
  }

  private final int[][] hist;
  private final int channelCount;
  private final int height;
  private final int width;
  private ImageType imageType;

  @Override
  public BufferedImage createHistogram() {

    BufferedImage histogramImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics g = histogramImage.getGraphics();

    g.setColor(Color.WHITE);
    g.fillRect(0, 0, width, height);
    for (int i = 0; i < channelCount; i++) {
      // Draw the Red histogram
      g.setColor(imageType.colorChannels.get(i));
      for (int j = 0; j < width; j++) {
        // int normalizedValue = height * redHistogram[i] / maxCount;
        g.drawLine(j, hist[i][j], j + 1, hist[i][j + 1]);
      }
    }
    return histogramImage;
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
  public int getFirstPeakPixelValue(int channelIndex) throws IllegalArgumentException {
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

  @Override
  public Image colorCorrect(Image image) {
    int averagePeakValue = calculateAveragePeakValue();

    // Calculate brightness adjustment for each channel
    float[] brightnessAdjustment = new float[image.getChannelCount()];
    for (int channelIndex = 0; channelIndex < image.getChannelCount(); channelIndex++) {
      int currentPeakValue = getPeakValue(channelIndex);
      int peakDifference = averagePeakValue - currentPeakValue;
      brightnessAdjustment[channelIndex] = peakDifference / 255.0f;
      image = image.brighten(brightnessAdjustment[channelIndex]);
    }

    // Apply brightness adjustment to the image
    return image;
  }

  private int calculateAveragePeakValue() {
    int sumPeakValue = 0;
    for (int channelIndex = 0; channelIndex < channelCount; channelIndex++) {
      sumPeakValue += getPeakValue(channelIndex);
    }
    return sumPeakValue / imageType.colorChannels.size();
  }


}
