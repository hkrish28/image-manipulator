package ime;

public enum ImageEnum {
  RGB(new float[][]{{0.393f, 0.769f, 0.189f}, {0.349f, 0.686f, 0.168f}, {0.272f, 0.534f, 0.131f}});

  private float[][] sepiaTransformer;

  private ImageEnum(float[][] sepiaTransformer) {
    this.sepiaTransformer = sepiaTransformer;
  }

  public float[][] getSepiaTransformer() {
    return sepiaTransformer;
  }
}
