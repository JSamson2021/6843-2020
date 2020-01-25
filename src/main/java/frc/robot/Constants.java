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
    //CAN Channels
    public static int leftMotor = 10; // The first two are the drive motors for the robot
    public static int rightMotor = 11;
    public static int rightMotor2 = 12;
    public static int leftMotor2 = 13;
    
    public static int compressor = 0; // Compressor for pneumaatics
    public static int pickupMotor = 15; //the pickup motor thing phsically spins the brush to pivkup the ball
    public static int colorMotor = 14;

    // PCM Channels
    public static int hangingOne = 0;  //First phase solenoid for hang
    public static int hangingTwoFirst = 1;  // second stage solenoid for hang, parts one and two of double solenoid
    public static int hangingTwoSecond = 1; // Beep boob beep
}
