package com.etelligens.ecommerce.model;

import com.etelligens.ecommerce.auth.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter

public class Orders implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 3936456283918523967L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String customerName;

	private String customerEmail;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	private List<OrderItem> orderItems = new ArrayList<>();

	private Timestamp orderDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="user_id", nullable = true , referencedColumnName = "id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private User user;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="address_id", referencedColumnName = "id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Address address;
}
