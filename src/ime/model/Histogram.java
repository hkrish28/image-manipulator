package ime.model;

import java.util.List;

public interface Histogram {



  /**
   * number of color channels in histogram.
   *
   * @return
   */
  int getChannelCount();

  public List<ColorChannelEnum> getColorChannels();

  /**
   * @param channelIndex
   * @return
   */
  int getPeakValue(int channelIndex);

  int getMostFrequentValue(int channelIndex);

  int getValueOccurence(int channelIndex, int pixelValue);



}
