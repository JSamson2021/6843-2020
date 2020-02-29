/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
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

// All of these can never have null values, so I set them all to 6.9, but they will refresh after 20ms
  private NetworkTableEntry ampEntry0 = Shuffleboard.getTab(Constants.ampTab).add("Amps port 0", 6.9).getEntry();
  private NetworkTableEntry ampEntry1 = Shuffleboard.getTab(Constants.ampTab).add("Amps port 1", 6.9).getEntry();
  private NetworkTableEntry ampEntry2 = Shuffleboard.getTab(Constants.ampTab).add("Amps port 2", 6.9).getEntry();
  private NetworkTableEntry ampEntry3 = Shuffleboard.getTab(Constants.ampTab).add("Amps port 3", 6.9).getEntry();
  private NetworkTableEntry ampEntry4 = Shuffleboard.getTab(Constants.ampTab).add("Amps port 4", 6.9).getEntry();
  private NetworkTableEntry ampEntry5 = Shuffleboard.getTab(Constants.ampTab).add("Amps port 5", 6.9).getEntry();
  private NetworkTableEntry ampEntry6 = Shuffleboard.getTab(Constants.ampTab).add("Amps port 6", 6.9).getEntry();
  private NetworkTableEntry ampEntry7 = Shuffleboard.getTab(Constants.ampTab).add("Amps port 7", 6.9).getEntry();
  private NetworkTableEntry ampEntry8 = Shuffleboard.getTab(Constants.ampTab).add("Amps port 8", 6.9).getEntry();
  private NetworkTableEntry ampEntry9 = Shuffleboard.getTab(Constants.ampTab).add("Amps port 9", 6.9).getEntry();
  private NetworkTableEntry ampEntry10 = Shuffleboard.getTab(Constants.ampTab).add("Amps port 10", 6.9).getEntry();
  private NetworkTableEntry ampEntry11 = Shuffleboard.getTab(Constants.ampTab).add("Amps port 11", 6.9).getEntry();
  private NetworkTableEntry ampEntry12 = Shuffleboard.getTab(Constants.ampTab).add("Amps port 12", 6.9).getEntry();
  private NetworkTableEntry ampEntry13 = Shuffleboard.getTab(Constants.ampTab).add("Amps port 13", 6.9).getEntry();
  private NetworkTableEntry ampEntry14 = Shuffleboard.getTab(Constants.ampTab).add("Amps port 14", 6.9).getEntry();
  private NetworkTableEntry ampEntry15 = Shuffleboard.getTab(Constants.ampTab).add("Amps port 15", 6.9).getEntry();



  public DriveSubsystem() {

    // Establishes encoders and tells Talons what they are
    rightMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
    rightMotor1.setSensorPhase(true);
    leftMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
    leftMotor1.setSensorPhase(true);

    // Sets the ramp to 0 and control mode to brake, so the motors stop when we say
    leftMotor1.configOpenloopRamp(0);
    rightMotor1.configOpenloopRamp(0);
    leftMotor2.configOpenloopRamp(0);
    rightMotor2.configOpenloopRamp(0);
    leftMotor1.setNeutralMode(NeutralMode.Brake);
    rightMotor1.setNeutralMode(NeutralMode.Brake);
    leftMotor2.setNeutralMode(NeutralMode.Brake);
    rightMotor2.setNeutralMode(NeutralMode.Brake);

    leftMotor2.follow(leftMotor1); // Tells the secondary motors to follow the TalonSRXs
    rightMotor2.follow(rightMotor1);

    // Set PID values for velocity control.
    // THESE ARE GUESSES RIGHT NOW.
    rightMotor1.config_kF(0, .25);
		rightMotor1.config_kP(0, 0.1);
		rightMotor1.config_kI(0, 0);
    rightMotor1.config_kD(0, 0);
    leftMotor1.config_kF(0, .25);
		leftMotor1.config_kP(0, 0.1);
		leftMotor1.config_kI(0, 0);
		leftMotor1.config_kD(0, 0);
  }

  @Override
  public void periodic() { // This method will be called once per scheduler run
    SmartDashboard.putNumber("Right Encoder", rightMotor1.getSelectedSensorPosition());
    SmartDashboard.putNumber("Left Encoder", leftMotor1.getSelectedSensorPosition());
    SmartDashboard.putNumber("Gyro Yaw", gyro.getAngle());
    SmartDashboard.putNumber("Gyro Heading", gyro.getCompassHeading());

    // Amps drawn by the specified ports of the PDP
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

    // Apparently this is how you set values to the Shuffleboard
    ampEntry0.setDouble(amperagePort0);
    ampEntry1.setDouble(amperagePort1);
    ampEntry2.setDouble(amperagePort2);
    ampEntry3.setDouble(amperagePort3);
    ampEntry4.setDouble(amperagePort4);
    ampEntry5.setDouble(amperagePort5);
    ampEntry6.setDouble(amperagePort6);
    ampEntry7.setDouble(amperagePort7);
    ampEntry8.setDouble(amperagePort8);
    ampEntry9.setDouble(amperagePort9);
    ampEntry10.setDouble(amperagePort10);
    ampEntry11.setDouble(amperagePort11);
    ampEntry12.setDouble(amperagePort12);
    ampEntry13.setDouble(amperagePort13);
    ampEntry14.setDouble(amperagePort14);
    ampEntry15.setDouble(amperagePort15);
  }

  public void arcadeDrive(double power, double curve) {
    diffDrive.arcadeDrive(power, curve);
  }

  /**
	 * Used to drive and known left and right velocities via PID.
	 * 
	 * @param leftVelocity  the velocity for the left side (clicks / sec ?)
	 * @param rightVelocity the velocity for the right side (clicks / sec ?)
	 */
	public void velocityDrive(double leftVelocity, double rightVelocity) {
		leftMotor1.set(ControlMode.Velocity, leftVelocity);
    rightMotor1.set(ControlMode.Velocity, rightVelocity);
    diffDrive.feed();
	}

  public void clearEncoders() {
    leftMotor1.setSelectedSensorPosition(0);
    rightMotor1.setSelectedSensorPosition(0);
  }

  public void clearGyro() {
    gyro.zeroYaw();
  }

public float getYaw(){
  return gyro.getYaw();
}

}
