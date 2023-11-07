package ime.controller.commands;

import java.io.IOException;

import ime.model.ImageRepository;

public class Sharpen extends AbstractCommand {
  @Override
  public String go(String[] tokens, ImageRepository imageRepository) {
    try {
      validateTokenCount(3, tokens.length);
      String imageName = tokens[2];
      String fileName = tokens[1];
      imageRepository.saveImage(fileName, imageName);
      return "Saved successfully.";
    } catch (IOException e) {
      return e.getMessage();
    }
  }
}
