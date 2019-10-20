package es.upm.miw.apaw_contest.api_controllers;

import es.upm.miw.apaw_contest.ApiTestConfig;
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
        SponsorBasicDto sponsorBasicDto = createSponsor("iberia", 30.75, "small");
        assertEquals("iberia", sponsorBasicDto.getName());
    }

    SponsorBasicDto createSponsor(String name, double donatedAmount, String sponsorType) {
        SponsorCreationDto sponsorCreationDto = new SponsorCreationDto(name, donatedAmount, sponsorType);
        return this.webTestClient
                .post().uri(SponsorResource.SPONSORS)
                .body(BodyInserters.fromObject(sponsorCreationDto))
                .exchange()
                .expectStatus().isOk()
                .expectBody(SponsorBasicDto.class)
                .returnResult().getResponseBody();
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

    SponsorCreationDto getSponsorWithType(String id) {
        return this.webTestClient
                .get().uri(SponsorResource.SPONSORS + SponsorResource.ID_ID + SponsorResource.TYPE, id)
                .exchange()
                .expectStatus().isOk()
                .expectBody(SponsorCreationDto.class)
                .returnResult().getResponseBody();
    }

    @Test
    void testPutSponsorType() {
        SponsorBasicDto sponsorBasicDto = createSponsor("SONY", 10000.750, "medium");
        SponsorCreationDto sponsorToSet = new SponsorCreationDto();
        sponsorToSet.setSponsorType("small");
        this.webTestClient
                .put().uri(SponsorResource.SPONSORS + SponsorResource.ID_ID + SponsorResource.TYPE, sponsorBasicDto.getId())
                .body(BodyInserters.fromObject(sponsorToSet))
                .exchange()
                .expectStatus().isOk();
        sponsorToSet = getSponsorWithType(sponsorBasicDto.getId());
        assertEquals(sponsorBasicDto.getId(), sponsorToSet.getId());
        assertEquals("small", sponsorToSet.getSponsorType());
    }
}
