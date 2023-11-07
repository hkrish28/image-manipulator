package ime.controller.commands;

import ime.model.ImageRepository;

public class ValueGreyscale extends AbstractCommand {
  @Override
  public String go(String[] tokens, ImageRepository imageRepository) {
    try {
      validateTokenCount(3, tokens.length);
      String imageName = tokens[1];
      String newImage = tokens[2];
      imageRepository.toValueGreyScale(imageName, newImage);
      return messageSenderHelper(tokens[0], imageName, newImage);
    } catch (IllegalArgumentException e) {
      return e.getMessage();
    }
  }
}
