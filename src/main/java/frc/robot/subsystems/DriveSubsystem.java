/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
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

  ShuffleboardTab testTab = Shuffleboard.getTab("TESTING");

  private final AHRS gyro = new AHRS(SPI.Port.kMXP, (byte) 200);

  private final WPI_TalonSRX leftMotor1 = new WPI_TalonSRX(Constants.leftMotor);
  private final WPI_TalonSRX rightMotor1 = new WPI_TalonSRX(Constants.rightMotor);
  private final WPI_VictorSPX leftMotor2 = new WPI_VictorSPX(Constants.leftMotor2);
  private final WPI_VictorSPX rightMotor2 = new WPI_VictorSPX(Constants.rightMotor2);


  private final DifferentialDrive diffDrive = new DifferentialDrive(leftMotor1, rightMotor1);

  private final PowerDistributionPanel powerPanel = new PowerDistributionPanel(Constants.pdpPort);

  Double amperagePort0;
  Double amperagePort1;
  Double amperagePort2;
  Double amperagePort3;
  Double amperagePort4;
  Double amperagePort5;
  Double amperagePort6;
  Double amperagePort7;
  Double amperagePort8;
  Double amperagePort9;
  Double amperagePort10;
  Double amperagePort11;
  Double amperagePort12;
  Double amperagePort13;
  Double amperagePort14;
  Double amperagePort15;
  
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

    // Amps drawn by the specified ports of the 
    Double amperagePort0 = powerPanel.getCurrent(0);
    Double amperagePort1 = powerPanel.getCurrent(1);
    Double amperagePort2 = powerPanel.getCurrent(2);
    Double amperagePort3 = powerPanel.getCurrent(3);
    Double amperagePort4 = powerPanel.getCurrent(4);
    Double amperagePort5 = powerPanel.getCurrent(5);
    Double amperagePort6 = powerPanel.getCurrent(6);
    Double amperagePort7 = powerPanel.getCurrent(7);
    Double amperagePort8 = powerPanel.getCurrent(8);
    Double amperagePort9 = powerPanel.getCurrent(9);
    Double amperagePort10 = powerPanel.getCurrent(10);
    Double amperagePort11 = powerPanel.getCurrent(11);
    Double amperagePort12 = powerPanel.getCurrent(12);
    Double amperagePort13 = powerPanel.getCurrent(13);
    Double amperagePort14 = powerPanel.getCurrent(14);
    Double amperagePort15 = powerPanel.getCurrent(15);

    Shuffleboard.selectTab("TESTING"); //* Anything under this line added to the SmartDashboard will be on TESTING tab *\\

      SmartDashboard.putNumber("Amps port 0", amperagePort0);
      SmartDashboard.putNumber("Amps port 1", amperagePort1);
      SmartDashboard.putNumber("Amps port 2", amperagePort2);
      SmartDashboard.putNumber("Amps port 3", amperagePort3);
      SmartDashboard.putNumber("Amps port 4", amperagePort4);
      SmartDashboard.putNumber("Amps port 5", amperagePort5);
      SmartDashboard.putNumber("Amps port 6", amperagePort6);
      SmartDashboard.putNumber("Amps port 7", amperagePort7);
      SmartDashboard.putNumber("Amps port 8", amperagePort8);
      SmartDashboard.putNumber("Amps port 9", amperagePort9);
      SmartDashboard.putNumber("Amps port 10", amperagePort10);
      SmartDashboard.putNumber("Amps port 11", amperagePort11);
      SmartDashboard.putNumber("Amps port 12", amperagePort12);
      SmartDashboard.putNumber("Amps port 13", amperagePort13);
      SmartDashboard.putNumber("Amps port 14", amperagePort14);
      SmartDashboard.putNumber("Amps port 15", amperagePort15);

    

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
