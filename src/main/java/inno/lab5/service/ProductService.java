package inno.lab5.service;

import ch.qos.logback.core.net.server.Client;
import inno.lab5.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    Product save(Product product);
    Product update(Product product);
    void deleteById(Long id);

}
