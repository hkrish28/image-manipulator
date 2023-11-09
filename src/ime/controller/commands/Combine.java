package ime.controller.commands;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

import ime.model.ImageRepository;

public class Combine extends AbstractCommand {

  public Combine() {
    super(5, 0, 1);
  }

  @Override
  protected BiConsumer<String, String> consumerMethod(String[] tokens, ImageRepository imageRepository) {
    List<String> colorChannelsImages = Arrays.asList(tokens).subList(2, tokens.length);
    return (src, dest) -> imageRepository.combineImages(colorChannelsImages, dest);
  }
}
