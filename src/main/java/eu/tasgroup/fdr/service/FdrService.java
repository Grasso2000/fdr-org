package eu.tasgroup.fdr.service;

import eu.tasgroup.fdr.models.allpublished.GetAll;
import eu.tasgroup.fdr.models.fdr.FdrPlusPayments;
import eu.tasgroup.fdr.models.payments.ApiResponsePayments;
import eu.tasgroup.fdr.models.published.PublishedFdr;
import eu.tasgroup.fdr.service.mapper.FdrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class FdrService {

    @Autowired
    private WebClient webClient;
    @Autowired
    private FdrMapper fdrMapper;

    public Mono<GetAll> fetchGetAllPublished(String organizationId) {
        String apiUrl = "/organizations/{organizationId}/fdrs";
        return fetchDataFromRemoteApi(apiUrl, GetAll.class, organizationId)
                .onErrorResume(throwable -> handleApiError(throwable, GetAll.class));
    }

    public Mono<PublishedFdr> fetchGetPublished(String organizationId, String fdr, String revision, String pspId) {
        String apiUrl = "/organizations/{organizationId}/fdrs/{fdr}/revisions/{revision}/psps/{pspId}";
        return fetchDataFromRemoteApi(apiUrl, PublishedFdr.class, organizationId, fdr, revision, pspId)
                .onErrorResume(throwable -> handleApiError(throwable, PublishedFdr.class));
    }

    public Mono<ApiResponsePayments> fetchPayments(String organizationId, String fdr, String revision, String pspId) {
        String apiUrl = "/organizations/{organizationId}/fdrs/{fdr}/revisions/{revision}/psps/{pspId}/payments";
        return fetchDataFromRemoteApi(apiUrl, ApiResponsePayments.class, organizationId, fdr, revision, pspId)
                .onErrorResume(throwable -> handleApiError(throwable, ApiResponsePayments.class));
    }

    public Mono<FdrPlusPayments> fetchCompleteFdr(String organizationId, String fdr, String revision, String pspId) {
        Mono<PublishedFdr> fdrResponseMono = fetchGetPublished(organizationId, fdr, revision, pspId);
        Mono<ApiResponsePayments> paymentsResponseMono = fetchPayments(organizationId, fdr, revision, pspId);
        return Mono.zip(fdrResponseMono, paymentsResponseMono, (publishedFdr, apiResponsePayments) -> {
            FdrPlusPayments fdrPlusPayments = fdrMapper.toFdrPlusPayments(publishedFdr);
            fdrPlusPayments.setPaymentList(apiResponsePayments.getData());
            return fdrPlusPayments;
        });
    }

    private <T> Mono<T> fetchDataFromRemoteApi(String apiUrl, Class<T> responseType, Object... uriVariables) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path(apiUrl).build(uriVariables))
                .retrieve()
                .bodyToMono(responseType);
    }

    private <T> Mono<T> handleApiError(Throwable throwable, Class<T> responseType) {
        String responseBody = null;
        WebClientResponseException webClientException;

        if (throwable instanceof WebClientResponseException) {
            webClientException = (WebClientResponseException) throwable;
            responseBody = webClientException.getResponseBodyAsString();
            HttpStatusCode statusCode = webClientException.getStatusCode();
        }

        return (Mono<T>) Mono.justOrEmpty(responseBody);
    }

}