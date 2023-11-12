package ime.controller.commands;

import java.util.function.BiConsumer;

import ime.model.ImageRepository;

public class BlueComponent extends AbstractCommand {

  public BlueComponent(){
    super(3);
  }

  @Override
  protected String extractTokensAndInvokeMethod(String[] tokens, ImageRepository imageRepository) {
    String imageName = tokens[1];
    String newImage = tokens[2];
    imageRepository.toBlueChannelImage(imageName, newImage);
    return messageSenderHelper(tokens);
  }

  @Override
  protected BiConsumer<String, String> consumerMethod(String[] tokens, ImageRepository imageRepository){
    return imageRepository::toIntensityGreyScale;
  }
}
