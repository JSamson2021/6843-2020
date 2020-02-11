/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class JoystickArcadeDrive extends CommandBase {
  final DriveSubsystem m_driveSubsystem;
  final Supplier<Double> m_powerSupplier;
  final Supplier<Double> m_curveSupplier;

  /**
   * Creates a new JoystickArcadeDrive.
   */
  public JoystickArcadeDrive(DriveSubsystem driveSubsystem, 
      Supplier<Double> powerSupplier,
      Supplier<Double> curveSupplier) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_driveSubsystem = driveSubsystem;
    m_powerSupplier = powerSupplier;
    m_curveSupplier = curveSupplier;
    addRequirements(driveSubsystem);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_driveSubsystem.arcadeDrive(m_powerSupplier.get(), m_curveSupplier.get());
    SmartDashboard.putNumber("Drive Power", m_powerSupplier.get());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
