package ime;

import java.util.List;

public class PixelImpl implements Pixel {

  private float[] values;

  public PixelImpl(float[] pixelValues) {
    values = pixelValues;
  }

  public PixelImpl(Pixel pixelValues) {
    values = new float[pixelValues.getColorChannelCount()];
    for (int i = 0; i < values.length; i++) {
      values[i] = pixelValues.getChannelValue(i);
    }
  }

  public PixelImpl(int channelCount) {
    values = new float[channelCount];
  }

  public int getColorChannelCount() {
    return values.length;
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
    return 0;
  }

  @Override
  public float getLuma() {
    return 0;
  }

  @Override
  public Pixel transformPixel(ColorChannel transformCoefficients) throws IllegalArgumentException {
    return null;
  }

  @Override
  public float getChannelValue(int channel) {
    //validation
    return values[channel];
  }

  @Override
  public void setColor(List<Float> colorChannelValues) throws IllegalArgumentException {
    if (values.length != colorChannelValues.size()) {
      throw new IllegalArgumentException("Incorrect number of values passed to set pixel values");
    }
    //validation pending
    for (int i = 0; i < values.length; i++) {
      values[i] = colorChannelValues.get(i);
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
    return new PixelImpl(result);
  }

}
