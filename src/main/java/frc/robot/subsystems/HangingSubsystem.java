/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class HangingSubsystem extends SubsystemBase {
  
  private Compressor compressor = new Compressor(Constants.compressor);
  private Solenoid firstStage = new Solenoid(Constants.hangingOne);
  private DoubleSolenoid secondStage = new DoubleSolenoid(Constants.hangingTwoFirst, Constants.hangingTwoSecond);

  public HangingSubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void hangPhaseOne(){
    firstStage.set(true); // Activates the first phase of hanging
  }
  

}
