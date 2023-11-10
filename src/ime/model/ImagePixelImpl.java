package ime.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ime.model.ImageConstants.BLUR_FILTER;
import static ime.model.ImageConstants.SEPIA_TRANSFORMER;
import static ime.model.ImageConstants.SHARPEN_FILTER;

/**
 * This implementation of {@link ImagePixelImpl} stores width x height number of pixels and has an
 * associated image type to it which can be any one of the types listed in {@link ImageType}.
 */
public class ImagePixelImpl implements Image {

  private final int width;
  private final int height;

  private final ImageType imageType;

  private Pixel[][] pixels;

  /**
   * This constructor initializes the {@link ImagePixelImpl} using a 2D pixel array.
   *
   * @param pixelValues the 2D pixel array that makes up the image
   * @param imageType   the type of this image
   */
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

  /**
   * This constructor initializes the {@link ImagePixelImpl} using a 2D array of float[] that will
   * be used to generate the 2D array of pixels that make up this image.
   *
   * @param pixelValues the 2D float[] array that makes up the image
   * @param imageType   the type of this image
   */
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
    int index = getColorChannelIndex(Color.RED);
    return toChannel(index);
  }

  @Override
  public Image getGreenComponent() {
    int index = getColorChannelIndex(Color.GREEN);
    return toChannel(index);
  }

  @Override
  public Image getBlueComponent() {
    int index = getColorChannelIndex(Color.BLUE);
    return toChannel(index);
  }

  @Override
  public Image compress(int compressPercent) {

    float[][][] padded = getPaddedPixels();
    float[][][] transformed = haar(padded);
    float[][][] y = compressByPercent(compressPercent, transformed);
    float[][][] z = invHaar(y);
    return new ImagePixelImpl(removePad(z), imageType);
  }


  @Override
  public List<Image> splitVertically(int splitPercent) {
    int splitPosition = splitPercent * width / 100;
    if (splitPosition <= 0 || splitPosition >= width) {
      return Arrays.asList(new ImagePixelImpl(pixels, imageType));
    }
    Pixel[][] leftImagePixels = new Pixel[height][splitPosition];
    Pixel[][] rightImagePixels = new Pixel[height][width - splitPosition];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (j >= splitPosition) {
          setPixelValue(rightImagePixels, i, j - splitPosition, pixels[i][j].getChannelValues());
        } else {
          setPixelValue(leftImagePixels, i, j, pixels[i][j].getChannelValues());
        }
      }
    }
    Image leftImage = new ImagePixelImpl(leftImagePixels, imageType);
    Image rightImage = new ImagePixelImpl(rightImagePixels, imageType);

    return Arrays.asList(leftImage, rightImage);
  }

  @Override
  public Image append(Image image) {
    if (image.getHeight() != height) {
      throw new IllegalArgumentException("The given image cannot be appended to this image");
    }
    Pixel[][] newImagePixels = new Pixel[height][width + image.getWidth()];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width + image.getWidth(); j++) {
        if (j < width) {
          setPixelValue(newImagePixels, i, j, pixels[i][j].getChannelValues());
        } else {
          setPixelValue(newImagePixels, i, j, image.getPixelValues(i, j - width));
        }
      }
    }
    return new ImagePixelImpl(newImagePixels, imageType);
  }

  @Override
  public ImageType getImageType() {
    return imageType;
  }

  private float[][][] removePad(float[][][] ar) {
    float[][][] newar = new float[height][width][getChannelCount()];
    for (int c = 0; c < getChannelCount(); c++) {
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          newar[i][j][c] = Math.max(0, Math.min(255, ar[i][j][c]));
        }
      }
    }
    return newar;
  }

  private float[][][] compressByPercent(float compressPercent, float[][][] transformed) {
    // think abt cases
    if (compressPercent == 0) {
      return transformed;
    } else {
      compressPercent = compressPercent / 100;
      for (int i = 0; i < getChannelCount(); i++) {
        List<Float> nonZeroChannel = getNonZeroArray(transformed, i);
        nonZeroChannel.sort(Float::compare);
        int num = (int) (compressPercent * nonZeroChannel.size());
        float threshold = nonZeroChannel.get(num - 1);
        for (int m = 0; m < transformed.length; m++) {
          for (int n = 0; n < transformed[0].length; n++) {
            if (Math.abs(transformed[m][n][i]) <= threshold) {
              transformed[m][n][i] = 0;
            }
          }
        }
      }
    }
    return transformed;
  }

  private List<Float> getNonZeroArray(float[][][] ar, int i) {
    ArrayList<Float> arr = new ArrayList<>();
    for (int m = 0; m < ar.length; m++) {
      for (int n = 0; n < ar.length; n++) {
        if (ar[m][n][i] != 0) {
          arr.add(Math.abs(ar[m][n][i]));
        }
      }
    }
    return arr;
  }


  public float[][][] haar(float[][][] pixelsToBeTransformed) {
    for (int a = 0; a < getChannelCount(); a++) {
      int c = pixelsToBeTransformed.length; // Find the maximum dimension
      while (c > 1) {
        for (int i = 0; i < c; i++) {
          double[] rowValues = extractRow(pixelsToBeTransformed[i], c, a);
          double[] transformed = transform(rowValues);
          for (int j = 0; j < c; j++) {
            pixelsToBeTransformed[i][j][a] = (float) transformed[j];
          }
        }
        for (int j = 0; j < c; j++) {
          double[] colValues = extractCol(pixelsToBeTransformed, j, c, a);
          double[] transformed = transform(colValues);
          for (int i = 0; i < c; i++) {
            pixelsToBeTransformed[i][j][a] = (float) transformed[i];
          }
        }
        c = c / 2;
      }
    }
    return pixelsToBeTransformed;
  }

  private double[] extractCol(float[][][] pixelsToBeTransformed, int j, int c, int a) {
    double[] res = new double[c];
    for (int i = 0; i < c; i++) {
      res[i] = pixelsToBeTransformed[i][j][a];
    }
    return res;
  }

  private double[] extractRow(float[][] pixels, int c, int a) {
    double[] res = new double[c];
    for (int i = 0; i < c; i++) {
      res[i] = pixels[i][a];
    }
    return res;
  }

  public float[][][] invHaar(float[][][] pixelsTransformed) {

    for (int a = 0; a < getChannelCount(); a++) {
      int c = 2;
      while (c <= pixelsTransformed.length) {
        for (int j = 0; j < c; j++) {
          double[] colValues = extractCol(pixelsTransformed, j, c, a);
          double[] transformed = invTransform(colValues);
          for (int i = 0; i < c; i++) {
            pixelsTransformed[i][j][a] = (float) transformed[i];
          }
        }
        for (int i = 0; i < c; i++) {
          double[] rowValues = extractRow(pixelsTransformed[i], c, a);
          double[] transformed = invTransform(rowValues);
          for (int j = 0; j < c; j++) {
            pixelsTransformed[i][j][a] = (float) transformed[j];
          }
        }
        c = c * 2;
      }
    }
    return pixelsTransformed;
  }


  private float[][][] getPaddedPixels() {
    int n = Math.max(height, width);
    int powerOf2 = 1;
    while (powerOf2 < n) {
      powerOf2 *= 2;
    }

    float[][][] result = new float[powerOf2][powerOf2][getChannelCount()];
    for (int i = 0; i < powerOf2; i++) {
      for (int j = 0; j < powerOf2; j++) {
        if ((i >= height) || (j >= width)) {
          result[i][j] = new float[getChannelCount()];
        } else {
          result[i][j] = pixels[i][j].getChannelValues();
        }
      }
    }
    return result;
  }

  private double[] transform(double[] arr) {
    int n = arr.length;
    double[] result = new double[n];

    for (int i = 0; i < n / 2; i = i + 1) {
      int sumIndex = i * 2;
      //int diffIndex = i * 2 + 1;

      // Compute the sum and difference coefficients
      result[i] = (arr[sumIndex] + arr[sumIndex + 1]) / Math.sqrt(2);
      result[n / 2 + i] = (arr[sumIndex] - arr[1 + sumIndex]) / Math.sqrt(2);
    }

    return result;
  }


  private double[] invTransform(double[] arr) {
    int n = arr.length / 2;
    double[] avg = new double[n];
    double[] diff = new double[n];

    int j = n;
    for (int i = 0; i < n; i++, j++) {
      double a = arr[i];
      double b = arr[j];
      double av = (a + b) / Math.sqrt(2);
      double de = (a - b) / Math.sqrt(2);

      avg[i] = av;
      diff[i] = de;
    }

    double[] result = new double[arr.length];
    for (int i = 0; i < n; i++) {
      result[i * 2] = avg[i];
      result[i * 2 + 1] = diff[i];
    }

    return result;
  }

  private int getColorChannelIndex(Color colorChannel) {
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

  /**
   * For a given coordinate (i,j), find the result of convolution between this image and the given
   * filter centred at (i,j).
   */
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

  /**
   * For a given channel index of pixels, return a new image that contains this image's values for
   * that channel and 0 as values for every other channel.
   */
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

  /*
  This method is used to generate a pixel for the given image type and assign the values given to
   the coordinates of the resulting Image.
   */
  private void setPixelValue(Pixel[][] resultPixels, int i, int j, float[] channelValues) {
    resultPixels[i][j] = imageType.generatePixel();
    resultPixels[i][j].setColor(channelValues);
  }


}
