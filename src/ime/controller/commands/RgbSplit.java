package ime.controller.commands;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

import ime.model.ImageRepository;

public class RgbSplit extends AbstractCommand {

  public RgbSplit() {
    super(5, 1, 0);
  }

  @Override
  protected BiConsumer<String, String> consumerMethod(String[] tokens, ImageRepository imageRepository) {
    List<String> colorChannelsImages = Arrays.asList(tokens).subList(2, tokens.length);
    return (src, dest) -> imageRepository.splitImageIntoColorChannels(src, colorChannelsImages);
  }
}
