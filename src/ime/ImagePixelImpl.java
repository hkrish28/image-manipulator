package ime;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class ImagePixelImpl implements Image {

  private final FileHandlerProvider fileHandlerProvider;
  private final int width;
  private final int height;

  Pixel[][] pixels;

//  public ImagePixelImpl(Pixel[][] colorChannels) {
//    this.colorChannels = colorChannels;
//    width = colorChannels.get(0).getWidth();
//    height = colorChannels.get(0).getHeight();
//    fileHandlerProvider = new FileHandlerProviderImpl();
//  }

  public ImagePixelImpl(String fileName) throws FileNotFoundException {
    fileHandlerProvider = new FileHandlerProviderImpl();
    List<ColorChannel> colorChannels =
            fileHandlerProvider.getFileHandler(fileName).loadFile(fileName);
//    this.colorChannels = colorChannels;
    width = colorChannels.get(0).getWidth();
    height = colorChannels.get(0).getHeight();
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

  }

  @Override
  public List<Image> splitIntoColorChannels() {
    return null;
  }

  @Override
  public Image brighten(float brightnessConstant) {
    return null;
  }

  @Override
  public Image darken(float darknessConstant) {
    return null;
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
    return 0;
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
    return 0;
  }
}
