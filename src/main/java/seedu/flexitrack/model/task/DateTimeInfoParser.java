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
    
    /**
     * To check if the minute inputed in 'from' is before the minute inputed in 'to'
     * @param starting Time
     * @param ending Time
     * @return 0 if it is after, 1 if it is before and 2 if they are the same 
     */
    public String durationOfTheEvent (String startingTime, String endingTime){
        int months = monthsOfTheEvent (startingTime,endingTime);
        int days = daysOfTheEvent (startingTime,endingTime);
        int hours = hoursOfTheEvent (startingTime,endingTime);
        int minutes = minutesOfTheEvent (startingTime,endingTime);
        
        return combineDuratingOfEvent(months,days,hours,minutes); 
    }
    
    
    private String combineDuratingOfEvent(int months, int days, int hours, int minutes) {
        String duration = new String(""); 
        boolean lessThanAnHour=false; 
        boolean lessThanADay=false; 
        boolean lessThanAMonth=false; 

        if (minutes != 0) { 
            if (minutes < 0){
                minutes = Math.floorMod(minutes, 60); 
                lessThanAnHour = true; 
            }
            duration = " " + minutes + "minute" + ((minutes == 1)?"":"s"); 
        }
        if (hours != 0){ 
            if (hours<0){ 
                hours = Math.floorMod(hours, 60);
                lessThanADay = true; 
            }
            if (lessThanAnHour) {
                hours = hours - 1; 
            }
            duration = " " + hours + "hours" + ((hours == 1)?"":"s" + duration); 
        }
        if (days != 0){ 
            if (days<0){ 
                days = Math.floorMod(days, 60);
                lessThanAMonth = true; 
            }
            if (lessThanADay) {
                days = days - 1; 
            }
            duration = days + "minute" + ((days == 1)?"":"s" + duration); 
        }
        if (months != 0){ 
            if (lessThanAMonth) {
                months = months - 1; 
            }
            duration = months + "minute" + ((months == 1)?"":"s" + duration ); 
        }
        duration = "Event starts and end at the same time"; 
        duration = duration.trim() + ".";
        return duration;

    }

    private static int minutesOfTheEvent(String startingTime, String endingTime) {
        int startMinute = Integer.parseInt(startingTime.substring(7,8));
        int endMinute = Integer.parseInt(endingTime.substring(7,8)); 
        return endMinute - startMinute;
    }
    
    private static int hoursOfTheEvent(String startingTime, String endingTime) {
        int startHours = Integer.parseInt(startingTime.substring(7,8));
        int endHours = Integer.parseInt(endingTime.substring(7,8)); 
        return endHours - startHours;
    }
    
    private static int daysOfTheEvent(String startingTime, String endingTime) {
        int startDate = Integer.parseInt(startingTime.substring(4,5));
        int endDate = Integer.parseInt(endingTime.substring(4,5)); 
        return endDate - startDate;
    }

    private static int monthsOfTheEvent(String startingTime, String endingTime) {
        String startMonth = startingTime.substring(0,2);
        String endMonth = endingTime.substring(0, 2); 
        int monthDifference = whatMonth(startMonth) - whatMonth(endMonth); 
        monthDifference = Math.floorMod(monthDifference, 12);
        return monthDifference;
    }

}