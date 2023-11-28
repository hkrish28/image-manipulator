package ime.controller;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import ime.controller.commands.Command;
import ime.model.ImageRepository;
import ime.view.GUIView;

public class GUIController extends AbstractController {

  private final GUIView view;
  protected ImageRepository imgRepo;

  public GUIController(ImageRepository imageRepository, GUIView view, FileHandlerProvider fileHandlerProvider) {
    super(fileHandlerProvider, imageRepository, view);
    this.view = view;
    this.imgRepo = imageRepository;
  }

  @Override
  public void execute() {
    view.setFeatures(new FeaturesImpl(this));
  }

  protected void updateImage(String imageName) {
    updateViewImage(imageName, view::setImage);
  }

  protected void updateHistogram(String imageName) {
    updateViewImage(imageName, view::setHistogram);
  }

  private void updateViewImage(String imageName, Consumer<Image> viewMethod) {
    try {
      float[][][] image = imgRepo.getImage(imageName);
      BufferedImage bufferedImage = new BufferedImageHandler().convertIntoImage(image);
      viewMethod.accept(bufferedImage);
    } catch (IllegalArgumentException e){
      view.displayMessage(e.getMessage());
    }
  }


  protected int getInput(String inputPreviewPercentage) {
    return view.getInput(inputPreviewPercentage);
  }

  protected void setupOperation(boolean apply, boolean preview) {
    view.enableApply(apply);
    view.enablePreview(preview);
  }

  protected void setToggle(boolean show) {
    view.enableToggle(show);
  }

  protected void sendDisplayMessage(String message) {
    view.displayMessage(message);
  }

  protected boolean getConfirmation(String message) {
    return view.getConfirmation(message);
  }

  @Override
  protected void runCommandObject(Command command, String[] tokens) {
      command.proceed(tokens, imgRepo);
  }

  protected String openFileAction() {
    List<String> supportedFormats = Arrays.stream(FileFormatEnum.values())
            .map(FileFormatEnum::name).collect(Collectors.toList());
    return view.getFilePathToLoad(supportedFormats);
  }

  protected String saveFileAction() {
    return view.getFilePathToSave();
  }

  /* GUI controller returns true when the command executes without no error. */
  @Override
  protected boolean returnValueNoError() {
    return true;
  }

}
