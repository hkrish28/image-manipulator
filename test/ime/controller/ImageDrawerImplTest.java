package ime.controller;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

public class ImageDrawerImplTest {

  private ImageDrawerImpl imageDrawer;

  @Before
  public void setUp() {
    imageDrawer = new ImageDrawerImpl();
    imageDrawer.setUpCanvas(100, 100);
  }

  @Test
  public void testMethodInvocationBeforeSetupCanvasThrowsException() {
    imageDrawer = new ImageDrawerImpl();
    assertThrows(IllegalArgumentException.class,
            () -> imageDrawer.setColor(new int[]{121, 231, 111}));
    assertThrows(IllegalArgumentException.class,
            () -> imageDrawer.drawLine(0, 1, 2, 3));
    assertThrows(IllegalArgumentException.class,
            () -> imageDrawer.getImageDrawing());
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
    float[] color = new float[]{255, 255, 255};
    imageDrawer.drawLine(x1, y1, x2, y2);

    float[][][] imageDrawing = imageDrawer.getImageDrawing();
    assertArrayEquals(color, imageDrawing[x1][y1], 0.0f);
    assertArrayEquals(color, imageDrawing[20][20], 0.0f); //point in betweem
    assertArrayEquals(color, imageDrawing[x2][y2], 0.0f);
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
    float[] expectedColor = {255, 0, 0}; // Red
    float[] defaultColor = {255, 255, 255}; // Red
    imageDrawer.setColor(colorPalette);
    imageDrawer.drawLine(10, 15, 10, 15); //draw on a point
    float[][][] imageDrawing = imageDrawer.getImageDrawing();

    assertArrayEquals(expectedColor, imageDrawing[15][10], 0);
    assertArrayEquals(defaultColor, imageDrawing[0][10], 0); //default color on other points
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

  @Test
  public void testSetUpCanvasInvalidDimensions() {

    assertThrows(IllegalArgumentException.class, () -> imageDrawer.setUpCanvas(-280, 150));
    assertThrows(IllegalArgumentException.class, () -> imageDrawer.setUpCanvas(250, -150));

  }

}