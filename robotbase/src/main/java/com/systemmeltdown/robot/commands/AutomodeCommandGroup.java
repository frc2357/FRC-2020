package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.shuffleboard.AutoWaitTimeAndChooser;
import com.systemmeltdown.robot.shuffleboard.AutomodeActions;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * Automode command group. This command will run a series of commands depending
 * on what selections have been selected in shuffleboard.
 */
public class AutomodeCommandGroup extends SequentialCommandGroup {
  /**
   * Creates a new ComplexAuto.
   */
  public AutomodeCommandGroup(AutoWaitTimeAndChooser[] autoWaitAndChoosers) {
    for (AutoWaitTimeAndChooser autoWaitTimeAndChooser : autoWaitAndChoosers) {
      boolean toBreak = false;
      AutomodeActions selectedAction = autoWaitTimeAndChooser.m_chooser.getSelected();
      double waitTime = autoWaitTimeAndChooser.m_autoTimeSelectorWidget.getDouble(0.0);

      switch (selectedAction) {
      case SHOOT: {
        // addCommands shoot command
        System.out.println("Ran shoot command after " + waitTime + " seconds.");
        break;
      }
      case PICKUP_FROM_SHEILD: {
        // addCommands pickup from shield command
        System.out.println("Ran pickup from shield command after " + waitTime + " seconds.");
        break;

      }
      case PICKUP_FROM_TRENCH: {
        // addCommands pickup from trench command
        System.out.println("Ran pickup from trench command after " + waitTime + " seconds.");
        break;

      }
      case NONE: {
        toBreak = true;
        System.out.println("Stopped running commands.");
        break;

      }

      }

      if (toBreak) {
        break;
      }

      

    }

    addCommands(
    /*
     * Create an object of the command you want to add here. //Example: new
     * ClimbLeftCommand(Paramaters bla bla bla), //The comma is part of the example,
     * since you are creating the object as parameters for addCommands()
     */
    );
  }
}