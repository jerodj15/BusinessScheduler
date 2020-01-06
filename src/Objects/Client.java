package Objects;


public class Client {
    private int clientID;
    private String firstName;
    private String lastName;
    private String phoneNum;
    private String emailAdd;
    private String physicalAdd;
    private String birthDate;
    private String clientNotes;
    
    public Client()
    {
        
    }
    
    public Client (String fName, String lName, String phone, String email, String address, String birth, String notes)
    {
        this.firstName = fName;
        this.lastName = lName;
        this.phoneNum = phone;
        this.emailAdd = email;
        this.physicalAdd = address;
        this.birthDate = birth;
        this.clientNotes = notes;
    }
    
    public void setClientID(int cliID)
    {
        this.clientID = cliID;
    }
    
    public int getClientID()
    {
        return this.clientID;
    }
    
    public void setFirstName(String first)
    {
        this.firstName = first;
    }
    
    public String getFirstName()
    {
        return this.firstName;
    }
    
    public void setLastName(String last)
    {
        this.lastName = last;
    }
    
    public String getLastName()
    {
        return this.lastName;
    }
    
    public void setPhoneNumber(String phone)
    {
        this.phoneNum = phone;
    }
    
    public String getPhoneNumber()
    {
        return this.phoneNum;
    }
    
    public void setEmailAddress(String email)
    {
        this.emailAdd = email;
    }
    
    public String getEmailAddress()
    {
        return this.emailAdd;
    }
    
    public void setAddress(String address)
    {
        this.physicalAdd = address;
    }
    
    public String getAddress()
    {
        return this.physicalAdd;
    }
    
    public void setClientNotes(String note)
    {
        this.clientNotes = note;
    }
    
    public String getClientNotes()
    {
        return this.clientNotes;
    }
    
    public void setBirthDate(String dob)
    {
        this.birthDate = dob;
    }
    
    public String getBirthDate()
    {
        return this.birthDate;
    }
    
}
