package eu.tasgroup.fdr.models.error_response;

public class FdrException extends RuntimeException {
    private final ErrorResponse errorResponse;

    public FdrException(ErrorResponse errorResponse) {
        super(errorResponse.getHttpStatusDescription());
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
