package application.view;

import application.model.Car;
import application.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CarEditDialogController {

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

    private Stage dialogStage;
    private Car car;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
        // bloqueia edição do id
        idField.setDisable(true);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCar(Car car) {
        this.car = car;
        idField.setText(String.valueOf(car.getId()));
        modelField.setText(car.getModel());
        categoryField.setText(car.getCategory());
        ageField.setText(String.valueOf(car.getAge()));
        mileageField.setText(String.valueOf(car.getMileage()));
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            car.setModel(modelField.getText());
            car.setCategory(categoryField.getText());
            car.setAge(Integer.parseInt(ageField.getText()));
            car.setMileage(Double.parseDouble(mileageField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        return true;
    }
}