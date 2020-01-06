package Objects;


public class SubTodo {
    
    private int ID;
    private int todoID;
    private String subTodoTitle;
    private String subTodoNotes;
    private int subTodoCompleted;
    
    
    public SubTodo()
    {
        
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getTodoID() {
        return todoID;
    }

    public void setTodoID(int todoID) {
        this.todoID = todoID;
    }

    public String getSubTodoTitle() {
        return subTodoTitle;
    }

    public void setSubTodoTitle(String subTodoTitle) {
        this.subTodoTitle = subTodoTitle;
    }

    public String getSubTodoNotes() {
        return subTodoNotes;
    }

    public void setSubTodoNotes(String subTodoNotes) {
        this.subTodoNotes = subTodoNotes;
    }

    public int getSubTodoCompleted() {
        return subTodoCompleted;
    }

    public void setSubTodoCompleted(int subTodoCompleted) {
        this.subTodoCompleted = subTodoCompleted;
    }

}
