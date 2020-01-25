/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
  /**
   * CAN IDS 1-10 Core Components of the Robot
   */
  public static final int PDP = 1;
  public static final int PCM_1 = 2;
  public static final int PCM_2 = 3;

  /**
   * CAN IDS 11-20 Drive Base Devices
   */
  public static final int DRIVE_MOTOR_LEFT_1 = 11;
  public static final int DRIVE_MOTOR_RIGHT_1 = 12;
  public static final int DRIVE_MOTOR_LEFT_2 = 13;
  public static final int DRIVE_MOTOR_RIGHT_2 = 14;
  public static final int DRIVE_MOTOR_LEFT_3 = 15;
  public static final int DRIVE_MOTOR_RIGHT_3 = 16;
  public static final int DRIVE_MOTOR_LEFT_4 = 17;
  public static final int DRIVE_MOTOR_RIGHT_4 = 18;

  public static final int[] DRIVE_MOTOR_LEFT_SLAVES = { DRIVE_MOTOR_LEFT_2, DRIVE_MOTOR_LEFT_3, DRIVE_MOTOR_LEFT_4 };

  public static final int[] DRIVE_MOTOR_RIGHT_SLAVES = { DRIVE_MOTOR_RIGHT_2, DRIVE_MOTOR_RIGHT_3,
      DRIVE_MOTOR_RIGHT_4 };

  /**
   * CAN IDS 21-60 Mechanisms and other devices (robot specific)
   */

  public static final int[] LEFT_ENCODER_PORTS = new int[]{11, 13};
  public static final int[] RIGHT_ENCODER_PORTS = new int[]{12, 14};

  public static final double WHEEL_DIAMETER_IN_METERS = 0.1524;
  public static final int ENCODER_CPR = 1024;

  public static final double ENCODER_DISTANCE_PER_PULSE = 
    (WHEEL_DIAMETER_IN_METERS * Math.PI) / (double) ENCODER_CPR;

  public static final int GYRO_ID = 22;
  public static final int SCISSOR_SOLENOID = -1;
  public static final int WINCH_MOTOR_LEFT = -1;
  public static final int WINCH_MOTOR_RIGHT = -1;

  /** Intake motor */
  public static final int INTAKE_MOTOR_ID = -1;

  /** Solenoid to extend intake assembly */
  public static final int INTAKE_SOLENOID_CHANNEL = -1;

  /** IR sensor before feed wheel */
  public static final int STORAGE_FEED_SENSOR_CHANNEL = -1;

  /**
   * Characterization Constants Zeroes are currently placeholder values
   */

  public static final double S_VOLTS = 0.0;
  public static final double V_VOLT_SECONDS_PER_METER = 0.0;
  public static final double A_VOLT_SECONDS_SQUARED_PER_METER = 0.0;

  /**
   * Differential Drive Kinematics Zeroes as place holder values
   */

  public static final double TRACK_WIDTH_METERS = 0.0;
  public static final DifferentialDriveKinematics DRIVE_KINEMATICS = new DifferentialDriveKinematics(TRACK_WIDTH_METERS);

  /**
   * Max Trajectory acceleration and velocity Zeroes as place holder values
   */

  public static final double MAX_SPEED_METERS_PER_SECOND = 0;
  public static final double MAX_ACCELERATION_METERS_PER_SECOND_SQUARED = 0;

  /**
   * Ramsete Parameters
   * Reasonable baseline values for a RAMSETE follower in units of meters and seconds
   */

  public static final double RAMSETE_B = 2;
  public static final double RAMSETE_ZETA = 0.7;

  /**
   * Controls if Ggyro is reversed or not.
   */

  public static final boolean GYRO_REVERSED = true;

  /**
   * Encoder Constants
   */

  public static final boolean LEFT_ENCODER_REVERSED = false;
  public static final boolean RIGHT_ENCODER_REVERSED = true;

  /**
   * No idea, but the Ramsete command wants two PID controllers with it, 
   * and I do as the Rasmsete command guides -- Nolan Campbell 
   */
  public static final double P_DRIVE_VEL = 8.5;
}
