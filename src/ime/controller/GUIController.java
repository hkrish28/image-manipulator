package ime.controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.Consumer;

import ime.model.ImageRepository;
import ime.view.GUIView;

public class GUIController extends AbstractController {

  private final GUIView view;
  protected ImageRepository imgRepo;

//  private List<Option> optionList;

  public GUIController(ImageRepository imageRepository, GUIView view, FileHandlerProvider fileHandlerProvider) {
    super(fileHandlerProvider, imageRepository, view);
    this.view = view;
    this.imgRepo = imageRepository;
//    initialiseOptions();
  }

  @Override
  public void execute() {
//    view.setOptions(optionList);
    view.setFeatures(new FeaturesImpl(this));
  }

  protected void updateImage(String imageName) {
    updateViewImage(imageName, view::setImage);
  }

  protected void updateHistogram(String imageName) {
    updateViewImage(imageName, view::setHistogram);
  }

  private void updateViewImage(String imageName, Consumer<Image> viewMethod) {
    float[][][] image = imgRepo.getImage(imageName);
    BufferedImage bufferedImage = new BufferedImageHandler().convertIntoImage(image);
    viewMethod.accept(bufferedImage);
  }

//
//  private void initialiseOptions() {
//    List<AdditionalInput> additionalInputs = Arrays.asList(new AdditionalInput("b", 0, 0, 255),
//            new AdditionalInput("m", 128, 0, 255), new AdditionalInput("w", 255, 0, 255));
//    List<AdditionalInput> additional = Arrays.asList(
//            new AdditionalInput("compress percent", 0, 0, 100));
//    optionList = new ArrayList<>();
//    optionList.add(new Option("Load Image", false, null, CommandEnum.load));
//    optionList.add(new Option("Save Image", false, null, CommandEnum.save));
//    optionList.add(new Option("Visualize Red", false, null, CommandEnum.red_component));
//    optionList.add(new Option("Visualize Green", false, null, CommandEnum.green_component));
//    optionList.add(new Option("Visualize Blue", false, null, CommandEnum.blue_component));
//    optionList.add(new Option("Flip Vertically", false, null, CommandEnum.verticalFlip));
//    optionList.add(new Option("Flip Horizontally", false, null, CommandEnum.horizontalFlip));
//    optionList.add(new Option("Blur", true, null, CommandEnum.blur));
//    optionList.add(new Option("Sharpen", true, null, CommandEnum.sharpen));
//    optionList.add(new Option("Convert to Greyscale", true, null, CommandEnum.luma_component));
//    optionList.add(new Option("Convert to Sepia", true, null, CommandEnum.sepia));
//    optionList.add(new Option("Compression", false, additional, CommandEnum.compress));
//    optionList.add(new Option("Levels Adjust", true, additionalInputs, CommandEnum.levels_adjust));
//    optionList.add(new Option("Color Correct", true, additionalInputs, CommandEnum.color_correct));
//  }

  public int getInput(String inputPreviewPercentage) {
    return view.getInput(inputPreviewPercentage);
  }

  public void setupOperation(boolean apply, boolean preview) {
    view.enableApply(apply);
    view.enablePreview(preview);
  }

  public void setToggle(boolean show) {
    view.enableToggle(show);
  }
}
