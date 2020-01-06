package Objects;


public class User {

        private int userID;
    private String userName;
    private String passwordString;
    private String emailAddress;
    private String createDate;
    private String firstName;
    private String lastName;
    
    public User()
    {
        
    }
    
    public int getUserID() {
        return userID;
    }
    
    public void setUSerID(int userIN)
    {
        this.userID = userIN;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordString() {
        return passwordString;
    }

    public void setPasswordString(String passwordString) {
        this.passwordString = passwordString;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String[] getUserStrings()
    {
        String[] result = new String[5];
        result[0] = this.firstName;
        result[1] = this.lastName;
        result[2] = this.emailAddress;
        result[3] = this.userName;
        result[4] = this.passwordString;

        
        return result;
    }
    
    
}
