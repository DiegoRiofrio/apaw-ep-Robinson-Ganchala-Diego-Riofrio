package es.upm.miw.apaw_contest.api_controllers;

import es.upm.miw.apaw_contest.business_controllers.SponsorBusinessController;
import es.upm.miw.apaw_contest.dtos.SponsorBasicDto;
import es.upm.miw.apaw_contest.dtos.SponsorCreationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SponsorResource.SPONSORS)
public class SponsorResource {

    public static final String SPONSORS = "/sponsors";

    private SponsorBusinessController sponsorBusinessController;

    @Autowired
    public SponsorResource(SponsorBusinessController sponsorBusinessController){
        this.sponsorBusinessController = sponsorBusinessController;
    }
    @PostMapping
    public SponsorBasicDto create(@RequestBody SponsorCreationDto sponsorCreationDto){
        sponsorCreationDto.validate();
        return this.sponsorBusinessController.create(sponsorCreationDto);
    }
}
