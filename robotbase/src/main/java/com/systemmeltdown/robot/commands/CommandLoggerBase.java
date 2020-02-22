package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robotlog.topics.LogTopicRegistry;
import com.systemmeltdown.robotlog.topics.StringTopic;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * A base for commands that has the ability to log commands.
 * THIS COMMAND SHOULD NOT BE SCHEDULED! ONLY EXTENDED OFF OF!
 */
class CommandLoggerBase extends CommandBase {
    private StringTopic m_commandTopic;
    private static final String COMMAND_TOPIC_NAME = "Command Topic";

    public CommandLoggerBase() {
        m_commandTopic = (StringTopic) LogTopicRegistry.getInstance().getTopic(COMMAND_TOPIC_NAME);

        if (m_commandTopic == null) {
            m_commandTopic = new StringTopic(COMMAND_TOPIC_NAME);
        }
    }

    @Override
    public void initialize() {
        m_commandTopic.log(getClass().getSimpleName() + " initialized");
    }

    @Override
    public void execute() {
        m_commandTopic.log(getClass().getSimpleName() + " executed");
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            m_commandTopic.log(getClass().getSimpleName() + " interrupted");
        } else {
            m_commandTopic.log(getClass().getSimpleName() + " ended");
        }
    }
}