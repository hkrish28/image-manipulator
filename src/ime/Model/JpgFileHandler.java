package ime.Model;

import java.io.FileNotFoundException;
import java.util.List;

public class JpgFileHandler implements FileHandler {
  @Override
  public List<ColorChannel> loadFile(String filename) {
    return null;
  }

  @Override
  public void saveFile(Image image, String filename){

  }

  @Override
  public float[][][] loadFileBase(String filename) throws FileNotFoundException {
    return new float[0][][];
  }
}
