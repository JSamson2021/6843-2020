/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorWheelSubsystem;

public class ColorPosition extends CommandBase {
  private final ColorWheelSubsystem m_colorWheelSubsystem;
  /**
   * Creates a new ColorPosition.
   */
  public ColorPosition(ColorWheelSubsystem colorWheelSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_colorWheelSubsystem = colorWheelSubsystem;
    addRequirements(colorWheelSubsystem);
  }
  

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    if(!m_colorWheelSubsystem.onColorTwoOver()){
      m_colorWheelSubsystem.spinColorWheel(1.0);
    }
  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_colorWheelSubsystem.stopSpinning();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_colorWheelSubsystem.onColorTwoOver();
  }
}
