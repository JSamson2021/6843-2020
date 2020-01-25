/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.util.Color;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;


import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorWheelSubsystem extends SubsystemBase {
  /**
   * Creates a new ColorWheelSubsystem.
   */
  private final WPI_TalonSRX colorSpinner = new WPI_TalonSRX(12);
  private final ColorSensorV3 cV3 = new ColorSensorV3(Port.kOnboard);
  private final ColorMatch m_colorMatcher = new ColorMatch();

  private final Color kBlueTarget = ColorMatch.makeColor(0.121, 0.430, 0.447);
  private final Color kGreenTarget = ColorMatch.makeColor(0.165, 0.587, 0.249);
  private final Color kRedTarget = ColorMatch.makeColor(0.520, 0.356, 0.125);
  private final Color kYellowTarget = ColorMatch.makeColor(0.320, 0.563, 0.114);

  
  
  public ColorWheelSubsystem() {
    
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);



  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public ColorMatchResult detectColor(){
    Color sensorValue = cV3.getColor();
    m_colorMatcher.setConfidenceThreshold(0.01);
    ColorMatchResult match = m_colorMatcher.matchClosestColor(sensorValue);
    return match;
  }
}
