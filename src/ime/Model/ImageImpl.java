//package ime;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ImageImpl implements Image {
//
//  private final FileHandlerProvider fileHandlerProvider;
//  private final int width;
//  private final int height;
//  private List<ColorChannel> colorChannels;
//
//  public ImageImpl(List<ColorChannel> colorChannels) {
//    this.colorChannels = colorChannels;
//    width = colorChannels.get(0).getWidth();
//    height = colorChannels.get(0).getHeight();
//    fileHandlerProvider = new FileHandlerProviderImpl();
//  }
//
//  public ImageImpl(String fileName) throws FileNotFoundException {
//    fileHandlerProvider = new FileHandlerProviderImpl();
//    List<ColorChannel> colorChannels =
//            fileHandlerProvider.getFileHandler(fileName).loadFile(fileName);
//    this.colorChannels = colorChannels;
//    width = colorChannels.get(0).getWidth();
//    height = colorChannels.get(0).getHeight();
//  }
//
//  @Override
//  public Image blur() {
//    return null;
//  }
//
//  @Override
//  public Image sharpen() {
//    return null;
//  }
//
//  @Override
//  public Image flipHorizontally() {
//    List<ColorChannel> resultingChannels = new ArrayList<>();
//    for (int i = 0; i < colorChannels.size(); i++) {
//      resultingChannels.add(colorChannels.get(i).reverseHorizontally());
//    }
//
//    return new ImageImpl(resultingChannels);
//  }
//
//  @Override
//  public Image flipVertically() {
//
//    List<ColorChannel> resultingChannels = new ArrayList<>();
//    for (int i = 0; i < colorChannels.size(); i++) {
//      resultingChannels.add(colorChannels.get(i).reverseVertically());
//    }
//
//    return new ImageImpl(resultingChannels);
//  }
//
//  @Override
//  public float getPixelValue(int colorChannel, int row, int col) {
//    return colorChannels.get(colorChannel).get(row, col);
//  }
//
//  @Override
//  public Image getIntensityImage() {
//    return null;
//  }
//
//  @Override
//  public Image getLumaImage() {
//    return null;
//  }
//
//  @Override
//  public void saveImage(String filename) throws IOException {
//    fileHandlerProvider.getFileHandler(filename).saveFile(this, filename);
//  }
//
//  @Override
//  public List<Image> splitIntoColorChannels() {
//    List<Image> result = new ArrayList<>();
//    for (int i = 0; i < getColorChannelCount(); i++) {
//      List<ColorChannel> singleColorChannel = new ArrayList<>();
//      for (int j = 0; j < i; j++) {
//        singleColorChannel.add(new ArrayColorChannel(width, height));
//      }
//      singleColorChannel.add(this.colorChannels.get(i));
//      for (int j = i + 1; j < getColorChannelCount(); j++) {
//        singleColorChannel.add(new ArrayColorChannel(width, height));
//      }
//      result.add(new ImageImpl(singleColorChannel));
//    }
//    return result;
//  }
//
////  @Override
////  public void combine(List<Image> colorChannelImages) {
////
////  }
//
//  @Override
//  public Image brighten(float brightnessConstant) {
//    List<ColorChannel> resultingChannels = new ArrayList<>();
//    for (int i = 0; i < colorChannels.size(); i++) {
//      resultingChannels.add(colorChannels.get(i).brighten(brightnessConstant));
//    }
//    return new ImageImpl(resultingChannels);
//  }
//
//  @Override
//  public Image darken(float darknessConstant) {
//    return this.brighten(-1 * darknessConstant);
//  }
//
//  @Override
//  public Image getValueImage() {
//    return null;
//  }
//
//  @Override
//  public Image getSepia() {
//    return null;
//  }
//
//  @Override
//  public int getWidth() {
//    return this.width;
//  }
//
//  @Override
//  public int getHeight() {
//    return this.height;
//  }
//
//
//  /**
//   * Given a filter, apply it to the image and return the result which is a new image.
//   *
//   * @param filter the filter to be applied.
//   * @return the image result after performing the filter on the original image.
//   */
//  private Image applyFilter(ColorChannel filter) {
//    return null;
//  }
//
//  /**
//   * Given a list of coefficients for the color channels, return an image that is the color
//   * transformed version of this image.
//   *
//   * @param transformCoefficients the matrix containing the coefficients for the color channels
//   * @return the ime.Image after color transformation
//   */
//  private Image performColorTransformation(ColorChannel transformCoefficients) {
//    return null;
//  }
//
//  public int getColorChannelCount() {
//    return this.colorChannels.size();
//  }
//}
