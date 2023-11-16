package ime.controller.commands;

import ime.controller.FileHandlerProvider;
import ime.model.ImageRepository;
import java.io.IOException;

/**
 * This class extends the AbstractCommand class and represents a specific command that loads an
 * image into the application.
 */
public class Load extends AbstractCommand {

  private final FileHandlerProvider fileHandlerProvider;

  /**
   * Constructor to initialize the fields.
   */
  public Load(FileHandlerProvider fileHandlerProvider) {
    super(3, 1, 2);
    this.fileHandlerProvider = fileHandlerProvider;
  }

  @Override
  protected String extractTokensAndInvokeMethod(String[] tokens, ImageRepository imageRepository) {
    String path = tokens[1];
    String imageName = tokens[2];
    try {
      float[][][] imagePixels = fileHandlerProvider.getFileHandler(path).loadImage(path);
      imageRepository.loadImage(imagePixels, imageName);
    } catch (IOException e) {
      return "Invalid file";
    }
    return "Loaded successfully.";
  }

}
