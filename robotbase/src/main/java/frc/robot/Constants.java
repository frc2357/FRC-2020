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
    /** Time between robot updates in seconds */
    public static final double UPDATE_PERIOD = Robot.kDefaultPeriod;

    /** Earth gravity in m/s^2 */
    public static final double GRAVITY = 9.8;
    /**
     * When setting values on components, wait this long for a response before
     * failing. milliseconds
     */
    public static final int TIMEOUT_MS = 30;

    /**
     * The PID code built into the Talon controllers uses 1023 to represent full
     * motor output
     */
    public static final int TALON_PID_FULL = 1023;

    /** 1min/100ms */
    public static final int MINUTES_TO_100_MS = 600;

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

    public static final int[] LEFT_ENCODER_PORTS = new int[] { 11, 13 };
    public static final int[] RIGHT_ENCODER_PORTS = new int[] { 12, 14 };

    public static final double WHEEL_DIAMETER_IN_METERS = 0.1524;
    public static final int ENCODER_CPR = 1024;

    /** Clicks per rotation for the internal encoder in the Falcon 500 */
    public static final int FALCON_ENCODER_CPR = 2048;

    public static final double ENCODER_DISTANCE_PER_PULSE = (WHEEL_DIAMETER_IN_METERS * Math.PI) / (double) ENCODER_CPR;

    public static final int GYRO_ID = 5;
    public static final int SHOOT_MOTOR_1 = 21;
    public static final int SHOOT_MOTOR_2 = 22;
    public static final int STORAGE_CAROUSEL_MOTOR = 23;
    public static final int FEEDER_MOTOR = 24;
    public static final int INTAKE_MOTOR_ID = 25;
    public static final int SCISSOR_SOLENOID_LEFT = 6;
    public static final int SCISSOR_SOLENOID_RIGHT = 7;
    public static final int WINCH_MOTOR = 26;
    public static final int TURRET_ROTATE_MOTOR = 0;
    public static final int TURRET_HOOD_MOTOR_1 = 1;
    public static final int TURRET_HOOD_MOTOR_2 = 2;

    /** Solenoid to extend intake assembly */
    public static final int INTAKE_SOLENOID_CHANNEL_FORWARD = 2;
    public static final int INTAKE_SOLENOID_CHANNEL_REVERSE = 3;

    /** IR sensor before feed wheel */
    public static final int FEED_SENSOR_CHANNEL = 9;

    public static final double INTAKE_PIVOT_ACTUATE_SECONDS = 3.0;
    public static final double INTAKE_ROTATION_SPEED = 1.0;
    public static final double INTAKE_REVERSE_SPEED = 1.0;

    /** Aboslute encoder on storage system for carousel alignment */
    public static final double STORAGE_CAROUSEL_SHOOTER_ROTATION_SPEED = 0.3;
    public static final double STORAGE_CAROUSEL_INTAKE_ROTATION_SPEED = 0.3;
    public static final int STORAGE_CAROUSEL_SEGMENTS = 5;
    public static final int STORAGE_CAROUSEL_ENCODER_CHANNEL = 0;
    public static final double STORAGE_CAROUSEL_TOTAL_DEGREES = 360;
    public static final double STORAGE_DISTANCE_PER_ROTATION_DEGREES = STORAGE_CAROUSEL_TOTAL_DEGREES
            / STORAGE_CAROUSEL_SEGMENTS;

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
    public static final DifferentialDriveKinematics DRIVE_KINEMATICS = new DifferentialDriveKinematics(
            TRACK_WIDTH_METERS);

    /**
     * Max Trajectory acceleration and velocity Zeroes as place holder values
     */

    public static final double MAX_SPEED_METERS_PER_SECOND = 0;
    public static final double MAX_ACCELERATION_METERS_PER_SECOND_SQUARED = 0;

    /**
     * Ramsete Parameters Reasonable baseline values for a RAMSETE follower in units
     * of meters and seconds
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
     * No idea, but the Ramsete command wants two PID controllers with it, and I do
     * as the Rasmsete command guides -- Nolan Campbell
     */
    public static final double P_DRIVE_VEL = 8.5;

    /**
     * Vision and Limelight constants
     */
    /** Angle of the Limelight axis from horizontal (degrees) */
    public static final double LIMELIGHT_MOUNTING_ANGLE = 0;

    /** Height of the Limelight lens center from the floor (inches) */
    public static final double LIMELIGHT_MOUNTING_HEIGHT = 0;

    /** Target width in inches */
    public static final double VISION_TARGET_WIDTH = 36;

    /** Target height in inches */
    public static final double VISION_TARGET_HEIGHT = 16;

    /** Target height from floor in inches */
    public static final double VISION_TARGET_HEIGHT_FROM_FLOOR = 80;

    /**
     * Horizontal distance in inches between the target and the hole we're aiming at
     */
    public static final double VISION_DISTANCE_TO_HOLE = 1;

    /** Height of the high target from the floor, meters */
    public static final double HIGH_TARGET_HEIGHT = 2.49;

    /** Height of the shooter from the floor, meters >> SET THIS << */
    public static final double SHOOTER_HEIGHT = 0.381;

    /**
     * Turret settings
     */
    public static final double TURRET_AIM_P = 0.26;
    public static final double TURRET_AIM_I = 0;
    public static final double TURRET_AIM_D = 0;
    public static final double TURRET_SEEK_SPEED = 0.2;
    public static final double TURRET_AIM_TOLERANCE = 0.5;

    public static final double TURRET_ROTATE_180_SECONDS = 4.78;

    /**
     * Sensor Contants
     */
    public static final int BAUD_RATE = 115200;

    /**
     * Arduino device Constants
     */

    public static final String ARDUINO_DEVICE_NAME = "ttyACM0";

    // Default mid-range values for intake TOFs.
    public static final int TOF_LOW_RANGE = 0;
    public static final int TOF_HIGH_RANGE = 0;

    /**
     * Current limit for shooter motor. Set this to limit the wheels to a safe speed
     * (<= 4kRPM)
     */
    public static final double SHOOTER_MOTOR_PEAK_OUTPUT = 0.6;

    /**
     * Run the motor at peak output and measure the encoder velocity in ticks per
     * 100ms (this is reported by getSelectedSensorVelocity)
     */
    public static final int SHOOTER_ENCODER_VELOCITY_AT_PEAK_OUTPUT = 1;

    /** Maximum safe speed for the shooter wheels */
    public static final int SHOOTER_MAX_SPEED_RPM = 5000;

    /** Speed for shooting in the low goal TODO: Calibrate */
    public static final int SHOOTER_LOW_GOAL_SPEED_RPM = 1000;

    /** Amount of time to wait for shooter to spin up before running feeder */
    public static final double FEEDER_SHOOT_DELAY = 0.5;

    /** Amount of time to wait for shooter to spin up before running carousel */
    public static final double CAROUSEL_SHOOT_DELAY = 0.5;

    /** Shooter PIDF values */
    public static final double SHOOTER_P = 0;
    public static final double SHOOTER_I = 0;
    public static final double SHOOTER_D = 0;
    public static final double SHOOTER_F = SHOOTER_MOTOR_PEAK_OUTPUT * TALON_PID_FULL
            / SHOOTER_ENCODER_VELOCITY_AT_PEAK_OUTPUT;

    /**
     * Motor speed for climb.
     */
    public static final double CLIMBER_CLIMB_SPEED = 0.1;

    /**
     * Motor speed for release (for resetting climber).
     */
    public static final double CLIMBER_RELEASE_SPEED = 0.1;

    /**
     * Climber Spark MAX Stall limit
     * TODO: Adjust this higher as needed.
     */
    public static final int CLIMBER_STALL_LIMIT_AMPS = 15;

    /**
     * Climber Spark MAX Free-running limit
     * TODO: Adjust this higher as needed.
     */
    public static final int CLIMBER_FREE_LIMIT_AMPS = 3;

    public static final double AUTO_SHOOT_SECONDS_3_CELLS = 4.0 + FEEDER_SHOOT_DELAY;

    public static final double AUTO_MOVE_OFF_LINE_SPEED = 0.25;
    public static final double AUTO_MOVE_OFF_LINE_TURN = 0.0;
    public static final double AUTO_MOVE_OFF_SECONDS = 1.0;

    public static final double AUTO_TURN_SPEED = 0.5;
    public static final double AUTO_TURN_SECONDS = 0.5;
}
