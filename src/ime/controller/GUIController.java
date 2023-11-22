package ime.controller;

import java.awt.image.BufferedImage;

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

  protected void imageLoaded(String imageName) {
    float[][][] image = imgRepo.getImage(imageName);
    BufferedImage bufferedImage = new BufferedImageHandler().convertIntoImage(image);
    view.setImage(bufferedImage);
  }

}
