package ime;

public class ArrayColorChannel implements ColorChannel {

  private final int width;
  private final int height;
  float[][] pixels;

  public ArrayColorChannel(float[][] newPixels) {
    validatePixels(newPixels);
    //save copy instead of directly saving
    this.pixels = newPixels;
    this.height = newPixels.length;
    this.width = newPixels[0].length;
  }

  public ArrayColorChannel(int width, int height) {
    this.width = width;
    this.height = height;
    this.pixels = new float[height][width];
  }

  void validateIndices(int i, int j) {
    if (i < 0 || i > height || j < 0 || j > width) {
      throw new
              IllegalArgumentException("Index values should be within the image width and height");
    }
  }

  void validatePixels(float[][] pixels) {
    int height = pixels.length;
    int width = pixels[0].length;
    for (int i = 0; i < height; i++) {
      if (pixels[i].length != width) {
        throw new IllegalArgumentException("All rows must be of same width");
      }
      for (int j = 0; j < width; j++) {
        if (pixels[i][j] < 0 || pixels[i][j] > 255) {
          throw new
                  IllegalArgumentException("Pixel value can't be greater than 255 or lesser than 0");
        }
      }
    }
  }

  @Override
  public void set(int i, int j, float value) throws IllegalArgumentException {
    validateIndices(i, j);
    if (value < 0 || value > 255) {
      throw new
              IllegalArgumentException("Can not set pixel value greater than 255 or lesser than 0");
    }
    pixels[i][j] = value;
  }

  @Override
  public float get(int i, int j) throws IllegalArgumentException {
    validateIndices(i, j);
    return pixels[i][j];
  }

//  @Override
//  public ColorChannel add(ColorChannel other) throws IllegalArgumentException {
//    for(int i = 0)
//    return null;
//  }

  @Override
  public ColorChannel brighten(float constant) {
    float[][] result = new float[this.height][this.width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        result[i][j] = Math.max(0, (Math.min(255, pixels[i][j] + constant)));
      }
    }
    return new ArrayColorChannel(result);
  }

  @Override
  public ColorChannel darken(float constant) {
    return this.brighten(-1*constant);
  }

  @Override
  public ColorChannel reverseHorizontally() {
    float[][] result = new float[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        result[i][j] = pixels[i][width - j - 1];
      }
    }
    return new ArrayColorChannel(result);
  }

  @Override
  public ColorChannel reverseVertically() {
    float[][] result = new float[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        result[i][j] = pixels[height - i - 1][j];
      }
    }
    return new ArrayColorChannel(result);
  }

  @Override
  public float convolve(ColorChannel other) {
    return 0;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }
}
