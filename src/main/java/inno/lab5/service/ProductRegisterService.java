package inno.lab5.service;

import inno.lab5.model.ProductRegister;

import java.util.List;

public interface ProductRegisterService {
    List<ProductRegister> findAll();
    ProductRegister findById(Long id);
    ProductRegister save(ProductRegister productRegister);
    ProductRegister update(ProductRegister ProductRegister);
    void deleteById(Long id);
    void deleteByIdin(List<Long> ids);
}
