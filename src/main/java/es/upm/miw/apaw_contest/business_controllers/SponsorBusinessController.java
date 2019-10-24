package es.upm.miw.apaw_contest.business_controllers;

import es.upm.miw.apaw_contest.daos.SponsorDao;
import es.upm.miw.apaw_contest.documents.Sponsor;
import es.upm.miw.apaw_contest.dtos.SponsorBasicDto;
import es.upm.miw.apaw_contest.dtos.SponsorCreationDto;
import es.upm.miw.apaw_contest.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SponsorBusinessController {

    private SponsorDao sponsorDao;

    private EmitterProcessor<String> emitterProcessor;

    @Autowired
    public SponsorBusinessController(SponsorDao sponsorDao) {
        this.sponsorDao = sponsorDao;
        this.emitterProcessor = EmitterProcessor.create();
    }

    public Flux<String> publisher() {
        return this.emitterProcessor;
    }

    public SponsorBasicDto create(SponsorCreationDto sponsorCreationDto) {
        Sponsor sponsor = new Sponsor(sponsorCreationDto.getName(), sponsorCreationDto.getDonatedAmount(), sponsorCreationDto.getSponsorType());
        this.sponsorDao.save(sponsor);
        SponsorBasicDto sponsorBasicDtoSaved = new SponsorBasicDto(sponsor);
        this.emitterProcessor.onNext(sponsorBasicDtoSaved.getName());
        return sponsorBasicDtoSaved;
    }

    public SponsorCreationDto getSponsorById(String id) {
        return new SponsorCreationDto(this.sponsorDao.findById(id).orElseThrow(() -> new NotFoundException("Sponsor id not found: " + id))
        );
    }

    public SponsorCreationDto readType(String id) {
        return new SponsorCreationDto(this.findSponsorById(id));
    }

    private Sponsor findSponsorById(String id) {
        return this.sponsorDao.findById(id).orElseThrow(() -> new NotFoundException("Sponsor id not found: " + id));
    }

    public void updateType(String id, String sponsorType) {
        Sponsor sponsor = this.findSponsorById(id);
        sponsor.setSponsorType(sponsorType);
        this.sponsorDao.save(sponsor);
    }

    public void delete(String id) {
        getSponsorById(id);
        this.sponsorDao.deleteById(id);
    }

    private Sponsor findSponsorAndEdit(String id, String name) {
        Sponsor sponsor = findSponsorById(id);
        sponsor.setName(name);
        return sponsor;
    }

    public void patch(List<SponsorBasicDto> sponsors) {
        List<Sponsor> sponsorList = sponsors.stream()
                .map(value ->
                        findSponsorAndEdit(value.getId(), value.getName())).collect(Collectors.toList());
        this.sponsorDao.saveAll(sponsorList);
    }
}
