package ime.controller.commands;

import java.util.function.BiConsumer;

import ime.model.ImageRepository;

public class GreenComponent extends AbstractCommand {

  public GreenComponent() {
    super(3,1,2);
  }

  @Override
  protected BiConsumer<String, String> consumerMethod(String[] tokens, ImageRepository imageRepository){
    return imageRepository::toGreenChannelImage;
  }
}
