package ime.controller.commands;

import java.util.function.BiConsumer;

import ime.model.ImageRepository;

public class Sharpen extends AbstractCommand {

  public Sharpen() {
    super(3, 1, 2, true);
  }

  @Override
  protected BiConsumer<String, String> consumerMethod(String[] tokens, ImageRepository imageRepository) {
    return imageRepository::sharpenImage;
  }
}
