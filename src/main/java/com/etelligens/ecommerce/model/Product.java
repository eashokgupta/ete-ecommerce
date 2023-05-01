package com.etelligens.ecommerce.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import jakarta.persistence.CascadeType;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Product")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7556208287679752588L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer quantity;
	private Integer categoryId;
	private String name;
	private String url;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "productimages", joinColumns = @JoinColumn(name = "product", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "product_images", referencedColumnName = "id"))
	private Set<ProductImages> images;

	private String shortDescription;

	private String description;

	private String sku;
	private String upc;
	private String category;
	private String label;
	private Double price;
	private Boolean visibility;
	private Boolean b2b;
	private Timestamp createdAt;
	private Timestamp updatedAt;

}
