package lt.academy.javau5.pizza.services;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import lt.academy.javau5.pizza.entities.Pizza;
import lt.academy.javau5.pizza.repositories.PizzaRepository;


@SpringBootTest
public class PizzaServiceTests {
	
	@Mock
	PizzaRepository repo;
	
	@InjectMocks
	PizzaService service;
	
	@Test
	void TestReturnsTrueIfPizzaServiceNotNull() {
		Assertions.assertNotNull(service);
	}
	
	@Test
	public void testGetsListofPizzaFromRepo(){
		//Arrange
		// Pizza(int id, String pizzaName, byte[] pizzaPhoto, double pizzaPrice, int pizzaSize,List<Product> products)
		Pizza p1 = new Pizza(1,"Margarita", null, 10.0, 20, null);
		Pizza p2 = new Pizza(2,"Hawaian", null, 10.0, 20, null);
		List<Pizza> pList = Arrays.asList(p1,p2);
		when(repo.findAll()).thenReturn(pList);
		// Act
		List<Pizza> listc = service.findAll();
		//Assert
		Assertions.assertEquals(pList, listc);
		verify(repo).findAll();
	}
	
	@Test
	public void testFindPizzaById(){
		//Arrange
		int pizzaId = 1;
		Optional<Pizza> p1 = Optional.of(new Pizza(1,"Margarita", null, 10.0, 20, null));
		when(repo.findById(pizzaId)).thenReturn(p1);
		// Act
		Pizza returnedPizza = service.findById(pizzaId);
		//Assert
		Assertions.assertEquals(pizzaId, returnedPizza.getId());
		verify(repo).findById(pizzaId);
	}
	
	@Test
	public void testSavePizzaToRepo(){
		//Arrange
		// Pizza(int id, String pizzaName, byte[] pizzaPhoto, double pizzaPrice, int pizzaSize,List<Product> products)
		Pizza p1 = new Pizza(1,"Margarita", null, 10.0, 20, null);
		// Act
		service.save(p1);
		//Assert
		verify(repo).save(p1);
	}
	
	@Test
	public void testDeletePizzaFromRepo(){
		//Arrange
		// Pizza(int id, String pizzaName, byte[] pizzaPhoto, double pizzaPrice, int pizzaSize,List<Product> products)
		Pizza p1 = new Pizza(1,"Margarita", null, 10.0, 20, null);
		// Act
		service.deletePizza(p1);
		//Assert
		verify(repo).delete(p1);
	}
	
	@Test
	public void testUploadPizzaPhotoToRepo(){
		//Arrange
		// Pizza(int id, String pizzaName, byte[] pizzaPhoto, double pizzaPrice, int pizzaSize,List<Product> products)
		int id =  1;
		Optional<Pizza> p1 = Optional.of(new Pizza(1,"Margarita", null, 10.0, 20, null));
		String mockImagePath = "path/Pizzeriaa_BackEnd/src/test/java/lt/academy/javau5/pizza/testPhoto.jpg";
		byte[] photoBytes = null;
		when(repo.findById(id)).thenReturn(p1);
		// Act
		service.uploadPizzaPhoto(id,photoBytes);
		//Assert
		verify(repo).save(p1.get());
		verify(repo).findById(id);
	}
	
	
	
	
	

}
