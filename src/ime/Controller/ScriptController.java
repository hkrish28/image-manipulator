package ime.Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import ime.Model.ImageRepository;
import ime.Model.ImageRepositoryImpl;
import ime.View.View;
import ime.View.ViewImpl;

/**
 * The `controller` class implements the ImageProcessingController interface and provides
 * functionality to execute image processing commands specified in a script file.
 */

public class ScriptController extends ImageProcessingAbstractController {

  /**
   * Constructs a new controller instance with an empty image files map.
   */
  public ScriptController(Scanner in) {
    super(in);
    this.imgRepo = new ImageRepositoryImpl();
    this.in = in;
    this.view = new ViewImpl(System.out);

  }

  @Override
  public void execute() {
    view.displayMessage("Please enter the command script file: ");
    String scriptFileName = in.next();
    processScriptFile(scriptFileName);
    view.displayMessage("Script file execution complete.");
  }

  public void processScriptFile(String scriptFileName) {
    try (BufferedReader reader = new BufferedReader(new FileReader(scriptFileName))) {
      String line;
      while ((line = reader.readLine()) != null) {
        // Trim leading and trailing whitespaces
        line = line.trim();
        // Ignore lines that start with #
        if (!(line.startsWith("#") || line.isEmpty())) {
          // Call the controller's executeScript method with the current line
          executeCommand(line);
        }
      }
    } catch (IOException e) {
      view.displayMessage("Invalid script location/file." + e.getMessage());
    }
  }
}


