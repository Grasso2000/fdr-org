package eu.tasgroup.fdr.models.fdr;

import eu.tasgroup.fdr.models.payments.PaymentItem;
import eu.tasgroup.fdr.models.published.PublishedFdr;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Data
public class FdrPlusPayments extends PublishedFdr {

    private List<PaymentItem> paymentList;
}
