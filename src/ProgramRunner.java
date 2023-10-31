import java.util.Scanner;

import ime.Controller.ImageProcessingController;
import ime.Controller.ControllerImpl;

public class ProgramRunner {

  public static void main(String[] args) {
    ImageProcessingController controller = new ControllerImpl(new Scanner(System.in));
    controller.execute();
  }

}
