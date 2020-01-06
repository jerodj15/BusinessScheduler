package Scenes;

import Database.DBDeletes;
import Database.DBEdits;
import Database.DBInserts;
import Database.DBRequests;
import Main.BusinessScheduler;
import Objects.Client;
import Objects.SubTodo;
import Objects.Todo;
import Objects.TodoFee;
import UsefulMethods.ComboBoxes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Jerod
 */
public class TodoSceneController {

    @FXML
    private Label toDoLabel;
    @FXML
    private Label titleLabel;
    @FXML
    private Label notesLabel;
    @FXML
    private Label createDateLabel;
    @FXML
    private Label completedLabel;
    @FXML
    private Label feeLabel;
    @FXML
    private TextArea noteTA;
    @FXML
    private TextField titleTF;
    @FXML
    private CheckBox completedCheckBox;
    @FXML
    private ListView feesLV;
    @FXML
    private TextField createDateTF;
    @FXML
    private Button newTodoFeeButton;
    @FXML
    private Label clientLabel;
    @FXML
    private Label subDoLabel;
    @FXML
    private Label windowedScheduleLabel;
    @FXML
    private TableColumn<SubTodo, String> subCompletedColumn;
    @FXML
    private TableColumn<SubTodo, Integer> subTitleColumn;
    @FXML
    private TableView<SubTodo> subTodoTV;
    @FXML
    private Button newClientButton;
    @FXML
    private Button newSubTodoButton;
    @FXML
    private Label betweenLabel;
    @FXML
    private Label windowStartLabel;
    @FXML
    private Label windowEndLabel;
    @FXML
    private Button windowScheduleButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button editButton;
    @FXML
    private Button backButton;
    @FXML
    private ComboBox clientCB;
    @FXML
    private TextField subTitleTF;
    @FXML
    private TextArea subNotesTA;
    @FXML
    private CheckBox subCheckBox;
    @FXML
    private TableView<TodoFee> todoFeeTV;
    @FXML
    private TableColumn<TodoFee, String> todoFeeTitleColumn;
    @FXML
    private TableColumn<TodoFee, String> todoFeeAmountColumn;

    
    private BusinessScheduler myBusinessScheduler;
    private Todo selectedTodo;
    public static int todoId;
    public boolean isEditable;
    public boolean  isDisbabled;
    private String clientFirst;
    private String clientLast;
    private int selectedClientIndex = 0;
    private Client selectedClient;
    private List<Client> allClients;
    private List<TodoFee> todoFeesList;
    private String comboBoxFiller = "";
    private static DateTimeFormatter fullDTF = DateTimeFormatter.ofPattern("MM" + "/" + "dd" + "/" + "yyyy");
    final ContextMenu contextMenu = new ContextMenu();
    
    public void setupTodoScene(BusinessScheduler scheduler, int todoID)
    {
        this.todoId = todoID;
        this.allClients = DBRequests.getAllClients();
        this.myBusinessScheduler = scheduler;
        clientCB.getItems().add(comboBoxFiller);
//        clientCB.setItems(FXCollections.observableArrayList(ComboBoxes.setupClientsComboBox(allClients))); 
        clientCB.getItems().addAll(ComboBoxes.setupClientsComboBox(allClients));
        subCompletedColumn.setCellValueFactory(new PropertyValueFactory<>("subTodoCompleted"));
        subTitleColumn.setCellValueFactory(new PropertyValueFactory<>("subTodoTitle"));
        subTodoTV.getItems().setAll(FXCollections.observableArrayList(SubtodoSceneController.subTodos));
        if (todoID == 0)
        {
            isDisbabled = false;
            isEditable = true;
            editButton.setVisible(false);
            editButton.setDisable(true);
            todoFeesList = TodoFeeSceneController.todoFees;
            selectedTodo = new Todo();
            selectedTodo.setTitle("");
            setupClientComboBox(0);
        }
        else 
        {
            setupClientComboBox(0);
            isDisbabled = true;
            isEditable = false;
            List<SubTodo> subTodos = DBRequests.getSubTodosByTodoID(todoID);
            todoFeesList = DBRequests.getTodoFeesByTodoID(todoID);
            this.selectedTodo = DBRequests.getTodoByTodoID(todoID);
            if (!(this.selectedTodo.getClientID() == 0))
            {
                this.selectedClient = DBRequests.getClientByID(this.selectedTodo.getClientID());
                System.out.println("Client is here and queer and their id is " + this.selectedTodo.getClientID());
                this.clientFirst = this.selectedClient.getFirstName();
                this.clientLast = this.selectedClient.getLastName();
                String fullName = clientLast + ", " + clientFirst;
                clientCB.getSelectionModel().select(fullName);
                
            }
            else 
            {
                clientCB.getSelectionModel().clearSelection();
            }
            
            titleTF.setText(selectedTodo.getTitle());
            titleTF.setEditable(isEditable);
            noteTA.setEditable(isEditable);
            noteTA.setText(selectedTodo.getNotes());
            int isCompleted = selectedTodo.getCompleted();
            System.out.println("isCompleted " + isCompleted);
            boolean todoCompleted = false;
            if (isCompleted == 1)
            {
                todoCompleted = true;
            }
            else
            {
                todoCompleted = false;
            }
            completedCheckBox.setSelected(todoCompleted);
            createDateTF.setEditable(isEditable);
            createDateTF.setText(selectedTodo.getDueDate());
            clientCB.setDisable(isDisbabled);
            newClientButton.setDisable(isDisbabled);
            newSubTodoButton.setDisable(isDisbabled);
            newTodoFeeButton.setDisable(isDisbabled);
            subTodoTV.getItems().setAll(FXCollections.observableArrayList(subTodos));
            subTodoTV.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() > 1)
                {
                    SubTodo subTodoItem = subTodoTV.getSelectionModel().getSelectedItem();
                    showSubTodoDetails(subTodoItem);
                }
            }
        });
            
        }
        setupAptFeesTableView();
        subTodoTV.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.isSecondaryButtonDown())
                    {
                        if (todoID == 0)
                        {
                            SubTodo subTodoItem = subTodoTV.getSelectionModel().getSelectedItem();
                            rightClickDeleteSubTodo(subTodoItem);
                        }
                        else
                        {
                            int subTodoItem = subTodoTV.getSelectionModel().getSelectedItem().getID();
                            rightClickDeleteSubTodo(subTodoItem);
                        }
                    }
                }
            });
        todoFeeTV.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isSecondaryButtonDown())
                    {
                        if (todoID == 0)
                        {
                            TodoFee todoFeeItem = todoFeeTV.getSelectionModel().getSelectedItem();
                            rightClickDeleteTodoFee(todoFeeItem);
                        }
                        else
                        {
                            int todoFeeID = todoFeeTV.getSelectionModel().getSelectedItem().getID();
                            rightClickDeleteTodoFee(todoFeeID);
                        }
                    }
            }
        });
    }
    
    public void setupAptFeesTableView()
    {
        todoFeeTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        todoFeeAmountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        if (todoId == 0)
        {
            todoFeeTV.getItems().setAll(TodoFeeSceneController.todoFees);
        }
        else
        {
            todoFeeTV.getItems().setAll(todoFeesList);
        }
    }
    
    public void newTodoFeeClicked()
    {
        SceneGenerator.showTodoFeeScene(selectedTodo);
    }
    
    public void editButtonClicked()
    {
            isDisbabled = false;
            isEditable = true;
            List<SubTodo> subTodos = DBRequests.getSubTodosByTodoID(todoId);
            selectedTodo = DBRequests.getTodoByTodoID(todoId);
            titleTF.setText(selectedTodo.getTitle());
            titleTF.setEditable(isEditable);
            noteTA.setEditable(isEditable);
            noteTA.setText(selectedTodo.getNotes());
            createDateTF.setEditable(isEditable);
            createDateTF.setText(selectedTodo.getDueDate());
            clientCB.setDisable(isDisbabled);
            newClientButton.setDisable(isDisbabled);
            newSubTodoButton.setDisable(isDisbabled);
            newTodoFeeButton.setDisable(isDisbabled);
            subTodoTV.getItems().setAll(FXCollections.observableArrayList(subTodos));
    }
    
    
    // Method for clicking the save button
    public void saveButtonClicked()
    {
        // Gather all the variabled to store in the todo item
        String todoTitle = titleTF.getText();
        String todoNotes = noteTA.getText();
        Todo newTodo = new Todo();
        boolean todoCompleted = completedCheckBox.selectedProperty().getValue();
        int clientID;
        if (this.selectedClientIndex != 0)
        {
            clientID = this.allClients.get(selectedClientIndex-1).getClientID();
        }
        else 
        {
            clientID = 0;
        }
        if (clientID != 0)
        {
            newTodo.setClientID(clientID);
        }
        int todoCompletetedInt = todoCompleted ? 1 : 0;
        String todoCreateDate = fullDTF.format(LocalDate.now());
        String todoDueDate = windowEndLabel.getText();
        
        if (todoTitle.isEmpty())
        {
            Alert emptyTitle = new Alert(Alert.AlertType.ERROR, "A title must be present in order to save this item", ButtonType.OK);
            emptyTitle.show();
        }
        else 
        {
            newTodo.setUserID(DashboardSceneController.getUserID());
                newTodo.setTitle(todoTitle);
                newTodo.setNotes(todoNotes);
                newTodo.setDueDate(todoDueDate);
                newTodo.setCreateDate(todoCreateDate);
                newTodo.setCompleted(todoCompletetedInt);
            if (todoId == 0)
            {
                if (subTodoTV.getItems().isEmpty())
                {
                DBInserts.createNewTodo(newTodo);
                SceneGenerator.dashboardScreen(myBusinessScheduler);
                }
                else
                {
                DBInserts.createNewTodo(newTodo);
                Todo getTodo = DBRequests.getTodoByTitleAndUserID(newTodo.getUserID(), newTodo.getTitle());
                if (SubtodoSceneController.subTodos.size() > 0)
                {
                    List<SubTodo> subToSave = new ArrayList<>();
                    for (int i = 0; i< SubtodoSceneController.subTodos.size(); i++)
                    {
                        SubTodo newSubTodo = new SubTodo();
                        newSubTodo.setTodoID(getTodo.getID());
                        newSubTodo.setSubTodoTitle(SubtodoSceneController.subTodos.get(i).getSubTodoTitle());
                        newSubTodo.setSubTodoNotes(SubtodoSceneController.subTodos.get(i).getSubTodoNotes());
                        newSubTodo.setSubTodoCompleted(SubtodoSceneController.subTodos.get(i).getSubTodoCompleted());
                        subToSave.add(newSubTodo);
                    }
                    DBInserts.createMultipleSubTodos(subToSave);
                }
                SceneGenerator.dashboardScreen(myBusinessScheduler);
                }
            }
            else 
            {
                newTodo.setID(todoId);
                newTodo.setUserID(DashboardSceneController.getUserID());
                DBEdits.editTodo(newTodo);
                SceneGenerator.dashboardScreen(myBusinessScheduler);
            }
        }
    }

    // Method for the back button
    public void backButtonPressed()
    {
        SceneGenerator.dashboardScreen(myBusinessScheduler);
    }
    
    public void setupClientComboBox(int todoID)
    {
        clientCB.setOnAction((event) -> {
           
           if (clientCB.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase(""))
           {
               this.selectedClientIndex = 0;
           }
           else
           {
               String clientName = (String) clientCB.getSelectionModel().getSelectedItem();
               System.out.println(clientName);
            String[] client = clientName.split(",");
            System.out.println(Arrays.toString(client));
            this.clientFirst = client[1];
            this.clientLast = client[0];
            this.selectedClientIndex = clientCB.getSelectionModel().getSelectedIndex();
            System.out.println("Combobox moved and the selection is now " + selectedClientIndex );
           }
            

        });
        
    }

    // Method for clicking the add sub todo item
    
    public void addSubTodoClicked()
    {
        SceneGenerator.addSubTodoClicked();
    }
    
    public void showSubTodoDetails(SubTodo userIn)
    {
        SceneGenerator.showSubTodoDetails(userIn);
    }
    
    public void rightClickDeleteSubTodo(int userIn)
    {
        MenuItem deleteItem = new MenuItem("Delete");
                    contextMenu.getItems().setAll(deleteItem);
                    subTodoTV.setContextMenu(contextMenu);
                    
                    deleteItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                        public void handle(ActionEvent event) {
                            DBDeletes.deleteSubTodoByID(userIn);
                            SceneGenerator.newTodoScreen();
                        }
                    });
    }
    
    public void rightClickDeleteSubTodo(SubTodo userIn)
    {
        MenuItem deleteItem = new MenuItem("Delete");
                    contextMenu.getItems().setAll(deleteItem);
                    subTodoTV.setContextMenu(contextMenu);
                    
                    deleteItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                        public void handle(ActionEvent event) {
                            SubtodoSceneController.subTodos.remove(userIn);
                            SceneGenerator.newTodoScreen();
                        }
                    });
    }
    
    public void rightClickDeleteTodoFee(int userIn)
    {
        MenuItem deleteItem = new MenuItem("Delete");
        contextMenu.getItems().setAll(deleteItem);
        todoFeeTV.setContextMenu(contextMenu);
        
        deleteItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBDeletes.deleteTodoFeeByID(userIn);
                SceneGenerator.newTodoScreen();
            }
        });
    }
    
    public void rightClickDeleteTodoFee(TodoFee userIn)
    {
        MenuItem deleteItem = new MenuItem("Delete");
        contextMenu.getItems().setAll(deleteItem);
        todoFeeTV.setContextMenu(contextMenu);
        
        deleteItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TodoFeeSceneController.todoFees.remove(userIn);
                SceneGenerator.newTodoScreen();
            }
        });
    }
}
