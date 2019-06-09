package application.view;

import application.controller.ControllerException;
import application.controller.CustomerController;
import application.model.Customer;
import application.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CustomerEditDialogController {

    @FXML
    private Node rootNode;
    @FXML
    private TextField nomeField;
    @FXML
    private TextField enderecoField;
    @FXML
    private TextField telefoneField;
    @FXML
    private TextField cpfField;
    @FXML
    private DatePicker nascimentoPicker;
    @FXML
    private DatePicker cnhPicker;

    private Customer customer;
    private boolean newEntryFlag;

    @FXML
    public void initialize() {
        nascimentoPicker.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                return DateUtil.format(date);
            }

            @Override
            public LocalDate fromString(String string) {
                return DateUtil.parse(string);
            }
        });

        cnhPicker.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                return DateUtil.format(date);
            }

            @Override
            public LocalDate fromString(String string) {
                return DateUtil.parse(string);
            }
        });
    }

    public void setNewEntryFlag(boolean newEntryFlag) {
        customer = new Customer();
        this.newEntryFlag = newEntryFlag;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;

    }

    @FXML
    private void handleOk() {
        Map<String,String> customerFields = new HashMap<>();
        customerFields.put("cpf", cpfField.getText());
        customerFields.put("nome", nomeField.getText());
        customerFields.put("endereco", enderecoField.getText());
        customerFields.put("telefone", telefoneField.getText());
        customerFields.put("cnh", cnhPicker.getValue().toString());
        customerFields.put("nascimento", nascimentoPicker.getValue().toString());


        try {
            CustomerController customerManager = new CustomerController();

            if (newEntryFlag) {
                customerManager.add(customerFields);
            } else {
//                customerFields.put("id", String.valueOf(customer.getId()));
//                customerManager.edit(customerFields);
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
