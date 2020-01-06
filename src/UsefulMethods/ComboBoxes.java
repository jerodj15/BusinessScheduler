package UsefulMethods;

import Objects.Client;
import java.util.ArrayList;
import java.util.List;

public class ComboBoxes {
    
    private static List<Client> clientList;
    private static List<String> result;
    
    public static List<String> setupClientsComboBox(List<Client> userIn)
    {
        clientList = userIn;
        result = new ArrayList<>();
        for (int i = 0; i < userIn.size(); i++)
        {
            String firstName = userIn.get(i).getFirstName();
            String lastName = userIn.get(i).getLastName();
            String fullName = lastName + ", " + firstName;
            result.add(fullName);
        }
        
        return result;
    }
    
    public static String getClientSelected(String firstName, String lastName)
    {
        String returnThis = "";
        String compareString = lastName + ", " + firstName;
        for (int i =0; i < result.size(); i++)
        {
            if (result.get(i).equals(compareString))
            {
                returnThis = result.get(i);
            }
        }
        return returnThis;
    }
}
