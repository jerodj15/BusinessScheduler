package UsefulMethods;

public class DateTimeSpinners {
    
    public static int [] getHours()
    {
        int[] result = new int[12];
        for (int i = 1; i < 13; i++)
        {
            result[i-1] = i;
        }
        return result;
    }
    
     public static int [] getMonthNumbers()
    {
        int[] result = new int[12];
        for (int i = 1; i < 13; i++)
        {
            result[i-1] = i;
        }
        return result;
    }
    
    public static int setHoursIn24Format(int hour, String ampmString)
    {
        int result = 0;
        if (ampmString.equalsIgnoreCase("am"))
        {
            result = hour;
        }
        else if((ampmString.equalsIgnoreCase("pm")) && (hour == 12))
        {
            result = hour;
        }
        else
        {
            result = hour + 12;
        }
        return result;
    }
    
    public static String[] getTimeOfDay()
    {
        String[] result = new String[2];
        result[0] = "AM";
        result[1] = "PM";
        return result;
    }
    
    public static int setNumberOfDays(int monthNumber, int yearNumber)
    {
        int result = 0;
        switch(monthNumber)
        {
            case 1:
                result = 31;
                break;
            case 2:
                if (yearNumber % 4 == 0)
                {
                    result = 29;
                }
                else
                {
                    result = 28;
                }
                break;
            case 3:
                result = 31;
                break;
            case 4:
                result = 30;
                break;
            case 5:
                result = 31;
                break;
            case 6:
                result = 30;
                break;
            case 7:
                result = 31;
                break;
            case 8:
                result = 31;
                break;
            case 9:
                result = 30;
                break;
            case 10:
                result = 31;
                break;
            case 11:
                result = 30;
                break;
            case 12:
                result = 31;
                break;
        }
        return result;
    }
    
}
