package ime.controller.commands;

import ime.model.ImageRepository;

public class Compress extends AbstractCommand {
  @Override
  public String go(String[] tokens, ImageRepository imageRepository) {
    try {
      validateTokenCount(4, tokens.length);
      Integer compressionPercentage = Integer.parseInt(tokens[1]);
      String imageName = tokens[2];
      String newImage = tokens[3];
      imageRepository.compress(imageName, newImage, compressionPercentage);
      return messageSenderHelper(tokens[0], imageName, newImage);
    } catch (IllegalArgumentException e) {
      return e.getMessage();
    }
  }
}
