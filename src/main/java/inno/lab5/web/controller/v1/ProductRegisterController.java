package inno.lab5.web.controller.v1;

import inno.lab5.mapper.v1.ProductRegisterMapper;
import inno.lab5.model.ProductRegister;
import inno.lab5.service.ProductRegisterService;
import inno.lab5.web.model.ProductRegisterListResponse;
import inno.lab5.web.model.ProductRegisterResponse;
import inno.lab5.web.model.UpsertProductRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/productRegister")
@RequiredArgsConstructor
public class ProductRegisterController {

    private final ProductRegisterService productRegisterService;
    private final ProductRegisterMapper productRegisterMapper;

    @GetMapping
    public ResponseEntity<ProductRegisterListResponse> findAll(){
        return ResponseEntity.ok(productRegisterMapper.productRegisterListToProductRegisterListRespons(productRegisterService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductRegisterResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                productRegisterMapper.productRegisterToResponse(productRegisterService.findById(id))
        );
    }

    @PostMapping
    public ResponseEntity<ProductRegisterResponse> create(@RequestBody UpsertProductRegisterRequest request){

        ProductRegister newProductRegister = productRegisterService.save(productRegisterMapper.requestToProductRegister(request));

        return  ResponseEntity.status(HttpStatus.CREATED).body(productRegisterMapper.productRegisterToResponse(newProductRegister));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductRegisterResponse> update(@PathVariable("id") Long productRegisterId,
                                                 @RequestBody UpsertProductRegisterRequest request){
        ProductRegister updatedProductRegister = productRegisterService.update(productRegisterMapper.requestToProductRegister(productRegisterId,request));

        return ResponseEntity.ok(productRegisterMapper.productRegisterToResponse(updatedProductRegister));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productRegisterService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
