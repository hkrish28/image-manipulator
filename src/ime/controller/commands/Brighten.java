package ime.controller.commands;

import java.util.function.BiConsumer;

import ime.model.ImageRepository;

public class Brighten extends AbstractCommand {

  public Brighten() {
    super(4, 2, 3);
  }

  @Override
  protected BiConsumer<String, String> consumerMethod(String[] tokens, ImageRepository imageRepository) {
    try {
      float brightnessConstant = Float.parseFloat(tokens[1]);
      return (src, dest) -> imageRepository.brightenImage(src, dest, brightnessConstant);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("brightness command expects a number following the command");
    }
  }

}
