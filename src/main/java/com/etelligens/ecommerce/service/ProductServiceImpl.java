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
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.etelligens.ecommerce.dto.ProductDTO;
import com.etelligens.ecommerce.dto.ProductMetaDataDTO;
import com.etelligens.ecommerce.dto.ImagesDTO;
import com.etelligens.ecommerce.dto.MetaData1DTO;
import com.etelligens.ecommerce.exception.ProductNotExistException;
import com.etelligens.ecommerce.model.Category;
import com.etelligens.ecommerce.model.Images;
import com.etelligens.ecommerce.model.MetaData1;
import com.etelligens.ecommerce.model.Offer;
import com.etelligens.ecommerce.model.Product;
import com.etelligens.ecommerce.model.ProductMetaData;
import com.etelligens.ecommerce.model.Review;
import com.etelligens.ecommerce.repositories.CategoryRepository;
import com.etelligens.ecommerce.repositories.MetaData1Repository;
import com.etelligens.ecommerce.repositories.OfferRepository;
import com.etelligens.ecommerce.repositories.ProductImagesRepository;
import com.etelligens.ecommerce.repositories.ProductMetaDataRepository;
import com.etelligens.ecommerce.repositories.ProductRepository;
import com.etelligens.ecommerce.repositories.ReviewRepository;

@Service
@Component
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepo;

	@Autowired
	ProductMetaDataRepository productMetaDataRepository;

	@Autowired
	MetaData1Repository metaData1Repository;

	@Autowired
	ProductImagesRepository imagesRepo;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	OfferRepository offerRepository;
	
	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	ModelMapper mapper;

	@Override
	public ProductDTO addNewProduct(ProductDTO productDTO) {

		Product product = mapper.map(productDTO, Product.class);

		Date d = new Date();

		product.setCreatedAt(new Timestamp(d.getTime()));
		Category categry = categoryRepository.findByName(productDTO.getCategory()).orElseThrow();
		product.setCategory(categry);

		Optional<Offer> offer = offerRepository.findById(productDTO.getOfferId());

		boolean off = offer.isPresent();

		if (off && "%".equals(offer.get().getAmountType())) {
			Offer offer1 = offer.get();
			Double price = productDTO.getPrice();
			Double priceAfterDiscount = price * (offer1.getAmount() / 100);
			productDTO.setPriceAfterDiscount(priceAfterDiscount);
			product.setPriceAfterDiscount(priceAfterDiscount);
		}
		Product savedProduct = productRepo.save(product);

		return mapper.map(savedProduct, ProductDTO.class);
	}

	@Override
	public ProductDTO addProductDetails(Long id, ProductMetaDataDTO productMetaData1DTO, MetaData1DTO metaData1DTO,
			MultipartFile[] files, Long offerId) {
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
				ProductDTO productDTO = mapper.map(savedProduct, ProductDTO.class);
				productDTO.setCategory(category);
				return productDTO;

			} else {
				productMetaData.setProduct(savedProduct);
				productMetaData.setImages(images);
				ProductMetaData productData = productMetaDataRepository.save(productMetaData);
				metaData1.setProductMetaData(productData);
				metaData1Repository.save(metaData1);
				ProductDTO productDTO = mapper.map(savedProduct, ProductDTO.class);
				productDTO.setCategory(category);
				return productDTO;
			}
		}
		return null;
	}

	@Override
	public List<ProductDTO> getAllProducts() {

		return mapper.map(productRepo.findAll(), new TypeToken<List<ProductDTO>>() {
		}.getType());
	}

	@Override
	public ProductDTO getProductById(Long productid) {
		Product product = productRepo.findById(productid)
				.orElseThrow(() -> new ProductNotExistException("Product is not present"));
		String category = product.getCategory().getName();
		List<Review> reviews = reviewRepository.findByProductId(productid);
		Double rating = reviews.stream().collect(Collectors.averagingDouble(Review::getRating));
		float rate = rating.floatValue();
		ProductDTO productDTO = mapper.map(product, ProductDTO.class);
		productDTO.setRating(rate);
		productDTO.setCategory(category);
		return productDTO;
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
	public ProductDTO updateProduct(ProductDTO product) throws ProductNotExistException {
		Optional<Product> existingProduct = productRepo.findById(product.getId());
		try {
			if (!existingProduct.isEmpty()) {
				Product prod = mapper.map(existingProduct.get(), Product.class);
				prod = productRepo.save(prod);
				return mapper.map(prod, ProductDTO.class);
			}
			return null;
		} catch (ProductNotExistException e) {
			throw new ProductNotExistException("product is invalid" + product.getId());
		}

	}

	@Override
	public List<ProductDTO> getProductByCategoryId(Long id) {
		List<ProductDTO> products = new ArrayList<>();
		try {
			products = mapper.map(productRepo.findAllByCategoryId(id), new TypeToken<List<ProductDTO>>() {
			}.getType());

		} catch (Exception e) {
			e.getMessage();
		}
		return products;

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

		return mapper.map(productImagesDTOs, new TypeToken<List<Images>>() {
		}.getType());
	}

	@Override
	public List<ProductDTO> searchProducts(String value) {
		List<Product> products = productRepo.getProducts(value);

		return mapper.map(products, new TypeToken<List<ProductDTO>>() {
		}.getType());
	}

	@Override
	public List<ProductDTO> filterProducts(Double minPrice, Double maxPrice) {
		List<Product> filterProducts = productRepo.getFilterProducts(minPrice, maxPrice);
		return mapper.map(filterProducts, new TypeToken<List<ProductDTO>>() {
		}.getType());
	}

	@Override
	public List<ProductDTO> getSalesProduct(String salesType) {
		return mapper.map(productRepo.getSalesProducts(salesType), new TypeToken<List<ProductDTO>>(){}.getType());
	}

}
