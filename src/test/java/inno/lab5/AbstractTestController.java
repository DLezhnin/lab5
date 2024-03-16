package inno.lab5;

import com.fasterxml.jackson.databind.ObjectMapper;
import inno.lab5.model.Product;
import inno.lab5.model.ProductRegister;
import inno.lab5.web.model.ProductRegisterResponse;
import inno.lab5.web.model.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class AbstractTestController {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    protected Product createProduct(Long id, ProductRegister productRegister){

        Product product = new Product(
                id,
                2L,
                2L,
                "Type",
                "number",
                1,
                null,
                null,
                null,
                6,
                BigDecimal.valueOf(10.01),
                BigDecimal.valueOf(100.01),
                BigDecimal.valueOf(1000.01),
                "RegisterType",
                "InterestRateType",
                BigDecimal.valueOf(9),
                "ReasonClose",
                "State",
                new ArrayList<>()
        );
        if(productRegister != null){
            productRegister.setProduct(product);
            product.addProductRegister(productRegister);
        }
        return product;
    }

    protected ProductRegister createProductRegister(Long id, Product product){
            ProductRegister productRegister = new ProductRegister(
                    id,
                    product,
                    "Type",
                    2L,
                    "CurrencyCode",
                    "State",
                    "AccountNumber"
            );
        return productRegister;
    }

    protected ProductResponse createProductResponse(Long id, ProductRegisterResponse productRegisterResponse){
        ProductResponse productResponse = new ProductResponse(
                id,
                2L,
                2L,
                "Type",
                "number",
                1,
                null,
                null,
                null,
                6,
                BigDecimal.valueOf(10.01),
                BigDecimal.valueOf(100.01),
                BigDecimal.valueOf(1000.01),
                "RegisterType",
                "InterestRateType",
                BigDecimal.valueOf(9),
                "ReasonClose",
                "State",
                new ArrayList<>()
        );
        if(productRegisterResponse != null){
            productResponse.getProductRegisters().add(productRegisterResponse);
        }
        return productResponse;
    }

    protected ProductRegisterResponse createProductRegisterResponse(Long id) {
        return new ProductRegisterResponse(
                id,
                1L,
                "Type",
                2L,
                "RUR",
                "State",
                "40815810000000000001"
        );
    }
}