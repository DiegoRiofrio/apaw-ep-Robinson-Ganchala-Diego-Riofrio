package es.upm.miw.apaw_contest.api_controllers;


import es.upm.miw.apaw_contest.ApiTestConfig;
import es.upm.miw.apaw_contest.documents.Jury;
import es.upm.miw.apaw_contest.dtos.GuitarContestBasicDto;
import es.upm.miw.apaw_contest.dtos.GuitarContestCreationDto;
import es.upm.miw.apaw_contest.dtos.JuryDto;
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
        Jury jury = new Jury(5, "Winner", "Local");
        GuitarContestBasicDto guitarContestBasicDto = createGuitarContest(LocalDateTime.of(2020, 2, 28, 22, 30), "AlexanderPlatz", "Alemania", jury);
        assertEquals("AlexanderPlatz", guitarContestBasicDto.getAddress());
    }

    GuitarContestBasicDto createGuitarContest(LocalDateTime date, String address, String country, Jury jury) {

        GuitarContestCreationDto guitarContestCreationDto = new GuitarContestCreationDto(date, address, country, jury);
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
        Jury jury = new Jury(5, "Winner", "Local");
        GuitarContestCreationDto guitarContestCreationDto = new GuitarContestCreationDto(LocalDateTime.of(2030, 2, 28, 22, 30), "Branes2", "", jury);
        this.webTestClient
                .post().uri(GuitarContestResource.GUITARCONTEST)
                .body(BodyInserters.fromObject(guitarContestCreationDto))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void testGetVeredict() {
        Jury jury = new Jury(5, "Winner", "Local");
        String guitartContestId = this.createGuitarContest(LocalDateTime.of(2020, 2, 28, 15, 30), "parla", "España", jury).getId();

        JuryDto jury2 = this.webTestClient
                .get().uri(GuitarContestResource.GUITARCONTEST + GuitarContestResource.ID_ID + GuitarContestResource.JURY + GuitarContestResource.VEREDICT, guitartContestId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(JuryDto.class)
                .returnResult().getResponseBody();
        assertEquals("Winner", jury2.getVeredict());

    }

    @Test
    void testVeredictNotFoundExeption() {
        Jury jury = new Jury(5, "Winner", "Local");
        String guitarContesdId = this.createGuitarContest(LocalDateTime.of(2020, 10, 3, 13, 30), "San Francisco", "EEUU", jury).getId();
        this.webTestClient
                .get().uri(GuitarContestResource.GUITARCONTEST + "/no" + GuitarContestResource.VEREDICT, guitarContesdId)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND);
    }
}
