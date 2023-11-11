package ime.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * This implementation of {@link ImageRepository} stores multiple images as a map between the tagged
 * name of the image to its actual {@link Image} object. It makes use of {@link FileHandlerProvider}
 * to perform functions like load and save of images.
 */
public class ImageRepositoryImpl implements ImageRepository {

//  private final FileHandlerProvider fileHandlerProvider;
  /**
   * map for storing the image with its name as the key.
   */
  private final Map<String, Image> imageMap;

  private final ImageAdapter imageAdapter;

  public ImageRepositoryImpl() {
    imageMap = new HashMap<>();
//    this.fileHandlerProvider = fileHandlerProvider;
    this.imageAdapter = new ImageAdapter();
  }


//  @Override
//  public void loadImage(String filePath, String imageName) {
//    try {
//      float[][][] imagePixels = fileHandlerProvider.getFileHandler(filePath).loadImage(filePath);
//      Image newImage = new ImagePixelImpl(imagePixels, ImageType.RGB);
//      imageMap.put(imageName, newImage);
//    } catch (IOException e) {
//      throw new IllegalArgumentException(e.getMessage());
//    }
//
//  }

  @Override
  public void loadImage(BufferedImage image, String imageName) {
    Image newImage = new ImagePixelImpl(new BufferedImageHandler().loadImage(image), ImageType.RGB);
    imageMap.put(imageName, newImage);
  }

//  @Override
//  public void saveImage(String fileName, String imageName)
//          throws IllegalArgumentException {
//    validateImagePresent(imageName);
//    Image image = imageMap.get(imageName);
//    try {
//      fileHandlerProvider.getFileHandler(fileName).saveImage(image, fileName);
//    } catch (IOException e) {
//      throw new IllegalArgumentException(e.getMessage());
//    }
//
//  }

  @Override
  public BufferedImage getImage(String imageName) {
    validateImagePresent(imageName);
    Image image = imageMap.get(imageName);
    float[][][] pixels = new ImageHandlerImpl().loadImage(image);
    return new BufferedImageHandler().saveImage(pixels, image.getImageType().colorChannels);
  }

  @Override
  public void splitImageIntoColorChannels(String srcImage, List<String> destImageNames)
          throws IllegalArgumentException {
    validateImagePresent(srcImage);
    List<Image> destImages = imageMap.get(srcImage).splitIntoColorChannels();
    for (int i = 0; i < destImages.size(); i++) {
      imageMap.put(destImageNames.get(i), destImages.get(i));
    }
  }

  @Override
  public void combineImages(List<String> srcImageNames, String imageDestName)
          throws IllegalArgumentException {
    validateImagesPresent(srcImageNames);
    List<Image> srcImageList = new ArrayList<>();
    Image firstSrcImage = imageMap.get(srcImageNames.get(0));
    for (int i = 1; i < srcImageNames.size(); i++) {
      srcImageList.add(imageMap.get(srcImageNames.get(i)));
    }
    Image newImage = firstSrcImage.combine(srcImageList);
    imageMap.put(imageDestName, newImage);
  }

  @Override
  public void brightenImage(String imageNameSrc, String imageNameDest, float brightnessConstant)
          throws IllegalArgumentException {
    validateImagePresent(imageNameSrc);
    Image newImage = imageMap.get(imageNameSrc).brighten(brightnessConstant);
    imageMap.put(imageNameDest, newImage);
  }

  @Override
  public void blurImage(String imageNameSrc, String imageNameDest)
          throws IllegalArgumentException {
    validateImagePresent(imageNameSrc);
    Image newImage = imageMap.get(imageNameSrc).blur();
    imageMap.put(imageNameDest, newImage);
  }

  @Override
  public void sharpenImage(String imageNameSrc, String imageNameDest)
          throws IllegalArgumentException {
    validateImagePresent(imageNameSrc);
    Image newImage = imageMap.get(imageNameSrc).sharpen();
    imageMap.put(imageNameDest, newImage);
  }

  @Override
  public void flipImageHorizontally(String imageNameSrc, String imageNameDest)
          throws IllegalArgumentException {
    validateImagePresent(imageNameSrc);
    Image newImage = imageMap.get(imageNameSrc).flipHorizontally();
    imageMap.put(imageNameDest, newImage);
  }

  @Override
  public void flipImageVertically(String imageNameSrc, String imageNameDest)
          throws IllegalArgumentException {
    validateImagePresent(imageNameSrc);
    Image newImage = imageMap.get(imageNameSrc).flipVertically();
    imageMap.put(imageNameDest, newImage);
  }

  @Override
  public void toIntensityGreyScale(String imageNameSrc, String imageNameDest)
          throws IllegalArgumentException {
    validateImagePresent(imageNameSrc);
    Image newImage = imageMap.get(imageNameSrc).getIntensityImage();
    imageMap.put(imageNameDest, newImage);
  }

  @Override
  public void toLumaGreyScale(String imageNameSrc, String imageNameDest)
          throws IllegalArgumentException {
    validateImagePresent(imageNameSrc);
    Image newImage = imageMap.get(imageNameSrc).getLumaImage();
    imageMap.put(imageNameDest, newImage);
  }

  @Override
  public void toValueGreyScale(String imageNameSrc, String imageNameDest)
          throws IllegalArgumentException {
    validateImagePresent(imageNameSrc);
    Image newImage = imageMap.get(imageNameSrc).getValueImage();
    imageMap.put(imageNameDest, newImage);
  }

  @Override
  public void toSepiaImage(String imageNameSrc, String imageNameDest)
          throws IllegalArgumentException {
    validateImagePresent(imageNameSrc);
    Image newImage = imageMap.get(imageNameSrc).getSepia();
    imageMap.put(imageNameDest, newImage);
  }

  @Override
  public void toRedChannelImage(String imageNameSrc, String imageNameDest)
          throws IllegalArgumentException {
    validateImagePresent(imageNameSrc);
    Image newImage = imageMap.get(imageNameSrc).getRedComponent();
    imageMap.put(imageNameDest, newImage);
  }

  @Override
  public void toGreenChannelImage(String imageNameSrc, String imageNameDest)
          throws IllegalArgumentException {
    validateImagePresent(imageNameSrc);
    Image newImage = imageMap.get(imageNameSrc).getGreenComponent();
    imageMap.put(imageNameDest, newImage);
  }

  @Override
  public void toBlueChannelImage(String imageNameSrc, String imageNameDest)
          throws IllegalArgumentException {
    validateImagePresent(imageNameSrc);
    Image newImage = imageMap.get(imageNameSrc).getBlueComponent();
    imageMap.put(imageNameDest, newImage);
  }

  @Override
  public boolean isImagePresent(String imageName) {
    return imageMap.containsKey(imageName);
  }

  @Override
  public void compress(String imageNameSrc, String imageNameDest, int compressPercent)
          throws IllegalArgumentException {
    validateImagePresent(imageNameSrc);
    Image newImage = imageMap.get(imageNameSrc).compress(compressPercent);
    imageMap.put(imageNameDest, newImage);
  }

  @Override
  public void preview(String imageNameSrc, String imageNameDest,
                      BiConsumer<String, String> operation, int verticalSplit) {
    if (verticalSplit < 0 || verticalSplit > 100) {
      throw new IllegalArgumentException("Invalid split position");
    }
    validateImagePresent(imageNameSrc);
    List<Image> images = imageMap.get(imageNameSrc).splitVertically(verticalSplit);
    imageMap.put("temp", images.get(1));
    operation.accept("temp", "temp");
    imageMap.put(imageNameDest, images.get(0).append(imageMap.get("temp")));
    imageMap.remove("temp");
  }

  @Override
  public void levelsAdjust(String imageNameSrc, String destImage, int b, int m, int w) {
    validateImagePresent(imageNameSrc);
    Image image = imageMap.get(imageNameSrc).levelAdjust(b, m, w);
    imageMap.put(destImage, image);
  }

  private int calculateAveragePeakValue(Image image) {
    int sumPeakValue = 0;
    HistogramImpl hist = new HistogramImpl(image, image.getHeight());
    for (int channelIndex = 0; channelIndex < hist.getChannelCount(); channelIndex++) {
      sumPeakValue += hist.getMostFrequentValue(channelIndex);
    }
    return sumPeakValue / hist.getChannelCount();
  }


  @Override
  public void colorCorrect(String imageNameSrc, String imageNameDest) {
    validateImagePresent(imageNameSrc);
    Image image = imageMap.get(imageNameSrc);
    HistogramImpl hist = new HistogramImpl(image, image.getHeight());

    int averagePeakValue = calculateAveragePeakValue(image);
    List<Image> limages = new ArrayList<>();

    // Calculate brightness adjustment for each channel
    float[] brightnessAdjustment = new float[image.getChannelCount()];
    for (int channelIndex = 0; channelIndex < image.getChannelCount(); channelIndex++) {
      int currentPeakValue = hist.getMostFrequentValue(channelIndex);
      int peakDifference = averagePeakValue - currentPeakValue;
      brightnessAdjustment[channelIndex] = peakDifference;
      limages.add(image.brighten(brightnessAdjustment[channelIndex]));
    }
    Image newimage = limages.get(0);
    limages.remove(0);
    imageMap.put(imageNameDest, newimage.combine(limages));
  }

  @Override
  public void toHistogram(String imageNameSrc, String imageNameDest) {
    validateImagePresent(imageNameSrc);
    Image newImage = new ImagePixelImpl(new HistogramImpl(imageMap.get(imageNameSrc), 256).createHistogram(), ImageType.RGB);
    imageMap.put(imageNameDest, newImage);
  }

  private void validateImagePresent(String imageName) throws IllegalArgumentException {
    if (!this.isImagePresent(imageName)) {
      throw new IllegalArgumentException("image name invalid");
    }
  }

  private void validateImagesPresent(List<String> srcImageNames) throws IllegalArgumentException {
    srcImageNames.forEach(this::validateImagePresent);
  }
}
