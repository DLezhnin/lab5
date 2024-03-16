package inno.lab5.web.model;

import inno.lab5.model.ProductRegister;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
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
    private List<ProductRegisterResponse> productRegisters = new ArrayList<>();

}
