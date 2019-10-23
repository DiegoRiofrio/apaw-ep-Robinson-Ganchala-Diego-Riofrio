package es.upm.miw.apaw_contest.api_controllers;


import es.upm.miw.apaw_contest.ApiTestConfig;
import es.upm.miw.apaw_contest.documents.Jury;
import es.upm.miw.apaw_contest.dtos.GuitarContestBasicDto;
import es.upm.miw.apaw_contest.dtos.GuitarContestCreationDto;
import es.upm.miw.apaw_contest.dtos.JuryDto;
import es.upm.miw.apaw_contest.dtos.GuitarPlayerCreationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

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
        GuitarPlayerCreationDto guitarPlayerCreationDto = new GuitarPlayerCreationDto("me", "surname", true, 9090090);
        GuitarPlayerCreationDto guitarPlayerCreationDto2 = new GuitarPlayerCreationDto("me2", "surname2", false, 90905090);
        GuitarContestBasicDto guitarContestBasicDto = this.webTestClient
                .post().uri(GuitarContestResource.GUITAR_CONTESTS)
                .body(BodyInserters.fromObject(guitarContestCreationDto))
                .exchange()
                .expectStatus().isOk()
                .expectBody(GuitarContestBasicDto.class)
                .returnResult().getResponseBody();

        postGuitarPlayersByGuitarContest(guitarPlayerCreationDto, guitarContestBasicDto);
        postGuitarPlayersByGuitarContest(guitarPlayerCreationDto2, guitarContestBasicDto);
        return guitarContestBasicDto;
    }

    private void postGuitarPlayersByGuitarContest(GuitarPlayerCreationDto guitarPlayerCreationDto, GuitarContestBasicDto guitarContestBasicDto) {
        this.webTestClient
                .post().uri(GuitarContestResource.GUITAR_CONTESTS + GuitarContestResource.ID_ID + GuitarContestResource.GUITAR_PLAYERS, guitarContestBasicDto.getId())
                .body(BodyInserters.fromObject(guitarPlayerCreationDto))
                .exchange()
                .expectStatus().isOk()
                .expectBody(GuitarPlayerCreationDto.class)
                .returnResult().getResponseBody();
    }

    @Test
    void testCreateGuitarContestExeption() {
        Jury jury = new Jury(5, "Winner", "Local");
        GuitarContestCreationDto guitarContestCreationDto = new GuitarContestCreationDto(LocalDateTime.of(2030, 2, 28, 22, 30), "Branes2", "", jury);

        this.webTestClient
                .post().uri(GuitarContestResource.GUITAR_CONTESTS)
                .body(BodyInserters.fromObject(guitarContestCreationDto))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void testGetVeredict() {
        Jury jury = new Jury(5, "Winner", "Local");
        String guitartContestId = this.createGuitarContest(LocalDateTime.of(2020, 2, 28, 15, 30), "parla", "España", jury).getId();

        JuryDto jury2 = this.webTestClient
                .get().uri(GuitarContestResource.GUITAR_CONTESTS + GuitarContestResource.ID_ID + GuitarContestResource.JURY + GuitarContestResource.VEREDICT, guitartContestId)
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
                .get().uri(GuitarContestResource.GUITAR_CONTESTS + "/no" + GuitarContestResource.VEREDICT, guitarContesdId)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void findAllGuitarPlayerByGuitarContestId() {
        Jury jury = new Jury(5, "Winner", "Local");

        String id = createGuitarContest(LocalDateTime.of(2022, 2, 28, 22, 30), "ANYWHERE", "ANYWHERE", jury).getId();
        List<GuitarPlayerCreationDto> guitarPlayerCreationDtoList =
                this.webTestClient
                        .get().uri(GuitarContestResource.GUITAR_CONTESTS + GuitarContestResource.ID_ID + GuitarContestResource.GUITAR_PLAYERS, id)
                        .exchange()
                        .expectStatus().isOk()
                        .expectBodyList(GuitarPlayerCreationDto.class)
                        .returnResult().getResponseBody();

        assertTrue(guitarPlayerCreationDtoList.size() > 0);
        assertEquals("me", guitarPlayerCreationDtoList.get(0).getName());
        assertEquals("me2", guitarPlayerCreationDtoList.get(1).getName());
        assertEquals("surname", guitarPlayerCreationDtoList.get(0).getSurname());
        assertEquals("surname2", guitarPlayerCreationDtoList.get(1).getSurname());
    }

    @Test
    void findGuitarPlayerBadRequest() {
        this.webTestClient
                .get().uri(GuitarContestResource.GUITAR_CONTESTS + GuitarContestResource.ID_ID, "")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void findGuitarPlayerNotFound() {
        Jury jury = new Jury(5, "Winner", "Local");
        String id = createGuitarContest(LocalDateTime.of(2023, 2, 28, 22, 30), "alas2", "Spain", jury).getId();
        this.webTestClient
                .get().uri(GuitarContestResource.GUITAR_CONTESTS + GuitarContestResource.ID_ID + "/no" + GuitarContestResource.GUITAR_PLAYERS, id).exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND);
    }


}
