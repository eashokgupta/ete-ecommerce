package com.etelligens.ecommerce.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.etelligens.ecommerce.dto.SalesDTO;
import com.etelligens.ecommerce.dto.SalesImagesDTO;
import com.etelligens.ecommerce.model.Sales;
import com.etelligens.ecommerce.model.SalesImages;
import com.etelligens.ecommerce.repositories.SalesRepository;

@Service
public class SalesServiceImpl implements SalesService{
	
	@Autowired
	SalesRepository salesRepository;
	
	@Autowired
	ModelMapper mapper;

	@Override
	public SalesDTO addNewSales(SalesDTO sales, MultipartFile[] salesImgs) {
		List<SalesImages> images = addImages(salesImgs);
		Sales sale = mapper.map(sales, Sales.class);
		sale.setImg(images);
		return mapper.map(salesRepository.save(sale), SalesDTO.class);
	}

	@Override
	public SalesDTO getSalesByTitle(String title) {
		return mapper.map(salesRepository.findByTitle(title), SalesDTO.class);
	}

	@Override
	public List<SalesDTO> getAllSales() {
		return mapper.map(salesRepository.findAll(), new TypeToken<List<SalesDTO>>() {}.getType());
	}

	@Override
	public String deleteSales(Long id) {
		salesRepository.deleteById(id);
		Boolean sales = salesRepository.findById(id).isEmpty();
		if(Boolean.TRUE.equals(sales)) {
			return "Deleted Successfully!";
		}
		return "Not Deleted";
	}

	@Override
	public SalesDTO updateSales(SalesDTO sales, MultipartFile[] salesImgs) {
		Optional<Sales> sale = salesRepository.findById(sales.getId());
		List<SalesImages> img = addImages(salesImgs);
		
		if(!sale.isEmpty()) {
			Sales sl = mapper.map(sales, Sales.class);
			sl.setImg(img);
			return mapper.map(salesRepository.save(sl), SalesDTO.class);
		}
		return mapper.map(sale, SalesDTO.class);
	}

	private List<SalesImages> addImages(MultipartFile[] files) {

		List<SalesImagesDTO> productImagesDTOs = new ArrayList<>();
		Arrays.asList(files).stream().forEach(file -> {
			try {
				byte[] img = file.getBytes();
				SalesImagesDTO productImagesDTO = new SalesImagesDTO();
				productImagesDTO.setImages(img);
				productImagesDTOs.add(productImagesDTO);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		return mapper.map(productImagesDTOs, new TypeToken<List<SalesImages>>() {
		}.getType());
	}
}
