package eu.tasgroup.fdr.models.error_response;

import lombok.Data;
import java.util.List;

@Data
public class ErrorResponse {
    private String errorId;
    private int httpStatusCode;
    private String httpStatusDescription;
    private String appErrorCode;
    private List<ErrorDetail> errors;
}
