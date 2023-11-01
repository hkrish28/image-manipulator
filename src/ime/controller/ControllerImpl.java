package ime.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import ime.model.ImageRepository;
import ime.view.View;

/**
 * The `controller` class implements the ImageProcessingController interface and provides
 * functionality to execute image processing commands specified in a script file.
 */

public class ControllerImpl implements ImageProcessingController {

  protected ImageRepository imgRepo;

  protected View view;

  Scanner in;

  /**
   * Constructs a new controller instance with an empty image files map.
   */
  public ControllerImpl(Scanner in, View view, ImageRepository imgRepo) {
    this.imgRepo = imgRepo;
    this.in = in;
    this.view = view;
  }

  protected boolean executeCommand(String command) {
    String[] tokens = command.split(" ");

    boolean end = false;
    CommandEnum commandKeyword = getCommandEnum(tokens[0]);
    if (commandKeyword != null) {
      try {
        switch (commandKeyword) {
          case load:
            processLoad(tokens);
            break;
          case save:
            processSave(tokens);
            break;
          case brighten:
            processBrighten(tokens);
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
            processValueGreyScale(tokens);
            break;
          case red_component:
            processRedChannel(tokens);
            break;
          case green_component:
            processGreenChannel(tokens);
            break;
          case blue_component:
            processBlueChannel(tokens);
            break;
          case luma_component:
            processLumaGreyScale(tokens);
            break;
          case intensity_component:
            processIntensityGreyScale(tokens);
            break;
          case blur:
            processBlur(tokens);
            break;
          case sharpen:
            processSharpen(tokens);
            break;
          case sepia:
            processSepia(tokens);
            break;
          case run:
            processScriptRun(tokens);
            break;
          case exit:
            end = true;
            break;
          default:
            throw new IllegalArgumentException("Invalid command provided");
        }
      } catch (IllegalArgumentException e) {
        view.displayMessage(e.getMessage());
      }
    }

    return end;
  }

  private void validateTokenCount(int expectedTokens, int tokenCount) {
    if (expectedTokens != tokenCount) {
      throw new IllegalArgumentException("Invalid number of tokens passed for the given command");
    }
  }

  private void processLoad(String[] tokens) {
    try {
      validateTokenCount(3, tokens.length);
      String path = tokens[1];
      String imageName = tokens[2];
      imgRepo.loadImage(path, imageName);
      view.displayMessage("Loaded successfully.");
    } catch (IOException e) {
      view.displayMessage(e.getMessage());
    }
  }

  private void processSave(String[] tokens) {
    try {
      validateTokenCount(3, tokens.length);
      String imageName = tokens[2];
      String fileName = tokens[1];
      this.imgRepo.saveImage(fileName, imageName);
      view.displayMessage("Saved successfully.");
    } catch (IOException e) {
      view.displayMessage(e.getMessage());
    }
  }

  private void processBrighten(String[] tokens) {
    try {
      validateTokenCount(4, tokens.length);
      float brightnessConstant = Float.parseFloat(tokens[1]);
      String imageName = tokens[2];
      String newImage = tokens[3];
      imgRepo.brightenImage(imageName, newImage, brightnessConstant);
      view.displayMessage("Brightened successfully.");
    } catch (NumberFormatException e) {
      view.displayMessage("brightness command expects a number following the command");
    } catch (IllegalArgumentException e) {
      view.displayMessage(e.getMessage());
    }
  }

  private void processScriptRun(String[] tokens) {
    try {
      validateTokenCount(2, tokens.length);
      processScriptFile(tokens[1]);
      view.displayMessage("Script file execution complete.");
    } catch (IllegalArgumentException e) {
      view.displayMessage(e.getMessage());
    }
  }

  private void processHorizontalFlip(String[] tokens) {
    try {
      validateTokenCount(3, tokens.length);
      String imageName = tokens[1];
      String newImage = tokens[2];
      imgRepo.flipImageHorizontally(imageName, newImage);
      messageSenderHelper(tokens[0], imageName,newImage);
    } catch (IllegalArgumentException e) {
      view.displayMessage(e.getMessage());
    }
  }

  private void processVerticalFlip(String[] tokens) {
    try {
      validateTokenCount(3, tokens.length);
      String imageName = tokens[1];
      String newImage = tokens[2];
      imgRepo.flipImageVertically(imageName, newImage);
      messageSenderHelper(tokens[0], imageName,newImage);
    } catch (IllegalArgumentException e) {
      view.displayMessage(e.getMessage());
    }
  }

  private void processSplit(String[] tokens) {
    try {
      validateTokenCount(5, tokens.length);

      String srcImage = tokens[1];
      List<String> colorChannelsImages = Arrays.asList(tokens).subList(2, tokens.length);
      imgRepo.splitImageIntoColorChannels(srcImage, colorChannelsImages);
      messageSenderHelper(tokens[0], srcImage,colorChannelsImages.toString());
    } catch (IllegalArgumentException e) {
      view.displayMessage(e.getMessage());
    }
  }

  private void processCombine(String[] tokens) {
    try {
      validateTokenCount(5, tokens.length);

      String destImage = tokens[1];
      List<String> colorChannelsImages = Arrays.asList(tokens).subList(2, tokens.length);
      imgRepo.combineImages(colorChannelsImages, destImage);
      messageSenderHelper(tokens[0], colorChannelsImages.toString(),destImage);
    } catch (IllegalArgumentException e) {
      view.displayMessage(e.getMessage());
    }
  }

  private void processRedChannel(String[] tokens) {
    try {
      validateTokenCount(3, tokens.length);
      String srcImage = tokens[1];
      String destImage = tokens[2];
      imgRepo.toRedChannelImage(srcImage, destImage);
      messageSenderHelper(tokens[0], srcImage,destImage);
    } catch (IllegalArgumentException e) {
      view.displayMessage(e.getMessage());
    }
  }

  private void processGreenChannel(String[] tokens) {
    try {
      validateTokenCount(3, tokens.length);
      String srcImage = tokens[1];
      String destImage = tokens[2];
      imgRepo.toGreenChannelImage(srcImage, destImage);
      messageSenderHelper(tokens[0], srcImage,destImage);
    } catch (IllegalArgumentException e) {
      view.displayMessage(e.getMessage());
    }
  }

  private void processBlueChannel(String[] tokens) {
    try {
      validateTokenCount(3, tokens.length);
      String srcImage = tokens[1];
      String destImage = tokens[2];
      imgRepo.toBlueChannelImage(srcImage, destImage);
      messageSenderHelper(tokens[0], srcImage,destImage);
    } catch (IllegalArgumentException e) {
      view.displayMessage(e.getMessage());
    }
  }

  private void processValueGreyScale(String[] tokens) {
    try {
      validateTokenCount(3, tokens.length);
      String imageName = tokens[1];
      String newImage = tokens[2];
      imgRepo.toValueGreyScale(imageName, newImage);
      messageSenderHelper(tokens[0], imageName,newImage);
    } catch (IllegalArgumentException e) {
      view.displayMessage(e.getMessage());
    }
  }

  private void processLumaGreyScale(String[] tokens) {
    try {
      validateTokenCount(3, tokens.length);
      String imageName = tokens[1];
      String newImage = tokens[2];
      imgRepo.toLumaGreyScale(imageName, newImage);
      messageSenderHelper(tokens[0], imageName,newImage);
    } catch (IllegalArgumentException e) {
      view.displayMessage(e.getMessage());
    }
  }

  private void processIntensityGreyScale(String[] tokens) {
    try {
      validateTokenCount(3, tokens.length);
      String imageName = tokens[1];
      String newImage = tokens[2];
      imgRepo.toIntensityGreyScale(imageName, newImage);
      messageSenderHelper(tokens[0], imageName,newImage);
    } catch (IllegalArgumentException e) {
      view.displayMessage(e.getMessage());
    }
  }

  private void processBlur(String[] tokens) {
    try {
      validateTokenCount(3, tokens.length);
      String imageName = tokens[1];
      String newImage = tokens[2];
      imgRepo.blurImage(imageName, newImage);
      messageSenderHelper(tokens[0], imageName,newImage);
    } catch (IllegalArgumentException e) {
      view.displayMessage(e.getMessage());
    }
  }

  private void processSharpen(String[] tokens) {
    try {
      validateTokenCount(3, tokens.length);
      String imageName = tokens[1];
      String newImage = tokens[2];
      imgRepo.sharpenImage(imageName, newImage);
      messageSenderHelper(tokens[0], imageName,newImage);
    } catch (IllegalArgumentException e) {
      view.displayMessage(e.getMessage());
    }
  }

  private void processSepia(String[] tokens) {
    try {
      validateTokenCount(3, tokens.length);
      String imageName = tokens[1];
      String newImage = tokens[2];
      imgRepo.toSepiaImage(imageName, newImage);
      messageSenderHelper(tokens[0], imageName,newImage);
    } catch (IllegalArgumentException e) {
      view.displayMessage(e.getMessage());
    }
  }

  private CommandEnum getCommandEnum(String commandStr) {
    for (CommandEnum cmd : CommandEnum.values()) {
      if (cmd.getRepresentation().equals(commandStr)) {
        return cmd;
      }
    }
    return null; // Command not found
  }

  @Override
  public void execute() {
    boolean endFlag = false;
    while (!endFlag) {
      view.displayMessage("Please enter the command to run: ");
      String command = in.nextLine();
      endFlag = executeCommand(command);
    }
  }

  private void processScriptFile(String scriptFileName) {
    try (BufferedReader reader = new BufferedReader(new FileReader(scriptFileName))) {
      String line;
      while ((line = reader.readLine()) != null) {
        // Trim leading and trailing whitespaces
        line = line.trim();
        // Ignore lines that start with #
        if (!(line.startsWith("#") || line.isEmpty())) {
          // Call the controller's executeScript method with the current line
          executeCommand(line);
        }
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("Invalid script location/file.");
    }
  }

  private void messageSenderHelper(String operation, String src, String dest) {
    view.displayMessage(operation + " operation completed successfully for " + src
            + " & put in " + dest);
  }
}


