package ime.controller.commands;

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
    float[][][] image = imageRepository.getImage(imageName);
    try {
      new FileHandlerProviderImpl().getFileHandler(file).saveImage(image, file);
    } catch (IOException e) {
      return "Invalid file";
    }

    return messageSenderHelper(tokens);
  }

  @Override
  protected BiConsumer<String, String> consumerMethod(String[] tokens, ImageRepository imageRepository) {
    return null;
  }

  @Override
  protected String messageSenderHelper(String[] tokens) {
    return "Saved successfully.";
  }
}
