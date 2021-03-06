package application.controller;

import application.dbclass.ClienteDao;
import application.model.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Map;

public class ClienteController {
	private ClienteDao dao;

	public ClienteController() {
		this.dao = new ClienteDao();
	}
    public ObservableList<String> getIdsClienteField() {
    	
    	ObservableList<Cliente> clienteResult = FXCollections.observableArrayList();
    	ObservableList<String> clientesCriados = FXCollections.observableArrayList();
    	searchByCpf(clienteResult,"");
    	for( Cliente cliente : clienteResult){
    		clientesCriados.add(cliente.getCpf());
        }
    	return clientesCriados;
    }

	public Cliente add(Map<String, String> mapOfFields) throws ControllerException {
		try {
			Form<Cliente> form = new Form<>(Cliente.class);
			form.addInfo(mapOfFields);
			validateClienteFields(form);
			Cliente cliente = new Cliente();
			form.fillObjectAttributes(cliente);
			dao.insert(cliente);
			return cliente;
		}
		catch(RuntimeException e) {
			String lowerCasedMessage = e.getMessage().toLowerCase();
			if(lowerCasedMessage.contains("duplicate entry") && lowerCasedMessage.contains("cpf")) {
				throw new ControllerException("Ja existe um cliente com este cpf");
			}
			else {
				throw e;
			}
		}
	}

	public void remove(Cliente cliente) {
		dao.delete(cliente);
	}

	public Cliente edit(Map<String, String> mapOfFields) throws ControllerException {
		Form<Cliente> form = new Form<>(Cliente.class);
		form.addInfo(mapOfFields);
		validateClienteFields(form);

		Cliente cliente = new Cliente();
		form.fillObjectAttributes(cliente);

		dao.update(cliente);
		return cliente;
	}

	public <L extends List<Cliente>> void searchByCpf(L list, String cpf) {
		dao.selectToList(list, "where cpf like '%" + cpf + "%'");
	}

	public <L extends List<Cliente>> void searchByNome(L list, String name) {
		dao.selectToList(list, "where nome like '%" + name + "%'");
	}

	public <L extends List<Cliente>> void searchAll(L list) {
		dao.selectToList(list);
	}

	private static void validateClienteFields(Form<Cliente> form) throws ControllerException {
		String errorMessage = "";

		if (!isCpfValid(form.getAttribute("cpf"))) {
			errorMessage += "CPF invalido.\n";
		}
		
		if (!isNameValid(form.getAttribute("nome"))) {
			errorMessage += "Nome invalido.\n";
		}
		
		if (!isAddressValid(form.getAttribute("endereco"))) {
			errorMessage += "Endereco invalido.\n";
		}
		
		if (!isCellphoneValid(form.getAttribute("telefone"))) {
			errorMessage += "Numero de telefone invalido.\n";
		}
		
		if (!isBirthdayValid(form.getAttribute("nascimento"))) {
			errorMessage += "Data de nascimento invalida!\n";
		}
		
		if (!isCnhExpiration(form.getAttribute("validadecnh"))) {
			errorMessage += "Validade da CNH invalida!\n";
		}

		if (errorMessage.length() != 0) {
			throw new ControllerException(errorMessage);
		}
	}

	private static boolean isNameValid(String name) {
		return (name != null && name.length() > 0);
	}

	private static boolean isCpfValid(String cpf) {
		return (cpf != null && cpf.matches("\\d{11}"));
	}


	private static boolean isAddressValid(String address) {
		return (address != null && address.length() > 0);
	}

	// Celular é valido se tiver pelo menos 8 algarismos
	private static boolean isCellphoneValid(String cellphone) {
		return (cellphone != null && cellphone.matches("\\d{8}\\d*"));
	}

	private static boolean isBirthdayValid(String birthday) {
		return (birthday != null);
	}
	
	private static boolean isCnhExpiration(String cnh) {
		return (cnh != null);
	}
}
