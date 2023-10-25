package ime;

import java.io.File;
import java.util.List;

public class ImageImpl implements Image {

  private Matrix<Pixel> pixels;

  public ImageImpl(Matrix<Pixel> pixels){
    this.pixels = pixels;
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
  public float getPixelValue(int row, int col) {
    return 0;
  }

  @Override
  public float getPixelIntensity(int row, int col) {
    return 0;
  }

  @Override
  public float getPixelLuma(int row, int col) {
    return 0;
  }

  @Override
  public void loadImage(File filename) {

  }

  @Override
  public void saveImage(File filename) {

  }

  @Override
  public List<Image> splitIntoColorChannels() {
    return null;
  }

  @Override
  public void combine(List<Image> colorChannelImages) {

  }

  @Override
  public Image brighten(float brightnessConstant) {
    return null;
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
    return 0;
  }

  @Override
  public int getHeight() {
    return 0;
  }


    /**
   * Given a filter, apply it to the image and return the result which is a new image.
   * @param filter the filter to be applied.
   * @return the image result after performing the filter on the original image.
   */
  private Image applyFilter(Matrix filter){
    return null;
  }

  /**
   * Given a list of coefficients for the color channels, return an image that is the color
   * transformed version of this image.
   * @param transformCoefficients the matrix containing the coefficients for the color channels
   * @return the ime.Image after color transformation
   */
  private Image performColorTransformation(Matrix transformCoefficients){
    return null;
  }
}
