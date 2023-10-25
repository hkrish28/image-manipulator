package ime;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * This class represents an image and provides some operations that can performed on it.
 */
public interface Image {

  /**
   * Get the width of the image.
   * @return the width of the image
   */
  int getWidth();

  /**
   * Get the height of the image.
   * @return the height of the image
   */
  int getHeight();

//  /**
//   * Given an image of supported extension, load the contents into the object.
//   * @param filename the image file
//   */
//  void loadImage(String filename) throws FileNotFoundException;

  /**
   * Save this object into the given file.
   * @param filename the file to save this object into
   */
  void saveImage(String filename) throws IOException;

  /**
   * Split this image into its respective color channels.
   * @return the list of images for each of its color channels
   */
  List<Image> splitIntoColorChannels();

//  /**
//   * Combine the color channels into o single image.
//   * @param colorChannelImages List of images to be combined.
//   */
//  void combine(List<Image> colorChannelImages);

  /**
   * Brighten this image by the given brightness constant and return the new image.
   * @param brightnessConstant the constant factor for the image to be brightened by
   */
  Image brighten(float brightnessConstant);

  /**
   * Darken this image by the given darkness constant and return the new image.
   * @param darknessConstant the constant factor for the image to be darkened by
   */
  Image darken(float darknessConstant);

  /**
   * Blur the image using appropriate filter and return the new blurred image.
   * @return blurred copy of the original.
   */
  Image blur();

  /**
   * Sharpen the image using appropriate filter and return the new blurred image.
   * @return blurred copy of the original.
   */
  Image sharpen();

  /**
   * Horizontally flip the image and return the new flipped image.
   * @return horizontally flipped image
   */
  Image flipHorizontally();

  /**
   * Vertically flip the image and return the new flipped image.
   * @return vertically flipped image
   */
  Image flipVertically();

  /**
   * For a given position, find the value of the pixel for a given color channel.
   * @param colorChannel the colorChannel of the pixel
   * @param row the row position of the pixel
   * @param col the column position of the pixel
   * @return the value of the pixel
   */
  float getPixelValue(int colorChannel, int row, int col);

  /**
   * For a given position, find the intensity of the pixel.
   * @param row the row position of the pixel
   * @param col the column position of the pixel
   * @return the intensity of the pixel
   */
  float getPixelIntensity(int row, int col);

  /**
   * For a given position, find the luma of the pixel.
   * @param row the row position of the pixel
   * @param col the column position of the pixel
   * @return the luma of the pixel
   */
  float getPixelLuma(int row, int col);

  /**
   * Convert this image into greyscale and return the copy of it.
   * @return the greyscale image of this image object
   */
  Image greyScale();

  /**
   * Convert this image into sepia and return the copy of it.
   * @return the sepia image of this image object
   */
  Image sepia();

  int getColorChannelCount();


}
