package eu.tasgroup.fdr.controller;


import eu.tasgroup.fdr.model.allpublished.ApiResponseGetAll;
import eu.tasgroup.fdr.model.payments.ApiResponsePeyments;
import eu.tasgroup.fdr.model.published.ApiResponsePublished;
import eu.tasgroup.fdr.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class AppController {

    private final AppService appService;

    @Autowired
    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("/organization-fdrs/{organizationId}/fdrs")
    public Mono<ApiResponseGetAll> getGetAllPublished(
            @PathVariable("organizationId") String organizationId) {
        return appService.fetchGetAllPublished(organizationId);
    }

    @GetMapping("/psp-details/{organizationId}/{fdr}/{revision}/{pspId}")
    public Mono<ApiResponsePublished> getPublished(
            @PathVariable String organizationId,
            @PathVariable String fdr,
            @PathVariable String revision,
            @PathVariable String pspId) {
        return appService.fetchGetPublished(organizationId, fdr, revision, pspId);
    }

    @GetMapping("/payments/{organizationId}/{fdr}/{revision}/{pspId}")
    public Mono<ApiResponsePeyments> getPayments(
            @PathVariable String organizationId,
            @PathVariable String fdr,
            @PathVariable String revision,
            @PathVariable String pspId) {
        return appService.fetchPayments(organizationId, fdr, revision, pspId);
    }
}