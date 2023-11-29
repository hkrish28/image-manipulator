package ime.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class FeaturesImplTest {

  private FeaturesImpl features;
  private GUIController guiController;

  private MockGUIView mockGUIView;
  private MockImgRepo imgRepo;
  private FileHandlerProvider fileHandlerProvider;

  @Before
  public void setup() {
    mockGUIView = new MockGUIView();
    imgRepo = new MockImgRepo();
    fileHandlerProvider = new FileHandlerProviderImpl();
    guiController = new GUIController(imgRepo, mockGUIView, fileHandlerProvider);
    features = new FeaturesImpl(guiController);
    //List<String> commandTokens = new ArrayList<>(tokens);
  }

  // invoking toggle when image is not there.
  @Test
  public void testToggle() {
    imgRepo.setFailureFlag(true);
    features.toggle();
    String expected = "message displayed" + " " + "Image Repository failed";
    assertEquals(expected, mockGUIView.getLogger());
    assertEquals("getImage called and previewImage passed\n", imgRepo.getLogger());
  }

  /**
   * invoke load when repo fails.
   */
  @Test
  public void testLoad() {
    imgRepo.setFailureFlag(true);
    features.loadImage();
    assertEquals("", imgRepo.getLogger());
  }

  /**
   * invoke save when model repo fails.
   */
  @Test
  public void testSave() {
    imgRepo.setFailureFlag(true);
    features.saveImage();
    assertEquals("", imgRepo.getLogger());
  }

  /**
   * test choose horizontal flip when model fails.
   */
  @Test
  public void testChooseHorizontalFlip() {
    features.chooseHorizontalFlip();
    assertHelper();
  }

  /**
   * test choose vertical flip when model fails.
   */
  @Test
  public void testChooseVerticalFlip() {
    features.chooseVerticalFlip();
    assertHelper();
  }

  /**
   * test choose color correct when model fails.
   */
  @Test
  public void testChooseColorCorrect() {
    features.chooseColorCorrect();
    assertHelper();
  }

  /**
   * test choose visualise red when model fails.
   */
  @Test
  public void testChooseVisualizeRed() {
    features.chooseVisualizeRed();
    String expected = "";
    assertEquals(expected, mockGUIView.getLogger());
  }

  /**
   * test choose visualise green when model fails.
   */
  @Test
  public void testChooseVisualizeGreen() {
    features.chooseVisualizeGreen();
    assertHelper();
  }

  /**
   * test choose visualise blue when model fails.
   */
  @Test
  public void testChooseVisualizeBlue() {
    features.chooseVisualizeBlue();
    assertHelper();
  }

  /**
   * test choose compress when model fails.
   */
  @Test
  public void testChooseCompression() {
    features.chooseCompression();
    String expected = "input is received Enter value between 0 - 100 for compression factor\n"
        + "Apply has been enabled\n"
        + "preview has been disabled\n";
    assertEquals(expected, mockGUIView.getLogger());
  }

  /**
   * test choose sepia when model fails.
   */
  @Test
  public void testchooseSepia() {
    features.chooseSepia();
    assertHelper();
  }

  /**
   * test choose luma when model fails.
   */
  @Test
  public void testChooseLumaGreyscale() {
    features.chooseLumaGreyscale();
    assertHelper();
  }

  /**
   * test choose levels adjust when model fails.
   */
  @Test
  public void testChooseLevelsAdjust() {
    features.chooseLevelsAdjust();
    String expected = "input is received Enter value between 0 - 253 for black point\n"
        + "input is received Enter value between 1 - 254 for mid point\n"
        + "input is received Enter value between 2 - 255 for white point\n"
        + "Apply has been enabled\n"
        + "preview has been enabled\n";
    assertEquals(expected, mockGUIView.getLogger());
    assertEquals("", imgRepo.getLogger());
  }

  /**
   * test choose blur when model fails.
   */
  @Test
  public void testChooseBlur() {
    features.chooseBlur();
    String expected = "Apply has been enabled\n"
        + "preview has been enabled\n";
    assertEquals(expected, mockGUIView.getLogger());
    assertEquals("", imgRepo.getLogger());
  }

  private void assertHelper() {
    imgRepo.setFailureFlag(true);
    String expected = "Apply has been enabled\n"
        + "preview has been disabled\n";
    assertEquals(expected, mockGUIView.getLogger());
    assertEquals("", imgRepo.getLogger());
  }

  /**
   * test load when both view and model works.
   */
  @Test
  public void testLoadImage() {
    features.loadImage();
    String expected = "Apply has been disabled\n"
        + "preview has been disabled\n"
        + "load success\n";
    assertEquals(expected, mockGUIView.getLogger());
  }

  /**
   * test save when both view and model works.
   */
  @Test
  public void testSaveImage() {
    features.saveImage();
    String expected = "Apply has been disabled\n"
        + "preview has been disabled\n"
        + "save success\n";
    assertEquals(expected, mockGUIView.getLogger());
  }

  /**
   * operation is not chosen and clicking apply.
   */
  @Test
  public void testApplyChosenValue() {
    //List<String> commandTokens = new ArrayList<>(tokens);
    features.applyChosenOperation();
    String expected = "operation not chosen";
    assertEquals(expected, mockGUIView.getLogger());
  }


  private void ApplyHelper(Runnable operation, boolean previewSupport, String name) {
    features.loadImage();
    operation.run();
    String expected = "Apply has been disabled\n"
        + "preview has been disabled\n"
        + "load success\n";
    if (name == "compress") {
      expected += "input is received Enter value between 0 - 100 for compression factor\n";
    } else if (name == "levels adjust") {
      expected += "input is received Enter value between 0 - 253 for black point\n"
          + "input is received Enter value between 1 - 254 for mid point\n"
          + "input is received Enter value between 2 - 255 for white point\n";
    }
    expected += "Apply has been enabled\n";
    features.applyChosenOperation();
    if (previewSupport) {
      expected += "preview has been enabled\n";
    } else {
      expected += "preview has been disabled\n";
    }
    expected += "image has been set\n"
        + "histogram has been set\n"
        + "toggle has been disabled\n";
    assertEquals(expected, mockGUIView.getLogger());
    assertEquals(name + " called guiImage and guiImage passed\n"
        + "histogram called guiImage and hist passed\n"
        + "getImage called and guiImage passed\n"
        + "getImage called and hist passed\n", imgRepo.getLogger());
  }

  /**
   * test when both model and view works.
   */
  @Test
  public void testChooseApplyHFlip() {
    ApplyHelper(() -> features.chooseHorizontalFlip(), false, "horizontal flip");
  }

  /**
   * test when both model and view works.
   */
  @Test
  public void testChooseApplyVFlip() {
    ApplyHelper(() -> features.chooseVerticalFlip(), false, "vertical flip");
  }

  /**
   * test when both model and view works.
   */
  @Test
  public void testChooseApplyBlur() {
    ApplyHelper(() -> features.chooseBlur(), true, "blurImage");
  }

  /**
   * test when both model and view works.
   */
  @Test
  public void testChooseApplySepia() {
    ApplyHelper(() -> features.chooseSepia(), true, "sepia");
  }

  /**
   * test when both model and view works.
   */
  @Test
  public void testChooseVisualizeRedApply() {
    ApplyHelper(() -> features.chooseVisualizeRed(), false, "red");
  }

  /**
   * test when both model and view works.
   */
  @Test
  public void testChooseVisualizeBlueApply() {
    ApplyHelper(() -> features.chooseVisualizeBlue(), false, "blue channel");
  }

  /**
   * test when both model and view works.
   */
  @Test
  public void testChooseVisualizeGreenApply() {
    ApplyHelper(() -> features.chooseVisualizeGreen(), false, "green channel");
  }

  /**
   * test when both model and view works.
   */
  @Test
  public void testChooseCompressionApply() {
    ApplyHelper(() -> features.chooseCompression(), false, "compress");
  }

  /**
   * test when both model and view works.
   */
  @Test
  public void testChooseLumaGreyscaleApply() {
    ApplyHelper(() -> features.chooseLumaGreyscale(), true, "luma gs");
  }

  /**
   * test when both model and view works.
   */
  @Test
  public void testChooseApplyLevelsAdjust() {
    ApplyHelper(() -> features.chooseLevelsAdjust(), true, "levels adjust");
  }

  private void testPreview(Runnable operation) {

    String expected = "Apply has been disabled\n"
        + "preview has been disabled\n"
        + "load success\n"
        + "Apply has been enabled\n"
        + "preview has been enabled\n"
        + "input is received Enter value between 0 - 100 for preview percentage\n"
        + "image has been set\n"
        + "toggle has been enabled\n"
        + "Apply has been enabled\n"
        + "preview has been disabled\n";
    testPreview(operation, expected);
  }

  private void testPreview(Runnable operation, String expectedString) {
    features.loadImage();
    operation.run();
    features.previewChosenOperation();
    assertEquals(expectedString, mockGUIView.getLogger());
    assertEquals("", imgRepo.getLogger());
  }

  @Test
  public void testPreviewBlur() {
    testPreview(() -> features.chooseBlur());
  }

  //shouldnt throw exception when operation that is not previewable is previewed.
  @Test
  public void testPreviewHFlip() {

    testPreview(() -> features.chooseHorizontalFlip());
    assertEquals("message displayed This operation can not be previewed.", mockGUIView.getLogger());
  }

  @Test
  public void testPreviewVFlip() {

    testPreview(() -> features.chooseVerticalFlip());
    assertEquals("message displayed This operation can not be previewed.", mockGUIView.getLogger());
  }

  @Test
  public void testPreviewVisualiseRed() {

    testPreview(() -> features.chooseVisualizeRed());
    assertEquals("message displayed This operation can not be previewed.", mockGUIView.getLogger());
  }

  @Test
  public void testPreviewVisualiseBlue() {

    testPreview(() -> features.chooseVisualizeBlue());
    assertEquals("message displayed This operation can not be previewed.", mockGUIView.getLogger());
  }

  @Test
  public void testPreviewVisualiseGreen() {

    testPreview(() -> features.chooseVisualizeGreen());
    assertEquals("message displayed This operation can not be previewed.", mockGUIView.getLogger());
  }

  @Test
  public void testPreviewSepia() {
    testPreview(() -> features.chooseSepia());
  }

  @Test
  public void testPreviewCompression() {
    testPreview(() -> features.chooseCompression());
    assertEquals("message displayed This operation can not be previewed.", mockGUIView.getLogger());
  }

  @Test
  public void testPreviewGreyScale() {
    testPreview(() -> features.chooseLumaGreyscale());
  }

  @Test
  public void testPreviewLevelsAdjust() {
    String expected = "Apply has been disabled\n"
        + "preview has been disabled\n"
        + "load success\n"
        + "input is received Enter value between 0 - 253 for black point\n"
        + "input is received Enter value between 1 - 254 for mid point\n"
        + "input is received Enter value between 2 - 255 for white point\n"
        + "Apply has been enabled\n"
        + "preview has been enabled\n"
        + "input is received Enter value between 0 - 100 for preview percentage\n"
        + "image has been set\n"
        + "toggle has been enabled\n"
        + "Apply has been enabled\n"
        + "preview has been disabled\n";
    testPreview(() -> features.chooseLevelsAdjust(), expected);
  }

}