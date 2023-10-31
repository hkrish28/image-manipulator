package ime;

import ime.Model.FileHandler;
import ime.Model.FileHandlerProvider;
import ime.Model.Image;
import java.io.IOException;

public class MockFileHandlerProvider implements FileHandlerProvider {


  @Override
  public FileHandler getFileHandler(String fileType) {
    return null;
  }
}
