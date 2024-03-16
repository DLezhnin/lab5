package inno.lab5.model;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long       id;
    private Long       productCodeId;
    private Long       client;
    private String     type;
    private String     number;
    private int        priority;
    private Timestamp  dateOfConclusion;
    private Timestamp  startDateTime;
    private Timestamp  endDateTime;
    private int        days;
    private BigDecimal penaltyRate;
    private BigDecimal nso;
    private BigDecimal thresholdAmount;
    private String     requisiteType;
    private String     interestRateType;
    private BigDecimal taxRate;
    private String     reasonClose;
    private String     state;
    private List<ProductRegister> productRegisters = new ArrayList<>();

    public void addProductRegister(ProductRegister productRegister){
        productRegisters.add(productRegister);
    }

    public void removeProductRegister(Long productRegisterId){
        productRegisters = productRegisters.stream().filter(o -> !o.getId().equals(productRegisterId)).collect(Collectors.toList());
    }
}
