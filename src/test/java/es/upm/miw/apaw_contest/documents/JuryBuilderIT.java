package es.upm.miw.apaw_contest.documents;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JuryBuilderIT {

    @Test
    void testFull() {
        Jury jury = new JuryBuilder(1, "Local").members(5).veredict("winner").type("Local").build();
        assertEquals("Local", jury.getType());
        assertEquals(new Integer(5), jury.getMembers());
        assertEquals("winner", jury.getVeredict());


    }

    @Test
    void testByDefault() {
        Jury jury = new JuryBuilder().byDefault().type("Local").build();
        assertEquals("veredict2", jury.getVeredict());
        assertEquals("Local", jury.getType());
    }
}
