package es.upm.miw.apaw_contest.api_controllers;

import es.upm.miw.apaw_contest.ApiTestConfig;
import es.upm.miw.apaw_contest.dtos.SponsorBasicDto;
import es.upm.miw.apaw_contest.dtos.SponsorCreationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ApiTestConfig
class SponsorResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testGetSponsor(){
        SponsorBasicDto sponsorBasicDto = createSponsor("bankia",3000.00,"high");
        String sponsorId = sponsorBasicDto.getId();

        SponsorCreationDto sponsor = this.webTestClient
                .get().uri(SponsorResource.SPONSORS + SponsorResource.ID_ID, sponsorId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(SponsorCreationDto.class)
                .returnResult().getResponseBody();
        assertEquals(sponsorId,sponsor.getId());
    }

    @Test
    void testGetSponsorBadRequestExeption(){
        this.webTestClient
                .get().uri(SponsorResource.SPONSORS + SponsorResource.ID_ID,"")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }

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

    @Test
    void testPutSponsorTypeNotFound() {
        this.webTestClient
                .put().uri(SponsorResource.SPONSORS + SponsorResource.ID_ID + SponsorResource.TYPE, "none")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void testDeleteSponsor(){
        SponsorBasicDto sponsorBasicDto = createSponsor("Wanda", 10050.57, "medium");
        String sponsorId = sponsorBasicDto.getId();

        this.webTestClient
                .delete().uri(SponsorResource.SPONSORS + SponsorResource.ID_ID, sponsorId)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.OK);

        this.webTestClient
                .get().uri(SponsorResource.SPONSORS + SponsorResource.ID_ID, sponsorId)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void testDeleteSponsorNotFound() {
        this.webTestClient
                .delete().uri(SponsorResource.SPONSORS + "/targetId" + "MongoID")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void testDeleteSponsorBadRequest(){
        this.webTestClient
                .delete().uri(SponsorResource.SPONSORS + SponsorResource.ID_ID, "")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void testPatch(){
        String idIneco = this.createSponsor("Ineco",100,"high").getId();
        String idFujitsu = this.createSponsor("Fujitsu",50,"medium").getId();
        String idDms = this.createSponsor("Dms",10, "small").getId();
        SponsorBasicDto newIneco = new SponsorBasicDto();
        newIneco.setId(idIneco);
        newIneco.setName("Renfe");
        SponsorBasicDto newFujitsu = new SponsorBasicDto();
        newFujitsu.setId(idFujitsu);
        newFujitsu.setName("Manpower");
        SponsorBasicDto newDms = new SponsorBasicDto();
        newDms.setId(idDms);
        newDms.setName("Experis");
        List<SponsorBasicDto> sponsorBasicDtosList = new ArrayList<>();
        sponsorBasicDtosList.add(newIneco);
        sponsorBasicDtosList.add(newFujitsu);
        sponsorBasicDtosList.add(newDms);
        this.webTestClient
                .patch().uri(SponsorResource.SPONSORS)
                .body(BodyInserters.fromObject(sponsorBasicDtosList))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testPatchNotFoundException(){
        this.webTestClient
                .patch().uri(SponsorResource.SPONSORS)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }
    @Test
    void testPatchListExeption(){
        List<SponsorBasicDto> sponsorBasicDtos = new ArrayList<>();
        this.webTestClient
                .patch().uri(SponsorResource.SPONSORS)
                .body(BodyInserters.fromObject(sponsorBasicDtos))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
