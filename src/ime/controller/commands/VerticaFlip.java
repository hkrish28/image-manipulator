package ime.controller.commands;

import java.util.function.BiConsumer;

import ime.model.ImageRepository;

/**
 * This class extends the AbstractCommand class and represents a specific command that flips an
 * image vertically.
 */
public class VerticaFlip extends AbstractCommand {

  /**
   * Constructor to initialize the fields.
   */
  public VerticaFlip() {
    super(3, 1, 2);
  }

  @Override
  protected BiConsumer<String, String> imageRepositoryMethodInvoker(String[] tokens, ImageRepository imageRepository){
    return imageRepository::flipImageVertically;
  }
}
