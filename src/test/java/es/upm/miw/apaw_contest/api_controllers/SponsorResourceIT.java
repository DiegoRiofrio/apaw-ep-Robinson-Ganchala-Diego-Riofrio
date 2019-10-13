package es.upm.miw.apaw_contest.api_controllers;

import es.upm.miw.apaw_contest.ApiTestConfig;
import es.upm.miw.apaw_contest.documents.Sponsor;
import es.upm.miw.apaw_contest.dtos.SponsorBasicDto;
import es.upm.miw.apaw_contest.dtos.SponsorCreationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ApiTestConfig
class SponsorResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        SponsorCreationDto sponsorCreationDto = new SponsorCreationDto("iberia", 30.75, "small");
        SponsorBasicDto sponsorBasicDto = this.webTestClient
                .post().uri(SponsorResource.SPONSORS)
                .body(BodyInserters.fromObject(sponsorCreationDto))
                .exchange()
                .expectStatus().isOk()
                .expectBody(SponsorBasicDto.class)
                .returnResult().getResponseBody();
        assertEquals("iberia", sponsorBasicDto.getName());
    }

    @Test
    void testCreateSponsorException() {
        SponsorCreationDto sponsorCreationDto =
                new SponsorCreationDto("LG", -1000, "large");
        this.webTestClient
                .post().uri(SponsorResource.SPONSORS)
                .body(BodyInserters.fromObject(sponsorCreationDto))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
