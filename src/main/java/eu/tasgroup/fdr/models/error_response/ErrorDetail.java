package eu.tasgroup.fdr.models.error_response;

import lombok.Data;

@Data
public class ErrorDetail {
    private String path;
    private String message;
}
