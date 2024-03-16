package inno.lab5.repository.impl;

import ch.qos.logback.core.net.server.Client;
import inno.lab5.exception.EntityNotFoundException;
import inno.lab5.model.Product;
import inno.lab5.model.ProductRegister;
import inno.lab5.repository.ProductRegisterRepository;
import inno.lab5.repository.ProductRepository;
import inno.lab5.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class InMemoryProductRepository implements ProductRepository {

    private ProductRegisterRepository productRegisterRepository;

    private final Map<Long,Product> repository = new ConcurrentHashMap<>();

    private final AtomicLong currentId = new AtomicLong(1);

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(repository.values());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(repository.get(id));
    }

    @Override
    public Product save(Product product) {
        Long productId = currentId.getAndIncrement();
        product.setId(productId);
        repository.put(productId,product);
        return product;
    }

    @Override
    public Product update(Product product) {
        Long productId = product.getId();
        Product currentProduct = repository.get(productId);
        if(currentProduct == null){
            throw new EntityNotFoundException(MessageFormat.format("Продукт по id {0} нк найден!", productId));
        }
        BeanUtils.copyNonNullProperties(product,currentProduct);
        currentProduct.setId(productId);
        repository.put(productId,product);
        return currentProduct;
    }

    @Override
    public void deleteById(Long id) {
        Product product = repository.get(id);
        if(product == null){
            throw new EntityNotFoundException(MessageFormat.format("Продукт по id {0} не найдн!", id));
        }
        productRegisterRepository.deleteByIdIn(product.getProductRegisters().stream().map(ProductRegister::getId).collect(Collectors.toList()));
        repository.remove(id);
    }

    @Autowired
    public void setProductRegisterRepository(ProductRegisterRepository productRegisterRepository){
        this.productRegisterRepository = productRegisterRepository;
    }
}
