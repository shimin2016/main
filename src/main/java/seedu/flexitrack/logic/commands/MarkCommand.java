package seedu.flexitrack.logic.commands;

import seedu.flexitrack.commons.core.EventsCenter;
import seedu.flexitrack.commons.core.Messages;
import seedu.flexitrack.commons.events.ui.JumpToListRequestEvent;
import seedu.flexitrack.model.task.ReadOnlyTask;
import seedu.flexitrack.model.task.UniqueTaskList.TaskNotFoundException;
import seedu.flexitrack.commons.core.UnmodifiableObservableList;

/**
 * Selects a person identified using it's last displayed index from the address book.
 */
public class MarkCommand extends Command {

    public final int targetIndex;

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified by the index number used in the task listing.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_PERSON_SUCCESS = "Marked Task: %1$s";

    public MarkCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute() {

        UnmodifiableObservableList<ReadOnlyTask> lastShownList = model.getFilteredTaskList();

        if (lastShownList.size() < targetIndex) {
            indicateAttemptToExecuteIncorrectCommand();
            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        
        model.markTask(targetIndex - 1);
                
        return new CommandResult(String.format(MESSAGE_MARK_PERSON_SUCCESS, targetIndex));

    }

}
