package Scenes;

import Database.DBInserts;
import Database.DBRequests;
import Main.BusinessScheduler;
import Objects.Client;
import UsefulMethods.CheckStrings;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class ClientSceneController {

    @FXML
    private GridPane clientInfoGV;
    @FXML
    private AnchorPane bigPane;
    @FXML
    private TabPane historyTP;
    @FXML
    private Tab historyTab;
    @FXML
    private Tab notesTab;
    @FXML
    private Label firstLabel;
    @FXML
    private Label lastLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label dobLabel;
    @FXML
    private TextField firstTF;
    @FXML
    private TextField lastTF;
    @FXML
    private TextField phoneTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField addressTF;
    @FXML
    private TextField dobTF;
    @FXML
    private Button editButton;
    @FXML
    private Button saveButton;
    @FXML
    private Label infoLabel;
    @FXML
    private Button backButton;
    @FXML
    private Label clientLabel;
    @FXML
    private TableView<?> historyTV;
    @FXML
    private TextArea noteTA;

    private BusinessScheduler myBusinessScheduler;
    private Client selectedClient;
    private boolean isEditable = true;
    
    public void setupClient(BusinessScheduler scheduler)
    {
        this.myBusinessScheduler = scheduler;
        setupTheme();
    }
    
    public void setupClient(BusinessScheduler scheduler, int clientID)
    {
        isEditable = false;
        System.out.println("Here is client details");
        this.myBusinessScheduler = scheduler;
        selectedClient = DBRequests.getClientByID(clientID);
        setupTextElements();
        greyOrWhiteTextElements();
        setupTheme();
    }
    
    public void setupTheme()
    {
        Themes.ThemeSetter.setMainPane(bigPane, DashboardSceneController.getThemeID());
        Themes.ThemeSetter.setGridPane(clientInfoGV, DashboardSceneController.getThemeID());
        Themes.ThemeSetter.setupTabPane(historyTP, DashboardSceneController.getThemeID());
    }
    
    public void backButtonClicked()
    {
       SceneGenerator.dashboardScreen(myBusinessScheduler);
    }
    
    public void editButtonClicked()
    {
        isEditable = true;
        setupTextElements();
        greyOrWhiteTextElements();
    }
    
    public void saveButtonClicked()
    {
        String[] requiredStrings = new String[4];
        String firstName = firstTF.getText();
        requiredStrings[0] = firstName;
        String lastName = lastTF.getText();
        requiredStrings[1] = lastName;
        String phoneNumber = phoneTF.getText();
        requiredStrings[2] = phoneNumber;
        String emailAddress = emailTF.getText();
        requiredStrings[3] = emailAddress;
        String physicalAddress = addressTF.getText();
        String birthDate = dobTF.getText();
        String clientNotes = noteTA.getText();
        boolean requiredFilled = CheckStrings.areEntriesEmpty(requiredStrings);
        if (requiredFilled)
        {
            if (physicalAddress.isEmpty())
            {
                physicalAddress = "";
            }
            if (birthDate.isEmpty())
            {
                birthDate = "";
            }
            if (clientNotes.isEmpty())
            {
                clientNotes = "";
            }
            Client newClient = new Client(firstName, lastName, 
                    phoneNumber, emailAddress, physicalAddress, birthDate, clientNotes);
            DBInserts.createNewClient(newClient);
            backButtonClicked();
        }
        
        else {
            Alert fieldsAlert = new Alert(Alert.AlertType.ERROR, "Please make sure all the required fields "
                    + "have information and try again.", ButtonType.OK);
            fieldsAlert.show();
        }
        
    }
    
    public void setupTextElements()
    {
        firstTF.setText(selectedClient.getFirstName());
        firstTF.setEditable(isEditable);
        lastTF.setText(selectedClient.getLastName());
        lastTF.setEditable(isEditable);
        phoneTF.setText(selectedClient.getPhoneNumber());
        phoneTF.setEditable(isEditable);
        emailTF.setText(selectedClient.getEmailAddress());
        emailTF.setEditable(isEditable);
        addressTF.setText(selectedClient.getAddress());
        addressTF.setEditable(isEditable);
        dobTF.setText(selectedClient.getBirthDate());
        dobTF.setEditable(isEditable);
        noteTA.setText(selectedClient.getClientNotes());
        noteTA.setEditable(isEditable);
    }
    
    public void greyOrWhiteTextElements ()
    {
        if (isEditable == false)
        {
            BackgroundFill unEditFill = new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY);
            Background unEditBG = new Background(unEditFill);
            firstTF.setBackground(unEditBG);
            lastTF.setBackground(unEditBG);
            phoneTF.setBackground(unEditBG);
            emailTF.setBackground(unEditBG);
            addressTF.setBackground(unEditBG);
            dobTF.setBackground(unEditBG);
            noteTA.setBackground(unEditBG);
        }
        else
        {
            BackgroundFill unEditFill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
            Background unEditBG = new Background(unEditFill);
            firstTF.setBackground(unEditBG);
            lastTF.setBackground(unEditBG);
            phoneTF.setBackground(unEditBG);
            emailTF.setBackground(unEditBG);
            addressTF.setBackground(unEditBG);
            dobTF.setBackground(unEditBG);
            noteTA.setBackground(unEditBG);
        }
    }
}
