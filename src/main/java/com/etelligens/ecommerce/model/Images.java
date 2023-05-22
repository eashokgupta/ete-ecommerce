package com.etelligens.ecommerce.model;


import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Images implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5008135862648871295L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String color;
	
	@Lob
	private byte[] img;
	
	private Date createdAt;
	
	private Date updatedAt;

}
