package ime.Model;

import java.util.function.Supplier;

import static ime.Model.ImageConstants.RGB_BLUR;
import static ime.Model.ImageConstants.RGB_SEPIA;
import static ime.Model.ImageConstants.RGB_SHARPEN;

public enum ImageEnum {
  RGB(RGB_SEPIA,
          RGB_BLUR,
          RGB_SHARPEN,
          PixelRgb::new);

  public final float[][] sepiaTransformer;
  public final float[][] blurFilter;
  public final float[][] sharpFilter;

  private Supplier<Pixel> pixelSupplier;

  ImageEnum(float[][] sepiaTransformer, float[][] blurFilter, float[][] sharpFilter,
            Supplier<Pixel> pixelSupplier) {
    this.sepiaTransformer = sepiaTransformer;
    this.blurFilter = blurFilter;
    this.sharpFilter = sharpFilter;
    this.pixelSupplier = pixelSupplier;
  }

  public Pixel generatePixel() {
    return pixelSupplier.get();
  }
}
