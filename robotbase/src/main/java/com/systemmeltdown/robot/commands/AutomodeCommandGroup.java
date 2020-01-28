package com.systemmeltdown.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * Automode command group. This command will run a series of commands depending
 * on what selections have been selected in shuffleboard.
 */
public class AutomodeCommandGroup extends SequentialCommandGroup {
  /**
   * Creates a new ComplexAuto.
   */
  public AutomodeCommandGroup() {
    addCommands(
        /*Create an object of the command you want to add here.
        //Example: new ClimbLeftCommand(Paramaters bla bla bla),
        //The comma is part of the example, since you are creating
        the object as parameters for addCommands() */
    );
  }
}