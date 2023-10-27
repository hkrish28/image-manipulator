package ime.Model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ImageRepository {
  void loadImage(String fileName, String imageName) throws FileNotFoundException;

  void saveImage(String fileName, String imageName) throws IOException;

  /**
   * Split this image into its respective color channels.
   * @return the list of images for each of its color channels
   */
  void splitImageIntoColorChannels(String srcImage, List<String> destImageNames);

  /**
   * Combine the color channels images into o single image.
   * @param images List of images to be combined.
   */
  void combineImages(List<String> images, String imageDestName);

  /**
   * Brighten this image by the given brightness constant and return the new image.
   * @param brightnessConstant the constant factor for the image to be brightened by
   */
  void brightenImage(String imageNameSrc, String imageNameDest, float brightnessConstant);

  /**
   * Darken this image by the given darkness constant and return the new image.
   * @param darknessConstant the constant factor for the image to be darkened by
   */
  void darkenenImage(String imageNameSrc, String imageNameDest, float darknessConstant);

  /**
   * Blur the image using appropriate filter and return the new blurred image.
   * @return blurred copy of the original.
   */
  void blurImage(String imageNameSrc, String imageNameDest);

  /**
   * Sharpen the image using appropriate filter and return the new blurred image.
   * @return blurred copy of the original.
   */
  void sharpenImage(String imageNameSrc, String imageNameDest);

  /**
   * Horizontally flip the image and return the new flipped image.
   * @return horizontally flipped image
   */
  void flipImageHorizontally(String imageNameSrc, String imageNameDest);

  /**
   * Vertically flip the image and return the new flipped image.
   * @return vertically flipped image
   */
  void flipImageVertically(String imageNameSrc, String imageNameDest);

  /**
   * Convert this image into its intensity greyscale and return the copy of it.
   * @return the greyscale image of its intensity
   */
  void toIntensityGreyScale(String imageNameSrc, String imageNameDest);

  /**
   * Convert this image into its luma greyscale and return the copy of it.
   * @return the greyscale image of its luma
   */
  void toLumaGreyScale(String imageNameSrc, String imageNameDest);

  /**
   * Convert this image into its value greyscale and return the copy of it.
   * @return the greyscale image of its values
   */
  void toValueGreyScale(String imageNameSrc, String imageNameDest);

  /**
   * Convert this image into sepia and return the copy of it.
   * @return the sepia image of this image object
   */
  void toSepiaImage(Image imageNameSrc, String imageNameDest);
}
