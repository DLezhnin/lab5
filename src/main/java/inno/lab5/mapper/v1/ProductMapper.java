package inno.lab5.mapper.v1;

import inno.lab5.model.Product;
import inno.lab5.web.model.ProductListResponse;
import inno.lab5.web.model.ProductResponse;
import inno.lab5.web.model.UpsertProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final ProductRegisterMapper productRegisterMapper;

    public Product requestToProduct(UpsertProductRequest request){
        Product product = new Product();

        product.setProductCodeId(request.getProductCodeId());
        product.setClient(request.getClient());
        product.setType(request.getType());
        product.setNumber(request.getNumber());
        product.setPriority(request.getPriority());
        product.setDateOfConclusion(request.getDateOfConclusion());
        product.setStartDateTime(request.getStartDateTime());
        product.setEndDateTime(request.getEndDateTime());
        product.setDays(request.getDays());
        product.setPenaltyRate(request.getPenaltyRate());
        product.setNso(request.getNso());
        product.setThresholdAmount(request.getThresholdAmount());
        product.setRequisiteType(request.getRequisiteType());
        product.setInterestRateType(request.getInterestRateType());
        product.setTaxRate(request.getTaxRate());
        product.setReasonClose(request.getReasonClose());
        product.setState(request.getState());

        return product;
    }

    public Product requestToProduct(Long productid, UpsertProductRequest request){
        Product product = requestToProduct(request);
        product.setId(productid);

        return product;
    }

    public ProductResponse productToResponse(Product product){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setProductCodeId(product.getProductCodeId());
        productResponse.setClient(product.getClient());
        productResponse.setType(product.getType());
        productResponse.setNumber(product.getNumber());
        productResponse.setPriority(product.getPriority());
        productResponse.setDateOfConclusion(product.getDateOfConclusion());
        productResponse.setStartDateTime(product.getStartDateTime());
        productResponse.setEndDateTime(product.getEndDateTime());
        productResponse.setDays(product.getDays());
        productResponse.setPenaltyRate(product.getPenaltyRate());
        productResponse.setNso(product.getNso());
        productResponse.setThresholdAmount(product.getThresholdAmount());
        productResponse.setRequisiteType(product.getRequisiteType());
        productResponse.setInterestRateType(product.getInterestRateType());
        productResponse.setTaxRate(product.getTaxRate());
        productResponse.setReasonClose(product.getReasonClose());
        productResponse.setState(product.getState());
        productResponse.setProductRegisters(productRegisterMapper.productRegisterListToResponseList(product.getProductRegisters()));
        return productResponse;
    }

    public ProductListResponse productListToProductResponseList(List<Product> products){
        ProductListResponse response = new ProductListResponse();

        response.setProducts(products.stream().map(this::productToResponse).collect(Collectors.toList()));

        return response;
    }
}
