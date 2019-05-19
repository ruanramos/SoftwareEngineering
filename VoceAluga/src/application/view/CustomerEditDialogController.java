package application.view;

import application.dbclass.CustomerDao;
import application.manager.CustomerManager;
import application.manager.ManagerException;
import application.model.Customer;
import application.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

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
        Map<String,String> customerFields = new HashMap<>();
        customerFields.put("firstName", firstNameField.getText());
        customerFields.put("lastName", lastNameField.getText());
        customerFields.put("cpf", cpfField.getText());
        customerFields.put("cnh", cnhField.getText());
        customerFields.put("birthday", birthdayField.getText());
        customerFields.put("cellphone", cellphoneField.getText());

        try {
            CustomerManager customerManager = new CustomerManager();

            if (newEntryFlag) {
                customerManager.add(customerFields);
            } else {
                customerFields.put("id", String.valueOf(customer.getId()));
                customerManager.edit(customerFields);
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
