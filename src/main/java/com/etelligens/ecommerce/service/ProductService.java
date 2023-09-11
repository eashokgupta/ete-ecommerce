package com.etelligens.ecommerce.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.etelligens.ecommerce.dto.ProductDTO;
import com.etelligens.ecommerce.dto.ProductDetailsDTO;
import com.etelligens.ecommerce.dto.ProductResponseDTO;


@Component
@Service
public interface ProductService {

	public ProductDTO addNewProduct(ProductDTO product);

	public List<ProductDTO> getAllProducts();

	public ProductDTO getProductById(Long productid);

	public String deleteProductById(Long id);

	public ProductDTO updateProduct(ProductDTO product);

	public List<ProductDTO> getProductByCategoryId(Long id);

	public ProductDTO addProductDetails(String productId, String productMetaData, String metaData,
			MultipartFile[] files) throws IOException;
	public List<ProductResponseDTO> searchProducts(String value);
	
	 public List<ProductResponseDTO> filterProducts(Double minPrice, Double maxPrice);

	public List<ProductDTO> getSalesProduct(String salesType);

	public ProductDetailsDTO getProductResponseDTO(Long id);

	public ProductDTO getProductsByColor(Long id, String color);

	public List<ProductResponseDTO> getProductResponseDTO();

	public ProductResponseDTO getProductResponseDTOById(Long productId);
}
