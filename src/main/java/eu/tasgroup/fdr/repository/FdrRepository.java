package eu.tasgroup.fdr.repository;

import eu.tasgroup.fdr.models.get_all_published.Fdr;
import org.springframework.beans.factory.annotation.Value;
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
        String url = baseUrl + String.format("/organizations/%s/fdrs/%s/revisions/%d/psps/%s", organizationId, fdr,
                revision, pspId);
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Fdr.class);
    }
}
