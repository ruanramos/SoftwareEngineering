package application.view;

import application.Main;
import application.dbclass.CarDao;
import application.model.Car;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CarSearchDialogController {
    @FXML
    private ChoiceBox<String> filterChoice;
    @FXML
    private TextField searchTextField;
    @FXML
    private TableView<Car> carTable;
    @FXML
    private TableColumn<Car, Number> idColumn;
    @FXML
    private TableColumn<Car, String> modelColumn;
    @FXML
    private TableColumn<Car, String> categoryColumn;

    private Main main;
    private String filter = "";
    private String searchValue = "";

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private void initialize() {
        filterChoice.getItems().addAll("Código", "Modelo", "Categoria");
        filterChoice.setValue("Categoria");

        carTable.setPlaceholder(new Label("Nenhum veículo encontrado."));

        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        modelColumn.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
    }

    @FXML
    private void handleSearchCar() {
        // TODO: e se não escolher nada?
        if (filterChoice.getValue().equals("Código")) {
            filter = "idCarro";
        } else if (filterChoice.getValue().equals("Modelo")) {
            filter = "Modelo";
        } else if (filterChoice.getValue().equals("Classe")) {
            filter = "Classe";
        }

        searchValue = searchTextField.getText();

        ObservableList<Car> carResult = FXCollections.observableArrayList();
        CarDao carDao = new CarDao();
        carDao.selectToList(carResult, filter + " like '%" + searchValue + "%'");

        carTable.setItems(carResult);
    }

    @FXML
    private void handleEditCar() {
        Car selectedCar = carTable.getSelectionModel().getSelectedItem();

        if (selectedCar != null) {
            boolean okClicked = main.showCarEditDialog(selectedCar);
            if (okClicked) {
                CarDao carDao = new CarDao();
                carDao.update(selectedCar);
            }
        }
    }

    @FXML
    private void handleDeleteCar() {
        int selectedIndex = carTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Car selectedCar = carTable.getItems().get(selectedIndex);
            carTable.getItems().remove(selectedIndex);

            CarDao carDao = new CarDao();
            carDao.delete(selectedCar);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nenhuma seleção");
            alert.setHeaderText("Nenhuma Carro Selecionadp");
            alert.setContentText("Por favor, selecione um carro na tabela.");
            alert.showAndWait();
        }
    }
}
