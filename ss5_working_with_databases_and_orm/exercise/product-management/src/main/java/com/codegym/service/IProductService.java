package com.codegym.service;

import com.codegym.model.Product;

import java.util.List;

public interface IProductService {

    List<Product> showList(String keySearch);

    void save(Product product);

    Product findById(int id);

    void update(Product product);

    void delete(Product product);
}
