package ime;

import ime.Model.FileHandler;
import ime.Model.Image;
import java.io.IOException;

public class MockFileHandler implements FileHandler {

  @Override
  public float[][][] loadImage(String filename) throws IOException {
    return new float[0][][];
  }

  @Override
  public void saveImage(Image image, String filename) throws IOException {

  }
}
