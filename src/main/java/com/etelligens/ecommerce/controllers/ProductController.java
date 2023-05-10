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

import com.etelligens.ecommerce.dto.CategoryDTO;
import com.etelligens.ecommerce.dto.ProductDto;
import com.etelligens.ecommerce.dto.SlugDTO;
import com.etelligens.ecommerce.model.Product;
import com.etelligens.ecommerce.service.CategoryService;
import com.etelligens.ecommerce.service.ProductService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/prod")
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
	public List<Product> findAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/product/{id}")
	public Product findProductById(@PathVariable int id) {
		return productService.getProductById(id);
	}
	
	@GetMapping("/product/category/{id}")
	public List<Product> findAllProductById(@PathVariable Long id) {
		try {
			return productService.getProductByCategoryId(id);
		}catch (Exception e) {
			e.getStackTrace();
		}
		return null;
		
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
	public ResponseEntity<ProductDto> uploadFile(@RequestParam("files") MultipartFile[] files, @RequestParam("product") String product) throws IOException {
		Gson g = new Gson();  
		ProductDto s = g.fromJson(product, ProductDto.class) ;
		System.out.println(s.getName());
		
		 return ResponseEntity.status(HttpStatus.OK).body(productService.store(files,s));
		
//			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED.value()).body(null);
		
	}
	
	@GetMapping("/categories")
	public ResponseEntity<List<CategoryDTO>> getAllCategories(){
		
		return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
		
	}
	
	@PostMapping("/newCategory")
	public ResponseEntity<CategoryDTO> addCategory(@RequestParam("img") MultipartFile file, @RequestParam("category") String data) throws IOException{
		Gson g = new Gson();  
		CategoryDTO category = g.fromJson(data, CategoryDTO.class) ;
		
		return new ResponseEntity<>(categoryService.addCategory(file, category), HttpStatus.OK);
	}
	
	@PostMapping("/{id}/addSlug")
	public ResponseEntity<CategoryDTO> addSlug(@PathVariable Long id, @RequestBody SlugDTO slug){
		
		
		return new ResponseEntity<CategoryDTO>(categoryService.addSlug(id,slug), HttpStatus.OK);
		
	}
}
