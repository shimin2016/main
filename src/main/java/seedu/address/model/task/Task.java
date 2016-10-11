package seedu.address.model.task;

import java.util.Objects;

import seedu.address.model.tag.UniqueTagList;
import seedu.address.model.task.DateTimeInfo;

public class Task implements ReadOnlyTask {
    private String taskName;
    private DateTimeInfo dateTimeInfo;
    private boolean isTask;
    private boolean isEvent;
    
    private UniqueTagList tags;
    
    public Task(String taskName) {
        this(taskName, null, true,false, new UniqueTagList());
    }
    
    public Task(String taskName, DateTimeInfo dateTimeInfo) {
        this.taskName = taskName;
        this.dateTimeInfo = dateTimeInfo;
        this.isTask = dateTimeInfo.getDueDate()!=null;
        this.isEvent = dateTimeInfo.getDueDate()==null;
        this.tags = new UniqueTagList();
    }
    
    public Task(String taskName, DateTimeInfo dateTimeInfo,boolean isTask, boolean isEvent, UniqueTagList tags) {
        this.taskName = taskName;
        this.dateTimeInfo = dateTimeInfo;
        this.isTask = isTask;
        this.isEvent = isEvent;
        this.tags = tags;
    }
    
    @Override
    public boolean getIsTask() {
        return isTask;
    }
    
    @Override
    public boolean getIsEvent() {
        return isEvent;
    }
    
    @Override
    public String getTaskName() {
        return taskName;
    }
    
    @Override
    public DateTimeInfo getDateTimeInfo() {
        return dateTimeInfo;
    }
    
    public void setIsTask(boolean new_isTask) {
        this.isTask = new_isTask;
    }
    
    public void setIsEvent(boolean new_isEvent) {
        this.isEvent = new_isEvent;
    }
    
    public void setTaskName(String newTask) {
        this.taskName = newTask;
    }
    
    public void setDateTimeInfo(DateTimeInfo newDate) {
        this.dateTimeInfo = newDate;
    }

    @Override
    public UniqueTagList getTags() {
        return new UniqueTagList(tags);
    }
    
    /**
     * Replaces this person's tags with the tags in the argument tag list.
     */
    public void setTags(UniqueTagList replacement) {
        tags.setTags(replacement);
    }
    
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyTask // instanceof handles nulls
                && this.isSameDateAndTimeAs((ReadOnlyTask) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(taskName, isTask, isEvent, dateTimeInfo, tags);
    }

    @Override
    public String toString() {
        return getAsText();
    }
}
