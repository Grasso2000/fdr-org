package eu.tasgroup.fdr.models.fdr;

import eu.tasgroup.fdr.models.payments.ApiResponsePayments;
import eu.tasgroup.fdr.models.payments.PaymentItem;
import eu.tasgroup.fdr.models.published.PublishedFdr;
import lombok.Data;

import java.util.List;
@Data
public class FdrPlusPayments extends PublishedFdr{

    private List<PaymentItem> paymentList;
}
