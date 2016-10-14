package seedu.flexitrack.testutil;

import seedu.flexitrack.commons.exceptions.IllegalValueException;
import seedu.flexitrack.model.tag.Tag;
import seedu.flexitrack.model.task.*;

//TODO: change the whole class 
/**
 *
 */
public class PersonBuilder {

    private TestTask person;

    public PersonBuilder() {
        this.person = new TestTask();
    }

    public PersonBuilder withName(String name) throws IllegalValueException {
        this.person.setName(new Name(name));
        return this;
    }

    public PersonBuilder withTags(String ... tags) throws IllegalValueException {
        for (String tag: tags) {
            person.getTags().add(new Tag(tag));
        }
        return this;
    }

    public PersonBuilder withAddress(String address) throws IllegalValueException {
        this.person.setDueDate(new DateTimeInfo(address));
        return this;
    }

    public PersonBuilder withPhone(String phone) throws IllegalValueException {
        this.person.setStartTime(new DateTimeInfo(phone));
        return this;
    }

    public PersonBuilder withEmail(String email) throws IllegalValueException {
        this.person.setEndTime(new DateTimeInfo(email));
        return this;
    }

    public TestTask build() {
        return this.person;
    }

}
