package inno.lab5.web.controller.v1;

import inno.lab5.exception.EntityNotFoundException;
import inno.lab5.mapper.v1.ProductMapper;
import inno.lab5.model.Product;
import inno.lab5.service.ProductService;
import inno.lab5.web.model.ProductListResponse;
import inno.lab5.web.model.ProductResponse;
import inno.lab5.web.model.UpsertProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<ProductListResponse> findAll(){
        return ResponseEntity.ok(
                productMapper.productListToProductResponseList(productService.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                productMapper.productToResponse(productService.findById(id))
        );
    }

    @PostMapping
    public  ResponseEntity<ProductResponse> create(@RequestBody UpsertProductRequest request){
        Product newProduct = productService.save(productMapper.requestToProduct(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                        .body(productMapper.productToResponse(newProduct));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable("id") Long productId,
                                                  @RequestBody UpsertProductRequest request){
        Product updateProduct = productService.update(productMapper.requestToProduct(productId,request));

        return ResponseEntity.ok(productMapper.productToResponse(updateProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
