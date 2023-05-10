package com.etelligens.ecommerce.dto;

	import java.sql.Timestamp;

	import lombok.AllArgsConstructor;
	import lombok.Getter;
	import lombok.NoArgsConstructor;
	import lombok.Setter;

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public class CartDTO {
		private Integer id;
	    private Integer productId;
	    private Integer userId;
	    private Integer quantity;
	    private Integer price;
	    private Timestamp createdAt;
	    private Timestamp updatedAt;
	}



