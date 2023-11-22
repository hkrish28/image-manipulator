package ime.controller.commands;

import java.util.function.BiConsumer;

import ime.controller.CommandEnum;
import ime.model.ImageRepository;

/**
 * This class extends the AbstractCommand class and represents a specific command that applies a
 * sharpen effect to an image.
 */
public class Sharpen extends AbstractCommand {

  /**
   * Constructor to initialize the fields.
   */
  public Sharpen() {
    super(3, 1, 2, true, CommandEnum.sharpen);
  }

  @Override
  protected BiConsumer<String, String> imageRepositoryMethodInvoker(String[] tokens,
                                                                    ImageRepository imageRepository) {
    return imageRepository::sharpenImage;
  }
}
