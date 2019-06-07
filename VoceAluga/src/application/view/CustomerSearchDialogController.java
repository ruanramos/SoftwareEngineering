package application.view;

import application.Main;
import application.controller.CustomerController;
import application.dbclass.CustomerDao;
import application.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

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

    @FXML
    private void initialize() {
        filterChoice.getItems().addAll("CPF", "Nome", "Sobrenome");
        filterChoice.setValue("CPF");

        customerTable.setPlaceholder(new Label("Nenhum cliente encontrado."));

        cpfColumn.setCellValueFactory(cellData -> cellData.getValue().cpfProperty());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    }

    @FXML
    private void handleSearchCustomer() {
        ObservableList<Customer> customerResult = FXCollections.observableArrayList();
        searchValue = searchTextField.getText();
        CustomerController customerManager = new CustomerController();

        // TODO: e se não escolher nada?
        if (filterChoice.getValue().equals("CPF")) {
            customerManager.searchByCpf(customerResult, searchValue);
        } else if (filterChoice.getValue().equals("Nome")) {
            customerManager.searchByFirstName(customerResult, searchValue);
        } else if (filterChoice.getValue().equals("Sobrenome")) {
            customerManager.searchByLastName(customerResult, searchValue);
        }

        customerTable.setItems(customerResult);
     }

    @FXML
    private void handleEditCustomer() throws IOException {
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/CustomerEditDialog.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Editar Cliente");
//        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);

        CustomerEditDialogController controller = loader.getController();
        controller.setNewEntryFlag(false);
        controller.setCustomer(selectedCustomer);

        stage.showAndWait();
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
