import ime.Model.FileHandlerProviderImpl;
import java.util.Scanner;

import ime.Controller.ControllerImpl;
import ime.Controller.ImageProcessingController;
import ime.Model.ImageRepositoryImpl;
import ime.View.ViewImpl;

public class ProgramRunner {

  public static void main(String[] args) {
    ImageProcessingController controller = new ControllerImpl(new Scanner(System.in),
            new ViewImpl(System.out), new ImageRepositoryImpl(new FileHandlerProviderImpl()));
    controller.execute();
  }

}
