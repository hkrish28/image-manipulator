package ime.model;

import ime.controller.ImageDrawer;

public class HistogramDrawerImpl implements HistogramDrawer {

  private final int height;
  private final int width;
  private final ImageDrawer imageDrawer;

  public HistogramDrawerImpl(int width, int height, ImageDrawer imageDrawer) {
    this.width = width;
    this.height = height;
    this.imageDrawer = imageDrawer;
  }

  private int getMaxCount(Histogram histogram) {
    int max = 0;
    for (int i = 0; i < histogram.getChannelCount(); i++) {
      if (histogram.getPeakValue(i) > max) {
        max = histogram.getPeakValue(i);
      }
    }
    return max;
  }

  private void setUpHistogram(ImageDrawer imageDrawer) {
    imageDrawer.setColor(new int[]{211, 211, 211});
    int columnLineAdder = width / 16;
    int rowLineAdder = height / 16;
    for (int i = 0; i < width; i += columnLineAdder) {
      imageDrawer.drawLine(i, 0, i, height);
    }
    for (int i = 0; i < height; i += rowLineAdder) {
      imageDrawer.drawLine(0, i, width, i);
    }
  }

  @Override
  public float[][][] visualizeHistogram(Histogram histogram) {
    imageDrawer.setUpCanvas(256, 256);

    setUpHistogram(imageDrawer);
    int maxCount = getMaxCount(histogram);
    for (int i = 0; i < histogram.getChannelCount(); i++) {
      imageDrawer.setColor(histogram.getColorChannels().get(i).rgb);
      for (int j = 0; j < width - 1; j++) {
        int normalizedValueStart = height * histogram.getValueOccurence(i, j) / maxCount;
        int normalizedValueEnd = height * histogram.getValueOccurence(i, j + 1) / maxCount;
        imageDrawer.drawLine(j, height - normalizedValueStart, j + 1, height - normalizedValueEnd);
      }
    }
    return imageDrawer.getImageDrawing();
  }
}
