package ime.controller;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FeaturesImplTest {

  private FeaturesImpl features;

  private MockGUIView mockGUIView;
  private MockImgRepo mockImgRepo;

  /**
   * setup method.
   */
  @Before
  public void setup() {
    mockGUIView = new MockGUIView();
    mockImgRepo = new MockImgRepo();
    FileHandlerProvider fileHandlerProvider = new FileHandlerProviderImpl();
    GUIController guiController = new GUIController(mockImgRepo, mockGUIView, fileHandlerProvider);
    features = new FeaturesImpl(guiController);
  }

  /**
   * invoking toggle when image is not there.
   */
  @Test
  public void testToggle() {
    mockImgRepo.setFailureFlag(true);
    features.toggle();
    String expected = "message displayed" + " " + "Image Repository failed\n";
    assertEquals(expected, mockGUIView.getLogger());
    assertEquals("getImage called and previewImage passed\n", mockImgRepo.getLogger());
  }


  /**
   * invoke load when repo fails.
   */
  @Test
  public void testLoad() {
    mockImgRepo.setFailureFlag(true);
    features.loadImage();
    assertEquals("loadImage called and guiImage passed\n", mockImgRepo.getLogger());
    assertEquals("Apply has been disabled\n" + "preview has been disabled\n" + "load success\n"
            + "message displayed Image Repository failed\n", mockGUIView.getLogger());
  }

  @Test
  public void testOverwritingImageCancel() {
    mockGUIView.setFail(true);
    features.loadImage();
    features.chooseBlur();
    features.applyChosenOperation();
    features.loadImage();
    assertEquals(
            "loadImage called and guiImage passed\n" + "histogram called guiImage and hist passed\n"
                    + "getImage called and guiImage passed\n" + "getImage called and hist passed\n"
                    + "blurImage called guiImage and guiImage passed\n"
                    + "histogram called guiImage and hist passed\n"
                    + "getImage called and guiImage passed\n" + "getImage called and hist passed\n",
            mockImgRepo.getLogger());
    assertEquals("Apply has been disabled\n" + "preview has been disabled\n" + "load success\n"
                    + "image has been set\n" + "histogram has been set\n" + "Apply has been enabled\n"
                    + "preview has been enabled\n" + "image has been set\n" + "histogram has been set\n"
                    + "toggle has been disabled\n" + "Apply has been disabled\n" + "preview has been disabled\n"
                    + "user canceled Current image is unsaved. Do you want to overwrite the image?\n",
            mockGUIView.getLogger());
  }

  @Test
  public void testOverwritingImageConfirm() {
    features.loadImage();
    features.chooseBlur();
    features.applyChosenOperation();
    features.loadImage();
    assertEquals(
            "loadImage called and guiImage passed\n" + "histogram called guiImage and hist passed\n"
                    + "getImage called and guiImage passed\n" + "getImage called and hist passed\n"
                    + "blurImage called guiImage and guiImage passed\n"
                    + "histogram called guiImage and hist passed\n"
                    + "getImage called and guiImage passed\n" + "getImage called and hist passed\n"
                    + "loadImage called and guiImage passed\n"
                    + "histogram called guiImage and hist passed\n"
                    + "getImage called and guiImage passed\n" + "getImage called and hist passed\n",
            mockImgRepo.getLogger());
    assertEquals("Apply has been disabled\n" + "preview has been disabled\n" + "load success\n"
                    + "image has been set\n" + "histogram has been set\n" + "Apply has been enabled\n"
                    + "preview has been enabled\n" + "image has been set\n" + "histogram has been set\n"
                    + "toggle has been disabled\n" + "Apply has been disabled\n" + "preview has been disabled\n"
                    + "confirmation received Current image is unsaved. Do you want to overwrite the image?\n"
                    + "load success\n" + "image has been set\n" + "histogram has been set\n",
            mockGUIView.getLogger());
  }

  @Test
  public void testLoadImageNotApply() {
    features.loadImage();
    features.chooseBlur();
    features.previewChosenOperation();
    features.loadImage();
    assertEquals(
            "loadImage called and guiImage passed\n" + "histogram called guiImage and hist passed\n"
                    + "getImage called and guiImage passed\n" + "getImage called and hist passed\n"
                    + "preview called 0.0 and guiImage and previewImage passed\n"
                    + "blurImage called guiImage and previewImage passed\n"
                    + "getImage called and previewImage passed\n" + "loadImage called and guiImage passed\n"
                    + "histogram called guiImage and hist passed\n"
                    + "getImage called and guiImage passed\n" + "getImage called and hist passed\n",
            mockImgRepo.getLogger());
    assertEquals("Apply has been disabled\n" + "preview has been disabled\n" + "load success\n"
            + "image has been set\n" + "histogram has been set\n" + "Apply has been enabled\n"
            + "preview has been enabled\n"
            + "input is received Enter value between 0 - 100 for preview percentage\n"
            + "image has been set\n" + "toggle has been enabled\n" + "Apply has been disabled\n"
            + "preview has been disabled\n" + "load success\n" + "image has been set\n"
            + "histogram has been set\n", mockGUIView.getLogger());
  }

  // one op another op (3) apply
  @Test
  public void testMultipleOperations() {
    features.loadImage();
    features.chooseBlur();
    features.chooseVisualizeRed();
    features.chooseSepia();
    features.applyChosenOperation();
    assertEquals(
            "loadImage called and guiImage passed\n" + "histogram called guiImage and hist passed\n"
                    + "getImage called and guiImage passed\n" + "getImage called and hist passed\n"
                    + "sepia called guiImage and guiImage passed\n"
                    + "histogram called guiImage and hist passed\n"
                    + "getImage called and guiImage passed\n" + "getImage called and hist passed\n",
            mockImgRepo.getLogger());
    assertEquals("Apply has been disabled\n" + "preview has been disabled\n" + "load success\n"
            + "image has been set\n" + "histogram has been set\n" + "Apply has been enabled\n"
            + "preview has been enabled\n" + "Apply has been enabled\n" + "preview has been disabled\n"
            + "Apply has been enabled\n" + "preview has been enabled\n" + "image has been set\n"
            + "histogram has been set\n" + "toggle has been disabled\n", mockGUIView.getLogger());
  }

  // one op another(compress)view-fail apply
  @Test
  public void testMultipleOperationWithUserInput() {
    features.loadImage();
    features.chooseHorizontalFlip();
    features.chooseCompression();
    features.applyChosenOperation();
    assertEquals(
            "loadImage called and guiImage passed\n" + "histogram called guiImage and hist passed\n"
                    + "getImage called and guiImage passed\n" + "getImage called and hist passed\n"
                    + "compress called guiImage and guiImage passed\n"
                    + "histogram called guiImage and hist passed\n"
                    + "getImage called and guiImage passed\n" + "getImage called and hist passed\n",
            mockImgRepo.getLogger());
    assertEquals("Apply has been disabled\n" + "preview has been disabled\n" + "load success\n"
            + "image has been set\n" + "histogram has been set\n" + "Apply has been enabled\n"
            + "preview has been disabled\n"
            + "input is received Enter value between 0 - 100 for compression factor\n"
            + "Apply has been enabled\n" + "preview has been disabled\n" + "image has been set\n"
            + "histogram has been set\n" + "toggle has been disabled\n", mockGUIView.getLogger());
  }

  @Test
  public void testWholeFlow() {
    mockGUIView.setFail(true);
    features.loadImage();
    features.chooseVisualizeBlue();
    features.applyChosenOperation();
    features.loadImage();
    mockGUIView.setFail(false);
    features.saveImage();
    features.loadImage();
    assertEquals(
            "loadImage called and guiImage passed\n" + "histogram called guiImage and hist passed\n"
                    + "getImage called and guiImage passed\n" + "getImage called and hist passed\n"
                    + "blue channel called guiImage and guiImage passed\n"
                    + "histogram called guiImage and hist passed\n"
                    + "getImage called and guiImage passed\n" + "getImage called and hist passed\n"
                    + "getImage called and guiImage passed\n"
                    + "histogram called guiImage and hist passed\n"
                    + "getImage called and guiImage passed\n" + "getImage called and hist passed\n"
                    + "loadImage called and guiImage passed\n"
                    + "histogram called guiImage and hist passed\n"
                    + "getImage called and guiImage passed\n" + "getImage called and hist passed\n",
            mockImgRepo.getLogger());
    assertEquals("Apply has been disabled\n" + "preview has been disabled\n" + "load success\n"
            + "image has been set\n" + "histogram has been set\n" + "Apply has been enabled\n"
            + "preview has been disabled\n" + "image has been set\n" + "histogram has been set\n"
            + "toggle has been disabled\n" + "Apply has been disabled\n" + "preview has been disabled\n"
            + "user canceled Current image is unsaved. Do you want to overwrite the image?\n"
            + "Apply has been disabled\n" + "preview has been disabled\n" + "save success\n"
            + "image has been set\n" + "histogram has been set\n" + "Apply has been disabled\n"
            + "preview has been disabled\n" + "load success\n" + "image has been set\n"
            + "histogram has been set\n", mockGUIView.getLogger());
  }

  /**
   * invoke save when model repo fails.
   */
  @Test
  public void testSave() {
    mockImgRepo.setFailureFlag(true);
    features.saveImage();
    assertEquals("getImage called and guiImage passed\n", mockImgRepo.getLogger());
  }

  /**
   * test choose horizontal flip when model fails.
   */
  @Test
  public void testChooseHorizontalFlip() {
    features.chooseHorizontalFlip();
    assertNoImageLoaded(false);
  }

  /**
   * test choose vertical flip when model fails.
   */
  @Test
  public void testChooseVerticalFlip() {
    features.chooseVerticalFlip();
    assertNoImageLoaded(false);
  }

  /**
   * test choose color correct when model fails.
   */
  @Test
  public void testChooseColorCorrect() {
    features.chooseColorCorrect();
    assertNoImageLoaded(true);
  }

  /**
   * test choose visualise red when model fails.
   */
  @Test
  public void testChooseVisualizeRed() {
    features.chooseVisualizeRed();
    assertNoImageLoaded(false);
  }

  /**
   * test choose visualise green when model fails.
   */
  @Test
  public void testChooseVisualizeGreen() {
    features.chooseVisualizeGreen();
    assertNoImageLoaded(false);
  }

  /**
   * test choose visualise blue when model fails.
   */
  @Test
  public void testChooseVisualizeBlue() {
    features.chooseVisualizeBlue();
    assertNoImageLoaded(false);
  }

  /**
   * test choose compress when model fails.
   */
  @Test
  public void testChooseCompression() {
    features.chooseCompression();
    String expected = "input is received Enter value between 0 - 100 for compression factor\n"
            + "Apply has been enabled\n" + "preview has been disabled\n";
    assertEquals(expected, mockGUIView.getLogger());
  }

  /**
   * test choose sepia when model fails.
   */
  @Test
  public void testchooseSepia() {
    features.chooseSepia();
    assertNoImageLoaded(true);
  }

  /**
   * test choose luma when model fails.
   */
  @Test
  public void testChooseLumaGreyscale() {
    features.chooseLumaGreyscale();
    assertNoImageLoaded(true);
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
            + "Apply has been enabled\n" + "preview has been enabled\n";
    assertEquals(expected, mockGUIView.getLogger());
    assertEquals("", mockImgRepo.getLogger());
  }

  /**
   * test choose blur when model fails.
   */
  @Test
  public void testChooseBlur() {
    features.chooseBlur();
    String expected = "Apply has been enabled\n" + "preview has been enabled\n";
    assertEquals(expected, mockGUIView.getLogger());
    assertEquals("", mockImgRepo.getLogger());
  }

  private void assertNoImageLoaded(boolean previewSupport) {
    mockImgRepo.setFailureFlag(true);
    String expected = "Apply has been enabled\npreview has been ";
    expected += previewSupport ? "enabled\n" : "disabled\n";
    assertEquals(expected, mockGUIView.getLogger());
    assertEquals("", mockImgRepo.getLogger());
  }

  /**
   * test load when both view and model works.
   */
  @Test
  public void testLoadImage() {
    features.loadImage();
    String expected =
            "Apply has been disabled\n" + "preview has been disabled\n" + "load success\n" +
                    "image has been set\n" + "histogram has been set\n";
    String expectedImgRepo = "loadImage called and guiImage passed\n" +
            "histogram called guiImage and hist passed\n" +
            "getImage called and guiImage passed\n" +
            "getImage called and hist passed\n";
    assertEquals(expected, mockGUIView.getLogger());
    assertEquals(expectedImgRepo, mockImgRepo.getLogger());
  }

  /**
   * test when invalid file was chosen.
   */
  @Test
  public void testInvalidFileChosen() {
    mockGUIView.setIOFail(true);
    features.loadImage();
    String expected =
            "Apply has been disabled\n" + "preview has been disabled\n" + "load unsuccessful\n"
                    + "message displayed Invalid file format provided.\n";
    assertEquals(expected, mockGUIView.getLogger());
    assertEquals("", mockImgRepo.getLogger());
  }

  /**
   * test invalid file to save.
   */
  @Test
  public void testInvalidFileSave() {
    mockGUIView.setIOFail(true);

    features.saveImage();
    String expected =
            "Apply has been disabled\n" + "preview has been disabled\n" + "save unsuccessful\n"
                    + "message displayed Invalid file format provided.\n";
    assertEquals(expected, mockGUIView.getLogger());
    assertEquals("getImage called and guiImage passed\n", mockImgRepo.getLogger());
  }

  /**
   * test when invalid image is toggled.
   */
  @Test
  public void testInvalidFileToggle() {
    mockGUIView.setFail(true);
    features.toggle();
    String expected = "image has been set\n";
    assertEquals(expected, mockGUIView.getLogger());
    assertEquals("getImage called and previewImage passed\n", mockImgRepo.getLogger());
  }

  /**
   * test when preview is done when no operation is chosen.
   */
  @Test
  public void testPreviewWhenNoOperationIsChosen() {
    mockGUIView.setFail(true);
    features.loadImage();
    features.previewChosenOperation();
    String expectedView = "Apply has been disabled\n" +
            "preview has been disabled\n" +
            "load success\n" +
            "image has been set\n" +
            "histogram has been set\n" +
            "message displayed Operation not chosen\n";
    String expectedImgRepo = "loadImage called and guiImage passed\n" +
            "histogram called guiImage and hist passed\n" +
            "getImage called and guiImage passed\n" +
            "getImage called and hist passed\n";
    assertEquals(expectedView, mockGUIView.getLogger());
    assertEquals(expectedImgRepo, mockImgRepo.getLogger());
  }

  /**
   * test when apply is chosen when no image is loaded.
   */
  @Test
  public void testApplyIsChosenWhenImageNotLoaded() {
    mockGUIView.setFail(true);
    features.applyChosenOperation();
    String expected = "message displayed Operation not chosen\n";
    assertEquals(expected, mockGUIView.getLogger());
    assertEquals("", mockImgRepo.getLogger());
  }

  /**
   * test save when both view and model works.
   */
  @Test
  public void testSaveImage() {
    features.saveImage();
    String expected =
            "Apply has been disabled\n" + "preview has been disabled\n" + "save success\n" +
                    "image has been set\n" + "histogram has been set\n";
    assertEquals(expected, mockGUIView.getLogger());
  }

  /**
   * operation is not chosen and clicking apply.
   */
  @Test
  public void testApplyChosenValue() {
    features.applyChosenOperation();
    String expected = "message displayed Operation not chosen\n";
    assertEquals(expected, mockGUIView.getLogger());
  }


  private void applyHelper(Runnable operation, boolean previewSupport, String name) {
    features.loadImage();
    operation.run();
    String expected =
            "Apply has been disabled\n" + "preview has been disabled\n" + "load success\nimage has been set\n" +
                    "histogram has been set\n";
    if (name.equals("compress")) {
      expected += "input is received Enter value between 0 - 100 for compression factor\n";
    } else if (name.equals("levels adjust")) {
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
    expected += "image has been set\n" + "histogram has been set\n" + "toggle has been disabled\n";
    assertEquals(expected, mockGUIView.getLogger());
    assertEquals("loadImage called and guiImage passed\n" +
            "histogram called guiImage and hist passed\n" +
            "getImage called and guiImage passed\n" +
            "getImage called and hist passed\n" + name + " called guiImage and guiImage passed\n"
            + "histogram called guiImage and hist passed\n" + "getImage called and guiImage passed\n"
            + "getImage called and hist passed\n", mockImgRepo.getLogger());
  }

  /**
   * test when both model and view works.
   */
  @Test
  public void testChooseApplyHFlip() {
    applyHelper(() -> features.chooseHorizontalFlip(), false, "horizontal flip");
  }

  /**
   * test when both model and view works.
   */
  @Test
  public void testChooseApplyVFlip() {
    applyHelper(() -> features.chooseVerticalFlip(), false, "vertical flip");
  }

  /**
   * test when both model and view works.
   */
  @Test
  public void testChooseApplyBlur() {
    applyHelper(() -> features.chooseBlur(), true, "blurImage");
  }

  /**
   * test when both model and view works.
   */
  @Test
  public void testChooseApplySepia() {
    applyHelper(() -> features.chooseSepia(), true, "sepia");
  }

  /**
   * test when both model and view works.
   */
  @Test
  public void testChooseVisualizeRedApply() {
    applyHelper(() -> features.chooseVisualizeRed(), false, "red channel");
  }

  /**
   * test when both model and view works.
   */
  @Test
  public void testChooseVisualizeBlueApply() {
    applyHelper(() -> features.chooseVisualizeBlue(), false, "blue channel");
  }

  /**
   * test when both model and view works.
   */
  @Test
  public void testChooseVisualizeGreenApply() {
    applyHelper(() -> features.chooseVisualizeGreen(), false, "green channel");
  }

  /**
   * test when both model and view works.
   */
  @Test
  public void testChooseCompressionApply() {
    applyHelper(() -> features.chooseCompression(), false, "compress");
  }

  /**
   * test when both model and view works.
   */
  @Test
  public void testChooseLumaGreyscaleApply() {
    applyHelper(() -> features.chooseLumaGreyscale(), true, "luma gs");
  }

  /**
   * test when both model and view works.
   */
  @Test
  public void testChooseApplyLevelsAdjust() {
    applyHelper(() -> features.chooseLevelsAdjust(), true, "levels adjust");
  }

  private void testPreview(Runnable operation, String operationName, boolean previewSupport) {
    String unsupportedCommand = "preview has been disabled\n"
            + "input is received Enter value between 0 - 100 for preview percentage\n"
            + "message displayed This operation can not be previewed.\n";
    String supportedCommand = "preview has been enabled\n"
            + "input is received Enter value between 0 - 100 for preview percentage\n"
            + "image has been set\n" + "toggle has been enabled\n";
    String expected = "Apply has been disabled\n" + "preview has been disabled\n" + "load success\n"
            + "image has been set\n" + "histogram has been set\n" + "Apply has been enabled\n";

    expected += previewSupport ? supportedCommand : unsupportedCommand;
    testPreview(operation, expected, operationName, previewSupport);
  }

  private void testPreview(Runnable operation, String expectedString, String operationName,
                           boolean previewSupport) {
    features.loadImage();
    operation.run();
    features.previewChosenOperation();
    assertEquals(expectedString, mockGUIView.getLogger());
    String expectedImgRepoLogger = previewSupport ? "loadImage called and guiImage passed\n" +
            "histogram called guiImage and hist passed\n"
            + "getImage called and guiImage passed\n"
            + "getImage called and hist passed\npreview called 0.0 and guiImage and previewImage passed\n"
            + operationName + " called guiImage and previewImage passed\n"
            + "getImage called and previewImage passed\n"
            : "loadImage called and guiImage passed\n" +
            "histogram called guiImage and hist passed\n" + "getImage called and guiImage passed\n"
            + "getImage called and hist passed\n";
    assertEquals(expectedImgRepoLogger, mockImgRepo.getLogger());
  }

  @Test
  public void testPreviewBlur() {
    testPreview(() -> features.chooseBlur(), "blurImage", true);
  }

  //shouldnt throw exception when operation that is not previewable is previewed.
  @Test
  public void testPreviewHFlip() {

    testPreview(() -> features.chooseHorizontalFlip(), "horizontal flip", false);
  }

  @Test
  public void testPreviewVFlip() {

    testPreview(() -> features.chooseVerticalFlip(), "vertical flip", false);
  }

  @Test
  public void testPreviewVisualiseRed() {
    testPreview(() -> features.chooseVisualizeRed(), "visualize red", false);
  }

  @Test
  public void testPreviewVisualiseBlue() {
    testPreview(() -> features.chooseVisualizeBlue(), "blue channel", false);
  }

  @Test
  public void testPreviewVisualiseGreen() {
    testPreview(() -> features.chooseVisualizeGreen(), "green channel", false);
  }

  @Test
  public void testPreviewSepia() {
    testPreview(() -> features.chooseSepia(), "sepia", true);
  }

  @Test
  public void testPreviewCompression() {
    String expected = "Apply has been disabled\n" + "preview has been disabled\n" + "load success\n"
            + "image has been set\n" + "histogram has been set\n"
            + "input is received Enter value between 0 - 100 for compression factor\n"
            + "Apply has been enabled\n" + "preview has been disabled\n"
            + "input is received Enter value between 0 - 100 for preview percentage\n"
            + "message displayed This operation can not be previewed.\n";
    testPreview(() -> features.chooseCompression(), expected, "compression", false);
  }

  @Test
  public void testPreviewGreyScale() {
    testPreview(() -> features.chooseLumaGreyscale(), "luma gs", true);
  }

  @Test
  public void testPreviewLevelsAdjust() {
    String expected = "Apply has been disabled\n" + "preview has been disabled\n" + "load success\n"
            + "image has been set\n" + "histogram has been set\n"
            + "input is received Enter value between 0 - 253 for black point\n"
            + "input is received Enter value between 1 - 254 for mid point\n"
            + "input is received Enter value between 2 - 255 for white point\n"
            + "Apply has been enabled\n" + "preview has been enabled\n"
            + "input is received Enter value between 0 - 100 for preview percentage\n"
            + "image has been set\n" + "toggle has been enabled\n";
    String expectedImageRepoLogger = "loadImage called and guiImage passed\n" +
            "histogram called guiImage and hist passed\n" + "getImage called and guiImage passed\n"
            + "getImage called and hist passed\npreview called 3.0 and guiImage and previewImage passed\n"
            + "levels adjust called guiImage and previewImage passed\n"
            + "getImage called and previewImage passed\n";
    features.loadImage();
    features.chooseLevelsAdjust();
    features.previewChosenOperation();
    assertEquals(expected, mockGUIView.getLogger());
    assertEquals(expectedImageRepoLogger, mockImgRepo.getLogger());
  }

}