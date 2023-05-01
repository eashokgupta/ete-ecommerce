package com.etelligens.ecommerce.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.etelligens.ecommerce.dto.ProductDto;
import com.etelligens.ecommerce.dto.ProductImagesDTO;
import com.etelligens.ecommerce.model.Product;
import com.etelligens.ecommerce.service.ProductService;

@RestController
@RequestMapping("/prod")
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping("/addProduct")
	public ProductDto addProduct(@RequestBody ProductDto product) {

		return productService.saveProduct(product);

	}

	@GetMapping("/allProducts")
	public List<Product> findAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/product/{id}")
	public Product findProductById(@PathVariable int id) {
		return productService.getProductById(id);
	}

	@PutMapping("/update")
	public ProductDto updateProduct(@RequestBody ProductDto product) {
		return productService.updateProduct(product);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable int id) {
		return productService.deleteProductById(id);
	}

	@PostMapping("/upload")
	public ResponseEntity<List<ProductImagesDTO>> uploadFile(@RequestParam("files") MultipartFile[] files) {
		
		try {
			List<ProductImagesDTO> productImagesDTOs = null;
			List<String> fileNames = new ArrayList<>();
			Arrays.asList(files).stream().forEach(file -> {
				try {
				final	ProductImagesDTO productImagesDTO = productService.store(file);
				productImagesDTOs.add(productImagesDTO);
				} catch (IOException e) {
					e.printStackTrace();
				}
				fileNames.add(file.getOriginalFilename());
			});
			return ResponseEntity.status(HttpStatus.OK).body(productImagesDTOs);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
	}
}
