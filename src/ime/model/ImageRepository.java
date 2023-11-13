package ime.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * An Image Repository is a class that manages multiple images and performs operations on them. It
 * is able to tag an image to a particular name and also save images that it has already tagged.
 */
public interface ImageRepository {

  /**
   * This method is used to load an image at a given file path and tag it the given name for the
   * image.
   *
   * @param filePath  the path at which the file is present to be loaded.
   * @param imageName the name for the image to be associated with
   * @throws IOException if any error occurs during the loading of the image or if the file does not
   *                     exist at the file path.
   */
//  void loadImage(String filePath, String imageName);

  void loadImage(BufferedImage image, String imageName );

  /**
   * This method is used to save a tagged image to a given file path.
   *
   * @param filePath  the path at which the image is to be saved.
   * @param imageName the name of the image to be saved.
   * @throws IOException              if any error occurs during the loading of the image or if the
   *                                  file does not exist at the file path.
   * @throws IllegalArgumentException if imageName has not been created/tagged yet.
   */
//  void saveImage(String filePath, String imageName);

  BufferedImage getImage(String imageName);

  /**
   * Split this image into its respective color channels and store the resulting images into the
   * destination image names.
   *
   * @throws IllegalArgumentException if srcImage has not been created/tagged yet.
   */
  void splitImageIntoColorChannels(String srcImage, List<String> destImageNames)
      throws IllegalArgumentException;

  /**
   * Combine the color channels images into o single image and save it into the destination image
   * name provided.
   *
   * @param images List of images to be combined.
   * @throws IllegalArgumentException if {@param images} has not been created/tagged yet.
   */
  void combineImages(List<String> images, String imageDestName)
      throws IllegalArgumentException;

  /**
   * Brighten this image by the given brightness constant and tag the new image as
   * {@param imageNameDest}.
   *
   * @param brightnessConstant the constant factor for the image to be brightened by
   * @throws IllegalArgumentException if {@param imageNameSrc} has not been created/tagged yet.
   */
  void brightenImage(String imageNameSrc, String imageNameDest, float brightnessConstant)
      throws IllegalArgumentException;


  /**
   * Blur the source image using appropriate filter and save the blurred image into destination.
   *
   * @param imageNameSrc  source image name
   * @param imageNameDest destination image name
   */
  void blurImage(String imageNameSrc, String imageNameDest)
      throws IllegalArgumentException;

  /**
   * Sharpen the source image using appropriate filter and save the sharpened image into the
   * destination image.
   *
   * @param imageNameSrc  source image name
   * @param imageNameDest destination image name
   * @throws IllegalArgumentException if {@param imageNameSrc} has not been created/tagged yet.
   */
  void sharpenImage(String imageNameSrc, String imageNameDest)
      throws IllegalArgumentException;

  /**
   * Horizontally flip the source image and save it into destination image.
   *
   * @param imageNameSrc  source image name
   * @param imageNameDest destination image name
   * @throws IllegalArgumentException if {@param imageNameSrc} has not been created/tagged yet.
   */
  void flipImageHorizontally(String imageNameSrc, String imageNameDest)
      throws IllegalArgumentException;

  /**
   * Vertically flip the source image and save it into destination image.
   *
   * @param imageNameSrc  source image name
   * @param imageNameDest destination image name
   * @throws IllegalArgumentException if {@param imageNameSrc} has not been created/tagged yet.
   */
  void flipImageVertically(String imageNameSrc, String imageNameDest)
      throws IllegalArgumentException;

  /**
   * Transform the source image into its intensity greyscale and save it into destination image.
   *
   * @param imageNameSrc  source image name
   * @param imageNameDest destination image name
   * @throws IllegalArgumentException if {@param imageNameSrc} has not been created/tagged yet.
   */
  void toIntensityGreyScale(String imageNameSrc, String imageNameDest)
      throws IllegalArgumentException;

  /**
   * Transform the source image into its luma greyscale and save it into the destination image.
   *
   * @param imageNameSrc  source image name
   * @param imageNameDest destination image name
   * @throws IllegalArgumentException if {@param imageNameSrc} has not been created/tagged yet.
   */
  void toLumaGreyScale(String imageNameSrc, String imageNameDest)
      throws IllegalArgumentException;

  /**
   * Transform the source image into its value greyscale and save it into the destination image.
   *
   * @param imageNameSrc  source image name
   * @param imageNameDest destination image name
   * @throws IllegalArgumentException if {@param imageNameSrc} has not been created/tagged yet.
   */
  void toValueGreyScale(String imageNameSrc, String imageNameDest)
      throws IllegalArgumentException;

  /**
   * Transform the source image into sepia and save it into the destination image.
   *
   * @param imageNameSrc  source image name
   * @param imageNameDest destination image name
   * @throws IllegalArgumentException if {@param imageNameSrc} has not been created/tagged yet.
   */
  void toSepiaImage(String imageNameSrc, String imageNameDest)
      throws IllegalArgumentException;

  /**
   * Get the red component image of the source image and save it into destination.
   *
   * @param imageNameSrc  source image name
   * @param imageNameDest destination image name
   * @throws IllegalArgumentException if {@param imageNameSrc} has not been created/tagged yet.
   */
  void toRedChannelImage(String imageNameSrc, String imageNameDest)
      throws IllegalArgumentException;

  /**
   * Get the green component image of the source image and save it into destination.
   *
   * @param imageNameSrc  source image name
   * @param imageNameDest destination image name
   * @throws IllegalArgumentException if {@param imageNameSrc} has not been created/tagged yet.
   */
  void toGreenChannelImage(String imageNameSrc, String imageNameDest)
      throws IllegalArgumentException;

  /**
   * Get the blue component image of the source image and save it into destination.
   *
   * @param imageNameSrc  source image name
   * @param imageNameDest destination image name
   * @throws IllegalArgumentException if {@param imageNameSrc} has not been created/tagged yet.
   */
  void toBlueChannelImage(String imageNameSrc, String imageNameDest)
      throws IllegalArgumentException;

  /**
   * Returns whether the given image name has been created/tagged in the repository yet.
   *
   * @param imageNameSrc image name to be searched for.
   * @return whether the image is present in the repository.
   */
  boolean isImagePresent(String imageNameSrc);

  void compress(String imageNameSrc, String imageNameDest, int compressPercent)
      throws IllegalArgumentException;

  void preview(String imageNameSrc, String imageNameDest, BiConsumer<String, String> operation,
      int verticalSplit);

  void levelsAdjust(String imageNameSrc, String destImage, int b, int m, int w);

  void colorCorrect(String imageNameSrc, String imageNameDest);

  void toHistogram(String imageNameSrc, String imageNameDest);
}
