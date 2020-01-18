package frc.robot;

import com.systemmeltdown.robot.commands.InvertDriveCommand;
import com.systemmeltdown.robot.controls.GunnerControls;
import com.systemmeltdown.robot.controls.InvertDriveControls;

public class ConfigButtons {
  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  public static void configureButtonBindings(InvertDriveControls driverControls, GunnerControls gunnerControls) {
    // m_gunnerControls.m_shootButton.whenPressed(command)
    driverControls.m_invertButton.whenPressed(new InvertDriveCommand(driverControls));
  }
}