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
import net.javacrumbs.jsonunit.JsonAssert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
}
