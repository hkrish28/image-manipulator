package ime.controller;

import java.io.IOException;
import java.util.List;

import ime.model.ImageRepository;

/**
 * A mock implementation of the {@link ImageRepository} interface used for testing purposes. This
 * class allows tracking method calls and simulating failures for various image processing
 * operations.
 */
public class MockImgRepo implements ImageRepository {


  StringBuilder methodCallLogger;
  Boolean fail;

  /**
   * Creates a new instance of the MockImgRepo.
   */
  public MockImgRepo() {
    methodCallLogger = new StringBuilder();
    fail = false;
  }

  /**
   * Loads an image from the specified file and logs the method call.
   *
   * @param filePath  The path to the image file to load.
   * @param imageName The name to associate with the loaded image.
   * @throws IOException If an error occurs during the loading process or if the MockImgRepo is set
   *                     to fail.
   */
  @Override
  public void loadImage(String filePath, String imageName) throws IOException {
    methodCallLogger.append("loadImage called " + filePath + " and " + imageName + " passed\n");
    if (fail) {
      throw new IOException("Image Repository failed");
    }
  }

  /**
   * Saves an image to the specified file and logs the method call.
   *
   * @param filePath  The path to the file where the image should be saved.
   * @param imageName The name of the image to be saved.
   * @throws IOException If an error occurs during the saving process or if the MockImgRepo is set
   *                     to fail.
   */

  @Override
  public void saveImage(String filePath, String imageName)
          throws IOException, IllegalArgumentException {
    methodCallLogger.append("saveImage called " + filePath + " and " + imageName + " passed\n");
    if (fail) {
      throw new IOException("Image Repository failed");
    }
  }

  /**
   * Splits the source image into multiple color channels and associates them with the specified
   * destination image names.
   *
   * @param srcImage       The name of the source image to be split.
   * @param destImageNames A list of names for the destination images.
   * @throws IllegalArgumentException If the operation fails due to an issue specified by the
   *                                  MockImgRepo or if the source image is invalid.
   */
  @Override
  public void splitImageIntoColorChannels(String srcImage, List<String> destImageNames) {
    methodCallLogger.append(
            "splitImage called " + srcImage + " and " + destImageNames + " passed\n");
    if (fail) {
      throw new IllegalArgumentException("Image Repository failed");
    }
  }

  /**
   * Combines a list of source images into a new image and associates it with the specified
   * destination image name.
   *
   * @param images        A list of image names to be combined.
   * @param imageDestName The name to associate with the combined image.
   * @throws IllegalArgumentException If the operation fails due to an issue specified by the
   *                                  MockImgRepo or if the source image names are invalid.
   */
  @Override
  public void combineImages(List<String> images, String imageDestName) {
    methodCallLogger.append(
            "combineImage called " + images + " and " + imageDestName + " passed\n");
    if (fail) {
      throw new IllegalArgumentException("Image Repository failed");
    }
  }

  /**
   * Brightens the source image by applying a specified brightness constant and associates the
   * result with the destination image name.
   *
   * @param imageNameSrc       The name of the source image to be brightened.
   * @param imageNameDest      The name to associate with the brightened image.
   * @param brightnessConstant The constant to adjust image brightness.
   * @throws IllegalArgumentException If the operation fails due to an issue specified by the
   *                                  MockImgRepo or if the source image name is invalid.
   */
  @Override
  public void brightenImage(String imageNameSrc, String imageNameDest, float brightnessConstant) {
    methodCallLogger.append(
            "brightenImage called " + imageNameSrc + " and " + imageNameDest + " passed\n");
    if (fail) {
      throw new IllegalArgumentException("Source Name invalid");
    }
  }

  /**
   * Blurs the source image and associates the result with the destination image name.
   *
   * @param imageNameSrc  The name of the source image to be blurred.
   * @param imageNameDest The name to associate with the blurred image.
   * @throws IllegalArgumentException If the operation fails due to an issue specified by the
   *                                  MockImgRepo or if the source image name is invalid.
   */
  @Override
  public void blurImage(String imageNameSrc, String imageNameDest) {
    methodCallLogger.append(
            "blurImage called " + imageNameSrc + " and " + imageNameDest + " passed\n");
    if (fail) {
      throw new IllegalArgumentException("Source Name invalid");
    }
  }

  /**
   * Sharpens the source image and associates the result with the destination image name.
   *
   * @param imageNameSrc  The name of the source image to be sharpened.
   * @param imageNameDest The name to associate with the sharpened image.
   * @throws IllegalArgumentException If the operation fails due to an issue specified by the
   *                                  MockImgRepo or if the source image name is invalid.
   */
  @Override
  public void sharpenImage(String imageNameSrc, String imageNameDest) {
    methodCallLogger.append(
            "sharpenImage called " + imageNameSrc + " and " + imageNameDest + " passed\n");
    if (fail) {
      throw new IllegalArgumentException("Source Name invalid");
    }
  }

  /**
   * Flips the source image horizontally and associates the result with the destination image name.
   *
   * @param imageNameSrc  The name of the source image to be flipped horizontally.
   * @param imageNameDest The name to associate with the flipped image.
   * @throws IllegalArgumentException If the operation fails due to an issue specified by the
   *                                  MockImgRepo or if the source image name is invalid.
   */
  @Override
  public void flipImageHorizontally(String imageNameSrc, String imageNameDest) {
    methodCallLogger.append(
            "horizontal flip called " + imageNameSrc + " and " + imageNameDest + " passed\n");
    if (fail) {
      throw new IllegalArgumentException("Source Name invalid");
    }
  }

  /**
   * Flips the source image vertically and associates the result with the destination image name.
   *
   * @param imageNameSrc  The name of the source image to be flipped vertically.
   * @param imageNameDest The name to associate with the flipped image.
   * @throws IllegalArgumentException If the operation fails due to an issue specified by the
   *                                  MockImgRepo or if the source image name is invalid.
   */
  @Override
  public void flipImageVertically(String imageNameSrc, String imageNameDest) {
    methodCallLogger.append(
            "vertical flip called " + imageNameSrc + " and " + imageNameDest + " passed\n");
    if (fail) {
      throw new IllegalArgumentException("Source Name invalid");
    }
  }

  /**
   * Converts the source image to intensity grayscale and associates the result with the destination
   * image name.
   *
   * @param imageNameSrc  The name of the source image to be converted to intensity grayscale.
   * @param imageNameDest The name to associate with the intensity grayscale image.
   * @throws IllegalArgumentException If the operation fails due to an issue specified by the
   *                                  MockImgRepo or if the source image name is invalid.
   */
  @Override
  public void toIntensityGreyScale(String imageNameSrc, String imageNameDest) {
    methodCallLogger.append(
            "intensity gs called " + imageNameSrc + " and " + imageNameDest + " passed\n");
    if (fail) {
      throw new IllegalArgumentException("Source Name invalid");
    }
  }

  /**
   * Converts the source image to luma grayscale and associates the result with the destination
   * image name.
   *
   * @param imageNameSrc  The name of the source image to be converted to luma grayscale.
   * @param imageNameDest The name to associate with the luma grayscale image.
   * @throws IllegalArgumentException If the operation fails due to an issue specified by the
   *                                  MockImgRepo or if the source image name is invalid.
   */
  @Override
  public void toLumaGreyScale(String imageNameSrc, String imageNameDest) {
    methodCallLogger.append(
            "luma gs called " + imageNameSrc + " and " + imageNameDest + " passed\n");
    if (fail) {
      throw new IllegalArgumentException("Source Name invalid");
    }
  }

  /**
   * Converts the source image to value grayscale and associates the result with the destination
   * image name.
   *
   * @param imageNameSrc  The name of the source image to be converted to value grayscale.
   * @param imageNameDest The name to associate with the value grayscale image.
   * @throws IllegalArgumentException If the operation fails due to an issue specified by the
   *                                  MockImgRepo or if the source image name is invalid.
   */
  @Override
  public void toValueGreyScale(String imageNameSrc, String imageNameDest) {
    methodCallLogger.append(
            "value gs called " + imageNameSrc + " and " + imageNameDest + " passed\n");
    if (fail) {
      throw new IllegalArgumentException("Source Name invalid");
    }
  }

  /**
   * Applies a sepia filter to the source image and associates the result with the destination image
   * name.
   *
   * @param imageNameSrc  The name of the source image to be converted to sepia.
   * @param imageNameDest The name to associate with the sepia image.
   * @throws IllegalArgumentException If the operation fails due to an issue specified by the
   *                                  MockImgRepo or if the source image name is invalid.
   */
  @Override
  public void toSepiaImage(String imageNameSrc, String imageNameDest) {
    methodCallLogger.append(
            "sepia called " + imageNameSrc + " and " + imageNameDest + " passed\n");
    if (fail) {
      throw new IllegalArgumentException("Source Name invalid");
    }
  }

  /**
   * Extracts the red channel of the source image and associates the result with the destination
   * image name.
   *
   * @param imageNameSrc  The name of the source image from which to extract the red channel.
   * @param imageNameDest The name to associate with the red channel image.
   * @throws IllegalArgumentException If the operation fails due to an issue specified by the
   *                                  MockImgRepo or if the source image name is invalid.
   */
  @Override
  public void toRedChannelImage(String imageNameSrc, String imageNameDest) {
    methodCallLogger.append(
            "red channel called " + imageNameSrc + " and " + imageNameDest + " passed\n");
    if (fail) {
      throw new IllegalArgumentException("Source Name invalid");
    }
  }

  /**
   * Extracts the green channel of the source image and associates the result with the destination
   * image name.
   *
   * @param imageNameSrc  The name of the source image from which to extract the green channel.
   * @param imageNameDest The name to associate with the green channel image.
   * @throws IllegalArgumentException If the operation fails due to an issue specified by the
   *                                  MockImgRepo or if the source image name is invalid.
   */
  @Override
  public void toGreenChannelImage(String imageNameSrc, String imageNameDest)
          throws IllegalArgumentException {
    methodCallLogger.append(
            "green channel called " + imageNameSrc + " and " + imageNameDest + " passed\n");
    if (fail) {
      throw new IllegalArgumentException("Source Name invalid");
    }
  }

  /**
   * Extracts the blue channel of the source image and associates the result with the destination
   * image name.
   *
   * @param imageNameSrc  The name of the source image from which to extract the blue channel.
   * @param imageNameDest The name to associate with the blue channel image.
   * @throws IllegalArgumentException If the operation fails due to an issue specified by the
   *                                  MockImgRepo or if the source image name is invalid.
   */
  @Override
  public void toBlueChannelImage(String imageNameSrc, String imageNameDest)
          throws IllegalArgumentException {
    methodCallLogger.append(
            "blue channel called " + imageNameSrc + " and " + imageNameDest + " passed\n");
    if (fail) {
      throw new IllegalArgumentException("Source Name invalid");
    }
  }

  /**
   * Checks whether an image with the given name is present in the repository.
   *
   * @param imageName The name of the image to check for in the repository.
   * @return true if the image is present; false otherwise.
   */
  @Override
  public boolean isImagePresent(String imageName) {
    methodCallLogger.append("isImagePresent called " + imageName + " passed\n");
    return fail;
  }

  /**
   * Get a log of method calls made to this MockImgRepo instance.
   *
   * @return A string containing the log of method calls.
   */
  public String getLogger() {
    return methodCallLogger.toString();
  }

  /**
   * Clear the log of method calls made to this MockImgRepo instance.
   */
  public void clearLogger() {
    methodCallLogger = new StringBuilder();
  }

  /**
   * Set the failure flag for the MockImgRepo, allowing or disallowing simulated failures.
   *
   * @param failFlag A boolean flag indicating whether the MockImgRepo should simulate failures.
   */
  public void setFailureFlag(Boolean failFlag) {
    this.fail = failFlag;
  }
}
