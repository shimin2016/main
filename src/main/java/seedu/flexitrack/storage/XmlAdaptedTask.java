package seedu.flexitrack.storage;

import seedu.flexitrack.commons.exceptions.IllegalValueException;
import seedu.flexitrack.model.tag.Tag;
import seedu.flexitrack.model.tag.UniqueTagList;
import seedu.flexitrack.model.task.*;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * JAXB-friendly version of the Person.
 */
public class XmlAdaptedTask {

    @XmlElement(required = true)
    private String name;
//    @XmlElement(required = true)
//    private Boolean isEvent;
//    @XmlElement(required = true)
//    private Boolean isTask;
    @XmlElement
    private String dueDate;
    @XmlElement
    private String startTime;
    @XmlElement
    private String endTime;

    @XmlElement
    private List<XmlAdaptedTag> tagged = new ArrayList<>();

    /**
     * No-arg constructor for JAXB use.
     */
    public XmlAdaptedTask() {}


    /**
     * Converts a given Task into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedPerson
     */
    public XmlAdaptedTask(ReadOnlyTask source) {
        name = source.getName().fullName;
        dueDate = source.getDueDate().setTime;
        startTime = source.getStartTime().setTime;
        endTime = source.getEndTime().setTime;
        tagged = new ArrayList<>();
        for (Tag tag : source.getTags()) {
            tagged.add(new XmlAdaptedTag(tag));
        }
    }

    /**
     * Converts this jaxb-friendly adapted person object into the model's Person object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */
    public Task toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (XmlAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }
        final Name name = new Name(this.name);
        final DateTimeInfo dueDate = new DateTimeInfo(this.dueDate);
        final DateTimeInfo startTime = new DateTimeInfo(this.startTime);
        final DateTimeInfo endTime = new DateTimeInfo(this.endTime);
        final UniqueTagList tags = new UniqueTagList(personTags);
        return new Task(name, dueDate,startTime,endTime, tags);
    }
}
