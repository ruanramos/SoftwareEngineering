package application.view;

import application.Main;
import application.controller.LocacaoController;
import application.dbclass.LocacaoDao;
import application.dbclass.ReservaDao;
import application.model.Locacao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LocacaoSearchDialogController {
    @FXML
    private ChoiceBox<String> filterChoice;
    @FXML
    private TextField searchTextField;
    @FXML
    private TableView<Locacao> locacaoTable;
    
    @FXML
    private TableColumn<Locacao, String> cpfColumn;
    @FXML
    private TableColumn<Locacao, String> placaColumn;
    
    private String filter = "";
    private String searchValue = "";

    @FXML
    private void initialize() {
       	filterChoice.getItems().addAll("CPF","Placa");
        filterChoice.setValue("CPF");
        locacaoTable.setPlaceholder(new Label("Nenhuma locação encontrada"));
        cpfColumn.setCellValueFactory(cellData -> cellData.getValue().idclienteProperty());
        placaColumn.setCellValueFactory(cellData -> cellData.getValue().idcarroProperty());
    }

    @FXML
    private void handleSearchLocacao() {
        ObservableList<Locacao> locacaoResult = FXCollections.observableArrayList();
        searchValue = searchTextField.getText();
        LocacaoController locacaoController = new LocacaoController();

        if (filterChoice.getValue().equals("CPF")) {
            locacaoController.searchByCpf(locacaoResult, searchValue);
        } else if (filterChoice.getValue().equals("Placa")) {
            locacaoController.searchByPlaca(locacaoResult, searchValue);
        }
        locacaoTable.setItems(locacaoResult);    	
    }

    @FXML
    private void handleEditLocacao() throws IOException {
        Locacao selectedLocacao= locacaoTable.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/LocacaoEditDialog.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Editar locacao");
        stage.setScene(scene);

        LocacaoEditDialogController controller = loader.getController();
        controller.setNewEntryFlag(false);
        controller.setLocacao(selectedLocacao);

        stage.showAndWait();
    }

    @FXML
    private void handleDeleteLocacao() {
        int selectedIndex = locacaoTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Locacao selectedLocacao = locacaoTable.getItems().get(selectedIndex);
            locacaoTable.getItems().remove(selectedIndex);

            LocacaoDao locacaoDao = new LocacaoDao();
            locacaoDao.delete(selectedLocacao);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nenhuma seleção");
            alert.setHeaderText("Nenhuma Locação Selecionada");
            alert.setContentText("Por favor, selecione uma locação na tabela.");
            alert.showAndWait();
        }
    }
}
