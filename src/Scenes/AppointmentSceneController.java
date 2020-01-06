package Scenes;

import Database.DBDeletes;
import Database.DBEdits;
import Database.DBInserts;
import Database.DBRequests;
import Main.BusinessScheduler;
import Objects.Appointment;
import Objects.ApptFee;
import Objects.Client;
import UsefulMethods.CheckEmptyObject;
import UsefulMethods.CheckStrings;
import UsefulMethods.DateConflictCheck;
import UsefulMethods.DateTimeSpinners;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


public class AppointmentSceneController {

    @FXML
    private GridPane apptGV;
    @FXML
    private Label clientLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label locationLabel;
    @FXML
    private Label notesLabel;
    @FXML
    private TextField phoneTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField locationTF;
    @FXML
    private TextField titleTF;
    @FXML
    private TextArea notesTA;
    @FXML
    private ComboBox clientCB;
    @FXML
    private Label appointmentLabel;
    @FXML
    private Label startDateLabel;
    @FXML
    private Label startTimeLabel;
    @FXML
    private Label endDateLabel;
    @FXML
    private Label endTimeLabel;
    @FXML
    private Label feesLabel;
    @FXML
    private Label monthStart;
    @FXML
    private Spinner startMonthSpin;
    @FXML
    private Spinner startDaySpin;
    @FXML
    private Spinner startYearSpin;
    @FXML
    private Label monthStart1;
    @FXML
    private Spinner endMonthSpin;
    @FXML
    private Spinner endDaySpin;
    @FXML
    private Spinner endYearSpin;
    @FXML
    private Label hourStart;
    @FXML
    private Label minuteStart;
    @FXML
    private Label ampmStart;
    @FXML
    private ComboBox startTimeAMPMCB;
    @FXML
    private Spinner startMinuteSpin;
    @FXML
    private Spinner startHourSpinner;
    @FXML
    private Label hourStart1;
    @FXML
    private Label minuteStart1;
    @FXML
    private Label ampmStart1;
    @FXML
    private ComboBox endTimeAMPMCB;
    @FXML
    private Spinner endMinuteSpin;
    @FXML
    private Spinner endHourSpin;
    @FXML
    private Button saveButton;
    @FXML
    private Button editButton;
    @FXML
    private Button backButton;
    @FXML
    private TableView<ApptFee> feeTV;
    @FXML
    private TableColumn<ApptFee, String> feeTitleColumn;
    @FXML
    private TableColumn<ApptFee, String> feeAmountColumn;
    @FXML
    private AnchorPane bigPane;
    @FXML
    private GridPane calendarGV;
    @FXML 
    private GridPane endGP;
    @FXML
    private GridPane startTimeGP;
    @FXML
    private GridPane startTimeGP1;
    @FXML
    private GridPane feeGP;
    @FXML
    private GridPane anotherGP;

    private BusinessScheduler myBusinessScheduler;
    private Client passedClient;
    private Appointment selectedAppointment;
    private String clientFirst;
    private String clientLast;
    private int selectedClientIndex;
    private List<Client> allClients;
    public Date dateNow = new Date();
    private static DateTimeFormatter fullDTF = DateTimeFormatter.ofPattern("MM" + "/" + "dd" + "/" + "yyyy");
    private int useSetup;
    private boolean isUneditable;
    final ContextMenu contextMenu = new ContextMenu();
    
    public void setupAppointmentScene(BusinessScheduler scheduler, int userSetup)
    {
        bigPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER)
                {
                    saveButtonClicked();
                }
            }
        });
        isUneditable = true;
        this.useSetup = userSetup;
        this.myBusinessScheduler = scheduler;
        this.allClients = DBRequests.getAllClients();
            List<String> clientNames = new ArrayList<>();
            for (Client clientName : allClients)
            {
                String firstName = clientName.getFirstName();
                String lastName = clientName.getLastName();
                String fullName = lastName + ", " + firstName;
                clientNames.add(fullName);
            }
            Themes.ThemeSetter.setMainPane(bigPane, DashboardSceneController.getThemeID());
            Themes.ThemeSetter.setGridPane(apptGV, DashboardSceneController.getThemeID());
            Themes.ThemeSetter.setGridPane(calendarGV, DashboardSceneController.getThemeID());
            Themes.ThemeSetter.setGridPane(endGP, DashboardSceneController.getThemeID());
            Themes.ThemeSetter.setGridPane(startTimeGP, DashboardSceneController.getThemeID());
            Themes.ThemeSetter.setGridPane(startTimeGP1, DashboardSceneController.getThemeID());
            Themes.ThemeSetter.setGridPane(feeGP, DashboardSceneController.getThemeID());
            Themes.ThemeSetter.setGridPane(anotherGP, DashboardSceneController.getThemeID());
        clientCB.setItems(FXCollections.observableArrayList(clientNames)); 
        feeTV.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() > 1)
                {
                    ApptFee selectedFee = feeTV.getSelectionModel().getSelectedItem();
                    SceneGenerator.appointmentFeeScreen(selectedFee, userSetup);                    
                }
            }
        });
        feeTV.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               int feeID = feeTV.getSelectionModel().getSelectedItem().getId();
                    if (useSetup == 0)
                    {
                        
                    }
                    else
                    {
                        contextMenu.show(feeTV, event.getSceneX(), event.getSceneY());
                        rightClickDeleteFee(feeID);
                    }
            }
        });
        if (userSetup == 0)
        {
            setupClientComboBox(0);
            setupStartDateArea(0);
            setupStartTimeArea(0);
            setupEndTimeArea(0);
            setupEndDateArea(0);
            editButton.setVisible(false);
            setupApptFeesTableView(userSetup);
            
        }
        else 
        {
            setEditable();
            this.selectedAppointment = DBRequests.getAppointmentByID(userSetup);
            this.passedClient = DBRequests.getClientByID(selectedAppointment.getClientID());
            this.clientFirst = passedClient.getFirstName();
            this.clientLast = passedClient.getLastName();
            String fullName = clientLast + ", " + clientFirst;
            clientCB.getSelectionModel().select(fullName);
            setupClientInformation(userSetup);
            setupStartDateArea(0);
            setupStartTimeArea(0);
            setupEndTimeArea(0);
            setupEndDateArea(0);
            setupAppointmentInformation(userSetup);
            setupApptFeesTableView(userSetup);
        }
        
    }
    
    public void newFeeButtonClicked()
    {
       SceneGenerator.appointmentFeeScreen(useSetup);
    }
        
     // Method for the back button
    public void backButtonClicked()
    {
        SceneGenerator.dashboardScreen(myBusinessScheduler);
    }
    
    public void editButtonClicked()
    {
        isUneditable = false;
        setEditable();
    }
    
    // Method for the saved button
    public void saveButtonClicked()
    {
        Appointment userAppointment = new Appointment();
        // Stuff for the begin date and time
            String startMonth = CheckStrings.fixDateTimeString(startMonthSpin.getValue().toString());
            String startDay = CheckStrings.fixDateTimeString(startDaySpin.getValue().toString());
            String startYear = CheckStrings.fixDateTimeString(startYearSpin.getValue().toString());
            String startHour = CheckStrings.fixDateTimeString(startHourSpinner.getValue().toString());
            String startMinute = CheckStrings.fixDateTimeString(startMinuteSpin.getValue().toString());
            String getStartAMPM = startTimeAMPMCB.getSelectionModel().getSelectedItem().toString();
        // Stuff for the end date and time
            String endMonth = CheckStrings.fixDateTimeString(endMonthSpin.getValue().toString());
            String endDay = CheckStrings.fixDateTimeString(endDaySpin.getValue().toString());
            String endYear = CheckStrings.fixDateTimeString(endYearSpin.getValue().toString());
            String endHour = CheckStrings.fixDateTimeString(endHourSpin.getValue().toString());
            String endMinute = CheckStrings.fixDateTimeString(endMinuteSpin.getValue().toString());
            String getEndAMPM = endTimeAMPMCB.getSelectionModel().getSelectedItem().toString();
            startHour = CheckStrings.convertTimeTo24(startHour, getStartAMPM);
            endHour = CheckStrings.convertTimeTo24(endHour, getEndAMPM);
            String startDate = startMonth + "/" + startDay + "/" + startYear;
            String startTime = startHour + ":" + startMinute;
            String endDate = endMonth + "/" + endDay + "/" + endYear;
            String endTime = endHour + ":" + endMinute;
        if (useSetup != 0)
        {
            userAppointment = selectedAppointment;
            userAppointment.setUserID(DashboardSceneController.getUserID());
            userAppointment.setClientID(this.allClients.get(selectedClientIndex).getClientID());
            userAppointment.setAptTitle(titleTF.getText());

            // Store the data into the appointment object
            userAppointment.setStartDate(startDate);
            userAppointment.setStartTime(startTime);
            userAppointment.setEndDate(endDate);
            userAppointment.setEndTime(endTime);
            boolean requiredFieldsFilled = CheckEmptyObject.checkAppointment(userAppointment);
            boolean datesArentConflicting = DateConflictCheck.isAppointmentValid(startDate, endDate, startTime, endTime, useSetup);
            if (requiredFieldsFilled) {
                if (datesArentConflicting)
                    {
                        DBEdits.editAppointment(userAppointment);
                        backButtonClicked();
                    }
                else
                {
                    Alert dateConflict = new Alert(Alert.AlertType.ERROR, "The dates are conflicting with another appointment. Please revise and try again.", ButtonType.OK);
                    dateConflict.show();
                }
            } else {
                Alert fillAll = new Alert(Alert.AlertType.ERROR, "Please make sure all the fields are filled in and try again", ButtonType.OK);
                fillAll.show();
            }
        }
        else 
        {
            
            userAppointment.setUserID(DashboardSceneController.getUserID());
            userAppointment.setClientID(this.allClients.get(selectedClientIndex).getClientID());
            userAppointment.setAptTitle(titleTF.getText());
            // Store the data into the appointment object
            userAppointment.setStartDate(startDate);
            userAppointment.setStartTime(startTime);
            userAppointment.setEndDate(endDate);
            userAppointment.setEndTime(endTime);
            
            boolean requiredFieldsFilled = CheckEmptyObject.checkAppointment(userAppointment);
            boolean datesArentConflicting = DateConflictCheck.isAppointmentValid(startDate, endDate, startTime, endTime, useSetup);
            if (requiredFieldsFilled) {
                if (datesArentConflicting)
                {
                    DBInserts.createNewAppointment(userAppointment);
                    backButtonClicked();
                }
                else
                {
                    Alert dateConflict = new Alert(Alert.AlertType.ERROR, "The dates are conflicting with another appointment. Please revise and try again.", ButtonType.OK);
                    dateConflict.show();
                }
            } else {
                Alert fillAll = new Alert(Alert.AlertType.ERROR, "Please make sure all the fields are filled in and try again", ButtonType.OK);
                fillAll.show();
            }
            AppointmentFeeSceneController.localAptFees.clear();
        }
    }
    
    
    //Setup End Date Area
    public void setupEndDateArea(int userSetup)
    {
        int currentDayNum = LocalDate.now().getDayOfMonth();
        int yearNum = LocalDate.now().getYear();
        int futurYearNum = yearNum + 10;
        int monthNum = LocalDate.now().getMonthValue();
        int numDays = DateTimeSpinners.setNumberOfDays(monthNum, yearNum);
        System.out.println(yearNum);
        endMonthSpin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, monthNum));
        endDaySpin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, numDays, currentDayNum));
        endYearSpin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(yearNum-1, futurYearNum, yearNum));
        endMinuteSpin.setEditable(true);
    }
    
    //Setup End Time Area
    public void setupEndTimeArea(int userSetup)
    {
        List<String> theAfterLetters = new ArrayList<>();
        theAfterLetters.add("AM");
        theAfterLetters.add("PM");
        endTimeAMPMCB.setItems(FXCollections.observableList(theAfterLetters));
        
        int currentHour = LocalTime.now().getHour();
        if (currentHour > 12)
        {
            currentHour -= 12;
           endTimeAMPMCB.getSelectionModel().selectLast();
        }
        else 
        {
            endTimeAMPMCB.getSelectionModel().selectFirst();
        }
        int currentMinute = LocalTime.now().getMinute();
        endHourSpin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, currentHour));
        endMinuteSpin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, currentMinute));
    }
    
    //Setup StartDate Area
    public void setupStartDateArea(int userSetup)
    {
        int currentDayNum = LocalDate.now().getDayOfMonth();
        int yearNum = LocalDate.now().getYear();
        int futurYearNum = yearNum + 10;
        int monthNum = LocalDate.now().getMonthValue();
        int numDays = DateTimeSpinners.setNumberOfDays(monthNum, yearNum);
        System.out.println(yearNum);
        startMonthSpin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, monthNum));
        startDaySpin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, numDays, currentDayNum));
        startYearSpin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(yearNum-1, futurYearNum, yearNum));
        startMinuteSpin.setEditable(true);
        
    }
    
    // Setup the starttime area
    public void setupStartTimeArea(int userSetup)
    {
        List<String> theAfterLetters = new ArrayList<>();
        theAfterLetters.add("AM");
        theAfterLetters.add("PM");
        startTimeAMPMCB.setItems(FXCollections.observableList(theAfterLetters));
        
        int currentHour = LocalTime.now().getHour();
        if (currentHour > 12)
        {
            currentHour -= 12;
           startTimeAMPMCB.getSelectionModel().selectLast();
        }
        else 
        {
            startTimeAMPMCB.getSelectionModel().selectFirst();
        }
        System.out.println(currentHour);
        int currentMinute = LocalTime.now().getMinute();
        startHourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, currentHour));
        startMinuteSpin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, currentMinute));
        
    }
    

    
    
    // Setup the Client ComboBox
    public void setupClientComboBox(int userSetup)
    {
            clientCB.setOnAction((event) -> {
           String clientName = (String) clientCB.getSelectionModel().getSelectedItem();
            System.out.println(clientName);
            String[] client = clientName.split(",");
            System.out.println(Arrays.toString(client));
            this.clientFirst = client[1];
            this.clientLast = client[0];
            this.selectedClientIndex = clientCB.getSelectionModel().getSelectedIndex();
            phoneTF.setText(allClients.get(this.selectedClientIndex).getPhoneNumber());
            emailTF.setText(allClients.get(this.selectedClientIndex).getEmailAddress());
            locationTF.setText(allClients.get(this.selectedClientIndex).getAddress());
            notesTA.setText(allClients.get(this.selectedClientIndex).getClientNotes());
        });

    }
    
    public void setupClientInformation(int userSetup)
    {
            phoneTF.setText(passedClient.getPhoneNumber());
            emailTF.setText(passedClient.getEmailAddress());
            locationTF.setText(passedClient.getAddress());
            notesTA.setText(passedClient.getClientNotes());
    }
    
    public void setupAppointmentInformation(int userSetup)
    {
        int ampmIndex = 0;
        titleTF.setText(selectedAppointment.getAptTitle());
        
        //Setup the date spinners
         
        String aptStartDate = selectedAppointment.getStartDate();
        LocalDate startDate = LocalDate.parse(aptStartDate, fullDTF);
        String aptEndDate = selectedAppointment.getEndDate();
        LocalDate endDate = LocalDate.parse(aptEndDate, fullDTF);
        // Starting Date
        int startingDay = startDate.getDayOfMonth();
        int startingMonth = startDate.getMonthValue();
        int startingYear = startDate.getYear();
        startDaySpin.getValueFactory().setValue(startingDay);
        startMonthSpin.getValueFactory().setValue(startingMonth);
        startYearSpin.getValueFactory().setValue(startingYear);
        // Ending Date
        int endingDay = endDate.getDayOfMonth();
        int endingMonth = endDate.getMonthValue();
        int endingYear = endDate.getYear();
        endDaySpin.getValueFactory().setValue(endingDay);
        endMonthSpin.getValueFactory().setValue(endingMonth);
        endYearSpin.getValueFactory().setValue(endingYear);
        //Starting Time
        String startingTime = selectedAppointment.getStartTime();
        int startingHour = Integer.parseInt(startingTime.substring(0, 2));
        int startingMinute = Integer.parseInt(startingTime.substring(3, 5));
        if (startingHour > 12)
        {
            ampmIndex  = 1;
            startingHour -= 12;
            startTimeAMPMCB.getSelectionModel().select(ampmIndex);
        }
        else 
        {
            startTimeAMPMCB.getSelectionModel().select(ampmIndex);
        }
        startHourSpinner.getValueFactory().setValue(startingHour);
        startMinuteSpin.getValueFactory().setValue(startingMinute);
        
        //Ending Time
        String endingTime = selectedAppointment.getEndTime();
        int endingHour = Integer.parseInt(endingTime.substring(0, 2));
        int endingMinute = Integer.parseInt(endingTime.substring(3, 5));
        if (endingHour > 12)
        {
            ampmIndex  = 1;
            endingHour -= 12;
            endTimeAMPMCB.getSelectionModel().select(ampmIndex);
        }
        else 
        {
            endTimeAMPMCB.getSelectionModel().select(ampmIndex);
        }
        endHourSpin.getValueFactory().setValue(endingHour);
        endMinuteSpin.getValueFactory().setValue(endingMinute);
        
        
    }
    
    public void setupApptFeesTableView(int aptID)
    {
        feeAmountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        feeTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        if (aptID == 0)
        {
            feeTV.getItems().setAll(AppointmentFeeSceneController.localAptFees);
        }
        else
        {
            List<ApptFee> appointmentFees = DBRequests.getApptFeesByApptID(aptID);
            if (appointmentFees.isEmpty())
            {
                feeTV.getItems().setAll(AppointmentFeeSceneController.localAptFees);
            }
            else
            {
                feeTV.getItems().setAll(appointmentFees);
            }
        }
        
    }
    
    public void rightClickDeleteFee(int feeID)
    {
         MenuItem deleteItem = new MenuItem("Delete");
                    contextMenu.getItems().setAll(deleteItem);
                    feeTV.setContextMenu(contextMenu);
                    
                    deleteItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                        public void handle(ActionEvent event) {
                            DBDeletes.deleteApptFeeByID(feeID);
                            SceneGenerator.showAppointmentDetails(useSetup);
                        }
                    });
    }
    
    public void rightClickDeleteFee(ApptFee userFee)
    {
        MenuItem deleteItem = new MenuItem("Delete");
                    contextMenu.getItems().setAll(deleteItem);
                    feeTV.setContextMenu(contextMenu);
                    
                    deleteItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                        public void handle(ActionEvent event) {
                            AppointmentFeeSceneController.localAptFees.remove(userFee);
                            SceneGenerator.showAppointmentDetails(useSetup);
                        }
                    });
    }
    
    
    public void setEditable()
    {
        String disabledStyle = "-fx-opacity: 1; -fx-text-fill: black;-fx-background-color: white";
        titleTF.setDisable(isUneditable);
            titleTF.setStyle(disabledStyle);
            clientCB.setDisable(isUneditable);
            clientCB.setStyle(disabledStyle);
            phoneTF.setDisable(isUneditable);
            phoneTF.setStyle(disabledStyle);
            emailTF.setDisable(isUneditable);
            emailTF.setStyle(disabledStyle);
            locationTF.setDisable(isUneditable);
            locationTF.setStyle(disabledStyle);
            notesTA.setDisable(isUneditable);
            notesTA.setStyle(disabledStyle);
            
            startMonthSpin.setDisable(isUneditable);
            startMonthSpin.setStyle(disabledStyle);
            startDaySpin.setDisable(isUneditable);
            startDaySpin.setStyle(disabledStyle);
            startYearSpin.setDisable(isUneditable);
            startYearSpin.setStyle(disabledStyle);
            startHourSpinner.setDisable(isUneditable);
            startHourSpinner.setStyle(disabledStyle);
            startMinuteSpin.setDisable(isUneditable);
            startMinuteSpin.setStyle(disabledStyle);
            startTimeAMPMCB.setDisable(isUneditable);
            startTimeAMPMCB.setStyle(disabledStyle);
            
            endMonthSpin.setDisable(isUneditable);
            endMonthSpin.setStyle(disabledStyle);
            endDaySpin.setDisable(isUneditable);
            endDaySpin.setStyle(disabledStyle);
            endYearSpin.setDisable(isUneditable);
            endYearSpin.setStyle(disabledStyle);
            endHourSpin.setDisable(isUneditable);
            endHourSpin.setStyle(disabledStyle);
            endMinuteSpin.setDisable(isUneditable);
            endMinuteSpin.setStyle(disabledStyle);
            endTimeAMPMCB.setDisable(isUneditable);
            endTimeAMPMCB.setStyle(disabledStyle);
    }
}
