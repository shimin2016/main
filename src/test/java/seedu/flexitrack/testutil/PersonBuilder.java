package seedu.flexitrack.testutil;

import seedu.flexitrack.commons.exceptions.IllegalValueException;
import seedu.flexitrack.model.tag.Tag;
import seedu.flexitrack.model.task.*;

//TODO: change the whole class 
/**
 *
 */
public class PersonBuilder {

    private TestTask task;

    public PersonBuilder() {
        this.task = new TestTask();
    }

    public PersonBuilder withName(String name) throws IllegalValueException {
        this.task.setName(new Name(name));
        return this;
    }

    public PersonBuilder withTags(String ... tags) throws IllegalValueException {
        for (String tag: tags) {
            task.getTags().add(new Tag(tag));
        }
        return this;
    }

    public PersonBuilder withAddress(String address) throws IllegalValueException {
        this.task.setDueDate(new DateTimeInfo(address));
        return this;
    }

    public PersonBuilder withPhone(String phone) throws IllegalValueException {
        this.task.setStartTime(new DateTimeInfo(phone));
        return this;
    }

    public PersonBuilder withEmail(String email) throws IllegalValueException {
        this.task.setEndTime(new DateTimeInfo(email));
        return this;
    }

    public TestTask build() {
        return this.task;
    }

}
