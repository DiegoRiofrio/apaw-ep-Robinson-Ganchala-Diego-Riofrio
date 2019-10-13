package es.upm.miw.apaw_contest.daos;

import es.upm.miw.apaw_contest.TestConfig;
import es.upm.miw.apaw_contest.documents.GuitarPlayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class GuitarPlayerDaoIT {

    @Autowired
    private GuitarPlayerDao guitarPlayerDao;

    @Test
    void testCreate() {
        GuitarPlayer guitarPlayer = new GuitarPlayer("diego", "riofrio", null, 655655655);
        this.guitarPlayerDao.save(guitarPlayer);
        GuitarPlayer databaseGuitarPlayer = this.guitarPlayerDao.findById(guitarPlayer.getId()).orElseGet(Assertions::fail);
        assertEquals("diego", databaseGuitarPlayer.getName());
    }
}
