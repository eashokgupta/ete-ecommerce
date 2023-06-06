package com.etelligens.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.etelligens.ecommerce.dto.MetaData1DTO;
import com.etelligens.ecommerce.dto.ProductDTO;
import com.etelligens.ecommerce.dto.ProductMetaDataDTO;


@Component
@Service
public interface ProductService {

	public ProductDTO addNewProduct(ProductDTO product);

	public List<ProductDTO> getAllProducts();

	public ProductDTO getProductById(Long productid);

	public String deleteProductById(Long id);

	public ProductDTO updateProduct(ProductDTO product);

	public List<ProductDTO> getProductByCategoryId(Long id);

	public ProductDTO addProductDetails(Long id, ProductMetaDataDTO productMetaData1DTO, MetaData1DTO metaData1DTO,
			MultipartFile[] files, Long offerId);
	public List<ProductDTO> searchProducts(String value);
	
	 public List<ProductDTO> filterProducts(Double minPrice, Double maxPrice);
}
