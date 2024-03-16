package inno.lab5.service.impl;

import inno.lab5.exception.EntityNotFoundException;
import inno.lab5.exception.UpdateStateException;
import inno.lab5.model.ProductRegister;
import inno.lab5.repository.ProductRegisterRepository;
import inno.lab5.service.ProductRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductRegisterServiceImpl implements ProductRegisterService {

    private final ProductRegisterRepository productRegisterRepository;

    @Override
    public List<ProductRegister> findAll() {
        return productRegisterRepository.finAll();
    }

    @Override
    public ProductRegister findById(Long id) {
        return productRegisterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Продуктовый репозиторий с id {0} не найден!",id)));
    }

    @Override
    public ProductRegister save(ProductRegister productRegister) {
        return productRegisterRepository.save(productRegister);
    }

    @Override
    public ProductRegister update(ProductRegister productRegister) {
        //checkForUpdate(productRegister.getId());
        return productRegisterRepository.update(productRegister);
    }

    @Override
    public void deleteById(Long id) {
        productRegisterRepository.deleteById(id);
    }

    @Override
    public void deleteByIdin(List<Long> ids) {
        productRegisterRepository.deleteByIdIn(ids);
    }

//    private void checkForUpdate(Long productRegistrId){
//        ProductRegister currentProductRegister = findById(productRegistrId);
//        Instant now = Instant.now();
//        Duration duration = Duration.between(дата первая, now);
//        if(duration.getSeconds()>5){
//            throw  new UpdateStateException("Невозможно изменить");
//        }
//    }
}
