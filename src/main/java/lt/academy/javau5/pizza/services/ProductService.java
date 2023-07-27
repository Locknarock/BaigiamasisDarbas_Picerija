package lt.academy.javau5.pizza.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.academy.javau5.pizza.entities.Pizza;
import lt.academy.javau5.pizza.entities.Product;
import lt.academy.javau5.pizza.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	
//	public ProductService(ProductRepository theProductRepository) {
//		productRepository = theProductRepository;
//	}
	
	
	public List<Product> findAll() {
			return productRepository.findAll();
	}


	public Product findById(int productId) {
		Optional<Product> result = productRepository.findById(productId);
		;
		Product theProduct = null;
		if (result.isPresent()) {
			theProduct = result.get();
		} else {
			throw new RuntimeException("Produktas su nr: " + productId + " nerastas");
		}
		return theProduct;
	}
	
	public Product save(Product theProduct) {
		return productRepository.save(theProduct);
	}
	
	public void deleteById(int productId) {
		productRepository.deleteById(productId);

	}
	
	


	
	

}
