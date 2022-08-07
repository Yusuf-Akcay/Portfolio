package de.yusuf.controller.bmiCalculator;

import de.yusuf.controller.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

import java.math.BigDecimal;
import java.math.RoundingMode;
@Controller("bodyMassIndexCalculatorController")
@RequestScope
public class BodyMassIndexCalculatorController extends AbstractController {
  private Gender gender = Gender.MALE;
  private int weight = 50;
  private int height = 100;
  private double calculatedBmi;
  private String bmiName;

  public Gender[] getGenders(){
    return Gender.values();
  }

  @Override public void init() {}

  public void calculate(){
    calculatedBmi = scale( weight / ( ( height / 100.0 ) * ( height / 100.0 ) ) );
    bmiName = switch ( gender ){
      case MALE -> (calculatedBmi < 20.0 ? "Untergewicht" :
                    calculatedBmi >= 20.0 && calculatedBmi < 25.0 ? "Normalgewicht" :
                    calculatedBmi >= 25.0 && calculatedBmi < 30.0 ? "Übergewicht" :
                    calculatedBmi >= 30.0 && calculatedBmi < 35.0 ? "Fettleibig" :
                    calculatedBmi >= 35.0 && calculatedBmi < 40.0 ? "Stark Fettleibig" : "Sehr stark Fettleibig");
      case FEMALE -> (calculatedBmi < 19.0 ? "Untergewicht" :
                     calculatedBmi >= 19.0 && calculatedBmi < 24.0 ? "Normalgewicht" :
                     calculatedBmi >= 24.0 && calculatedBmi < 30.0 ? "Übergewicht" :
                     calculatedBmi >= 30.0 && calculatedBmi < 35.0 ? "Fettleibig" :
                     calculatedBmi >= 35.0 && calculatedBmi < 40.0 ? "Stark Fettleibig" : "Sehr stark Fettleibig");
    };
  }

  private double scale(double index){
    return BigDecimal.valueOf( index ).setScale( 2, RoundingMode.HALF_UP).doubleValue();
  }

  public enum Gender{
    MALE("Männer"), FEMALE("Frauen");
    private final String label;
    Gender(String label ){
      this.label = label;
    }
    public String getLabel() { return label; }
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(
      Gender gender ) {
    this.gender = gender;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight( int weight ) {
    this.weight = weight;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight( int height ) {
    this.height = height;
  }

  public double getCalculatedBmi() {
    return calculatedBmi;
  }

  public void setCalculatedBmi( double calculatedBmi ) {
    this.calculatedBmi = calculatedBmi;
  }

  public String getBmiName() {
    return bmiName;
  }

  public void setBmiName( String bmiName ) {
    this.bmiName = bmiName;
  }
}
