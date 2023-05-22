package com.etelligens.ecommerce.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.etelligens.ecommerce.auth.model.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Cart")
public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7556208287679752588L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
	private User userId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id", nullable = false, referencedColumnName = "id")
	private Product productId;
	
	private Integer quantity;
	
	private Integer price;
	
	private Timestamp createdAt;
	
	private Timestamp updatedAt;

}
