package ime.controller.commands;

import java.util.function.BiConsumer;

import ime.model.ImageRepository;

/**
 * This abstract class implements the Command interface and provides a common structure for all
 * command classes that will extend it.
 */
public abstract class AbstractCommand implements Command {

  protected final boolean splitSupport;
  protected int tokensRequired;
  protected int srcIndex;
  protected int destIndex;

  /**
   * Constructor for the AbstractCommand class that initializes the common fields for the commands
   * that extend this class. This sets the split support for the command to false, since nothing was
   * passed explicitly.
   *
   * @param tokensRequired number of tokens that the command expects, including the command itself.
   * @param srcIndex       index of the source image name token for the operation
   * @param destIndex      index of the destination image name token for the operation
   */
  protected AbstractCommand(int tokensRequired, int srcIndex, int destIndex) {
    this.tokensRequired = tokensRequired;
    splitSupport = false;
    this.srcIndex = srcIndex;
    this.destIndex = destIndex;
  }

  /**
   * Constructor for the AbstractCommand class that initializes the common fields for the commands
   * that extend this class.
   *
   * @param tokensRequired number of tokens that the command expects, including the command itself.
   * @param srcIndex       index of the source image name token for the operation
   * @param destIndex      index of the destination image name token for the operation
   * @param splitSupport   whether the command supports split(preview) operation
   */
  protected AbstractCommand(int tokensRequired, int srcIndex, int destIndex, boolean splitSupport) {
    this.tokensRequired = tokensRequired;
    this.splitSupport = splitSupport;
    this.srcIndex = srcIndex;
    this.destIndex = destIndex;
  }

  /**
   * This constructor initializes the fields using their default values, expecting the source image
   * name to be followed by the command, which would be followed by the destination image. Split
   * support is disabled.
   *
   * @param tokensRequired number of tokens that the command expects, including the command itself.
   */
  protected AbstractCommand(int tokensRequired) {
    this.tokensRequired = tokensRequired;
    splitSupport = false;
    this.srcIndex = 1;
    this.destIndex = 2;
  }

  /* Checks whether the given number of tokens matches to the tokensRequired for the command. */
  protected boolean validateTokenCount(int tokenCount) {
    return tokensRequired == tokenCount;
  }

  /* A helper that provides a generic message response for the command. */
  protected String messageSenderHelper(String[] tokens) {
    return tokens[0] + " operation completed successfully for " + tokens[srcIndex]
            + " & put in " + tokens[destIndex];
  }

  /* Validate token count, proceed to extract tokens and invoke appropriate method from
  ImageRepository (if command supports preview operation and the input tokens has valid params
   for the same, invoke the preview method of ImageRepository.*/
  @Override
  public String go(String[] tokens, ImageRepository imageRepository) {
    if (validateTokenCount(tokens.length)) {
      return extractTokensAndInvokeMethod(tokens, imageRepository);
    } else if (splitSupport
            && validateTokenCount(tokens.length - 2)
            && tokens[tokensRequired].equals("split")) {
      return extractTokensAndInvokePreview(tokens, imageRepository);
    } else {
      return "Invalid number of tokens passed for the given command";
    }

  }

  /* Extract tokens, get the split percentage and invoke the preview method for the set of src and
   * dest image names. Pass along with it, the operation within the imageRepository that has to be
   * invoked for the given split percentage. */
  protected String extractTokensAndInvokePreview(String[] tokens, ImageRepository imageRepository) {
    String srcImageName = tokens[srcIndex];
    String destImageName = tokens[destIndex];
    int splitPercent = Integer.parseInt(tokens[tokens.length - 1]);
    imageRepository.preview(srcImageName, destImageName, imageRepositoryMethodInvoker(tokens, imageRepository), splitPercent);
    return "Successfully Previewed";
  }

  /* Extract src and destination image names from the tokens and invoke the appropriate method of
   * image repository provided by the imageRepositoryMethodInvoker for the command.*/
  protected String extractTokensAndInvokeMethod(String[] tokens, ImageRepository imageRepository) {
    String srcImageName = tokens[srcIndex];
    String destImageName = tokens[destIndex];
    imageRepositoryMethodInvoker(tokens, imageRepository).accept(srcImageName, destImageName);
    return messageSenderHelper(tokens);
  }

  /**
   * This method provides the mapper to the method inside imageRepository that this
   * command makes use of. Any commands that do not require any method invocation of the
   * ImageRepository need not override it.
   *
   * @param tokens          the tokens received for the command
   * @param imageRepository the imageRepository to be used for the method invocation
   * @return a BiConsumer that provides a function expecting two string which will call the
   * appropriate method inside the imageRepository for the command.
   */
  protected BiConsumer<String, String> imageRepositoryMethodInvoker(
          String[] tokens, ImageRepository imageRepository) {
    return null;
  }
}
