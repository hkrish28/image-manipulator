package ime.model;

import org.junit.Before;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import ime.controller.MockFileHandler;
import ime.controller.MockFileHandlerProvider;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Class that tests for {@link ImageRepositoryImpl}.
 */
public class ImageRepositoryTest {

  private final float[][][] testPixels;


  /**
   * constructor to declare the mock classes.
   */
  public ImageRepositoryTest() {

    testPixels = new float[][][]{{{1, 1, 1}, {1, 1, 1,}}, {{2, 2, 2}, {2, 3, 4}}};
  }

  /**
   * test to check when load works.
   */
  @Test
  public void testLoadWorksWhenFileHandlerWorks() {

    ImageRepository imageRepository = new ImageRepositoryImpl();
    try {
      imageRepository.loadImage(testPixels, "ImageName");
    } catch (IllegalArgumentException e) {
      fail("Not supposed to fail");
    }
  }

  /**
   * test when invalid file format is passed.
   */
  @Test
  public void testLoadThrowsExceptionWhenInvalidFileFormat() {
    ImageRepository imageRepository = new ImageRepositoryImpl();
    assertThrows(IllegalArgumentException.class, () ->
            imageRepository.loadImage(testPixels, "ImageName"));
  }

  /**
   * test when invalid file is passed to load.
   */
  @Test
  public void testLoadThrowsExceptionWhenInvalidFile() {
    ImageRepository imageRepository = new ImageRepositoryImpl();
    assertThrows(IllegalArgumentException.class, () ->
            imageRepository.loadImage(testPixels, "ImageName"));

  }

  /**
   * test and invalid file and invalid file format is passed.
   */
  @Test
  public void testLoadThrowsExceptionWhenInvalidFileFormatAndInvalidFile() {
    ImageRepository imageRepository = new ImageRepositoryImpl();
    assertThrows(IllegalArgumentException.class, () ->
            imageRepository.loadImage(testPixels, "ImageName"));
  }

  /**
   * test save for invalid dirrectory.
   */
  @Test
  public void testSaveThrowsExceptionWhenInvalidDirectory() {
    ImageRepository imageRepository = new ImageRepositoryImpl();
    try {
      imageRepository.loadImage(testPixels, "ImageName");
    } catch (IllegalArgumentException e) {
      fail("Not supposed to fail");
    }
    assertThrows(IllegalArgumentException.class, () ->
            imageRepository.getImage("ImageName"));
  }

  /**
   * test save for invalif file format.
   */
  @Test
  public void testSaveThrowsExceptionWhenInvalidFileFormat() {
    ImageRepository imageRepository = new ImageRepositoryImpl();
    try {
      imageRepository.loadImage(testPixels, "ImageName");
    } catch (IllegalArgumentException e) {
      fail("Not supposed to fail");
    }

    assertThrows(IllegalArgumentException.class, () ->
            imageRepository.getImage("ImageName"));
  }

  /**
   * test save when invalid file and invalid format is passed.
   */
  @Test
  public void testSaveThrowsExceptionWhenInvalidFileFormatAndInvalidDirectory() {
    ImageRepository imageRepository = new ImageRepositoryImpl();
    try {
      imageRepository.loadImage(testPixels, "ImageName");
    } catch (IllegalArgumentException e) {
      fail("Not supposed to fail");
    }

    assertThrows(IllegalArgumentException.class, () ->
            imageRepository.getImage("ImageName"));
  }

  /**
   * test save wghen valid arguments are passed.
   */
  @Test
  public void testSaveSuccessfullyWhenValidFileFormatAndFile() {
    ImageRepository imageRepository = new ImageRepositoryImpl();
    try {
      imageRepository.loadImage(testPixels, "ImageName");
    } catch (IllegalArgumentException e) {
      fail("Not supposed to fail");
    }
    try {
      imageRepository.getImage("ImageName");
    } catch (IllegalArgumentException e) {
      fail("Not supposed to throw exception");
    }
  }

  /**
   * test load when filehandler completes load.
   */
  @Test
  public void testLoadSuccessWhenFileHandlerCompletesLoad() {
    ImageRepository imageRepository = new ImageRepositoryImpl();
    try {
      imageRepository.loadImage(testPixels, "ImageName");
    } catch (IllegalArgumentException e) {
      fail("Not supposed to fail");
    }
    assertTrue("Value needs to be present",
            imageRepository.isImagePresent("ImageName"));
  }

  /**
   * test all operations when image is not present.
   */
  @Test
  public void testOperationsForImageNotPresent() {
    ImageRepository imageRepository = new ImageRepositoryImpl();

    assertThrows(IllegalArgumentException.class, () ->
            imageRepository.getImage("ImageName"));
    assertThrows(IllegalArgumentException.class, () ->
            imageRepository.brightenImage("fileName", "ImageName", 5));
    assertThrows(IllegalArgumentException.class, () ->
            imageRepository.combineImages(Arrays.asList("red", "green", "blue"), "ImageName"));
    assertThrows(IllegalArgumentException.class, () ->
            imageRepository.blurImage("fileName", "ImageName"));
    assertThrows(IllegalArgumentException.class, () ->
            imageRepository.sharpenImage("fileName", "ImageName"));
    assertThrows(IllegalArgumentException.class, () ->
            imageRepository.flipImageHorizontally("fileName", "ImageName"));
    assertThrows(IllegalArgumentException.class, () ->
            imageRepository.flipImageVertically("fileName", "ImageName"));
    assertThrows(IllegalArgumentException.class, () ->
            imageRepository.toIntensityGreyScale("fileName", "ImageName"));
    assertThrows(IllegalArgumentException.class, () ->
            imageRepository.toLumaGreyScale("fileName", "ImageName"));
    assertThrows(IllegalArgumentException.class, () ->
            imageRepository.toValueGreyScale("fileName", "ImageName"));
    assertThrows(IllegalArgumentException.class, () ->
            imageRepository.toSepiaImage("fileName", "ImageName"));
    assertThrows(IllegalArgumentException.class, () ->
            imageRepository.toRedChannelImage("fileName", "ImageName"));
    assertThrows(IllegalArgumentException.class, () ->
            imageRepository.toGreenChannelImage("fileName", "ImageName"));
    assertThrows(IllegalArgumentException.class, () ->
            imageRepository.toBlueChannelImage("fileName", "ImageName"));
    assertThrows(IllegalArgumentException.class, () ->
            imageRepository.splitImageIntoColorChannels("fileName",
                    Arrays.asList("red", "green", "blue")));
  }

  /**
   * Test the 'brightenImage' method in the 'ImageRepository' implementation.
   * Ensures that the method loads an image, applies brightness adjustment, and checks if the
   * brightened image exists.
   *
   * @throws IllegalArgumentException If there's an issue during image loading.
   */
  @Test
  public void testbrighten() throws IllegalArgumentException {
    ImageRepository imageRepository = new ImageRepositoryImpl();
    try {
      imageRepository.loadImage(testPixels, "ImageName");
    } catch (IllegalArgumentException e) {
      fail("Not supposed to fail");
    }
    try {
      imageRepository.brightenImage("ImageName", "brightenedImage", 10);
    } catch (NullPointerException e) {
      fail("Not supposed to fail");
    }
    assertTrue("brighten not found ",
            imageRepository.isImagePresent("brightenedImage"));
  }

  @Test
  public void testBlur() throws IllegalArgumentException {
    ImageRepository imageRepository = new ImageRepositoryImpl();
    try {
      imageRepository.loadImage(testPixels, "ImageName");
    } catch (IllegalArgumentException e) {
      fail("Not supposed to fail");
    }
    try {
      imageRepository.blurImage("ImageName", "blurImage");
    } catch (NullPointerException e) {
      fail("Not supposed to fail");
    }
    assertTrue("blurred image not found ",
            imageRepository.isImagePresent("blurImage"));
  }

  @Test
  public void testSharpen() throws IllegalArgumentException {
    ImageRepository imageRepository = new ImageRepositoryImpl();
    try {
      imageRepository.loadImage(testPixels, "ImageName");
    } catch (IllegalArgumentException e) {
      fail("Not supposed to fail");
    }
    try {
      imageRepository.sharpenImage("ImageName", "sharpenImage");
    } catch (NullPointerException e) {
      fail("Not supposed to fail");
    }
    assertTrue("sharpened image not found ",
            imageRepository.isImagePresent("sharpenImage"));
  }

  @Test
  public void testflipImageHorizontally() throws IllegalArgumentException {
    ImageRepository imageRepository = new ImageRepositoryImpl();
    try {
      imageRepository.loadImage(testPixels, "ImageName");
    } catch (IllegalArgumentException e) {
      fail("Not supposed to fail");
    }
    try {
      imageRepository.flipImageHorizontally("ImageName", "flippedImage");
    } catch (NullPointerException e) {
      fail("Not supposed to fail");
    }
    assertTrue("horizontally flipped image not found ",
            imageRepository.isImagePresent("flippedImage"));
  }

  @Test
  public void testflipImageVertically() throws IllegalArgumentException {
    ImageRepository imageRepository = new ImageRepositoryImpl();
    try {
      imageRepository.loadImage(testPixels, "ImageName");
    } catch (IllegalArgumentException e) {
      fail("Not supposed to fail");
    }
    try {
      imageRepository.flipImageVertically("ImageName", "flippedImage");
    } catch (NullPointerException e) {
      fail("Not supposed to fail");
    }
    assertTrue("vertically flipped image not found ",
            imageRepository.isImagePresent("flippedImage"));
  }

  @Test
  public void testIntensityGreyScale() throws IllegalArgumentException {
    ImageRepository imageRepository = new ImageRepositoryImpl();
    try {
      imageRepository.loadImage(testPixels, "ImageName");
    } catch (IllegalArgumentException e) {
      fail("Not supposed to fail");
    }
    try {
      imageRepository.toIntensityGreyScale("ImageName", "intensityImage");
    } catch (NullPointerException e) {
      fail("Not supposed to fail");
    }
    assertTrue("intensity image not found ",
            imageRepository.isImagePresent("intensityImage"));
  }

  @Test
  public void testLumaGreyScale() throws IllegalArgumentException {
    ImageRepository imageRepository = new ImageRepositoryImpl();
    try {
      imageRepository.loadImage(testPixels, "ImageName");
    } catch (IllegalArgumentException e) {
      fail("Not supposed to fail");
    }
    try {
      imageRepository.toLumaGreyScale("ImageName", "lumaImage");
    } catch (NullPointerException e) {
      fail("Not supposed to fail");
    }
    assertTrue("luma image not found ",
            imageRepository.isImagePresent("lumaImage"));
  }

  @Test
  public void testValueGreyScale() throws IllegalArgumentException {
    ImageRepository imageRepository = new ImageRepositoryImpl();
    try {
      imageRepository.loadImage(testPixels, "ImageName");
    } catch (IllegalArgumentException e) {
      fail("Not supposed to fail");
    }
    try {
      imageRepository.toValueGreyScale("ImageName", "valueImage");
    } catch (NullPointerException e) {
      fail("Not supposed to fail");
    }
    assertTrue("valued image not found ",
            imageRepository.isImagePresent("valueImage"));
  }

  @Test
  public void testSepia() throws IllegalArgumentException {
    ImageRepository imageRepository = new ImageRepositoryImpl();
    try {
      imageRepository.loadImage(testPixels, "ImageName");
    } catch (IllegalArgumentException e) {
      fail("Not supposed to fail");
    }
    try {
      imageRepository.toSepiaImage("ImageName", "sepiaImage");
    } catch (NullPointerException e) {
      fail("Not supposed to fail");
    }
    assertTrue("sepia image not found ",
            imageRepository.isImagePresent("sepiaImage"));
  }

  @Test
  public void testRedChannelImage() throws IllegalArgumentException {
    ImageRepository imageRepository = new ImageRepositoryImpl();
    try {
      imageRepository.loadImage(testPixels, "ImageName");
    } catch (IllegalArgumentException e) {
      fail("Not supposed to fail");
    }
    try {
      imageRepository.toRedChannelImage("ImageName", "redImage");
    } catch (NullPointerException e) {
      fail("Not supposed to fail");
    }
    assertTrue("red image not found ",
            imageRepository.isImagePresent("redImage"));
  }

  @Test
  public void testGreenChannelImage() throws IllegalArgumentException {
    ImageRepository imageRepository = new ImageRepositoryImpl();
    try {
      imageRepository.loadImage(testPixels, "ImageName");
    } catch (IllegalArgumentException e) {
      fail("Not supposed to fail");
    }
    try {
      imageRepository.toGreenChannelImage("ImageName", "greenImage");
    } catch (NullPointerException e) {
      fail("Not supposed to fail");
    }
    assertTrue("green image not found ",
            imageRepository.isImagePresent("greenImage"));
  }

  @Test
  public void testBlueChannelImage() throws IllegalArgumentException {
    ImageRepository imageRepository = new ImageRepositoryImpl();
    try {
      imageRepository.loadImage(testPixels, "ImageName");
    } catch (IllegalArgumentException e) {
      fail("Not supposed to fail");
    }
    try {
      imageRepository.toBlueChannelImage("ImageName", "blueImage");
    } catch (NullPointerException e) {
      fail("Not supposed to fail");
    }
    assertTrue("blue image not found ",
            imageRepository.isImagePresent("blueImage"));
  }

  //test for image present after operation

}
