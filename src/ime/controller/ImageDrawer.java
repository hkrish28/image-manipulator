package ime.controller;

public interface ImageDrawer{

  float[][][] getImageDrawing();

  void drawLine(int x1, int y1, int x2, int y2);

  void setColor(int[] colorPalette);


  void setImage(float[][][] imagePixels);

  void setUpCanvas(int width, int height);
}
