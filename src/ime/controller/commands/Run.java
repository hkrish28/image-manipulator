package ime.controller.commands;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.function.BiConsumer;

import ime.controller.ControllerImpl;
import ime.model.ImageRepository;
import ime.view.View;

/**
 * This class extends the AbstractCommand class and represents a specific command that runs a
 * script file.
 */
public class Run extends AbstractCommand {

  private final View view;

  /**
   * Constructor to initialize the fields that also takes in the view which will be used to
   * create a new controller object.
   */
  public Run(View view) {
    super(2);
    this.view = view;
  }

  @Override
  protected String extractTokensAndInvokeMethod(String[] tokens, ImageRepository imageRepository) {
    try {
      File reader = new File(tokens[1]);
      new ControllerImpl(new Scanner(reader), view, imageRepository, false).execute();
      return "Script file execution complete.";
    } catch (IOException e) {
      return "Invalid script location/file.";
    }
  }

}
