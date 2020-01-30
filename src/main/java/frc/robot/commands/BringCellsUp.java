/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PickUpSubsystem;

public class BringCellsUp extends CommandBase {
  final PickUpSubsystem m_pickUpSubsystem;
  /**
   * Creates a new BringCellsUp.
   */
  public BringCellsUp(PickUpSubsystem pickUpSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_pickUpSubsystem = pickUpSubsystem;
    addRequirements(pickUpSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_pickUpSubsystem.spinConveyor(.25);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_pickUpSubsystem.stopConveyor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
