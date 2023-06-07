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
public class ProductDTO extends CommanPropertiesDTO {

	private Long id;

	private String category;

	private List<ProductMetaDataDTO> productMetaData;

	private Long offerId;
	
	private SalesDTO sales;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		ProductDTO other = (ProductDTO) obj;
		return Objects.equals(id, other.id) && Objects.equals(category, other.category)
				&& Objects.equals(productMetaData, other.productMetaData) && Objects.equals(offerId, other.offerId)  && Objects.equals(sales, other.sales);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, category, productMetaData, offerId, sales);
	}
}
