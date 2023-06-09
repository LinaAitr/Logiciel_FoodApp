package app.appmeteo.model;

import app.foodapp.model.Authors;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorsTest {

    private Authors authors = new Authors(Arrays.asList("Martin Dupont", "Marie Martin", "François Cordonnier"));

    @Test
    public void testToString() {
        String expected = "Martin Dupont, Marie Martin, François Cordonnier";
        assertEquals(expected, authors.toString());
    }
}

