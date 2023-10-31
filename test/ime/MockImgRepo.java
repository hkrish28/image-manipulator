package ime;

import ime.Model.ImageRepository;
import java.io.IOException;
import java.util.List;

public class MockImgRepo implements ImageRepository {


  StringBuilder methodCallLogger;
  Boolean fail;

  public MockImgRepo(){
    methodCallLogger = new StringBuilder();
    fail = false;
  }
  @Override
  public void loadImage(String filePath, String imageName) throws IOException {
    methodCallLogger.append("loadImage called "+ filePath + " and " + imageName + " passed");
    if(fail) {
      throw new IOException("Image Repository failed");
    }
  }

  @Override
  public void saveImage(String filePath, String imageName) throws IOException {
    throw new IOException("Image Repository failed");
  }

  @Override
  public void splitImageIntoColorChannels(String srcImage, List<String> destImageNames) {
    throw new IllegalArgumentException("Image Repository failed");
  }

  @Override
  public void combineImages(List<String> images, String imageDestName) {
    throw new IllegalArgumentException("Image Repository failed");
  }

  @Override
  public void brightenImage(String imageNameSrc, String imageNameDest, float brightnessConstant) {
    throw new IllegalArgumentException("Source Name invalid");
  }

  @Override
  public void blurImage(String imageNameSrc, String imageNameDest) {
    throw new IllegalArgumentException("Source Name invalid");
  }

  @Override
  public void sharpenImage(String imageNameSrc, String imageNameDest) {
    throw new IllegalArgumentException("Source Name invalid");
  }

  @Override
  public void flipImageHorizontally(String imageNameSrc, String imageNameDest) {
    throw new IllegalArgumentException("Source Name invalid");
  }

  @Override
  public void flipImageVertically(String imageNameSrc, String imageNameDest) {
    throw new IllegalArgumentException("Source Name invalid");
  }

  @Override
  public void toIntensityGreyScale(String imageNameSrc, String imageNameDest) {
    throw new IllegalArgumentException("Source Name invalid");
  }

  @Override
  public void toLumaGreyScale(String imageNameSrc, String imageNameDest) {
    throw new IllegalArgumentException("Source Name invalid");
  }

  @Override
  public void toValueGreyScale(String imageNameSrc, String imageNameDest) {
    throw new IllegalArgumentException("Source Name invalid");
  }

  @Override
  public void toSepiaImage(String imageNameSrc, String imageNameDest) {
    throw new IllegalArgumentException("Source Name invalid");
  }

  @Override
  public void toRedChannelImage(String imageNameSrc, String imageNameDest) {
    throw new IllegalArgumentException("Source Name invalid");
  }

  @Override
  public void toGreenChannelImage(String imageNameSrc, String imageNameDest) {
    throw new IllegalArgumentException("Source Name invalid");
  }

  @Override
  public void toBlueChannelImage(String imageNameSrc, String imageNameDest) {
    throw new IllegalArgumentException("Source Name invalid");
  }

  public String getLogger(){
    return methodCallLogger.toString();
  }

  public void clearLogger(){
    methodCallLogger = new StringBuilder();
  }

  public void setFailureFlag(Boolean failFlag) {
    this.fail = failFlag;
  }
}
