package UsefulMethods;

public class CheckStrings {
    
    public static boolean areEntriesEmpty(String[] userStrings)
    {
        boolean result = true;
        for (int i = 0; i < userStrings.length; i++)
        {
            if (userStrings[i].isEmpty())
            {
                result = false;
                break;
            }
        }
        return result;
    }
    
    public static String fixDateTimeString(String userIn)
    {
        String userInput = userIn;
        String result;
        if (userIn.length() < 2)
        {
            result = "0" + userInput;
        }
        else
        {
            result = userInput;
        }
        return result;
    }
      
    public static String convertTimeTo24(String userHour, String userAMPM)
    {
        String result;
        if ((userAMPM.equalsIgnoreCase("pm")) && (Integer.parseInt(userHour) < 12)) {
                int endingHour = Integer.parseInt(userHour) + 12;
                result = String.valueOf(endingHour);
            }
        else
        {
            result = userHour;
        }
        return result;
    }
}
