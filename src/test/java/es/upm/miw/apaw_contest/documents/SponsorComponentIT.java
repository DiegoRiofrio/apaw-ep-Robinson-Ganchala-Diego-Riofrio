package es.upm.miw.apaw_contest.documents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SponsorComponentIT {

    private SponsorComponent root;

    private SponsorComponent sub11;

    private SponsorComponent leaf;

    @BeforeEach
    void ini() {
        SponsorComponent sub1;
        SponsorComponent sub12;
        this.root = new SponsorComposite("raiz", "LG");

        this.leaf = new SponsorLeaf(new Sponsor("Sony", 99999.0, "small"));
        this.root.add(leaf);
        sub1 = new SponsorComposite("sub1", "M&Ms");
        this.root.add(sub1);
        this.root.add(new SponsorLeaf(new Sponsor("DELL", 8989.7, "medium")));

        this.sub11 = new SponsorComposite("sub11", "Orange");
        sub1.add(sub11);
        sub1.add(new SponsorLeaf(new Sponsor("Vodafone", 750.45, "Large")));
        sub12 = new SponsorComposite("sub12", "Samsung");
        sub1.add(sub12);

        this.sub11.add(new SponsorLeaf(new Sponsor("IBM", 9090.12, "small")));
        this.sub11.add(new SponsorLeaf(new Sponsor("RED HAT", 9090.12, "small")));

    }

    @Test
    void testNameOfSponsorIfLeaf() {
        assertEquals("Sony", this.leaf.name());
    }

    @Test
    void testNameOfSponsorIfComposite() {
        assertEquals("LG", this.root.name());
        assertEquals("Orange", this.sub11.name());
    }

    @Test
    void testIdIfComposite() {
        assertEquals("sub11", this.sub11.id());
    }

    @Test
    void testIsComposite() {
        assertTrue(this.root.isComposite());
        assertTrue(this.sub11.isComposite());
        assertFalse(this.leaf.isComposite());
    }

    @Test
    void testAddLeaf() {
        assertThrows(UnsupportedOperationException.class, () -> this.leaf.add(new SponsorLeaf(new Sponsor("DELL", 8989.7, "medium"))));
    }

}
