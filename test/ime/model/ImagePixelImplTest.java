package ime.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Unit tests for the {@link ImagePixelImpl} class.
 */
public class ImagePixelImplTest {

  private final ImagePixelImpl image;

  private final ImageType imageType;
  private final float[][][] testPixels;

  /**
   * Initialize a test image for use in other test methods.
   */
  public ImagePixelImplTest() {
    // Create a test Pixel matrix
    testPixels = new float[][][]{{{0, 0, 0}, {0, 10, 10}, {0, 20, 20}},
      {{10, 0, 10}, {10, 10, 20}, {10, 20, 30}}, {{20, 0, 20}, {20, 10, 30}, {20, 20, 40}}};
    imageType = ImageType.RGB;
    image = new ImagePixelImpl(testPixels, imageType);

  }


  /**
   * Test the {@link ImagePixelImpl} constructor with illegal float[] input.
   */
  @Test
  public void testFloatConstructor() {
    float[][][] testPixels = {{{0, 0, 0}, {0, 10, 10}, {0, 20}},
      {{10, 0, 10}, {10, 20}, {10, 20, 30}}, {{20, 0, 20}, {20, 10, 30}, {20, 20, 40}}};
    assertThrows(IllegalArgumentException.class, () ->
            new ImagePixelImpl(testPixels, imageType));
  }

  /**
   * Test the {@link ImagePixelImpl} constructor with Pixel[][] input.
   */
  @Test(expected = Test.None.class)
  public void testConstructorWithPixel() {
    RgbPixel[][] testPixels = new RgbPixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        testPixels[i][j] = new RgbPixel(new float[]{i * 10, j * 10, (i + j) * 10});
      }
    }
    new ImagePixelImpl(testPixels, imageType);
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
   * Test the {@link ImagePixelImpl#getPixelValues(int, int)} ()} method.
   */
  @Test
  public void testGetPixelValues() {
    for (int i = 0; i < testPixels.length; i++) {
      for (int j = 0; j < testPixels[0].length; j++) {
        assertArrayEquals(testPixels[i][j], image.getPixelValues(i, j), 0.001f);
      }
    }
  }

  /**
   * Test the {@link ImagePixelImpl#splitIntoColorChannels()} method.
   */
  @Test
  public void testSplitIntoColorChannels() {
    int expectedChannelCount = testPixels[0][0].length;
    assertEquals(expectedChannelCount, image.splitIntoColorChannels().size());
  }

  /**
   * Test the {@link ImagePixelImpl#brighten(float)} method.
   */
  @Test
  public void testBrighten() {
    Image brightenedImage = image.brighten(1.5f);

    // Assert that the pixel values are increased by 1.5x
    for (int i = 0; i < testPixels.length; i++) {
      for (int j = 0; j < testPixels[0].length; j++) {
        float[] brightenedValues = brightenedImage.getPixelValues(i, j);
        for (int k = 0; k < testPixels[0][0].length; k++) {
          assertEquals(Math.min(255, testPixels[i][j][k] + 1.5f), brightenedValues[k], 0.01f);
        }
      }
    }
  }

  /**
   * Test the {@link ImagePixelImpl#brighten(float)} method multiple times.
   */
  @Test
  public void testBrightenImageMultiple() {
    Image brightened = image.brighten(255);
    float[] expected = new float[testPixels[0][0].length];
    Arrays.fill(expected, 255);
    for (int i = 0; i < testPixels.length; i++) {
      for (int j = 0; j < testPixels[0].length; j++) {
        assertArrayEquals(expected, brightened.getPixelValues(i, j), 0.01f);
      }
    }
    Image brightenedWhite = brightened.brighten(10);
    for (int i = 0; i < testPixels.length; i++) {
      for (int j = 0; j < testPixels[0].length; j++) {
        assertArrayEquals(expected, brightenedWhite.getPixelValues(i, j), 0.01f);
      }
    }
  }


  /**
   * Test the {@link ImagePixelImpl#brighten(float)} method with a negative value.
   */
  @Test
  public void testDarken() {
    Image darkenedImage = image.brighten(-1.5f);

    // Assert that the pixel values are increased by 1.5x
    for (int i = 0; i < testPixels.length; i++) {
      for (int j = 0; j < testPixels[0].length; j++) {
        float[] brightenedValues = darkenedImage.getPixelValues(i, j);
        for (int k = 0; k < testPixels[0][0].length; k++) {
          assertEquals(Math.max(0, testPixels[i][j][k] - 1.5f), brightenedValues[k], 0.01f);
        }
      }
    }
  }

  /**
   * Test the {@link ImagePixelImpl#brighten(float)} method with a negative value multiple times.
   */
  @Test
  public void testDarkenImageMultiple() {
    Image darkened = image.brighten(-255);
    float[] expected = new float[testPixels[0][0].length];
    Arrays.fill(expected, 0);
    for (int i = 0; i < testPixels.length; i++) {
      for (int j = 0; j < testPixels[0].length; j++) {
        assertArrayEquals(expected, darkened.getPixelValues(i, j), 0.01f);
      }
    }
    Image darkenedBlack = darkened.brighten(-10);
    for (int i = 0; i < testPixels.length; i++) {
      for (int j = 0; j < testPixels[0].length; j++) {
        assertArrayEquals(expected, darkenedBlack.getPixelValues(i, j), 0.01f);
      }
    }
  }

  /**
   * Test the {@link ImagePixelImpl#combine(List)} method for invalid input.
   */
  @Test
  public void testCombineFailWithInvalidNumberOfImages() {
    List<Image> images = new ArrayList<>();
    assertThrows(IllegalArgumentException.class, () -> image.combine(images));
    images.add(image);
    assertThrows(IllegalArgumentException.class, () -> image.combine(images));
    images.add(image);
    image.combine(images); // No error when images size is 2
    images.add(image);
    assertThrows(IllegalArgumentException.class, () -> image.combine(images));
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

    Image testImage = new ImagePixelImpl(testPixelValues, imageType);

    int height = testImage.getHeight();
    int width = testImage.getWidth();
    List<Image> imagesToCombine = new ArrayList<>();
    float[][][] image1Values = {
            {{0, 0, 0}, {50, 50, 50}},
            {{0, 0, 0}, {25, 25, 25}}
    };
    Image image1 = new ImagePixelImpl(image1Values, imageType);
    imagesToCombine.add(image1);

    float[][][] image2Values = {
            {{255, 255, 255}, {100, 100, 100}},
            {{255, 255, 255}, {200, 200, 200}}
    };
    Image image2 = new ImagePixelImpl(image2Values, imageType);
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
    Image testImage = new ImagePixelImpl(testPixelValues, imageType);

    float[][][] expectedPixelValues = {
            {{67.187f, 36.562f, 48.25f}, {78.125f, 49.687f, 59}},
            {{59.375f, 26.25f, 35.562f}, {62.5f, 33.75f, 38.3125f}}
    };
    Image blurredImage = testImage.blur();
    assertExpectedImage(testPixelValues, testImage);
    assertExpectedImage(expectedPixelValues, blurredImage);

    //Blur one more time
    float[][][] expectedPixelValuesBlurred = {
            {{37.89f, 20.742f, 26.277f}, {39.453f, 22.851f, 27.792f}},
            {{35.937f, 18.457f, 23.398f}, {37.011f, 20.214f, 24.414f}}
    };
    blurredImage = blurredImage.blur();
    assertExpectedImage(expectedPixelValuesBlurred, blurredImage);

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
    Image testImage = new ImagePixelImpl(testPixelValues, imageType);

    float[][][] expectedPixelValues = {
            {{187.5f, 112.5f, 150f}, {255.0f, 187.5f, 225f}},
            {{150f, 93.75f, 121.875f}, {187.5f, 131.25f, 159.375f}}
    };

    Image sharpenedImage = testImage.sharpen();
    assertExpectedImage(testPixelValues, testImage);
    assertExpectedImage(expectedPixelValues, sharpenedImage);

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
    assertExpectedImage(testPixels, image); //No change to original image
    // Compare the pixel values before and after flipping horizontally
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        float[] originalPixelValues = image.getPixelValues(row, col);
        float[] flippedPixelValues = flippedImage.getPixelValues(row, width - col - 1);
        Assert.assertArrayEquals(originalPixelValues, flippedPixelValues, 0.001f);
      }
    }
    //Flipping twice should bring the orignal image
    flippedImage = flippedImage.flipHorizontally();
    assertExpectedImage(testPixels, flippedImage);
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
    assertExpectedImage(testPixels, image); //No change to original image
    // Compare the pixel values before and after flipping vertically
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        float[] originalPixelValues = image.getPixelValues(row, col);
        float[] flippedPixelValues = flippedImage.getPixelValues(height - row - 1, col);
        Assert.assertArrayEquals(originalPixelValues, flippedPixelValues, 0.001f);
      }
    }
    flippedImage = flippedImage.flipVertically();
    assertExpectedImage(testPixels, flippedImage);
  }

  /**
   * Test the {@link ImagePixelImpl#getIntensityImage()} method.
   */
  @Test
  public void testGetIntensityImage() {
    float[][][] testPixelValues = {
            {{100, 50, 75}, {200, 150, 175}},
            {{50, 25, 37.5f}, {100, 75, 87.5f}}
    };

    Image testImage = new ImagePixelImpl(testPixelValues, imageType);

    float[][][] expectedIntensities = {
            {{75, 75, 75}, {175, 175, 175}},
            {{37.5f, 37.5f, 37.5f}, {87.5f, 87.5f, 87.5f}}
    };

    Image intensityImage = testImage.getIntensityImage();

    assertExpectedImage(expectedIntensities, intensityImage);
    assertExpectedImage(testPixelValues, testImage);

    intensityImage = intensityImage.getIntensityImage();
    assertExpectedImage(expectedIntensities, intensityImage); //doing twice will return same result
  }

  /**
   * Test the {@link ImagePixelImpl#getLumaImage()} method.
   */
  @Test
  public void testGetLumaImage() {
    float[][][] testPixelValues = {
            {{100, 50, 75}, {200, 150, 175}},
            {{50, 25, 37.5f}, {100, 75, 87.5f}}
    };

    Image testImage = new ImagePixelImpl(testPixelValues, imageType);

    float[][][] expectedLumaValues =
            new float[testImage.getHeight()][testImage.getWidth()][testImage.getChannelCount()];

    for (int row = 0; row < testImage.getHeight(); row++) {
      for (int col = 0; col < testImage.getWidth(); col++) {
        float[] pixelValues = testImage.getPixelValues(row, col);
        float lumaValue
                = 0.2126f * pixelValues[0] + 0.7152f * pixelValues[1] + 0.0722f * pixelValues[2];
        Arrays.fill(expectedLumaValues[row][col], lumaValue);
      }
    }

    // Get the luma image
    Image lumaImage = testImage.getLumaImage();
    assertExpectedImage(expectedLumaValues, lumaImage);
    assertExpectedImage(testPixelValues, testImage);
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

    Image testImage = new ImagePixelImpl(testPixelValues, imageType);
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

    Image testImage = new ImagePixelImpl(testPixelValues, imageType);

    // Calculate the expected grayscale intensity values for the test image.
    float[][][] expectedGrayscaleValues = {
            {{100, 100, 100}, {200, 200, 200}},
            {{50, 50, 50}, {100, 100, 100}}
    };

    // Get the grayscale intensity image
    Image valueImage = testImage.getValueImage();
    assertExpectedImage(expectedGrayscaleValues, valueImage);
    assertExpectedImage(testPixelValues, testImage);
    valueImage = valueImage.getValueImage();
    assertExpectedImage(expectedGrayscaleValues, valueImage);
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

    //Image testImage = new ImagePixelImpl(testPixelValues,imageType);
    assertThrows(IllegalArgumentException.class, () ->
            new ImagePixelImpl(testPixelValues, imageType));
    assertThrows(IllegalArgumentException.class, () ->
            new ImagePixelImpl(testPixelValues2, imageType));
  }

  /**
   * Test the {@link ImagePixelImpl#getSepia()} method.
   */
  @Test
  public void testGetSepia() {

    float[][][] testPixelValues = {
            {{100, 50, 75}, {200, 150, 175}}
    };
    Image testImage = new ImagePixelImpl(testPixelValues, imageType);
    float[][][] expected = {{{91.925f, 81.8f, 63.725f}, {227.025f, 202.1f, 157.425f}}};

    Image sepiaImage = testImage.getSepia();
    assertExpectedImage(expected, sepiaImage);
    assertExpectedImage(testPixelValues, testImage); //no change to original
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
    float[][][] expectedPixels = {
            {{100, 0, 0}, {200, 0, 0}},
            {{50, 0, 0}, {100, 0, 0}}
    };
    Image testImage = new ImagePixelImpl(testPixelValues, imageType);

    Image redChannelImage = testImage.getRedComponent();
    assertExpectedImage(expectedPixels, redChannelImage);
    assertExpectedImage(testPixelValues, testImage);
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
    float[][][] expectedPixels = {
            {{0, 50, 0}, {0, 150, 0}},
            {{0, 25, 0}, {0, 75, 0}}
    };
    Image testImage = new ImagePixelImpl(testPixelValues, imageType);
    Image greenChannelImage = testImage.getGreenComponent();

    assertExpectedImage(expectedPixels, greenChannelImage);
    assertExpectedImage(testPixelValues, testImage);
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
    float[][][] expectedPixels = {
            {{0, 0, 75}, {0, 0, 175}},
            {{0, 0, 37.5f}, {0, 0, 87.5f}}
    };
    Image testImage = new ImagePixelImpl(testPixelValues, imageType);

    Image blueChannelImage = testImage.getBlueComponent();
    assertExpectedImage(expectedPixels, blueChannelImage);
    assertExpectedImage(testPixelValues, testImage);
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
    }, imageType);

    Image greenChannel = new ImagePixelImpl(new float[][][]{
            {{0, 50, 0}, {0, 150, 0}},
            {{0, 25, 0}, {0, 75, 0}}
    }, imageType);

    Image blueChannel = new ImagePixelImpl(new float[][][]{
            {{0, 0, 75}, {0, 0, 175}},
            {{0, 0, 37.5f}, {0, 0, 87.5f}}
    }, imageType);

    List<Image> colorChannels = new ArrayList<>();
    assertThrows(IllegalArgumentException.class, () -> redChannel.combine(colorChannels));
    colorChannels.add(greenChannel);
    assertThrows(IllegalArgumentException.class, () -> redChannel.combine(colorChannels));
    colorChannels.add(blueChannel);
    Image combinedImage = redChannel.combine(colorChannels);
    float[][][] expectedCombinedImage = new float[][][]{
            {{100, 50, 75}, {200, 150, 175}},
            {{50, 25, 37.5f}, {100, 75, 87.5f}}
    };
    assertExpectedImage(expectedCombinedImage, combinedImage);
  }

  @Test
  public void testCompress(){
    float[][][] testPixels = new float[][][]{{{5,5,5},{3,3,3}},{{2,2,2},{4,4,4}}};
    ImagePixelImpl image = new ImagePixelImpl(testPixels,ImageType.RGB);

    Image compressed = image.compress(0);
    assertExpectedImage(testPixels, compressed);
//    image.invHaar(new float[][][]{{{7.071f},{2.828f},{1.0f},{0f},{1.414f},{-1.414f},{0.707f},{-2.121f}}});
  }

  private void assertExpectedImage(float[][][] expectedPixelValues, Image blurredImage) {
    assertEquals(expectedPixelValues.length, blurredImage.getHeight());
    assertEquals(expectedPixelValues[0].length, blurredImage.getWidth());

    for (int i = 0; i < expectedPixelValues.length; i++) {
      for (int j = 0; j < expectedPixelValues[0].length; j++) {
        assertArrayEquals(expectedPixelValues[i][j], blurredImage.getPixelValues(i, j), 0.01f);
      }
    }
  }

}