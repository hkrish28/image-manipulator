package ime.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

  private ButtonGroup radioGroup;

  private JScrollPane mainScrollPane;

  private JLabel mainImage;
  private JLabel histogramImage;
  private JPanel imagePanel;
  private Map<String, JTextField[]> feildmap;

  public Jview() {
    setTitle("Image processing");
    setSize(800, 800);

    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);
    initialiseOption();

    //dialog boxes
    JPanel StartMenuPanel = new JPanel();
    StartMenuPanel.setBorder(BorderFactory.createTitledBorder("Start Menu"));
    StartMenuPanel.setLayout(new BoxLayout(StartMenuPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(StartMenuPanel);

    //file open
    JPanel fileOpenPanel = new JPanel();
    fileOpenPanel.setLayout(new FlowLayout());
    StartMenuPanel.add(fileOpenPanel);
    JButton fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");
    fileOpenButton.addActionListener(this);
    fileOpenPanel.add(fileOpenButton);


    //file save
    JPanel fileSavePanel = new JPanel();
    fileSavePanel.setLayout(new FlowLayout());
    StartMenuPanel.add(fileSavePanel);
    JButton fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand("Save file");
    fileSaveButton.addActionListener(this);
    fileSavePanel.add(fileSaveButton);
    // toggle button
    JPanel togglePanel = new JPanel();
    togglePanel.setLayout(new FlowLayout());
    StartMenuPanel.add(togglePanel);
    JButton toggleButton = new JButton("Toggle");
    toggleButton.setActionCommand("Toggle");
    toggleButton.addActionListener(this);
    togglePanel.add(toggleButton);

// showing an image
    imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    imagePanel.setLayout(new BorderLayout());
    mainPanel.add(imagePanel);
    mainImage = new JLabel();
    JScrollPane imageScrollPane = new JScrollPane(mainImage);
    imageScrollPane.setPreferredSize(new Dimension(256, 450));
    String image = "koala.jpg";
    ImageIcon imageIcon = new ImageIcon(image);
    imageIcon = new ImageIcon(image);
    mainImage.setIcon(imageIcon);
    imagePanel.add(imageScrollPane, BorderLayout.CENTER);

// showing histogram
    JPanel imagePanelHistogram = new JPanel();
    imagePanelHistogram.setBorder(BorderFactory.createTitledBorder("Showing histogram"));
    imagePanelHistogram.setLayout(new BorderLayout());
    mainPanel.add(imagePanelHistogram);
    histogramImage = new JLabel();
    JScrollPane imageScrollPaneHistogram = new JScrollPane(histogramImage);
    imageScrollPaneHistogram.setPreferredSize(new Dimension(256, 256));
    String imageHistogram = "koala.jpg";
    ImageIcon imageIconH = new ImageIcon(imageHistogram);
    histogramImage.setIcon(imageIconH);
    imagePanelHistogram.add(imageScrollPaneHistogram, BorderLayout.CENTER);

    // radio buttons
    JPanel radioPanel = new JPanel();
    radioPanel.setBorder(BorderFactory.createTitledBorder("Filter options "));
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));
    JRadioButton[] radioButtons = new JRadioButton[optionList.size()];
    this.radioGroup = new ButtonGroup();
    for (int i = 0; i < optionList.size(); i++) {
      radioButtons[i] = new JRadioButton(optionList.get(i).getName());
      radioButtons[i].setActionCommand(optionList.get(i).getName());
      radioGroup.add(radioButtons[i]);
      radioPanel.add(radioButtons[i]);
      radioButtons[i].addActionListener(this);
      if (optionList.get(i).isPreviewable()) {
        JButton previewButton = new JButton("Preview");
        previewButton.setActionCommand("Preview");
        int finalI = i;
        previewButton.addActionListener(event ->
                previewImage(previewPercent -> features.previewOperation(optionList.get(finalI).getCommandEnum(), previewPercent)));
        radioPanel.add(previewButton);
      }
    }
    mainPanel.add(radioPanel);

    // apply filter button
    JPanel applyFilterPanel = new JPanel();
    applyFilterPanel.setLayout(new FlowLayout());
    radioPanel.add(applyFilterPanel);
    JButton applyFilterButton = new JButton("Apply filter");
    applyFilterButton.setActionCommand("Apply filter");
    applyFilterButton.addActionListener(this);
    applyFilterPanel.add(applyFilterButton);
  }

  private void previewImage(Consumer<Integer> previewer) {
    previewer.accept(previewAction());
  }

  private void initialiseOption() {
    List<AdditionalInput> additionalInputs = Arrays.asList(new AdditionalInput("b", 0, 0, 255),
            new AdditionalInput("m", 128, 0, 255), new AdditionalInput("w", 255, 0, 255));
    List<AdditionalInput> additional = Arrays.asList(
            new AdditionalInput("compress percent", 0, 0, 100));
    optionList.add(new Option("Visualize Red", false, null, "red visual", CommandEnum.red_component));
    optionList.add(new Option("Visualize Green", false, null, "visualise green", CommandEnum.green_component));
    optionList.add(new Option("Visualize Blue", false, null, "Visualize Blue", CommandEnum.blue_component));
    optionList.add(new Option("Flip Vertically", false, null, "Flip Vertically", CommandEnum.verticalFlip));
    optionList.add(new Option("Flip Horizontally", false, null, "flip", CommandEnum.horizontalFlip));
    optionList.add(new Option("Blur", true, null, "blurs the image", CommandEnum.blur));
    optionList.add(new Option("Sharpen", true, null, "Sharpening image", CommandEnum.sharpen));
    optionList.add(new Option("Convert to Greyscale", true, null, "Converting to Greyscale", CommandEnum.luma_component));
    optionList.add(new Option("Convert to Sepia", true, null, "Converting to Sepia", CommandEnum.sepia));
    optionList.add(new Option("Compression", true, additional, "compressing by compress percent", CommandEnum.compress));
    optionList.add(new Option("Levels Adjust", true, additionalInputs, "b<m<w", CommandEnum.levels_adjust));
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();

    switch (command) {
      case "Open file":
        openFileAction();
        break;
      case "Save file":
        saveFileAction();
        break;
      case "Apply filter":
        applyFilterAction();
        break;
      case "Toggle":
        features.toggle();
        break;
      case "Visualize Red":
        operation = () -> features.visualizeRed();
        break;
      case "Visualize Green":
        operation = () -> features.visualizeGreen();
        break;
      case "Visualize Blue":
        operation = () -> features.visualizeBlue();
        break;
      case "Flip Vertically":
        operation = () -> features.applyVerticalFlip();
        break;
      case "Flip Horizontally":
        operation = () -> features.applyHorizontalFlip();
        break;
      case "Blur":
        operation = () -> features.applyBlur();
        break;
      case "Sharpen":
        operation = () -> features.applySharpen();
        break;
      case "Convert to Greyscale":
        operation = () -> features.applyLumaGreyScale();
        break;
      case "Convert to Sepia":
        operation = () -> features.applySepia();
        break;
      case "Compression":
        askForCompressPercent();
        break;
      case "Color Correct":
        operation = () -> features.applyColorCorrection();
        break;
      case "Levels Adjust":
        bmwValues();
        break;
      default:
        JOptionPane.showMessageDialog(this, "Invalid input.", "Error",
                JOptionPane.ERROR_MESSAGE);
    }
  }

  private void askForCompressPercent() {
    String inputValue = JOptionPane.showInputDialog("Enter compress percent (integer):");
    try {
      int compressPercent = Integer.parseInt(inputValue);
      operation = () -> features.applyCompression(compressPercent);
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error",
              JOptionPane.ERROR_MESSAGE);
      askForCompressPercent();
    }
  }

  private void bmwValues() {
    String inputB = JOptionPane.showInputDialog("Enter b value (integer):");
    String inputM = JOptionPane.showInputDialog("Enter m value (integer):");
    String inputW = JOptionPane.showInputDialog("Enter w value (integer):");
    try {
      int b = Integer.parseInt(inputB);
      int m = Integer.parseInt(inputM);
      int w = Integer.parseInt(inputW);
      operation = () -> features.applyLevelsAdjust(b, m, w);
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error",
              JOptionPane.ERROR_MESSAGE);
    }

  }


  private void saveFileAction() {
    final JFileChooser fileChooser = new JFileChooser(".");
    int retValue = fileChooser.showSaveDialog(Jview.this);
    if (retValue == JFileChooser.APPROVE_OPTION) {
      File f = fileChooser.getSelectedFile();
      features.saveImage(f.getAbsolutePath());
    }
  }

  private void openFileAction() {
    final JFileChooser fileChooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, GIF, PNG, PPM Images", "jpg", "gif","png","ppm");
    fileChooser.setFileFilter(filter);
    int retValue = fileChooser.showOpenDialog(Jview.this);
    if (retValue == JFileChooser.APPROVE_OPTION) {
      File f = fileChooser.getSelectedFile();
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
  }


  @Override
  public void setFeatures(Features features) {
    this.features = features;
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
}
