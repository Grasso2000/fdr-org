package eu.tasgroup.fdr.model.payments;

import lombok.Data;

import java.util.List;

@Data
public class ApiResponsePeyments {

    private List<PaymentItem> data;
}
