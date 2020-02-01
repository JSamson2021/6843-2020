/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // CAN Channels
    public static int leftMotor = 10; // Drivebase left front motor, TalonSRX
    public static int rightMotor = 11; // Drivebase right front motor, TalonSRX
    public static int rightMotor2 = 12; // Drivebase right rear motor, VictorSPX
    public static int leftMotor2 = 13; // Drivebase left rear motor, VictorSPX
    public static int colorMotor = 14; // Motor to spin the color wheel
    public static int pickupMotor = 15; // The pickup motor that physically spins the brush to pick up the ball
    public static int hangMotor = 16; // The motor to pull up the robot once it is secured to the hang bar
    public static int conveyorMotor = 17; // The motor that controls the belt to move the balls to the shooter.
    public static int shootMotor = 18; // The motor that shoots the balls.
    public static int spinnerMotor = 19; // The motor that spins the wheel.

    public static int compressor = 0; // Compressor for pneumatics system

    // PCM Channels
    public static int hangingOne = 0;  //First phase solenoid for hang
    public static int hangingTwoFirst = 1;  // Second stage solenoid for hang, parts one and two of double solenoid
    public static int hangingTwoSecond = 1; // Beep boob beep

    // DIO Channels
    public static int hangLimit = 0;

    public static int recievingIntake = 1;
    public static int recievingFill = 2;

    public static int emittingIntake = 3;
    public static int emittingFill = 5;

    // Constants for things other than ports 
    public static int debounceCount = 4; // The number of scheduler runs before the color is considered read (20 ms is 1 run)

} 


// Stink