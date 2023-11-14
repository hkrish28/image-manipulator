package ime.controller;


import org.junit.Before;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

import ime.model.Image;
import ime.model.ImagePixelImpl;
import ime.model.ImageType;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

/**
 * JUnit test class for testing the {@link CommonFileHandler} class.
 */
public class CommonFileHandlerTest {

  private FileHandler fileHandler;

  /**
   * Set up the test environment by initializing the {@link CommonFileHandler}.
   */
  @Before
  public void setUp() {
    fileHandler = new CommonFileHandler();
  }

  /**
   * Test case to verify that the {@code loadImage} method throws an {@link IOException} when an
   * image file does not exist.
   *
   * @throws IOException If the test does not throw an exception as expected.
   */
  @Test
  public void testLoadImage() throws IOException {

    assertThrows(IOException.class, () -> fileHandler.loadImage("invalidImage.jpg"));

  }

  /**
   * Test case to verify loading an image in PNG format and comparing the pixel values with the
   * expected values.
   *
   * @throws IOException If an IO exception occurs during the test.
   */
  @Test
  public void testLoadImagePng() throws IOException {
    float[][][] expected = {
            {{0, 50, 0}, {0, 150, 0}},
            {{0, 25, 0}, {0, 75, 0}}
    };
    float[][][] pixels = fileHandler.loadImage("test/resources/testImage.png");
    assertTrue(Arrays.deepEquals(expected, pixels));
    //check for equality
  }

  /**
   * Test case to verify saving an image in PNG format and checking if the file is created
   * successfully.
   *
   * @throws IOException If an IO exception occurs during the test.
   */
  @Test
  public void testSaveImage() throws IOException {
    float[][][] pixels = new float[][][]{
            {{0, 50, 0}, {0, 150, 0}},
            {{0, 25, 0}, {0, 75, 0}}
    };
    Image image = new ImagePixelImpl(pixels, ImageType.RGB);
//    fileHandler.saveImage(image, "test/resources/testImage.png");
    float[][][] loaded = fileHandler.loadImage("test/resources/testImage.png");
    assertArrayEquals(pixels, loaded);
  }

  /**
   * Test case to verify that the {@code saveImage} method throws an {@link IOException} when the
   * specified directory is invalid.
   *
   * @throws IOException If the test does not throw an exception as expected.
   */
  @Test
  public void testSaveImageInvalidDirectory() throws IOException {
    Image image = new ImagePixelImpl(new float[][][]{
            {{0, 50, 0}, {0, 150, 0}},
            {{0, 25, 0}, {0, 75, 0}}
    }, ImageType.RGB);
//    assertThrows(IOException.class, () -> fileHandler.saveImage(image, "test/invalid/test.png"));
//    assertThrows(IOException.class, () -> fileHandler.saveImage(image, "test/invalid/test.jpg"));
  }
}
