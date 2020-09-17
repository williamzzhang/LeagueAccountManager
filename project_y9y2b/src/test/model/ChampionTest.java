package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Unit tests for the Champion class
public class ChampionTest {
    Champion blitzcrank;
    Champion darius;
    Champion syndra;
    Champion lucian;
    Champion akali;
    Champion sejuani;
    Champion fiddlesticks;

    @BeforeEach
    void runBefore() {
        blitzcrank = new Champion("Blitzcrank", "The Great Steam Golem",
                                  "Controller", 4,
                                  3150, 790);
        darius = new Champion("Darius", "The Hand of Noxus",
                              "Fighter", 2,
                              4800, 880);

        syndra = new Champion("Syndra", "The Dark Sovereign",
                              "Mage", 8,
                              4800, 880);

        lucian = new Champion("Lucian", "The Purifier",
                              "Marksman", 6,
                              4800, 880);

        akali = new Champion("Akali", "The Rogue Assasin",
                             "Slayer", 7,
                             3150, 790);

        sejuani = new Champion("Sejuani", "The Fury of The North",
                               "Tank", 4,
                               4800, 880);

        fiddlesticks = new Champion("Fiddlesticks", "The Harbinger of Doom",
                                    "Specialist", 9,
                                    1350, 585);
    }

    @Test
    void testConstructor() {
        assertEquals("Blitzcrank", blitzcrank.getName());
        assertEquals("The Hand of Noxus", darius.getTitle());
        assertEquals("Mage", syndra.getRole());
        assertEquals(6, lucian.getDifficulty());
        assertEquals(3150, akali.getBlueEssenceCost());
        assertEquals(880, sejuani.getRiotPointsCost());
        assertEquals("Fiddlesticks", fiddlesticks.getName());
        assertEquals("The Harbinger of Doom", fiddlesticks.getTitle());
        assertEquals("Specialist", fiddlesticks.getRole());
        assertEquals(9, fiddlesticks.getDifficulty());
        assertEquals(1350, fiddlesticks.getBlueEssenceCost());
        assertEquals(585, fiddlesticks.getRiotPointsCost());
    }



}
