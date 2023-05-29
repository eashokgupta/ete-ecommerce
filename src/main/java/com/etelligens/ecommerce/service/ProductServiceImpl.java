package com.etelligens.ecommerce.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.etelligens.ecommerce.dto.ProductDto;
import com.etelligens.ecommerce.dto.ProductMetaDataDTO;
import com.etelligens.ecommerce.dto.ImagesDTO;
import com.etelligens.ecommerce.dto.MetaData1DTO;
import com.etelligens.ecommerce.exception.ProductNotExistException;
import com.etelligens.ecommerce.model.Category;
import com.etelligens.ecommerce.model.Images;
import com.etelligens.ecommerce.model.MetaData1;
import com.etelligens.ecommerce.model.Product;
import com.etelligens.ecommerce.model.ProductMetaData;
import com.etelligens.ecommerce.repositories.CategoryRepository;
import com.etelligens.ecommerce.repositories.MetaData1Repository;
import com.etelligens.ecommerce.repositories.ProductImagesRepo;
import com.etelligens.ecommerce.repositories.ProductMetaDataRepository;
import com.etelligens.ecommerce.repositories.ProductRepo;

@Service
@Component
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepo productRepo;

	@Autowired
	ProductMetaDataRepository productMetaDataRepository;

	@Autowired
	MetaData1Repository metaData1Repository;

	@Autowired
	ProductImagesRepo imagesRepo;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ModelMapper mapper;

	@Override
	public ProductDto saveProduct(ProductDto product) {
		Product pro = mapper.map(product, Product.class);
		return mapper.map(productRepo.save(pro), ProductDto.class);
	}

	@Override
	public List<ProductDto> getAllProducts() {

		return mapper.map(productRepo.findAll(), new TypeToken<List<ProductDto>>() {
		}.getType());
	}

	@Override
	public ProductDto getProductById(Long productid) {
		Product product = productRepo.findById(productid)
				.orElseThrow(() -> new ProductNotExistException("Product is not present"));
		String category = product.getCategory().getName();
		ProductDto productDto = mapper.map(product, ProductDto.class);
		productDto.setCategory(category);
		return productDto;
	}

	@Override
	public String deleteProductById(Long id) {
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
		} catch (ProductNotExistException e) {
			throw new ProductNotExistException("product is invalid" + product.getId());
		}

	}

	@Override
	public ProductDto store(ProductDto productDto) throws IOException {

		Product product = mapper.map(productDto, Product.class);

		Date d = new Date();

		product.setCreatedAt(new Timestamp(d.getTime()));
		Category categry = categoryRepository.findByName(productDto.getCategory()).orElseThrow();
		product.setCategory(categry);

		Product savedProduct = productRepo.save(product);

		return mapper.map(savedProduct, ProductDto.class);
	}

	@Override
	public List<ProductDto> getProductByCategoryId(Long id) {
		List<ProductDto> products = new ArrayList<>();
		try {
			products = mapper.map(productRepo.findAllByCategoryId(id), new TypeToken<List<ProductDto>>() {
			}.getType());

		} catch (Exception e) {
			e.getMessage();
		}
		return products;

	}

	@Override
	public ProductDto addProductDetails(Long id, ProductMetaDataDTO productMetaData1DTO, MetaData1DTO metaData1DTO,
			MultipartFile[] files) {
		ProductMetaData productMetaData = mapper.map(productMetaData1DTO, ProductMetaData.class);
		MetaData1 metaData1 = mapper.map(metaData1DTO, MetaData1.class);
		Product savedProduct = productRepo.findById(id).orElseThrow();
		List<Images> images = addImages(files);

		if (savedProduct != null) {
			String category = savedProduct.getCategory().getName();
			Optional<ProductMetaData> prod = productMetaDataRepository.findByProductIdAndColor(id,
					productMetaData1DTO.getColor());
			if (!prod.isEmpty()) {
				metaData1.setProductMetaData(prod.get());
				metaData1Repository.save(metaData1);
				ProductDto productDto = mapper.map(savedProduct, ProductDto.class);
				productDto.setCategory(category);
				return productDto;

			} else {
				productMetaData.setProduct(savedProduct);
				productMetaData.setImages(images);
				ProductMetaData productData = productMetaDataRepository.save(productMetaData);
				metaData1.setProductMetaData(productData);
				metaData1Repository.save(metaData1);
				ProductDto productDto = mapper.map(savedProduct, ProductDto.class);
				productDto.setCategory(category);
				return productDto;
			}
		}
		return null;
	}

	private List<Images> addImages(MultipartFile[] files) {

		Set<ImagesDTO> productImagesDTOs = new HashSet<>();
		Arrays.asList(files).stream().forEach(file -> {
			try {
				byte[] img = file.getBytes();
				ImagesDTO productImagesDTO = new ImagesDTO();
				productImagesDTO.setImg(img);
				productImagesDTOs.add(productImagesDTO);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		return mapper.map(productImagesDTOs, new TypeToken<Set<Images>>() {
		}.getType());
	}

	@Override
	public List<ProductDto> searchProducts(String value) {
		List<Product> products = productRepo.getProducts(value);
		
		return mapper.map(products, new TypeToken<List<ProductDto>>() {
		}.getType());
	}

}
