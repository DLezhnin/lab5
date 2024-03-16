package inno.lab5.web.controller;

import inno.lab5.AbstractTestController;
import inno.lab5.StringTestUtils;
import inno.lab5.mapper.v1.ProductMapper;
import inno.lab5.model.Product;
import inno.lab5.model.ProductRegister;
import inno.lab5.service.ProductService;
import inno.lab5.web.model.ProductListResponse;
import inno.lab5.web.model.ProductRegisterResponse;
import inno.lab5.web.model.ProductResponse;
import inno.lab5.web.model.UpsertProductRequest;
import net.javacrumbs.jsonunit.JsonAssert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest extends AbstractTestController {

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductMapper productMapper;

    @Test
    public void whenFindAll_thenReturnAllProducts() throws Exception{
        List<Product> products = new ArrayList<>();
        products.add(createProduct(1L,null));
        ProductRegister productRegister = createProductRegister(1L,null);
        products.add(createProduct(2L,productRegister));

        List<ProductResponse> productResponses = new ArrayList<>();
        productResponses.add(createProductResponse(1L,null));
        ProductRegisterResponse productRegisterResponse = createProductRegisterResponse(1L);

        productResponses.add((createProductResponse(2L,productRegisterResponse)));
        ProductListResponse productListResponse = new ProductListResponse(productResponses);

        Mockito.when(productService.findAll()).thenReturn(products);
        Mockito.when(productMapper.productListToProductResponseList(products)).thenReturn(productListResponse);

        String actualResponse = mockMvc.perform(get("/v1/product"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String expectedResponse = StringTestUtils.readStringFromResource("response/find_all_products_response.json");

        Mockito.verify(productService, Mockito.times(1)).findAll();
        Mockito.verify(productMapper, Mockito.times(1)).productListToProductResponseList(products);

        JsonAssert.assertJsonEquals(expectedResponse, actualResponse);
    }

    @Test
    public void whenGetProductById_thenReturnProductById() throws Exception{
        Product product = createProduct(1L,null);
        ProductResponse productResponse =createProductResponse(1L,null);

        Mockito.when(productService.findById(1L)).thenReturn(product);
        Mockito.when(productMapper.productToResponse(product)).thenReturn(productResponse);

        String actualResponse = mockMvc.perform(get("/v1/product/1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String expectedResponse = StringTestUtils.readStringFromResource("response/find_product_by_id_response.json");

        Mockito.verify(productService, Mockito.times(1)).findById(1L);
        Mockito.verify(productMapper, Mockito.times(1)).productToResponse(product);

        JsonAssert.assertJsonEquals(expectedResponse, actualResponse);
    }

    @Test
    public void whenCreateProduct_thenReturnNewProduct() throws Exception{
        Product product = new Product();
        product.setProductCodeId(2L);
        product.setClient(2L);
        product.setType("Type");
        product.setNumber("number");
        product.setPriority(1);
        product.setDays(6);
        product.setPenaltyRate(BigDecimal.valueOf(10.01));
        product.setNso(BigDecimal.valueOf(100.01));
        product.setThresholdAmount(BigDecimal.valueOf(1000.01));
        product.setRequisiteType("RegisterType");
        product.setInterestRateType("InterestRateType");
        product.setTaxRate(BigDecimal.valueOf(9));
        product.setReasonClose("ReasonClose");
        product.setState("State");

        Product createproduct = createProduct(1L,null);
        ProductResponse productResponse =createProductResponse(1L,null);
        UpsertProductRequest request = new UpsertProductRequest(2L,2L,"Type","new_number",1,null,null,null,6,BigDecimal.valueOf(10.01),
                BigDecimal.valueOf(100.01),BigDecimal.valueOf(1000.01),"RegisterType","InterestRateType",BigDecimal.valueOf(9),
                "ReasonClose","State");

        Mockito.when(productService.save(product)).thenReturn(createproduct);
        Mockito.when(productMapper.requestToProduct(request)).thenReturn(product);
        Mockito.when(productMapper.productToResponse(createproduct)).thenReturn(productResponse);

        String actualResponse = mockMvc.perform(post("/v1/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String expectedResponse = StringTestUtils.readStringFromResource("response/create_product_response.json");

        Mockito.verify(productService, Mockito.times(1)).save(product);
        Mockito.verify(productMapper, Mockito.times(1)).requestToProduct(request);
        Mockito.verify(productMapper, Mockito.times(1)).productToResponse(createproduct);

        JsonAssert.assertJsonEquals(expectedResponse, actualResponse);


    }

}
