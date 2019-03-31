package application.view;

import application.dbclass.CustomerDao;
import application.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class CustomerSearchDialogController {

    @FXML
    private ChoiceBox<String> filterChoice;
    @FXML
    private TextField searchTextField;
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, String> cpfColumn;
    @FXML
    private TableColumn<Customer, String> firstNameColumn;
    @FXML
    private TableColumn<Customer, String> lastNameColumn;

    private String filter = "";
    private String searchValue = "";

    @FXML
    private void initialize() {
        filterChoice.getItems().addAll("CPF", "Nome", "Sobrenome");

        cpfColumn.setCellValueFactory(cellData -> cellData.getValue().cpfProperty());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    }

    @FXML
    private void handleSearchCustomer() {
        // TODO: e se não escolher nada?
        if (filterChoice.getValue().equals("CPF")) {
            filter = "Cpf";
        } else if (filterChoice.getValue().equals("Nome")) {
            filter = "FirstName";
        } else if (filterChoice.getValue().equals("Sobrenome")) {
            filter = "LastName";
        }

        searchValue = searchTextField.getText();

        ObservableList<Customer> customerResult = FXCollections.observableArrayList();
        CustomerDao customerDao = new CustomerDao();
        customerDao.selectToList(customerResult, filter + " like '%" + searchValue + "%'");

        customerTable.setItems(customerResult);
     }
}
