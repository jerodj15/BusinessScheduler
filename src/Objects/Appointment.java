package Objects;


public class Appointment {

    private int appointmentID;
    private int userID;
    private int clientID;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String aptTitle;

    public Appointment()
    {
        
    }
    
    public Appointment(int usID, String title ,int cliID, String sDate, String eDate, String sTime, String eTime)
    {
        this.aptTitle = title;
        this.userID = usID;
        this.clientID = cliID;
        this.startDate = sDate;
        this.endDate = eDate;
        this.endTime = eTime;
        this.startTime = sTime;
    }
    
    public String getAptTitle()
    {
        return this.aptTitle;
    }
    
    public void setAptTitle(String title)
    {
        this.aptTitle = title;
    }
    
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    
    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    public String[] getAppointmentStrings()
    {
        String[] result = new String[7];
        result[0] = this.aptTitle;
        result[1] = String.valueOf(this.userID);
        result[2] = String.valueOf(this.clientID);
        result[3] = this.startDate;
        result[4] = this.startTime;
        result[5] = this.endDate;
        result[6] = this.endTime;
        
        return result;
    }
    
}
