package application.view;

import application.model.Car;
import application.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CarEditDialogController {
    private Stage dialogStage;
    private Car car;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    }


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCar(Car car) {
        this.car = car;
//        firstNameField.setText(car.getFirstName());
//        lastNameField.setText(car.getLastName());
//        cpfField.setText(car.getCpf());
//        cnhField.setText(car.getCnh());
//        birthdayField.setText(DateUtil.format(car.getBirthday()));
//        cellphoneField.setText(car.getCellphone());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
//            car.setFirstName(firstNameField.getText());
//            car.setLastName(lastNameField.getText());
//            car.setCpf(cpfField.getText());
//            car.setCnh(cnhField.getText());
//            car.setBirthday(DateUtil.parse(birthdayField.getText()));
//            car.setCellphone(cellphoneField.getText());
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