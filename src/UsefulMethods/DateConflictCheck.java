package UsefulMethods;

import Database.DBRequests;
import Objects.Appointment;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DateConflictCheck {
    
    private static boolean datesValid = false;
    private static boolean timesValid = false;
    private static boolean timesChrono = false;
    private static LocalTime userTime1;
    private static LocalTime userTime2;
    private static LocalDate userDate1;
    private static LocalDate userDate2;
    private static DateTimeFormatter fullDTF = DateTimeFormatter.ofPattern("MM" + "/" + "dd" + "/" + "yyyy");
    private static DateTimeFormatter timeDTF = DateTimeFormatter.ISO_LOCAL_TIME;
    private static int userId; 
    private static List<Appointment> userAppointments;
    
    public static boolean isAppointmentValid(String date1, String date2, String time1, String time2, int userID)
    {
        boolean result;
        userId = userID;
        // See what is being passed through
        System.out.println("First date = " + date1);
        System.out.println("Second date = " + date2);
        System.out.println("First Time = " + time1);
        System.out.println("Second Time = " + time2);
        userAppointments = DBRequests.getAppointmentsByUserID(userId);
        userDate1 = LocalDate.parse(date1, fullDTF);
        userDate2 = LocalDate.parse(date2, fullDTF);
        userTime1 = LocalTime.parse(time1 + ":00", timeDTF);
        userTime2 = LocalTime.parse(time2 + ":00", timeDTF);
        datesValid = checkDateConflicts(userDate1, userDate2);
        timesValid = checkTimeConflicts(userTime1, userTime2);
        timesChrono = checkTimeChronology(userTime1, userTime2);
        if ((datesValid == true) && (timesValid == true) && (timesChrono == true))
        {
            result = true;
        }
        else
        {
            result = false;
        }
        return result;
    }
    
    // CHECKING THE DATES++++++++++++++++++++++++++++++++++++++++++++++++++
    
    private static boolean checkDateConflicts(LocalDate date1, LocalDate date2)
    {
        boolean result;
        boolean isChrono = areDatesChronologic(date1, date2);
        if (isChrono == true)
        {
            result = true;
        }
        else
        {
            result = false;
        }
        System.out.println("Result for date conflicts = " + result);
        return result;
    }
    
    //              Checking for chronology
    private static boolean areDatesChronologic(LocalDate date1, LocalDate date2)
    {
        boolean result;
        if (date1.isBefore(date2) || date1.isEqual(date2))
        {
            result = true;
        }
        else
        {
            result = false;
        }
        System.out.println("Result for date chronology = " + result);
        return result;
    }

    
    
    // CHECKING THE TIMES+++++++++++++++++++++++++++++++++++++++++++++++++++
    
    private static boolean checkTimeConflicts(LocalTime time1, LocalTime time2)
    {
        boolean result = true;
        for (int i = 0; i < userAppointments.size(); i++)
        {
            LocalTime compareStart = LocalTime.parse(userAppointments.get(i).getStartTime(), timeDTF);
            LocalTime compareEnd = LocalTime.parse(userAppointments.get(i).getEndTime(), timeDTF);
            // Sandwiched time
            if ((time1.isAfter(compareStart)) && (time2.isBefore(compareEnd)))
            {
                result = false;
                break;
            }
            else if ((time1.isAfter(compareStart)) && (time2.isBefore(compareEnd)))
            {
                result = false;
                break;
            }
            else if ((time2.isAfter(compareStart)) && (time2.isBefore(compareEnd)))
            {
                result = false;
                break;
            }
            else if ((time1.isBefore(compareStart)) && (time2.isAfter(compareEnd)))
            {
                result = false;
                break;
            }
        }
        System.out.println("Result for time conflicts = " + result);
        return result;
    }
    
    private static boolean checkTimeChronology(LocalTime time1, LocalTime time2)
    {
        boolean result = true;
        if (time1.isAfter(time2))
        {
            result = false;
        }
        if (time2.isBefore(time1))
        {
            result = false;
        }
        if (time1.equals(time2))
        {
            result = false;
        }
        System.out.println("Result for time chronology = " + result);
        return result;
    }

}
