/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.ButtonMonitor;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.JoystickArcadeDrive;
import frc.robot.subsystems.ColorWheelSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.PickUpSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  public static final double DEAD_ZONE = 0.1;
	public static final int DRIVE_AXIS = 1;
  public static final int CURVE_AXIS = 4;
  
  
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  private final ColorWheelSubsystem m_colorWheelSubsystem = new ColorWheelSubsystem();

  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();

  private final JoystickArcadeDrive m_driveCommand = new JoystickArcadeDrive(m_driveSubsystem, ()-> getDrivePower(), ()-> getCurvePower());

  private final PickUpSubsystem m_PickUpSubsystem = new PickUpSubsystem();

  private final XboxController driver = new XboxController(0);

  private final JoystickButton driverY = new JoystickButton(driver, 4);
	private final JoystickButton driverB = new JoystickButton(driver, 2);
	private final JoystickButton driverA = new JoystickButton(driver, 1);
	private final JoystickButton driverX = new JoystickButton(driver, 3);
	private final JoystickButton driverBumperLeft = new JoystickButton(driver, 5);
	private final JoystickButton driverBumperRight = new JoystickButton(driver, 6);
	private final JoystickButton driverBack = new JoystickButton(driver, 7);
	private final JoystickButton driverStart = new JoystickButton(driver, 8);
/* 	private final Trigger driverLeftThrottleButton = new ThrottleButton(driver, LEFT_FRONT_THROTTLE);
  private final Trigger driverRightThrottleButton = new ThrottleButton(driver, RIGHT_FRONT_THROTTLE); 
  Was here */
	private final POVButton driverPOV0 = new POVButton(driver, 0);
	private final POVButton driverPOV90 = new POVButton(driver, 90);
	private final POVButton driverPOV180 = new POVButton(driver, 180);
	private final POVButton driverPOV270 = new POVButton(driver, 270);


	private final XboxController secondary = new XboxController(1);
	private final JoystickButton secondaryY = new JoystickButton(secondary, 4);
	private final JoystickButton secondaryB = new JoystickButton(secondary, 2);
	private final JoystickButton secondaryA = new JoystickButton(secondary, 1);
	private final JoystickButton secondaryX = new JoystickButton(secondary, 3);
	private final JoystickButton secondaryBumperLeft = new JoystickButton(secondary, 5);
	private final JoystickButton secondaryBumperRight = new JoystickButton(secondary, 6);
	private final JoystickButton secondaryBack = new JoystickButton(secondary, 7);
	private final JoystickButton secondaryStart = new JoystickButton(secondary, 8);

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
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }

  /**
	 * @return the drive power using cubed inputs.
	 */
	public double getDrivePower() {
		double drivePower = -driver.getRawAxis(DRIVE_AXIS);
		if (Math.abs(drivePower) < DEAD_ZONE) {
			drivePower = 0.0;
		}
		return Math.pow(drivePower, 3.0);
	}

	/**
	 * @return the drive power using inputs to the fifth.
	 */
	public double getCurvePower() {
		double curvePower = driver.getRawAxis(CURVE_AXIS);
		if (Math.abs(curvePower) < DEAD_ZONE) {
			curvePower = 0.0;
		}
		return Math.pow(curvePower, 5.0);
	}

}
