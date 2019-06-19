package application.test;

import application.model.Carro;
import application.controller.CarroController;
import application.controller.ControllerException;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CarroTest {

	@Test
	void addsValidClientAndEditsItAndRemovesIt() throws ControllerException {
		Carro c1;
		HashMap<String,String> map = new HashMap<String,String>();
		CarroController controller = new CarroController();

		map.put("placa","LUX-1234");
		map.put("quilometragem","8000.8");
		map.put("grupo","B");
		map.put("ano","11");
		map.put("modelo","novo");
		c1 = controller.add(map);
		
		assertEquals(11, c1.getAno());
		
		map.put("ano","12");
		c1 = controller.edit(map);
		
		assertEquals(12, c1.getAno());
		
		controller.remove(c1);
	}

}
