package es.upm.miw.apaw_contest.business_controllers;

import es.upm.miw.apaw_contest.TestConfig;
import es.upm.miw.apaw_contest.api_controllers.SponsorResource;
import es.upm.miw.apaw_contest.dtos.SponsorBasicDto;
import es.upm.miw.apaw_contest.dtos.SponsorCreationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

@TestConfig
class SponsorControllerPublisherIT {

    @Autowired
    SponsorResource sponsorResource;

    @Test
    void sponsorInsert() {
        SponsorCreationDto sponsorCreationDto = new SponsorCreationDto("Robin", 20000.3, "Small");
        StepVerifier
                .create(sponsorResource.publisher())
                .then(() -> sponsorResource.create(sponsorCreationDto))
                .expectNext(sponsorCreationDto.getName())
                .thenCancel().verify();

    }
}
