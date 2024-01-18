package eu.tasgroup.fdr.service;

import eu.tasgroup.fdr.models.allpublished.ApiResponseGetAll;
import eu.tasgroup.fdr.models.payments.ApiResponsePayments;
import eu.tasgroup.fdr.models.published.ApiResponsePublished;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class FdrService {

    @Autowired
    private WebClient webClient;

    public Mono<ApiResponseGetAll> fetchGetAllPublished(String organizationId) {
        String apiUrl = "/organizations/{organizationId}/fdrs";
        return fetchDataFromRemoteApi(apiUrl, ApiResponseGetAll.class, organizationId);
    }

    public Mono<ApiResponsePublished> fetchGetPublished(String organizationId, String fdr, String revision,
                                                        String pspId) {
        String apiUrl = "/organizations/{organizationId}/fdrs/{fdr}/revisions/{revision}/psps/{pspId}";
        return fetchDataFromRemoteApi(apiUrl, ApiResponsePublished.class, organizationId, fdr, revision, pspId);
    }

    public Mono<ApiResponsePayments> fetchPayments(String organizationId, String fdr, String revision, String pspId) {
        String apiUrl = "/organizations/{organizationId}/fdrs/{fdr}/revisions/{revision}/psps/{pspId}/payments";
        return fetchDataFromRemoteApi(apiUrl, ApiResponsePayments.class, organizationId, fdr, revision, pspId);
    }

    private <T> Mono<T> fetchDataFromRemoteApi(String apiUrl, Class<T> responseType, Object... uriVariables) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path(apiUrl).build(uriVariables))
                .retrieve()
                .bodyToMono(responseType);
    }
}