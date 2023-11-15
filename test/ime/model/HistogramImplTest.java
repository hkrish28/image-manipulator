package ime.model;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * class to test the histogramImpl class.
 */
public class HistogramImplTest {

  private Image image;
  private Histogram histogram;

  @Before
  public void setUp() {
    float[][][] testPixels = new float[][][]{{{55, 5, 5}, {73, 3, 83}},
        {{122, 242, 2}, {84, 4, 4}}};
    image = new ImagePixelImpl(testPixels, ImageType.RGB);
    histogram = new HistogramImpl(image);
  }

  /**
   * Test getChannelCount method
   */
  @Test
  public void testGetChannelCount() {

    int expectedChannelCount = 3;
    int actualChannelCount = histogram.getChannelCount();
    assertEquals(expectedChannelCount, actualChannelCount);
  }

  /**
   * Test getColorChannels method
   */
  @Test
  public void testGetColorChannels() {

    List<ColorChannelEnum> expectedChannels = Arrays.asList(ColorChannelEnum.RED,
        ColorChannelEnum.GREEN, ColorChannelEnum.BLUE);
    List<ColorChannelEnum> actualChannels = histogram.getColorChannels();
    //System.out.println(actualChannels);
    assertEquals(expectedChannels, actualChannels);
  }

  /**
   * Test getPeakValue method when there is peak
   */
  @Test
  public void testGetPeakValue() {
    int channelIndex = 1;
    int peakValue = histogram.getPeakValue(channelIndex, 10, 245);
    int actual = 1;
    assertEquals(actual, peakValue);
  }

  @Test
  public void testGetPeakValueNoPeak() {
    float[][][] testPixels = new float[][][]{{{5, 5, 5}, {3, 3, 3}}, {{2, 2, 2}, {4, 4, 4}}};
    image = new ImagePixelImpl(testPixels, ImageType.RGB);
    histogram = new HistogramImpl(image);
    int channelIndex = 1;
    int peakValue = histogram.getPeakValue(channelIndex, 10, 245);
    int actual = 0;
    assertEquals(actual, peakValue);
  }

  /**
   * Test getPeakValue with an invalid channel index
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetPeakValueInvalidIndex() {

    int invalidChannelIndex = 422;
    histogram.getPeakValue(invalidChannelIndex, 11, 23);
  }


  /**
   * Test getPeakValue with an invalid channel index
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetPeakValueInvalidStart() {

    int invalidChannelIndex = 1;
    histogram.getPeakValue(invalidChannelIndex, -1, 23);
  }

  /**
   * Test getPeakValue with an invalid channel index
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetPeakValueInvalidEnd() {

    int invalidChannelIndex = 1;
    histogram.getPeakValue(invalidChannelIndex, 11, 273);
  }

  /**
   * Test when providing valid inputs
   */
  @Test
  public void testGetMostFrequentValueValid() {
    // Test when providing valid inputs
    int channelIndex = 0;
    int start = 10;
    int end = 245;
    int actual = 55;

    int mostFrequentValue = histogram.getMostFrequentValue(channelIndex, start, end);
    assertEquals(actual, mostFrequentValue);
  }

  /**
   * test when image is black (no peaks).
   */
  @Test
  public void testGetMostFrequentValueBlackImageNoPeak() {
    float[][][] testPixels = new float[][][]{{{0, 0, 0}, {0, 0, 0}}, {{0, 0, 0}, {0, 0, 0}}};
    image = new ImagePixelImpl(testPixels, ImageType.RGB);
    histogram = new HistogramImpl(image);

    int channelIndex = 0;
    int start = 10;
    int end = 245;
    int actual = 0;

    int mostFrequentValue = histogram.getMostFrequentValue(channelIndex, start, end);
    assertEquals(actual, mostFrequentValue);
  }

  /**
   * test for invalid channel index.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetMostFrequentValueInvalidChannelIndex() {
    // Test when providing an invalid channel index
    int invalidChannelIndex = -1;
    int start = 10;
    int end = 245;

    histogram.getMostFrequentValue(invalidChannelIndex, start, end);
  }

  /**
   * test for invalid start.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetMostFrequentValueInvalidStart() {
    int channelIndex = 0;
    int invalidStart = 1;
    int invalidEnd = 245;

    histogram.getMostFrequentValue(channelIndex, invalidStart, invalidEnd);
  }

  /**
   * test for invalid end.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetMostFrequentInavlidEnd() {
    // Test when providing an empty range
    int channelIndex = 0;
    int start = 10;
    int end = 250;
    int mostFrequentValue = histogram.getMostFrequentValue(channelIndex, start, end);
  }

  /**
   * test when there is no duplicates in the list.
   */
  @Test
  public void testGetValueOccurrenceNoDuplicates() {

    // Test when providing valid inputs
    int channelIndex = 0;
    int pixelValue = 10;
    int actual = 0;
    int occurrence = histogram.getValueOccurence(channelIndex, pixelValue);
    assertEquals(actual, occurrence);

  }

  /**
   * // Test when providing valid inputs
   */
  @Test
  public void testGetValueOccurrenceValid() {

    float[][][] testPixels = new float[][][]{{{0, 0, 0}, {0, 0, 0}}, {{0, 0, 0}, {0, 0, 0}}};
    image = new ImagePixelImpl(testPixels, ImageType.RGB);
    histogram = new HistogramImpl(image);
    int channelIndex = 0;
    int pixelValue = 10;
    int actual = 0;
    int occurrence = histogram.getValueOccurence(channelIndex, pixelValue);
    assertEquals(actual, occurrence);
  }

  /**
   * // Test when providing an invalid channel index
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetValueOccurrenceInvalidChannelIndex() {

    int invalidChannelIndex = -1;
    int pixelValue = 10;

    histogram.getValueOccurence(invalidChannelIndex, pixelValue);
  }

  /**
   * // Test when providing an invalid pixel value
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetValueOccurrenceInvalidPixelValue() {

    int channelIndex = 0;
    int invalidPixelValue = 256;

    histogram.getValueOccurence(channelIndex, invalidPixelValue);
  }


}