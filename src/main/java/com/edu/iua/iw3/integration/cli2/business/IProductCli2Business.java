package com.edu.iua.iw3.integration.cli2.business;
import java.util.Date;
import java.util.List;
import com.edu.iua.iw3.integration.cli2.model.ProductCli2;
import com.edu.iua.iw3.model.business.BusinessException;

public interface IProductCli2Business {
	public List<ProductCli2> listExpired(Date date) throws BusinessException;
}