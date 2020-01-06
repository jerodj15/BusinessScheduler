package Scenes;

import Database.DBEdits;
import Database.DBInserts;
import Main.BusinessScheduler;
import Objects.TodoFee;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TodoFeeSceneController{

    @FXML
    private Label todoFeeLabel;
    @FXML
    private Label titleLabel;
    @FXML
    private TextField titleTF;
    @FXML
    private Label amountLabel;
    @FXML
    private Label dollarSign;
    @FXML
    private TextField amountTF;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
    
    private BusinessScheduler myBusinessScheduler;
    public static List<TodoFee> todoFees = new ArrayList<>();
    public static int todoId;
    public static TodoFee selectedTodoFee = new TodoFee();
    
    public void setupTodoFeeScene(BusinessScheduler scheduler, int todoID)
    {
        this.myBusinessScheduler = scheduler;
        todoId = todoID;
        this.myBusinessScheduler.secondStage.setX(myBusinessScheduler.primaryStage.getX() + myBusinessScheduler.primaryStage.getWidth() - 205);
        this.myBusinessScheduler.secondStage.setY(myBusinessScheduler.primaryStage.getY());
        this.myBusinessScheduler.secondStage.setResizable(false);
        selectedTodoFee.setTitle("");
    }
    
    public void setupTodoFeeScene(BusinessScheduler scheduler, TodoFee todoFee)
    {
        this.myBusinessScheduler = scheduler;
        selectedTodoFee = todoFee;
    }

    public void cancelButtonClicked() 
    {
        SceneGenerator.showTodoDetails(todoId);
        SceneGenerator.hideClicked();
    }

    public void saveButtonClicked() 
    {
        String todoFeeTitle = titleTF.getText();
        String todoFeeAmount = amountTF.getText();
        if (todoFeeTitle.isEmpty() || todoFeeAmount.isEmpty())
        {
            Alert emptyAlert = new Alert(Alert.AlertType.ERROR, "Please make sure all fields are completely filled out and try again.", ButtonType.OK);
            emptyAlert.show();
        }
        else
        {
            if (todoId == 0)
            {
                TodoFee addMe = new TodoFee();
                addMe.setTitle(todoFeeTitle);
                addMe.setAmount(todoFeeAmount);
                todoFees.add(addMe);
            }
            else
            {
                if (selectedTodoFee.getTitle().isEmpty())
                {
                    TodoFee addMe = new TodoFee();
                    addMe.setTitle(todoFeeTitle);
                    addMe.setAmount(todoFeeAmount);
                    addMe.setTodoID(todoId);
                    DBInserts.createTodoFee(addMe);
                }
                else
                {
                    TodoFee addMe = new TodoFee();
                    addMe.setTitle(todoFeeTitle);
                    addMe.setAmount(todoFeeAmount);
                    addMe.setTodoID(todoId);
                    addMe.setID(selectedTodoFee.getID());
                    DBEdits.editTodoFee(addMe);
                }
            }
            SceneGenerator.showTodoDetails(todoId);
            SceneGenerator.hideClicked();
        }
    }
}
