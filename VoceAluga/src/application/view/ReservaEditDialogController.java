package application.view;

import application.controller.ClienteController;
import application.controller.ControllerException;
import application.controller.ReservaController;
import application.model.Cliente;
import application.model.Reserva;
import application.util.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class ReservaEditDialogController {

    @FXML
    private Node rootNode;
    @FXML
    private TextField idField;
    @FXML
    private ChoiceBox<String> idclienteField;
    @FXML
    private DatePicker dataPicker;
    @FXML
    private TextField grupoField;
    @FXML
    private TextField modeloField;
    @FXML
    private TextField duracaodiasField;

    private Reserva reserva;
    
    
    private boolean newEntryFlag;

    @FXML
    public void initialize() {
        // bloqueia edição do id
        idField.setDisable(true);
        //dataPicker.setConverter(DateUtil.getStringConverter());
    }

    public void setNewEntryFlag(boolean newEntryFlag) {
        reserva = new Reserva();
        ClienteController clienteController = new ClienteController();
        this.newEntryFlag = newEntryFlag;
        this.idclienteField.setItems(clienteController.getIdsClienteField());
        
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
        this.idField.setText(String.valueOf(reserva.getId()));
        this.idclienteField.setValue(String.valueOf(reserva.getIdcliente()));
        this.dataPicker.setValue(reserva.getData());
        this.grupoField.setText(reserva.getGrupo());
        this.modeloField.setText(reserva.getModelo());
        this.duracaodiasField.setText(String.valueOf(reserva.getDuracaodias()));
        
    }
    private Map<String,String> buildFieldsMap() {
    	Map<String,String> fields = new HashMap<>();
    	fields.put("id",idField.getText());
    	fields.put("idcliente", idclienteField.getValue());
    	fields.put("data", DateUtil.parse(dataPicker.getEditor().getText()).toString());
    	fields.put("grupo", grupoField.getText());
    	fields.put("modelo", modeloField.getText());
    	fields.put("duracaodias", duracaodiasField.getText());
    	return fields;
    }
    
    @FXML
    private void handleOk() {
        Map<String, String> reservationFields = buildFieldsMap();


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
