package lt.academy.javau5.pizza.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="pizza")
public class Pizza {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	
	@Column(name="pizzaName")
	private String pizzaName;
		
	
	@Column(name="pizzaPhoto", length=1048576)
	private byte[] pizzaPhoto;
	
	@Column(name="pizzaPrice")
	private double pizzaPrice;
	
	@Column(name="pizzaSize")
	private int pizzaSize;
	
	@JoinTable(name = "pizza_products", 
				joinColumns = @JoinColumn(name = "pizza_id"),
				inverseJoinColumns = @JoinColumn(name = "product_id"))
	@ManyToMany(fetch=FetchType.LAZY,
	cascade= {CascadeType.PERSIST,
			CascadeType.MERGE})//,CascadeType.DETACH,CascadeType.REFRESH})
	private List<Product> products;

	public Pizza(String pizzaName, byte[] pizzaPhoto, double pizzaPrice, int pizzaSize) {		
		this.pizzaName = pizzaName;
		this.pizzaPhoto = pizzaPhoto;
		this.pizzaPrice = pizzaPrice;
		this.pizzaSize = pizzaSize;	
	}

	public void addProduct(Product theProduct) {
		if(products==null) {
			products = new ArrayList<>();	
		}
		products.add(theProduct);
	}

	//If needed
	/*
	@Column(name="isSpicy")
	private boolean isSpicy;
	
	@Column(name="hasMeat")
	private boolean hasMeat;
	
	@Column(name="soldOut")
	private boolean soldOut;
	*/
	
}
