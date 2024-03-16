package inno.lab5.web.model;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class UpsertProductRequest {
    private Long       productCodeId;
    private Long       client;
    private String     type;
    private String     number;
    private int        priority;
    private Timestamp dateOfConclusion;
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
}
