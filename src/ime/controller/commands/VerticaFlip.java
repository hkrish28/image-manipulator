package ime.controller.commands;

import java.util.function.BiConsumer;

import ime.model.ImageRepository;

public class VerticaFlip extends AbstractCommand {

  public VerticaFlip() {
    super(3, 1, 2);
  }

  @Override
  protected BiConsumer<String, String> consumerMethod(String[] tokens, ImageRepository imageRepository){
    return imageRepository::flipImageVertically;
  }
}
