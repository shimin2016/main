package seedu.flexitrack.testutil;

import seedu.flexitrack.commons.exceptions.IllegalValueException;
import seedu.flexitrack.model.tag.Tag;
import seedu.flexitrack.model.task.*;

//TODO: change the whole class 
/**
 *
 */
public class TaskBuilder {

    private TestTask task;

    public TaskBuilder() {
        this.task = new TestTask();
    }

    public TaskBuilder withName(String name) throws IllegalValueException {
        this.task.setName(new Name(name));
        return this;
    }

    public TaskBuilder withTags(String ... tags) throws IllegalValueException {
        for (String tag: tags) {
            task.getTags().add(new Tag(tag));
        }
        return this;
    }

    public TaskBuilder withDueDate(String address) throws IllegalValueException {
        this.task.setDueDate(new DateTimeInfo(address));
        return this;
    }

    public TaskBuilder withStartTime(String phone) throws IllegalValueException {
        this.task.setStartTime(new DateTimeInfo(phone));
        return this;
    }

    public TaskBuilder withEndTime(String email) throws IllegalValueException {
        this.task.setEndTime(new DateTimeInfo(email));
        return this;
    }

    public TestTask build() {
        return this.task;
    }

}
