package application.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.jupiter.api.Assertions.fail;

import application.dbclass.CarDao;
import application.model.Car;

@RunWith(MockitoJUnitRunner.class)
class CarManagerTest {
	CarManager subject;

	@Mock
	CarDao mockDao;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		subject = new CarManager(mockDao);
	}

	@Test
	void testAdd() {
	}

	@Test
	void remove_ShouldCallDeleteOnCarDao() throws ManagerException {
		Car car = new Car();
		car.setId(42);

		//Mockito.doNothing().when(mockDao).delete(car);

		subject.remove(car);

		Mockito.verify(mockDao, Mockito.times(1)).delete(car);
	}
	
	@Test
	void remove_ShouldNotTryToDeleteCarWithNoId() throws ManagerException {
		Car car = new Car();
		
		try {
			subject.remove(car);
			fail("Expected an exception to be thrown");
		} catch (ManagerException expected) {
			// I was expecting an exception.
		}
		
		Mockito.verifyZeroInteractions(mockDao);
	}

	@Test
	void testEdit() {
	}

	@Test
	void testSearchByModel() {
	}

}
