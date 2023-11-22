package ime.view;

import java.util.List;

public class Option {

  List<AdditionalInput> additionalInputs;
  private String name;
  private boolean previewable;
  private String comments;

  public Option(String name, boolean previewable, List<AdditionalInput> additionalInputs, String comments) {
    this.name = name;
    this.previewable = previewable;
    this.additionalInputs = additionalInputs;
    this.comments = comments;

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

  public String getComments() {
    return comments;
  }
}

//new Option("horizontal flip",false,);
