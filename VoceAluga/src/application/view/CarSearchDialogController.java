package application.view;

import application.Main;
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
    private TableColumn<Car, Number> idColumn;
    @FXML
    private TableColumn<Car, String> modelColumn;
    @FXML
    private TableColumn<Car, String> categoryColumn;

    private String filter = "";
    private String searchValue = "";

    @FXML
    private void initialize() {
        filterChoice.getItems().addAll("Código", "Modelo", "Classe");
        filterChoice.setValue("Classe");

        carTable.setPlaceholder(new Label("Nenhum veículo encontrado."));

        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        modelColumn.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
    }

    @FXML
    private void handleSearchCar() {
        // TODO: e se não escolher nada?
        if (filterChoice.getValue().equals("Código")) {
            filter = "Id";
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
