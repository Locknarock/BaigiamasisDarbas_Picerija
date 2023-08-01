package lt.academy.javau5.pizza.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import lt.academy.javau5.pizza.entities.Pizza;
import lt.academy.javau5.pizza.entities.Product;
import lt.academy.javau5.pizza.services.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

	private ProductService productService;

	@Autowired
	public ProductController(ProductService theProductService) {
		productService = theProductService;
	}

	// Show all products
	@GetMapping("/products")
	public List<Product> findAll() {
		return productService.findAll();
	}

	// Show product by ID
	@GetMapping("/product/{productId}")
	public Product getproduct(@PathVariable int productId) {
		Product theProduct = productService.findById(productId);
		if (theProduct == null) {
			throw new RuntimeException("Produktas su nr: " + productId + " nerastas");
		}
		return theProduct;
	}

	// Add product
	@PostMapping("/product")
	public String addProduct(@RequestBody Product theProduct) {
		String tempProduct=theProduct.getProductName();
		if (productService.productAlreadyExists(tempProduct)) {
			return "Toks produktas jau egzistuoja.";
		}

		theProduct.setId(0);
		productService.save(theProduct);
		return "Produktas pridėtas";
	}

	// Delete product
	@Transactional
	@DeleteMapping("/product/{productId}")
	public String deleteProduct(@PathVariable int productId) {

		Product tempProduct = productService.findById(productId);

		if (tempProduct != null) {

			List<Pizza> pizzas = tempProduct.getPizza();
			if (pizzas != null) {
				for (Pizza pizza : pizzas) {
					pizza.getProducts().remove(tempProduct);
				}
			}
			productService.delete(tempProduct);
			return "Produktas su nr: " + productId + " ištrinta";
		} else {
			throw new RuntimeException("Produktas su nr: " + productId + " nerastas");
		}

	}

	// Update product

	@PutMapping("/product")
	public Product updateProduct(@RequestBody Product theProduct) {
		Product dbProduct = productService.save(theProduct);
		return dbProduct;
	}

	@GetMapping("/dummyProduct")
	public void addProductDummies() {
		productService.seedProductRepository();

	}

}
