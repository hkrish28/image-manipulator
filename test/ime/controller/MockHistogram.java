package ime.controller;

import ime.model.ColorChannel;
import ime.model.Histogram;
import java.util.List;

public class MockHistogram implements Histogram {
  private StringBuilder methodCallLogger;
  private Boolean fail;
  public MockHistogram(){
    this.methodCallLogger = new StringBuilder();
    this.fail = false;
  }

  @Override
  public int getChannelCount() {
    methodCallLogger.append("get channel count called\n");
    if (fail) {
      throw new IllegalArgumentException("histogram drawer failed");
    }
    return 0;
  }

  @Override
  public List<ColorChannel> getColorChannels() {
    methodCallLogger.append("get color channel  called\n");
    if (fail) {
      throw new IllegalArgumentException("histogram drawer failed");
    }
    return null;
  }

  @Override
  public int getPeakValue(int channelIndex, int start, int end) {
    methodCallLogger.append("get peak value called\n");
    if (fail) {
      throw new IllegalArgumentException("histogram drawer failed");
    }
    return 0;
  }

  @Override
  public int getMostFrequentValue(int channelIndex, int start, int end) {
    methodCallLogger.append("get most frequent value  called\n");
    if (fail) {
      throw new IllegalArgumentException("histogram drawer failed");
    }
    return 0;
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
