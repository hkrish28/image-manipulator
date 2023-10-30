package ime.Model;

/**
 * This class contains the constants required for operations on different types of images.
 */
public class ImageConstants {
  protected static final float[][] SEPIA_TRANSFORMER =
          {{0.393f, 0.769f, 0.189f}, {0.349f, 0.686f, 0.168f}, {0.272f, 0.534f, 0.131f}};
  protected static final float[][] BLUR_FILTER =
          {{1 / 16f, 1 / 8f, 1 / 16f}, {1 / 8f, 1 / 4f, 1 / 8f}, {1 / 16f, 1 / 8f, 1 / 16f}};
  protected static final float[][] SHARPEN_FILTER =
          {{-1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f}, {-1 / 8f, 1 / 4f, 1 / 4f, 1 / 4f, -1 / 8f},
                  {-1 / 8f, 1 / 4f, 1, 1 / 4f, -1 / 8f}, {-1 / 8f, 1 / 4f, 1 / 4f, 1 / 4f, -1 / 8f},
                  {-1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f}
          };
}
