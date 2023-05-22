package com.etelligens.ecommerce.controllers;

import java.io.IOException;
import java.util.Collections;
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

import com.etelligens.ecommerce.dto.CategoryDTO;
import com.etelligens.ecommerce.dto.MetaData1DTO;
import com.etelligens.ecommerce.dto.ProductDto;
import com.etelligens.ecommerce.dto.ProductMetaDataDTO;
import com.etelligens.ecommerce.service.CategoryService;
import com.etelligens.ecommerce.service.ProductService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/toAll")
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@PostMapping("/addProduct")
	public ProductDto addProduct(@RequestBody ProductDto product) {

		return productService.saveProduct(product);

	}

	@GetMapping("/allProducts")
	public List<ProductDto> findAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/product/{id}")
	public ProductDto findProductById(@PathVariable Long id) {
		return productService.getProductById(id);
	}

	@GetMapping("/product/category/{id}")
	public List<ProductDto> findAllProductById(@PathVariable Long id) {
		try {
			return productService.getProductByCategoryId(id);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return Collections.emptyList();

	}

	@PutMapping("/product/update")
	public ProductDto updateProduct(@RequestBody ProductDto product) {
		return productService.updateProduct(product);
	}

	@DeleteMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable Long id) {
		return productService.deleteProductById(id);
	}

	@PostMapping("/product/upload")
	public ResponseEntity<ProductDto> uploadFile(@RequestBody ProductDto product) throws IOException {

		return new ResponseEntity<>(productService.store(product), HttpStatus.OK);

	}

	@PostMapping("/addDetail")
	public ResponseEntity<ProductDto> addMetaDataToProduct(@RequestParam("product") String productId,
			@RequestParam("productMetaData") String productMetaData, @RequestParam("metaData") String metaData,
			@RequestParam("files") MultipartFile[] files) {
		Gson gson = new Gson();
		Long id = gson.fromJson(productId, Long.class);
		ProductMetaDataDTO productMetaData1DTO = gson.fromJson(productMetaData, ProductMetaDataDTO.class);
		MetaData1DTO metaData1DTO = gson.fromJson(metaData, MetaData1DTO.class);
	
		return new ResponseEntity<>(productService.addProductDetails(id, productMetaData1DTO, metaData1DTO, files), HttpStatus.OK);

	}

	@GetMapping("/categories")
	public ResponseEntity<List<CategoryDTO>> getAllCategories() {

		return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);

	}

	@PostMapping("/newCategory")
	public ResponseEntity<CategoryDTO> addCategory(@RequestParam("img") MultipartFile file,
			@RequestParam("name") String name) throws IOException {

		CategoryDTO category = new CategoryDTO();
		category.setName(name);

		return new ResponseEntity<>(categoryService.addCategory(file, category), HttpStatus.OK);
	}
}
