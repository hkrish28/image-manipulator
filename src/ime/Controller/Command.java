package ime.Controller;

/**
 * enum command to save the commands possible from the script.
 */
public enum Command {
  load("load"),
  save("save"),
  brighten("brighten"),
  horizontalFlip("horizontal-flip"),
  verticalFlip("vertical-flip"),
  rgb_split("rgb-split"),
  value_component("value-component"),
  rgb_combine("rgb-combine");
  private final String s;

  /**
   * constructor that initialises string s which is the value recieved from the script.
   *
   * @param s is the value of command from the script.
   */
  Command(String s) {
    this.s = s;
  }

  /**
   * getter method to return the s.
   *
   * @return s which is the representation as recieved from the script.
   */
  public String getRepresentation() {
    return s;
  }

}
