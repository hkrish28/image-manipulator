package ime.controller.commands;

import java.util.function.BiConsumer;

import ime.model.ImageRepository;

public class ColorCorrect extends AbstractCommand {

  public ColorCorrect(){
    super(3, 1, 2, true);
  }

  @Override
  protected BiConsumer<String, String> consumerMethod(String[] tokens, ImageRepository imageRepository){
    return imageRepository::colorCorrect;
  }

}
