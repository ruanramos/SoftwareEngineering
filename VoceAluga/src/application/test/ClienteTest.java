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
		Cliente c1;
		HashMap<String,String> map = new HashMap<String,String>();
		ClienteController controller = new ClienteController();

		map.put("cpf","12345678910");
		map.put("endereco","rua oito");
		map.put("nascimento","14/06/2019");
		map.put("nome","Debis Dibris");
		map.put("telefone","22342212");
		map.put("validadecnh","21/11/2020");
		c1 = controller.add(map);
		assertEquals("Debis Dibris", c1.getNome());
		
		map.put("nome","Debus Dubrus");
		c1 = controller.edit(map);
		assertEquals("Debus Dubrus", c1.getNome());
		
		controller.remove(c1);
	}

}
