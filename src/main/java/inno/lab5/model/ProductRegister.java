package inno.lab5.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRegister {

    private Long       id;
    private Product    product;
    private String     type;
    private Long       account;
    private String     currencyCode;
    private String     state;
    private String     accountNumber;

}
