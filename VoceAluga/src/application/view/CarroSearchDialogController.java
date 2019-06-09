package application.view;

import application.Main;
import application.controller.CarroController;
import application.dbclass.CarroDao;
import application.model.Carro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class CarroSearchDialogController {
    @FXML
    private ChoiceBox<String> filterChoice;
    @FXML
    private TextField searchTextField;
    @FXML
    private TableView<Carro> carroTable;
    @FXML
    private TableColumn<Carro, String> placaColumn;
    @FXML
    private TableColumn<Carro, String> modeloColumn;
    @FXML
    private TableColumn<Carro, String> grupoColumn;

    private String filter = "";
    private String searchValue = "";

    @FXML
    private void initialize() {
        filterChoice.getItems().addAll("Placa", "Modelo", "Grupo");
        filterChoice.setValue("Grupo");

        carroTable.setPlaceholder(new Label("Nenhum veículo encontrado."));

        placaColumn.setCellValueFactory(cellData -> cellData.getValue().placaProperty());
        modeloColumn.setCellValueFactory(cellData -> cellData.getValue().modeloProperty());
        grupoColumn.setCellValueFactory(cellData -> cellData.getValue().grupoProperty());
    }

    @FXML
    private void handleSearchCar() {
        ObservableList<Carro> carroResult = FXCollections.observableArrayList();
        searchValue = searchTextField.getText();
        CarroController carroController = new CarroController();

        // TODO: e se não escolher nada?
        if (filterChoice.getValue().equals("Placa")) {
            carroController.searchByPlaca(carroResult, searchValue);
        } else if (filterChoice.getValue().equals("Modelo")) {
            carroController.searchByModelo(carroResult, searchValue);
        } else if (filterChoice.getValue().equals("Grupo")) {
            carroController.searchByGrupo(carroResult, searchValue);
        }

        carroTable.setItems(carroResult);
    }

    @FXML
    private void handleEditCar() throws IOException {
        Carro selectedCarro = carroTable.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/CarroEditDialog.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Editar carro");
        stage.setScene(scene);

        CarroEditDialogController controller = loader.getController();
        controller.setNewEntryFlag(false);
        controller.setCarro(selectedCarro);

        stage.showAndWait();
    }

    @FXML
    private void handleDeleteCar() {
        int selectedIndex = carroTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Carro selectedCarro = carroTable.getItems().get(selectedIndex);
            carroTable.getItems().remove(selectedIndex);

            // TODO: não usar o DAO diretamente
            CarroDao carroDao = new CarroDao();
            carroDao.delete(selectedCarro);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nenhuma seleção");
            alert.setHeaderText("Nenhuma Carro Selecionadp");
            alert.setContentText("Por favor, selecione um carro na tabela.");
            alert.showAndWait();
        }
    }
}
