package ime.controller.commands;

import java.util.function.BiConsumer;

import ime.model.ImageRepository;

public class Load extends AbstractCommand {
  public Load() {
    super(3, 1,2);
  }

  @Override
  protected String extractTokensAndInvokeMethod(String[] tokens, ImageRepository imageRepository) {
    String path = tokens[1];
    String imageName = tokens[2];
    imageRepository.loadImage(path, imageName);
    return "Loaded successfully.";
  }

  @Override
  protected BiConsumer<String, String> consumerMethod(String[] tokens, ImageRepository imageRepository) {
    return imageRepository::loadImage;
  }
}
