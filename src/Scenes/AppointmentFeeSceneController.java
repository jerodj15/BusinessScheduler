package Scenes;

import Database.DBEdits;
import Database.DBInserts;
import Database.DBRequests;
import Main.BusinessScheduler;
import Objects.ApptFee;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class AppointmentFeeSceneController {

    @FXML
    private Label apptFeeLabel;
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
    @FXML
    private AnchorPane smallPane;

    private BusinessScheduler myBusinessScheduler;
    public static List<ApptFee> localAptFees = new ArrayList<>();
    public static int userSet;
    public static int apptFeeID;
    
    public void setupAptFeeScene(BusinessScheduler scheduler, int userSetup)
    {

        userSet = userSetup;
        apptFeeID = 0;
        SceneGenerator.setupSideMenu();
        if (userSetup == 0)
        {
            localAptFees = new ArrayList<>();
        }
        else
        {
            localAptFees = DBRequests.getApptFeesByApptID(userSetup);
            
        }
    }
    
    public void setupAptFeeScene(BusinessScheduler scheduler, ApptFee userFee, int userSetup)
    {
        userSet = userSetup;
        SceneGenerator.setupSideMenu();
        titleTF.setText(userFee.getTitle());
        amountTF.setText(userFee.getAmount());
        apptFeeID = userFee.getId();
    }
    
    public void saveButtonClicked()
    {
        ApptFee userApptFee = new ApptFee();
        String apptTitle = titleTF.getText();
        String apptAmount = amountTF.getText();
        if (apptTitle.isEmpty() || apptAmount.isEmpty())
        {
            Alert emptyAlert = new Alert(Alert.AlertType.ERROR, "Please make sure all fields are filled and try again.", ButtonType.OK);
            emptyAlert.show();
        }
        else
        {
            userApptFee.setTitle(apptTitle);
            userApptFee.setAmount(apptAmount);
            if (userSet == 0)
            {
                localAptFees.add(userApptFee);
            }
            else
            {
                userApptFee.setApptID(userSet);
                if (apptFeeID == 0)
                {
                    DBInserts.createAppointmentFee(userApptFee);
                }
                else
                {
                    userApptFee.setId(apptFeeID);
                    DBEdits.editAppointmentFee(userApptFee);
                }
                
            }
            cancelButtonClicked();
        }
    }
    
    public void cancelButtonClicked()
    {
        SceneGenerator.hideClicked();
        SceneGenerator.appointmentScreen();
    }
}
