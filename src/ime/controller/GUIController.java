package ime.controller;


import ime.controller.commands.Command;
import ime.model.ImageRepository;
import ime.view.GUIView;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * controller that interacts with the guiview.It passes sets of callback function through @link
 * featuresimpl class which was implemented seperately to prevent view from accessing the public
 * methods of the controller.
 */
public class GUIController extends AbstractController {

  private final GUIView view;
  protected ImageRepository imgRepo;

  /**
   * constructor to initialise and set the view and imageRepository objects.
   *
   * @param imageRepository     object of imageRepository class.
   * @param view                object of view clas.
   * @param fileHandlerProvider object of filehandler provider class.
   */
  public GUIController(ImageRepository imageRepository, GUIView view,
      FileHandlerProvider fileHandlerProvider) {
    super(fileHandlerProvider, imageRepository, view);
    this.view = view;
    this.imgRepo = imageRepository;
  }

  /**
   * to execute the appropriate actions depending on the options clicked.
   */
  @Override
  public void execute() {
    view.setFeatures(new FeaturesImpl(this));
  }

  /**
   * updates the image. this method uses a runnable object so code duplication is reduced.
   *
   * @param imageName name of the image.
   */
  protected void updateImage(String imageName) {
    updateViewImage(imageName, view::setImage);
  }

  /**
   * updates histogram and sends the image name as parameter.
   *
   * @param imageName name of the image.
   */
  protected void updateHistogram(String imageName) {
    updateViewImage(imageName, view::setHistogram);
  }

  /**
   * to update the view.
   *
   * @param imageName  name of the image.
   * @param viewMethod view method is a consumer method that takes in Image object.
   */
  private void updateViewImage(String imageName, Consumer<Image> viewMethod) {
    try {
      float[][][] image = imgRepo.getImage(imageName);
      BufferedImage bufferedImage = new BufferedImageHandler().convertIntoImage(image);
      viewMethod.accept(bufferedImage);
    } catch (IllegalArgumentException e) {
      view.displayMessage(e.getMessage());
    }
  }

  /**
   * gets the input from the user.
   *
   * @param inputPreviewPercentage input percent for preview.
   * @return view.getInput.
   */
  protected int getInput(String inputPreviewPercentage) {
    return view.getInput(inputPreviewPercentage);
  }

  /**
   * sets up an operation.
   *
   * @param apply   true/false if apply button is clicked or not.
   * @param preview is preview clicked or not.
   */
  protected void setupOperation(boolean apply, boolean preview) {
    view.enableApply(apply);
    view.enablePreview(preview);
  }

  /**
   * is toggle set or not.
   *
   * @param show boolean value.
   */
  protected void setToggle(boolean show) {
    view.enableToggle(show);
  }

  /**
   * to display the message.
   *
   * @param message message by controller.
   */
  protected void sendDisplayMessage(String message) {
    view.displayMessage(message);
  }

  /**
   * gets the confirmation message.
   *
   * @param message message.
   * @return the confirmation message by the controller.
   */
  protected boolean getConfirmation(String message) {
    return view.getConfirmation(message);
  }

  /**
   * run the command runnable object.
   *
   * @param command command to be run.
   * @param tokens  the command words.
   */
  @Override
  protected void runCommandObject(Command command, String[] tokens) {
    command.proceed(tokens, imgRepo);
  }

  /**
   * action for opening the file.
   */
  protected String openFileAction() {
    List<String> supportedFormats = Arrays.stream(FileFormatEnum.values())
        .map(FileFormatEnum::name).collect(Collectors.toList());
    return view.getFilePathToLoad(supportedFormats);
  }

  /**
   * action for saving the file.
   *
   * @return the file path.
   */
  protected String saveFileAction() {
    return view.getFilePathToSave();
  }

  /**
   * GUI controller returns true when the command executes without no error.
   **/
  @Override
  protected boolean returnValueNoError() {
    return true;
  }

}
