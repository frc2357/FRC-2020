/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.systemmeltdown.robot.subsystems.IntakeSubsystem;
import com.systemmeltdown.robot.subsystems.ShooterSubsystem;
import com.systemmeltdown.robotlib.subsystems.drive.FalconTrajectoryDriveSubsystem;
import com.systemmeltdown.robot.commands.AutoTemporaryCommand;
import com.systemmeltdown.robot.subsystems.ClimbSubsystem;
import com.systemmeltdown.robotlib.subsystems.drive.FalconTrajectoryDriveSubsystem;
import com.systemmeltdown.robot.commands.AutoTemporaryCommand;
import com.systemmeltdown.robot.controls.GunnerControls;
import com.systemmeltdown.robot.controls.InvertDriveControls;
import com.systemmeltdown.robot.subsystems.SubsystemFactory;
import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem;
import com.systemmeltdown.robotlib.commands.DriveProportionalCommand;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;
import com.systemmeltdown.robot.shuffleboard.AutoWaitTimeAndChooser;
import com.systemmeltdown.robot.shuffleboard.FailsafeButtonWidget;
import com.systemmeltdown.robot.shuffleboard.LoggerTab;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj2.command.Command;

import java.util.ArrayList;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private FalconTrajectoryDriveSubsystem m_driveSub;
  private final ShooterSubsystem m_shootSub;
  private final IntakeSubsystem m_intakeSub;
  // private final StorageSubsystem m_storageSub;
  private final TogglableLimelightSubsystem m_visionSub;
  private final ClimbSubsystem m_climbSub;


  private final InvertDriveControls m_driverControls;
  private final GunnerControls m_gunnerControls;
  //public final VL53LOXSensorOutput m_sensor = new VL53LOXSensorOutput(Constants.BAUD_RATE, Port.kUSB);

  private final AutoWaitTimeAndChooser[] m_waitTimeAndChooser = new AutoWaitTimeAndChooser[3];

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    SubsystemFactory subsystemFactory = new SubsystemFactory();
    m_driveSub = subsystemFactory.CreateFalconTrajectoryDriveSubsystem();
    m_shootSub = subsystemFactory.CreateShooterSubsystem();
    m_intakeSub = subsystemFactory.CreateIntakeSubsystem();
    // m_storageSub = subsystemFactory.CreateStorageSubsystem();
    m_visionSub = subsystemFactory.CreateLimelightSubsystem();
    m_climbSub = null; // subsystemFactory.CreateClimbSubsystem();

    // Configure the button bindings
    m_driverControls = new InvertDriveControls.InvertDriveControlsBuilder(new XboxController(0), .1)
                      .withDriveSub(m_driveSub)
                      .withVisionSub(m_visionSub)
                      .build();

    m_gunnerControls = new GunnerControls.GunnerControlsBuilder(new XboxController(1))
                      .withIntakeSub(m_intakeSub)
                      .withShooterSubsystem(m_shootSub)
                      .withClimbSubsystem(m_climbSub)
                      .build();

    configureDriveSub();
    configureShuffleboard();
  }

  private void configureShuffleboard() {
    //CellNumberWidget cellNumberWidget = new CellNumberWidget("Robot", m_storageSub);
    
    // for(int i = 0; i < 4; i++) {
    //  m_waitTimeAndChooser[i] = new AutoWaitTimeAndChooser("AUTO", i);
    // }

    LoggerTab loggerTab = new LoggerTab();
    
    FailsafeButtonWidget failsafeButton = new FailsafeButtonWidget("Robot",
     new ClosedLoopSubsystem[] {m_intakeSub, m_shootSub, m_climbSub, m_driveSub, m_visionSub});
  }

  private void configureDriveSub() {
    //m_driveSub.setDefaultCommand(new DriveProportionalCommand(m_driveSub, m_driverControls));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  //public Command getAutonomousCommand() {
  //  return new AutoTemporaryCommand(m_driveSub).getRamsete();
  //}
}
