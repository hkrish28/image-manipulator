package ime.controller.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.function.BiConsumer;

import ime.controller.ControllerImpl;
import ime.model.ImageRepository;
import ime.view.View;

public class Run extends AbstractCommand {

  private final View view;

  public Run(View view) {
    super(2);
    this.view = view;
  }

  @Override
  protected String extractTokensAndInvokeMethod(String[] tokens, ImageRepository imageRepository) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(tokens[1]));
      new ControllerImpl(new Scanner(reader), view, imageRepository, false).execute();
      return "Script file execution complete.";
    } catch (IOException e) {
      return "Invalid script location/file.";
    }
  }

  @Override
  protected BiConsumer<String, String> consumerMethod(String[] tokens, ImageRepository imageRepository) {
    return null;
  }


}
