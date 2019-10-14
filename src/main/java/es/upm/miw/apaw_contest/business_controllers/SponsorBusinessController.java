package es.upm.miw.apaw_contest.business_controllers;

import es.upm.miw.apaw_contest.daos.SponsorDao;
import es.upm.miw.apaw_contest.documents.Sponsor;
import es.upm.miw.apaw_contest.dtos.SponsorBasicDto;
import es.upm.miw.apaw_contest.dtos.SponsorCreationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SponsorBusinessController {

    private SponsorDao sponsorDao;

    @Autowired
    public SponsorBusinessController(SponsorDao sponsorDao) {
        this.sponsorDao = sponsorDao;
    }

    public SponsorBasicDto create(SponsorCreationDto sponsorCreationDto) {
        Sponsor sponsor = new Sponsor(sponsorCreationDto.getName(), sponsorCreationDto.getDonatedAmount(), sponsorCreationDto.getSponsorType());
        this.sponsorDao.save(sponsor);
        return new SponsorBasicDto(sponsor);
    }
}
