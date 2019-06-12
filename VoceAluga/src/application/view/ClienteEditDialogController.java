package application.view;

import application.controller.ControllerException;
import application.controller.ClienteController;
import application.model.Cliente;
import application.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ClienteEditDialogController {

    @FXML
    private Node rootNode;
    @FXML
    private TextField nomeField;
    @FXML
    private TextField enderecoField;
    @FXML
    private TextField telefoneField;
    @FXML
    private TextField cpfField;
    @FXML
    private DatePicker nascimentoPicker;
    @FXML
    private DatePicker validadeCnhPicker;

    private Cliente cliente;
    private boolean newEntryFlag;

    @FXML
    public void initialize() {
        nascimentoPicker.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                return DateUtil.format(date);
            }

            @Override
            public LocalDate fromString(String string) {
                return DateUtil.parse(string);
            }
        });

        validadeCnhPicker.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                return DateUtil.format(date);
            }

            @Override
            public LocalDate fromString(String string) {
                return DateUtil.parse(string);
            }
        });
    }

    public void setNewEntryFlag(boolean newEntryFlag) {
        cliente = new Cliente();
        this.newEntryFlag = newEntryFlag;
    }
    // pode fazer a flag default true e fazê-la falsa aqui
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        cpfField.setText(cliente.getCpf());
        nomeField.setText(cliente.getNome());
        enderecoField.setText(cliente.getEndereco());
        telefoneField.setText(cliente.getTelefone());
        nascimentoPicker.setValue(cliente.getNascimento());
        validadeCnhPicker.setValue(cliente.getValidadecnh());
    }

    @FXML
    private void handleOk() {
        Map<String,String> clienteFields = new HashMap<>();
        clienteFields.put("cpf", cpfField.getText());
        clienteFields.put("nome", nomeField.getText());
        clienteFields.put("endereco", enderecoField.getText());
        clienteFields.put("telefone", telefoneField.getText());
        clienteFields.put("nascimento", nascimentoPicker.getValue().toString());
        clienteFields.put("validadecnh", validadeCnhPicker.getValue().toString());

        try {
            ClienteController clienteController = new ClienteController();

            if (newEntryFlag) {
                clienteController.add(clienteFields);

            } else {
                clienteController.edit(clienteFields);
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
