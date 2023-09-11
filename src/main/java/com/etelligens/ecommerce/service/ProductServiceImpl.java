package com.etelligens.ecommerce.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.etelligens.ecommerce.dto.ProductDTO;
import com.etelligens.ecommerce.dto.ProductMetaDataDTO;
import com.etelligens.ecommerce.dto.ProductResponseDTO;
import com.etelligens.ecommerce.dto.ProductDetailsDTO;
import com.etelligens.ecommerce.dto.ImagesDTO;
import com.etelligens.ecommerce.dto.MetaData1DTO;
import com.etelligens.ecommerce.exception.ProductNotExistException;
import com.etelligens.ecommerce.model.Category;
import com.etelligens.ecommerce.model.Images;
import com.etelligens.ecommerce.model.MetaData1;
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
import com.etelligens.ecommerce.utils.ProductToProductDTO;
import com.etelligens.ecommerce.utils.StringToObjectConvert;

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
	
	@Autowired
	StringToObjectConvert stringToObjectConvert;
	
	@Autowired
	ProductToProductDTO productToProductDTO;

	@Override
	public ProductDTO addNewProduct(ProductDTO productDTO) {

		Product product = mapper.map(productDTO, Product.class);

		Date d = new Date();

		product.setCreatedAt(new Timestamp(d.getTime()));
		Category categry = categoryRepository.findByName(productDTO.getCategory()).orElseThrow();
		product.setCategory(categry);
		
		String discountType = productDTO.getDiscountType();

		if ("%".equals(discountType)) {
			Double discountAmount = productDTO.getDiscountAmount();
			Double p = productDTO.getPrice();
			Double priceAfterDiscount = p * (discountAmount / 100);
			Double prce = p-priceAfterDiscount;
			productDTO.setPriceAfterDiscount(prce);
			product.setPriceAfterDiscount(priceAfterDiscount);
		}
		else {
			Double priceAfterDiscount = productDTO.getPrice()-productDTO.getDiscountAmount();
			product.setPriceAfterDiscount(priceAfterDiscount);
		}
		Product savedProduct = productRepo.save(product);
		ProductDTO productdto =  mapper.map(savedProduct, ProductDTO.class);
		productdto.setCategory(savedProduct.getCategory().getName());
		return productdto;
	}

	@Override
	public ProductDTO addProductDetails(String productId, String productMeta, String metaData1DTO,
			MultipartFile[] files) throws IOException {
		ProductMetaDataDTO productMetaData1DTO = stringToObjectConvert.convertJsonToObject(productMeta, ProductMetaDataDTO.class);
		MetaData1DTO metaData1DTO2 = stringToObjectConvert.convertJsonToObject(metaData1DTO, MetaData1DTO.class);
		ProductMetaData productMetaData = mapper.map(productMetaData1DTO, ProductMetaData.class);	
		MetaData1 metaData1 = mapper.map(metaData1DTO2, MetaData1.class);
		Long id = Long.parseLong(productId);
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
				productMetaData.setMetaData(Arrays.asList(metaData1));
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

		List<Product> allProducts = productRepo.findAll();
		List<ProductDTO> productList = new ArrayList<>();
		for(Product product : allProducts) {
			ProductDTO productDTO = mapper.map(product, ProductDTO.class);
			productDTO.setCategory(product.getCategory().getName());
		productList.add(productDTO);
		}
		return productList;
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
	public ProductDetailsDTO getProductResponseDTO(Long productid) {
		Product product = productRepo.findById(productid)
				.orElseThrow(() -> new ProductNotExistException("Product is not present"));
		ProductDTO productDTO = mapper.map(product, ProductDTO.class);
		ProductDetailsDTO productResponseDTO = new ProductDetailsDTO();
		productResponseDTO = mapper.map(product, ProductDetailsDTO.class);
		productResponseDTO.setId(product.getId()); 
		productResponseDTO.setCategory(product.getCategory().getName());
		List<ProductMetaDataDTO> productMetaData = productDTO.getProductMetaData();
		List<ImagesDTO> images;
		List<MetaData1DTO> metaData;
		List<byte[]> imgs = new ArrayList<>();
		List<String> color = new ArrayList<>();
		List<String> size = new ArrayList<>();
		for(ProductMetaDataDTO data : productMetaData) {
			color.add(data.getColor());
			images= data.getImages();
			for(ImagesDTO image : images) {
				imgs.add(image.getImg());
			}
			
			metaData = data.getMetaData();
			for(MetaData1DTO metaData1 : metaData) {
				size.add(metaData1.getSize());
			}
		}
		
			productResponseDTO.setColor(color);
			productResponseDTO.setImages(imgs);
			productResponseDTO.setSize(size);
		
		return productResponseDTO;
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
		String discountType = product.getDiscountType();
		try {
			if (!existingProduct.isEmpty()) {
				Product prod = existingProduct.get();
				prod = mapper.map(product, Product.class);
				Double discountPrice = null;
				if(discountType.equalsIgnoreCase("%")) {
					 discountPrice = (product.getPrice() - ((product.getDiscountAmount() * 0.01) * product.getPrice()));
				}else {
					 discountPrice = product.getPrice()-product.getDiscountAmount();
					
				}
				prod.setPriceAfterDiscount(discountPrice);
				prod = productRepo.save(prod);
				ProductDTO productdto = mapper.map(prod, ProductDTO.class);
				productdto.setCategory(prod.getCategory().getName());
				return productdto;
			}
			return null;
		} catch (ProductNotExistException e) {
			throw new ProductNotExistException("Product not found!!" + product.getId());
		}

	}

	@Override
	public List<ProductDTO> getProductByCategoryId(Long id) {
		List<Product> products = productRepo.findAllByCategoryId(id);
		try {
			return null;// productToProductDTO.convertListProductToListProductDTO(products);

		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	private List<Images> addImages(MultipartFile[] files) {

		List<ImagesDTO> productImagesDTOs = new ArrayList<>();
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
	public List<ProductResponseDTO> searchProducts(String value) {
		List<Product> products = productRepo.getProducts(value);
		List<ProductResponseDTO> productList = new ArrayList<>();
		for(Product product : products) {
			ProductResponseDTO productDTO = mapper.map(product, ProductResponseDTO.class);
			productDTO.setProductImage(product.getProductMetaData().get(0).getImages().get(0).getImg());
		productList.add(productDTO);
		}
		return productList;
	}

	@Override
	public List<ProductResponseDTO> filterProducts(Double minPrice, Double maxPrice) {
		List<Product> filterProducts = productRepo.getFilterProducts(minPrice, maxPrice);
		List<ProductResponseDTO> productList = new ArrayList<>();
		for(Product product : filterProducts) {
			ProductResponseDTO productDTO = mapper.map(product, ProductResponseDTO.class);
			productDTO.setProductImage(product.getProductMetaData().get(0).getImages().get(0).getImg());
		productList.add(productDTO);
		}
		return productList;
	}

	@Override
	public List<ProductDTO> getSalesProduct(String salesType) {
		List<Product> products = productRepo.getSalesProducts(salesType);
		return null ; // productToProductDTO.convertListProductToListProductDTO(products);
	}

	public ProductDTO getProductsByColor(Long productId, String color){
		Product product = productRepo.findByIdAndColor(productId, color);
		return null; //productToProductDTO.convertProductToProductDTO(product);
	}

	@Override
	public List<ProductResponseDTO> getProductResponseDTO() {
		
		List<Product> allProducts = productRepo.findAll();
		List<ProductResponseDTO> productList = new ArrayList<>();
		for(Product product : allProducts) {
			ProductResponseDTO productDTO = mapper.map(product, ProductResponseDTO.class);
			productDTO.setProductImage(product.getProductMetaData().get(0).getImages().get(0).getImg());
		productList.add(productDTO);
		}
		return productList;
	}
	
	@Override
	public ProductResponseDTO getProductResponseDTOById(Long productId) {
		
		Optional<Product> product = productRepo.findById(productId);
		
			ProductResponseDTO productDTO = mapper.map(product.get(), ProductResponseDTO.class);
			productDTO.setProductImage(product.get().getProductMetaData().get(0).getImages().get(0).getImg());
		
		return productDTO;
	}
}
