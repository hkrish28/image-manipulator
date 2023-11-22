package ime.controller;

import ime.model.ImageRepository;
import ime.view.GUIView;

public class GUIController extends AbstractController {


  private final GUIView view;


  public GUIController(ImageRepository imageRepository, GUIView view, FileHandlerProvider fileHandlerProvider) {
    super(fileHandlerProvider, imageRepository);
    this.view = view;
  }

  @Override
  public void execute() {
    view.setFeatures(new FeaturesImpl(this));
  }


}
