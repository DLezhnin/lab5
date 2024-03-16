package inno.lab5.mapper.v1;

import inno.lab5.model.ProductRegister;
import inno.lab5.service.ProductService;
import inno.lab5.web.model.ProductRegisterListResponse;
import inno.lab5.web.model.ProductRegisterResponse;
import inno.lab5.web.model.ProductResponse;
import inno.lab5.web.model.UpsertProductRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductRegisterMapper {
    private final ProductService productService;

    public ProductRegister requestToProductRegister(UpsertProductRegisterRequest request){
        ProductRegister productRegister = new ProductRegister();

        productRegister.setProduct(productService.findById(request.getProduct()));
        productRegister.setType(request.getType());
        productRegister.setAccount(request.getAccount());
        productRegister.setCurrencyCode(request.getCurrencyCode());
        productRegister.setState(request.getState());
        productRegister.setAccountNumber(request.getAccountNumber());

        return productRegister;
    }

    public ProductRegister requestToProductRegister(Long productRegisterId, UpsertProductRegisterRequest request) {
        ProductRegister productRegister = requestToProductRegister(request);
        productRegister.setId(productRegisterId);

        return productRegister;
    }

    public ProductRegisterResponse productRegisterToResponse(ProductRegister productRegister){
        ProductRegisterResponse productRegisterResponse = new ProductRegisterResponse();

        productRegisterResponse.setId(productRegister.getId());
       // productRegisterResponse.setProduct(productRegister.getProduct());

        productRegisterResponse.setType(productRegister.getType());
        productRegisterResponse.setAccount(productRegister.getAccount());
        productRegisterResponse.setCurrencyCode(productRegister.getCurrencyCode());
        productRegisterResponse.setState(productRegister.getState());
        productRegisterResponse.setAccountNumber(productRegister.getAccountNumber());

        return productRegisterResponse;
    }

    public List<ProductRegisterResponse> productRegisterListToResponseList(List<ProductRegister> productRegisters){
        return productRegisters.stream()
                .map(this::productRegisterToResponse)
                .collect(Collectors.toList());
    }

    public ProductRegisterListResponse productRegisterListToProductRegisterListRespons(List<ProductRegister> productRegisters){
        ProductRegisterListResponse response = new ProductRegisterListResponse();
        response.setProductRegisters(productRegisterListToResponseList(productRegisters));

        return response;
    }
}
