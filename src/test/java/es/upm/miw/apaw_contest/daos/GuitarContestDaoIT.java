package es.upm.miw.apaw_contest.daos;


import es.upm.miw.apaw_contest.TestConfig;
import es.upm.miw.apaw_contest.documents.GuitarContest;
import es.upm.miw.apaw_contest.documents.Jury;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class GuitarContestDaoIT {

    @Autowired
    private GuitarContestDao guitarContestDao;

    @Test
    void testCreate() {
        Jury jury = new Jury(5, "Winner", "Local");
        GuitarContest guitarContest = new GuitarContest(LocalDateTime.of(2020, 2, 28, 20, 30), "puerta del sol, Madrid", "España", jury);
        this.guitarContestDao.save(guitarContest);
        GuitarContest databaseGuitarContest = this.guitarContestDao.findById(guitarContest.getId()).orElseGet(Assertions::fail);
        assertEquals("España", databaseGuitarContest.getCountry());
    }
}
