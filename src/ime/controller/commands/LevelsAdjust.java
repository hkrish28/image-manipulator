package ime.controller.commands;

import java.util.function.BiConsumer;

import ime.model.ImageRepository;

public class LevelsAdjust extends AbstractCommand {

  public LevelsAdjust() {
    super(6, 4, 5);
  }

  @Override
  protected BiConsumer<String, String> consumerMethod(String[] tokens, ImageRepository imageRepository) {
    int b = Integer.parseInt(tokens[1]);
    int m = Integer.parseInt(tokens[2]);
    int w = Integer.parseInt(tokens[3]);
    return (src, dest) -> imageRepository.levelsAdjust(src, dest, b, m, w);
  }

}
