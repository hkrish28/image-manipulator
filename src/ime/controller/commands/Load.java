package ime.controller.commands;

import java.io.IOException;

import ime.model.ImageRepository;

public class Load extends AbstractCommand {
  @Override
  public String go(String[] tokens, ImageRepository imageRepository) {
    try {
      validateTokenCount(3, tokens.length);
      String path = tokens[1];
      String imageName = tokens[2];
      imageRepository.loadImage(path, imageName);
      return "Loaded successfully.";
    } catch (IOException e) {
      return e.getMessage();
    }
  }
}
