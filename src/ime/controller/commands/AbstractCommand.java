package ime.controller.commands;

import java.util.function.BiConsumer;

import ime.model.ImageRepository;

public abstract class AbstractCommand implements Command {

  protected final boolean splitSupport;
  protected int tokensRequired;
  protected int srcIndex;
  protected int destIndex;

  protected AbstractCommand(int tokensRequired, int srcIndex, int destIndex) {
    this.tokensRequired = tokensRequired;
    splitSupport = false;
    this.srcIndex = srcIndex;
    this.destIndex = destIndex;
  }

  protected AbstractCommand(int tokensRequired, int srcIndex, int destIndex, boolean splitSupport) {
    this.tokensRequired = tokensRequired;
    this.splitSupport = splitSupport;
    this.srcIndex = srcIndex;
    this.destIndex = destIndex;
  }

  protected AbstractCommand(int tokensRequired) {
    this.tokensRequired = tokensRequired;
    splitSupport = false;
    this.srcIndex = 1;
    this.destIndex = 2;
  }

  protected boolean validateTokenCount(int expectedTokens, int tokenCount) {
    return expectedTokens == tokenCount;
  }

  protected String messageSenderHelper(String[] tokens) {
    return tokens[0] + " operation completed successfully for " + tokens[srcIndex]
            + " & put in " + tokens[destIndex];
  }

  public String go(String[] tokens, ImageRepository imageRepository) {
    if (validateTokenCount(tokensRequired, tokens.length)) {
      return extractTokensAndInvokeMethod(tokens, imageRepository);
    } else if (splitSupport
            && validateTokenCount(tokensRequired + 2, tokens.length)
    && tokens[tokensRequired].equals("split")) {
      return extractTokensAndInvokePreview(tokens, imageRepository);
    } else {
      return "Invalid number of tokens passed for the given command";
    }

  }

  protected String extractTokensAndInvokePreview(String[] tokens, ImageRepository imageRepository) {
    String srcImageName = tokens[srcIndex];
    String destImageName = tokens[destIndex];
    int splitPercent = Integer.parseInt(tokens[tokens.length - 1]);
    imageRepository.preview(srcImageName, destImageName, consumerMethod(tokens, imageRepository), splitPercent);
    return "Successfully Previewed";
  }

  protected String extractTokensAndInvokeMethod(String[] tokens, ImageRepository imageRepository) {
    String srcImageName = tokens[srcIndex];
    String destImageName = tokens[destIndex];
    consumerMethod(tokens, imageRepository).accept(srcImageName, destImageName);
    return messageSenderHelper(tokens);
  }

  protected abstract BiConsumer<String, String> consumerMethod(String[] tokens, ImageRepository imageRepository);
}
