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
import com.etelligens.ecommerce.dto.ProductDTO;
import com.etelligens.ecommerce.dto.ProductDetailsDTO;
import com.etelligens.ecommerce.dto.ProductResponseDTO;
import com.etelligens.ecommerce.service.CategoryService;
import com.etelligens.ecommerce.service.ProductService;

/*
 * This Controller class  will work for add,get,update and delete  product,product detail,filters and search products.
 */
@RestController
@RequestMapping("/toAll")
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	/*
	 * This API will work for add product.
	 */
	@PostMapping("/product/upload")
	public ResponseEntity<ProductDTO> addNewProduct(@RequestBody ProductDTO product) {

		return new ResponseEntity<>(productService.addNewProduct(product), HttpStatus.OK);

	}
	/*
	 * This API will work for add product details.
	 */

	@PostMapping("/addDetail")
	public ResponseEntity<ProductDTO> addMetaDataToProduct(@RequestParam("product") String productId,
			@RequestParam("productMetaData") String productMetaData, @RequestParam("metaData") String metaData,
			@RequestParam("files") MultipartFile[] files) throws IOException {

		return new ResponseEntity<>(productService.addProductDetails(productId, productMetaData, metaData, files),
				HttpStatus.OK);

	}

	/*
	 * This API will work for get all products.
	 */

	@GetMapping("/allProducts")
	public ResponseEntity<List<ProductDTO>> findAllProducts() {
		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
	}

	/*
	 * This API will work for get product by id.
	 */

	@GetMapping("/product/{id}")
	public ResponseEntity<ProductDTO> findProductById(@PathVariable Long id) {
		return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
	}

	/*
	 * This API will work for get product by id or color.
	 */

	@GetMapping("/product/{id}/{color}")
	public ResponseEntity<ProductDTO> getProductByIdAndColor(@PathVariable Long id, @PathVariable String color) {
		return new ResponseEntity<>(productService.getProductsByColor(id, color), HttpStatus.OK);
	}

	/*
	 * This API will work for get product by category id.
	 */
	@GetMapping("/product/category/{id}")
	public ResponseEntity<List<ProductDTO>> findAllProductById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(productService.getProductByCategoryId(id), HttpStatus.OK);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);

	}

	/*
	 * This API will work for update product.
	 */
	@PutMapping("/product/update")
	public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO product) {
		return new ResponseEntity<>(productService.updateProduct(product), HttpStatus.OK);
	}

	/*
	 * This API will work for delete product by id.
	 */

	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
		return new ResponseEntity<>(productService.deleteProductById(id), HttpStatus.OK);
	}

	/*
	 * This API will work for get call categories.
	 */
	@GetMapping("/categories")
	public ResponseEntity<List<CategoryDTO>> getAllCategories() {
		return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
	}

	/*
	 * This API will work for add category.
	 */
	@PostMapping("/newCategory")
	public ResponseEntity<CategoryDTO> addCategory(@RequestParam("img") MultipartFile file,
			@RequestParam("name") String name) throws IOException {
		return new ResponseEntity<>(categoryService.addCategory(file, name), HttpStatus.OK);
	}

	/*
	 * This API will work for update category By Id.
	 */
	@PutMapping("/updateCategory/{id}")
	public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("id") Long id,
			@RequestParam("img") MultipartFile file, @RequestParam("name") String name) throws IOException {
		return new ResponseEntity<>(categoryService.updateCategory(id, file, name), HttpStatus.OK);
	}

	/*
	 * This API will work for search products.
	 */

	@GetMapping("/search/{value}")
	public ResponseEntity<List<ProductResponseDTO>> searchProducts(@PathVariable("value") String value) {
		return new ResponseEntity<>(productService.searchProducts(value), HttpStatus.OK);

	}

	/*
	 * This API will work for filetr the products based on min and max price.
	 */
	@GetMapping("/filter")
	public ResponseEntity<List<ProductResponseDTO>> filterProducts(@RequestParam("minPrice") Double minPrice,
			@RequestParam("maxPrice") Double maxPrice) {
		return new ResponseEntity<>(productService.filterProducts(minPrice, maxPrice), HttpStatus.OK);
	}

	/*
	 * This API will work for get sales on products.
	 */

	@GetMapping("/sales")
	public ResponseEntity<List<ProductDTO>> salesProducts(@RequestParam("salesName") String salesType) {
		return new ResponseEntity<>(productService.getSalesProduct(salesType), HttpStatus.OK);
	}

	/*
	 * This API will work for get sales on products.
	 */
	@GetMapping("/products")
	public ResponseEntity<List<ProductResponseDTO>> getProductResponseDTO() {
		return new ResponseEntity<>(productService.getProductResponseDTO(), HttpStatus.OK);
	}

	/*
	 * This API will work for get products by id.
	 */
	@GetMapping("/products/{id}")
	public ResponseEntity<ProductResponseDTO> getProductResponseById(@PathVariable Long id) {
		return new ResponseEntity<>(productService.getProductResponseDTOById(id), HttpStatus.OK);
	}
	/*
	 * This API will work for get product details by id.
	 */

	@GetMapping("/productDetails/{id}")
	public ResponseEntity<ProductDetailsDTO> getProductDetails(@PathVariable Long id) {
		return new ResponseEntity<>(productService.getProductResponseDTO(id), HttpStatus.OK);
	}
}
