package application.test;

import application.model.Cliente;
import application.controller.ClienteController;
import application.controller.ControllerException;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ClienteTest {

	@Test
	void addsValidClientAndEditsItAndRemovesIt() throws ControllerException {
		Cliente c1 = new Cliente();
		HashMap<String,String> map = new HashMap<String,String>();
		ClienteController controller = new ClienteController();
		ArrayList<Cliente> list = new ArrayList<Cliente>();

		map.put("cpf","12345678910");
		map.put("endereco","rua oito");
		map.put("nascimento","14/06/2019");
		map.put("nome","Debis Dibris");
		map.put("telefone","22342212");
		map.put("validadecnh","21/11/2020");
		controller.add(map);
		
		controller.searchAll(list);
		for (Cliente c : list) if(c.getNome().equals("Debis Dibris")) c1 = c;
		assertEquals("Debis Dibris", c1.getNome());
		
		list = new ArrayList<Cliente>();
		map.put("nome","Debus Dubrus");
		controller.edit(map);
		controller.searchAll(list);
		for (Cliente c : list) if(c.getNome().equals("Debus Dubrus")) c1 = c;
		assertEquals("Debus Dubrus", c1.getNome());
		
		controller.remove(c1);
	}

}
