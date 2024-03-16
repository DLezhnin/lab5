package inno.lab5.service.impl;

import inno.lab5.exception.EntityNotFoundException;
import inno.lab5.model.Product;
import inno.lab5.repository.ProductRepository;
import inno.lab5.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById((id))
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Продуктс ID {0} не найден!",id)));
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        return productRepository.update(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
