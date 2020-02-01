/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedController;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorWheelSubsystem;

import frc.robot.Constants;



public class ColorWheelSpinner extends CommandBase {
  /**
   * Creates a new ColorWheelSpinner.
   */
  private final SpeedController m_spinnerMotor = new WPI_TalonSRX(Constants.spinnerMotor);

  private final ColorWheelSubsystem m_colorWheelSubsystem;

  public ColorWheelSpinner(ColorWheelSubsystem colorWheelSubsystem) {
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

    m_spinnerMotor.set(.1); //start the motor
  
    if(m_colorWheelSubsystem.numRotaions() > 6){
      m_spinnerMotor.set(0.0); //stops the motor when the wheel is spun three times  
    }
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
