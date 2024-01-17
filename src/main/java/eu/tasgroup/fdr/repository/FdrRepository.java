package eu.tasgroup.fdr.repository;

import eu.tasgroup.fdr.models.get_all_published.Fdr;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
public class FdrRepository {

    private final WebClient webClient;

    public FdrRepository(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Fdr> getFdr(String organizationId, String fdr, int revision, String pspId) {
        String url = String.format("/organizations/%s/fdrs/%s/revisions/%d/psps/%s", organizationId, fdr, revision, pspId);
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Fdr.class);
    }
}
