package com.edu.iua.iw3.model.business;

import java.util.List;

import com.edu.iua.iw3.model.Product;

public interface IProductBusiness {

    public List<Product> list() throws BusinessException;
    
    public Product load(String product) throws BusinessException, NotFoundException;
    public Product add(Product product) throws BusinessException, FoundException;
    public Product update(Product product) throws FoundException,BusinessException, NotFoundException;
    public void delete(Long id) throws BusinessException, NotFoundException;
    Product load(long id) throws NotFoundException, BusinessException;
    
}