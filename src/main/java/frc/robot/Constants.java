/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
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
    public static int hangMotor2 = 18; // The second motor to pull up the robot once it is secured to the hang bar
    public static int conveyorMotor = 17; // The motor that controls the belt to move the balls to the shooter.

    public static int compressor = 0; // Compressor for pneumatics system

    public static int pdpPort = 0; // Port of the Power Distribution Panel

    // PCM Channels
    public static int hangingOneFirst = 0; // First phase solenoid for hang: Pivot port
    public static int hangingOneSecond = 1; // First phase solenoid to hang: Return port
    public static int hangingTwoFirst = 2; // Second stage solenoid for hang, part one: UP
    public static int hangingTwoSecond = 3; // Second stage solenoid for hang, part two: DOWN
    public static int hangClutch = 6;

    // DIO Channels
    public static int hangLimit = 0;

    public static int recievingIntake = 1;
    public static int recievingFill = 2;

    public static int emittingIntake = 3;
    public static int emittingFill = 5;

    public static int pivotLimit = 6;

    // Constants for things other than ports
    public static int debounceCount = 3; // The number of runs before the color is considered read (20 ms is 1 run)
    public static int driveRamps = 0;

    public static final int IMAGE_WIDTH = 320;
    public static final int IMAGE_HEIGHT = 240;

    public static final String testTab = "TESTING";
    public static final String ampTab = "AMPS";
    public static final String colorWheelTab = "ColorWheel";

}

// Stink