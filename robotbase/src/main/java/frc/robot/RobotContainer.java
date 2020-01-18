/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

import com.systemmeltdown.robotlib.subsystems.drive.SingleSpeedTalonDriveSubsystem;
import com.systemmeltdown.robotlib.subsystems.drive.TalonGroup;
import com.systemmeltdown.robot.commands.InvertDriveCommand;
import com.systemmeltdown.robot.controls.GunnerControls;
import com.systemmeltdown.robot.controls.InvertDriveControls;
import com.systemmeltdown.robot.subsystems.SubsystemFactory;
import com.systemmeltdown.robotlib.commands.DriveProportionalCommand;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final SingleSpeedTalonDriveSubsystem m_driveSub;

  private final InvertDriveControls m_driverControls = new InvertDriveControls(new XboxController(0), .25);
  private final GunnerControls m_gunnerControls = new GunnerControls(new XboxController(1));
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    Map<String, Object> configMap = new HashMap<>();
    configMap.put(SingleSpeedTalonDriveSubsystem.CONFIG_IS_RIGHT_INVERTED, true);
    configMap.put(SingleSpeedTalonDriveSubsystem.CONFIG_IS_LEFT_INVERTED, false);

    SubsystemFactory subsystemFactory = new SubsystemFactory(configMap);
    m_driveSub = subsystemFactory.CreateSingleSpeedTalonDriveSubsystem();

    // Configure the button bindings
    configureDriveSub();
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // TODO: create shoot command
    // m_gunnerControls.m_shootButton.whenPressed(command)
    m_driverControls.m_invertButton.whenPressed(new InvertDriveCommand(m_driverControls));
  }

  private void configureDriveSub() {
    m_driveSub
        .setDefaultCommand(new DriveProportionalCommand(m_driveSub, m_driverControls));
  }
}