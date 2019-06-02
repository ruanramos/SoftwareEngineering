package application.view;

import application.dbclass.ReservationDao;
import application.manager.ManagerException;
import application.manager.ReservationManager;
import application.model.Reservation;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class ReservationEditDialogController {

    @FXML
    Node rootNode;
    @FXML
    TextField idField;
    @FXML
    TextField reservationidField;
    @FXML
    TextField categoryField;
    @FXML
    TextField ageField;
    @FXML
    TextField mileageField;

    private Reservation reservation;
    private boolean newEntryFlag;

    @FXML
    public void initialize() {
        // bloqueia edição do id
        idField.setDisable(true);
    }

    public void setNewEntryFlag(boolean newEntryFlag) {
        reservation = new Reservation();
        this.newEntryFlag = newEntryFlag;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
        
    }

    @FXML
    private void handleOk() {
        Map<String, String> reservationFields = new HashMap<>();
       

        try {
            ReservationManager reservationManager = new ReservationManager();
            if (newEntryFlag) {
                reservationManager.add(reservationFields);
            } else {
                reservationFields.put("id", String.valueOf(reservation.getId()));
                reservationManager.edit(reservationFields);
            }
        } catch (ManagerException e) {
            e.printStackTrace();
        }

        this.getStage().close();
    }

    @FXML
    private void handleCancel() {
        this.getStage().close();
    }

    private Stage getStage() {
        return (Stage) rootNode.getScene().getWindow();
    }
}
