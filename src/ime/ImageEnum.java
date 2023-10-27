package ime;

public enum ImageEnum {
  RGB(new float[][]{{0.393f, 0.769f, 0.189f}, {0.349f, 0.686f, 0.168f}, {0.272f, 0.534f, 0.131f}},
          new float[][]{{1/16f, 1/8f, 1/16f}, {1/8f, 1/4f, 1/8f}, {1/16f, 1/8f, 1/16f}});

  private float[][] sepiaTransformer;
  private float[][] blurFilter;

  private ImageEnum(float[][] sepiaTransformer, float[][] blurFilter) {
    this.sepiaTransformer = sepiaTransformer;
    this.blurFilter = blurFilter;
  }

  public float[][] getSepiaTransformer() {
    return sepiaTransformer;
  }

  public float[][] getBlurFilter() {
    return blurFilter;
  }
}
