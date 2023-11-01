import java.util.Scanner;

import ime.controller.ControllerImpl;
import ime.controller.ImageProcessingController;
import ime.model.FileHandlerProviderImpl;
import ime.model.ImageRepositoryImpl;
import ime.view.ViewImpl;

public class ProgramRunner {

  public static void main(String[] args) {
    ImageProcessingController controller = new ControllerImpl(new Scanner(System.in),
            new ViewImpl(System.out), new ImageRepositoryImpl(new FileHandlerProviderImpl()));
    controller.execute();
  }

}
