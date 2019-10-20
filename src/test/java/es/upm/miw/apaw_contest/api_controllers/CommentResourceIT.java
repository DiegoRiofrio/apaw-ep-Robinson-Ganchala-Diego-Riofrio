package es.upm.miw.apaw_contest.api_controllers;

import es.upm.miw.apaw_contest.ApiTestConfig;
import es.upm.miw.apaw_contest.dtos.CommentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ApiTestConfig
public class CommentResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testGetAllComments() {
        createComment(true, "Bien hecho!");
        createComment(false, "Mal hecho!");

        List<CommentDto> list = this.webTestClient
                .get().uri(CommentResource.COMMENTS)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(CommentDto.class)
                .returnResult().getResponseBody();
        assertTrue(list.size() > 0);
        assertEquals(list.get(0).getDescription(), "Bien hecho!");
        assertEquals(list.get(1).getDescription(), "Mal hecho!");
    }

    CommentDto createComment(Boolean positive, String description) {
        CommentDto commentDto = new CommentDto(positive, description);
        return this.webTestClient
                .post().uri(CommentResource.COMMENTS)
                .body(BodyInserters.fromObject(commentDto))
                .exchange()
                .expectStatus().isOk()
                .expectBody(CommentDto.class)
                .returnResult().getResponseBody();
    }
}
