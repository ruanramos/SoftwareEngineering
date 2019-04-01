package application.view;

import application.dbclass.CustomerDao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import application.util.DateUtil;
import application.Main;
import application.model.Customer;

public class CustomercrudController {
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, String> firstNameColumn;
    
    
    
    @FXML
    private TableColumn<Customer, String> lastNameColumn;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label cpfLabel;
    @FXML
    private Label rgLabel;
    @FXML
    private Label cnhLabel;
    @FXML
    private Label birthdayLabel;
    @FXML
    private Label cellphoneLabel;
    @FXML
    private Label adressLabel;
    
    //Reference of main class application
    private Main main;
    
    public CustomercrudController() {
    }
    /* Initialize the controller class. This method is called automatically after the fxml file has been loaded */
    @FXML
    private void initialize() {
        // Initialize the table with 2 columns
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        // Clears the details of the customer.
        showCustomerDetails(null);


        // Detects selection changes and shows the customer's details when there is a change
        customerTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showCustomerDetails(newValue));
    }
    
    /* This is called for the main for make a reference to itself */
    public void setMain(Main main) {
        this.main = main;

        // Adiciona os dados da observable list na tabela
//        customerTable.setItems(main.getCustomerData());
        
    }
    /**
     * All fields of text is a customer details
     * If the customer is null all fields is "" empty
     * 
     * @param customer or null
     */
    private void showCustomerDetails(Customer customer) {
        if (customer != null) {
            // The labels receive the customer attributes 
            firstNameLabel.setText(customer.getFirstName());
            lastNameLabel.setText(customer.getLastName());
            cpfLabel.setText(customer.getCpf());
            cnhLabel.setText(customer.getCnh());
            birthdayLabel.setText(String.valueOf(customer.getBirthday()));
            cellphoneLabel.setText(customer.getCellphone());
            birthdayLabel.setText(DateUtil.format(customer.getBirthday()));
        } else {
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            cpfLabel.setText("");
            cnhLabel.setText("");
            birthdayLabel.setText("");
            cellphoneLabel.setText("");
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
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Nenhuma seleção");
            alert.setHeaderText("Nenhuma Pessoa Selecionada");
            alert.setContentText("Por favor, selecione um cliente na tabela.");
            alert.showAndWait();
        }
    }
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new Customer.
     */
    @FXML
    private void handleNewCustomer() {
        Customer tempCustomer = new Customer();
        boolean okClicked = main.showCustomerEditDialog(tempCustomer);
        if (okClicked) {
            main.getCustomerData().add(tempCustomer);
        }
        CustomerDao customerDao = new CustomerDao();
        customerDao.insert(tempCustomer);
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected Customer.
     */
    @FXML
    private void handleEditCustomer() {
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            boolean okClicked = main.showCustomerEditDialog(selectedCustomer);
            if (okClicked) {
                showCustomerDetails(selectedCustomer);
                CustomerDao customerDao = new CustomerDao();
                customerDao.update(selectedCustomer);
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
      //    alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Nada selecionado");
            alert.setHeaderText("Nenhum Cliente Selecionado.");
            alert.setContentText("Por Favor Selecione um Cliente da Tabela.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleSearchCustomer() {
        main.showCustomerSearchDialog();
    }
    
    
}
