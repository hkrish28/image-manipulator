package ime;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The `controller` class implements the ImageProcessingController interface and provides
 * functionality to execute image processing commands specified in a script file.
 */

public class controller implements ImageProcessingController {

  /**
   * map for storing the image with its name as the key.
   */
  private Map<String, Image> imageFilesMap;

  /**
   * Constructs a new controller instance with an empty image files map.
   */
  public controller() {

    this.imageFilesMap = new HashMap<>();
  }

  /**
   * Executes image processing commands specified in a script file.
   *
   * @param scriptFileName The path to the script file containing image processing commands.
   */
  @Override
  public void executeScript(String scriptFileName) {
    try (BufferedReader reader = new BufferedReader(new FileReader(scriptFileName))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] tokens = line.split(" ");
        if (tokens.length >= 2) {
          String commandStr = tokens[0];
          command command = getCommandEnum(commandStr);
          if (command != null) {
            switch (command) {
              case load:
                processLoad(tokens);
                break;
              case save:
                processSave(tokens);
                break;
              case brighten:
                processB(tokens);
                break;
              case horizontalFlip:
                processHorizontalFlip(tokens);
                break;
              case verticalFlip:
                processVerticalFlip(tokens);
                break;
              case rgb_split:
                processSplit(tokens);
                break;
              case rgb_combine:
                processCombine(tokens);
                break;
              case value_component:
                processGrey(tokens);
                break;
            }
          }

        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void processLoad(String[] tokens) {
    Image toLoad = new ImageImpl();
    toLoad.loadImage(tokens[1]);
    imageFilesMap.put(tokens[2], toLoad);
  }

  private void processSave(String[] tokens) {
    String imageNameToSave = tokens[2];
    Image fileToSave = imageFilesMap.get(tokens[1]);
    if (fileToSave != null) {
      fileToSave.saveImage(imageNameToSave);
    } else {
      throw new IllegalArgumentException("image not found " + fileToSave);
    }
  }

  private void processB(String[] tokens) {
    float brightnessConstant = Float.parseFloat(tokens[1]);
    Image fileToBrighten = imageFilesMap.get(tokens[2]);
    fileToBrighten.brighten(brightnessConstant);
    String newName = tokens[3];
    imageFilesMap.put(newName, fileToBrighten);

  }

  private void processHorizontalFlip(String[] tokens) {
    Image fileToFlip = imageFilesMap.get(tokens[1]);
    fileToFlip.flipHorizontally();
    imageFilesMap.put(tokens[2], fileToFlip);
  }

  private void processVerticalFlip(String[] tokens) {
    Image fileToFlip = imageFilesMap.get(tokens[1]);
    fileToFlip.flipVertically();
    imageFilesMap.put(tokens[2], fileToFlip);
  }

  private void processSplit(String[] tokens) {
    Image fileToSplit = imageFilesMap.get(tokens[1]);
    List<Image> colorChannel = fileToSplit.splitIntoColorChannels();
    imageFilesMap.put(tokens[2], colorChannel.get(0));
    imageFilesMap.put(tokens[3], colorChannel.get(1));
    imageFilesMap.put(tokens[4], colorChannel.get(2));
  }

  private void processCombine(String[] tokens) {
    Image red = imageFilesMap.get(tokens[2]);
    List<Image> gb = new ArrayList<>();
    gb.add(imageFilesMap.get(tokens[3]));
    gb.add(imageFilesMap.get(tokens[4]));
    red.combine(gb);
    imageFilesMap.put(tokens[1], red);
  }

  private void processGrey(String[] tokens) {
    Image fileToFilter = imageFilesMap.get(tokens[1]);
    fileToFilter.greyScale();
    imageFilesMap.put(tokens[2], fileToFilter);
  }

  private command getCommandEnum(String commandStr) {
    for (command cmd : command.values()) {
      if (cmd.getRepresentation().equals(commandStr)) {
        return cmd;
      }
    }
    return null; // Command not found
  }
}


