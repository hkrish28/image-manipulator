package ime.model;

public interface Histogram {


  float[][][] createHistogram();

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

  int getMostFrequentValue(int channelIndex);



}
