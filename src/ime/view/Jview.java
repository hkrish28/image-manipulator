package ime.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Jview extends JFrame implements ActionListener {

  private JPanel mainPanel;
  private JTextField blurIntensityField;
  private JLabel radioDisplay;
  private ButtonGroup rGroup;
  private JLabel fileOpenDisplay;
  private JLabel applyfilterDisplay;
  private JLabel fileSaveDisplay;
  private JScrollPane mainScrollPane;

  public Jview() {
    setTitle("Image processing");
    setSize(400, 400);

    mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    //scroll bars around this main panel
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    //dialog boxes
    JPanel dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Dialog boxes"));
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(dialogBoxesPanel);

    //file open
    JPanel fileopenPanel = new JPanel();
    fileopenPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileopenPanel);
    JButton fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");
    fileOpenButton.addActionListener(this);
    fileopenPanel.add(fileOpenButton);

    //file save
    JPanel filesavePanel = new JPanel();
    filesavePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(filesavePanel);
    JButton fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand("Save file");
    fileSaveButton.addActionListener(this);
    filesavePanel.add(fileSaveButton);
    //apply filter button
    JPanel applyfilterPanel = new JPanel();
    applyfilterPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(applyfilterPanel);
    JButton applyfilterButton = new JButton("apply filter");
    applyfilterButton.setActionCommand("apply filter");
    applyfilterButton.addActionListener(this);
    applyfilterPanel.add(applyfilterButton);

// showing an image
    JPanel imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    imagePanel.setLayout(new BorderLayout());
    mainPanel.add(imagePanel);
    JLabel imageLabel = new JLabel();
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageScrollPane.setPreferredSize(new Dimension(256, 256));
    String image = "koala.jpg";
    ImageIcon imageIcon = new ImageIcon(image);
    imageLabel.setIcon(imageIcon);
    imagePanel.add(imageScrollPane, BorderLayout.CENTER);

// showing histogram
    JPanel imagePanelHistogram = new JPanel();
    //a border around the panel with a caption
    imagePanelHistogram.setBorder(BorderFactory.createTitledBorder("Showing histogram"));
    imagePanelHistogram.setLayout(new BorderLayout());
    mainPanel.add(imagePanelHistogram);
    JLabel imageLabelHistogram = new JLabel();
    JScrollPane imageScrollPaneHistogram = new JScrollPane(imageLabelHistogram);
    imageScrollPaneHistogram.setPreferredSize(new Dimension(256, 256));
    String imageHistogram = "koala.jpg";
    ImageIcon imageIconH = new ImageIcon(imageHistogram);
    imageLabelHistogram.setIcon(imageIconH);
    imagePanelHistogram.add(imageScrollPaneHistogram, BorderLayout.CENTER);
    // radio buttons
    JPanel radioPanel = new JPanel();
    radioPanel.setBorder(BorderFactory.createTitledBorder("Filter options "));
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));


    String[] radioOptions = {"Visualize Red", "Visualize Green", "Visualize Blue",
        "Flip Vertically", "Flip Horizontally", "Blur", "Sharpen", "Convert to Greyscale",
        "Convert to Sepia", "Compression", "Color Correct", "Levels Adjust"};

    JRadioButton[] radioButtons = new JRadioButton[radioOptions.length];
    ButtonGroup rGroup = new ButtonGroup();

    for (int i = 0; i < radioOptions.length; i++) {
      radioButtons[i] = new JRadioButton(radioOptions[i]);
      radioButtons[i].setActionCommand(radioOptions[i]);
      rGroup.add(radioButtons[i]);
      radioPanel.add(radioButtons[i]);

      // Add a preview button for each option
      JButton previewButton = new JButton("Preview");
      previewButton.setActionCommand("Preview");
      previewButton.addActionListener(this);
      radioPanel.add(previewButton);
      radioButtons[i].addActionListener(this);


    }
    //radioButtons[5].doClick();
    radioDisplay = new JLabel("choose an option");
    radioPanel.add(radioDisplay);
    mainPanel.add(radioPanel);

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
      case "apply filter":
        // Implement apply filter action
        applyFilterAction();
        break;
      case "Visualize Red":
        radioDisplay.setText("visualise red 1 was selected");
        break;
      case "Visualize Green":
        radioDisplay.setText("visualise green 1 was selected");
        break;
      case "Visualize Blue":
        radioDisplay.setText("visualise blue 1 was selected");
        break;
      case "Flip Vertically":
        radioDisplay.setText("Flip Vertically was selected");
        break;
      case "Flip Horizontally":
        radioDisplay.setText("Flip Horizontally was selected");
        break;
      case "Blur":
        radioDisplay.setText("Blur was selected");
        break;
      // radioDisplay.setText("Blur was selected");
      case "Sharpen":
        radioDisplay.setText("Sharpen was selected");
        break;
      case "Convert to Greyscale":
        radioDisplay.setText("Convert to Greyscale was selected");
        break;
      case "Convert to Sepia":
        radioDisplay.setText("Convert to Sepia was selected");
        break;
      case "Compression":
        askForCompressprecent();
        radioDisplay.setText("Compression was selected");
        break;
      case "Color Correct":
        radioDisplay.setText("Color Correct was selected");
        break;
      case "Levels Adjust":
        bmwValues();
        radioDisplay.setText("Levels Adjust was selected");
        break;
      case "Preview":
        previewAction(getSelectedRadioButtonText());
        break;
    }
  }

  private void askForCompressprecent() {
    String inputValue = JOptionPane.showInputDialog("Enter compress percent (integer):");
    try {
      int blurIntensity = Integer.parseInt(inputValue);
      System.out.println("compress percent entered: " + blurIntensity);
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }
  private void bmwValues(){
    String inputValueb = JOptionPane.showInputDialog("Enter b value (integer):");
    String inputValuem = JOptionPane.showInputDialog("Enter m value (integer):");
    String inputValuew = JOptionPane.showInputDialog("Enter w value (integer):");
    try {
      int b = Integer.parseInt(inputValueb);
      int m =  Integer.parseInt(inputValuem);
      int w =  Integer.parseInt(inputValuew);
      System.out.println("b value entered: " + b+"m value entered: "+m+"w value entered: "+ w );

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
      fileSaveDisplay.setText(f.getAbsolutePath());
    }
  }

  private void openFileAction() {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(Jview.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      fileOpenDisplay.setText(f.getAbsolutePath());
    }
  }

  private void applyFilterAction() {

  }

  private void previewAction(String selectedOption) {
    String inputValue = JOptionPane.showInputDialog("Enter preview percent (integer):");
    try {
      int previewPercent = Integer.parseInt(inputValue);
      System.out.println("compress percent entered: " + previewPercent);
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error",
          JOptionPane.ERROR_MESSAGE);
    }
    JOptionPane.showMessageDialog(this, "Previewing: " + selectedOption);
  }

  private String getSelectedRadioButtonText() {
    ButtonModel selectedButton = rGroup.getSelection();
    if (selectedButton != null) {
      return selectedButton.getActionCommand();
    }
    return null;
  }
}
