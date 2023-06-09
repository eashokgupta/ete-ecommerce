package com.etelligens.ecommerce.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7556208287679752588L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer quantity;

	private String name;

	private String shortDescription;

	private String description;

	private String color;

	private String sku;

	private String upc;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false, referencedColumnName = "id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Category category;

	@OneToMany(mappedBy = "product")
	private List<ProductMetaData> productMetaData;
	
	private String brand;

	private float rating;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "offer_id", nullable = false, referencedColumnName = "id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Offer offer;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sales_id", nullable = false, referencedColumnName = "id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Sales sales;

	private String url;

	private String label;

	private Double price;
	
	private Double priceAfterDiscount;

	private Boolean visibility;

	private Boolean b2b;

	private Timestamp createdAt;

	private Timestamp updatedAt;

}
