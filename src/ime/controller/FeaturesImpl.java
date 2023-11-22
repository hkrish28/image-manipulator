//package ime.controller;
//
//import java.util.Map;
//
//import ime.controller.commands.Command;
//import ime.controller.commands.Load;
//
//public class FeaturesImpl implements Features {
//
//  private final FileHandlerProvider fileHandlerProvider;
//  private final Map<CommandEnum, Command> knownCommands;
//  public FeaturesImpl(FileHandlerProvider fileHandlerProvider, Map<CommandEnum, Command> knownCommands){
//      this.fileHandlerProvider = fileHandlerProvider;
//      this.knownCommands = knownCommands;
//  }
//  @Override
//  public void loadImage(String fileName) {
//
//  }
//
//  @Override
//  public void saveImage(String fileName) {
//
//  }
//
//  @Override
//  public void applyBlur() {
//    String command = knownCommands.get(CommandEnum.blur).constructCommand(new String[]{"guiImage", "guiImage"});
//    executeCommand(command);
//  }
//
//  @Override
//  public void applySharpen() {
//
//  }
//
//  @Override
//  public void applySepia() {
//
//  }
//
//  @Override
//  public void applyLumaGreyScale() {
//
//  }
//
//  @Override
//  public void applyLevelsAdjust(int b, int m, int w) {
//
//  }
//
//  @Override
//  public void applyCompression(int compressionFactor) {
//
//  }
//
//  @Override
//  public void visualizeRed() {
//
//  }
//
//  @Override
//  public void visualizeGreen() {
//
//  }
//
//  @Override
//  public void visualizeBlue() {
//
//  }
//
//  @Override
//  public void applyHorizontalFlip() {
//
//  }
//
//  @Override
//  public void applyVerticalFlip() {
//
//  }
//
//  @Override
//  public void applyColorCorrection() {
//
//  }
//}
