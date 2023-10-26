package ime;

import java.io.FileNotFoundException;
import java.io.IOException;
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
        pixels[i][j] = new PixelImpl(pixelValues[i][j]);
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
        pixels[i][j] = new PixelImpl(pixelValues[i][j]);
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
    return null;
  }

  @Override
  public Image brighten(float brightnessConstant) {
    Pixel[][] resultPixels = new PixelImpl[height][width];
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
    return null;
  }

  @Override
  public Image sharpen() {
    return null;
  }

  @Override
  public Image flipHorizontally() {
    return null;
  }

  @Override
  public Image flipVertically() {
    return null;
  }

  @Override
  public float getPixelValue(int colorChannel, int row, int col) {
    //validation
    return pixels[row][col].getChannelValue(colorChannel);
  }

  @Override
  public float getPixelIntensity(int row, int col) {
    return 0;
  }

  @Override
  public float getPixelLuma(int row, int col) {
    return 0;
  }

  @Override
  public Image greyScale() {
    return null;
  }

  @Override
  public Image sepia() {
    return null;
  }

  @Override
  public int getColorChannelCount() {
    //validation
    return pixels[0][0].getColorChannelCount();
  }
}
