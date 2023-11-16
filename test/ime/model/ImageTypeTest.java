package ime.model;

import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the {@link ImageType} enum.
 */
public class ImageTypeTest {


  @Test
  public void testGeneratePixelRgb() {
    Pixel pixel = ImageType.RGB.generatePixel();
    assertTrue(pixel instanceof RgbPixel);
  }

  @Test
  public void testChannelIndexForRgb() {
    ImageType imageType = ImageType.RGB;
    assertEquals(0, imageType.colorChannels.indexOf(ColorChannel.RED));
    assertEquals(1, imageType.colorChannels.indexOf(ColorChannel.GREEN));
    assertEquals(2, imageType.colorChannels.indexOf(ColorChannel.BLUE));
  }

}