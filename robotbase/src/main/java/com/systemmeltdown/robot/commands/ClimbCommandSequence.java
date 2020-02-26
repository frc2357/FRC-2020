package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.ClimbSubsystem;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;

/**
 * This command sequence extends the scissor lift, hooks on the bar, and climbs level.
 */
public class ClimbCommandSequence extends SequentialCommandGroup {
    // public ClimbCommandSequence(final ClimbSubsystem climbSubsystem) {
    //     if (climbSubsystem != null) {
    //         final Command raiseHook = new ClimbRaiseScissorCommand(climbSubsystem);
    //         final Command reelWinch = new ClimbReelWinchCommand(climbSubsystem);
    //         final Command climbLevel = new ClimbLevelCommand(climbSubsystem);

    //         // Run the raise scissor lift and reel winch commands in parallel. The reel
    //         // winch command ends when it detects that the hook is hooked.
    //         final Command reachToHookBar = new ParallelRaceGroup(
    //           raiseHook,
    //           new SequentialCommandGroup(
    //             new WaitCommand(Constants.CLIMB_WAIT_FOR_WINCH),
    //             reelWinch));
      
    //         // Hook on the bar and then climb level
    //         addCommands(reachToHookBar, climbLevel);
    //     }
    // }
}