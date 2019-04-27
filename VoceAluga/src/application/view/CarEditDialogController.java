package application.view;

import application.dbclass.CarDao;
import application.model.Car;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    private Stage dialogStage;
    private Car car;
    private boolean okClicked = false;
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

            CarDao carDao = new CarDao();
            if (newEntryFlag) {
                carDao.insert(car);
            } else {
                carDao.update(car);
            }

            this.getStage().close();
        }
    }

    @FXML
    private void handleCancel() {
        this.getStage().close();
    }

    private Stage getStage() {
        return (Stage) rootNode.getScene().getWindow();
    }

    private boolean isInputValid() {
        return true;
    }
}