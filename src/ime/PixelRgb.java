package ime;

public class PixelRgb implements Pixel {

  private static final int COLOR_CHANNEL_COUNT = 3;
  private float[] values;

  public PixelRgb(float[] pixelValues) {
    if (pixelValues.length != COLOR_CHANNEL_COUNT) {
      throw new IllegalArgumentException("Number of values provided to the pixel is incorrect");
    }
    values = pixelValues;
  }

  public PixelRgb(Pixel pixels) {
    values = new float[COLOR_CHANNEL_COUNT];
    float[] pixelValues = pixels.getChannelValues();
    for (int i = 0; i < COLOR_CHANNEL_COUNT; i++) {
      values[i] = pixelValues[i];
    }
  }

  public PixelRgb() {
    values = new float[COLOR_CHANNEL_COUNT];
  }

  public int getColorChannelCount() {
    return COLOR_CHANNEL_COUNT;
  }

  @Override
  public float getValue() {
    float max = 0;
    for (int i = 0; i < values.length; i++) {
      if (values[i] == 255) {
        return 255;
      } else {
        if (values[i] > max) {
          max = values[i];
        }
      }
    }
    return max;
  }

  @Override
  public float getIntensity() {
    float sum = 0;
    for (int i = 0; i < getColorChannelCount(); i++) {
      sum += values[i];
    }
    return sum / getColorChannelCount();
  }

  @Override
  public float getLuma() {
    //rgb specific
    return (float) (0.2126 * values[0] + 0.7152 * values[1] + 0.0722 * values[2]);

  }

  @Override
  public Pixel transformPixel(float[][] transformCoefficients) throws IllegalArgumentException {
    if (transformCoefficients.length != COLOR_CHANNEL_COUNT || transformCoefficients[0].length != COLOR_CHANNEL_COUNT) {
      throw new IllegalArgumentException("Invalid transformation matrix provided");
    }
    float[] result = new float[COLOR_CHANNEL_COUNT];
    for (int i = 0; i < COLOR_CHANNEL_COUNT; i++) {
        float sum = 0;
        for (int j = 0; j < COLOR_CHANNEL_COUNT; j++) {
          sum+= transformCoefficients[i][j] * values[j];
        }
        result[i] = Math.max(0,Math.min(255,sum));
      }
    return new PixelRgb(result);
    }

  @Override
  public float getChannelValue(int channel) {
    if (channel < 0 || channel > COLOR_CHANNEL_COUNT) {
      throw new IllegalArgumentException("Invalid color channel");
    }
    return values[channel];
  }

  @Override
  public float[] getChannelValues() {
    //validation
    //LOOK IF REFERENCE ISSUES ARISE
    return values;
  }

  @Override
  public void setColor(float[] colorChannelValues) throws IllegalArgumentException {
    if (COLOR_CHANNEL_COUNT != colorChannelValues.length) {
      throw new IllegalArgumentException("Incorrect number of values passed to set pixel values");
    }
    //validation pending
    for (int i = 0; i < COLOR_CHANNEL_COUNT; i++) {
      values[i] = colorChannelValues[i];
    }
  }

  @Override
  public void setColorChannel(int i, float value) {
    //validation pending
    values[i] = value;
  }

  public Pixel brighten(float brightnessConstant) {
    float[] result = new float[values.length];
    for (int i = 0; i < values.length; i++) {
      result[i] = Math.max(0, Math.min(255, values[i] + brightnessConstant));
    }
    return new PixelRgb(result);
  }

}
