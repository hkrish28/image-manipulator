package ime.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Unit tests for the {@link ImagePixelImpl} class.
 */
public class ImagePixelImplTest {

  private ImagePixelImpl image;

  /**
   * Initialize a test image for use in other test methods.
   */

  @Before
  public void setUp() {
    // Create a test Pixel matrix
    RgbPixel[][] testPixels = new RgbPixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        testPixels[i][j] = new RgbPixel(new float[]{i * 10, j * 10, (i + j) * 10});
      }
    }
    image = new ImagePixelImpl(testPixels, ImageType.RGB);
  }


  /**
   * Test the {@link ImagePixelImpl#getWidth()} method.
   */
  @Test
  public void testGetWidth() {
    assertEquals(3, image.getWidth());
  }

  /**
   * Test the {@link ImagePixelImpl#getHeight()} method.
   */
  @Test
  public void testGetHeight() {
    assertEquals(3, image.getHeight());
  }

  /**
   * Test the {@link ImagePixelImpl#splitIntoColorChannels()} method.
   */
  @Test
  public void testSplitIntoColorChannels() {
    int expectedChannelCount = image.getChannelCount();
    assertEquals(expectedChannelCount, image.splitIntoColorChannels().size());
  }

  /**
   * Test the {@link ImagePixelImpl#brighten(float)} method.
   */
  @Test
  public void testBrighten() {
    Image brightenedImage = image.brighten(1.5f);
    float[] originalValues = image.getPixelValues(1, 1);
    float[] brightenedValues = brightenedImage.getPixelValues(1, 1);

    // Assert that the pixel values are increased by 1.5x
    assertEquals(originalValues[0] + 1.5f, brightenedValues[0], 0.001);
    assertEquals(originalValues[1] + 1.5f, brightenedValues[1], 0.001);
    assertEquals(originalValues[2] + 1.5f, brightenedValues[2], 0.001);
  }

  /**
   * Test the {@link ImagePixelImpl#brighten(float)} method.
   */
  @Test
  public void testDarken() {
    Image darkenedImage = image.brighten(-0.5f);
    float[] originalValues = image.getPixelValues(1, 1);
    float[] darkenedValues = darkenedImage.getPixelValues(1, 1);

    // Assert that the pixel values are halved (0.5x)
    assertEquals(originalValues[0] - 0.5f, darkenedValues[0], 0.001);
    assertEquals(originalValues[1] - 0.5f, darkenedValues[1], 0.001);
    assertEquals(originalValues[2] - 0.5f, darkenedValues[2], 0.001);
  }

  /**
   * Test the {@link ImagePixelImpl#combine(List)} method.
   */
  @Test
  public void testCombine() {
    float[][][] testPixelValues = {
            {{100, 50, 75}, {200, 150, 175}},
            {{50, 25, 37.5f}, {100, 75, 87.5f}}
    };

    Image testImage = new ImagePixelImpl(testPixelValues, ImageType.RGB);

    int height = testImage.getHeight();
    int width = testImage.getWidth();
    List<Image> imagesToCombine = new ArrayList<>();
    float[][][] image1Values = {
            {{0, 0, 0}, {50, 50, 50}},
            {{0, 0, 0}, {25, 25, 25}}
    };
    Image image1 = new ImagePixelImpl(image1Values, ImageType.RGB);
    imagesToCombine.add(image1);

    float[][][] image2Values = {
            {{255, 255, 255}, {100, 100, 100}},
            {{255, 255, 255}, {200, 200, 200}}
    };
    Image image2 = new ImagePixelImpl(image2Values, ImageType.RGB);
    imagesToCombine.add(image2);

    Image combinedImage = testImage.combine(imagesToCombine);

    assertEquals(height, combinedImage.getHeight());
    assertEquals(width, combinedImage.getWidth());
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        float[] combinedPixelValues = combinedImage.getPixelValues(row, col);

        float[] expectedPixelValues = new float[combinedPixelValues.length];
        expectedPixelValues[0] = testPixelValues[row][col][0];
        for (int k = 1; k < expectedPixelValues.length; k++) {
          expectedPixelValues[k] = imagesToCombine.get(k - 1).getPixelValues(row, col)[k];
        }

        for (int channel = 0; channel < expectedPixelValues.length; channel++) {
          assertEquals(expectedPixelValues[channel], combinedPixelValues[channel], 0.001f);
        }
      }
    }
  }

  /**
   * Test the {@link ImagePixelImpl#blur()} method.
   */
  @Test
  public void testBlur() {

    float[][][] testPixelValues = {
            {{100, 50, 75}, {200, 150, 175}},

            {{100, 25, 50}, {75, 35, 22}}
    };

    Image testImage = new ImagePixelImpl(testPixelValues, ImageType.RGB);

    float[][] blurFilter = {
            {1 / 16f, 1 / 8f, 1 / 16f},
            {1 / 8f, 1 / 4f, 1 / 8f},
            {1 / 16f, 1 / 8f, 1 / 16f}
    };

    int height = testImage.getHeight();
    int width = testImage.getWidth();

    // Create the expected blurred image by applying the filter to the test image
    float[][][] expectedPixelValues = {

            {{67.187f, 36.562f, 48.25f}, {78.125f, 49.682f, 59}},
            {{59.375f, 26.25f, 35.55f}, {62.5f, 33.75f, 38.3125f}}
    };

    Image blurredImage = testImage.blur();

    // Check if the dimensions match
    assertEquals(height, blurredImage.getHeight());
    assertEquals(width, blurredImage.getWidth());

    // Compare the pixel values in the blurred image with the expected values
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        float[] expectedValues = expectedPixelValues[row][col];
        float[] blurredValues = blurredImage.getPixelValues(row, col);

        // Check if the pixel values match within a small tolerance
        for (int channel = 0; channel < 3; channel++) {
          assertEquals(expectedValues[channel], blurredValues[channel], 0.1f);
        }
      }
    }
  }

  /**
   * Test the {@link ImagePixelImpl#sharpen()} method.
   */
  @Test
  public void testSharpen() {
    // Create a test image with known pixel values
    float[][][] testPixelValues = {
            {{100, 50, 75}, {200, 150, 175}},
            {{50, 25, 37.5f}, {100, 75, 87.5f}}
    };

    Image testImage = new ImagePixelImpl(testPixelValues, ImageType.RGB);

    float[][] sharpenFilter = {
            {-1 / 8, -1 / 8, -1 / 8, -1 / 8, -1 / 8},
            {-1 / 8, 1 / 4, 1 / 4, 1 / 4, -1 / 8},
            {-1 / 8, 1 / 4, 1, 1 / 4, -1 / 8},
            {-1 / 8, 1 / 4, 1 / 4, 1 / 4, -1 / 8},
            {-1 / 8, -1 / 8, -1 / 8, -1 / 8, -1 / 8}
    };

    int height = testImage.getHeight();
    int width = testImage.getWidth();

    // Create the expected sharpened image by applying the filter to the test image
    float[][][] expectedPixelValues = {
            {{187.5f, 112.5f, 150f}, {255.0f, 187.5f, 225f}},
            {{150f, 93.75f, 121.875f}, {187.5f, 131.25f, 159.375f}}
    };

    // Get the sharpened image
    Image sharpenedImage = testImage.sharpen();

    // Check if the dimensions match
    assertEquals(height, sharpenedImage.getHeight());
    assertEquals(width, sharpenedImage.getWidth());

    // Compare the pixel values in the sharpened image with the expected values
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        float[] expectedValues = expectedPixelValues[row][col];
        float[] sharpenedValues = sharpenedImage.getPixelValues(row, col);

        // Check if the pixel values match within a small tolerance
        for (int channel = 0; channel < 3; channel++) {
          assertEquals(expectedValues[channel], sharpenedValues[channel], 0.1f);
        }
      }
    }
  }

  /**
   * Test the {@link ImagePixelImpl#flipHorizontally()} method.
   */
  @Test
  public void testFlipHorizontally() {
    Image flippedImage = image.flipHorizontally();

    int height = image.getHeight();
    int width = image.getWidth();

    // Check if the dimensions (height and width) remain the same after flipping
    assertEquals(height, flippedImage.getHeight());
    assertEquals(width, flippedImage.getWidth());

    // Compare the pixel values before and after flipping horizontally
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        float[] originalPixelValues = image.getPixelValues(row, col);
        float[] flippedPixelValues = flippedImage.getPixelValues(row, width - col - 1);
        Assert.assertArrayEquals(originalPixelValues, flippedPixelValues, 0.001f);
      }
    }
  }

  /**
   * Test the {@link ImagePixelImpl#flipVertically()} method.
   */
  @Test
  public void testFlipVertically() {
    Image flippedImage = image.flipVertically();
    int height = image.getHeight();
    int width = image.getWidth();

    // Check if the dimensions (height and width) remain the same after flipping
    assertEquals(height, flippedImage.getHeight());
    assertEquals(width, flippedImage.getWidth());

    // Compare the pixel values before and after flipping vertically
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        float[] originalPixelValues = image.getPixelValues(row, col);
        float[] flippedPixelValues = flippedImage.getPixelValues(height - row - 1, col);
        Assert.assertArrayEquals(originalPixelValues, flippedPixelValues, 0.001f);
      }
    }
  }

  /**
   * Test the {@link ImagePixelImpl#getIntensityImage()} method.
   */
  @Test
  public void testGetIntensityImage() {
    // Create a test image with known pixel values
    float[][][] testPixelValues = {
            {{100, 50, 75}, {200, 150, 175}},
            {{50, 25, 37.5f}, {100, 75, 87.5f}}
    };

    Image testImage = new ImagePixelImpl(testPixelValues, ImageType.RGB);

    // Calculate the expected intensity values for the test image
    float[][][] expectedIntensities = {
            {{75, 75, 75}, {175, 175, 175}},
            {{37.5f, 37.5f, 37.5f}, {87.5f, 87.5f, 87.5f}}
    };

    // Get the intensity image
    Image intensityImage = testImage.getIntensityImage();

    int height = testImage.getHeight();
    int width = testImage.getWidth();

    // Check if the dimensions match
    assertEquals(height, intensityImage.getHeight());
    assertEquals(width, intensityImage.getWidth());

    // Compare the intensity values in the intensityImage
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        float[] expectedIntensity = expectedIntensities[row][col];
        float[] intensityPixelValues = intensityImage.getPixelValues(row, col);
        assertArrayEquals(expectedIntensity, intensityPixelValues, 0.001f);
      }
    }
  }

  /**
   * Test the {@link ImagePixelImpl#getLumaImage()} method.
   */
  @Test
  public void testGetLumaImage() {
    // Create a test image with known pixel values
    float[][][] testPixelValues = {
            {{100, 50, 75}, {200, 150, 175}},
            {{50, 25, 37.5f}, {100, 75, 87.5f}}
    };

    Image testImage = new ImagePixelImpl(testPixelValues, ImageType.RGB);

    // Calculate the expected luma values for the test image
    float[][] expectedLumaValues = new float[testImage.getHeight()][testImage.getWidth()];

    for (int row = 0; row < testImage.getHeight(); row++) {
      for (int col = 0; col < testImage.getWidth(); col++) {
        float[] pixelValues = testImage.getPixelValues(row, col);
        // Calculate luma based on the luminance formula
        expectedLumaValues[row][col] =
                0.2126f * pixelValues[0] + 0.7152f * pixelValues[1] + 0.0722f * pixelValues[2];
      }
    }

    // Get the luma image
    Image lumaImage = testImage.getLumaImage();

    int height = testImage.getHeight();
    int width = testImage.getWidth();

    // Check if the dimensions match
    assertEquals(height, lumaImage.getHeight());
    assertEquals(width, lumaImage.getWidth());

    // Compare the luma values in the lumaImage
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        float expectedLuma = expectedLumaValues[row][col];
        float[] lumaPixelValues = lumaImage.getPixelValues(row, col);
        for (int i = 0; i < lumaPixelValues.length; i++) {
          // Check if the luma values match within a small tolerance
          assertEquals(expectedLuma, lumaPixelValues[i], 0.001f);
        }

      }
    }
  }

  /**
   * Test the {@link ImagePixelImpl#getChannelCount()} method.
   */
  @Test
  public void testGetChannelCount() {

    float[][][] testPixelValues = {
            {{100, 50, 75}, {200, 150, 175}},
            {{50, 25, 37.5f}, {100, 75, 87.5f}}
    };

    Image testImage = new ImagePixelImpl(testPixelValues, ImageType.RGB);
    int channelCount = testImage.getChannelCount();
    int expectedChannelCount = 3;
    assertEquals(expectedChannelCount, channelCount);
  }

  /**
   * Test the {@link ImagePixelImpl#getValueImage()} method.
   */
  @Test
  public void testGetValueImage() {
    // initialise test image.
    float[][][] testPixelValues = {
            {{100, 50, 75}, {200, 150, 175}},
            {{50, 25, 37.5f}, {100, 75, 87.5f}}
    };

    Image testImage = new ImagePixelImpl(testPixelValues, ImageType.RGB);

    // Calculate the expected grayscale intensity values for the test image.
    float[][][] expectedGrayscaleValues = {
            {{100, 100, 100}, {200, 200, 200}},
            {{50, 50, 50}, {100, 100, 100}}
    };

    // Get the grayscale intensity image
    Image valueImage = testImage.getValueImage();

    int height = testImage.getHeight();
    int width = testImage.getWidth();

    // Check if the dimensions match
    assertEquals(height, valueImage.getHeight());
    assertEquals(width, valueImage.getWidth());

    // Compare the grayscale intensity values in the valueImage
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        float[] expectedIntensity = expectedGrayscaleValues[row][col];
        float[] valuePixelValues = valueImage.getPixelValues(row, col);

        // Check if the grayscale intensity values match
        assertArrayEquals(expectedIntensity, valuePixelValues, 0.001f);
      }
    }
  }

  /**
   * test to check invalid pixel inputs.
   */
  @Test
  public void testInvalidPixelValues() {
    // initialise test image.
    float[][][] testPixelValues = {
            {{100, -75, 87.5f}}
    };
    float[][][] testPixelValues2 = {
            {{300, 50, 75}},
    };

    //Image testImage = new ImagePixelImpl(testPixelValues,ImageType.RGB);
    assertThrows(IllegalArgumentException.class, () ->
            new ImagePixelImpl(testPixelValues, ImageType.RGB));
    assertThrows(IllegalArgumentException.class, () ->
            new ImagePixelImpl(testPixelValues2, ImageType.RGB));
  }


  /**
   * Test the {@link ImagePixelImpl#getSepia()} method.
   */

  @Test
  public void testGetSepia() {

    float[][][] testPixelValues = {
            {{100, 50, 75}, {200, 150, 175}}
    };

    Image testImage = new ImagePixelImpl(testPixelValues, ImageType.RGB);
    float[][] sepiaMatrix = {
            {0.393f, 0.769f, 0.189f},
            {0.349f, 0.686f, 0.168f},
            {0.272f, 0.534f, 0.131f}
    };
    float[][][] expected = {{{91.925f, 81.8f, 63.725f}, {227.025f, 202.1f, 157.425f}}};

    int height = testImage.getHeight();
    int width = testImage.getWidth();

    // Create the expected Sepia image
    float[][][] expectedPixelValues = new float[height][width][3];

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        for (int channel = 0; channel < 3; channel++) {
          float sum = 0;
          for (int c = 0; c < 3; c++) {
            sum += testPixelValues[row][col][c] * sepiaMatrix[channel][c];
          }
          expectedPixelValues[row][col][channel] = Math.min(255, Math.max(0, sum));
        }
      }
    }

    Image sepiaImage = testImage.getSepia();

    // Check if the dimensions match
    assertEquals(height, sepiaImage.getHeight());
    assertEquals(width, sepiaImage.getWidth());

    // Compare the pixel values in the Sepia image with the expected values
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        float[] expectedValues = expectedPixelValues[row][col];
        float[] sepiaValues = sepiaImage.getPixelValues(row, col);

        // Check if the pixel values match within a small tolerance
        for (int channel = 0; channel < 3; channel++) {
          assertEquals(expectedValues[channel], sepiaValues[channel], 0.001f);
        }
      }
    }
  }

  /**
   * Test the {@link ImagePixelImpl#getRedComponent()}  method.
   */
  @Test
  public void testToRedChannel() {
    // Create a test image with known pixel values
    float[][][] testPixelValues = {
            {{100, 50, 75}, {200, 150, 175}},
            {{50, 25, 37.5f}, {100, 75, 87.5f}}
    };

    Image testImage = new ImagePixelImpl(testPixelValues, ImageType.RGB);

    int height = testImage.getHeight();
    int width = testImage.getWidth();

    Image redChannelImage = testImage.getRedComponent();

    // Check if the dimensions match
    assertEquals(height, redChannelImage.getHeight());
    assertEquals(width, redChannelImage.getWidth());

    // Compare the pixel values in the Red channel image with the expected values
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        float[] originalValues = testPixelValues[row][col];
        float[] redChannelValues = redChannelImage.getPixelValues(row, col);
        assertEquals(originalValues[0], redChannelValues[0], 0.001f);

        // Check that the Green and Blue channel values are set to 0
        assertEquals(0.0f, redChannelValues[1], 0.001f);
        assertEquals(0.0f, redChannelValues[2], 0.001f);
      }
    }
  }

  /**
   * Test the {@link ImagePixelImpl#getGreenComponent()} method.
   */
  @Test
  public void testToGreenChannel() {
    // Create a test image with known pixel values
    float[][][] testPixelValues = {
            {{100, 50, 75}, {200, 150, 175}},
            {{50, 25, 37.5f}, {100, 75, 87.5f}}
    };

    Image testImage = new ImagePixelImpl(testPixelValues, ImageType.RGB);

    int height = testImage.getHeight();
    int width = testImage.getWidth();

    // Get the Green channel image
    Image greenChannelImage = testImage.getGreenComponent();

    // Check if the dimensions match
    assertEquals(height, greenChannelImage.getHeight());
    assertEquals(width, greenChannelImage.getWidth());

    // Compare the pixel values in the Green channel image with the expected values
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        float[] originalValues = testPixelValues[row][col];
        float[] greenChannelValues = greenChannelImage.getPixelValues(row, col);

        // Check if the Green channel values match
        assertEquals(originalValues[1], greenChannelValues[1], 0.001f);

        // Check that the Red and Blue channel values are set to 0
        assertEquals(0.0f, greenChannelValues[0], 0.001f);
        assertEquals(0.0f, greenChannelValues[2], 0.001f);
      }
    }
  }

  /**
   * Test the {@link ImagePixelImpl#getBlueComponent()} method.
   */
  @Test
  public void testToBlueChannel() {
    // Create a test image with known pixel values
    float[][][] testPixelValues = {
            {{100, 50, 75}, {200, 150, 175}},
            {{50, 25, 37.5f}, {100, 75, 87.5f}}
    };

    Image testImage = new ImagePixelImpl(testPixelValues, ImageType.RGB);

    int height = testImage.getHeight();
    int width = testImage.getWidth();

    // Get the Blue channel image
    Image blueChannelImage = testImage.getBlueComponent();

    // Check if the dimensions match
    assertEquals(height, blueChannelImage.getHeight());
    assertEquals(width, blueChannelImage.getWidth());

    // Compare the pixel values in the Blue channel image with the expected values
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        float[] originalValues = testPixelValues[row][col];
        float[] blueChannelValues = blueChannelImage.getPixelValues(row, col);

        // Check if the Blue channel values match
        assertEquals(originalValues[2], blueChannelValues[2], 0.001f);

        // Check that the Red and Green channel values are set to 0
        assertEquals(0.0f, blueChannelValues[0], 0.001f);
        assertEquals(0.0f, blueChannelValues[1], 0.001f);
      }
    }
  }

  /**
   * Test for an exception when combining channels with an invalid input.
   */
  @Test
  public void testCombineExc() {
    // Create test images for the color channels
    Image redChannel = new ImagePixelImpl(new float[][][]{
            {{100, 0, 0}, {200, 0, 0}},
            {{50, 0, 0}, {100, 0, 0}}
    }, ImageType.RGB);

    Image greenChannel = new ImagePixelImpl(new float[][][]{
            {{0, 50, 0}, {0, 150, 0}},
            {{0, 25, 0}, {0, 75, 0}}
    }, ImageType.RGB);

    Image blueChannel = new ImagePixelImpl(new float[][][]{
            {{0, 0, 75}, {0, 0, 175}},
            {{0, 0, 37.5f}, {0, 0, 87.5f}}
    }, ImageType.RGB);

    List<Image> colorChannels = new ArrayList<>();
    assertThrows(IllegalArgumentException.class, () -> redChannel.combine(colorChannels));
    colorChannels.add(greenChannel);
    assertThrows(IllegalArgumentException.class, () -> redChannel.combine(colorChannels));
    colorChannels.add(blueChannel);
    Image combinedImage = redChannel.combine(colorChannels);
    // Create the expected combined image
    Image expectedCombinedImage = new ImagePixelImpl(new float[][][]{
            {{100, 50, 75}, {200, 150, 175}},
            {{50, 25, 37.5f}, {100, 75, 87.5f}}
    }, ImageType.RGB);


  }


}