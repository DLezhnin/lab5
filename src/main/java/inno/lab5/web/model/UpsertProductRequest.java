package inno.lab5.web.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertProductRequest {
    private Long       productCodeId;
    private Long       client;
    private String     type;
    @NotBlank(message = "Номер должен быть заполнен")
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
}
