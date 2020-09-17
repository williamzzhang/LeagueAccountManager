package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Unit tests for the Collection class
public class CollectionTest {
    Collection collection1;
    Collection collection2;

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
                "Controller", 4, 3150, 790);
        darius = new Champion("Darius", "The Hand of Noxus",
                "Fighter", 2, 4800, 880);

        syndra = new Champion("Syndra", "The Dark Sovereign",
                "Mage", 8,4800, 880);

        lucian = new Champion("Lucian", "The Purifier",
                "Marksman", 6, 4800, 880);

        akali = new Champion("Akali", "The Rogue Assasin",
                "Slayer", 7, 3150, 790);

        sejuani = new Champion("Sejuani", "The Fury of The North",
                "Tank", 4, 4800, 880);

        fiddlesticks = new Champion("FiddleSticks", "The Harbinger of Doom",
                "Specialist", 9, 1350, 585);


        collection1 = new Collection("Favorites");
        collection2 = new Collection("Hard to Play");
    }

    @Test
    void testConstructor() {
        collection1.addChampionName("Lucian");
        collection1.addChampionName("Akali");
        collection2.addChampionName("Syndra");
        collection2.addChampionName("Fiddlesticks");

        ArrayList<String> cc1 = new ArrayList<String>();
        cc1.add(lucian.getName());
        cc1.add(akali.getName());


        assertEquals("Favorites", collection1.getCategoryName());
        assertEquals(2, collection2.length());
        assertEquals("[ Collection " + "Favorites" + " contains"
                        + collection1.getListOfChampionName(collection1)
                                 + " for a total of " + 2 + " champions ]",
                                         collection1.championCollectionToString(collection1));
        assertEquals(cc1, collection1.getListOfChampionName(collection1));
    }

}
