package ime.model;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ImageHandlerImpl implements ImageHandler<Image> {
  @Override
  public float[][][] loadImage(Image image) throws IOException {
    int height = image.getHeight();
    int width = image.getWidth();
    int channelCount = image.getChannelCount();
    float[][][] result = new float[height][width][channelCount];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        float[] pixelValues = image.getPixelValues(i, j);
        for (int k = 0; k < channelCount; k++) {
          result[i][j][k] = pixelValues[k];
        }
      }
    }
    return result;
  }

  @Override
  public Image saveImage(float[][][] pixelValues, List<Color> colorChannels) {
    ImageType imageType = ImageType.getImageTypeFromChannels(colorChannels);
    return new ImagePixelImpl(pixelValues, imageType);
  }
}
