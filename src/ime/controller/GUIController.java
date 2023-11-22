package ime.controller;

import ime.model.ImageRepository;
import ime.view.GUIView;

public class GUIController extends AbstractController {

  private final ImageRepository imgRepo;
  private final GUIView view;
  private final FileHandlerProvider fileHandlerProvider;


  public GUIController(ImageRepository imageRepository, GUIView view, FileHandlerProvider fileHandlerProvider) {
    super(fileHandlerProvider, imageRepository);
    this.imgRepo = imageRepository;
    this.view = view;
    this.fileHandlerProvider = fileHandlerProvider;
  }

  @Override
  public void execute() {
//    view.setFeatures(new FeaturesImpl(fileHandlerProvider, knownCommands));
  }

  private static class FeaturesImpl implements Features{
    @Override
    public void loadImage(String fileName) {

    }

    @Override
    public void saveImage(String fileName) {

    }

    @Override
    public void applyBlur() {
      invokeCommand(CommandEnum.blur);
    }

    private static void invokeCommand(CommandEnum commandEnum) {
      String command = knownCommands.get(commandEnum).constructCommand(new String[]{"guiImage", "guiImage"});
      executeCommand(command);
    }

    @Override
    public void applySharpen() {
      invokeCommand(CommandEnum.sharpen);
    }

    @Override
    public void applySepia() {
      invokeCommand(CommandEnum.sepia);
    }

    @Override
    public void applyLumaGreyScale() {
      invokeCommand(CommandEnum.luma_component);
    }

    @Override
    public void applyLevelsAdjust(int b, int m, int w) {

    }

    @Override
    public void applyCompression(int compressionFactor) {

    }

    @Override
    public void visualizeRed() {
      invokeCommand(CommandEnum.red_component);
    }

    @Override
    public void visualizeGreen() {
      invokeCommand(CommandEnum.green_component);
    }

    @Override
    public void visualizeBlue() {
      invokeCommand(CommandEnum.blue_component);
    }

    @Override
    public void applyHorizontalFlip() {
      invokeCommand(CommandEnum.horizontalFlip);
    }

    @Override
    public void applyVerticalFlip() {
      invokeCommand(CommandEnum.verticalFlip);
    }

    @Override
    public void applyColorCorrection() {
      invokeCommand(CommandEnum.color_correct);
    }
  }

}
