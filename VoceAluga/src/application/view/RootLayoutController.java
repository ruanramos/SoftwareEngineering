package application.view;

import application.Main;
import application.dbclass.CarDao;
import application.dbclass.CustomerDao;
import application.model.Car;
import application.model.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class RootLayoutController {

    private Main main;

    private void initialize() {
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private void handleSearchUser() {
        main.showCustomerSearchDialog();
    }

    @FXML
    private void handleNewUser() {
        Customer tempCustomer = new Customer();
        boolean okClicked = main.showCustomerEditDialog(tempCustomer);
        if (okClicked) {
            main.getCustomerData().add(tempCustomer);
            CustomerDao customerDao = new CustomerDao();
            customerDao.insert(tempCustomer);
        }
    }

    @FXML
    private void handleSearchCar() {
        main.showCarSearchDialog();
    }

    @FXML
    private void handleNewCar() {
        Car tempCar = new Car();
        boolean okClicked = main.showCarEditDialog(tempCar);
        if (okClicked) {
            main.getCarData().add(tempCar);
            CarDao carDao = new CarDao();
            carDao.insert(tempCar);
        }
    }
}
