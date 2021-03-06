package com.spring.service;

import com.spring.domain.model.Product;

/**
 * @Description 产品service接口
 * @Author ErnestCheng
 * @Date 2017/5/27.
 */
public interface ProductService {
    /**
     * 添加产品
     * @param product
     */
    void addProduct(Product product);

    /**
     * 通过产品id获得产品
     * @param productId
     * @return
     */
    Product getProductById(Integer productId);
}
