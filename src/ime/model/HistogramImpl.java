package ime.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class HistogramImpl implements Histogram {



  @Override
  public BufferedImage createHistogram(Image image) {
    int width = 256;
    int height = 256;
    BufferedImage histogramImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics g = histogramImage.getGraphics();

    // Calculate histograms for each color channel (Red, Green, Blue)
    int[] redHistogram = new int[256];
    int[] greenHistogram = new int[256];
    int[] blueHistogram = new int[256];
    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {

        float[] pixel = image.getPixelValues(x, y);

        redHistogram[(int) (pixel[0])]++;
        greenHistogram[(int) (pixel[1])]++;
        blueHistogram[(int) (pixel[2])]++;
      }
    }
    // finding max of frequency count of each color channel
    /*int maxCount = Math.max(
        Math.max(Arrays.stream(redHistogram).max().orElse(0), Arrays.stream(greenHistogram).max().orElse(0)),
        Arrays.stream(blueHistogram).max().orElse(0)
    );*/

    g.setColor(Color.WHITE);
    g.fillRect(0, 0, width, height);

    // Draw the Red histogram
    g.setColor(Color.RED);
    for (int i = 0; i < 255; i++) {
      // int normalizedValue = height * redHistogram[i] / maxCount;
      g.drawLine(i, redHistogram[i],i+1,redHistogram[i+1] );
    }
    // Draw the Green histogram
    g.setColor(Color.GREEN);
    for (int i = 0; i < 255; i++) {
      // int normalizedValue = height * greenHistogram[i] / maxCount;
      g.drawLine(i, greenHistogram[i], i+1, greenHistogram[i+1]);
    }
    // Draw the Blue histogram
    g.setColor(Color.BLUE);
    for (int i = 0; i < 255; i++) {
      //int normalizedValue = height * blueHistogram[i] / maxCount;
      g.drawLine(i, blueHistogram[i], i+1, greenHistogram[i+1]);
    }
    return histogramImage;
  }

  @Override
  public int getChannelCount() {
    return 0;
  }

  @Override
  public int getMax(int channelIndex) {
    return 0;
  }
}
