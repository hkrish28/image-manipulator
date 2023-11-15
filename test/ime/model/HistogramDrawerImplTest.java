package ime.model;

import static org.junit.Assert.*;

import ime.controller.MockHistogram;
import ime.controller.MockImageDrawer;
import org.junit.Before;
import org.junit.Test;

public class HistogramDrawerImplTest {
private MockImageDrawer mockImageDrawer;
private MockHistogram mockHistogram;
  private HistogramDrawerImpl histogramDrawer;

  public HistogramDrawerImplTest() {
    this.mockImageDrawer = new MockImageDrawer();
    this.mockHistogram = new MockHistogram();
  }

  @Before
  public void setUp() {

    histogramDrawer = new HistogramDrawerImpl(256, 256,mockImageDrawer);
    mockHistogram.clearLogger();
    mockImageDrawer.clearLogger();
  }
  @Test
  public void testVisualizeHistogram() {

    mockImageDrawer.setFailureFlag(false);
    mockHistogram.setFailureFlag(false);
    float[][][] result = histogramDrawer.visualizeHistogram(mockHistogram);
    assertEquals(256, result.length);
    assertEquals(256, result[0].length);
    assertEquals(3, result[0][0].length);
  }
  // fail histogram
  @Test(expected = IllegalArgumentException.class)
  public void testVisualizeHistogramInvalidHistogram() {

    mockImageDrawer.setFailureFlag(false);
    mockHistogram.setFailureFlag(true);
    float[][][] result = histogramDrawer.visualizeHistogram(mockHistogram);
    assertEquals(256, result.length);
    assertEquals(256, result[0].length);
    assertEquals(3, result[0][0].length);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testVisualizeHistogramInvalidimageDrawer() {
    mockImageDrawer.setFailureFlag(true);
    mockHistogram.setFailureFlag(false);
    float[][][] result = histogramDrawer.visualizeHistogram(mockHistogram);
    assertEquals(256, result.length);
    assertEquals(256, result[0].length);
    assertEquals(3, result[0][0].length);
  }

}