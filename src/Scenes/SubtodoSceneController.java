package Scenes;

import Database.DBEdits;
import Database.DBInserts;
import Database.DBRequests;
import Main.BusinessScheduler;
import Objects.SubTodo;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SubtodoSceneController {

    @FXML
    private Label subDoLabel;
    @FXML
    private Label subTitleLabel;
    @FXML
    private Label subNotesLabel;
    @FXML
    private Label subCompleteLabel;
    @FXML
    private TextArea subNotesTA;
    @FXML
    private TextField subTitleTF;
    @FXML
    private CheckBox subCheckBox;
    @FXML
    private Button subCancelButton;
    @FXML
    private Button subSaveButton;
    @FXML
    private Button subCloseButton;


    private BusinessScheduler myBusinessScheduler;
    public static List<SubTodo> subTodos = new ArrayList<>();
    public static int todoId;
    public static SubTodo userSub;
    
    public void setupSubTodoScene(BusinessScheduler scheduler, int todoID)
    {
        todoId = todoID;
        userSub = new SubTodo();
        userSub.setSubTodoTitle("");
        this.myBusinessScheduler = scheduler;
        this.myBusinessScheduler.secondStage.setX(myBusinessScheduler.primaryStage.getX() + myBusinessScheduler.primaryStage.getWidth() - 205);
        this.myBusinessScheduler.secondStage.setY(myBusinessScheduler.primaryStage.getY());
        this.myBusinessScheduler.secondStage.setResizable(false);
        if (todoId == 0)
        {
            
        }
        else
        {
            subTodos = DBRequests.getSubTodosByTodoID(todoId);
        }
        
    }
    
    public void setupSubTodoScene(BusinessScheduler scheduler, SubTodo userSubTodo)
    {
        this.myBusinessScheduler = scheduler;
        this.myBusinessScheduler.secondStage.setX(myBusinessScheduler.primaryStage.getX() + myBusinessScheduler.primaryStage.getWidth() - 205);
        this.myBusinessScheduler.secondStage.setY(myBusinessScheduler.primaryStage.getY());
        this.myBusinessScheduler.secondStage.setResizable(false);
        userSub = userSubTodo;
        todoId = userSubTodo.getTodoID();
        subNotesTA.setText(userSub.getSubTodoNotes());
        subTitleTF.setText(userSub.getSubTodoTitle());
        int completion = userSub.getSubTodoCompleted();
        boolean isCompleted = false;
        if (completion == 1)
        {
            isCompleted = true;
        }
        else
        {
            isCompleted = false;
        }
        subCheckBox.setSelected(isCompleted);
    }
    
    public void subCancelClicked()
    {
        BusinessScheduler.secondStage.close();
        SceneGenerator.hideClicked();
    }
    
    public void subSaveClicked()
    {
        if (userSub.getSubTodoTitle().isEmpty())
        {
            if (todoId == 0)
            {
                SubTodo userSubTodo = new SubTodo();
                userSubTodo.setSubTodoTitle(subTitleTF.getText());
                userSubTodo.setSubTodoNotes(subNotesTA.getText());
                boolean isCompleted = subCheckBox.selectedProperty().getValue();
                int subTodoCompletetedInt = isCompleted ? 1 : 0;
                userSubTodo.setSubTodoCompleted(subTodoCompletetedInt);
                subTodos.add(userSubTodo);
                System.out.println("Todo note xisted");
            
            }
            else 
            {
                SubTodo userSubTodo = new SubTodo();
                userSubTodo.setSubTodoTitle(subTitleTF.getText());
                userSubTodo.setSubTodoNotes(subNotesTA.getText());
                boolean isCompleted = subCheckBox.selectedProperty().getValue();
                int subTodoCompletetedInt = isCompleted ? 1 : 0;
                userSubTodo.setTodoID(todoId);
                userSubTodo.setSubTodoCompleted(subTodoCompletetedInt);
                subTodos.add(userSubTodo);
                DBInserts.appendNewSubTodo(userSubTodo);
                System.out.println("Todo existed");
            }
        }
        else 
        {
                SubTodo userSubTodo = new SubTodo();
                userSubTodo.setSubTodoTitle(subTitleTF.getText());
                userSubTodo.setSubTodoNotes(subNotesTA.getText());
                boolean isCompleted = subCheckBox.selectedProperty().getValue();
                int subTodoCompletetedInt = isCompleted ? 1 : 0;
                userSubTodo.setTodoID(todoId);
                userSubTodo.setID(userSub.getID());
                userSubTodo.setSubTodoCompleted(subTodoCompletetedInt);
                DBEdits.editSubTodo(userSubTodo);
        }
        
        System.out.println(subTodos.toString());
        subCancelClicked();
        SceneGenerator.showTodoDetails(todoId);
//        SceneGenerator.newTodoScreen();
        
    }
        
}
