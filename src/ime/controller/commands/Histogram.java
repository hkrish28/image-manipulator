package ime.controller.commands;

import java.util.function.BiConsumer;

import ime.controller.ImageDrawerImpl;
import ime.model.ImageRepository;

public class Histogram extends AbstractCommand {

  public Histogram() {
    super(3);
  }

  @Override
  protected BiConsumer<String, String> consumerMethod(String[] tokens, ImageRepository imageRepository) {
    return (srcImage, destImage) -> imageRepository.toHistogram(srcImage, destImage, new ImageDrawerImpl());
  }


}
