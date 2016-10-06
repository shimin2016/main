package seedu.address.model.task;

import seedu.address.model.task.DateTimeInfo;

public class Task {
    private String taskName;
    private DateTimeInfo dateTimeInfo;
    
    
    public Task(String taskName) {
        this(taskName, new DateTimeInfo());
    }
    
    public Task(String taskName, DateTimeInfo dateTimeInfo) {
        this.taskName = taskName;
        this.dateTimeInfo = dateTimeInfo;

    }
    
    private String getTaskName() {
        return taskName;
    }
    
    private DateTimeInfo getDate() {
        return dateTimeInfo;
    }
}
