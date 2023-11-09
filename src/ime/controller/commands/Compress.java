package ime.controller.commands;

import java.util.function.BiConsumer;

import ime.model.ImageRepository;

public class Compress extends AbstractCommand {

  public Compress() {
    super(4,2,3);
  }

  @Override
  protected BiConsumer<String, String> consumerMethod(String[] tokens, ImageRepository imageRepository){
    int compressionPercentage = Integer.parseInt(tokens[1]);
    return (src, dest) -> imageRepository.compress(src, dest, compressionPercentage);
  }

}
