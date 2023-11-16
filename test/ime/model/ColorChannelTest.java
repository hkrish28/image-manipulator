package ime.model;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import org.junit.Test;

public class ColorChannelTest {

  @Test
  public void testColorChannelSize() {
    Arrays.asList(ColorChannel.values())
        .stream()
        .map(channel -> channel.rgb.length)
        .forEach(size -> assertTrue("RGB array size is not 3", size == 3));
  }
}