package seedu.flexitrack.testutil;

import seedu.flexitrack.commons.exceptions.IllegalValueException;
import seedu.flexitrack.model.tag.Tag;
import seedu.flexitrack.model.task.Task;
import seedu.flexitrack.model.task.UniqueTaskList;
import seedu.flexitrack.model.FlexiTrack;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").withTag("Friend").build();}
 */
public class AddressBookBuilder {

    private FlexiTrack addressBook;

    public AddressBookBuilder(FlexiTrack addressBook){
        this.addressBook = addressBook;
    }

    public AddressBookBuilder withPerson(Task person) throws UniqueTaskList.DuplicateTaskException {
        addressBook.addTask(person);
        return this;
    }

    public AddressBookBuilder withTag(String tagName) throws IllegalValueException {
        addressBook.addTag(new Tag(tagName));
        return this;
    }

    public FlexiTrack build(){
        return addressBook;
    }
}
