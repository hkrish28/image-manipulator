import java.util.Scanner;

import ime.controller.ControllerImpl;
import ime.controller.ImageProcessingController;

import ime.model.ImageRepository;
import ime.model.ImageRepositoryImpl;
import ime.view.View;
import ime.view.ViewImpl;

/**
 * This class contains the main method to be run to start the application.
 */
public class ProgramRunner {

  /**
   * This main method requires no arguments to be run and passes the program execution control to
   * the controller after providing it the Model and View classes for its instantiation.
   */
  public static void main(String[] args) {
    Scanner controllerInput = new Scanner(System.in);
    View view = new ViewImpl(System.out);
    ImageRepository imageRepository = new ImageRepositoryImpl();

    ImageProcessingController controller = new ControllerImpl(controllerInput, view,
            imageRepository);

    controller.execute();
  }

}
