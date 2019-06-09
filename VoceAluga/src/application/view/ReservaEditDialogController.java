package application.view;

import application.controller.ControllerException;
import application.controller.ReservaController;
import application.model.Reserva;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class ReservaEditDialogController {

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

    private Reserva reserva;
    private boolean newEntryFlag;

    @FXML
    public void initialize() {
        // bloqueia edição do id
        idField.setDisable(true);
    }

    public void setNewEntryFlag(boolean newEntryFlag) {
        reserva = new Reserva();
        this.newEntryFlag = newEntryFlag;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
        
    }

    @FXML
    private void handleOk() {
        Map<String, String> reservationFields = new HashMap<>();
       

        try {
            ReservaController reservationManager = new ReservaController();
            if (newEntryFlag) {
                reservationManager.add(reservationFields);
            } else {
                reservationFields.put("id", String.valueOf(reserva.getId()));
                reservationManager.edit(reservationFields);
            }
        } catch (ControllerException e) {
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
