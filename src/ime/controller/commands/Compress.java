package ime.controller.commands;

import java.util.function.BiConsumer;

import ime.model.ImageRepository;

/**
 * This class extends the AbstractCommand class and represents a specific command that compresses
 * an image.
 */
public class Compress extends AbstractCommand {

  /**
   * Constructor to initialize the fields.
   */
  public Compress() {
    super(4,2,3);
  }

  @Override
  protected BiConsumer<String, String> imageRepositoryMethodInvoker(String[] tokens, ImageRepository imageRepository){
    int compressionPercentage = Integer.parseInt(tokens[1]);
    return (src, dest) -> imageRepository.compress(src, dest, compressionPercentage);
  }

}
