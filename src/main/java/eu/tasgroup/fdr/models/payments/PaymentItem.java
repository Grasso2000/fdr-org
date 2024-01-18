package eu.tasgroup.fdr.models.payments;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentItem {

    private String iuv;

    private String iur;

    private int index;

    private double pay;

    private String payStatus;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime payDate;
}
