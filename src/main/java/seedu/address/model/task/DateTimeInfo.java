package seedu.address.model.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

import seedu.address.model.person.Email;


/**
 * Represents a DateTimeInfo class in FlexiTrack
 */
public class DateTimeInfo {
	private Calendar dueDate;
	private Calendar startTime;
	private Calendar endTime;
	
	public DateTimeInfo(Calendar dueDate, Calendar startTime, Calendar endTime) {
		this.dueDate = dueDate;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public DateTimeInfo () {
	     this.dueDate = null;
	     this.startTime = null;
	     this.startTime = null;
	}
	
    public void setDueDate(Calendar dueDate) {
        this.dueDate = dueDate;
    }
    
    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }
    
    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }
	
	public Calendar getDueDate() {
	    return dueDate;
	}
	
	public Calendar getStartTime() {
        return startTime;
    }
	
	public Calendar getEndTime() {
        return endTime;
    }
	
	@Override
    public String toString() {
	    SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:m:s a");
        if(dueDate!=null) {
            return "by " + dateFormatter.format(dueDate);
        }else {
            return "from " + dateFormatter.format(startTime) + " to " 
                    + dateFormatter.format(endTime);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateTimeInfo // instanceof handles nulls
                && dueDate==null? 
                this.dueDate.equals(((DateTimeInfo) other).dueDate) : 
                    this.startTime.equals(((DateTimeInfo) other).startTime) 
                    && this.endTime.equals(((DateTimeInfo) other).endTime)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(dueDate, startTime, endTime);
    }
}
