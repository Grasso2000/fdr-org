package eu.tasgroup.fdr.models.get_fdr;

import lombok.Data;
import java.time.ZonedDateTime;

@Data
public class Fdr {
    private String status;
    private int revision;
    private ZonedDateTime created;
    private ZonedDateTime updated;
    private String fdr;
    private ZonedDateTime fdrDate;
    private String regulation;
    private ZonedDateTime regulationDate;
    private String bicCodePouringBank;
    private Party sender;
    private Party receiver;
    private ZonedDateTime published;
    private int computedTotPayments;
    private double computedSumPayments;
    private int totPayments;
    private double sumPayments;
}

