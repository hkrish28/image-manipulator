package ime.Model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageRepositoryImpl implements ImageRepository {

  /**
   * map for storing the image with its name as the key.
   */
  private Map<String, Image> imageFilesMap;

  public ImageRepositoryImpl() {
    imageFilesMap = new HashMap<>();
  }

  @Override
  public void loadImage(String fileName, String imageName) throws FileNotFoundException {
    Image newImage = new ImagePixelImpl(fileName);
    imageFilesMap.put(imageName, newImage);
  }

  @Override
  public void saveImage(String fileName, String imageName) throws IOException {
    if (imageFilesMap.get(imageName) == null) {
      throw new IllegalArgumentException("image name invalid");
    } else {
      imageFilesMap.get(imageName).saveImage(fileName);
    }
  }

  @Override
  public void splitImageIntoColorChannels(String srcImage, List<String> destImageNames) {

  }

  @Override
  public void combineImages(List<String> images, String imageDestName) {

  }

  @Override
  public void brightenImage(String imageNameSrc, String imageNameDest, float brightnessConstant) {

  }

  @Override
  public void darkenenImage(String imageNameSrc, String imageNameDest, float darknessConstant) {

  }

  @Override
  public void blurImage(String imageNameSrc, String imageNameDest) {

  }

  @Override
  public void sharpenImage(String imageNameSrc, String imageNameDest) {

  }

  @Override
  public void flipImageHorizontally(String imageNameSrc, String imageNameDest) {

  }

  @Override
  public void flipImageVertically(String imageNameSrc, String imageNameDest) {

  }

  @Override
  public void toIntensityGreyScale(String imageNameSrc, String imageNameDest) {

  }

  @Override
  public void toLumaGreyScale(String imageNameSrc, String imageNameDest) {

  }

  @Override
  public void toValueGreyScale(String imageNameSrc, String imageNameDest) {

  }

  @Override
  public void toSepiaImage(Image imageNameSrc, Image imageNameDest) {

  }
}
