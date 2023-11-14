package ime.model;

/**
 * A histogram drawer will
 */
public interface HistogramDrawer {
  /**
   * Visualizes a histogram as a graphical representation. the method computes the normalised value
   * the start and the end of the pairs of pixels and draws the lines according to the values.
   *
   * @param histogram The histogram to visualize.
   * @return A 3D array of floating-point pixel values representing the visualized histogram.
   */
  float[][][] visualizeHistogram(Histogram histogram);
}
