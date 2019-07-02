package application.view;

import application.controller.CarroController;
import application.controller.ControllerException;
import application.model.Carro;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class CarroEditDialogController {

    @FXML
    Node rootNode;
    @FXML
    TextField placaField;
    @FXML
    TextField modeloField;
    @FXML
    TextField grupoField;
    @FXML
    TextField anoField;
    @FXML
    TextField quilometragemField;

    private Carro carro;
    private boolean newEntryFlag;

    @FXML
    public void initialize() {

    }

    public void setNewEntryFlag(boolean newEntryFlag) {
        carro = new Carro();
        this.newEntryFlag = newEntryFlag;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
        placaField.setText(String.valueOf(carro.getPlaca()));
        modeloField.setText(carro.getModelo());
        grupoField.setText(carro.getGrupo());
        anoField.setText(String.valueOf(carro.getAno()));
        quilometragemField.setText(String.valueOf(carro.getQuilometragem()));
    }
    private Map<String,String> buildFieldsMap() {
    	Map<String,String> carroFields = new HashMap<>();
        carroFields.put("placa", placaField.getText());
        carroFields.put("modelo", modeloField.getText());
        carroFields.put("grupo", grupoField.getText());
        carroFields.put("ano", anoField.getText());
        carroFields.put("quilometragem", quilometragemField.getText());    	
    	return carroFields;
    }
    @FXML
    private void handleOk() {
        Map<String, String> carroFields = buildFieldsMap();
        
        try {
            CarroController carroController = new CarroController();
            if (newEntryFlag) {
                carroController.add(carroFields);
            } else {
                carroController.edit(carroFields);
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
