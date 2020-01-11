/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    /**
   * CAN IDS 1-10
   * Core Components of the Robot
   */
  public static final int PDP = 1;
  public static final int PCM_1 = 2;
  public static final int PCM_2 = 3;

  /**
   * CAN IDS 11-20
   * Drive Base Devices
   */
  public static final int DRIVE_MOTOR_LEFT_1 = 11;
  public static final int DRIVE_MOTOR_RIGHT_1 = 12;
  public static final int DRIVE_MOTOR_LEFT_2 = 13;
  public static final int DRIVE_MOTOR_RIGHT_2 = 14;
  public static final int DRIVE_MOTOR_LEFT_3 = 15;
  public static final int DRIVE_MOTOR_RIGHT_3 = 16;
  public static final int DRIVE_MOTOR_LEFT_4 = 17;
  public static final int DRIVE_MOTOR_RIGHT_4 = 18;

  public static final int[] DRIVE_MOTOR_LEFT_SLAVES = {
    DRIVE_MOTOR_LEFT_2, DRIVE_MOTOR_LEFT_3, DRIVE_MOTOR_LEFT_4};

  public static final int[] DRIVE_MOTOR_RIGHT_SLAVES = {
    DRIVE_MOTOR_RIGHT_2, DRIVE_MOTOR_RIGHT_3, DRIVE_MOTOR_RIGHT_4};

  public static final int SHOOT_MOTOR_1 = 20;
  public static final int SHOOT_MOTOR_2 = 21;

  /**
   * CAN IDS 21-60
   * Mechanisms and other devices (robot specific)
   */
}
