package seedu.address.model.task;

import java.util.Date;


/**
 * Represents a DateTimeInfo class in FlexiTrack
 */
public class DateTimeInfo {
	private Date dueDate;
	private String startTime;
	private String endTime;
	
	public DateTimeInfo(Date dueDate, String startTime, String endTime) {
		this.dueDate = dueDate;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public DateTimeInfo () {
	     this.dueDate = new Date();
	     this.startTime = "";
	     this.startTime = "";
	}
	
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
	
	public Date getDueDate() {
	    return dueDate;
	}
	
	public String getStartTime() {
        return startTime;
    }
	
	public String getEndTime() {
        return endTime;
    }
}
