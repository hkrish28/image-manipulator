package ime.model;

import java.util.List;

/**
 * interface histogram contains methods to creat a histogram image for a given image.
 */
public interface Histogram {


  /**
   * Retrieves the number of color channels in the image.
   *
   * @return The number of color channels present in the image.
   */
  int getChannelCount();

  /**
   * Retrieves the list of color channels associated with the image type used in the histogram.
   *
   * @return A list of ColorChannelEnum representing the color channels in the image type.
   */
  List<ColorChannelEnum> getColorChannels();

  /**
   * Finds the y-coordinate (peak value) of the histogram peak for the specified channel index.
   *
   * @param channelIndex The index of the color channel.
   * @return The peak value (y-coordinate) of the histogram for the specified channel.
   */
  int getPeakValue(int channelIndex, int start, int end);

  /**
   * Finds the most frequent value (x-coordinate of the peak) for the specified channel index.
   *
   * @param channelIndex The index of the color channel.
   * @return The most frequent value (x-coordinate of the peak) for the specified channel.
   */
  int getMostFrequentValue(int channelIndex, int start , int end);

  /**
   * Retrieves the occurrence frequency of a specific pixel value for a given channel index.
   *
   * @param channelIndex The index of the color channel.
   * @param pixelValue   The value of the pixel for which occurrence frequency is required.
   * @return The frequency of occurrence of the specified pixel value in the given channel.
   */
  int getValueOccurence(int channelIndex, int pixelValue);


}
