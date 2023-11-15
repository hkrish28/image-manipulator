package ime.controller.commands;

import java.io.IOException;
import java.util.function.BiConsumer;

import ime.controller.FileHandlerProviderImpl;
import ime.model.ImageRepository;

/**
 * This class extends the AbstractCommand class and represents a specific command that loads an
 * image into the application.
 */
public class Load extends AbstractCommand {

  /**
   * Constructor to initialize the fields.
   */
  public Load() {
    super(3, 1, 2);
  }

  @Override
  protected String extractTokensAndInvokeMethod(String[] tokens, ImageRepository imageRepository) {
    String path = tokens[1];
    String imageName = tokens[2];
    try {
      float[][][] imagePixels = new FileHandlerProviderImpl().getFileHandler(path).loadImage(path);
      imageRepository.loadImage(imagePixels, imageName);
    } catch (IOException e) {
      return "Invalid file";
    }
    return "Loaded successfully.";
  }

}
