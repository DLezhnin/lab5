package inno.lab5.repository.impl;

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

@Repository
public class InMemoryProductRegisterRepository implements ProductRegisterRepository {

    private ProductRepository productRepository;
    private final Map<Long, ProductRegister> repository = new ConcurrentHashMap<>();
    private final AtomicLong currentId = new AtomicLong(1);

    @Override
    public List<ProductRegister> finAll() {
        return new ArrayList<>(repository.values());
    }

    @Override
    public Optional<ProductRegister> findById(Long id) {
        return Optional.ofNullable(repository.get(id));
    }

    @Override
    public ProductRegister save(ProductRegister productRegister) {
        Long productRegisterId = currentId.getAndIncrement();
        Long productId = productRegister.getProduct().getId();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Продукт не найден!"));

        productRegister.setProduct(product);
        productRegister.setId(productRegisterId);

        repository.put(productRegisterId,productRegister);
        product.addProductRegister(productRegister);
        productRepository.update(product);
        return productRegister;
    }

    @Override
    public ProductRegister update(ProductRegister productRegister) {
        Long productRegisterId = productRegister.getId();

        ProductRegister currentProductRegister = repository.get(productRegisterId);

        if(currentProductRegister == null){
            throw new EntityNotFoundException(MessageFormat.format("Продуктовый регистрпо id {0} не найден!",productRegisterId));
        }
        BeanUtils.copyNonNullProperties(productRegister,currentProductRegister);

        currentProductRegister.setId(productRegisterId);
        repository.put(productRegisterId,productRegister);
        return currentProductRegister;
    }

    @Override
    public void deleteById(Long id) {
        repository.remove(id);
    }

    @Override
    public void deleteByIdIn(List<Long> ids) {
        ids.forEach(repository::remove);
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
}
