/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

/**
 * Must always be composed into a parallel race group with a timeout.
 * 
 * @see Command#withTimeout(double)
 */
public class AutoVeloDrive extends CommandBase {
    public static final double DEFAULT_SPEED = 1000.0;

    private final DriveSubsystem m_driveSubsystem;
    private final double m_speed;

    /**
     * Must always be composed into a parallel race group with a timeout.
     * 
     * @param driveSubsystem the required DriveSubsystem
     */
    public AutoVeloDrive(final DriveSubsystem driveSubsystem) {
        this(driveSubsystem, DEFAULT_SPEED);
    }

    /**
     * Must always be composed into a parallel race group with a timeout.
     * 
     * @param driveSubsystem the required DriveSubsystem
     * @param speed the speed in clicks per second (?)
     */
    public AutoVeloDrive(final DriveSubsystem driveSubsystem, final double speed) {
        this.m_driveSubsystem = driveSubsystem;
        this.m_speed = speed;
        addRequirements(this.m_driveSubsystem);
    }

    /**
     * The initial subroutine of a command. Called once when the command is
     * initially scheduled.
     */
    @Override
    public void initialize() {
        this.m_driveSubsystem.clearEncoders();
    }

    /**
     * The main body of a command. Called repeatedly while the command is scheduled.
     */
    @Override
    public void execute() {
        this.m_driveSubsystem.velocityDrive(this.m_speed, -this.m_speed);
    }

    /**
     * The action to take when the command ends. Called when either the command
     * finishes normally, or when it interrupted/canceled.
     *
     * @param interrupted whether the command was interrupted/canceled
     */
    @Override
    public void end(boolean interrupted) {
        this.m_driveSubsystem.velocityDrive(0.0, 0.0);
    }
}