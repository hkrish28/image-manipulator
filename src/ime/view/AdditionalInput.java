package ime.view;

public class AdditionalInput {

  private String name;
  private int value;

  private int minValue;

  private int maxValue;

  public AdditionalInput(String name, int value, int minVal, int maxVal){
    this.name=name;
    this.value=value;
    this.minValue=minVal;
    this.maxValue=maxVal;

  }
  public void setValue(int value) throws IllegalArgumentException{
    if (value<minValue || value>maxValue){
      throw new IllegalArgumentException("not in range");
    }
    this.value = value;
  }

  public int getValue(){
    return value;
  }

  public int getMinValue(){
    return minValue;
  }

  public int  getMaxValue(){
    return  maxValue;
  }

  public String getName() {
    return name;
  }
}
