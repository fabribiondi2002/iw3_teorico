package com.edu.iua.iw3.model.business;
import java.util.List;

import com.edu.iua.iw3.model.Category;

public interface ICategoryBusiness {
    public Category load(String category) throws BusinessException, NotFoundException;
    public Category add(Category category) throws BusinessException, FoundException;
    public Category update(Category category) throws FoundException,BusinessException, NotFoundException;
    public void delete(Long id) throws BusinessException, NotFoundException;
    public List<Category> list() throws BusinessException;
    public Category load(long id) throws NotFoundException, BusinessException;
}
