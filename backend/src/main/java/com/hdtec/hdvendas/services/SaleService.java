package com.hdtec.hdvendas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hdtec.hdvendas.dto.SaleDTO;
import com.hdtec.hdvendas.dto.SaleSuccessDTO;
import com.hdtec.hdvendas.dto.SaleSumDTO;
import com.hdtec.hdvendas.entities.Sale;
import com.hdtec.hdvendas.repositories.SaleRepository;
import com.hdtec.hdvendas.repositories.SellerRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository saleRepository;

	@Autowired
	private SellerRepository sellerRepository;

	@Transactional(readOnly = true)
	public Page<SaleDTO> findAll(Pageable pageable) {
		sellerRepository.findAll();
		Page<Sale> result = saleRepository.findAll(pageable);
		return result.map(x -> new SaleDTO(x));
	}
	
	@Transactional(readOnly = true)
	public List<SaleSumDTO> amountGroupedBySeller(){
		return saleRepository.amountGroupedBySeller();
	}

	@Transactional(readOnly = true)
	public List<SaleSuccessDTO> successGroupedBySeller(){
		return saleRepository.successGroupedBySeller();
	}
}