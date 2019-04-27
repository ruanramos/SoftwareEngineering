package application.view;

import application.dbclass.CustomerDao;
import application.model.Customer;
import application.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CustomerEditDialogController {

    @FXML
    private Node rootNode;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField cpfField;
    @FXML
    private TextField cnhField;
    @FXML
    private TextField birthdayField;
    @FXML
    private TextField cellphoneField;

    private Customer customer;
    private boolean newEntryFlag;

    @FXML
    public void initialize() {
    }

    public void setNewEntryFlag(boolean newEntryFlag) {
        customer = new Customer();
        this.newEntryFlag = newEntryFlag;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;

        firstNameField.setText(customer.getFirstName());
        lastNameField.setText(customer.getLastName());
        cpfField.setText(customer.getCpf());
        cnhField.setText(customer.getCnh());
        birthdayField.setText(DateUtil.format(customer.getBirthday()));
        cellphoneField.setText(customer.getCellphone());
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            customer.setFirstName(firstNameField.getText());
            customer.setLastName(lastNameField.getText());
            customer.setCpf(cpfField.getText());
            customer.setCnh(cnhField.getText());
            customer.setBirthday(DateUtil.parse(birthdayField.getText()));
            customer.setCellphone(cellphoneField.getText());

            CustomerDao customerDao = new CustomerDao();
            if (newEntryFlag) {
                customerDao.insert(customer);
            } else {
                customerDao.update(customer);
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
        String errorMessage = "";

        if (!isNameValid(firstNameField.getText())) {
            errorMessage += "Nome inválido.\n";
        }

        if (!isNameValid(lastNameField.getText())) {
            errorMessage += "Sobrenome inválido.\n";
        }

        if (!isCpfValid(cpfField.getText())) {
            errorMessage += "CPF inválido.\n";
        }

        if (!isCnhValid(cnhField.getText())) {
            errorMessage += "CNH inválida.\n";
        }

        if (!isBirthdayValid(birthdayField.getText())) {
            errorMessage += "Data de nascimento inválida!\n";
        }

        if (!isCellphoneValid(cellphoneField.getText())) {
            errorMessage += "Número de celular inválido.\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(this.getStage());
            alert.setTitle("Campo inválido");
            alert.setHeaderText("Por favor corrija os campos inválidos.");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    private boolean isNameValid(String name) {
        return (name != null && name.length() > 0);
    }

    private boolean isCpfValid(String cpf) {
        return (cpf != null && cpf.matches("\\d{11}"));
    }

//    private boolean isRgValid(String rg) {
//        return (rg != null && rg.matches("\\d{9}"));
//    }

    private boolean isCnhValid(String cnh) {
        return (cnh != null && cnh.matches("\\d{10}"));
    }

//    private boolean isAddressValid(String address) {
//        return (address != null && address.length() > 0);
//    }

    private boolean isCellphoneValid(String cellphone) {
        return (cellphone != null && cellphone.matches("\\d{8}\\d*"));
    }

    private boolean isBirthdayValid(String birthday) {
        return (birthday != null && birthday.length() > 0 && DateUtil.validDate(birthday));
    }
}