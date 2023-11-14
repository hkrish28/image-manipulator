package ime.model;

import java.util.List;

/**
 * The HistogramImpl class implements the Histogram interface, providing methods to generate and
 * analyze histograms based on the pixel values of an Image. It calculates histograms for individual
 * color channels and provides methods to extract peak values and occurrence frequency.
 **/
public class HistogramImpl implements Histogram {

  private final int[][] hist;
  private final int channelCount;
  private final int width;
  private final ImageType imageType;

  /**
   * Constructs a HistogramImpl object based on the given Image, initializing the histogram data.
   *
   * @param image The Image from which to generate the histogram.
   */
  public HistogramImpl(Image image) {

    channelCount = image.getChannelCount();
    imageType = image.getImageType();
    width = 256;
    hist = new int[image.getChannelCount()][256];

    for (int x = 0; x < image.getHeight(); x++) {
      for (int y = 0; y < image.getWidth(); y++) {

        float[] pixel = image.getPixelValues(x, y);
        for (int i = 0; i < channelCount; i++) {
          hist[i][(int) (pixel[i])]++;
        }
      }
    }

  }

  /**
   * Retrieves the number of color channels in the image.
   *
   * @return The number of color channels present in the image.
   */

  @Override
  public int getChannelCount() {
    return channelCount;
  }

  /**
   * Retrieves the list of color channels associated with the image type used in the histogram.
   *
   * @return A list of ColorChannelEnum representing the color channels in the image type.
   */
  @Override
  public List<ColorChannelEnum> getColorChannels() {
    return imageType.colorChannels;
  }

  /**
   * Finds the y-coordinate (peak value) of the histogram peak for the specified channel index.
   *
   * @param channelIndex The index of the color channel.
   * @return The peak value (y-coordinate) of the histogram for the specified channel.
   * @throws IllegalArgumentException If the channel index is invalid.
   */
  // y coordinate of peak
  @Override
  public int getPeakValue(int channelIndex) throws IllegalArgumentException {
    validateChannelIndex(channelIndex);

    int maxFrequency = 0;

    for (int pixelValue = 10; pixelValue < 245; pixelValue++) {
      if (hist[channelIndex][pixelValue] > maxFrequency) {
        maxFrequency = hist[channelIndex][pixelValue];

      }
    }

    return maxFrequency;
  }

  /**
   * Finds the most frequent value (x-coordinate of the peak) for the specified channel index.
   *
   * @param channelIndex The index of the color channel.
   * @return The most frequent value (x-coordinate of the peak) for the specified channel.
   * @throws IllegalArgumentException If the channel index is invalid.
   */
  // for the given channel the x coordinate of the peak
  @Override
  public int getMostFrequentValue(int channelIndex) throws IllegalArgumentException {
    validateChannelIndex(channelIndex);

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

  /**
   * Retrieves the occurrence frequency of a specific pixel value for a given channel index.
   *
   * @param channelIndex The index of the color channel.
   * @param pixelValue   The value of the pixel for which occurrence frequency is required.
   * @return The frequency of occurrence of the specified pixel value in the given channel.
   * @throws IllegalArgumentException If the channel index or pixel value is invalid.
   */
  @Override
  public int getValueOccurence(int channelIndex, int pixelValue) {
    validateChannelIndex(channelIndex);
    validatePixelValue(pixelValue);
    return hist[channelIndex][pixelValue];
  }

  /**
   * checks if the pixel value is a valid value( less than 255).
   *
   * @param pixelValue is the pixel value that has to be validated.
   */
  private void validatePixelValue(int pixelValue) {
    if (pixelValue < 0 || pixelValue >= width) {
      throw new IllegalArgumentException("Invalid channel index");
    }
  }

  /**
   * checks if the passed channel index is valid.
   *
   * @param channelIndex channel index.
   */
  private void validateChannelIndex(int channelIndex) {
    if (channelIndex < 0 || channelIndex >= channelCount) {
      throw new IllegalArgumentException("Invalid channel index");
    }
  }

}
