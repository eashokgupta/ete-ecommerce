package com.etelligens.ecommerce.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.etelligens.ecommerce.dto.ProductDto;
import com.etelligens.ecommerce.dto.ProductImagesDTO;
import com.etelligens.ecommerce.model.Product;


@Component
@Service
public interface ProductService {

	public ProductDto saveProduct(ProductDto product);

	public List<Product> getAllProducts();

	public Product getProductById(int productid);

	public String deleteProductById(int id);

	public ProductDto updateProduct(ProductDto product);
	public ProductImagesDTO store(MultipartFile file) throws IOException;
}
