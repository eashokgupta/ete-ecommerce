package com.etelligens.ecommerce.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.etelligens.ecommerce.dto.ProductDto;
import com.etelligens.ecommerce.dto.ProductImagesDTO;
import com.etelligens.ecommerce.exception.ProductNotExistException;
import com.etelligens.ecommerce.model.Product;
import com.etelligens.ecommerce.model.ProductImages;
import com.etelligens.ecommerce.repositories.ProductImagesRepo;
import com.etelligens.ecommerce.repositories.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	ProductImagesRepo imagesRepo;

	@Autowired
	ModelMapper mapper;

	@Override
	public ProductDto saveProduct(ProductDto product) {
		Product pro = mapper.map(product, Product.class);
		return mapper.map(productRepo.save(pro), ProductDto.class);
	}

	@Override
	public List<Product> getAllProducts() {

		return productRepo.findAll();
	}

	@Override
	public Product getProductById(int productid) {

		return productRepo.findByProductId(productid)
				.orElseThrow(() -> new ProductNotExistException("Product is present"));
	}

	@Override
	public String deleteProductById(int id) {
		productRepo.deleteById(id);
		Boolean flag = productRepo.findByProductId(id).isEmpty();
		if (Boolean.TRUE.equals(flag)) {
			return "Product Successfully Deleted";
		}
		return "Not Deleted";
	}

	@Override
	public ProductDto updateProduct(ProductDto product) throws ProductNotExistException {
		Optional<Product> existingProduct = productRepo.findByProductId(product.getId());
		try {
			if (!existingProduct.isEmpty()) {
				Product prod = mapper.map(existingProduct, Product.class);
				prod = productRepo.save(prod);
				return mapper.map(prod, ProductDto.class);
			}
			return null;
		}catch (ProductNotExistException e) {
			throw new ProductNotExistException("product is invalid" + product.getId());
		}
		
		
	}

	@Override
	public ProductImagesDTO store(MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		ProductImagesDTO FileDB = new ProductImagesDTO();
		FileDB.setImg(file.getBytes());
		ProductImages images = mapper.map(FileDB, ProductImages.class);
		
	   return mapper.map(imagesRepo.save(images), ProductImagesDTO.class);
		
	}

}
