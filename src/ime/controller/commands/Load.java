package ime.controller.commands;

import ime.controller.CommandEnum;
import ime.controller.FileHandlerProvider;
import ime.model.ImageRepository;
import java.io.IOException;
import java.util.StringJoiner;

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
    super(3, 1, 2, CommandEnum.load);
    this.fileHandlerProvider = fileHandlerProvider;
  }

  /* Validate token count, proceed to extract tokens and invoke appropriate method from
  ImageRepository (if command supports preview operation and the input tokens has valid params
   for the same, invoke the preview method of ImageRepository.*/
  @Override
  public String proceed(String[] tokens, ImageRepository imageRepository) {
    if (tokens.length < tokensRequired) {
      throw new IllegalArgumentException("Invalid number of tokens passed for the given command");
    }
    StringJoiner stringJoiner = new StringJoiner("");
    for (int i = 1; i < tokens.length - 1; i++) {
      stringJoiner.add(tokens[i]);
    }
    String path = stringJoiner.toString();
    String imageName = tokens[tokens.length - 1];
    try {
      float[][][] imagePixels = fileHandlerProvider.getFileHandler(path).loadImage(path);
      imageRepository.loadImage(imagePixels, imageName);
    } catch (IOException e) {
      return "Invalid file";
    }
    return "Loaded successfully.";

  }

}
