package ime.controller.commands;

import java.util.Arrays;
import java.util.List;

import ime.model.ImageRepository;

public class Combine extends AbstractCommand {
  @Override
  public String go(String[] tokens, ImageRepository imageRepository) {
    try {
      validateTokenCount(5, tokens.length);

      String destImage = tokens[1];
      List<String> colorChannelsImages = Arrays.asList(tokens).subList(2, tokens.length);
      imageRepository.combineImages(colorChannelsImages, destImage);
      return messageSenderHelper(tokens[0], colorChannelsImages.toString(),destImage);
    } catch (IllegalArgumentException e) {
      return e.getMessage();
    }
  }
}
