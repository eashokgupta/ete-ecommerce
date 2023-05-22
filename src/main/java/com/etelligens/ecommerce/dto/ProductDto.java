package com.etelligens.ecommerce.dto;

import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto extends CommanPropertiesDTO {
	
	private Long id;

	private String category;
	
	private List<ProductMetaDataDTO> productMetaData;
	
	 @Override
	    public boolean equals(Object obj) {
	        if (this == obj) {
	            return true;
	        }
	        if (obj == null || getClass() != obj.getClass()) {
	            return false;
	        }
	        ProductDto other = (ProductDto) obj;
	        return Objects.equals(id, other.id) && Objects.equals(category, other.category) && Objects.equals(productMetaData, other.productMetaData);
	    }
	 
	 @Override
	    public int hashCode() {
	        return Objects.hash(id, category, productMetaData);
	    }
}
