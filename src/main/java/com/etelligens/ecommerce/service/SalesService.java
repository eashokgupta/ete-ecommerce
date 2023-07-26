package com.etelligens.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.etelligens.ecommerce.dto.SalesDTO;

@Component
public interface SalesService {

	public SalesDTO addNewSales(String sales, MultipartFile[] salesImgs);

	public SalesDTO getSalesByTitle(String title);

	public List<SalesDTO> getAllSales();

	public String deleteSales(Long id);

	public SalesDTO updateSales(String sales, MultipartFile[] salesImgs);
}
