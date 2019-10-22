package es.upm.miw.apaw_contest.api_controllers;

import es.upm.miw.apaw_contest.ApiTestConfig;
import es.upm.miw.apaw_contest.dtos.GuitarPlayerBasicDto;
import es.upm.miw.apaw_contest.dtos.GuitarPlayerCreationDto;
import es.upm.miw.apaw_contest.dtos.GuitarPlayerPatchDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ApiTestConfig
public class GuitarPlayerResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testGetGuitarPlayer() {
        GuitarPlayerBasicDto fakeGuitarPlayerBasicDto = createGuitarPlayer("Robin");
        String guitarPlayerId = fakeGuitarPlayerBasicDto.getId();

        GuitarPlayerCreationDto guitarPlayer = this.webTestClient
                .get().uri(GuitarPlayerResource.GUITARPLAYERS + GuitarPlayerResource.ID_ID, guitarPlayerId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(GuitarPlayerCreationDto.class)
                .returnResult().getResponseBody();

        assertEquals(guitarPlayerId, guitarPlayer.getId());
    }

    @Test
    void testGetGuitarPlayerBadRequestException() {
        this.webTestClient
                .get().uri(GuitarPlayerResource.GUITARPLAYERS + GuitarPlayerResource.ID_ID, "")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void testGuitarPlayerNotFoundException() {
        String guitarPlayerId = "none";
        this.webTestClient
                .get().uri(GuitarPlayerResource.GUITARPLAYERS + "/bad-uri" + GuitarPlayerResource.ID_ID, guitarPlayerId)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void testCreate() {
        GuitarPlayerBasicDto guitarPlayerBasicDto = createGuitarPlayer("diego");
        assertEquals("diego", guitarPlayerBasicDto.getName());
    }

    GuitarPlayerBasicDto createGuitarPlayer(String name) {
        GuitarPlayerCreationDto guitarPlayerCreationDto = new GuitarPlayerCreationDto(name, "riofrio", false, 677393838);
        return this.webTestClient
                .post().uri(GuitarPlayerResource.GUITARPLAYERS)
                .body(BodyInserters.fromObject(guitarPlayerCreationDto))
                .exchange()
                .expectStatus().isOk()
                .expectBody(GuitarPlayerBasicDto.class)
                .returnResult().getResponseBody();
    }

    @Test
    void testCreateGuitarPlayerException() {
        GuitarPlayerCreationDto guitarPlayerCreationDto = new GuitarPlayerCreationDto("diego-2", "Vasconez", null, 666555666);
        this.webTestClient
                .post().uri(GuitarPlayerResource.GUITARPLAYERS)
                .body(BodyInserters.fromObject(guitarPlayerCreationDto))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }

    GuitarPlayerCreationDto getGuitarPlayerWithSurname(String id) {
        return this.webTestClient
                .get().uri(GuitarPlayerResource.GUITARPLAYERS + GuitarPlayerResource.ID_ID + GuitarPlayerResource.SURNAME, id)
                .exchange()
                .expectStatus().isOk()
                .expectBody(GuitarPlayerCreationDto.class)
                .returnResult().getResponseBody();
    }


    @Test
    void testPutGuitarPlayerSurname() {
        GuitarPlayerBasicDto guitarPlayerBasicDto = createGuitarPlayer("vinicio");
        GuitarPlayerCreationDto guitarPlayerToSet = new GuitarPlayerCreationDto();
        guitarPlayerToSet.setSurname("Vasconez");
        this.webTestClient
                .put().uri(GuitarPlayerResource.GUITARPLAYERS + GuitarPlayerResource.ID_ID + GuitarPlayerResource.SURNAME, guitarPlayerBasicDto.getId())
                .body(BodyInserters.fromObject(guitarPlayerToSet))
                .exchange()
                .expectStatus().isOk();
        guitarPlayerToSet = getGuitarPlayerWithSurname(guitarPlayerBasicDto.getId());
        assertEquals(guitarPlayerBasicDto.getId(), guitarPlayerToSet.getId());
        assertEquals("Vasconez", guitarPlayerToSet.getSurname());
    }

    @Test
    void testPutGuitarPlayerSurnameNotFound() {
        this.webTestClient
                .put().uri(GuitarPlayerResource.GUITARPLAYERS + GuitarPlayerResource.ID_ID + GuitarPlayerResource.SURNAME, "none")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void testGuitarPlayerPatch() {
        String id = createGuitarPlayer("Francisco").getId();
        this.webTestClient
                .patch().uri(GuitarPlayerResource.GUITARPLAYERS + GuitarPlayerResource.ID_ID, id)
                .body(BodyInserters.fromObject(new GuitarPlayerPatchDto("surname", "García")))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testGuitarPlayerPatchBadRequestException() {
        String id = createGuitarPlayer("Fake").getId();
        this.webTestClient
                .patch().uri(GuitarPlayerResource.GUITARPLAYERS + GuitarPlayerResource.ID_ID, id)
                .body(BodyInserters.fromObject(new GuitarPlayerPatchDto("name",null)))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void testGuitarPlayerPatchNotFoundException() {
        this.webTestClient
                .patch().uri(GuitarPlayerResource.GUITARPLAYERS + GuitarPlayerResource.ID_ID, "no")
                .body(BodyInserters.fromObject(new GuitarPlayerPatchDto("name", "newName")))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND);
    }
}
