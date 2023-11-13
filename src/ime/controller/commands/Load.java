package ime.controller.commands;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.function.BiConsumer;

import ime.controller.FileHandlerProviderImpl;
import ime.controller.BufferedImageHandler;
import ime.controller.ImageHandler;
import ime.model.ImageRepository;

public class Load extends AbstractCommand {

  private final ImageHandler<BufferedImage> bufferedImageImageHandler;

  public Load() {
    super(3, 1, 2);
    bufferedImageImageHandler = new BufferedImageHandler();
  }

  @Override
  protected String extractTokensAndInvokeMethod(String[] tokens, ImageRepository imageRepository) {
    String path = tokens[1];
    String imageName = tokens[2];
    try {
      BufferedImage image = new FileHandlerProviderImpl().getFileHandler(path).loadImage(path);
      imageRepository.loadImage(bufferedImageImageHandler.getImagePixels(image), imageName);
    } catch (IOException e) {
      return "Invalid file";
    }
    return "Loaded successfully.";
  }

  @Override
  protected BiConsumer<String, String> consumerMethod(String[] tokens, ImageRepository imageRepository) {
    return null;
  }
}
