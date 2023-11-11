package ime.controller.commands;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.function.BiConsumer;

import ime.controller.FileHandlerProviderImpl;
import ime.model.ImageRepository;

public class Save extends AbstractCommand {

  public Save() {
    super(3, 1, 2);
  }

  protected String extractTokensAndInvokeMethod(String[] tokens, ImageRepository imageRepository) {
    String file = tokens[1];
    String imageName = tokens[2];
    BufferedImage bufferedImage = imageRepository.getImage(imageName);
    try {
      new FileHandlerProviderImpl().getFileHandler(file).saveImage(bufferedImage, file);
    } catch (IOException e) {
      return "Invalid file";
    }

    return messageSenderHelper(tokens[0], file, imageName);
  }

  @Override
  protected BiConsumer<String, String> consumerMethod(String[] tokens, ImageRepository imageRepository) {
    return null;
  }

  @Override
  protected String messageSenderHelper(String operation, String src, String dest) {
    return "Saved successfully.";
  }
}
