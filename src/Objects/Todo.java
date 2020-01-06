package Objects;


public class Todo {

    private int ID;
    private int userID;
    private String title;
    private String dueDate;
    private String notes;
    private int subdoID;
    private int completed;
    private String createDate;
    private int clientID;
    
    public Todo()
    {
        
    }
    
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    
    public void setID(int id)
    {
        this.ID = id;
    }
    
    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getSubdoID() {
        return subdoID;
    }

    public void setSubdoID(int subdoID) {
        this.subdoID = subdoID;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }
    
    
    
}
