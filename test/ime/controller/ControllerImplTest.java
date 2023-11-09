package ime.controller;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import ime.view.View;
import ime.view.ViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class contains the unit tests for the controller {@link ControllerImpl}.
 */
public class ControllerImplTest {

  MockImgRepo mockImgRepo;
  OutputStream outputStream;
  PrintStream printStream;
  View view;

  /**
   * Constructor initializing the Mock objects that will be used for testing of controller class.
   */
  public ControllerImplTest() {
    mockImgRepo = new MockImgRepo();
    outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(new ByteArrayOutputStream());
    view = new ViewImpl(printStream);
  }

  /**
   * This method is used to clear all the loggers and to set the default behaviours for the mock
   * classes.
   */
  @Before
  public void setUp() {
    outputStream = new ByteArrayOutputStream();
    printStream = new PrintStream(outputStream);
    view = new ViewImpl(printStream);
    mockImgRepo.setFailureFlag(true);
    mockImgRepo.clearLogger();
  }

  @Test
  public void testInvalidToken() {

    ImageProcessingController controller = new ControllerImpl(
            new Scanner("load\nsave\nsplit\nhorizontal-flip\nvertical-flip\nbrighten\nexit"),
            view, mockImgRepo);
    controller.execute();
    assertEquals("", mockImgRepo.getLogger());
    assertEquals("Please enter the command to run: \n"
            + "Invalid number of tokens passed for the given command\n"
            + "Please enter the command to run: \n"
            + "Invalid number of tokens passed for the given command\n"
            + "Please enter the command to run: \n"
            + "Command not found\n"
            + "Please enter the command to run: \n"
            + "Invalid number of tokens passed for the given command\n"
            + "Please enter the command to run: \n"
            + "Invalid number of tokens passed for the given command\n"
            + "Please enter the command to run: \n"
            + "Invalid number of tokens passed for the given command\n"
            + "Please enter the command to run:", outputStream.toString().trim());
  }

  @Test
  public void testRunInvalidScript() {

    ImageProcessingController controller =
            new ControllerImpl(new Scanner("run testscript.txt\nexit"), view, mockImgRepo);
    controller.execute();
    assertEquals("", mockImgRepo.getLogger());
    String expectedView = "Please enter the command to run: \n" +
            "Invalid script location/file.\n" +
            "Please enter the command to run:";
    assertEquals(expectedView, outputStream.toString().trim());
  }

  @Test
  public void testRunValidScript() {

    ImageProcessingController controller =
            new ControllerImpl(new Scanner("run test/resources/testscript.txt\nexit"),
                    view, mockImgRepo);
    controller.execute();
    assertEquals("loadImage called images/test.ppm and test passed\n",
            mockImgRepo.getLogger());
    String expectedView = "Please enter the command to run: \n" +
            "Image Repository failed\n" +
            "Exiting with no more commands\n" +
            "Script file execution complete.\n" +
            "Please enter the command to run:";
    assertEquals(expectedView, outputStream.toString().trim());
  }


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
                    "horizontal flip called invalidImageName and destImage passed\n" +
                    "vertical flip called invalidImageName and destImage passed\n" +
                    "intensity gs called invalidImageName and destImage passed\n" +
                    "value gs called invalidImageName and destImage passed\n" +
                    "luma gs called invalidImageName and destImage passed\n" +
                    "red channel called invalidImageName and destImage passed\n" +
                    "green channel called invalidImageName and destImage passed\n" +
                    "blue channel called invalidImageName and destImage passed\n" +
                    "sepia called invalidImageName and destImage passed\n",
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

  @Test
  public void testInvalidImageForSave() {
    ImageProcessingController controller = new ControllerImpl(
            new Scanner("save invalidName.ppm destImage"
                    + "\nexit"), view,
            mockImgRepo);
    controller.execute();
    assertEquals("saveImage called invalidName.ppm and destImage passed\n",
            mockImgRepo.getLogger());
    assertEquals("Please enter the command to run: \n"
            + "Image Repository failed\n"
            + "Please enter the command to run:", outputStream.toString().trim());
  }

  @Test
  public void testInvalidImageForSplit() {
    ImageProcessingController controller = new ControllerImpl(
            new Scanner("rgb-split invalidName.ppm red green blue"
                    + "\nexit"), view,
            mockImgRepo);
    controller.execute();
    mockImgRepo.getLogger();
    assertEquals("splitImage called invalidName.ppm and [red, green, blue] passed\n",
            mockImgRepo.getLogger());
    assertEquals("Please enter the command to run: \n"
            + "Image Repository failed\n"
            + "Please enter the command to run:", outputStream.toString().trim());
  }

  @Test
  public void testInvalidImageForCombine() {
    ImageProcessingController controller = new ControllerImpl(
            new Scanner("rgb-combine invalidName.ppm red green blue"
                    + "\nexit"), view,
            mockImgRepo);
    controller.execute();
    mockImgRepo.getLogger();
    assertEquals("combineImage called [red, green, blue] and invalidName.ppm passed\n",
            mockImgRepo.getLogger());
    assertEquals("Please enter the command to run: \n"
            + "Image Repository failed\n"
            + "Please enter the command to run:", outputStream.toString().trim());
  }

  @Test
  public void testInvalidImageForBrighten() {
    ImageProcessingController controller = new ControllerImpl(
            new Scanner("brighten 122 invalidName.ppm destImage "
                    + "\nexit"), view,
            mockImgRepo);
    controller.execute();
    mockImgRepo.getLogger();
    assertEquals("brightenImage called invalidName.ppm and destImage passed\n",
            mockImgRepo.getLogger());
    assertEquals("Please enter the command to run: \n"
            + "Source Name invalid\n"
            + "Please enter the command to run:", outputStream.toString().trim());
  }

  //test when string is given in place of float.
  @Test
  public void testInvalidFloatForBrighten() {
    ImageProcessingController controller = new ControllerImpl(
            new Scanner("brighten number invalidName.ppm destImage "
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
  public void testValidImageSplitCombine() {
    mockImgRepo.setFailureFlag(false);
    ImageProcessingController controller = new ControllerImpl(
            new Scanner("rgb-split invalidName red green blue"
                    + "\nrgb-combine invalidName red green blue\nexit"), view, mockImgRepo);
    controller.execute();
    assertEquals("splitImage called invalidName and [red, green, blue] passed\n" +
                    "combineImage called [red, green, blue] and invalidName passed\n",
            mockImgRepo.getLogger());
    assertEquals("Please enter the command to run: \n" +
            "rgb-split operation completed successfully for invalidName & put in " +
            "[red, green, blue]\nPlease enter the command to run: \n" +
            "rgb-combine operation completed successfully for [red, green, blue] & put in " +
            "invalidName\nPlease enter the command to run:", outputStream.toString().trim());
  }

  @Test
  public void testValidImageOperations() {
    mockImgRepo.setFailureFlag(false);
    ImageProcessingController controller = new ControllerImpl(
            new Scanner("brighten 122 invalidName.ppm destImage"
                    + "\nblur invalidName.ppm destImage" +
                    "\nsharpen invalidName.ppm destImage\nexit"), view, mockImgRepo);
    controller.execute();
    assertEquals("brightenImage called invalidName.ppm and destImage passed\n" +
            "blurImage called invalidName.ppm and destImage passed\n" +
            "sharpenImage called invalidName.ppm and destImage passed\n", mockImgRepo.getLogger());
    assertEquals("Please enter the command to run: \n" +
            "brighten operation completed successfully for invalidName.ppm & put in destImage\n" +
            "Please enter the command to run: \n" +
            "blur operation completed successfully for invalidName.ppm & put in destImage\n" +
            "Please enter the command to run: \n" +
            "sharpen operation completed successfully for invalidName.ppm & put in destImage\n" +
            "Please enter the command to run:", outputStream.toString().trim());
  }

  @Test
  public void testValidImageGreyscales() {
    mockImgRepo.setFailureFlag(false);
    ImageProcessingController controller = new ControllerImpl(
            new Scanner("\nintensity-component invalidName.ppm destImage"
                    + "\nvalue-component invalidName.ppm destImage"
                    + "\nluma-component invalidName.ppm destImage\nexit"), view, mockImgRepo);
    controller.execute();
    assertEquals("intensity gs called invalidName.ppm and destImage passed\n" +
                    "value gs called invalidName.ppm and destImage passed\n" +
                    "luma gs called invalidName.ppm and destImage passed\n",
            mockImgRepo.getLogger());
    assertEquals("Please enter the command to run: \n" +
            "Please enter the command to run: \n" +
            "intensity-component operation completed successfully for invalidName.ppm "
            + "& put in destImage\nPlease enter the command to run: \n" +
            "value-component operation completed successfully for invalidName.ppm" +
            " & put in destImage\nPlease enter the command to run: \n" +
            "luma-component operation completed successfully for invalidName.ppm " +
            "& put in destImage\nPlease enter the command to run:", outputStream.toString().trim());
  }

  @Test
  public void testValidImageFlipsSepia() {
    mockImgRepo.setFailureFlag(false);
    ImageProcessingController controller = new ControllerImpl(
            new Scanner("\nhorizontal-flip invalidName.ppm destImage"
                    + "\nvertical-flip invalidName.ppm destImage"
                    + "\nsepia invalidName.ppm destImage" + "\nexit"), view, mockImgRepo);
    controller.execute();
    assertEquals("horizontal flip called invalidName.ppm and destImage passed\n" +
            "vertical flip called invalidName.ppm and destImage passed\n" +
            "sepia called invalidName.ppm and destImage passed\n", mockImgRepo.getLogger());
    assertEquals("Please enter the command to run: \nPlease enter the command to run: \n" +
            "horizontal-flip operation completed successfully for invalidName.ppm " +
            "& put in destImage\nPlease enter the command to run: \n" +
            "vertical-flip operation completed successfully for invalidName.ppm " +
            "& put in destImage\nPlease enter the command to run: \n" +
            "sepia operation completed successfully for invalidName.ppm & put in destImage\n" +
            "Please enter the command to run:", outputStream.toString().trim());
  }

  @Test
  public void testValidImageComponents() {
    mockImgRepo.setFailureFlag(false);
    ImageProcessingController controller = new ControllerImpl(
            new Scanner("\nred-component invalidName destImage"
                    + "\ngreen-component invalidName destImage"
                    + "\nblue-component invalidName destImage\nexit"), view, mockImgRepo);
    controller.execute();
    assertEquals(
            "red channel called invalidName and destImage passed\n" +
                    "green channel called invalidName and destImage passed\n" +
                    "blue channel called invalidName and destImage passed\n",
            mockImgRepo.getLogger());
    assertEquals("Please enter the command to run: \n" +
            "Please enter the command to run: \n" +
            "red-component operation completed successfully for invalidName " +
            "& put in destImage\nPlease enter the command to run: \n" +
            "green-component operation completed successfully for invalidName " +
            "& put in destImage\nPlease enter the command to run: \n" +
            "blue-component operation completed successfully for invalidName " +
            "& put in destImage\nPlease enter the command to run:", outputStream.toString().trim());
  }

  @Test
  public void testValidImageLoadSave() {
    mockImgRepo.setFailureFlag(false);
    ImageProcessingController controller = new ControllerImpl(
            new Scanner("load invalidName.ppm destImage"
                    + "\nsave invalidName.ppm destImage\nexit"), view, mockImgRepo);
    controller.execute();
    assertEquals("loadImage called invalidName.ppm and destImage passed\n" +
                    "saveImage called invalidName.ppm and destImage passed\n",
            mockImgRepo.getLogger());
    assertEquals("Please enter the command to run: \n" +
            "Loaded successfully.\n" +
            "Please enter the command to run: \n" +
            "Saved successfully.\n" +
            "Please enter the command to run:", outputStream.toString().trim());
  }


}