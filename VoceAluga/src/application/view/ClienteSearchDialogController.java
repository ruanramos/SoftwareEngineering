package application.view;

import application.Main;
import application.controller.ClienteController;
import application.dbclass.ClienteDao;
import application.model.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ClienteSearchDialogController {

    @FXML
    private ChoiceBox<String> filterChoice;
    @FXML
    private TextField searchTextField;
    @FXML
    private TableView<Cliente> clienteTable;
    @FXML
    private TableColumn<Cliente, String> cpfColumn;
    @FXML
    private TableColumn<Cliente, String> nomeColumn;
    @FXML
    private TableColumn<Cliente, String> telefoneColumn;

    private Main main;
    private String filter = "";
    private String searchValue = "";

    @FXML
    private void initialize() {
        filterChoice.getItems().addAll("CPF", "Nome", "Telefone");
        filterChoice.setValue("CPF");

        clienteTable.setPlaceholder(new Label("Nenhum cliente encontrado."));

        cpfColumn.setCellValueFactory(cellData -> cellData.getValue().cpfProperty());
        nomeColumn.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        telefoneColumn.setCellValueFactory(cellData -> cellData.getValue().telefoneProperty());
    }

    @FXML
    private void handleSearchCustomer() {
        ObservableList<Cliente> clienteResult = FXCollections.observableArrayList();
        searchValue = searchTextField.getText();
        ClienteController clienteController = new ClienteController();

        // TODO: e se não escolher nada?
        if (filterChoice.getValue().equals("CPF")) {
            clienteController.searchByCpf(clienteResult, searchValue);
        } else if (filterChoice.getValue().equals("Nome")) {
            clienteController.searchByNome(clienteResult, searchValue);
        } else if (filterChoice.getValue().equals("Telefone")) {
//            clienteController.searchByLastName(clienteResult, searchValue);
        }

        clienteTable.setItems(clienteResult);
     }

    @FXML
    private void handleEditCustomer() throws IOException {
        Cliente selectedCliente = clienteTable.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/ClienteEditDialog.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Editar Cliente");
//        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);

        ClienteEditDialogController controller = loader.getController();
        controller.setNewEntryFlag(false);
        controller.setCliente(selectedCliente);

        stage.showAndWait();
    }

    @FXML
    private void handleDeleteCustomer() {
        int selectedIndex = clienteTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Cliente selectedCliente = clienteTable.getItems().get(selectedIndex);
            clienteTable.getItems().remove(selectedIndex);

            // TODO: não usar o DAO diretamente
            ClienteDao clienteDao = new ClienteDao();
            clienteDao.delete(selectedCliente);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nenhuma seleção");
            alert.setHeaderText("Nenhuma Pessoa Selecionada");
            alert.setContentText("Por favor, selecione um cliente na tabela.");
            alert.showAndWait();
        }
    }
}
