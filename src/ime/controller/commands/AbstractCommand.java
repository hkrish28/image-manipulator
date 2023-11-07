package ime.controller.commands;

public abstract class AbstractCommand implements Command {

  protected void validateTokenCount(int expectedTokens, int tokenCount) {
    if (expectedTokens != tokenCount) {
      throw new IllegalArgumentException("Invalid number of tokens passed for the given command");
    }
  }

  protected String messageSenderHelper(String operation, String src, String dest) {
    return operation + " operation completed successfully for " + src
            + " & put in " + dest;
  }
}
