package ime.model;

import java.awt.image.BufferedImage;

public interface Histogram {


  BufferedImage createHistogram();

  /**
   * number of color channels in histogram.
   *
   * @return
   */
  int getChannelCount();

  /**
   * @param channelIndex
   * @return
   */
  int getPeakValue(int channelIndex);

  int getFirstPeakPixelValue(int channelIndex);


}
