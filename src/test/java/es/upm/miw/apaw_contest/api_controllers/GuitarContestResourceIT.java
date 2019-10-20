package es.upm.miw.apaw_contest.api_controllers;


import es.upm.miw.apaw_contest.ApiTestConfig;
import es.upm.miw.apaw_contest.dtos.GuitarContestBasicDto;
import es.upm.miw.apaw_contest.dtos.GuitarContestCreationDto;
import es.upm.miw.apaw_contest.dtos.GuitarPlayerCreationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

@ApiTestConfig
public class GuitarContestResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        GuitarContestBasicDto guitarContestBasicDto = createGuitarContest(LocalDateTime.of(2020,2,28,22,30), "AlexanderPlatz","Alemania");
        assertEquals("AlexanderPlatz", guitarContestBasicDto.getAddress());
    }

    GuitarContestBasicDto createGuitarContest(LocalDateTime date, String address, String country) {
        GuitarContestCreationDto guitarContestCreationDto = new GuitarContestCreationDto(date, address, country);
        return this.webTestClient
                .post().uri(GuitarContestResource.GUITARCONTEST)
                .body(BodyInserters.fromObject(guitarContestCreationDto))
                .exchange()
                .expectStatus().isOk()
                .expectBody(GuitarContestBasicDto.class)
                .returnResult().getResponseBody();
    }

    @Test
    void testCreateGuitarContestExeption() {
        GuitarContestCreationDto guitarContestCreationDto = new GuitarContestCreationDto(LocalDateTime.of(2030,2,28,22,30), "Branes2", "");
        this.webTestClient
                .post().uri(GuitarContestResource.GUITARCONTEST)
                .body(BodyInserters.fromObject(guitarContestCreationDto))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
