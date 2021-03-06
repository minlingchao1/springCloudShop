package com.spring.service.impl;

import com.spring.common.model.model.RedisKey;
import com.spring.domain.model.Product;
import com.spring.persistence.ProductMapper;
import com.spring.service.ProductService;
import com.sun.istack.internal.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Description 产品service实现
 * @Author ErnestCheng
 * @Date 2017/5/27.
 */
@Service
public class ProductServiceImpl implements ProductService {

    Logger logger= Logger.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void addProduct(Product product) {
        productMapper.addProduct(product);
    }

    @Override
  //  @Cacheable(value = "shop_product",key="T(String).valueOf(#productId)")
    public Product getProductById(Integer productId) {
        String key= RedisKey.product+":"+productId;
            Product product=(Product)redisTemplate.opsForValue().get(key);
            if(product==null) {
                logger.info("查询数据库");
                Product product2=productMapper.getProductById(productId);
                if(product2!=null) {
                    redisTemplate.opsForValue().set(key, product2);
                }
                return product2;
            }else{
            return product;
        }
     //  return productMapper.getProductById(productId);
    }
}
