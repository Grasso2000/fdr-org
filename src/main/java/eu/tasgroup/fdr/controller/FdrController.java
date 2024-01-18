package eu.tasgroup.fdr.controller;

import eu.tasgroup.fdr.models.error_response.ErrorResponse;
import eu.tasgroup.fdr.service.FdrService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/tas-fdr")
public class FdrController {

    private final FdrService fdrService;

    public FdrController(FdrService fdrService) {
        this.fdrService = fdrService;
    }

    @GetMapping("/organizations/{organizationId}/fdrs/{fdr}/revisions/{revision}/psps/{pspId}")
    public Mono<ResponseEntity<?>> getFdr(@PathVariable String organizationId,
                                          @PathVariable String fdr,
                                          @PathVariable int revision,
                                          @PathVariable String pspId) {
        return fdrService.getFdr(organizationId, fdr, revision, pspId)
                .map(fdrData -> ResponseEntity.ok().body(fdrData)) // ResponseEntity with Fdr as the body
                .onErrorResume(e -> {

                    }
                });
    }
}
