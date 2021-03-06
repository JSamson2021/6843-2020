/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HangingSubsystem;
public class PickRobotUp extends CommandBase {
  final HangingSubsystem m_hangingSubsystem;
  /**
   * Creates a new PickRobotUp.
   */
  public PickRobotUp(HangingSubsystem hangingSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_hangingSubsystem = hangingSubsystem;
    addRequirements(hangingSubsystem);
    // pneumatics stuff
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_hangingSubsystem.releaseHangingMech();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_hangingSubsystem.pullRobotUp();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
