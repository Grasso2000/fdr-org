package eu.tasgroup.fdr.controller;

import eu.tasgroup.fdr.models.get_all_published.Fdr;
import eu.tasgroup.fdr.service.FdrService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fdr")
public class FdrController {

    private final FdrService fdrService;

    public FdrController(FdrService fdrService) {
        this.fdrService = fdrService;
    }

    @GetMapping("/organizations/{organizationId}/fdrs/{fdr}/revisions/{revision}/psps/{pspId}")
    public Mono<ResponseEntity<Fdr>> getFdr(@PathVariable String organizationId, @PathVariable String fdr,
                                            @PathVariable int revision, @PathVariable String pspId) {
        return fdrService.getFdr(organizationId, fdr, revision, pspId)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> {
                    if (e instanceof HttpClientErrorException clientError) {
                        return Mono.just(ResponseEntity.status(clientError.getStatusCode()).body(null));
                    } else if (e instanceof HttpServerErrorException serverError) {
                        return Mono.just(ResponseEntity.status(serverError.getStatusCode()).body(null));
                    } else {
                        return Mono.just(ResponseEntity.status(500).body(null));
                    }
                });
    }
}
