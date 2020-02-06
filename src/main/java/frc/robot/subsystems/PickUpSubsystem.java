/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PickUpSubsystem extends SubsystemBase {
  /**
   * Creates a new PickUpSubsystem.
   */
  
  private final SpeedController m_pickupMotor = new WPI_TalonSRX(Constants.pickupMotor);
  private final SpeedController m_conveyorMotor = new WPI_TalonSRX(Constants.conveyorMotor);
  private final SpeedController m_shootMotor = new WPI_TalonSRX(Constants.shootMotor);
  
  private DigitalInput recievingIntake = new DigitalInput(Constants.recievingIntake);
  private DigitalInput recievingFill = new DigitalInput(Constants.recievingFill);
  
  public PickUpSubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void spinPickup(double speed){
    m_pickupMotor.set(speed);
  }
  public void stopPickup(){
    m_pickupMotor.set(0.0);
    m_pickupMotor.stopMotor(); // Extra safe
  }
  public void spinConveyor(double speed){
    m_conveyorMotor.set(speed);
  }
  public void stopConveyor(){
    m_conveyorMotor.set(0.0);
    m_conveyorMotor.stopMotor(); // Still extra safe
  }
  public void shootBall(double speed){
    m_shootMotor.set(speed);
  }
  public void stopShooting(){
    m_shootMotor.set(0.0);
    m_shootMotor.stopMotor(); // Still still extra safe
  }

  public String State(){
    if(recievingFill.get() == true){   // if the sensor at the end of the conveyor is broken the pickup system is "full"
      return "Full";
    }else if (recievingFill.get() == false){ // if the sensor at the end of the conveyor is not broken it checks for:
      if(recievingIntake.get() == false){ // if the sensor at the begining of the conveyor is not broken the pickup system is "active"
        return "Active";    
    
      }else if(recievingIntake.get() == true){ //if thte sensor at the begining of thte conveyor is broken the pickup system is "stowing"
        return "Stowing";
      }
      //return "Empty";    // commented out returning a value for when the pickup system is empty
    }
    return null;
  }



}