package ime.Model;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static ime.Model.ImageConstants.RGB_BLUR;
import static ime.Model.ImageConstants.RGB_SEPIA;
import static ime.Model.ImageConstants.RGB_SHARPEN;

public enum ImageEnum {
  RGB(RGB_SEPIA,
          RGB_BLUR,
          RGB_SHARPEN,
          RgbPixel::new,
          Arrays.asList(ColorChannel.RED, ColorChannel.GREEN, ColorChannel.BLUE));

  public final float[][] sepiaTransformer;
  public final float[][] blurFilter;
  public final float[][] sharpFilter;

  public final List<ColorChannel> colorChannels;


  private Supplier<Pixel> pixelSupplier;

  ImageEnum(float[][] sepiaTransformer, float[][] blurFilter, float[][] sharpFilter,
            Supplier<Pixel> pixelSupplier, List<ColorChannel> colorChannels) {
    this.sepiaTransformer = sepiaTransformer;
    this.blurFilter = blurFilter;
    this.sharpFilter = sharpFilter;
    this.pixelSupplier = pixelSupplier;
    this.colorChannels = colorChannels;
  }

  public Pixel generatePixel() {
    return pixelSupplier.get();
  }
}
