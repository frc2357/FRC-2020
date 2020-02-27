/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.systemmeltdown.robot.subsystems.IntakeSubsystem;
import com.systemmeltdown.robot.subsystems.ShooterSubsystem;
import com.systemmeltdown.robot.subsystems.StorageSubsystem;
import com.systemmeltdown.robotlib.subsystems.drive.FalconTrajectoryDriveSubsystem;
import com.systemmeltdown.robotlib.subsystems.drive.SingleSpeedFalconDriveSubsystem;
import com.systemmeltdown.robot.commands.AutoTemporaryCommand;
import com.systemmeltdown.robot.subsystems.ClimbSubsystem;
import com.systemmeltdown.robot.subsystems.FeederSubsystem;
import com.systemmeltdown.robot.controls.GunnerControls;
import com.systemmeltdown.robot.controls.InvertDriveControls;
import com.systemmeltdown.robot.subsystems.SubsystemFactory;
import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem;
import com.systemmeltdown.robot.subsystems.TurretSubsystem;
import com.systemmeltdown.robotlib.commands.DriveProportionalCommand;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;
import com.systemmeltdown.robot.shuffleboard.AutoWaitTimeAndChooser;
import com.systemmeltdown.robot.shuffleboard.CellNumberWidget;
import com.systemmeltdown.robot.shuffleboard.DriveTab;
import com.systemmeltdown.robot.shuffleboard.FailsafeButtonWidget;
import com.systemmeltdown.robot.shuffleboard.TargetingWidget;
import com.systemmeltdown.robot.shuffleboard.LoggerTab;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.XboxController;
// import edu.wpi.first.wpilibj.SerialPort.Port; <- Used for VL53LOX Sensor Output

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  public static final String SHUFFLEBOARD_TAB_ROBOT = "Robot";

  // The robot's subsystems and commands are defined here...
  private SingleSpeedFalconDriveSubsystem m_driveSub;
  private final ClimbSubsystem m_climbSub;
  private final FeederSubsystem m_feederSub;
  public final IntakeSubsystem m_intakeSub;
  private final ShooterSubsystem m_shootSub;
  private final StorageSubsystem m_storageSub;
  private final TurretSubsystem m_turretSub;
  private final TogglableLimelightSubsystem m_visionSub;
  private final Compressor m_compressor;

  private final InvertDriveControls m_driverControls;
  private final GunnerControls m_gunnerControls;
  // public final VL53LOXSensorOutput m_sensor = new
  // VL53LOXSensorOutput(Constants.BAUD_RATE, Port.kUSB);

  private final AutoWaitTimeAndChooser[] m_waitTimeAndChooser = new AutoWaitTimeAndChooser[3];

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    SubsystemFactory subsystemFactory = new SubsystemFactory();
    m_driveSub = subsystemFactory.CreateFalconTrajectoryDriveSubsystem();
    m_climbSub = subsystemFactory.CreateClimbSubsystem();
    m_feederSub = subsystemFactory.CreateFeederSubsystem();
    m_intakeSub = subsystemFactory.CreateIntakeSubsystem();
    m_shootSub = subsystemFactory.CreateShooterSubsystem();
    m_storageSub = subsystemFactory.CreateStorageSubsystem();
    m_turretSub = subsystemFactory.CreateTurretSubsystem();
    m_visionSub = subsystemFactory.CreateLimelightSubsystem();
    m_compressor = new Compressor();
    // m_compressor.setClosedLoopControl(false);

    // Configure the button bindings
    m_driverControls = new InvertDriveControls.InvertDriveControlsBuilder(new XboxController(0), .1)
        .withDriveSub(m_driveSub)
        .withVisionSub(m_visionSub)
        .build();

    m_gunnerControls = new GunnerControls.GunnerControlsBuilder(new XboxController(1))
        .withClimbSubsystem(m_climbSub)
        .withIntakeSub(m_intakeSub)
        .withFeederSubsystem(m_feederSub)
        .withShooterSubsystem(m_shootSub)
        .withClimbSubsystem(m_climbSub)
        .withStorageSubsystem(m_storageSub)
        .withTurretSub(m_turretSub)
        .withVisionSub(m_visionSub)
        .build();

    configureDriveSub();
    configureShuffleboard();
  }

  private void configureShuffleboard() {
    DriveTab driveTab = new DriveTab();

    driveTab.addWidget(new FailsafeButtonWidget(SHUFFLEBOARD_TAB_ROBOT,
        new ClosedLoopSubsystem[] { m_intakeSub, m_shootSub, m_climbSub, m_driveSub, m_visionSub }));

    driveTab.addWidget(new CellNumberWidget(SHUFFLEBOARD_TAB_ROBOT, m_storageSub));

    TargetingWidget targetingWidget = new TargetingWidget(SHUFFLEBOARD_TAB_ROBOT, m_turretSub);

    LoggerTab loggerTab = new LoggerTab();

    for(int i = 0; i < 3; i++) {
      m_waitTimeAndChooser[i] = new AutoWaitTimeAndChooser("AUTO", i);
    }

    // driveTab.show();
  }

  private void configureDriveSub() {
    m_driveSub.setDefaultCommand(new DriveProportionalCommand(m_driveSub, m_driverControls));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  // return new AutoTemporaryCommand(m_driveSub).getRamsete();
  // }
}
