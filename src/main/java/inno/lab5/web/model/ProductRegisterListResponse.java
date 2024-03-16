package inno.lab5.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRegisterListResponse {
    private List<ProductRegisterResponse> productRegisters = new ArrayList<>();
}
