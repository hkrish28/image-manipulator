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
    methodCallLogger.append("loadImage called "+ filePath + " and " + imageName + " passed\n");
    if(fail) {
      throw new IOException("Image Repository failed");
    }
  }

  @Override
  public void saveImage(String filePath, String imageName) throws IOException {
    methodCallLogger.append("saveImage called "+ filePath + " and " + imageName + " passed\n");
    if(fail){
    throw new IOException("Image Repository failed");}
  }

  @Override
  public void splitImageIntoColorChannels(String srcImage, List<String> destImageNames) {
    methodCallLogger.append("splitImage called "+ srcImage + " and " + destImageNames + " passed\n");
    if(fail){
    throw new IllegalArgumentException("Image Repository failed");}
  }

  @Override
  public void combineImages(List<String> images, String imageDestName) {
    methodCallLogger.append("combineImage called "+ images + " and " + imageDestName + " passed\n");
    if(fail){
    throw new IllegalArgumentException("Image Repository failed");}
  }

  @Override
  public void brightenImage(String imageNameSrc, String imageNameDest, float brightnessConstant) {
    methodCallLogger.append("brightenImage called "+ imageNameSrc + " and " + imageNameDest + " passed\n");
    if(fail){
    throw new IllegalArgumentException("Source Name invalid");}
  }

  @Override
  public void blurImage(String imageNameSrc, String imageNameDest) {
    methodCallLogger.append("blurImage called "+ imageNameSrc + " and " + imageNameDest + " passed\n");
    if(fail){
    throw new IllegalArgumentException("Source Name invalid");}
  }

  @Override
  public void sharpenImage(String imageNameSrc, String imageNameDest) {
    methodCallLogger.append("sharpenImage called "+ imageNameSrc + " and " + imageNameDest + " passed\n");
    if(fail){
    throw new IllegalArgumentException("Source Name invalid");}
  }

  @Override
  public void flipImageHorizontally(String imageNameSrc, String imageNameDest) {
    methodCallLogger.append("loadImage called "+ imageNameSrc + " and " + imageNameDest + " passed\n");
    if(fail){
    throw new IllegalArgumentException("Source Name invalid");}
  }

  @Override
  public void flipImageVertically(String imageNameSrc, String imageNameDest) {
    methodCallLogger.append("loadImage called "+ imageNameSrc + " and " + imageNameDest + " passed\n");
    if(fail){
    throw new IllegalArgumentException("Source Name invalid");}
  }

  @Override
  public void toIntensityGreyScale(String imageNameSrc, String imageNameDest) {
    methodCallLogger.append("loadImage called "+ imageNameSrc + " and " + imageNameDest + " passed\n");
    if(fail){
    throw new IllegalArgumentException("Source Name invalid");}
  }

  @Override
  public void toLumaGreyScale(String imageNameSrc, String imageNameDest) {
    methodCallLogger.append("loadImage called "+ imageNameSrc + " and " + imageNameDest + " passed\n");
    if(fail){
    throw new IllegalArgumentException("Source Name invalid");}
  }

  @Override
  public void toValueGreyScale(String imageNameSrc, String imageNameDest) {
    methodCallLogger.append("loadImage called "+ imageNameSrc + " and " + imageNameDest + " passed\n");
    if(fail){
    throw new IllegalArgumentException("Source Name invalid");}
  }

  @Override
  public void toSepiaImage(String imageNameSrc, String imageNameDest) {
    methodCallLogger.append("loadImage called "+ imageNameSrc + " and " + imageNameDest + " passed\n");
    if(fail){
    throw new IllegalArgumentException("Source Name invalid");}
  }

  @Override
  public void toRedChannelImage(String imageNameSrc, String imageNameDest) {
    methodCallLogger.append("loadImage called "+ imageNameSrc + " and " + imageNameDest + " passed\n");
    if(fail){
    throw new IllegalArgumentException("Source Name invalid");}
  }

  @Override
  public void toGreenChannelImage(String imageNameSrc, String imageNameDest) {
    methodCallLogger.append("loadImage called "+ imageNameSrc + " and " + imageNameDest + " passed\n");
    if(fail){
    throw new IllegalArgumentException("Source Name invalid");}
  }

  @Override
  public void toBlueChannelImage(String imageNameSrc, String imageNameDest) {
    methodCallLogger.append("loadImage called "+ imageNameSrc + " and " + imageNameDest + " passed\n");
    if(fail){
    throw new IllegalArgumentException("Source Name invalid");}
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
