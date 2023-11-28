//package ime.view;
//
//import java.io.File;
//import java.io.FileFilter;
//
//import javax.swing.*;
//
//public class FileChooserImpl implements FileChooser {
//
//  private JFileChooser fileChooser;
//  private FileFilter fileFilter;
//  public  FileChooserImpl(){
//    this.fileChooser = new JFileChooser();
//    fileFilter = new FileFilter() {
//      @Override
//      public boolean accept(File pathname) {
//        return false;
//      }
//    }
//  }
//  @Override
//  public void setCurrentDirectoryPath(File path) {
//    fileChooser.setCurrentDirectory(path);
//  }
//
//  @Override
//  public void setFileFilter(String message, String... extensions) {
//
//  }
//
//  @Override
//  public boolean isFileChosen() {
//    return false;
//  }
//
//  @Override
//  public void showOpenDialog() {
//
//  }
//
//  @Override
//  public void showSaveDialog() {
//
//  }
//
//  @Override
//  public File getSelectedFile() {
//    return null;
//  }
//}
