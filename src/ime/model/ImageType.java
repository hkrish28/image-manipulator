package ime.model;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * This enum list the different types of images and provides some functions pertaining to each of
 * the specific image types.
 */
public enum ImageType {
  RGB(RgbPixel::new, Collections.unmodifiableList(Arrays.asList(ColorChannelEnum.RED, ColorChannelEnum.GREEN, ColorChannelEnum.BLUE)));

  /**
   * The color channels present in the image type.
   */
  public final List<ColorChannelEnum> colorChannels;


  private Supplier<Pixel> pixelSupplier;

  ImageType(Supplier<Pixel> pixelSupplier, List<ColorChannelEnum> colorChannels) {
    this.pixelSupplier = pixelSupplier;
    this.colorChannels = colorChannels;
  }

  public static ImageType getImageTypeFromChannels(List<Color> colors) {
    List<ImageType> colorChannelImageType = Arrays.stream(ImageType.values()).filter(imageType -> {
      if (imageType.colorChannels.size() != colors.size()) {
        return false;
      }
      return Arrays.deepEquals(colors.toArray(), imageType.colorChannels.toArray());
    }).collect(Collectors.toList());
    if (colorChannelImageType.isEmpty()) {
      return null;
    } else {
      return colorChannelImageType.get(0);
    }
  }

  /**
   * Generate a new pixel for an image of this image type.
   *
   * @return a new pixel for the image type
   */
  public Pixel generatePixel() {
    return pixelSupplier.get();
  }
}
