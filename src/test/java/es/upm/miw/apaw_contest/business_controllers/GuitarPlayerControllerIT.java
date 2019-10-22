package es.upm.miw.apaw_contest.business_controllers;

import es.upm.miw.apaw_contest.TestConfig;
import es.upm.miw.apaw_contest.daos.GuitarPlayerDao;
import es.upm.miw.apaw_contest.documents.GuitarPlayer;
import es.upm.miw.apaw_contest.dtos.GuitarPlayerPatchDto;
import es.upm.miw.apaw_contest.exceptions.BadRequestException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestConfig
class GuitarPlayerControllerIT {

    @Autowired
    private GuitarPlayerDao guitarPlayerDao;

    @Autowired
    private GuitarPlayerBusinessController guitarPlayerBusinessController;

    private GuitarPlayer guitarPlayer;

    @BeforeEach
    void before() {
        this.guitarPlayer = new GuitarPlayer("Robin", "Ganchala", true, 9999999);
        this.guitarPlayerDao.save(guitarPlayer);
    }

    @Test
    void testPatchName() {
        GuitarPlayerPatchDto guitarPlayerPatchDto = new GuitarPlayerPatchDto("name", "Nombre");
        guitarPlayerBusinessController.patch(this.guitarPlayer.getId(), guitarPlayerPatchDto);
        assertEquals("Nombre", this.guitarPlayerDao.findById(guitarPlayer.getId()).get().getName());
    }

    @Test
    void testPatchPhone() {
        GuitarPlayerPatchDto guitarPlayerPatchDto = new GuitarPlayerPatchDto("phone", "9999999");
        guitarPlayerBusinessController.patch(this.guitarPlayer.getId(), guitarPlayerPatchDto);
        assertEquals("9999999", this.guitarPlayerDao.findById(guitarPlayer.getId()).get().getPhone().toString());
    }

    @Test
    void testPatchException() {
        GuitarPlayerPatchDto guitarPlayerPatchDto = new GuitarPlayerPatchDto("invalid", "Invalid");
        assertThrows(BadRequestException.class, () -> guitarPlayerBusinessController.patch(guitarPlayer.getId(), guitarPlayerPatchDto));
    }

    @AfterEach
    void after() {
        this.guitarPlayerDao.deleteById(this.guitarPlayer.getId());
    }

}
