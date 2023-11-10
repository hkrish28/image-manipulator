package ime.model;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

/**
 * An Image Handler is used for converting an Image into a 2D array of its pixel values. A pixel
 * value is represented as an array of each of its color channels.
 */
public interface ImageHandler <T> {


  float[][][] loadImage(T image) throws IOException;


  T saveImage(float[][][] pixelValues, List<Color> colorChannels);

}
