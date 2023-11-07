package ime.controller.commands;

import ime.model.ImageRepository;

public class Brighten extends AbstractCommand {
  @Override
  public String go(String[] tokens, ImageRepository imageRepository) {
    try {
      validateTokenCount(4, tokens.length);
      float brightnessConstant = Float.parseFloat(tokens[1]);
      String imageName = tokens[2];
      String newImage = tokens[3];
      imageRepository.brightenImage(imageName, newImage, brightnessConstant);
      return "Brightened successfully.";
    } catch (NumberFormatException e) {
      return "brightness command expects a number following the command";
    } catch (IllegalArgumentException e) {
      return e.getMessage();
    }
  }
}
