package ime.controller.commands;

import java.util.function.BiConsumer;

import ime.model.ImageRepository;

/**
 * This class extends the AbstractCommand class and represents a specific command that adjusts the
 * levels of an image.
 */
public class LevelsAdjust extends AbstractCommand {

  public LevelsAdjust() {
    super(6, 4, 5, true);
  }

  @Override
  protected BiConsumer<String, String> imageRepositoryMethodInvoker(String[] tokens,
                                                                    ImageRepository imageRepository) throws IllegalArgumentException {
    try {
      int b = Integer.parseInt(tokens[1]);
      int m = Integer.parseInt(tokens[2]);
      int w = Integer.parseInt(tokens[3]);
      return (src, dest) -> imageRepository.levelsAdjust(src, dest, b, m, w);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("3 numbers required following the levels-adjust command");
    }
  }

}
