package seedu.address.logic.commands;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;
import seedu.address.model.task.DateTimeInfo;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;
import seedu.address.model.task.UniqueTaskList.DuplicateTaskTimeException;

import java.util.HashSet;
import java.util.Set;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the FlexiTrack. "
            + "Parameters: TASKDETAIL -by yyyy-M-d or -from yyyy-M-d -to yyyy-M-d [t/TAG]...\n"
            + "Example 1: " + COMMAND_WORD + " go shopping  t/friends \n"
            + "Example 2: " + COMMAND_WORD + " finish homework -by 2016/5/1 t/friends \n"
            + "Example 3: " + COMMAND_WORD 
            + " go travel -from 2016-5-1 -to 2016-5-7 t/friends";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Task toAdd;

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public AddCommand(String taskDetail, DateTimeInfo newDate, boolean isTask, boolean isEvent, Set<String> tags)
            throws IllegalValueException {
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.toAdd = new Task(
                taskDetail,
                newDate,
                isTask,
                isEvent,
                new UniqueTagList(tagSet)
        );
    }

    @Override
    public CommandResult execute() {
        assert model != null;
        try {
            model.addTask(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (DuplicateTaskTimeException e) {
            return new CommandResult(MESSAGE_DUPLICATE_PERSON);
        }

    }

}
