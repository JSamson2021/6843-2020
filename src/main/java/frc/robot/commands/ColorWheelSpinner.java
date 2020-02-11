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

  public ColorWheelSpinner(ColorWheelSubsystem colorWheelSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_colorWheelSubsystem = colorWheelSubsystem;
    addRequirements(colorWheelSubsystem);
    
  }

  public String startColor;

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
    String startColor = m_colorWheelSubsystem.colorToString(m_colorWheelSubsystem.debouncedColor); //assigns the debounced color to startColor  
  }



  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    int rotationCount = 0;
    String seenColor = m_colorWheelSubsystem.colorToString(m_colorWheelSubsystem.matchColor()); 
    
    m_colorWheelSubsystem.spinColorWheel(1.0); //starts the motor

    if(seenColor.equals(startColor) && startColor != null ){
      rotationCount++; //increases rotationCount everytime the sensor sees the first color
    }

     
    if(m_colorWheelSubsystem.numRotations(rotationCount) > 6){
      m_colorWheelSubsystem.stopSpinning(); //stops the motor when the wheel is spun three times  
    } 
     // TODO Uncomment after numRotations fixed
  }



  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_colorWheelSubsystem.stopSpinning();
  }



  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
