package ime.controller.commands;

import java.util.function.BiConsumer;

import ime.controller.CommandEnum;
import ime.model.ImageRepository;

/**
 * This class extends the AbstractCommand class and represents a specific command that applies color
 * correction on an image.
 */
public class ColorCorrect extends AbstractCommand {

  /**
   * Constructor to initialize the fields.
   */
  public ColorCorrect() {
    super(3, 1, 2, true, CommandEnum.color_correct);
  }

  @Override
  protected BiConsumer<String, String> imageRepositoryMethodInvoker(String[] tokens,
                                                                    ImageRepository imageRepository) {
    return imageRepository::colorCorrect;
  }

}
