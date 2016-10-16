package seedu.flexitrack.logic.commands;

import java.util.HashSet;
import java.util.Set;

/**
 * Finds and lists all tasks in FlexiTrack whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose names contain any of "
            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n"
            + "Default Search\n\t"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n\t\t"
            + "Example: " + COMMAND_WORD + "  CS2103"
            + "Search by exact task name\n\t"
            + "Parameters: f/ KEYWORD [MORE KEYWORDS]...\n\t\t"
            + "Example: " + COMMAND_WORD + "f/ attend CS2103 lecture";

    private final Set<String> keywords;

    public FindCommand(Set<String> keywords) {
        this.keywords = keywords;
    }
    
    public FindCommand(String keywords) {
        Set<String> keyword2 = new HashSet<String>();
        keyword2.add(keywords);
        this.keywords = keyword2;
    }

    @Override
    public CommandResult execute() {
        System.out.println(keywords);
        model.updateFilteredTaskList(keywords);
        return new CommandResult(getMessageForTaskListShownSummary(model.getFilteredTaskList().size()));
    }

}
