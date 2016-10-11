package seedu.address.model.task;

import seedu.address.model.tag.UniqueTagList;

/**
 * A read-only immutable interface for a Person in the addressbook.
 * Implementations should guarantee: details are present and not null, field values are validated.
 */
public interface ReadOnlyTask {
    String getTaskName();
    boolean getIsTask();
    boolean getIsEvent();
    DateTimeInfo getDateTimeInfo();


    /**
     * The returned TagList is a deep copy of the internal TagList,
     * changes on the returned list will not affect the person's internal tags.
     */
    UniqueTagList getTags();

    /**
     * Returns true if both have the same state. (interfaces cannot override .equals)
     */
    default boolean isSameDateAndTimeAs(ReadOnlyTask other) {
        if((!other.getIsEvent() && !other.getIsTask()) && other.getIsTask()) {
            return other == this // short circuit if same object
                    || (other.getDateTimeInfo().getDueDate().equals(this.getDateTimeInfo().getDueDate()));
        }else if((!other.getIsEvent() && !other.getIsTask()) && other.getIsEvent()) {
            return other == this // short circuit if same object
                    || (other.getDateTimeInfo().getStartTime().equals(this.getDateTimeInfo().getDueDate())
                        && other.getDateTimeInfo().getEndTime().equals(this.getDateTimeInfo().getEndTime()));
        }else {
            return false;
        }
        
    }

    /**
     * Formats the person as text, showing all contact details.
     */
    default String getAsText() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTaskName())
                .append(getIsTask() ? " by " : " from ")
                .append(getIsTask() ? getDateTimeInfo().getDueDate() : getDateTimeInfo().getStartTime())
                .append(getIsTask() ? "" : " to ")
                .append(getIsTask() ? "" : getDateTimeInfo().getEndTime())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Returns a string representation of this Person's tags
     */
    default String tagsString() {
        final StringBuffer buffer = new StringBuffer();
        final String separator = ", ";
        getTags().forEach(tag -> buffer.append(tag).append(separator));
        if (buffer.length() == 0) {
            return "";
        } else {
            return buffer.substring(0, buffer.length() - separator.length());
        }
    }
}
