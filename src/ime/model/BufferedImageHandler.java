package ime.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class BufferedImageHandler implements ImageHandler<BufferedImage> {

  @Override
  public float[][][] loadImage(BufferedImage image) throws IOException {


    return new float[0][][];

  }

  @Override
  public BufferedImage saveImage(float[][][] pixelValues, List<Color> colorChannels) {
    return null;
  }
}
