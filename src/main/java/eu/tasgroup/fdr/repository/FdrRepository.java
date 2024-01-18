package eu.tasgroup.fdr.repository;

import eu.tasgroup.fdr.models.error_response.ErrorResponse;
import eu.tasgroup.fdr.models.error_response.FdrException;
import eu.tasgroup.fdr.models.get_fdr.Fdr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
public class FdrRepository {

    private final WebClient webClient;
    private final String baseUrl;

    public FdrRepository(WebClient webClient, @Value("${app.base-url}") String baseUrl) {
        this.webClient = webClient;
        this.baseUrl = baseUrl;
    }

    public Mono<Fdr> getFdr(String organizationId, String fdr, int revision, String pspId) {
        String url = baseUrl + String.format("/organizations/%s/fdrs/%s/revisions/%d/psps/%s", organizationId, fdr, revision, pspId);
        return webClient.get()
                .uri(url)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                        clientResponse.bodyToMono(ErrorResponse.class).flatMap(error -> Mono.error(new FdrException(error))))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
                        clientResponse.bodyToMono(ErrorResponse.class).flatMap(error -> Mono.error(new FdrException(error))))
                .bodyToMono(Fdr.class);
    }
}
