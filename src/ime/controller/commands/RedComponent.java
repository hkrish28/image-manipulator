package ime.controller.commands;

import java.util.function.BiConsumer;

import ime.model.ImageRepository;

public class RedComponent extends AbstractCommand {

  public RedComponent(){
    super(3,1,2);
  }

  @Override
  protected BiConsumer<String, String> consumerMethod(String[] tokens, ImageRepository imageRepository){
    return imageRepository::toRedChannelImage;
  }
}
