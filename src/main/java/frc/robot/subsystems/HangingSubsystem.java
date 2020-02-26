/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HangingSubsystem extends SubsystemBase {
  
  private Compressor compressor = new Compressor(Constants.compressor); // Instantiating the compressor establishes the entire pneumatic system
  private DoubleSolenoid firstStage = new DoubleSolenoid(Constants.hangingOneFirst, Constants.hangingOneSecond); // Double solenoid for raising the secondary solenoid
  private DoubleSolenoid secondStage = new DoubleSolenoid(Constants.hangingTwoFirst, Constants.hangingTwoSecond); // Double solenoid, raised before firing to hook the hang bar
  private DoubleSolenoid secondStage2 = new DoubleSolenoid(Constants.hangingThreeFirst, Constants.hangingThreeSecond);
  private Solenoid hangClutch = new Solenoid(Constants.hangClutch);

  private WPI_TalonSRX pullMotor = new WPI_TalonSRX(Constants.hangMotor); // Motor to pull robot up once we're hooked on the hang bar
  private WPI_TalonSRX pullMotor2 = new WPI_TalonSRX(Constants.hangMotor2); // Motor to pull robot up once we're hooked on the hang bar

  private DigitalInput hangLimit = new DigitalInput(Constants.hangLimit);
  private DigitalInput pivotLimit = new DigitalInput(Constants.pivotLimit);
  
  public HangingSubsystem() {
    pullMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0); // Tell the motor controllers that there are encoders connected to them
    pullMotor.setSensorPhase(true); // Inverts the phase of the Encoder
    pullMotor.setNeutralMode(NeutralMode.Brake); // Tells the motor to stop spinning when it is stopped, instead of coastinng to a stop
    pullMotor2.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0); // Tell the motor controllers that there are encoders connected to them
    pullMotor2.setSensorPhase(true); // Inverts the phase of the Encoder
    pullMotor2.setNeutralMode(NeutralMode.Brake); // Tells the motor to stop spinning when it is stopped, instead of coastinng to a stop
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void hangPhaseOne(){
    firstStage.set(Value.kForward); // Activates the first phase of hanging
  }

  public void hangPhaseTwo(){
    disengageClutch();
    secondStage.set(Value.kForward); // Activates the second stage of hanging
    secondStage2.set(Value.kForward);
  }

  public void releaseHangingMech(){
    secondStage.set(Value.kOff);
    secondStage2.set(Value.kOff); 
  }

  public void pullRobotUp(){ // FIXME Encoder Values are wrong
    engageClutch();
    if (hangLimit.get() == true){
      pullMotor.set(0.0);
      pullMotor.stopMotor();
      pullMotor2.set(0.0);
      pullMotor2.stopMotor(); // Just to be extra safe
    } else if (pullMotor.getSelectedSensorPosition() < 100){
      pullMotor.set(.3);
      pullMotor2.set(.3);
    } else if (pullMotor.getSelectedSensorPosition() >= 100){
      pullMotor.set(0.0);
      pullMotor.stopMotor();
      pullMotor2.set(0.0);
      pullMotor2.stopMotor(); // Just to be extra safe again 
    } else {
      pullMotor.set(0.0);
      pullMotor.stopMotor();
      pullMotor2.set(0.0);
      pullMotor2.stopMotor(); // Just to be extra safe again again 
    }
  }

  public Boolean isHung() {
    return hangLimit.get();
  }

  public Boolean isVertical() {
    return pivotLimit.get();
  }

  public void hangDrive(double leftDrive, double rightDrive) {
    pullMotor.set(leftDrive);
    pullMotor2.set(rightDrive);
  }

  public Boolean pneumaticsCharged(){
    return compressor.getPressureSwitchValue();
  }

  public void disengageClutch(){
    hangClutch.set(true);
  }

  public void engageClutch(){
    hangClutch.set(false);
  }

}
