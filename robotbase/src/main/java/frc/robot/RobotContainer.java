/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

import com.systemmeltdown.robotlib.subsystems.drive.SingleSpeedTalonDriveSubsystem;
import com.systemmeltdown.robotlib.subsystems.drive.TalonGroup;
import com.systemmeltdown.robotlib.commands.DriveProportionalCommand;
import com.systemmeltdown.robotlib.controllers.DriverControls;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  private final SingleSpeedTalonDriveSubsystem m_driveSub = new SingleSpeedTalonDriveSubsystem(
    new TalonGroup(Constants.DRIVE_MOTOR_RIGHT_1, Constants.DRIVE_MOTOR_RIGHT_SLAVES),
    new TalonGroup(Constants.DRIVE_MOTOR_LEFT_1, Constants.DRIVE_MOTOR_LEFT_SLAVES)
  );


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureDriveSub();
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }

  private void configureDriveSub() {
    Map<String, Object> configMap = new HashMap<>();
    configMap.put(m_driveSub.CONFIG_IS_RIGHT_INVERTED, true);
    configMap.put(m_driveSub.CONFIG_IS_LEFT_INVERTED, false);
    m_driveSub.configure(configMap);
    m_driveSub.setDefaultCommand(
      new DriveProportionalCommand(
        m_driveSub, 
        new DriverControls(
          new XboxController(0),
          .25
        )
      )
    );
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
}
