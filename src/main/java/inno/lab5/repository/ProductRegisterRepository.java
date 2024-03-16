package inno.lab5.repository;

import inno.lab5.model.ProductRegister;

import java.util.List;
import java.util.Optional;

public interface ProductRegisterRepository {

    List<ProductRegister> finAll();
    Optional<ProductRegister> findById(Long id);
    ProductRegister save(ProductRegister productRegister);
    ProductRegister update(ProductRegister productRegister);
    void deleteById(Long id);
    void deleteByIdIn(List<Long> ids);


}
