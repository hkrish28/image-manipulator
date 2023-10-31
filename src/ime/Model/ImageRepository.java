package ime.Model;

import java.io.IOException;
import java.util.List;

/**
 * An Image Repository is a class that manages multiple images and performs operations on them.
 * It is able to tag an image to a particular name and also save images that it has already tagged.
 */
public interface ImageRepository {

  /**
   * This method is used to load an image at a given file path and tag it the given name for the
   * image.
   * @param filePath the path at which the file is present to be loaded.
   * @param imageName the name for the image to be associated with
   * @throws IOException if any error occurs during the loading of the image or if the file does not
   * exist at the file path.
   */
  void loadImage(String filePath, String imageName) throws IOException;

  /**
   * This method is used to save a tagged image to a given file path.
   * @param filePath the path at which the image is to be saved.
   * @param imageName the name of the image to be saved.
   * @throws IOException if any error occurs during the loadig of the image or if the file does not
   * exist at the file path.
   */
  void saveImage(String filePath, String imageName) throws IOException;

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
   * Blur the source image using appropriate filter and save the blurred image into destination.
   * @param imageNameSrc source image name
   * @param imageNameDest destination image name
   */
  void blurImage(String imageNameSrc, String imageNameDest);

  /**
   * Sharpen the source image using appropriate filter and save the sharpened image into
   * the destination image.
   * @param imageNameSrc source image name
   * @param imageNameDest destination image name
   */
  void sharpenImage(String imageNameSrc, String imageNameDest);

  /**
   * Horizontally flip the source image and save it into destination image.
   * @param imageNameSrc source image name
   * @param imageNameDest destination image name
   */
  void flipImageHorizontally(String imageNameSrc, String imageNameDest);

  /**
   * Vertically flip the source image and save it into destination image.
   * @param imageNameSrc source image name
   * @param imageNameDest destination image name
   */
  void flipImageVertically(String imageNameSrc, String imageNameDest);

  /**
   * Transform the source image into its intensity greyscale and save it into destination image.
   * @param imageNameSrc source image name
   * @param imageNameDest destination image name
   */
  void toIntensityGreyScale(String imageNameSrc, String imageNameDest);

  /**
   * Transform the source image into its luma greyscale and save it into the destination image.
   * @param imageNameSrc source image name
   * @param imageNameDest destination image name
   */
  void toLumaGreyScale(String imageNameSrc, String imageNameDest);

  /**
   * Transform the source image into its value greyscale and save it into the destination image.
   * @param imageNameSrc source image name
   * @param imageNameDest destination image name
   */
  void toValueGreyScale(String imageNameSrc, String imageNameDest);

  /**
   * Transform the source image into sepia and save it into the destination image.
   * @param imageNameSrc source image name
   * @param imageNameDest destination image name
   */
  void toSepiaImage(String imageNameSrc, String imageNameDest);

  /**
   * Get the red component image of the source image and save it into destination.
   * @param imageNameSrc source image name
   * @param imageNameDest destination image name
   */
  void toRedChannelImage(String imageNameSrc, String imageNameDest);

  /**
   * Get the green component image of the source image and save it into destination.
   * @param imageNameSrc source image name
   * @param imageNameDest destination image name
   */
  void toGreenChannelImage(String imageNameSrc, String imageNameDest);

  /**
   * Get the blue component image of the source image and save it into destination.
   * @param imageNameSrc source image name
   * @param imageNameDest destination image name
   */
  void toBlueChannelImage(String imageNameSrc, String imageNameDest);
}
