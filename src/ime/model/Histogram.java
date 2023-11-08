package ime.model;

import java.awt.image.BufferedImage;

public interface Histogram {


  BufferedImage createHistogram(Image image);

  /**
   * number of channels in histogram.
   * @return
   */
  int getChannelCount();
  int getMax(int channelIndex);


}
