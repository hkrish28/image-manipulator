package ime.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ime.model.ImageConstants.BLUR_FILTER;
import static ime.model.ImageConstants.SEPIA_TRANSFORMER;
import static ime.model.ImageConstants.SHARPEN_FILTER;

/**
 * This implementation of {@link ImagePixelImpl} stores width x height number of pixels and has
 * an associated image type to it which can be any one of the types listed in {@link ImageType}.
 */
public class ImagePixelImpl implements Image {

  private final int width;
  private final int height;

  private final ImageType imageType;

  Pixel[][] pixels;

  public ImagePixelImpl(Pixel[][] pixelValues, ImageType imageType) {
    if (!(pixelValues.length > 0 && pixelValues[0].length > 0)) {
      throw new IllegalArgumentException("Image should contain at least one pixel");
    }
    this.imageType = imageType;
    height = pixelValues.length;
    width = pixelValues[0].length;
    pixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        setPixelValue(this.pixels, i, j, pixelValues[i][j].getChannelValues());
      }
    }
  }

  public ImagePixelImpl(float[][][] pixelValues, ImageType imageType) {
    if (!(pixelValues.length > 0 && pixelValues[0].length > 0)) {
      throw new IllegalArgumentException("Image should contain at least one pixel");
    }
    this.imageType = imageType;
    width = pixelValues[0].length;
    height = pixelValues.length;
    pixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        setPixelValue(this.pixels, i, j, pixelValues[i][j]);
      }
    }
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public List<Image> splitIntoColorChannels() {
    List<Image> result = new ArrayList<>();
    int channelCount = this.pixels[0][0].getColorChannelCount();

    for (int i = 0; i < channelCount; i++) {
      result.add(toChannel(i));
    }
    return result;
  }

  @Override
  public Image combine(List<Image> images) {
    if (images.size() != this.getChannelCount() - 1) {
      throw new IllegalArgumentException("Invalid number of images");
    }

    Pixel[][] resultPixels = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        float[] pixelValues = new float[this.getChannelCount()];
        pixelValues[0] = this.pixels[i][j].getChannelValues()[0];
        for (int k = 1; k < this.getChannelCount(); k++) {
          pixelValues[k] = images.get(k - 1).getPixelValues(i, j)[k];
        }
        setPixelValue(resultPixels, i, j, pixelValues);
      }
    }

    return new ImagePixelImpl(resultPixels, imageType);
  }

  @Override
  public Image brighten(float brightnessConstant) {
    Pixel[][] resultPixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        resultPixels[i][j] = pixels[i][j].brighten(brightnessConstant);
      }
    }
    return new ImagePixelImpl(resultPixels, imageType);
  }

  @Override
  public Image blur() {
    return applyFilter(BLUR_FILTER);
  }

  @Override
  public Image sharpen() {
    return applyFilter(SHARPEN_FILTER);
  }

  @Override
  public Image flipHorizontally() {
    Pixel[][] resultPixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        setPixelValue(resultPixels, i, j, pixels[i][width - j - 1].getChannelValues());
      }
    }
    return new ImagePixelImpl(resultPixels, imageType);
  }

  @Override
  public Image flipVertically() {
    Pixel[][] resultPixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        setPixelValue(resultPixels, i, j, pixels[height - i - 1][j].getChannelValues());
      }
    }
    return new ImagePixelImpl(resultPixels, imageType);
  }

  @Override
  public float[] getPixelValues(int row, int col) {
    if (row < 0 || row >= height || col < 0 || col >= width) {
      throw new IllegalArgumentException("Pixel location invalid");
    }
    return pixels[row][col].getChannelValues();
  }


  @Override
  public Image getIntensityImage() {
    Pixel[][] resultPixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        float pixelIntensity = pixels[i][j].getIntensity();
        float[] greyscale = new float[pixels[i][j].getColorChannelCount()];
        Arrays.fill(greyscale, pixelIntensity);
        setPixelValue(resultPixels, i, j, greyscale);
      }
    }
    return new ImagePixelImpl(resultPixels, imageType);
  }

  @Override
  public Image getLumaImage() {
    Pixel[][] resultPixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        float luma = pixels[i][j].getLuma();
        float[] greyscale = new float[pixels[i][j].getColorChannelCount()];
        Arrays.fill(greyscale, luma);
        setPixelValue(resultPixels, i, j, greyscale);
      }
    }
    return new ImagePixelImpl(resultPixels, imageType);
  }

  @Override
  public Image getValueImage() {
    Pixel[][] resultPixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        float pixelIntensity = pixels[i][j].getValue();
        float[] greyscale = new float[pixels[i][j].getColorChannelCount()];
        Arrays.fill(greyscale, pixelIntensity);
        setPixelValue(resultPixels, i, j, greyscale);
      }
    }
    return new ImagePixelImpl(resultPixels, imageType);
  }

  @Override
  public Image getSepia() {
    return performColorTransformation(SEPIA_TRANSFORMER);
  }

  @Override
  public int getChannelCount() {
    return pixels[0][0].getColorChannelCount();
  }

  @Override
  public Image getRedComponent() {
    int index = getColorChannelIndex(ColorChannel.RED);
    return toChannel(index);
  }

  @Override
  public Image getGreenComponent() {
    int index = getColorChannelIndex(ColorChannel.GREEN);
    return toChannel(index);
  }

  @Override
  public Image getBlueComponent() {
    int index = getColorChannelIndex(ColorChannel.BLUE);
    return toChannel(index);
  }

  private int getColorChannelIndex(ColorChannel colorChannel) {
    int index = imageType.colorChannels.indexOf(colorChannel);
    if (index < 0) {
      throw new IllegalArgumentException("red component can not be obtained for the given image");
    }
    return index;
  }

  /**
   * Given a matrix of coefficients for the color channels, return an image that is the color
   * transformed version of this image.
   *
   * @param transformCoefficients the matrix containing the coefficients for the color channels
   * @return the Image after color transformation
   */
  private Image performColorTransformation(float[][] transformCoefficients) {
    Pixel[][] resultPixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        resultPixels[i][j] = pixels[i][j].transformPixel(transformCoefficients);
      }
    }
    return new ImagePixelImpl(resultPixels, imageType);
  }

  /**
   * Given a filter, apply it to the image and return the result which is a new image.
   *
   * @param filter the filter to be applied.
   * @return the image result after performing the filter on the original image.
   */
  private Image applyFilter(float[][] filter) {
    Pixel[][] resultPixel = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        float[] filterValues = new float[this.getChannelCount()];
        for (int k = 0; k < this.getChannelCount(); k++) {
          float sum = getConvolutionProduct(i, j, filter, k);

          filterValues[k] = Math.max(0, Math.min(255, sum));
        }
        setPixelValue(resultPixel, i, j, filterValues);
      }
    }
    return new ImagePixelImpl(resultPixel, imageType);
  }

  private float getConvolutionProduct(int i, int j, float[][] filter, int channel) {
    int filterHeight = filter.length;
    int filterWidth = filter[0].length;

    int leftOffset = Math.max(0, j - filterWidth / 2) - (j - filterWidth / 2);
    int rightOffset = (j + filterWidth / 2) - Math.min(width - 1, j + filterWidth / 2);
    int topOffset = Math.max(0, i - filterHeight / 2) - (i - filterHeight / 2);
    int bottomOffset = (i + filterHeight / 2) - Math.min(height - 1, i + filterHeight / 2);

    float sum = 0;

    for (int m = topOffset; m < filterHeight - bottomOffset; m++) {
      for (int n = leftOffset; n < filterWidth - rightOffset; n++) {
        sum += filter[m][n] * pixels[i - (filterHeight / 2) + m][j - (filterWidth / 2) + n]
                .getChannelValues()[channel];
      }
    }

    return sum;
  }

  private Image toChannel(int channel) {

    int channelCount = this.pixels[0][0].getColorChannelCount();
    if (channel >= channelCount || channel < 0) {
      throw new IllegalArgumentException("Invalid channel provided");
    }
    Pixel[][] resultPixels = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        resultPixels[i][j] = imageType.generatePixel();
        resultPixels[i][j].setColorChannel(channel, this.pixels[i][j].getChannelValue(channel));
      }
    }

    return new ImagePixelImpl(resultPixels, imageType);
  }

  private void setPixelValue(Pixel[][] resultPixels, int i, int j, float[] channelValues) {
    resultPixels[i][j] = imageType.generatePixel();
    resultPixels[i][j].setColor(channelValues);
  }
}
