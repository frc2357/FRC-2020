/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

import com.systemmeltdown.robotlib.subsystems.drive.SingleSpeedTalonDriveSubsystem;

import com.systemmeltdown.robot.commands.InvertDriveCommand;
import com.systemmeltdown.robot.controls.InvertDriveControls;
import com.systemmeltdown.robot.controls.GunnerControls;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final SingleSpeedTalonDriveSubsystem m_driveSub = SubsystemFactory.createDriveSubsystem();

  private final InvertDriveControls m_driverControls = new InvertDriveControls(new XboxController(0), .25);
  private final GunnerControls m_gunnerControls = new GunnerControls(new XboxController(1));

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    ConfigButtons.configureButtonBindings(m_driverControls, m_gunnerControls);
    CommandFactory.createDriveProportionalCommand(m_driveSub, m_driverControls);
  }
}
