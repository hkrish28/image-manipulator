package ime.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class HistogramImpl implements Histogram {

  public HistogramImpl(Image image, int height) {
    this.height = height;
    channelCount = image.getChannelCount();
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

  private int[][] hist;
  private int channelCount;
  private int height;
  private int width;
  private ImageType imageType;

  @Override
  public BufferedImage createHistogram() {
    int width = 256;
    int height = 256;
    BufferedImage histogramImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics g = histogramImage.getGraphics();

    // finding max of frequency count of each color channel
    /*int maxCount = Math.max(
        Math.max(Arrays.stream(redHistogram).max().orElse(0), Arrays.stream(greenHistogram).max().orElse(0)),
        Arrays.stream(blueHistogram).max().orElse(0)
    );*/

    g.setColor(Color.WHITE);
    g.fillRect(0, 0, width, height);
    for (int i = 0; i < channelCount; i++) {
      // Draw the Red histogram
      g.setColor(imageType.colorChannels.get(i));
      for (int j = 0; j < width; j++) {
        // int normalizedValue = height * redHistogram[i] / maxCount;
        g.drawLine(j, hist[i][j], j + 1, hist[i][j + 1]);
      }
      // Draw the Green histogram

    }
    return histogramImage;
  }

  @Override
  public int getChannelCount() {
    return channelCount;
  }

  // y coordinate of peak
  @Override
  public int getPeakValue(int channelIndex) {
    return 0;
  }

  // for the given channel the x coordinate of the peak
  @Override
  public int getFirstPeakPixelValue(int channelIndex) {
    return 0;
  }


}
