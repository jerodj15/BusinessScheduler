package UsefulMethods;

import Objects.Appointment;
import Objects.User;


public class CheckEmptyObject {
    
    public static boolean checkAppointment(Appointment userIn)
    {
        boolean result = true;
        
        String[] appointmentFields = userIn.getAppointmentStrings();
        for (int i = 0; i < appointmentFields.length; i++)
        {
            if (appointmentFields[i].isEmpty())
            {
                result = false;
                break;
            }
        }
        
        return result;
    }
    
    public static boolean checkUser(User userIn)
    {
        boolean result = true;
        
        String[] userFields = userIn.getUserStrings();
        for (int i = 0; i < userFields.length; i++)
        {
            if (userFields[i].isEmpty())
            {
                result = false;
                break;
            }
        }
        return result;
    }
    
}
