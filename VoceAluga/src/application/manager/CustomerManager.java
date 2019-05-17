package application.manager;

import application.dbclass.CustomerDao;
import application.model.Customer;
import application.util.DateUtil;
import javafx.scene.control.Alert;

import java.util.List;
import java.util.Map;

public class CustomerManager {
	private CustomerDao dao;
	
	public CustomerManager(CustomerDao dao) {
		this.dao = dao;
	}

	public void add(Map<String, String> mapOfFields) throws ManagerException {
		try {
			Form<Customer> form = new Form<>(Customer.class);
			form.addInfo(mapOfFields);
			validateCustomerFields(form);
			
			Customer customer = new Customer();
			form.fillObjectAttributes(customer);
			
			dao.insert(customer);
		}
		catch(RuntimeException e) {
			String lowerCasedMessage = e.getMessage().toLowerCase();
			if(lowerCasedMessage.contains("duplicate entry") && lowerCasedMessage.contains("cpf")) {
				throw new ManagerException("J� existe um cliente com este CPF");
			}
			else {
				throw e;
			}
		}
	}
	
	public void remove(Customer customer) {
		dao.delete(customer);
	}
	
	public void edit(Map<String, String> mapOfFields) throws ManagerException {
		Form<Customer> form = new Form<>(Customer.class);
		form.addInfo(mapOfFields);
		validateCustomerFields(form);
		
		Customer customer = new Customer();
		form.fillObjectAttributes(customer);
		
		dao.update(customer);
	}
	
	public <L extends List<Customer>> void searchByCpf(L list, String cpf) {
		dao.selectToList(list, String.format("Cpf=\"%s\"", cpf));
	}
	
	public <L extends List<Customer>> void searchByFirstName(L list, String name) {
		dao.selectToList(list, String.format("FirstName=\"%s\"", name));
	}
	
	public <L extends List<Customer>> void searchByLastName(L list, String name) {
		dao.selectToList(list, String.format("LastName=\"%s\"", name));
	}
	
	public <L extends List<Customer>> void searchAll(L list) {
		dao.selectToList(list);
	}
	
	private static void validateCustomerFields(Form<Customer> form) throws ManagerException {
		String errorMessage = "";

	    if (!isNameValid(form.getAttribute("firstName"))) {
	        errorMessage += "Nome inv�lido.\n";
	    }

	    if (!isNameValid(form.getAttribute("lastName"))) {
	        errorMessage += "Sobrenome inv�lido.\n";
	    }

	    if (!isCpfValid(form.getAttribute("cpf"))) {
	        errorMessage += "CPF inv�lido.\n";
	    }

	    if (!isCnhValid(form.getAttribute("cnh"))) {
	        errorMessage += "CNH inv�lida.\n";
	    }

	    if (!isBirthdayValid(form.getAttribute("birthday"))) {
	        errorMessage += "Data de nascimento inv�lida!\n";
	    }

	    if (!isCellphoneValid(form.getAttribute("cellphone"))) {
	        errorMessage += "N�mero de celular inv�lido.\n";
	    }

	    if (errorMessage.length() != 0) {
	    	throw new ManagerException(errorMessage);
	    }
	}
	    
    private static boolean isNameValid(String name) {
        return (name != null && name.length() > 0);
    }

    private static boolean isCpfValid(String cpf) {
        return (cpf != null && cpf.matches("\\d{11}"));
    }

    //private static boolean isRgValid(String rg) {
//	        return (rg != null && rg.matches("\\d{9}"));
    //}

    private static boolean isCnhValid(String cnh) {
        return (cnh != null && cnh.matches("\\d{10}"));
    }

    //private static boolean isAddressValid(String address) {
//	        return (address != null && address.length() > 0);
    //}

    // Celular é valido se tiver pelo menos 8 algarismos
    private static boolean isCellphoneValid(String cellphone) {
        return (cellphone != null && cellphone.matches("\\d{8}\\d*"));
    }

    private static boolean isBirthdayValid(String birthday) {
        return (birthday != null && birthday.length() > 0 && DateUtil.validDate(birthday));

	}
}



