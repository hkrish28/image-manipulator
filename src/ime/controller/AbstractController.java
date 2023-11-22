package ime.controller;

import java.util.HashMap;
import java.util.Map;

import ime.controller.commands.BlueComponent;
import ime.controller.commands.Blur;
import ime.controller.commands.Brighten;
import ime.controller.commands.ColorCorrect;
import ime.controller.commands.Combine;
import ime.controller.commands.Command;
import ime.controller.commands.Compress;
import ime.controller.commands.GreenComponent;
import ime.controller.commands.Histogram;
import ime.controller.commands.HorizontalFlip;
import ime.controller.commands.IntensityGreyscale;
import ime.controller.commands.LevelsAdjust;
import ime.controller.commands.Load;
import ime.controller.commands.LumaGreyscale;
import ime.controller.commands.RedComponent;
import ime.controller.commands.RgbSplit;
import ime.controller.commands.Save;
import ime.controller.commands.Sepia;
import ime.controller.commands.Sharpen;
import ime.controller.commands.ValueGreyscale;
import ime.controller.commands.VerticaFlip;
import ime.model.ImageRepository;

public abstract class AbstractController implements ImageProcessingController{

  private final FileHandlerProvider fileHandlerProvider;
  private final ImageRepository imgRepo;
  protected Map<CommandEnum, Command> knownCommands;

  public AbstractController(FileHandlerProvider fileHandlerProvider, ImageRepository imageRepository) {
    this.fileHandlerProvider = fileHandlerProvider;
    this.imgRepo = imageRepository;
    initializeKnownCommands();
  }

  private void initializeKnownCommands() {
    knownCommands = new HashMap<>();
    knownCommands.put(CommandEnum.blur, new Blur());
    knownCommands.put(CommandEnum.sharpen, new Sharpen());
    knownCommands.put(CommandEnum.brighten, new Brighten());
    knownCommands.put(CommandEnum.load, new Load(fileHandlerProvider));
    knownCommands.put(CommandEnum.save, new Save(fileHandlerProvider));
    knownCommands.put(CommandEnum.horizontalFlip, new HorizontalFlip());
    knownCommands.put(CommandEnum.verticalFlip, new VerticaFlip());
    knownCommands.put(CommandEnum.rgb_combine, new Combine());
    knownCommands.put(CommandEnum.rgb_split, new RgbSplit());
    knownCommands.put(CommandEnum.value_component, new ValueGreyscale());
    knownCommands.put(CommandEnum.luma_component, new LumaGreyscale());
    knownCommands.put(CommandEnum.intensity_component, new IntensityGreyscale());
    knownCommands.put(CommandEnum.sepia, new Sepia());
    knownCommands.put(CommandEnum.red_component, new RedComponent());
    knownCommands.put(CommandEnum.green_component, new GreenComponent());
    knownCommands.put(CommandEnum.blue_component, new BlueComponent());
//    knownCommands.put(CommandEnum.run, new Run(view, fileHandlerProvider));
    knownCommands.put(CommandEnum.compress, new Compress());
    knownCommands.put(CommandEnum.histogram, new Histogram());
    knownCommands.put(CommandEnum.color_correct, new ColorCorrect());
    knownCommands.put(CommandEnum.levels_adjust, new LevelsAdjust());
  }

  protected boolean executeCommand(String commandTokens) {
    commandTokens = commandTokens.trim();
    if (commandTokens.startsWith("#") || commandTokens.isEmpty()) {
      return false;
    }

    String[] tokens = commandTokens.split(" ");
    try {
      CommandEnum commandKeyword = getCommandEnum(tokens[0]);
      if (commandKeyword == CommandEnum.exit) {
        return true;
      }

      Command commandObject = knownCommands.get(commandKeyword);
      String returnedMessage = commandObject.proceed(tokens, imgRepo);
//        view.displayMessage(returnedMessage);
    } catch (IllegalArgumentException e) {
//        view.displayMessage(e.getMessage());
    }
    return false;
  }

  private static CommandEnum getCommandEnum(String commandStr) throws IllegalArgumentException {
    for (CommandEnum cmd : CommandEnum.values()) {
      if (cmd.getRepresentation().equals(commandStr)) {
        return cmd;
      }
    }
    throw new IllegalArgumentException("Command not found"); // Command not found
  }
}

