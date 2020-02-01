/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.util.Color;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorSensorV3;

import com.revrobotics.ColorMatch;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ColorWheelSubsystem extends SubsystemBase {
  /**
   * Creates a new ColorWheelSubsystem.
   */
  private final WPI_TalonSRX colorSpinner = new WPI_TalonSRX(Constants.colorMotor);
  private final ColorSensorV3 cV3 = new ColorSensorV3(Port.kOnboard);
  private final ColorMatch m_colorMatcher = new ColorMatch();

  private final Color kBlueTarget = ColorMatch.makeColor(0.121, 0.430, 0.447);
  private final Color kGreenTarget = ColorMatch.makeColor(0.165, 0.587, 0.249);
  private final Color kRedTarget = ColorMatch.makeColor(0.520, 0.356, 0.125);
  private final Color kYellowTarget = ColorMatch.makeColor(0.320, 0.563, 0.114);

  
  private String gameData = DriverStation.getInstance().getGameSpecificMessage();

  int rotationCount = 0;

  Color sensorColor;
  Color debouncedColor;
  int colorCounter;
  
  public ColorWheelSubsystem() {
    
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);



  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    gameData = DriverStation.getInstance().getGameSpecificMessage();

    // This line through the next 11 lines are for color sensor debounce
    Color currentColor = cV3.getColor();
    if (currentColor.equals(sensorColor) & sensorColor != null){ // Increments the counter if the color is the same as it was last scheduler run
      colorCounter++;
    } else { // If the color has not been detected again, reset the color counter and set the new detected color
      colorCounter = 0;
     sensorColor = currentColor;
    }
    if (colorCounter > Constants.debounceCount) { // Confirms that the same color has been detected a certain number of times
      colorCounter = 0; 
      debouncedColor = currentColor;
    }
  }

  public Color matchColor(){
    m_colorMatcher.setConfidenceThreshold(0.01);
    Color match = m_colorMatcher.matchClosestColor(debouncedColor).color;
    return match; // returns color the sensor is on
  }

  public String colorToString(Color input) {
    String string1 = input.toString();
    Character char1 = string1.charAt(1);
    String finalString = char1.toString();
    return finalString; //returns a one character string for what color the sensor is on (B, G, R, or Y)
  }

  public boolean onColor(){  //returns true if the sensor is on the color that we are given during the match
    if (gameData != null) {
      return false;
    } else if (gameData.equals(colorToString(matchColor()))) {
        return true;
    } else {
      return false;
    }

  }

  public int numRotaions(){ //returns the amount of times the sensor sees the first color
    
    String currentColor = colorToString(matchColor());
    String startColor = colorToString(debouncedColor);

    if(currentColor.equals(startColor) && startColor != null){
      rotationCount++; //increases rotationCount everytime the sensor sees the first color
      return rotationCount;
    } else {
      rotationCount = 0;
      return rotationCount;
    }
     
  }
}
