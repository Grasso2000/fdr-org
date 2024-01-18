package eu.tasgroup.fdr.service;

import eu.tasgroup.fdr.models.get_fdr.Fdr;
import eu.tasgroup.fdr.repository.FdrRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class FdrService {

    private final FdrRepository fdrRepository;

    public FdrService(FdrRepository fdrRepository) {
        this.fdrRepository = fdrRepository;
    }

    public Mono<Fdr> getFdr(String organizationId, String fdr, int revision, String pspId) {
        return fdrRepository.getFdr(organizationId, fdr, revision, pspId);
    }
}
