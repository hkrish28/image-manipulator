package ime.Controller;

import static org.junit.Assert.assertEquals;

import ime.MockImgRepo;
import ime.View.View;
import ime.View.ViewImpl;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;

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
  public void testInvalidTokenForLoad() {

    ImageProcessingController controller = new ControllerImpl(new Scanner("load\nsave\nexit"), view,
        mockImgRepo);
    controller.execute();
    assertEquals("", mockImgRepo.getLogger());
    assertEquals("Please enter the command to run: \n"
        + "Invalid number of tokens passed for the given command\n"
        + "Invalid number of tokens passed for the given command\n"
        + "Please enter the command to run:", outputStream.toString().trim());
  }

  @Test
  public void testInvalidImageForLoad() {
    ImageProcessingController controller = new ControllerImpl(
        new Scanner("load invalidImageName.ppm destImage"
            + "\nexit"), view,
        mockImgRepo);
    controller.execute();
    assertEquals("loadImage called invalidImageName.ppm and destImage passed", mockImgRepo.getLogger());
    assertEquals("Please enter the command to run: \n"
        + "Image Repository failed\n"
        + "Please enter the command to run:", outputStream.toString().trim());
  }

  @Test
  public void testValidImage(){
    mockImgRepo.setFailureFlag(false);
    ImageProcessingController controller = new ControllerImpl(
        new Scanner("load invalidImageName.ppm destImage"
            + "\nexit"), view,
        mockImgRepo);
    controller.execute();
    assertEquals("loadImage called invalidImageName.ppm and destImage passed", mockImgRepo.getLogger());
    assertEquals("Please enter the command to run: \n"
        + "Loaded successfully.\n"
        + "Please enter the command to run:", outputStream.toString().trim());
  }


}