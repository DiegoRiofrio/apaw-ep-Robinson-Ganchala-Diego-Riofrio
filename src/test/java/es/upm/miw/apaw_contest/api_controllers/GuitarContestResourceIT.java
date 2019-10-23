package es.upm.miw.apaw_contest.api_controllers;


import es.upm.miw.apaw_contest.ApiTestConfig;
import es.upm.miw.apaw_contest.documents.GuitarContest;
import es.upm.miw.apaw_contest.dtos.GuitarContestBasicDto;
import es.upm.miw.apaw_contest.dtos.GuitarContestCreationDto;
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
        GuitarContestBasicDto guitarContestBasicDto = createGuitarContest(LocalDateTime.of(2020, 2, 28, 22, 30), "AlexanderPlatz", "Alemania");
        assertEquals("AlexanderPlatz", guitarContestBasicDto.getAddress());
    }

    GuitarContestBasicDto createGuitarContest(LocalDateTime date, String address, String country) {
        GuitarContestCreationDto guitarContestCreationDto = new GuitarContestCreationDto(date, address, country);
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
    void testCreateGuitarContestException() {
        GuitarContestCreationDto guitarContestCreationDto = new GuitarContestCreationDto(LocalDateTime.of(2030, 2, 28, 22, 30), "Branes2", "");
        this.webTestClient
                .post().uri(GuitarContestResource.GUITAR_CONTESTS)
                .body(BodyInserters.fromObject(guitarContestCreationDto))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void findAllGuitarPlayerByGuitarContestId() {
        String id = createGuitarContest(LocalDateTime.of(2022, 2, 28, 22, 30), "ANYWHERE", "ANYWHERE").getId();
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
        String id = createGuitarContest(LocalDateTime.of(2023, 2, 28, 22, 30), "alas2", "Spain").getId();
        this.webTestClient
                .get().uri(GuitarContestResource.GUITAR_CONTESTS + GuitarContestResource.ID_ID + "/no" + GuitarContestResource.GUITAR_PLAYERS, id)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND);
    }
}
