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
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HangingSubsystem extends SubsystemBase {
  
  private Compressor compressor = new Compressor(Constants.compressor); // Instantiating the compressor establishes the entire pneumatic system
  private Solenoid firstStage = new Solenoid(Constants.hangingOne); // Single solenoid for raising the secondary solenoid
  private DoubleSolenoid secondStage = new DoubleSolenoid(Constants.hangingTwoFirst, Constants.hangingTwoSecond); // Double solenoid, raised before firing to hook the hang bar
  private DoubleSolenoid secondStage2 = new DoubleSolenoid(Constants.hangingThreeFirst, Constants.hangingThreeSecond);

  private WPI_TalonSRX pullMotor = new WPI_TalonSRX(Constants.hangMotor); // Motor to pull robot up once we're hooked on the hang bar
  private WPI_TalonSRX pullMotor2 = new WPI_TalonSRX(Constants.hangMotor); // Motor to pull robot up once we're hooked on the hang bar
  private SpeedController pullCon = pullMotor;
  private SpeedController pullCon2 = pullMotor2;

  private DigitalInput hangLimit = new DigitalInput(Constants.hangLimit);
  
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
    firstStage.set(true); // Activates the first phase of hanging
  }

  public void hangPhaseTwo(){
    secondStage.set(Value.kForward); // Activates the second stage of hanging
    secondStage2.set(Value.kForward);
  }

  public void releaseHangingMech(){
    secondStage.set(Value.kOff);
    secondStage2.set(Value.kOff); 
  }

  public void pullRobotUp(){ // FIXME Encoder Values are wrong
    if (hangLimit.get() == true){
      pullCon.set(0.0);
      pullCon.stopMotor(); // Just to be extra safe
    } else if (pullMotor.getSelectedSensorPosition() < 100){
      pullCon.set(.2);
    } else if (pullMotor.getSelectedSensorPosition() >= 100){
      pullCon.set(0.0);
      pullCon.stopMotor(); // Just to be extra safe again 
    } else {
      pullCon.set(0.0);
      pullCon.stopMotor(); // Just to be extra safe again again 
    }
  }
  
// poopoopopopoopoppoppooopopopopp poopopopoooppoooopopoopopopooopoopooopoopoopoopoopoopopooop
}
