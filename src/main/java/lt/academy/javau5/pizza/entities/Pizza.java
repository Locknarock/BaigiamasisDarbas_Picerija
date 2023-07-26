package lt.academy.javau5.pizza.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="pizza")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pizza {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	@Column(name="pizzaName")
	private String pizzaName;
		
	
	@Column(name="pizzaPhoto", length=1048576)
	
	private byte[] pizzaPhoto;
	
	@Column(name="pizzaPrice")
	private double pizzaPrice;
	
	@Column(name="pizzaSize")
	private int pizzaSize;
	
	@OneToMany(mappedBy="pizza", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Product> products;

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
