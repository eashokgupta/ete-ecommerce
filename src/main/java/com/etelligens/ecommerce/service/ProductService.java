package com.etelligens.ecommerce.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.etelligens.ecommerce.dto.MetaData1DTO;
import com.etelligens.ecommerce.dto.ProductDto;
import com.etelligens.ecommerce.dto.ProductMetaDataDTO;


@Component
@Service
public interface ProductService {

	public ProductDto saveProduct(ProductDto product);

	public List<ProductDto> getAllProducts();

	public ProductDto getProductById(Long productid);

	public String deleteProductById(Long id);

	public ProductDto updateProduct(ProductDto product);
	public ProductDto store(ProductDto productDto) throws IOException;

	public List<ProductDto> getProductByCategoryId(Long id);

	public ProductDto addProductDetails(Long id, ProductMetaDataDTO productMetaData1DTO, MetaData1DTO metaData1DTO,
			MultipartFile[] files);
	public List<ProductDto> searchProducts(String value);
}
