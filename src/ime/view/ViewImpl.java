package ime.view;

import java.io.PrintStream;

public class ViewImpl implements View {
  PrintStream out;

  public ViewImpl(PrintStream out) {
    this.out = out;
  }

  @Override
  public void displayMessage(String message) {
    out.println(message);
  }
}
