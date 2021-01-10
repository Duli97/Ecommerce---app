package com.myapp.ecommerce.config;

import com.myapp.ecommerce.entity.Product;
import com.myapp.ecommerce.entity.ProductCategory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.List;

@Configurable
public class MyDataRestConfig implements RepositoryRestConfigurer {






    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {

        HttpMethod[] unsupportedActions = {HttpMethod.DELETE, HttpMethod.PUT, HttpMethod.POST};

        // disable HTTP methods for Products: DELETE, POST, PUT
        config.getExposureConfiguration()
                .forDomainType(Product.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions));

        // disable HTTP methods for ProductCategory: DELETE, POST, PUT
        config.getExposureConfiguration()
                .forDomainType(ProductCategory.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions));


    }
}
