package application.view;

import application.Main;
import application.controller.CarController;
import application.dbclass.CarDao;
import application.model.Car;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class CarSearchDialogController {
    @FXML
    private ChoiceBox<String> filterChoice;
    @FXML
    private TextField searchTextField;
    @FXML
    private TableView<Car> carTable;
    @FXML
    private TableColumn<Car, String> placaColumn;
    @FXML
    private TableColumn<Car, String> modeloColumn;
    @FXML
    private TableColumn<Car, String> grupoColumn;

    private String filter = "";
    private String searchValue = "";

    @FXML
    private void initialize() {
        filterChoice.getItems().addAll("Placa", "Modelo", "Grupo");
        filterChoice.setValue("Grupo");

        carTable.setPlaceholder(new Label("Nenhum veículo encontrado."));

        placaColumn.setCellValueFactory(cellData -> cellData.getValue().placaProperty());
        modeloColumn.setCellValueFactory(cellData -> cellData.getValue().modeloProperty());
        grupoColumn.setCellValueFactory(cellData -> cellData.getValue().grupoProperty());
    }

    @FXML
    private void handleSearchCar() {
        ObservableList<Car> carResult = FXCollections.observableArrayList();
        searchValue = searchTextField.getText();
        CarController carManager = new CarController();

        // TODO: e se não escolher nada?
        if (filterChoice.getValue().equals("Placa")) {
            carManager.searchByPlaca(carResult, searchValue);
        } else if (filterChoice.getValue().equals("Modelo")) {
            carManager.searchByModelo(carResult, searchValue);
        } else if (filterChoice.getValue().equals("Grupo")) {
            carManager.searchByGrupo(carResult, searchValue);
        }

        carTable.setItems(carResult);
    }

    @FXML
    private void handleEditCar() throws IOException {
        Car selectedCar = carTable.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/CarEditDialog.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Editar carro");
        stage.setScene(scene);

        CarEditDialogController controller = loader.getController();
        controller.setNewEntryFlag(false);
        controller.setCar(selectedCar);

        stage.showAndWait();
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
