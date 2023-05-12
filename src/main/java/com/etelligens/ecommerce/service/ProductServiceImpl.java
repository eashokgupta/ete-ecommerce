package com.etelligens.ecommerce.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.etelligens.ecommerce.dto.ProductDto;
import com.etelligens.ecommerce.dto.ImagesDTO;
import com.etelligens.ecommerce.exception.ProductNotExistException;
import com.etelligens.ecommerce.model.Images;
import com.etelligens.ecommerce.model.Product;
import com.etelligens.ecommerce.repositories.ProductImagesRepo;
import com.etelligens.ecommerce.repositories.ProductRepo;

@Service
@Component
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

		return productRepo.findById(productid)
				.orElseThrow(() -> new ProductNotExistException("Product is present"));
	}

	@Override
	public String deleteProductById(int id) {
		productRepo.deleteById(id);
		Boolean flag = productRepo.findById(id).isEmpty();
		if (Boolean.TRUE.equals(flag)) {
			return "Product Successfully Deleted";
		}
		return "Not Deleted";
	}

	@Override
	public ProductDto updateProduct(ProductDto product) throws ProductNotExistException {
		Optional<Product> existingProduct = productRepo.findById(product.getId());
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
	public ProductDto store(MultipartFile[] files, ProductDto product) throws IOException {
		
//		Set<Images> images = new HashSet();
		Set<ImagesDTO> productImagesDTOs = new HashSet<>();
		Arrays.asList(files).stream().forEach(file -> {
			try {
//			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			byte[] img = file.getBytes();
			ImagesDTO productImagesDTO = new ImagesDTO();
			productImagesDTO.setImg(img);
			productImagesDTOs.add(productImagesDTO);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		 product.setImages(productImagesDTOs);
		 Product product1 = mapper.map(product, Product.class);
		 Optional<Product> prod = productRepo.findById(product.getId());
		 if(!prod.isEmpty()) {
			 prod.get().getImages().add((Images) productImagesDTOs);
			 return mapper.map(productRepo.save(prod.get()), ProductDto.class);
		 }
		 return mapper.map(productRepo.save(product1), ProductDto.class);
	}

	@Override
	public List<Product> getProductByCategoryId(Long id) {
		List<Product> products = new ArrayList<>();
		try {
			products = productRepo.findAllByCategoryId(id);
		}catch (Exception e) {
			e.getMessage();
		}
		products.stream().forEach((d)->System.out.println(d.getId()));
		
		return products;
		
	}

}
