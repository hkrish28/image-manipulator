package ime.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * class to test the RgbPixel class.
 */
public class RgbPixelTest {

  private RgbPixel pixel;

  /**
   * setup for rgb matrix.
   */
  @Before
  public void setUp() {
    float[] initialValues = {100.0f, 150.0f, 200.0f};
    pixel = new RgbPixel(initialValues);
  }

  /**
   * test to check getters. getValue method check.
   */
  @Test
  public void testGetValue() {
    float maxValue = pixel.getValue();
    assertEquals(200.0f, maxValue, 0.001f);
  }

  /**
   * test to get intensity.
   */
  @Test
  public void testGetIntensity() {
    float intensity = pixel.getIntensity();
    assertEquals(150.0f, intensity, 0.001f);
  }

  /**
   * test to get luma.
   */
  @Test
  public void testGetLuma() {
    float luma = pixel.getLuma();
    assertEquals(142.98f, luma, 0.001f);
  }

  /**
   * test to transform pixel.
   */
  @Test
  public void testTransformPixel() {
    float[][] transformCoefficients = {
            {0.5f, 0.0f, 0.0f},
            {0.0f, 0.5f, 0.0f},
            {0.0f, 0.0f, 0.5f}
    };

    Pixel transformedPixel = pixel.transformPixel(transformCoefficients);
    float[] transformedValues = transformedPixel.getChannelValues();

    assertEquals(50.0f, transformedValues[0], 0.001f);
    assertEquals(75.0f, transformedValues[1], 0.001f);
    assertEquals(100.0f, transformedValues[2], 0.001f);
  }

  /**
   * test to get channel values.
   */
  @Test
  public void testGetChannelValue() {
    float channelValue = pixel.getChannelValue(1);
    assertEquals(150.0f, channelValue, 0.001f);
  }

  /**
   * test to set color.
   */
  @Test
  public void testSetColor() {
    float[] newValues = {50.0f, 100.0f, 150.0f};
    pixel.setColor(newValues);

    float[] updatedValues = pixel.getChannelValues();
    assertArrayEquals(newValues, updatedValues, 0.001f);
  }

  /**
   * test to set color channels.
   */
  @Test
  public void testSetColorChannel() {
    float newValue = 75.0f;
    pixel.setColorChannel(1, newValue);

    float updatedValue = pixel.getChannelValue(1);
    assertEquals(newValue, updatedValue, 0.001f);
  }

  /**
   * test to check brighten method.
   */
  @Test
  public void testBrighten() {
    float brightnessConstant = 30.0f;
    Pixel brightenedPixel = pixel.brighten(brightnessConstant);

    float[] brightenedValues = brightenedPixel.getChannelValues();

    assertEquals(130.0f, brightenedValues[0], 0.001f);
    assertEquals(180.0f, brightenedValues[1], 0.001f);
    assertEquals(230.0f, brightenedValues[2], 0.001f);
  }
}