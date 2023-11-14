package ime.model;

import java.util.List;

public class HistogramImpl implements Histogram {

  private final int[][] hist;
  private final int channelCount;
  private final int width;
  private final ImageType imageType;


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


  @Override
  public int getChannelCount() {
    return channelCount;
  }

  @Override
  public List<ColorChannelEnum> getColorChannels() {
    return imageType.colorChannels;
  }

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

  @Override
  public int getValueOccurence(int channelIndex, int pixelValue) {
    validateChannelIndex(channelIndex);
    validatePixelValue(pixelValue);
    return hist[channelIndex][pixelValue];
  }

  private void validatePixelValue(int pixelValue) {
    if (pixelValue < 0 || pixelValue >= width) {
      throw new IllegalArgumentException("Invalid channel index");
    }
  }

  private void validateChannelIndex(int channelIndex){
    if (channelIndex < 0 || channelIndex >= channelCount) {
      throw new IllegalArgumentException("Invalid channel index");
    }
  }

}
