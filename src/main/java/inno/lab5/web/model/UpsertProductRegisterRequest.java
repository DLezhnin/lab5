package inno.lab5.web.model;

import inno.lab5.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertProductRegisterRequest {
    private Long    id;
    private Long product;
    private String  type;
    private Long    account;
    private String  currencyCode;
    private String  state;
    private String  accountNumber;
}
