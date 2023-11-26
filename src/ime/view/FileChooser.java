package ime.view;

import java.io.File;

public interface FileChooser {
  void setCurrentDirectoryPath(String path);
  void setFileFilter(String message, String... extensions);
  boolean isFileChosen();
  void showOpenDialog();
  void showSaveDialog();
  File getSelectedFile();
}
