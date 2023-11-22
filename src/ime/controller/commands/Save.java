package ime.controller.commands;

import java.io.IOException;

import ime.controller.CommandEnum;
import ime.controller.FileHandlerProvider;
import ime.model.ImageRepository;

/**
 * This class extends the AbstractCommand class and represents a specific command that saves an
 * image from the application to a file.
 */
public class Save extends AbstractCommand {

  private final FileHandlerProvider fileHandlerProvider;

  /**
   * Constructor to initialize the fields.
   */
  public Save(FileHandlerProvider fileHandlerProvider) {
    super(3, 1, 2, CommandEnum.save);
    this.fileHandlerProvider = fileHandlerProvider;
  }

  protected String extractTokensAndInvokeMethod(String[] tokens, ImageRepository imageRepository) {
    String file = tokens[1];
    String imageName = tokens[2];
    float[][][] image = imageRepository.getImage(imageName);
    try {
      fileHandlerProvider.getFileHandler(file).saveImage(image, file);
    } catch (IOException e) {
      return "Invalid file";
    }

    return messageSenderHelper(tokens);
  }

  @Override
  protected String messageSenderHelper(String[] tokens) {
    return "Saved successfully.";
  }
}
