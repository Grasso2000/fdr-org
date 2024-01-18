package eu.tasgroup.fdr.models.payments;

import lombok.Data;

import java.util.List;

@Data
public class ApiResponsePayments {

    private List<PaymentItem> data;
}
