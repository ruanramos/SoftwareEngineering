package application.view;
import application.controller.CarroController;
import application.controller.ClienteController;
import application.controller.ControllerException;
import application.controller.LocacaoController;
import application.model.Carro;
import application.model.Cliente;
import application.model.Locacao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class LocacaoEditDialogController {

    @FXML
    Node rootNode;
    @FXML
    TextField idField;
    @FXML
    ChoiceBox<String> idclienteField;
    @FXML
    ChoiceBox<String> idcarroField;
    @FXML
    TextField problemaField;
 

    private Locacao locacao;
    private boolean newEntryFlag;

    @FXML
    public void initialize() {
        // bloqueia edição do id
        idField.setDisable(true);

    }

    public void setNewEntryFlag(boolean newEntryFlag) {
        locacao = new Locacao();
        this.newEntryFlag = newEntryFlag;
        this.idclienteField.setItems(getIdsClienteField());
        this.idcarroField.setItems(getIdsCarroField());
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
        this.idField.setText(String.valueOf(locacao.getId()));

        this.problemaField.setText(String.valueOf(locacao.getProblema()));
    }
    public ObservableList<String> getIdsClienteField() {
    	ClienteController clienteController = new ClienteController();
    	ObservableList<Cliente> clienteResult = FXCollections.observableArrayList();
    	ObservableList<String> clientesCriados = FXCollections.observableArrayList();
    	clienteController.searchByCpf(clienteResult,"");
    	for( Cliente cliente : clienteResult){
    		clientesCriados.add(cliente.getCpf());
        }
    	return clientesCriados;
    }
    public ObservableList<String> getIdsCarroField() {
    	CarroController carroController = new CarroController();
    	ObservableList<Carro> carroResult = FXCollections.observableArrayList();
    	ObservableList<String> placasCriadas = FXCollections.observableArrayList();
    	carroController.searchByPlaca(carroResult,"");
    	for( Carro carro : carroResult){
    		placasCriadas.add(carro.getPlaca());
        }
    	return placasCriadas;
    }
    
    @FXML
    private void handleOk() {
        Map<String, String> locacaoFields = new HashMap<>();
       

        try {
            LocacaoController locacaoManager = new LocacaoController();
            if (newEntryFlag) {
                locacaoManager.add(locacaoFields);
            } else {
                locacaoFields.put("id", String.valueOf(locacao.getId()));
                locacaoManager.edit(locacaoFields);
            }
        } catch (ControllerException e) {
            e.printStackTrace();
        }

        this.getStage().close();
    }

    @FXML
    private void handleCancel() {
        this.getStage().close();
    }

    private Stage getStage() {
        return (Stage) rootNode.getScene().getWindow();
    }
}
