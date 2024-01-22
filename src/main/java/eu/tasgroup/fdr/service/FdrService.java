package eu.tasgroup.fdr.service;

import eu.tasgroup.fdr.models.allpublished.GetAll;
import eu.tasgroup.fdr.models.fdr.FdrPlusPayments;
import eu.tasgroup.fdr.models.payments.ApiResponsePayments;
import eu.tasgroup.fdr.models.published.PublishedFdr;
import eu.tasgroup.fdr.service.mapper.FdrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class FdrService {

    @Autowired
    private WebClient webClient;
    @Autowired
    private FdrMapper fdrMapper;

    public Mono<GetAll> fetchGetAllPublished(String organizationId) {
        String apiUrl = "/organizations/{organizationId}/fdrs";
        return fetchDataFromRemoteApi(apiUrl, GetAll.class, organizationId);
    }

    public Mono<PublishedFdr> fetchGetPublished(String organizationId, String fdr, String revision, String pspId) {
        String apiUrl = "/organizations/{organizationId}/fdrs/{fdr}/revisions/{revision}/psps/{pspId}";
        return fetchDataFromRemoteApi(apiUrl, PublishedFdr.class, organizationId, fdr, revision, pspId);
    }

    public Mono<ApiResponsePayments> fetchPayments(String organizationId, String fdr, String revision, String pspId) {
        String apiUrl = "/organizations/{organizationId}/fdrs/{fdr}/revisions/{revision}/psps/{pspId}/payments";
        return fetchDataFromRemoteApi(apiUrl, ApiResponsePayments.class, organizationId, fdr, revision, pspId);
    }

    public Mono<FdrPlusPayments> fetchCompleteFdr(String organizationId, String fdr, String revision, String pspId) {
        Mono<PublishedFdr> publishedFdrMono = fetchGetPublished(organizationId, fdr, revision, pspId);
        Mono<ApiResponsePayments> paymentsMono = fetchPayments(organizationId, fdr, revision, pspId);

        return Mono.zip(publishedFdrMono, paymentsMono)
                .map(tuple -> {
                    FdrPlusPayments fdrPlusPayments = fdrMapper.toFdrPlusPayments(tuple.getT1());
                    fdrPlusPayments.setPaymentList(tuple.getT2().getData());
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
}