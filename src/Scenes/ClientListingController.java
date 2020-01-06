package Scenes;

import Database.DBRequests;
import Main.BusinessScheduler;
import Objects.Client;
import UsefulMethods.ComboBoxes;

import java.util.List;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;


public class ClientListingController {

    @FXML
    private Label clientListing;
    @FXML
    private Button backButton;
    @FXML
    private ListView<String> clientLV;
    @FXML
    private TextField searchTF;
    @FXML
    private Button searchButton;
    @FXML
    private Button saveButton;
    @FXML
    private GridPane clientInfoGV;
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
    private TextArea notesTA;
    @FXML
    private Button editButton;
    @FXML
    private Button selectButton;
    @FXML
    private Button newClientButton;
    
    private BusinessScheduler myBusinessScheduler;
    private List<Client> allClients;
    private List<String> clientStrings;
    private Client selectedClient;
    private boolean isEditable;

    public void setupClientListing(BusinessScheduler scheduler)
    {
        myBusinessScheduler = scheduler;
        allClients = DBRequests.getAllClients();
        setupClientList();
        isEditable = false;
        setEdit();
    }
    
    public void selectClientClicked()
    {
        populateInformation();
        isEditable = false;
    }
    
    public void editButtonClicked()
    {
        isEditable = true;
        setEdit();
    }
    
    public void backButtonPressed()
    {
        SceneGenerator.dashboardScreen(myBusinessScheduler);
    }
    
    public void newClientButtonPressed()
    {
        SceneGenerator.newClientScreen();
    }
    
    public void setupClientList()
    {
        clientStrings = ComboBoxes.setupClientsComboBox(allClients);
        clientLV.getItems().setAll(clientStrings);
        clientLV.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() > 1)
                {
                    isEditable = false;
                    populateInformation();
                }
            }
        });
    }
    
    public void populateInformation()
    {
        int listSelectedIndex = clientLV.getSelectionModel().getSelectedIndex();
        selectedClient = allClients.get(listSelectedIndex);
        firstTF.setText(selectedClient.getFirstName());
        lastTF.setText(selectedClient.getLastName());
        phoneTF.setText(selectedClient.getPhoneNumber());
        emailTF.setText(selectedClient.getEmailAddress());
        addressTF.setText(selectedClient.getAddress());
        dobTF.setText(selectedClient.getBirthDate());
        notesTA.setText(selectedClient.getClientNotes());
        greyOrWhiteTextElements();
        setEdit();
    }
    
    public void setEdit()
    {
        firstTF.setEditable(isEditable);
        lastTF.setEditable(isEditable);
        phoneTF.setEditable(isEditable);
        emailTF.setEditable(isEditable);
        addressTF.setEditable(isEditable);
        dobTF.setEditable(isEditable);
        notesTA.setEditable(isEditable);
        greyOrWhiteTextElements();
        
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
            notesTA.setBackground(unEditBG);
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
            notesTA.setBackground(unEditBG);
        }
    }
    
}
