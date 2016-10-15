package seedu.flexitrack.model.task;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.flexitrack.commons.exceptions.IllegalValueException;
import seedu.flexitrack.logic.parser.Parser;


/**
 * Represents a DateTimeInfo class in FlexiTrack
 */
public class DateTimeInfo {
    public static final String MESSAGE_DATETIMEINFO_CONSTRAINTS = "Time format should follow DD/MM/YYYY-hhmm(in 24 hour format)";
    
	private static final Pattern TIME_TYPE_DATA_ARGS_FORMAT = // '/' forward slashes are reserved for delimiter prefixes
            Pattern.compile("(?<info>.+)");
	
//	private Calendar dateAndTime= new GregorianCalendar();
	public String setTime;
	
	public DateTimeInfo( String givenTime)throws IllegalValueException {
	    assert givenTime != null;
        final Matcher matcher = TIME_TYPE_DATA_ARGS_FORMAT.matcher(givenTime.trim());
        matcher.matches();
	    DateTimeInfoParser parsedTiming = new DateTimeInfoParser( matcher.group("info"));
        this.setTime = parsedTiming.getParsedTimingInfo();
//        setDateTimeInfo(givenTime);
        
	}
    
    public void setDateTimeInfo(String givenTime) {
        this.setTime = givenTime;
/*        SimpleDateFormat sdf = new SimpleDateFormat("DDMMYYYY");
        Date dateparse = new  Date();
        try {
            dateparse = sdf.parse(givenTime);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        dateAndTime.setTime(dateparse);*/
    }

    @Override
    public String toString() {
        return setTime;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateTimeInfo // instanceof handles nulls
                && this.setTime.equals(((DateTimeInfo) other).setTime)); // state check
    }

    @Override
    public int hashCode() {
        return setTime.hashCode();
    }

    public boolean isDateNull() {
        
        return this.setTime.equals("Feb 29 23:23:23");
    }
}
