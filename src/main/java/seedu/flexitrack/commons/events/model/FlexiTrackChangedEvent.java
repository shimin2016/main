package seedu.flexitrack.commons.events.model;

import seedu.flexitrack.commons.events.BaseEvent;
import seedu.flexitrack.model.ReadOnlyAddressBook;

/** Indicates the AddressBook in the model has changed*/
public class FlexiTrackChangedEvent extends BaseEvent {

    public final ReadOnlyAddressBook data;

    public FlexiTrackChangedEvent(ReadOnlyAddressBook data){
        this.data = data;
    }

    @Override
    public String toString() {
        return "number of persons " + data.getPersonList().size() + ", number of tags " + data.getTagList().size();
    }
}
