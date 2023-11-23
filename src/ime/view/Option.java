package ime.view;

import java.util.List;

import ime.controller.CommandEnum;

public class Option {

  List<AdditionalInput> additionalInputs;
  private String name;
  private boolean previewable;
  private CommandEnum commandEnum;

  public Option(String name, boolean previewable, List<AdditionalInput> additionalInputs, CommandEnum commandEnum) {
    this.name = name;
    this.previewable = previewable;
    this.additionalInputs = additionalInputs;
    this.commandEnum = commandEnum;

  }

  public String getName() {
    return name;
  }

  public boolean isPreviewable() {
    return previewable;
  }


  public List<AdditionalInput> getAdditionalInputs() {

    return additionalInputs;
  }

  public CommandEnum getCommandEnum() {
    return commandEnum;
  }
}

