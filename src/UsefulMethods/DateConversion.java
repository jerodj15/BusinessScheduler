package UsefulMethods;

import Objects.Appointment;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DateConversion {
    
    private static LocalDate currentDate = LocalDate.now();
    private static DateTimeFormatter fullDTF = DateTimeFormatter.ofPattern("MM" + "/" + "dd" + "/" + "yyyy");
    private static DateTimeFormatter yearDTF = DateTimeFormatter.ofPattern("yyyy");
    private static DateTimeFormatter monthDTF = DateTimeFormatter.ofPattern("MM");
    private static DateTimeFormatter dayDTF = DateTimeFormatter.ofPattern("dd");
    
    public static List<Appointment> getCurrentMonthsAppointments(List<Appointment> userAppointments)
    {
        List<Appointment> result = new ArrayList<>();
        for (int i = 0; i < userAppointments.size(); i++)
        {
            String aptStartDate = userAppointments.get(i).getStartDate();
            LocalDate checkDate = LocalDate.parse(aptStartDate, fullDTF);
            
            if (currentDate.getMonthValue() == Integer.parseInt(monthDTF.format(checkDate)))
            {
                result.add(userAppointments.get(i));
            }
        }
        return result;
        
    }
    
    public static List<Appointment> getTodaysAppointments(List<Appointment> userAppointments)
    {
        List<Appointment> result = new ArrayList<>();
        LocalDate todayDate = currentDate;
        for (int i = 0; i < userAppointments.size(); i++)
        {
            String aptStartDate = userAppointments.get(i).getStartDate();
            LocalDate checkDate = LocalDate.parse(aptStartDate, fullDTF);
            if (todayDate.getDayOfYear() == checkDate.getDayOfYear())
            {
                result.add(userAppointments.get(i));
            }
        }
        return result;
    }
    
    public static List<Appointment> getCurrentWeeksAppointments(List<Appointment> userAppointments)
    {
        LocalDate beginningOfTheWeek = getBegginingWeekDate();
        LocalDate endOfTheWeek = getEndingWeekDate();
        List<Appointment> result = new ArrayList<>();
        for (int i = 0; i < userAppointments.size(); i++)
        {
            String aptStartDate = userAppointments.get(i).getStartDate();
            LocalDate checkDate = LocalDate.parse(aptStartDate, fullDTF);
            if ((checkDate.isBefore(endOfTheWeek)) && (checkDate.isAfter(beginningOfTheWeek)))
            {
               result.add(userAppointments.get(i));
            }
            
        }
        return result;
    }
    
    public static LocalDate getBegginingWeekDate()
    {
        DayOfWeek sundayDay = DayOfWeek.SUNDAY;
        LocalDate dateToReturn = LocalDate.now();
        while (!(dateToReturn.getDayOfWeek().equals(sundayDay)))
        {
            dateToReturn = dateToReturn.minusDays(1);
        }
        return dateToReturn;
    }
    
    public static LocalDate getEndingWeekDate()
    {
        DayOfWeek saturdayDay = DayOfWeek.SATURDAY;
        LocalDate dateToReturn = LocalDate.now();
        while (!(dateToReturn.getDayOfWeek().equals(saturdayDay)))
        {
            dateToReturn = dateToReturn.plusDays(1);
        }
        return dateToReturn;
    }
    
}
