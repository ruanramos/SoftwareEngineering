package application.view;

import application.Main;
import application.dbclass.CustomerDao;
import application.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    private Main main;
    private String filter = "";
    private String searchValue = "";

    public void setMain(Main main) {
        this.main = main;
    }

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

    @FXML
    private void handleEditCustomer() {
     Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();

     if (selectedCustomer != null) {
         boolean okClicked = main.showCustomerEditDialog(selectedCustomer);
         if (okClicked) {
             System.out.println("OMG it's finally happening");
             CustomerDao customerDao = new CustomerDao();
             customerDao.update(selectedCustomer);
         }
     }
    }

    @FXML
    private void handleDeleteCustomer() {
        int selectedIndex = customerTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Customer selectedCustomer = customerTable.getItems().get(selectedIndex);
            customerTable.getItems().remove(selectedIndex);

            CustomerDao customerDao = new CustomerDao();
            customerDao.delete(selectedCustomer);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nenhuma seleção");
            alert.setHeaderText("Nenhuma Pessoa Selecionada");
            alert.setContentText("Por favor, selecione um cliente na tabela.");
            alert.showAndWait();
        }
    }
}
