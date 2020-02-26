/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ColorPosition;
import frc.robot.commands.ColorWheelSpinner;
import frc.robot.commands.DeployHooks;
import frc.robot.commands.JoystickArcadeDrive;
import frc.robot.commands.ManualBalanceHang;
import frc.robot.commands.PickRobotUp;
import frc.robot.commands.PickupState;
import frc.robot.commands.PivotHangBar;
import frc.robot.commands.ShootCells;
import frc.robot.subsystems.ColorWheelSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.HangingSubsystem;
import frc.robot.subsystems.PickUpSubsystem;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  public static final double DEAD_ZONE = 0.054321; // used to be 0.1
  public static final int DRIVE_AXIS = 1;
  public static final int CURVE_AXIS = 4;

  private final ColorWheelSubsystem m_colorWheelSubsystem = new ColorWheelSubsystem();

  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();

  private final JoystickArcadeDrive m_driveCommand = new JoystickArcadeDrive(m_driveSubsystem, ()-> getDrivePower(), ()-> getCurvePower());

  private final PickUpSubsystem m_pickUpSubsystem = new PickUpSubsystem();

  private final HangingSubsystem m_hangingSubsystem = new HangingSubsystem();

  private final XboxController driver = new XboxController(0);
// Yes Michael I took all this stuff away.
  private final XboxController secondary = new XboxController(1);
	
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
	// Configure the button bindings
	configureButtonBindings();

  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  m_driveSubsystem.setDefaultCommand(m_driveCommand);
  

  // Primary drive controller button bindings:
    new JoystickButton(driver, Button.kBumperRight.value).whileHeld(new PickupState(m_pickUpSubsystem));
    new JoystickButton(driver, Button.kY.value).whileHeld(new ShootCells(m_pickUpSubsystem));

  // Secondary drive controller button bindings:
    new JoystickButton(secondary, Button.kA.value).whenPressed(new ColorWheelSpinner(m_colorWheelSubsystem));
    new JoystickButton(secondary, Button.kB.value).whenPressed(new ColorPosition(m_colorWheelSubsystem));
    new JoystickButton(secondary, Button.kX.value).whenPressed(new SequentialCommandGroup(new PivotHangBar(m_hangingSubsystem).withTimeout(2.0), new DeployHooks(m_hangingSubsystem).withTimeout(3.0)));
    new JoystickButton(secondary, Button.kY.value).whenPressed(new SequentialCommandGroup(new PickRobotUp(m_hangingSubsystem), new ManualBalanceHang(m_hangingSubsystem, () -> getSecondaryRightStick(), () -> getSecondaryLeftStick())));
    new JoystickButton(secondary, Button.kStart.value).whenPressed(new InstantCommand(() -> CommandScheduler.getInstance().cancelAll()));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_driveCommand;
  }

  /**
	 * @return the drive power using cubed inputs.
	 */
	public double getDrivePower() {
		double drivePower = -driver.getRawAxis(DRIVE_AXIS);
		if (Math.abs(drivePower) < DEAD_ZONE) { 
			drivePower = 0.0;
    }
    return Math.pow((drivePower * 0.95) , 3.0); // 0.75 works
		
	}

	/**
	 * @return the drive power using inputs to the third.
	 */
	public double getCurvePower() {
		double curvePower = driver.getRawAxis(CURVE_AXIS);
		if (Math.abs(curvePower) < DEAD_ZONE) {
			curvePower = 0.0;
		}
		return Math.pow((curvePower * 0.85) , 3.0); 
  }
  
  public double getSecondaryLeftStick() {
    double leftStick = secondary.getRawAxis(DRIVE_AXIS);
    if (Math.abs(leftStick) < DEAD_ZONE) {
			leftStick = 0.0;
    }
    return Math.pow((leftStick * 0.9), 3.0);
  }

  public double getSecondaryRightStick() {
    double rightStick = secondary.getRawAxis(CURVE_AXIS);
    if (Math.abs(rightStick) < DEAD_ZONE) {
			rightStick = 0.0;
    }
    return Math.pow((rightStick * 0.9), 3.0);
  }

}