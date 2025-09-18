package com.edu.iua.iw3.integration.cli1.business;

import java.util.List;

import com.edu.iua.iw3.integration.cli1.model.ProductCli1;
import com.edu.iua.iw3.model.business.NotFoundException;
import com.edu.iua.iw3.model.business.BusinessException;
import com.edu.iua.iw3.model.business.FoundException;

public interface IProductCli1Business {
	public ProductCli1 load(String codCli1) throws NotFoundException, BusinessException;
	public List<ProductCli1> list() throws BusinessException;
	public ProductCli1 add(ProductCli1 product) throws FoundException, BusinessException;

}