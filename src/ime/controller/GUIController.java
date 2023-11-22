package ime.controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.Consumer;

import ime.model.ImageRepository;
import ime.view.GUIView;

public class GUIController extends AbstractController {

  private final GUIView view;
  protected ImageRepository imgRepo;


  public GUIController(ImageRepository imageRepository, GUIView view, FileHandlerProvider fileHandlerProvider) {
    super(fileHandlerProvider, imageRepository);
    this.view = view;
    this.imgRepo = imageRepository;
  }

  @Override
  public void execute() {
    view.setFeatures(new FeaturesImpl(this));
  }

  protected void updateImage(String imageName) {
    updateViewImage(imageName, view::setImage);
//    float[][][] image = imgRepo.getImage(imageName);
//    BufferedImage bufferedImage = new BufferedImageHandler().convertIntoImage(image);
//    view.setImage(bufferedImage);
  }

  protected void updateHistogram(String imageName) {
    updateViewImage(imageName, view::setHistogram);
//    float[][][] image = imgRepo.getImage(imageName);
//    BufferedImage bufferedImage = new BufferedImageHandler().convertIntoImage(image);
//    view.setHistogram(bufferedImage);
  }

  private void updateViewImage(String imageName, Consumer<Image> viewMethod) {
    float[][][] image = imgRepo.getImage(imageName);
    BufferedImage bufferedImage = new BufferedImageHandler().convertIntoImage(image);
    viewMethod.accept(bufferedImage);
  }

}
