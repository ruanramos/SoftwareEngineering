package application.view;

import application.controller.CarController;
import application.controller.ControllerException;
import application.dbclass.CarDao;
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
    TextField idField;
    @FXML
    TextField modelField;
    @FXML
    TextField categoryField;
    @FXML
    TextField ageField;
    @FXML
    TextField mileageField;

    private Car car;
    private boolean newEntryFlag;

    @FXML
    public void initialize() {
        // bloqueia edição do id
        idField.setDisable(true);
    }

    public void setNewEntryFlag(boolean newEntryFlag) {
        car = new Car();
        this.newEntryFlag = newEntryFlag;
    }

    public void setCar(Car car) {
        this.car = car;
        idField.setText(String.valueOf(car.getId()));
        modelField.setText(car.getModel());
        categoryField.setText(car.getCategory());
        ageField.setText(String.valueOf(car.getAge()));
        mileageField.setText(String.valueOf(car.getMileage()));
    }

    @FXML
    private void handleOk() {
        Map<String, String> carFields = new HashMap<>();
        carFields.put("model", modelField.getText());
        carFields.put("category", categoryField.getText());
        carFields.put("age", ageField.getText());
        carFields.put("mileage", mileageField.getText());

        try {
            CarController carManager = new CarController();
            if (newEntryFlag) {
                carManager.add(carFields);
            } else {
                carFields.put("id", String.valueOf(car.getId()));
                carManager.edit(carFields);
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
