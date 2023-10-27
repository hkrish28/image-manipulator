package ime.Model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImagePixelImpl implements Image {

  private final FileHandlerProvider fileHandlerProvider;
  private final int width;
  private final int height;

  Pixel[][] pixels;

  public ImagePixelImpl(Pixel[][] pixelValues) {
    fileHandlerProvider = new FileHandlerProviderImpl();
    height = pixelValues.length;
    width = pixelValues[0].length;
    pixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixels[i][j] = new PixelRgb(pixelValues[i][j]);
      }
    }

  }

  public ImagePixelImpl(String fileName) throws FileNotFoundException {
    fileHandlerProvider = new FileHandlerProviderImpl();
    float[][][] pixelValues =
            fileHandlerProvider.getFileHandler(fileName).loadFileBase(fileName);
    width = pixelValues[0].length;
    height = pixelValues.length;
    pixels = new Pixel[height][width];
    //validation required
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixels[i][j] = new PixelRgb(pixelValues[i][j]);
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
  public void saveImage(String filename) throws IOException {
    fileHandlerProvider.getFileHandler(filename).saveFile(this, filename);
  }

  @Override
  public List<Image> splitIntoColorChannels() {
    List<Image> result = new ArrayList<>();
    int channelCount = this.pixels[0][0].getColorChannelCount();
    List<Pixel[][]> resultPixels = new ArrayList<>(channelCount);

    for (int i = 0; i < channelCount; i++) {
      resultPixels.add(new PixelRgb[height][width]);
    }
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < channelCount; k++) {
          Pixel colorChannelPixel = new PixelRgb();
          colorChannelPixel.setColorChannel(k, this.pixels[i][j].getChannelValue(k));
          resultPixels.get(k)[i][j] = colorChannelPixel;
        }
      }
    }
    for (int i = 0; i < channelCount; i++) {
      result.add(new ImagePixelImpl(resultPixels.get(i)));
    }
    return result;
  }

  @Override
  public Image combine(List<Image> images) {
    if (images.size() != this.getChannelCount() - 1) {
      throw new IllegalArgumentException("Invalid number of images");
    }
    Pixel[][] resultPixels = new PixelRgb[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        float[] pixelValues = new float[this.getChannelCount()];
        pixelValues[0] = this.pixels[i][j].getChannelValues()[0];
        for (int k = 1; k < this.getChannelCount(); k++) {
          pixelValues[k] = images.get(k - 1).getPixelValues(i, j)[k];
        }
        resultPixels[i][j] = new PixelRgb(pixelValues);
      }
    }

    return new ImagePixelImpl(resultPixels);
  }

  @Override
  public Image brighten(float brightnessConstant) {
    Pixel[][] resultPixels = new PixelRgb[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        resultPixels[i][j] = pixels[i][j].brighten(brightnessConstant);
      }
    }
    return new ImagePixelImpl(resultPixels);
  }

  @Override
  public Image darken(float darknessConstant) {
    return this.brighten(-darknessConstant);
  }

  @Override
  public Image blur() {
    return applyFilter(ImageEnum.RGB.getBlurFilter());
  }

  @Override
  public Image sharpen() {
    return applyFilter(ImageEnum.RGB.getSharpFilter());
  }

  @Override
  public Image flipHorizontally() {
    Pixel[][] result = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        result[i][j] = new PixelRgb(pixels[i][width - j - 1]);
      }
    }
    return new ImagePixelImpl(result);
  }

  @Override
  public Image flipVertically() {
    Pixel[][] result = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        result[i][j] = new PixelRgb(pixels[height - i - 1][j]);
      }
    }
    return new ImagePixelImpl(result);
  }

  @Override
  public float[] getPixelValues(int row, int col) {
    //validation
    return pixels[row][col].getChannelValues();
  }


  @Override
  public Image getIntensityImage() {
    Pixel[][] resultPixels = new PixelRgb[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        float pixelIntensity = pixels[i][j].getLuma();
        float[] greyscale = new float[pixels[i][j].getColorChannelCount()];
        Arrays.fill(greyscale, pixelIntensity);
        resultPixels[i][j] = new PixelRgb(greyscale);
      }
    }
    return new ImagePixelImpl(resultPixels);
  }

  @Override
  public Image getLumaImage() {
    Pixel[][] resultPixels = new PixelRgb[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        float pixelIntensity = pixels[i][j].getLuma();
        float[] greyscale = new float[pixels[i][j].getColorChannelCount()];
        Arrays.fill(greyscale, pixelIntensity);
        resultPixels[i][j] = new PixelRgb(greyscale);
      }
    }
    return new ImagePixelImpl(resultPixels);
  }

  @Override
  public Image getValueImage() {
    Pixel[][] resultPixels = new PixelRgb[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        float pixelIntensity = pixels[i][j].getIntensity();
        float[] greyscale = new float[pixels[i][j].getColorChannelCount()];
        Arrays.fill(greyscale, pixelIntensity);
        resultPixels[i][j] = new PixelRgb(greyscale);
      }
    }
    return new ImagePixelImpl(resultPixels);
  }

  @Override
  public Image getSepia() {
    return performColorTransformation(ImageEnum.RGB.getSepiaTransformer());
  }

  @Override
  public int getChannelCount() {
    //validation
    return pixels[0][0].getColorChannelCount();
  }

  /**
   * Given a matrix of coefficients for the color channels, return an image that is the color
   * transformed version of this image.
   *
   * @param transformCoefficients the matrix containing the coefficients for the color channels
   * @return the Image after color transformation
   */
  private Image performColorTransformation(float[][] transformCoefficients) {
    Pixel[][] resultPixels = new PixelRgb[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        resultPixels[i][j] = pixels[i][j].transformPixel(transformCoefficients);
      }
    }
    return new ImagePixelImpl(resultPixels);
  }

  /**
   * Given a filter, apply it to the image and return the result which is a new image.
   *
   * @param filter the filter to be applied.
   * @return the image result after performing the filter on the original image.
   */
  private Image applyFilter(float[][] filter) {
    int filterHeight = filter.length;
    int filterWidth = filter[0].length;
    Pixel[][] resultPixel = new PixelRgb[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int leftOffset = i - filterWidth / 2;
        int rightOffset = i + filterWidth / 2;
        int topOffset = j - filterHeight / 2;
        int bottomOffset = j + filterHeight / 2;
        float[] filterValues = new float[this.getChannelCount()];
        for (int k = 0; k < this.getChannelCount(); k++) {
          float sum = 0;

          for (int m = 0; m < filter.length; m++) {
            for (int n = 0; n < filter[0].length; n++) {
              try {
                sum += filter[m][n] * pixels[i - (filterHeight / 2) + m][j - (filterWidth / 2) + n]
                        .getChannelValues()[k];
              }
              catch(IndexOutOfBoundsException e){
                System.out.println("Expected ");
              }
            }
          }
          filterValues[k] = Math.max(0,Math.min(255,sum));
        }
        resultPixel[i][j] = new PixelRgb(filterValues);
      }
    }
    return new ImagePixelImpl(resultPixel);
  }
}
