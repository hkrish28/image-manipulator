package ime.model;
import java.util.List;

/**
 * This class represents an image and provides some operations that can performed on it.
 */
public interface Image {

  /**
   * Get the width of the image.
   *
   * @return the width of the image
   */
  int getWidth();

  /**
   * Get the height of the image.
   *
   * @return the height of the image
   */
  int getHeight();

  /**
   * Split this image into its respective color channels.
   *
   * @return the list of images for each of its color channels
   */
  List<Image> splitIntoColorChannels();

  /**
   * Combine the color channels images into o single image.
   *
   * @param images List of images to be combined.
   */
  Image combine(List<Image> images);

  /**
   * Brighten this image by the given brightness constant and return the new image.
   *
   * @param brightnessConstant the constant factor for the image to be brightened by
   */
  Image brighten(float brightnessConstant);

  /**
   * Blur the image using appropriate filter and return the new blurred image.
   *
   * @return blurred copy of the original.
   */
  Image blur();

  /**
   * Sharpen the image using appropriate filter and return the new blurred image.
   *
   * @return blurred copy of the original.
   */
  Image sharpen();

  /**
   * Horizontally flip the image and return the new flipped image.
   *
   * @return horizontally flipped image
   */
  Image flipHorizontally();

  /**
   * Vertically flip the image and return the new flipped image.
   *
   * @return vertically flipped image
   */
  Image flipVertically();

  /**
   * For a given position, find the value of the pixel for a given color channel.
   *
   * @param row the row position of the pixel
   * @param col the column position of the pixel
   * @return the value of the pixel
   */
  float[] getPixelValues(int row, int col);

  /**
   * Convert this image into its intensity greyscale and return the copy of it.
   *
   * @return the greyscale image of its intensity
   */
  Image getIntensityImage();

  /**
   * Convert this image into its luma greyscale and return the copy of it.
   *
   * @return the greyscale image of its luma
   */
  Image getLumaImage();

  /**
   * Convert this image into its value greyscale and return the copy of it.
   *
   * @return the greyscale image of its values
   */
  Image getValueImage();

  /**
   * Convert this image into sepia and return the copy of it.
   *
   * @return the sepia image of this image object
   */
  Image getSepia();

  /**
   * Get the number of color channels in the image.
   *
   * @return the number of color channels
   */
  int getChannelCount();


  /**
   * Return the red component of the image as a new copy. Original image should not be changed.
   *
   * @return red component of the image
   */
  Image getRedComponent();

  /**
   * Return the green component of the image as a new copy. Original image should not be changed.
   *
   * @return green component of the image
   */
  Image getGreenComponent();

  /**
   * Return the blue component of the image as a new copy. Original image should not be changed.
   *
   * @return blue component of the image
   */
  Image getBlueComponent();

  /**
   * Return a compressed version of the image. Compression size depends on the compression
   * percentage provided.
   * @param compressPercent The percentage of compression
   * @return compressed image
   */
  Image compress(int compressPercent);

  /**
   * Split the image vertically according to the split position given. If splitPercent is greater
   * than 100 or lesser than 0, a copy of the same image will be returned.
   * @param splitPercent the percentage of the split. 50 will split the image into 2 halves.
   * @return images that have been split vertically at the position given
   */
  List<Image> splitVertically(int splitPercent);

  /**
   * Return an image that is the given image of the same height appended to the right of this image.
   * @param image image to be appended to the right of this image
   * @return appended version of the image
   */
  Image append(Image image);

  ImageType getImageType();
}
