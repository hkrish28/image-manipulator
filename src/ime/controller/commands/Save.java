package ime.controller.commands;

import java.util.function.BiConsumer;

import ime.model.ImageRepository;

public class Save extends AbstractCommand {

  public Save() {
    super(3, 1, 2);
  }

  @Override
  protected BiConsumer<String, String> consumerMethod(String[] tokens, ImageRepository imageRepository) {
    return imageRepository::saveImage;
  }

  @Override
  protected String messageSenderHelper(String operation, String src, String dest) {
    return "Saved successfully.";
  }
}
