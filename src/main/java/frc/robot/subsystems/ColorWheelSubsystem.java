/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ColorWheelSubsystem extends SubsystemBase {
  /**
   * Creates a new ColorWheelSubsystem.
   */
  private final WPI_VictorSPX colorSpinner = new WPI_VictorSPX(Constants.colorMotor);
  private final ColorSensorV3 cV3 = new ColorSensorV3(Port.kOnboard);
  private final ColorMatch m_colorMatcher = new ColorMatch();

  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  private String gameData = DriverStation.getInstance().getGameSpecificMessage();

  // public int rotationCount = 0;


  Color sensorColor;
  public Color debouncedColor;
  int colorCounter;

  public String colorString;

  ShuffleboardTab testTab = Shuffleboard.getTab(Constants.testTab);

  int rotationIncrease;

  ColorMatchResult match;

  private NetworkTableEntry rotationEntry = Shuffleboard.getTab(Constants.testTab).add("NumRotations", numRotations(rotationIncrease)).getEntry();
  private NetworkTableEntry redEntry = Shuffleboard.getTab(Constants.testTab).add("Red", debouncedColor.red).getEntry();
  private NetworkTableEntry greenEntry = Shuffleboard.getTab(Constants.testTab).add("Green", debouncedColor.green).getEntry();
  private NetworkTableEntry blueEntry = Shuffleboard.getTab(Constants.testTab).add("Blue", debouncedColor.blue).getEntry();
  private NetworkTableEntry confidenceEntry = Shuffleboard.getTab(Constants.testTab).add("Confidence", match.confidence).getEntry();
  private NetworkTableEntry detectedEntry = Shuffleboard.getTab(Constants.testTab).add("Detected Color", colorString).getEntry();
  
  public ColorWheelSubsystem() {

    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);

    colorSpinner.setNeutralMode(NeutralMode.Brake);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    gameData = DriverStation.getInstance().getGameSpecificMessage();

    // This line through the next 11 lines are for color sensor debounce
    Color currentColor = cV3.getColor();
    if (currentColor.equals(sensorColor) & sensorColor != null) {
      // Increments the counter if the color is the same as it was last scheduler run
      colorCounter++;
    } else { // If the color is not the same, reset the color counter and use the new color
      colorCounter = 0;
      sensorColor = currentColor;
    }
    if (colorCounter > Constants.debounceCount) { // Confirms that the same color has been seen a given number of times
      colorCounter = 0;
      debouncedColor = currentColor;
    }

    if (debouncedColor != null) {
      // Below here turns the color into a string with the correct name (Hopefully!)
      ColorMatchResult match = m_colorMatcher.matchClosestColor(debouncedColor);

      if (match.color == kBlueTarget) {
        colorString = "Blue";
      } else if (match.color == kRedTarget) {
        colorString = "Red";
      } else if (match.color == kGreenTarget) {
        colorString = "Green";
      } else if (match.color == kYellowTarget) {
        colorString = "Yellow";
      } else {
        colorString = "Unknown";
      } // End "Below here"
    }

    rotationEntry.setValue(numRotations(rotationIncrease));
    redEntry.setDouble(debouncedColor.red);
    greenEntry.setDouble(debouncedColor.green);
    blueEntry.setDouble(debouncedColor.red);
    confidenceEntry.setDouble(match.confidence);
    detectedEntry.setString(colorString); 

  }

  public String firstCharString(String initialString) {
    Character char1 = initialString.charAt(1);
    String finalString = char1.toString();
    return finalString;
  }

  public boolean onColor() { // Returns true if the sensor is on the color that we are given during the match
    if (gameData != null) {
      return false;
    } else if (gameData.equals(firstCharString(colorString))) {
      return true;
    } else {
      return false;
    }

  }

  public int numRotations(int rotationIncrease){ // takes in the amount of times the sensor sees the first color and returns it's value 
    int rotationNum = rotationIncrease; 
    return rotationNum;
     
  }
 

  public void spinColorWheel(double power) {
    colorSpinner.set(power);
  }

  public void stopSpinning() {
    colorSpinner.set(0.0);
    colorSpinner.stopMotor();
  }
}
