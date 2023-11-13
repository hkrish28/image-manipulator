package ime.model;

import java.awt.*;
import java.util.List;

/**
 * An Image Handler is used for converting an Image into a 2D array of its pixel values and vice
 * versa. A pixel value is represented as an array of each of its color channels.
 */
public interface ImageHandler<T> {


  float[][][] getImagePixels(T image);


  T convertIntoImage(float[][][] pixelValues, List<Color> colorChannels);

}
