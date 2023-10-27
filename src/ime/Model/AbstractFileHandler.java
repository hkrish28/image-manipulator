package ime.Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class AbstractFileHandler implements FileHandler {

  public Scanner getScanner(String filename) throws FileNotFoundException {
    Scanner sc = new Scanner(new FileInputStream(filename));
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());
    return sc;
  }

}
