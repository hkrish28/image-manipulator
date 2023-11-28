package ime.controller;

import static org.junit.Assert.*;

import ime.model.ImageRepository;
import ime.model.ImageRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
public class FeaturesImplTest {
  private FeaturesImpl features;
  private GUIController guiController;
  private MockGUIView mockGUIView;
  private ImageRepository ImgRepo;
  private FileHandlerProvider fileHandlerProvider;
@Before
  public void setup(){
    mockGUIView= new MockGUIView();
    ImgRepo = new ImageRepositoryImpl();
    fileHandlerProvider = new FileHandlerProviderImpl();
    guiController=new GUIController(ImgRepo,mockGUIView,fileHandlerProvider);
  features = new FeaturesImpl(guiController);
  }
  // invoking toggle when image is not there.
  @Test
  public void testToggle() {
    features.toggle();
    String expected = "message displayed"+" "+"image name invalid";
    assertEquals(expected, mockGUIView.getLogger());
  }
  @Test
  public void testChooseHorizontalFlip(){
  features.chooseHorizontalFlip();
    assertHelper();
  }
  @Test
  public void testChooseVerticalFlip(){
  features.chooseVerticalFlip();
  assertHelper();
  }
  @Test
  public void testChooseColorCorrect(){
    features.chooseColorCorrect();
    assertHelper();
  }
  @Test
  public void testChooseVisualizeRed(){
    features.chooseVisualizeRed();
    assertHelper();
  }
  @Test
  public void testChooseVisualizeGreen(){
    features.chooseVisualizeGreen();
    assertHelper();
  }
  @Test
  public void testChooseVisualizeBlue(){
    features.chooseVisualizeBlue();
    assertHelper();
  }
  @Test
  public void testChooseCompression(){
    features.chooseCompression();
    assertHelper();
  }
  @Test
  public void testchooseSepia(){
    features.chooseSepia();
    assertHelper();
  }
  @Test
  public void testChooseLumaGreyscale(){
    features.chooseLumaGreyscale();
    assertHelper();
  }
  @Test
  public void testChooseLevelsAdjust(){
    features.chooseLevelsAdjust();
    assertHelper();
  }
  @Test
  public void testChooseBlur(){
    features.chooseBlur();
    assertHelper();
  }
private void assertHelper(){
  String expected = "message displayed"+" "+"image name invalid";
  assertEquals(expected, mockGUIView.getLogger());
}

}