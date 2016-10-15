package seedu.flexitrack.model.task;

import java.util.List;

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;



import seedu.flexitrack.commons.exceptions.IllegalValueException;

public class DateTimeInfoParser {
    
    public String timingInfo;

    public DateTimeInfoParser(String givenTime) throws IllegalValueException {
        Parser parser = new Parser(); 
        List<DateGroup> dateParser = parser.parse(givenTime);
        if (!isValidDateTimeInfo(dateParser)) {
            throw new IllegalValueException("error");
            //TODO: use MESSAGE_DATETIMEINFO_CONSTRAINTS instead of error
        }       

        this.timingInfo = dateParser.get(0).getDates().toString();
        if (!isTimeSpecified(timingInfo)){
            timingInfo = timingInfo.substring(5, 12);
            timingInfo = timingInfo + "08:00";
        }else { 
            timingInfo = timingInfo.substring(5, 17);
        }
    }
    
    public String getParsedTimingInfo(){
        return timingInfo;
    }
    
    public boolean isTimeSpecified(String timingInfo){ 
        Parser parser = new Parser(); 
        List<DateGroup> dateParser = parser.parse("now");
        String timingInfoNow = dateParser.get(0).getDates().toString();
        if (timingInfo.substring(12, 20).equals(timingInfoNow.substring(12, 20))){
            return false; 
        } else {
            return true;
        }
    }
    
    public static boolean isValidDateTimeInfo(List<DateGroup> test) {
        if (!test.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    
    public static int whatMonth(String month){ 
        switch (month){
            case "Jan": 
                return 1; 
            case "Feb": 
                return 2;
            case "Mar": 
                return 3;
            case "Apr": 
                return 4;
            case "May": 
                return 5;
            case "Jun": 
                return 6;
            case "Jul": 
                return 7;
            case "Aug": 
                return 8;
            case "Sep": 
                return 9;
            case "Oct": 
                return 10;
            case "Nov": 
                return 11;
            case "Dec": 
                return 12;
            default: 
                return 0; 
        }
    }
    
    public boolean isFromBeforeTo (String fromTiming, String toTiming){
        //next year 
        if (isFromMonthBeforeToMonth(fromTiming, toTiming) == 0 ) {
            return false; 
            // Warning this is due next year 
        } else if ( isFromMonthBeforeToMonth(fromTiming, toTiming) == 2){
            if (isFromDateBeforeToDate(fromTiming, toTiming) == 0){
                return false;
                // Warning this is due next year on the same month 
            } else if ( isFromDateBeforeToDate(fromTiming, toTiming) == 2 ) { 
                if ( isFromHourBeforeToHour(fromTiming, toTiming) == 0 ){
                    return false; 
                    // same day diff hour. due now or next your. do u want to change? 
                }
                else if ( isFromHourBeforeToHour(fromTiming, toTiming) == 0 ) { 
                    if ( isFromMinuteBeforeToMinute(fromTiming, toTiming) == 0 ){
                        return false; 
                        // same hour different minute. due now or next your. do u want to change? 
                    }
                    else if ( isFromMinuteBeforeToMinute(fromTiming, toTiming) == 0 ) { 
                        return false;
                        // same minute. due now or next your. do u want to change? 
                    }
                }
            }
        }
        return true; 
    }
    
    /**
     * To check if the minute inputed in 'from' is before the minute inputed in 'to'
     * @param starting Time
     * @param ending Time
     * @return 0 if it is after, 1 if it is before and 2 if they are the same 
     */
    private int isFromMinuteBeforeToMinute(String fromTiming, String toTiming) {
        int fromMinute = Integer.parseInt(fromTiming.substring(7,8)); 
        int toMinute = Integer.parseInt(toTiming.substring(7,8)); 
        if ( fromMinute > toMinute ){ 
            return 0; 
        } else if ( fromMinute == toMinute ){ 
            return 2; 
        }
        return 1; 
    }

    /**
     * To check if the hour inputed in 'from' is before the hour inputed in 'to'
     * @param starting Time
     * @param ending Time
     * @return 0 if it is after, 1 if it is before and 2 if they are the same 
     */
    private int isFromHourBeforeToHour(String fromTiming, String toTiming) {
        int fromHour = Integer.parseInt(fromTiming.substring(7,8)); 
        int toHour = Integer.parseInt(toTiming.substring(7,8)); 
        if ( fromHour > toHour ){ 
            return 0; 
        }
        else if ( fromHour == toHour ){ 
            return 2; 
        }
        return 1; 
    }

    /**
     * To check if the date inputed in 'from' is before the date inputed in 'to'
     * @param starting Time
     * @param ending Time
     * @return 0 if it is after, 1 if it is before and 2 if they are the same 
     */
    private int isFromDateBeforeToDate(String fromTiming, String toTiming) {
        int fromDate = Integer.parseInt(fromTiming.substring(4,5)); 
        int toDate = Integer.parseInt(toTiming.substring(4,5)); 
        if ( fromDate > toDate ){ 
            return 0; 
        }
        else if ( fromDate == toDate ){ 
            return 2; 
        }
        return 1;
    }

    /**
     * To check if the month inputed in 'from' is before the month inputed in 'to'
     * @param starting Time
     * @param ending Time
     * @return 0 if it is after, 1 if it is before and 2 if they are the same 
     */
    private int isFromMonthBeforeToMonth(String fromTiming, String toTiming) {
        int fromMonth = DateTimeInfoParser.whatMonth(fromTiming.substring(0, 2)); 
        int toMonth = DateTimeInfoParser.whatMonth(toTiming.substring(0, 2)); 
        if ( fromMonth > toMonth ){ 
            return 0;  
        } else if (fromMonth == toMonth ) { 
            return 2; 
        }
        return 1; 
    }

}