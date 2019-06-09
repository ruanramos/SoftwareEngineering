package application.view;

import application.controller.CarController;
import application.controller.ControllerException;
import application.model.Car;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class CarEditDialogController {

    @FXML
    Node rootNode;
    @FXML
    TextField placaField;
    @FXML
    TextField modeloField;
    @FXML
    TextField grupoField;
    // TODO: transformar no em um drop down
    @FXML
    TextField anoField;
    @FXML
    TextField quilometragemField;

    private Car car;
    private boolean newEntryFlag;

    @FXML
    public void initialize() {

    }

    public void setNewEntryFlag(boolean newEntryFlag) {
        car = new Car();
        this.newEntryFlag = newEntryFlag;
    }

    public void setCar(Car car) {
        this.car = car;
        placaField.setText(String.valueOf(car.getPlaca()));
        modeloField.setText(car.getModelo());
        grupoField.setText(car.getGrupo());
        anoField.setText(String.valueOf(car.getAno()));
        quilometragemField.setText(String.valueOf(car.getQuilometragem()));
    }

    @FXML
    private void handleOk() {
        Map<String, String> carFields = new HashMap<>();
        carFields.put("placa", placaField.getText());
        carFields.put("modelo", modeloField.getText());
        carFields.put("grupo", grupoField.getText());
        carFields.put("ano", anoField.getText());
        carFields.put("quilometragem", quilometragemField.getText());

        try {
            CarController carManager = new CarController();
            if (newEntryFlag) {
                carManager.add(carFields);
            } else {
//                carFields.put("id", String.valueOf(car.getId()));
//                carManager.edit(carFields);
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
