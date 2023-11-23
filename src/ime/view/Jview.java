package ime.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import ime.controller.CommandEnum;
import ime.controller.Features;

public class Jview extends JFrame implements ActionListener, GUIView {

  List<Option> optionList = new ArrayList<>();
  private Features features;
  private Runnable operation;
  private JPanel mainPanel;

  private JLabel mainImage;
  private JLabel histogramImage;

  private CommandEnum commandEnumSelected;

  private List<String> additionalInputs;
  private JPanel confirmationPanel;
  private JPanel previewPanel;
  private JPanel togglePanel;

  public Jview() {
    setTitle("Image processing");
    setSize(800, 800);

    mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    //scroll bars around this main panel
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    addImagePane();

    // intialise the options
    initialiseOption();

    togglePanel = new JPanel();
    togglePanel.setVisible(false);
    mainPanel.add(togglePanel);
    // toggle button
    addButtonToPanel("Toggle", togglePanel);

    // showing an image
    addOptions();

    // apply filter button
    addConfirmationPanel();

  }

  private void addButtonToPanel(String name, JPanel panel) {
    JButton newButton = new JButton(name);
    newButton.setActionCommand(name);
    newButton.addActionListener(this);
    panel.add(newButton);
  }

  private void addConfirmationPanel() {
    confirmationPanel = new JPanel();
    confirmationPanel.setLayout(new FlowLayout());
    confirmationPanel.setVisible(false);
    mainPanel.add(confirmationPanel);
    previewPanel = new JPanel();
    confirmationPanel.add(previewPanel);
    addButtonToPanel("Preview", previewPanel);
    addButtonToPanel("Apply filter", confirmationPanel);
  }

  private void addImagePane() {
    JPanel imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Active Image"));
    imagePanel.setLayout(new BorderLayout());
    mainPanel.add(imagePanel);
    mainImage = new JLabel();
    JScrollPane imageScrollPane = new JScrollPane(mainImage);
    imageScrollPane.setPreferredSize(new Dimension(320, 256));
//    imagePanel.add(imageScrollPane);

// showing histogram
    JPanel imagePanelHistogram = new JPanel();
    //a border around the panel with a caption
    imagePanelHistogram.setBorder(BorderFactory.createTitledBorder("Showing histogram"));
    imagePanelHistogram.setLayout(new BorderLayout());
//    mainPanel.add(imagePanelHistogram);
    histogramImage = new JLabel();
    JScrollPane imageScrollPaneHistogram = new JScrollPane(histogramImage);
    imageScrollPaneHistogram.setPreferredSize(new Dimension(256, 256));
//    imagePanelHistogram.add(imageScrollPaneHistogram);
//    imagePanel.add(imageScrollPaneHistogram);
    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, imageScrollPane, imageScrollPaneHistogram);
    splitPane.setOneTouchExpandable(true);
//    splitPane.setDividerLocation(150);
    splitPane.setResizeWeight(0.7);
    imagePanel.add(splitPane);
  }

  private void addOptions() {
    // radio buttons
    JPanel radioPanel = new JPanel();
    radioPanel.setBorder(BorderFactory.createTitledBorder("Filter options "));
//    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));
    radioPanel.setLayout(new GridLayout(0, 4));


    for (int i = 0; i < optionList.size(); i++) {
      Option option = optionList.get(i);
      addButtonToPanel(option.getName(), radioPanel);
//      if (optionList.get(i).isPreviewable()) {
//        // Add a preview button for each option
//        JButton previewButton = new JButton("Preview");
//        previewButton.setActionCommand("Preview");
//        int finalI = i;
//        previewButton.addActionListener(event ->
//                previewImage(previewPercent -> features.previewOperation(optionList.get(finalI).getCommandEnum(), previewPercent)));
//        radioPanel.add(previewButton);
//      }

      /*if (optionList.get(i).additionalInputs != null) {
        JTextField jTextField[] = new JTextField[optionList.get(i).getAdditionalInputs().size()];
        feildmap.put(optionList.get(i).getName(),jTextField);
        for (int j = 0; j < optionList.get(i).getAdditionalInputs().size(); j++) {
           jTextField[i] = new JTextField(
              optionList.get(i).getAdditionalInputs().get(j).getName());

          jTextField[i].setVisible(false);
          jTextField[i].setPreferredSize(new Dimension(100,50));
          radioPanel.add(inputField);
        }
      }*/
    }

    mainPanel.add(radioPanel);
  }

  private void previewImage(Consumer<Integer> previewer) {
    previewer.accept(previewAction());
  }

  private void initialiseOption() {
    List<AdditionalInput> additionalInputs = Arrays.asList(new AdditionalInput("b", 0, 0, 255),
            new AdditionalInput("m", 128, 0, 255), new AdditionalInput("w", 255, 0, 255));
    List<AdditionalInput> additional = Arrays.asList(
            new AdditionalInput("compress percent", 0, 0, 100));
    optionList.add(new Option("Load Image", false, null, CommandEnum.load));
    optionList.add(new Option("Save Image", false, null, CommandEnum.save));
    optionList.add(new Option("Visualize Red", false, null, CommandEnum.red_component));
    optionList.add(new Option("Visualize Green", false, null, CommandEnum.green_component));
    optionList.add(new Option("Visualize Blue", false, null, CommandEnum.blue_component));
    optionList.add(new Option("Flip Vertically", false, null, CommandEnum.verticalFlip));
    optionList.add(new Option("Flip Horizontally", false, null, CommandEnum.horizontalFlip));
    optionList.add(new Option("Blur", true, null, CommandEnum.blur));
    optionList.add(new Option("Sharpen", true, null, CommandEnum.sharpen));
    optionList.add(new Option("Convert to Greyscale", true, null, CommandEnum.luma_component));
    optionList.add(new Option("Convert to Sepia", true, null, CommandEnum.sepia));
    optionList.add(new Option("Compression", true, additional, CommandEnum.compress));
    optionList.add(new Option("Levels Adjust", true, additionalInputs, CommandEnum.levels_adjust));
    optionList.add(new Option("Color Correct", true, additionalInputs, CommandEnum.color_correct));
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    switch (command) {
      case "Load Image":
        commandEnumSelected = CommandEnum.load;
        openFileAction();
        previewPanel.setVisible(getOptionFromName(command).isPreviewable());
        break;
      case "Save Image":
        commandEnumSelected = CommandEnum.save;
        saveFileAction();
        previewPanel.setVisible(getOptionFromName(command).isPreviewable());
        break;
      case "Apply filter":
        // Implement apply filter action
        togglePanel.setVisible(false);
        confirmationPanel.setVisible(false);
        previewPanel.setVisible(false);
        applyFilterAction();
        break;
      case "Toggle":
        commandEnumSelected = null;
        confirmationPanel.setVisible(false);
        features.toggle();
        break;
      case "Visualize Red":
        commandEnumSelected = CommandEnum.red_component;
        confirmationPanel.setVisible(true);
        previewPanel.setVisible(getOptionFromName(command).isPreviewable());
        operation = () -> features.visualizeRed();
        break;
      case "Visualize Green":
        commandEnumSelected = CommandEnum.green_component;
        confirmationPanel.setVisible(true);
        previewPanel.setVisible(getOptionFromName(command).isPreviewable());
        operation = () -> features.visualizeGreen();
        break;
      case "Visualize Blue":
        commandEnumSelected = CommandEnum.blue_component;
        confirmationPanel.setVisible(true);
        previewPanel.setVisible(getOptionFromName(command).isPreviewable());
        operation = () -> features.visualizeBlue();
        break;
      case "Flip Vertically":
        commandEnumSelected = CommandEnum.verticalFlip;
        confirmationPanel.setVisible(true);
        previewPanel.setVisible(getOptionFromName(command).isPreviewable());
        operation = () -> features.applyVerticalFlip();
        break;
      case "Flip Horizontally":
        commandEnumSelected = CommandEnum.horizontalFlip;
        confirmationPanel.setVisible(true);
        previewPanel.setVisible(getOptionFromName(command).isPreviewable());
        operation = () -> features.applyHorizontalFlip();
        break;
      case "Blur":
        commandEnumSelected = CommandEnum.blur;
        confirmationPanel.setVisible(true);
        previewPanel.setVisible(getOptionFromName(command).isPreviewable());
        operation = () -> features.applyBlur();
        break;
      case "Sharpen":
        commandEnumSelected = CommandEnum.sharpen;
        confirmationPanel.setVisible(true);
        operation = () -> features.applySharpen();
        previewPanel.setVisible(getOptionFromName(command).isPreviewable());
        break;
      case "Convert to Greyscale":
        commandEnumSelected = CommandEnum.luma_component;
        operation = () -> features.applyLumaGreyScale();
        confirmationPanel.setVisible(true);
        previewPanel.setVisible(getOptionFromName(command).isPreviewable());
        break;
      case "Convert to Sepia":
        commandEnumSelected = CommandEnum.sepia;
        confirmationPanel.setVisible(true);
        operation = () -> features.applySepia();
        previewPanel.setVisible(getOptionFromName(command).isPreviewable());
        break;
      case "Compression":
        /*JTextField[] compressFeild = feildmap.get("Compression");
        for (int i=0;i< compressFeild.length;i++){
        compressFeild[i].setVisible(true);
        }*/
        askForCompressprecent();
        previewPanel.setVisible(getOptionFromName(command).isPreviewable());
        //operation=()->features.applyCompression(askForCompressprecent());
        break;
      case "Color Correct":
        commandEnumSelected = CommandEnum.color_correct;
        operation = () -> features.applyColorCorrection();
        confirmationPanel.setVisible(true);
        previewPanel.setVisible(getOptionFromName(command).isPreviewable());
        break;
      case "Levels Adjust":
        commandEnumSelected = CommandEnum.levels_adjust;
        bmwValues();
        confirmationPanel.setVisible(true);
        previewPanel.setVisible(getOptionFromName(command).isPreviewable());
        break;
      case "Preview":
        features.previewOperation(commandEnumSelected, additionalInputs, previewAction());
        togglePanel.setVisible(true);
        previewPanel.setVisible(false);
        break;
      default:
        JOptionPane.showMessageDialog(this, "Invalid input.", "Error",
                JOptionPane.ERROR_MESSAGE);
    }
  }

  private void askForCompressprecent() {
    String inputValue = JOptionPane.showInputDialog("Enter compress percent (integer):");
    try {
      int compressPercent = Integer.parseInt(inputValue);
      additionalInputs = Arrays.asList(inputValue);
      //System.out.println("compress percent entered: " + compressPercent);
      operation = () -> features.applyCompression(compressPercent);
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error",
              JOptionPane.ERROR_MESSAGE);
      askForCompressprecent();
    }
  }

  private void bmwValues() {
    String inputValueb = JOptionPane.showInputDialog("Enter b value (integer):");
    String inputValuem = JOptionPane.showInputDialog("Enter m value (integer):");
    String inputValuew = JOptionPane.showInputDialog("Enter w value (integer):");
    try {
      int b = Integer.parseInt(inputValueb);
      int m = Integer.parseInt(inputValuem);
      int w = Integer.parseInt(inputValuew);
      operation = () -> features.applyLevelsAdjust(b, m, w);
      additionalInputs = Arrays.asList(inputValueb, inputValuem, inputValuew);
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error",
              JOptionPane.ERROR_MESSAGE);
    }

  }


  private void saveFileAction() {
    final JFileChooser fchooser = new JFileChooser(".");
    int retvalue = fchooser.showSaveDialog(Jview.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      features.saveImage(f.getAbsolutePath());
    }
  }

  private void openFileAction() {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(Jview.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      features.loadImage(f.getAbsolutePath());
    }
  }

  private void applyFilterAction() {
    operation.run();
  }

  private int previewAction() {
    String inputValue = JOptionPane.showInputDialog("Enter preview percent (integer):");
    try {
      int previewPercent = Integer.parseInt(inputValue);
      System.out.println("preview percent entered: " + previewPercent);
      return previewPercent;
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error",
              JOptionPane.ERROR_MESSAGE);
      return previewAction();
    }
//    JOptionPane.showMessageDialog(this, "Previewing: " + selectedOption);
  }


  @Override
  public void setFeatures(Features features) {
    this.features = features;
  }

  @Override
  public void setOptions(List<Option> optionList) {
    this.optionList = optionList;
  }

  @Override
  public void setImage(Image image) {
    ImageIcon imageIcon = new ImageIcon(image);
    mainImage.setIcon(imageIcon);
  }

  @Override
  public void setHistogram(Image image) {
    ImageIcon imageIcon = new ImageIcon(image);
    histogramImage.setIcon(imageIcon);
  }

  private Option getOptionFromName(String optionName) {
    for (int i = 0; i < optionList.size(); i++) {
      if (optionList.get(i).getName().equals(optionName)) {
        return optionList.get(i);
      }
    }
    return null;
  }
}
