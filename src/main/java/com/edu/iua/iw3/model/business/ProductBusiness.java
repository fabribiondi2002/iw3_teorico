package com.edu.iua.iw3.model.business;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.iua.iw3.model.Product;
import com.edu.iua.iw3.model.persistence.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductBusiness implements IProductBusiness {
    @Autowired
    private ProductRepository productDAO;

    @Override
    public List<Product> list() throws BusinessException {
        try {
            return productDAO.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).message(e.getMessage()).build();
        }
    }

    @Override
    public Product load(Long id) throws BusinessException, NotFoundException {
        Optional <Product> r;
        try {
            r = productDAO.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).message(e.getMessage()).build();
        }
        if(r.isEmpty()) {
            throw NotFoundException.builder().message("No se encontro el producto con id: "+id).build();
        }
        return r.get();
    }

    @Override
    public Product load(String product) throws BusinessException, NotFoundException {
        Optional <Product> r;
        try {
            r = productDAO.findByProduct(product);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).message(e.getMessage()).build();
        }
        if(r.isEmpty()) {
            throw NotFoundException.builder().message("No se encontro el producto con nombre: "+ product).build();
        }
        return r.get();
    }

    @Override
    public Product add(Product product) throws BusinessException, FoundException {
        try {
            load(product.getId());
            throw FoundException.builder().message("El producto ya existe").build();
        } catch (Exception e) {
            
        }
        try {
            load(product.getProduct());
            throw FoundException.builder().message("El producto ya existe").build();
        } catch (Exception e) {
            
        }
        try {
            return productDAO.save(product);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).message(e.getMessage()).build();
        }
        
    }

    @Override
    public Product update(Product product) throws BusinessException, NotFoundException {
        load(product.getId());
        try {
            return productDAO.save(product);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).message(e.getMessage()).build();
        }
    }

    @Override
    public void delete(Long id) throws BusinessException, NotFoundException {
        load(id);
        try {
            productDAO.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).message(e.getMessage()).build();
        }
        
    }
    
}
