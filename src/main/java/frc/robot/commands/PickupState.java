/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PickUpSubsystem;

public class PickupState extends CommandBase {
  final PickUpSubsystem m_pickUpSubsystem;
  /**
   * Creates a new PickupState.
   */
  public PickupState(PickUpSubsystem pickUpSubsystem) {
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
    if(m_pickUpSubsystem.State().equals("Active")){   //when the pickup system is "active" the pickup motor in front of the conveyor opening runs
      m_pickUpSubsystem.spinPickup(.5);
      m_pickUpSubsystem.stopConveyor();
   
    }else if(m_pickUpSubsystem.State().equals("Stowing")){ //when the pickup system is "stowing" the pickup motor turns off and the conveyor motore runs 
      m_pickUpSubsystem.spinConveyor(.5);
      m_pickUpSubsystem.stopPickup();
    
    }else if(m_pickUpSubsystem.State().equals("Full")){  //when the pickup system is "full" both the motors turn off
      m_pickUpSubsystem.stopConveyor();
      m_pickUpSubsystem.stopPickup();
    
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_pickUpSubsystem.stopConveyor(); 
    m_pickUpSubsystem.stopPickup();  

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
