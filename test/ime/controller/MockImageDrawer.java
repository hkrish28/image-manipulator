package ime.controller;

public class MockImageDrawer implements ImageDrawer{
  private StringBuilder methodCallLogger;
  private Boolean fail;

  public MockImageDrawer(){
    this.methodCallLogger = new StringBuilder();
    this.fail = false;
  }


  @Override
  public float[][][] getImageDrawing() {
    methodCallLogger.append("get Image called\n");
    if (fail) {
      throw new IllegalArgumentException("image drawer failed");
    }
    return new float[256][256][3];
  }

  @Override
  public void drawLine(int x1, int y1, int x2, int y2) {
    methodCallLogger.append("draw line called\n" + x1+ " "+ y1+ " "+ x2+ " "+y2);
    if (fail) {
      throw new IllegalArgumentException("image drawer failed");
    }
  }

  @Override
  public void setColor(int[] colorPalette) {
    methodCallLogger.append("set color called\n");
    if (fail) {
      throw new IllegalArgumentException("image drawer failed");
    }
  }

  @Override
  public void setUpCanvas(int width, int height) {
    methodCallLogger.append("set up canvas called\n");
    if (fail) {
      throw new IllegalArgumentException("image drawer failed");
    }
  }
  public String getLogger() {
    return methodCallLogger.toString();
  }

  /**
   * Clear the log of method calls made to this MockFileHandlerProvider instance.
   */
  public void clearLogger() {
    methodCallLogger = new StringBuilder();
  }

  /**
   * Set the failure flag for the MockFileHandlerProvider.
   *
   * @param failFlag A boolean flag indicating whether the MockFileHandlerProvider should simulate
   *                 failures.
   */
  public void setFailureFlag(Boolean failFlag) {
    this.fail = failFlag;
  }
}
