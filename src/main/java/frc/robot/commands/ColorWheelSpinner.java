/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ColorWheelSubsystem;

public class ColorWheelSpinner extends CommandBase {
  /**
   * Creates a new ColorWheelSpinner.
   */
  
   private final ColorWheelSubsystem m_colorWheelSubsystem;
   int rotationCount;

   private NetworkTableEntry rotationEntry = Shuffleboard.getTab(Constants.colorWheelTab).add("NumRotations", 0).getEntry();
   private NetworkTableEntry startColorEntry = Shuffleboard.getTab(Constants.colorWheelTab).add("StartColor", "Null").getEntry();
   private NetworkTableEntry seenColorEntry = Shuffleboard.getTab(Constants.colorWheelTab).add("SeenColor", "Null").getEntry();


  public ColorWheelSpinner(ColorWheelSubsystem colorWheelSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_colorWheelSubsystem = colorWheelSubsystem;
    addRequirements(colorWheelSubsystem);
    
  }
  boolean searching; 
  public String startColor;

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    rotationCount = 0;
    startColor = m_colorWheelSubsystem.firstCharString(m_colorWheelSubsystem.colorString); // Assigns the debounced color to startColor  
    searching = false; 
  }



  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() { 

    String seenColor = m_colorWheelSubsystem.firstCharString(m_colorWheelSubsystem.colorString); 
    
    m_colorWheelSubsystem.spinColorWheel(.25); // Starts the motor

    if(seenColor.equals(startColor) && startColor != null && searching){
      rotationCount++; // Increases rotationCount everytime the sensor sees the first color
      searching = false; 
    }else if(!seenColor.equals(startColor)){
      searching = true;
    }

    rotationEntry.setValue(rotationCount);
    startColorEntry.setString(startColor == null ? "Null" : startColor);
    seenColorEntry.setString(seenColor == null ? "Null" : seenColor);
  }



  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_colorWheelSubsystem.stopSpinning();
  }



  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_colorWheelSubsystem.numRotations(rotationCount) > 7; // Stops the motor when the wheel is spun three and a half times 
  }
}
