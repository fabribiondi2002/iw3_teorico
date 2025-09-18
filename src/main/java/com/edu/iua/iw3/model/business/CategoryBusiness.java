package com.edu.iua.iw3.model.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.iua.iw3.model.Category;
import com.edu.iua.iw3.model.persistence.CategoryRepository;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class CategoryBusiness implements ICategoryBusiness {

    @Autowired
    private CategoryRepository categoryDAO;

    @Override
    public Category load(String category) throws BusinessException, NotFoundException {
        Optional <Category> r;
        try {
            r = categoryDAO.findOneByCategory(category);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).message(e.getMessage()).build();
        }
        if(r.isEmpty()) {
            throw NotFoundException.builder().message("No se encontro la categoria con nombre: "+ category).build();
        }
        return r.get();
    }

    @Override
    public Category add(Category category) throws BusinessException, FoundException {
        try {
            load(category.getId());
            throw FoundException.builder().message("La categoria ya existe").build();
        } catch (Exception e) {
            
        }
        try {
            load(category.getCategory());
            throw FoundException.builder().message("La categoria ya existe").build();
        } catch (Exception e) {
            
        }
        try {
            return categoryDAO.save(category);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).message(e.getMessage()).build();
        }
    }

    @Override
    public Category update(Category category) throws FoundException, BusinessException, NotFoundException {
        load(category.getId());
		Optional<Category> nombreExistente=null;
		try {
			nombreExistente=categoryDAO.findByCategoryAndIdNot(category.getCategory(), category.getId());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
		if(nombreExistente.isPresent()) {
			throw FoundException.builder().message("Se encontró una categoria nombre="+category.getCategory()).build();
		}

		try {
			return categoryDAO.save(category);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
    }
    @Override
    public void delete(Long id) throws BusinessException, NotFoundException {
        load(id);
        try {
            categoryDAO.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).message(e.getMessage()).build();
        }
    }

    @Override
    public List<Category> list() throws BusinessException {
        try {
            return categoryDAO.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).message(e.getMessage()).build();
        }
    }
    @Override
    public Category load(long id) throws NotFoundException, BusinessException {
        Optional <Category> r;
        try {
            r = categoryDAO.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).message(e.getMessage()).build();
        }
        if(r.isEmpty()) {
            throw NotFoundException.builder().message("No se encontro la categoria con id: "+ id).build();
        }
        return r.get();
    }
    
}
