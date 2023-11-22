package ime.controller.commands;

import java.util.function.BiConsumer;

import ime.controller.CommandEnum;
import ime.model.ImageRepository;

/**
 * This class extends the AbstractCommand class and represents a specific command that converts an
 * image to its value greyscale.
 */
public class ValueGreyscale extends AbstractCommand {

  /**
   * Constructor to initialize the fields.
   */
  public ValueGreyscale() {
    super(3, 1, 2, true, CommandEnum.value_component);
  }

  @Override
  protected BiConsumer<String, String> imageRepositoryMethodInvoker(String[] tokens,
                                                                    ImageRepository imageRepository) {
    return imageRepository::toValueGreyScale;
  }
}
