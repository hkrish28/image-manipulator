package ime.model;

import org.junit.Test;

import java.awt.*;

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
    assertEquals(0, imageType.colorChannels.indexOf(Color.RED));
    assertEquals(1, imageType.colorChannels.indexOf(Color.GREEN));
    assertEquals(2, imageType.colorChannels.indexOf(Color.BLUE));
  }

}