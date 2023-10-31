package ime.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This implementation of {@link ImageRepository} stores multiple images as a map between the
 * tagged name of the image to its actual {@link Image} object. It makes use of
 * {@link FileHandlerProvider} to perform functions like load and save of images.
 */
public class ImageRepositoryImpl implements ImageRepository {

  private final FileHandlerProvider fileHandlerProvider;
  /**
   * map for storing the image with its name as the key.
   */
  private Map<String, Image> imageMap;

  public ImageRepositoryImpl() {
    imageMap = new HashMap<>();
    fileHandlerProvider = new FileHandlerProviderImpl();

  }

  @Override
  public void loadImage(String filePath, String imageName) throws IOException {
    float[][][] imagePixels = fileHandlerProvider.getFileHandler(filePath).loadImage(filePath);
    Image newImage = new ImagePixelImpl(imagePixels, ImageType.RGB);
    imageMap.put(imageName, newImage);
  }

  @Override
  public void saveImage(String fileName, String imageName) throws IOException {
    if (imageMap.get(imageName) == null) {
      throw new IllegalArgumentException("image name invalid");
    } else {
      Image image = imageMap.get(imageName);
      fileHandlerProvider.getFileHandler(fileName).saveImage(image, fileName);
    }
  }

  @Override
  public void splitImageIntoColorChannels(String srcImage, List<String> destImageNames) {
    List<Image> destImages = imageMap.get(srcImage).splitIntoColorChannels();
    for (int i = 0; i < destImages.size(); i++) {
      imageMap.put(destImageNames.get(i), destImages.get(i));
    }
  }

  @Override
  public void combineImages(List<String> srcImageNames, String imageDestName) {
    List<Image> srcImageList = new ArrayList<>();
    Image firstSrcImage = imageMap.get(srcImageNames.get(0));
    for (int i = 1; i < srcImageNames.size(); i++) {
      srcImageList.add(imageMap.get(srcImageNames.get(i)));
    }
    Image newImage = firstSrcImage.combine(srcImageList);
    imageMap.put(imageDestName, newImage);
  }

  @Override
  public void brightenImage(String imageNameSrc, String imageNameDest, float brightnessConstant) {
    Image newImage = imageMap.get(imageNameSrc).brighten(brightnessConstant);
    imageMap.put(imageNameDest, newImage);
  }

  @Override
  public void blurImage(String imageNameSrc, String imageNameDest) {
    Image newImage = imageMap.get(imageNameSrc).blur();
    imageMap.put(imageNameDest, newImage);
  }

  @Override
  public void sharpenImage(String imageNameSrc, String imageNameDest) {
    Image newImage = imageMap.get(imageNameSrc).sharpen();
    imageMap.put(imageNameDest, newImage);
  }

  @Override
  public void flipImageHorizontally(String imageNameSrc, String imageNameDest) {
    Image newImage = imageMap.get(imageNameSrc).flipHorizontally();
    imageMap.put(imageNameDest, newImage);
  }

  @Override
  public void flipImageVertically(String imageNameSrc, String imageNameDest) {
    Image newImage = imageMap.get(imageNameSrc).flipVertically();
    imageMap.put(imageNameDest, newImage);
  }

  @Override
  public void toIntensityGreyScale(String imageNameSrc, String imageNameDest) {
    Image newImage = imageMap.get(imageNameSrc).getIntensityImage();
    imageMap.put(imageNameDest, newImage);
  }

  @Override
  public void toLumaGreyScale(String imageNameSrc, String imageNameDest) {
    Image newImage = imageMap.get(imageNameSrc).getLumaImage();
    imageMap.put(imageNameDest, newImage);
  }

  @Override
  public void toValueGreyScale(String imageNameSrc, String imageNameDest) {
    Image newImage = imageMap.get(imageNameSrc).getValueImage();
    imageMap.put(imageNameDest, newImage);
  }

  @Override
  public void toSepiaImage(String imageNameSrc, String imageNameDest) {
    Image newImage = imageMap.get(imageNameSrc).getSepia();
    imageMap.put(imageNameDest, newImage);
  }

  @Override
  public void toRedChannelImage(String imageNameSrc, String imageNameDest) {
    Image newImage = imageMap.get(imageNameSrc).getRedComponent();
    imageMap.put(imageNameDest, newImage);
  }

  @Override
  public void toGreenChannelImage(String imageNameSrc, String imageNameDest) {
    Image newImage = imageMap.get(imageNameSrc).getGreenComponent();
    imageMap.put(imageNameDest, newImage);
  }

  @Override
  public void toBlueChannelImage(String imageNameSrc, String imageNameDest) {
    Image newImage = imageMap.get(imageNameSrc).getBlueComponent();
    imageMap.put(imageNameDest, newImage);
  }
}
