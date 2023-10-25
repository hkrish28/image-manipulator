package ime;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageImpl implements Image {

  private final FileHandlerProvider fileHandlerProvider;
  private final int width;
  private final int height;
  private List<ColorChannel> colorChannels;

  public ImageImpl(List<ColorChannel> colorChannels) {
    this.colorChannels = colorChannels;
    width = colorChannels.get(0).getWidth();
    height = colorChannels.get(0).getHeight();
    fileHandlerProvider = new FileHandlerProviderImpl();
  }

  public ImageImpl(String fileName) throws FileNotFoundException {
    fileHandlerProvider = new FileHandlerProviderImpl();
    List<ColorChannel> colorChannels =
            fileHandlerProvider.getFileHandler(fileName).loadFile(fileName);
    this.colorChannels = colorChannels;
    width = colorChannels.get(0).getWidth();
    height = colorChannels.get(0).getHeight();
  }

  @Override
  public Image blur() {
    return null;
  }

  @Override
  public Image sharpen() {
    return null;
  }

  @Override
  public Image flipHorizontally() {
    return null;
  }

  @Override
  public Image flipVertically() {
    return null;
  }

  @Override
  public float getPixelValue(int colorChannel,int row, int col) {
    return colorChannels.get(colorChannel).get(row,col);
  }

  @Override
  public float getPixelIntensity(int row, int col) {
    return 0;
  }

  @Override
  public float getPixelLuma(int row, int col) {
    return 0;
  }

//  @Override
//  public void loadImage(String filename) throws FileNotFoundException {
//
//
//  }

  @Override
  public void saveImage(String filename) throws IOException {
    fileHandlerProvider.getFileHandler(filename).saveFile(this,filename);
  }

  @Override
  public List<Image> splitIntoColorChannels() {
    return null;
  }

//  @Override
//  public void combine(List<Image> colorChannelImages) {
//
//  }

  @Override
  public Image brighten(float brightnessConstant) {
    List<ColorChannel> resultingChannels = new ArrayList<>();
    for(int i = 0; i < colorChannels.size(); i++){
      resultingChannels.add(colorChannels.get(i).brighten(brightnessConstant));
    }
    return new ImageImpl(resultingChannels);
  }

  @Override
  public Image darken(float darknessConstant) {
    return null;
  }

  @Override
  public Image greyScale() {
    return null;
  }

  @Override
  public Image sepia() {
    return null;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }


  /**
   * Given a filter, apply it to the image and return the result which is a new image.
   *
   * @param filter the filter to be applied.
   * @return the image result after performing the filter on the original image.
   */
  private Image applyFilter(ColorChannel filter) {
    return null;
  }

  /**
   * Given a list of coefficients for the color channels, return an image that is the color
   * transformed version of this image.
   *
   * @param transformCoefficients the matrix containing the coefficients for the color channels
   * @return the ime.Image after color transformation
   */
  private Image performColorTransformation(ColorChannel transformCoefficients) {
    return null;
  }

  public int getColorChannelCount(){
    return this.colorChannels.size();
  }
}
