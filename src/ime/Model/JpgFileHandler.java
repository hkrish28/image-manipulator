package ime.Model;

import java.io.FileNotFoundException;
import java.util.List;

public class JpgFileHandler implements FileHandler {

  @Override
  public void saveFile(Image image, String filename){

  }

  @Override
  public float[][][] loadFile(String filename) throws FileNotFoundException {
    return new float[0][][];
  }
}
