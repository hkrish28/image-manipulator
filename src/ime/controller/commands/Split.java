package ime.controller.commands;

import java.util.Arrays;
import java.util.List;

import ime.model.ImageRepository;

public class Split extends AbstractCommand {
  @Override
  public String go(String[] tokens, ImageRepository imageRepository) {
    try {
      validateTokenCount(5, tokens.length);

      String srcImage = tokens[1];
      List<String> colorChannelsImages = Arrays.asList(tokens).subList(2, tokens.length);
      imageRepository.splitImageIntoColorChannels(srcImage, colorChannelsImages);
      return messageSenderHelper(tokens[0], srcImage,colorChannelsImages.toString());
    } catch (IllegalArgumentException e) {
      return e.getMessage();
    }
  }
}
