package com.etelligens.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
public class OrderItem implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 4786533181732339080L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String productCode;
	
	private String productName;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id", nullable = true, referencedColumnName = "id")
	private Orders orders;
	
	private int quantity;
	private float price;

}
