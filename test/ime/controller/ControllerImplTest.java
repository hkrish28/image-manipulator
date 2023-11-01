package ime.controller;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import ime.MockImgRepo;
import ime.view.View;
import ime.view.ViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class contains the unit tests for {@link ControllerImpl}
 */
public class ControllerImplTest {

  MockImgRepo mockImgRepo;
  OutputStream outputStream;
  PrintStream printStream;
  View view;

  public ControllerImplTest() {
    mockImgRepo = new MockImgRepo();
    outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(new ByteArrayOutputStream());
    view = new ViewImpl(printStream);
  }

  @Before
  public void setUp() throws IOException {
    outputStream = new ByteArrayOutputStream();
    printStream = new PrintStream(outputStream);
    view = new ViewImpl(printStream);
    mockImgRepo.setFailureFlag(true);
    mockImgRepo.clearLogger();
  }

  @Test
  public void testInvalidToken() {

    ImageProcessingController controller = new ControllerImpl(
            new Scanner("load\nsave\nsplit\nhorizontal-flip\nvertical-flip\nbrighten\nexit"), view,
            mockImgRepo);
    controller.execute();
    assertEquals("", mockImgRepo.getLogger());
    assertEquals("Please enter the command to run: \n"
            + "Invalid number of tokens passed for the given command\n"
            + "Please enter the command to run: \n"
            + "Invalid number of tokens passed for the given command\n"
            + "Please enter the command to run: \n"
            + "Please enter the command to run: \n"
            + "Invalid number of tokens passed for the given command\n"
            + "Please enter the command to run: \n"
            + "Invalid number of tokens passed for the given command\n"
            + "Please enter the command to run: \n"
            + "Invalid number of tokens passed for the given command\n"
            + "Please enter the command to run:", outputStream.toString().trim());
  }

  /*  @Test(timeout=1000)
    public void testTimeoutWithoutExit() {

      ImageProcessingController controller = new ControllerImpl(new Scanner("load\nsave\nsplit\nhorizontal-flip\nvertical-flip\nbrighten"), view,
          mockImgRepo);
      controller.execute();
      assertEquals("", mockImgRepo.getLogger());
      assertEquals("Please enter the command to run: \n"
          + "Invalid number of tokens passed\n for the given command\n"
          + "Please enter the command to run: \n"
          + "Invalid number of tokens passed\n for the given command\n"
          + "Please enter the command to run: \n"
          + "Please enter the command to run: \n"
          + "Invalid number of tokens passed\n for the given command\n"
          + "Please enter the command to run: \n"
          + "Invalid number of tokens passed\n for the given command\n"
          + "Please enter the command to run: \n"
          + "Invalid number of tokens passed\n for the given command\n"
          + "Please enter the command to run:", outputStream.toString().trim());
    }*/
// exit
  @Test
  public void testInvalidImageForLoad() {
    ImageProcessingController controller = new ControllerImpl(
            new Scanner("load invalidImageName destImage"
                    + "\nblur invalidImageName destImage" + "\nsharpen invalidImageName destImage"
                    + "\nhorizontal-flip invalidImageName destImage"
                    + "\nvertical-flip invalidImageName destImage"
                    + "\nintensity-component invalidImageName destImage"
                    + "\nvalue-component invalidImageName destImage"
                    + "\nluma-component invalidImageName destImage"
                    + "\nred-component invalidImageName destImage"
                    + "\ngreen-component invalidImageName destImage"
                    + "\nblue-component invalidImageName destImage"
                    + "\nsepia invalidImageName destImage" + "\nexit"), view,
            mockImgRepo);
    controller.execute();
    assertEquals(
            "loadImage called invalidImageName and destImage passed\n" +
                    "blurImage called invalidImageName and destImage passed\n" +
                    "sharpenImage called invalidImageName and destImage passed\n" +
                    "loadImage called invalidImageName and destImage passed\n" +
                    "loadImage called invalidImageName and destImage passed\n" +
                    "loadImage called invalidImageName and destImage passed\n" +
                    "loadImage called invalidImageName and destImage passed\n" +
                    "loadImage called invalidImageName and destImage passed\n" +
                    "loadImage called invalidImageName and destImage passed\n" +
                    "loadImage called invalidImageName and destImage passed\n" +
                    "loadImage called invalidImageName and destImage passed\n" +
                    "loadImage called invalidImageName and destImage passed\n",
            mockImgRepo.getLogger());
    assertEquals("Please enter the command to run: \n"
            + "Image Repository failed\n"
            + "Please enter the command to run: \n"
            + "Source Name invalid\n"
            + "Please enter the command to run: \n"
            + "Source Name invalid\n"
            + "Please enter the command to run: \n"
            + "Source Name invalid\n"
            + "Please enter the command to run: \n"
            + "Source Name invalid\n"
            + "Please enter the command to run: \n"
            + "Source Name invalid\n"
            + "Please enter the command to run: \n"
            + "Source Name invalid\n"
            + "Please enter the command to run: \n"
            + "Source Name invalid\n"
            + "Please enter the command to run: \n"
            + "Source Name invalid\n"
            + "Please enter the command to run: \n"
            + "Source Name invalid\n"
            + "Please enter the command to run: \n"
            + "Source Name invalid\n"
            + "Please enter the command to run: \n"
            + "Source Name invalid\n"
            + "Please enter the command to run:", outputStream.toString().trim());
  }
  //brighten accepting float


  @Test
  public void testInvalidImageForSave() {
    ImageProcessingController controller = new ControllerImpl(
            new Scanner("save invalidImageName.ppm destImage"
                    + "\nexit"), view,
            mockImgRepo);
    controller.execute();
    assertEquals("saveImage called invalidImageName.ppm and destImage passed\n",
            mockImgRepo.getLogger());
    assertEquals("Please enter the command to run: \n"
            + "Image Repository failed\n"
            + "Please enter the command to run:", outputStream.toString().trim());
  }

  @Test
  public void testInvalidImageForSplit() {
    ImageProcessingController controller = new ControllerImpl(
            new Scanner("rgb-split invalidImageName.ppm red green blue"
                    + "\nexit"), view,
            mockImgRepo);
    controller.execute();
    mockImgRepo.getLogger();
    assertEquals("splitImage called invalidImageName.ppm and [red, green, blue] passed\n",
            mockImgRepo.getLogger());
    assertEquals("Please enter the command to run: \n"
            + "Image Repository failed\n"
            + "Please enter the command to run:", outputStream.toString().trim());
  }

  @Test
  public void testInvalidImageForCombine() {
    ImageProcessingController controller = new ControllerImpl(
            new Scanner("rgb-combine invalidImageName.ppm red green blue"
                    + "\nexit"), view,
            mockImgRepo);
    controller.execute();
    mockImgRepo.getLogger();
    assertEquals("combineImage called [red, green, blue] and invalidImageName.ppm passed\n",
            mockImgRepo.getLogger());
    assertEquals("Please enter the command to run: \n"
            + "Image Repository failed\n"
            + "Please enter the command to run:", outputStream.toString().trim());
  }

  @Test
  public void testInvalidImageForBrighten() {
    ImageProcessingController controller = new ControllerImpl(
            new Scanner("brighten 122 invalidImageName.ppm destImage "
                    + "\nexit"), view,
            mockImgRepo);
    controller.execute();
    mockImgRepo.getLogger();
    assertEquals("brightenImage called invalidImageName.ppm and destImage passed\n",
            mockImgRepo.getLogger());
    assertEquals("Please enter the command to run: \n"
            + "Source Name invalid\n"
            + "Please enter the command to run:", outputStream.toString().trim());
  }

  //test when string is given in place of float.
  @Test
  public void testInvalidFloatForBrighten() {
    ImageProcessingController controller = new ControllerImpl(
            new Scanner("brighten number invalidImageName.ppm destImage "
                    + "\nexit"), view,
            mockImgRepo);
    controller.execute();
    mockImgRepo.getLogger();
    assertEquals("", mockImgRepo.getLogger());
    assertEquals("Please enter the command to run: \n"
            + "brightness command expects a number following the command\n"
            + "Please enter the command to run:", outputStream.toString().trim());
  }


  @Test
  public void testValidImageLoad() {
    mockImgRepo.setFailureFlag(false);
    ImageProcessingController controller = new ControllerImpl(
            new Scanner("load invalidImageName.ppm destImage"
                    + "\nsave invalidImageName.ppm destImage"
                    + "\nrgb-split invalidImageName.ppm red green blue"
                    + "\nrgb-combine invalidImageName.ppm red green blue"
                    + "\nbrighten 122 invalidImageName.ppm destImage"
                    + "\nblur invalidImageName.ppm destImage" + "\nsharpen invalidImageName.ppm destImage"
                    + "\nhorizontal-flip invalidImageName.ppm destImage"
                    + "\nvertical-flip invalidImageName.ppm destImage"
                    + "\nintensity-component invalidImageName.ppm destImage"
                    + "\nvalue-component invalidImageName.ppm destImage"
                    + "\nluma-component invalidImageName.ppm destImage"
                    + "\nred-component invalidImageName.ppm destImage"
                    + "\ngreen-component invalidImageName.ppm destImage"
                    + "\nblue-component invalidImageName.ppm destImage"
                    + "\nsepia invalidImageName.ppm destImage" + "\nexit"), view,
            mockImgRepo);
    controller.execute();
    assertEquals(
            "loadImage called invalidImageName.ppm and destImage passed\nsaveImage called invalidImageName.ppm and destImage passed\nsplitImage called invalidImageName.ppm and [red, green, blue] passed\ncombineImage called [red, green, blue] and invalidImageName.ppm passed\nbrightenImage called invalidImageName.ppm and destImage passed\nblurImage called invalidImageName.ppm and destImage passed\nsharpenImage called invalidImageName.ppm and destImage passed\nloadImage called invalidImageName.ppm and destImage passed\nloadImage called invalidImageName.ppm and destImage passed\nloadImage called invalidImageName.ppm and destImage passed\nloadImage called invalidImageName.ppm and destImage passed\nloadImage called invalidImageName.ppm and destImage passed\nloadImage called invalidImageName.ppm and destImage passed\nloadImage called invalidImageName.ppm and destImage passed\nloadImage called invalidImageName.ppm and destImage passed\nloadImage called invalidImageName.ppm and destImage passed\n",
            mockImgRepo.getLogger());
    assertEquals("Please enter the command to run: \n"
            + "Loaded successfully.\n"
            + "Please enter the command to run: \n"
            + "Saved successfully.\n"
            + "Please enter the command to run: \n"
            + "Images split.\n"
            + "Please enter the command to run: \n"
            + "Images combined.\n"
            + "Please enter the command to run: \n"
            + "Brightened successfully.\n"
            + "Please enter the command to run: \n"
            + "Please enter the command to run: \n"
            + "Please enter the command to run: \n"
            + "Horizontally flipped.\n"
            + "Please enter the command to run: \n"
            + "Please enter the command to run: \n"
            + "Please enter the command to run: \n"
            + "Please enter the command to run: \n"
            + "Please enter the command to run: \n"
            + "Red channel obtained.\n"
            + "Please enter the command to run: \n"
            + "Green channel obtained.\n"
            + "Please enter the command to run: \n"
            + "Please enter the command to run: \n"
            + "Please enter the command to run:", outputStream.toString().trim());
  }


}