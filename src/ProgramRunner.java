import java.util.Scanner;

import ime.Controller.ImageProcessingController;
import ime.Controller.ScriptController;

public class ProgramRunner {

  public static void main(String[] args) {
    ImageProcessingController controller = new ScriptController(new Scanner(System.in));
    controller.execute();
  }

}
