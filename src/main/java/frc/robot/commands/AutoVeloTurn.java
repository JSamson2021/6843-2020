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
 * May want to compose into a parallel race group with a timeout.
 * 
 * Designed for pre-planned autonomous turns.
 * 
 * @see Command#withTimeout(double)
 */
public class AutoVeloTurn extends CommandBase {
    private final DriveSubsystem m_driveSubsystem;
    private final double m_leftSpeed;
    private final double m_rightSpeed;
    private final float m_targetYaw;
    private float m_startYawDelta;

    /**
     * May want to compose into a parallel race group with a timeout.
     */
    public AutoVeloTurn(final DriveSubsystem driveSubsystem, final double leftSpeed, final double rightSpeed,
            final float targetYaw) {
        this.m_driveSubsystem = driveSubsystem;
        this.m_leftSpeed = leftSpeed;
        this.m_rightSpeed = rightSpeed;
        this.m_targetYaw = targetYaw;
        addRequirements(this.m_driveSubsystem);
    }

    /**
     * The initial subroutine of a command. Called once when the command is
     * initially scheduled.
     */
    @Override
    public void initialize() {
        this.m_driveSubsystem.clearEncoders();
        this.m_startYawDelta = this.m_targetYaw - this.m_driveSubsystem.getYaw();
    }

    /**
     * The main body of a command. Called repeatedly while the command is scheduled.
     */
    @Override
    public void execute() {
        this.m_driveSubsystem.velocityDrive(this.m_leftSpeed, this.m_rightSpeed);
    }

    /**
     * Whether the command has finished. Once a command finishes, the scheduler will
     * call its end() method and un-schedule it.
     *
     * @return whether the command has finished.
     */
    @Override
    public boolean isFinished() {
        final float currentYaw = this.m_driveSubsystem.getYaw();
        final float currentDelta = this.m_targetYaw - currentYaw;
        return (Math.abs(currentDelta) < 1.0) || (currentDelta * this.m_startYawDelta) > 0.0f;
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