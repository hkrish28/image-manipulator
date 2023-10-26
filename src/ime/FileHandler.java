package ime;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface FileHandler {

  List<ColorChannel> loadFile(String filename) throws FileNotFoundException;

  public void saveFile(Image image, String filename) throws IOException;

  float[][][] loadFileBase(String filename) throws FileNotFoundException;
}
