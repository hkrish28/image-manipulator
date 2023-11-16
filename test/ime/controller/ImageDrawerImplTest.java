package ime.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class ImageDrawerImplTest {

  private ImageDrawerImpl imageDrawer;

  @Before
  public void setUp() {
    imageDrawer = new ImageDrawerImpl();
    imageDrawer.setUpCanvas(100, 100);
  }

  @Test
  public void testGetImageDrawing() {
    float[][][] imageDrawing = imageDrawer.getImageDrawing();
    assertNotNull(imageDrawing);
    assertEquals(100, imageDrawing.length);
    assertEquals(100, imageDrawing[0].length);
    assertEquals(3, imageDrawing[0][0].length);
  }

  @Test
  public void testDrawLine() {
    int x1 = 10, y1 = 10, x2 = 90, y2 = 90;

    imageDrawer.drawLine(x1, y1, x2, y2);

    float[][][] imageDrawing = imageDrawer.getImageDrawing();
    assertEquals(255, (int) imageDrawing[x1][y1][0]);
    assertEquals(255, (int) imageDrawing[x2][y2][0]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDrawLineWithInvalidCoordinates() {
    int x1 = -2, y1 = 10, x2 = 110, y2 = 90;

    imageDrawer.drawLine(x1, y1, x2, y2);
  }

  /**
   * x>width
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDrawLineWithOutOfBoundCoordinate() {
    int x1 = 267, y1 = 10, x2 = 110, y2 = 90;
    imageDrawer.drawLine(x1, y1, x2, y2);
  }

  /**
   * y>height
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDrawLineWithOutOfBoundCoordinateY() {
    int x1 = 267, y1 = 400, x2 = 110, y2 = 90;
    imageDrawer.drawLine(x1, y1, x2, y2);
  }

  /**
   * color pallet for green and blue should be 0
   */
  @Test
  public void testSetColor() {
    int[] colorPalette = {255, 0, 0}; // Red

    imageDrawer.setColor(colorPalette);

    float[][][] imageDrawing = imageDrawer.getImageDrawing();
    assertEquals(colorPalette[0], (int) imageDrawing[0][0][0]);
    assertEquals(0, (int) imageDrawing[0][0][1]); // Green
    assertEquals(0, (int) imageDrawing[0][0][2]); // Blue
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetColorWithInvalidPalette() {
    int[] invalidPalette = {255, 0}; // Invalid palette
    imageDrawer.setColor(invalidPalette);
  }

  @Test
  public void testSetUpCanvas() {
    int width = 200, height = 150;

    imageDrawer.setUpCanvas(width, height);

    float[][][] imageDrawing = imageDrawer.getImageDrawing();
    assertNotNull(imageDrawing);
    assertEquals(height, imageDrawing.length);
    assertEquals(width, imageDrawing[0].length);
    assertEquals(3, imageDrawing[0][0].length);
  }

  /**
   * does not through exception when canvas height / width is set > 256
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetUpCanvasInvalidDimensions() {
    int width = 280, height = 150;

    imageDrawer.setUpCanvas(width, height);

    float[][][] imageDrawing = imageDrawer.getImageDrawing();
    assertNotNull(imageDrawing);
    assertEquals(height, imageDrawing.length);
    assertEquals(width, imageDrawing[0].length);
    assertEquals(3, imageDrawing[0][0].length);
  }

}