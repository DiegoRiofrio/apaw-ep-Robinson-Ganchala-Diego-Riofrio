package es.upm.miw.apaw_contest.daos;

import es.upm.miw.apaw_contest.TestConfig;
import es.upm.miw.apaw_contest.documents.Sponsor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class SponsorDaoIT {

    @Autowired
    private SponsorDao sponsorDao;

    @Test
    void testCreate() {
        Sponsor sponsor = new Sponsor("iberia", 25000.50, "medium");
        this.sponsorDao.save(sponsor);
        Sponsor databaseSponsor = this.sponsorDao.findById(sponsor.getId()).orElseGet(Assertions::fail);
        assertEquals("iberia", databaseSponsor.getName());
        assertEquals(25000.50, databaseSponsor.getDonatedAmount());
    }
}
