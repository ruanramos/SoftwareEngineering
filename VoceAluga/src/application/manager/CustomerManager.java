package application.manager;

import application.dbclass.CustomerDao;
import application.model.Customer;
import java.util.List;

public class CustomerManager {
	
	public static void add(Customer customer) throws ManagerException {
		try {
			CustomerDao dao = new CustomerDao();
			validateCustomerFields(customer);
			dao.insert(customer);
		}
		catch(RuntimeException e) {
			String lowerCasedMessage = e.getMessage().toLowerCase();
			if(lowerCasedMessage.contains("duplicate entry") && lowerCasedMessage.contains("cpf")) {
				throw new ManagerException("Já existe um cliente com este CPF");
			}
			else {
				throw e;
			}
		}
	}
	
	public static void remove(Customer customer) {
		CustomerDao dao = new CustomerDao();
		dao.delete(customer);
	}
	
	public static void edit(Customer customer) throws ManagerException {
		CustomerDao dao = new CustomerDao();
		validateCustomerFields(customer);
		dao.update(customer);
	}
	
	public static <L extends List<Customer>> void searchByCpf(L list, String cpf) {
		CustomerDao dao = new CustomerDao();
		dao.selectToList(list, String.format("Cpf=\"%s\"", cpf));
	}
	
	public static <L extends List<Customer>> void searchByFirstName(L list, String name) {
		CustomerDao dao = new CustomerDao();
		dao.selectToList(list, String.format("FirstName=\"%s\"", name));
	}
	
	public static <L extends List<Customer>> void searchByLastName(L list, String name) {
		CustomerDao dao = new CustomerDao();
		dao.selectToList(list, String.format("LastName=\"%s\"", name));
	}
	
	public static <L extends List<Customer>> void searchAll(L list) {
		CustomerDao dao = new CustomerDao();
		dao.selectToList(list);
	}
	
	private static void validateCustomerFields(Customer customer) throws ManagerException {
		validateCpf(customer.getCpf());
		validateCnh(customer.getCnh());
	}
	
	private static void validateCpf(String cpf) throws ManagerException {
		if(cpf.length() != 11) {
			throw new ManagerException("CPF inválido");
		}
	}
	
	private static void validateCnh(String cnh) throws ManagerException {
		if(cnh.length() != 11) {
			throw new ManagerException("CNH inválida");
		}
	}
}
