/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

public class DriveSubsystem extends SubsystemBase {
  /**
   * Creates a new DriveSubsystem.
   */
  private final AHRS gyro = new AHRS(SPI.Port.kMXP, (byte) 200);

  private final WPI_TalonSRX leftMotor1 = new WPI_TalonSRX(Constants.leftMotor);
  private final WPI_TalonSRX rightMotor1 = new WPI_TalonSRX(Constants.rightMotor);
  private final WPI_VictorSPX leftMotor2 = new WPI_VictorSPX(Constants.leftMotor2);
  private final WPI_VictorSPX rightMotor2 = new WPI_VictorSPX(Constants.rightMotor2);


  private final DifferentialDrive diffDrive = new DifferentialDrive(leftMotor1, rightMotor1);
  
  public DriveSubsystem() {

    rightMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0); // Tell the motor controllers that there are encoders connected to them
    rightMotor1.setSensorPhase(true);
    leftMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
    leftMotor1.setSensorPhase(true);

    leftMotor1.configOpenloopRamp(0);
    rightMotor1.configOpenloopRamp(0);
    leftMotor1.setNeutralMode(NeutralMode.Brake);
    rightMotor1.setNeutralMode(NeutralMode.Brake);
    

    leftMotor2.follow(leftMotor1); // Tells the secondary motors to follow the TalonSRXs
    rightMotor2.follow(rightMotor1); 
  
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Right Encoder", rightMotor1.getSelectedSensorPosition());
    SmartDashboard.putNumber("Left Encoder", leftMotor1.getSelectedSensorPosition());
    SmartDashboard.putNumber("Gyro Yaw" , gyro.getAngle());
    SmartDashboard.putNumber("Gyro Heading" , gyro.getCompassHeading());
  }

  public void arcadeDrive(double power, double curve){
    diffDrive.arcadeDrive(power, curve);
  }

  public void clearEncoders(){
    leftMotor1.setSelectedSensorPosition(0);
    rightMotor1.setSelectedSensorPosition(0);
  }

  public void clearGyro(){
    gyro.zeroYaw();
  }

}
