package ime.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImagePixelImpl implements Image {

  private final int width;
  private final int height;

  private final ImageEnum imageType;

  Pixel[][] pixels;

  public ImagePixelImpl(Pixel[][] pixelValues, ImageEnum imageType) {
    if (!(pixelValues.length > 0 && pixelValues[0].length > 0)) {
      throw new IllegalArgumentException("Image should contain at least one pixel");
    }
    this.imageType = imageType;
    height = pixelValues.length;
    width = pixelValues[0].length;
    pixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixels[i][j] = this.imageType.generatePixel();
        pixels[i][j].setColor(pixelValues[i][j].getChannelValues());
      }
    }
  }

  public ImagePixelImpl(float[][][] pixelValues, ImageEnum imageType) {
    if (!(pixelValues.length > 0 && pixelValues[0].length > 0)) {
      throw new IllegalArgumentException("Image should contain at least one pixel");
    }
    this.imageType = imageType;
    width = pixelValues[0].length;
    height = pixelValues.length;
    pixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixels[i][j] = this.imageType.generatePixel();
        pixels[i][j].setColor(pixelValues[i][j]);
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
        resultPixels[i][j] = this.imageType.generatePixel();
        resultPixels[i][j].setColor(pixelValues);
      }
    }

    return new ImagePixelImpl(resultPixels,imageType);
  }

  @Override
  public Image brighten(float brightnessConstant) {
    Pixel[][] resultPixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        resultPixels[i][j] = pixels[i][j].brighten(brightnessConstant);
      }
    }
    return new ImagePixelImpl(resultPixels,imageType);
  }

  @Override
  public Image darken(float darknessConstant) {
    return this.brighten(-darknessConstant);
  }

  @Override
  public Image blur() {
    return applyFilter(ImageEnum.RGB.blurFilter);
  }

  @Override
  public Image sharpen() {
    return applyFilter(ImageEnum.RGB.sharpFilter);
  }

  @Override
  public Image flipHorizontally() {
    Pixel[][] result = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        result[i][j] = imageType.generatePixel();
        result[i][j].setColor(pixels[i][width - j - 1].getChannelValues());
      }
    }
    return new ImagePixelImpl(result, imageType);
  }

  @Override
  public Image flipVertically() {
    Pixel[][] result = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        result[i][j] = imageType.generatePixel();
        result[i][j].setColor(pixels[height - i - 1][j].getChannelValues());
      }
    }
    return new ImagePixelImpl(result, imageType);
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
        resultPixels[i][j] = imageType.generatePixel();
        resultPixels[i][j].setColor(greyscale);
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
        resultPixels[i][j] = imageType.generatePixel();
        resultPixels[i][j].setColor(greyscale);
      }
    }
    return new ImagePixelImpl(resultPixels,imageType);
  }

  @Override
  public Image getValueImage() {
    Pixel[][] resultPixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        float pixelIntensity = pixels[i][j].getValue();
        float[] greyscale = new float[pixels[i][j].getColorChannelCount()];
        Arrays.fill(greyscale, pixelIntensity);
        resultPixels[i][j] = imageType.generatePixel();
        resultPixels[i][j].setColor(greyscale);
      }
    }
    return new ImagePixelImpl(resultPixels, imageType);
  }

  @Override
  public Image getSepia() {
    return performColorTransformation(ImageEnum.RGB.sepiaTransformer);
  }

  @Override
  public int getChannelCount() {
    return pixels[0][0].getColorChannelCount();
  }

  @Override
  public Image toRedChannel() {
    return toChannel(0);
  }

  @Override
  public Image toGreenChannel() {
    return toChannel(1);
  }

  @Override
  public Image toBlueChannel() {
    return toChannel(2);
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
        resultPixel[i][j] = imageType.generatePixel();
        resultPixel[i][j].setColor(filterValues);
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
}
