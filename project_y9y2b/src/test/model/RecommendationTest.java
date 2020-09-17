//package model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//// Unit tests for the Recommendation class
//public class RecommendationTest {
//    Recommendation recommendation1;
//    Recommendation recommendation2;
//    Champion blitzcrank;
//    Champion darius;
//    Champion syndra;
//    Champion lucian;
//    Champion akali;
//    Champion sejuani;
//    Champion fiddlesticks;
//
//
//    @BeforeEach
//    void runBefore() {
//        blitzcrank = new Champion("Blitzcrank", "The Great Steam Golem",
//                "Controller", 4, 3150, 790);
//        darius = new Champion("Darius", "The Hand of Noxus",
//                "Fighter", 2, 4800, 880);
//
//        syndra = new Champion("Syndra", "The Dark Sovereign",
//                "Mage", 8, 4800, 880);
//
//        lucian = new Champion("Lucian", "The Purifier",
//                "Marksman", 6, 4800, 880);
//
//        akali = new Champion("Akali", "The Rogue Assasin",
//                "Slayer", 7, 3150, 790);
//
//        sejuani = new Champion("Sejuani", "The Fury of The North",
//                "Tank", 4, 4800, 880);
//
//        fiddlesticks = new Champion("FiddleSticks", "The Harbinger of Doom",
//                "Specialist", 9, 1350, 585);
//
//        recommendation1 = new Recommendation(4);
//        recommendation2 = new Recommendation(8);
//    }
//
//    @Test
//    void testConstructor() {
//        recommendation1.addRecommendedChampion("Blitzcrank");
//        recommendation1.addRecommendedChampion("Sejuani");
//        recommendation2.addRecommendedChampion("Syndra");
//
//        ArrayList<String> rr1 = new ArrayList<String>();
//        rr1.add(blitzcrank.getName());
//        rr1.add(sejuani.getName());
//
//
//        assertEquals(4, recommendation1.getDifficultyGrouping());
//        assertEquals(1, recommendation2.length());
//        assertEquals(rr1, recommendation1.getRecommendations());
//    }
//}
