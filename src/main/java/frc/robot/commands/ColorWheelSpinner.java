/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorWheelSubsystem;

public class ColorWheelSpinner extends CommandBase {
  /**
   * Creates a new ColorWheelSpinner.
   */
  private final ColorWheelSubsystem m_colorWheelSubsystem;
  private static String m_startColor;

  public ColorWheelSpinner(ColorWheelSubsystem colorWheelSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_colorWheelSubsystem = colorWheelSubsystem;
    addRequirements(colorWheelSubsystem);
    
  }

  
  
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // start the motor
    ColorWheelSpinner.m_startColor = m_colorWheelSubsystem.colorToString(m_colorWheelSubsystem.detectColor());
  }



  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //call numRotations while motor is running 
    final String startColor = m_startColor;
    m_colorWheelSubsystem.numRotaions(startColor);
  }



  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
    //stop the motor when numRotations is max

  }



  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
