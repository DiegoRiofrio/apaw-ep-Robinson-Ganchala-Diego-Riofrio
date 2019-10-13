package es.upm.miw.apaw_contest.api_controllers;

import es.upm.miw.apaw_contest.ApiTestConfig;
import es.upm.miw.apaw_contest.dtos.GuitarPlayerBasicDto;
import es.upm.miw.apaw_contest.dtos.GuitarPlayerCreationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ApiTestConfig
public class GuitarPlayerResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        GuitarPlayerBasicDto guitarPlayerBasicDto = createGuitarPlayer("name");
        assertEquals("name", guitarPlayerBasicDto.getName());
    }

    GuitarPlayerBasicDto createGuitarPlayer(String name) {
        GuitarPlayerCreationDto guitarPlayerCreationDto = new GuitarPlayerCreationDto(name, "riofrio", null, 677393838);
        return this.webTestClient
                .post().uri(GuitarPlayerResource.GUITARPLAYERS)
                .body(BodyInserters.fromObject(guitarPlayerCreationDto))
                .exchange()
                .expectStatus().isOk()
                .expectBody(GuitarPlayerBasicDto.class)
                .returnResult().getResponseBody();
    }
}
