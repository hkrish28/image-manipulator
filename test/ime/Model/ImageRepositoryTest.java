package ime.Model;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import ime.MockFileHandler;
import ime.MockFileHandlerProvider;
import java.io.IOException;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
public class ImageRepositoryTest {

  private MockFileHandler mockFileHandler;
  private MockFileHandlerProvider mockFileHandlerProvider;

  /**
   * constructor to declare the mock classes.
   */
  public ImageRepositoryTest() {
    mockFileHandler = new MockFileHandler();
    mockFileHandlerProvider = new MockFileHandlerProvider(mockFileHandler);
  }

  /**
   * setup method to set the failure flags to false.
   */
  @Before
  public void setUp() {
    mockFileHandlerProvider.setFailureFlag(false);
    mockFileHandler.setFailureFlag(false);
  }

  /**
   * test to check when load works.
   */
  @Test
  public void testLoadWorksWhenFileHandlerWorks() {

    ImageRepository imageRepository = new ImageRepositoryImpl(mockFileHandlerProvider);
    try {
      imageRepository.loadImage("fileName", "ImageName");
    } catch (IOException e) {
      fail("Not supposed to fail");
    }
  }

  /**
   * test when invalid file format is passed.
   */
  @Test
  public void testLoadThrowsExceptionWhenInvalidFileFormat() {
    mockFileHandlerProvider.setFailureFlag(true);
    ImageRepository imageRepository = new ImageRepositoryImpl(mockFileHandlerProvider);
    assertThrows(IOException.class, () -> imageRepository.loadImage("fileName", "ImageName"));
  }

  /**
   * test when invalid file is passed to load.
   */
  @Test
  public void testLoadThrowsExceptionWhenInvalidFile() {
    mockFileHandler.setFailureFlag(true);
    ImageRepository imageRepository = new ImageRepositoryImpl(mockFileHandlerProvider);
    assertThrows(IOException.class, () -> imageRepository.loadImage("fileName", "ImageName"));

  }

  /**
   * test and invalid file and invalid file format is passed.
   */
  @Test
  public void testLoadThrowsExceptionWhenInvalidFileFormatAndInvalidFile() {
    mockFileHandler.setFailureFlag(true);
    mockFileHandlerProvider.setFailureFlag(true);
    ImageRepository imageRepository = new ImageRepositoryImpl(mockFileHandlerProvider);
    assertThrows(IOException.class, () -> imageRepository.loadImage("fileName", "ImageName"));
  }

  /**
   * test save for invalid dirrectory.
   */
  @Test
  public void testSaveThrowsExceptionWhenInvalidDirectory() {
    ImageRepository imageRepository = new ImageRepositoryImpl(mockFileHandlerProvider);
    try {
      imageRepository.loadImage("fileName", "ImageName");
    } catch (IOException e) {
      fail("Not supposed to fail");
    }
    ;
    mockFileHandler.setFailureFlag(true);
    assertThrows(IOException.class, () -> imageRepository.saveImage("filePath", "ImageName"));
  }

  /**
   * test save for invalif file format.
   */
  @Test
  public void testSaveThrowsExceptionWhenInvalidFileFormat() {
    ImageRepository imageRepository = new ImageRepositoryImpl(mockFileHandlerProvider);
    try {
      imageRepository.loadImage("fileName", "ImageName");
    } catch (IOException e) {
      fail("Not supposed to fail");
    }
    ;

    mockFileHandlerProvider.setFailureFlag(true);
    assertThrows(IOException.class, () -> imageRepository.saveImage("filePath", "ImageName"));
  }

  /**
   * test save when invalid file and invalid format is passed.
   */
  @Test
  public void testSaveThrowsExceptionWhenInvalidFileFormatAndInvalidDirectory() {
    ImageRepository imageRepository = new ImageRepositoryImpl(mockFileHandlerProvider);
    try {
      imageRepository.loadImage("fileName", "ImageName");
    } catch (IOException e) {
      fail("Not supposed to fail");
    }
    ;
    mockFileHandler.setFailureFlag(true);
    mockFileHandlerProvider.setFailureFlag(true);
    assertThrows(IOException.class, () -> imageRepository.saveImage("filePath", "ImageName"));
  }

  /**
   * test save wghen valid arguments are passed.
   */
  @Test
  public void testSaveSuccessfullyWhenValidFileFormatAndFile() {
    ImageRepository imageRepository = new ImageRepositoryImpl(mockFileHandlerProvider);
    try {
      imageRepository.loadImage("fileName", "ImageName");
    } catch (IOException e) {
      fail("Not supposed to fail");
    }
    try {
      imageRepository.saveImage("filePath", "ImageName");
    } catch (IOException e) {
      fail("Not supposed to throw exception");
    }
  }

  /**
   * test load when filehandler completes load.
   */
  @Test
  public void testLoadSuccessWhenFileHandlerCompletesLoad() {
    ImageRepository imageRepository = new ImageRepositoryImpl(mockFileHandlerProvider);
    try {
      imageRepository.loadImage("fileName", "ImageName");
    } catch (IOException e) {
      fail("Not supposed to fail");
    }
    ;
    assertTrue("Value needs to be present",
        imageRepository.isImagePresent("ImageName"));
  }

  /**
   * test all operations when image is not present.
   */
  @Test
  public void testOperationsForImageNotPresent() {
    ImageRepository imageRepository = new ImageRepositoryImpl(mockFileHandlerProvider);

    assertThrows(IllegalArgumentException.class,
        () -> imageRepository.saveImage("fileName", "ImageName"));
    assertThrows(IllegalArgumentException.class,
        () -> imageRepository.brightenImage("fileName", "ImageName", 5));
    assertThrows(IllegalArgumentException.class,
        () -> imageRepository.combineImages(Arrays.asList("red", "green", "blue"), "ImageName"));
    assertThrows(IllegalArgumentException.class,
        () -> imageRepository.blurImage("fileName", "ImageName"));
    assertThrows(IllegalArgumentException.class,
        () -> imageRepository.sharpenImage("fileName", "ImageName"));
    assertThrows(IllegalArgumentException.class,
        () -> imageRepository.flipImageHorizontally("fileName", "ImageName"));
    assertThrows(IllegalArgumentException.class,
        () -> imageRepository.flipImageVertically("fileName", "ImageName"));
    assertThrows(IllegalArgumentException.class,
        () -> imageRepository.toIntensityGreyScale("fileName", "ImageName"));
    assertThrows(IllegalArgumentException.class,
        () -> imageRepository.toLumaGreyScale("fileName", "ImageName"));
    assertThrows(IllegalArgumentException.class,
        () -> imageRepository.toValueGreyScale("fileName", "ImageName"));
    assertThrows(IllegalArgumentException.class,
        () -> imageRepository.toSepiaImage("fileName", "ImageName"));
    assertThrows(IllegalArgumentException.class,
        () -> imageRepository.toRedChannelImage("fileName", "ImageName"));
    assertThrows(IllegalArgumentException.class,
        () -> imageRepository.toGreenChannelImage("fileName", "ImageName"));
    assertThrows(IllegalArgumentException.class,
        () -> imageRepository.toBlueChannelImage("fileName", "ImageName"));
    assertThrows(IllegalArgumentException.class,
        () -> imageRepository.splitImageIntoColorChannels("fileName",
            Arrays.asList("red", "green", "blue")));
  }

  /**
   * Test the 'brightenImage' method in the 'ImageRepository' implementation.
   * Ensures that the method loads an image, applies brightness adjustment, and checks if the brightened image exists.
   *
   * @throws IOException If there's an issue during image loading.
   */
  @Test
  public void testbrighten() throws IOException {
    ImageRepository imageRepository = new ImageRepositoryImpl(mockFileHandlerProvider);
    try {
      imageRepository.loadImage("fileName", "ImageName");
    } catch (IOException e) {
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
  public void testBlur() throws IOException {
    ImageRepository imageRepository = new ImageRepositoryImpl(mockFileHandlerProvider);
    try {
      imageRepository.loadImage("fileName", "ImageName");
    } catch (IOException e) {
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
  public void testSharpen() throws IOException {
    ImageRepository imageRepository = new ImageRepositoryImpl(mockFileHandlerProvider);
    try {
      imageRepository.loadImage("fileName", "ImageName");
    } catch (IOException e) {
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
  public void testflipImageHorizontally() throws IOException {
    ImageRepository imageRepository = new ImageRepositoryImpl(mockFileHandlerProvider);
    try {
      imageRepository.loadImage("fileName", "ImageName");
    } catch (IOException e) {
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
  public void testflipImageVertically() throws IOException {
    ImageRepository imageRepository = new ImageRepositoryImpl(mockFileHandlerProvider);
    try {
      imageRepository.loadImage("fileName", "ImageName");
    } catch (IOException e) {
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
  public void testIntensityGreyScale() throws IOException {
    ImageRepository imageRepository = new ImageRepositoryImpl(mockFileHandlerProvider);
    try {
      imageRepository.loadImage("fileName", "ImageName");
    } catch (IOException e) {
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
  public void testLumaGreyScale() throws IOException {
    ImageRepository imageRepository = new ImageRepositoryImpl(mockFileHandlerProvider);
    try {
      imageRepository.loadImage("fileName", "ImageName");
    } catch (IOException e) {
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
  public void testValueGreyScale() throws IOException {
    ImageRepository imageRepository = new ImageRepositoryImpl(mockFileHandlerProvider);
    try {
      imageRepository.loadImage("fileName", "ImageName");
    } catch (IOException e) {
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
  public void testSepia() throws IOException {
    ImageRepository imageRepository = new ImageRepositoryImpl(mockFileHandlerProvider);
    try {
      imageRepository.loadImage("fileName", "ImageName");
    } catch (IOException e) {
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
  public void testRedChannelImage() throws IOException {
    ImageRepository imageRepository = new ImageRepositoryImpl(mockFileHandlerProvider);
    try {
      imageRepository.loadImage("fileName", "ImageName");
    } catch (IOException e) {
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
  public void testGreenChannelImage() throws IOException {
    ImageRepository imageRepository = new ImageRepositoryImpl(mockFileHandlerProvider);
    try {
      imageRepository.loadImage("fileName", "ImageName");
    } catch (IOException e) {
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
  public void testBlueChannelImage() throws IOException {
    ImageRepository imageRepository = new ImageRepositoryImpl(mockFileHandlerProvider);
    try {
      imageRepository.loadImage("fileName", "ImageName");
    } catch (IOException e) {
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
