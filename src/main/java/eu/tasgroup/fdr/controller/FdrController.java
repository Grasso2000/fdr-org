package eu.tasgroup.fdr.controller;


import eu.tasgroup.fdr.models.allpublished.ApiResponseGetAll;
import eu.tasgroup.fdr.models.payments.ApiResponsePayments;
import eu.tasgroup.fdr.models.published.ApiResponsePublished;
import eu.tasgroup.fdr.service.FdrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class FdrController {

    private final FdrService fdrService;

    @Autowired
    public FdrController(FdrService fdrService) {
        this.fdrService = fdrService;
    }

    @GetMapping(value = "/organization-fdrs/{organizationId}/fdrs",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiResponseGetAll> getGetAllPublished(
            @PathVariable("organizationId") String organizationId) {
        return fdrService.fetchGetAllPublished(organizationId);
    }

    @GetMapping(value = "/psp-details/{organizationId}/{fdr}/{revision}/{pspId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiResponsePublished> getPublished(
            @PathVariable String organizationId,
            @PathVariable String fdr,
            @PathVariable String revision,
            @PathVariable String pspId) {
        return fdrService.fetchGetPublished(organizationId, fdr, revision, pspId);
    }

    @GetMapping(value = "/payments/{organizationId}/{fdr}/{revision}/{pspId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiResponsePayments> getPayments(
            @PathVariable String organizationId,
            @PathVariable String fdr,
            @PathVariable String revision,
            @PathVariable String pspId) {
        return fdrService.fetchPayments(organizationId, fdr, revision, pspId);
    }
}