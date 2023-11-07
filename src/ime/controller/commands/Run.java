package ime.controller.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import ime.controller.ControllerImpl;
import ime.controller.ImageProcessingController;
import ime.model.ImageRepository;
import ime.view.View;

public class Run extends AbstractCommand {

  private final View view;

  public Run(View view) {
    super();
    this.view = view;
  }

  @Override
  public String go(String[] tokens, ImageRepository imageRepository) {
    try {
      validateTokenCount(2, tokens.length);
      BufferedReader reader = new BufferedReader(new FileReader(tokens[1]));
      new ControllerImpl(new Scanner(reader), view, imageRepository).execute();
      return "Script file execution complete.";
    } catch (IllegalArgumentException e) {
      return e.getMessage();
    } catch (IOException e) {
      return "Invalid script location/file.";
    }
  }

}
