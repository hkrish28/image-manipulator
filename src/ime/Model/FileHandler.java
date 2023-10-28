package ime.Model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface FileHandler {

  float[][][] loadFile(String filename) throws IOException;

  public void saveFile(Image image, String filename) throws IOException;

}
