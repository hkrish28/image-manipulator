package ime;

import java.util.List;

public class PixelImpl implements Pixel {

  private float[] values;

  public PixelImpl(float[] pixelValues) {
    values = pixelValues;
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

  public void brighten(float brightnessConstant) {
    for (int i = 0; i < values.length; i++) {

    }
  }
}
