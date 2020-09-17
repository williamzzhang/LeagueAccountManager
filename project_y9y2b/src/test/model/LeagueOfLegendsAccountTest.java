package model;

import exceptions.ImpossibleValue;
import exceptions.NegativeValue;
import exceptions.PurchaseFail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Unit tests for the LeagueOfLegendsAccount class
public class LeagueOfLegendsAccountTest {
    LeagueOfLegendsAccount acc1;
    LeagueOfLegendsAccount acc2;
    LeagueOfLegendsAccount acc3;
    Champion lucian;
    Champion akali;
    Champion syndra;

    @BeforeEach
    void runBefore() {
        acc1 = new LeagueOfLegendsAccount("Republic of Korea (KR)", "Faker",
                                          10000, 10000);
        acc2 = new LeagueOfLegendsAccount("North America (NA)", "Doublelift",
                                          3000, 1000);
        acc3 = new LeagueOfLegendsAccount("North America (NA)", "Oof",
                -10, -10);
        lucian = new Champion("Lucian", "The Purifier",
                "Marksman", 6, 4800, 880);
        akali = new Champion("Akali", "The Rogue Assasin",
                "Slayer", 7, 3150, 790);
        syndra = new Champion("Syndra", "The Dark Sovereign",
                "Mage", 8,4800, 880);
    }

    @Test
    void testConstructor() {
        assertEquals("Republic of Korea (KR)", acc1.getRegion());
        assertEquals("Doublelift", acc2.getInGameName());
        assertEquals(0, acc3.getBlueEssenceBalance());
        assertEquals(0, acc3.getRiotPointsBalance());

        acc3.setRegion("Republic of Korea (KR)");
        acc3.setInGameName("Foo");

        assertEquals("Republic of Korea (KR)", acc3.getRegion());
        assertEquals("Foo", acc3.getInGameName());
        assertEquals(10000, acc1.getBlueEssenceBalance());
        assertEquals(1000, acc2.getRiotPointsBalance());
        assertEquals(0, acc1.getCollectionSize());
        assertEquals(0, acc2.getCollectionSize());
    }

    @Test
    void testEarnInGame() throws NegativeValue {
        acc1 = new LeagueOfLegendsAccount("Republic of Korea (KR)", "Faker",
                10000, 10000);
        acc2 = new LeagueOfLegendsAccount("North America (NA)", "Doublelift",
                3000, 1000);
        acc1.earnInGame(2000);
        acc2.earnInGame(3000);

        assertEquals(12000, acc1.getBlueEssenceBalance());
        assertEquals(6000, acc2.getBlueEssenceBalance());
        assertEquals("New balance is "
                              + "9000"
                              + " Blue Essence",
                              acc2.earnInGame(3000));
    }

    @Test
    void testEarnNegativeValue() {
        acc2 = new LeagueOfLegendsAccount("North America (NA)", "Doublelift",
                                              0, 0);
        try {
            acc2.earnInGame(-2000);
            fail("Exception not thrown when it should have been");
        } catch (NegativeValue e) {
            System.out.println("Exception thrown correctly!");
        }

        try {
            acc2.earnInGame(2000);
            System.out.println("No exception thrown!");
        } catch (NegativeValue e) {
            fail("Exception thrown when it shouldn't have been");
        }
    }


    @Test
    void testPurchasePremium() throws ImpossibleValue {
        acc3 = new LeagueOfLegendsAccount("North America (NA)", "Oof",
                -10, -10);
        acc3.purchasePremium(5);
        acc3.purchasePremium(10);
        acc3.purchasePremium(20);
        acc3.purchasePremium(35);
        acc3.purchasePremium(50);
        acc3.purchasePremium(100);

        assertEquals(23635, acc3.getRiotPointsBalance());
    }

    @Test
    void testPurchaseImpossible() {
        acc2 = new LeagueOfLegendsAccount("North America (NA)", "Doublelift",
                0, 0);
        try {
            acc2.purchasePremium(-2000);
            fail("Exception not thrown when it should have been");
        } catch (ImpossibleValue e) {
            System.out.println("Exception thrown correctly!");
        }

        try {
            acc2.purchasePremium(10);
            System.out.println("No exception thrown!");
        } catch (ImpossibleValue e) {
            fail("Exception thrown when it shouldn't have been");
        }
    }

    @Test
    void testMakeBlueEssencePurchase() throws PurchaseFail {
        acc1 = new LeagueOfLegendsAccount("Republic of Korea (KR)", "Faker",
                100000, 10000);
        ArrayList<String> collectionOwned = new ArrayList<>();
        collectionOwned.add("Blitzcrank");
        collectionOwned.add("Akali");
        collectionOwned.add("Darius");
        collectionOwned.add("Syndra");
        collectionOwned.add("Lucian");
        collectionOwned.add("Sejuani");
        collectionOwned.add("Fiddlesticks");
        acc1.makeBlueEssencePurchase("Blitzcrank");
        acc1.makeBlueEssencePurchase("Akali");
        acc1.makeBlueEssencePurchase("Darius");
        acc1.makeBlueEssencePurchase("Syndra");
        acc1.makeBlueEssencePurchase("Lucian");
        acc1.makeBlueEssencePurchase("Sejuani");
        acc1.makeBlueEssencePurchase("Fiddlesticks");
        assertEquals(73150, acc1.getBlueEssenceBalance());
        assertEquals(collectionOwned, acc1.myCollection.getListOfChampionName(acc1.myCollection));
    }

    @Test
    void testMakeRiotPointsPurchase() throws PurchaseFail {
        acc1 = new LeagueOfLegendsAccount("Republic of Korea (KR)", "Faker",
                100000, 100000);
        ArrayList<String> collectionOwned = new ArrayList<>();
        collectionOwned.add("Blitzcrank");
        collectionOwned.add("Akali");
        collectionOwned.add("Darius");
        collectionOwned.add("Syndra");
        collectionOwned.add("Lucian");
        collectionOwned.add("Sejuani");
        collectionOwned.add("Fiddlesticks");
        acc1.makeRiotPointsPurchase("Blitzcrank");
        acc1.makeRiotPointsPurchase("Akali");
        acc1.makeRiotPointsPurchase("Darius");
        acc1.makeRiotPointsPurchase("Syndra");
        acc1.makeRiotPointsPurchase("Lucian");
        acc1.makeRiotPointsPurchase("Sejuani");
        acc1.makeRiotPointsPurchase("Fiddlesticks");
        assertEquals(94315, acc1.getRiotPointsBalance());
        assertEquals(collectionOwned, acc1.myCollection.getListOfChampionName(acc1.myCollection));
    }

    @Test
    void testBlueEssencePurchaseMessage() throws PurchaseFail {
        acc1 = new LeagueOfLegendsAccount("Republic of Korea (KR)", "Faker",
                100000, 10000);
        assertEquals("Excellent purchase " + "Faker" + "!, "
                        + "we await you on the the fields of justice Summoner.",
                acc1.makeBlueEssencePurchase("Blitzcrank"));
        assertEquals("Excellent purchase " + "Faker" + "!, "
                        + "we await you on the the fields of justice Summoner.",
                acc1.makeBlueEssencePurchase("Akali"));
        assertEquals("Excellent purchase " + "Faker" + "!, "
                        + "we await you on the the fields of justice Summoner.",
                acc1.makeBlueEssencePurchase("Darius"));
        assertEquals("Excellent purchase " + "Faker" + "!, "
                + "we await you on the the fields of justice Summoner.",
                acc1.makeBlueEssencePurchase("Syndra"));
        assertEquals("Excellent purchase " + "Faker" + "!, "
                        + "we await you on the the fields of justice Summoner.",
                acc1.makeBlueEssencePurchase("Lucian"));
        assertEquals("Excellent purchase " + "Faker" + "!, "
                        + "we await you on the the fields of justice Summoner.",
                acc1.makeBlueEssencePurchase("Sejuani"));
        assertEquals("Excellent purchase " + "Faker" + "!, "
                        + "we await you on the the fields of justice Summoner.",
                acc1.makeBlueEssencePurchase("Fiddlesticks"));
    }

    @Test
    void testBlueEssencePurchaseFailMessage() {
        acc2 = new LeagueOfLegendsAccount("North America (NA)", "Doublelift",
                2000, 0);
        try {
            acc2.makeBlueEssencePurchase("Blitzcrank");
            fail("Exception not thrown when it should have been");
        } catch (PurchaseFail e) {
            System.out.println("Exception thrown correctly!");
        }

        try {
            acc2.makeBlueEssencePurchase("Fiddlesticks");
            System.out.println("No exception thrown!");
        } catch (PurchaseFail e) {
            fail("Exception thrown when it shouldn't have been");
        }
    }

    @Test
    void testRiotPointsPurchaseMessage() throws PurchaseFail {
        assertEquals("Excellent purchase " + "Faker" + "!, "
                        + "we await you on the the fields of justice Summoner.",
                acc1.makeRiotPointsPurchase("Blitzcrank"));
        assertEquals("Excellent purchase " + "Faker" + "!, "
                        + "we await you on the the fields of justice Summoner.",
                acc1.makeRiotPointsPurchase("Akali"));
        assertEquals("Excellent purchase " + "Faker" + "!, "
                        + "we await you on the the fields of justice Summoner.",
                acc1.makeRiotPointsPurchase("Darius"));
        assertEquals("Excellent purchase " + "Faker" + "!, "
                        + "we await you on the the fields of justice Summoner.",
                acc1.makeRiotPointsPurchase("Syndra"));
        assertEquals("Excellent purchase " + "Faker" + "!, "
                        + "we await you on the the fields of justice Summoner.",
                acc1.makeRiotPointsPurchase("Lucian"));
        assertEquals("Excellent purchase " + "Faker" + "!, "
                        + "we await you on the the fields of justice Summoner.",
                acc1.makeRiotPointsPurchase("Sejuani"));
        assertEquals("Excellent purchase " + "Faker" + "!, "
                        + "we await you on the the fields of justice Summoner.",
                acc1.makeRiotPointsPurchase("Fiddlesticks"));
    }

    @Test
    void testRiotPointsPurchaseFailMessage() throws PurchaseFail {
    acc2 = new LeagueOfLegendsAccount("North America (NA)", "Doublelift",
            0, 0);
    try {
        acc2.makeRiotPointsPurchase("Blitzcrank");
        fail("Exception not thrown when it should have been");
    } catch (PurchaseFail e) {
        System.out.println("Exception thrown correctly!");
    }
}

    @Test
    void testInadequateBlueEssenceBalance() {
        acc2 = new LeagueOfLegendsAccount("North America (NA)", "Doublelift",
                0, 0);

        assertEquals("Only " + "3150 Blue Essence" + " to go!",
                      acc2.balanceBlueEssenceDifference("Blitzcrank"));
        assertEquals("Only " + "3150 Blue Essence" + " to go!",
                      acc2.balanceBlueEssenceDifference("Akali"));
        assertEquals("Only " + "4800 Blue Essence" + " to go!",
                      acc2.balanceBlueEssenceDifference("Darius"));
        assertEquals("Only " + "4800 Blue Essence" + " to go!",
                      acc2.balanceBlueEssenceDifference("Syndra"));
        assertEquals("Only " + "4800 Blue Essence" + " to go!",
                      acc2.balanceBlueEssenceDifference("Lucian"));
        assertEquals("Only " + "4800 Blue Essence" + " to go!",
                      acc2.balanceBlueEssenceDifference("Sejuani"));
        assertEquals("Only " + "1350 Blue Essence" + " to go!",
                      acc2.balanceBlueEssenceDifference("Fiddlesticks"));
    }

    @Test
    void testAdequateBlueEssenceBalance() {
        acc1 = new LeagueOfLegendsAccount("Republic of Korea (KR)", "Faker",
                10000, 10000);

        assertEquals("Enough to Purchase with Blue Essence!",
                acc1.balanceBlueEssenceDifference("Blitzcrank"));
        assertEquals("Enough to Purchase with Blue Essence!",
                acc1.balanceBlueEssenceDifference("Akali"));
        assertEquals("Enough to Purchase with Blue Essence!",
                acc1.balanceBlueEssenceDifference("Darius"));
        assertEquals("Enough to Purchase with Blue Essence!",
                acc1.balanceBlueEssenceDifference("Syndra"));
        assertEquals("Enough to Purchase with Blue Essence!",
                acc1.balanceBlueEssenceDifference("Lucian"));
        assertEquals("Enough to Purchase with Blue Essence!",
                acc1.balanceBlueEssenceDifference("Sejuani"));
        assertEquals("Enough to Purchase with Blue Essence!",
                acc1.balanceBlueEssenceDifference("Fiddlesticks"));
    }

    @Test
    void testInAdequateRiotPointsBalance() {
        acc2 = new LeagueOfLegendsAccount("North America (NA)", "Doublelift",
                0, 0);

        assertEquals("Only " + "790 Riot Points" + " to go!",
                     acc2.balanceRiotPointsDifference("Blitzcrank"));
        assertEquals("Only " + "790 Riot Points" + " to go!",
                     acc2.balanceRiotPointsDifference("Akali"));
        assertEquals("Only " + "880 Riot Points" + " to go!",
                     acc2.balanceRiotPointsDifference("Darius"));
        assertEquals("Only " + "880 Riot Points" + " to go!",
                     acc2.balanceRiotPointsDifference("Syndra"));
        assertEquals("Only " + "880 Riot Points" + " to go!",
                     acc2.balanceRiotPointsDifference("Lucian"));
        assertEquals("Only " + "880 Riot Points" + " to go!",
                     acc2.balanceRiotPointsDifference("Sejuani"));
        assertEquals("Only " + "585 Riot Points" + " to go!",
                     acc2.balanceRiotPointsDifference("Fiddlesticks"));
    }

    @Test
    void testAdequateRiotPointsBalance() {
        acc1 = new LeagueOfLegendsAccount("Republic of Korea (KR)", "Faker",
                10000, 100000);

        assertEquals("Enough to Purchase with Riot Points!",
                acc1.balanceRiotPointsDifference("Blitzcrank"));
        assertEquals("Enough to Purchase with Riot Points!",
                acc1.balanceRiotPointsDifference("Akali"));
        assertEquals("Enough to Purchase with Riot Points!",
                acc1.balanceRiotPointsDifference("Darius"));
        assertEquals("Enough to Purchase with Riot Points!",
                acc1.balanceRiotPointsDifference("Syndra"));
        assertEquals("Enough to Purchase with Riot Points!",
                acc1.balanceRiotPointsDifference("Lucian"));
        assertEquals("Enough to Purchase with Riot Points!",
                acc1.balanceRiotPointsDifference("Sejuani"));
        assertEquals("Enough to Purchase with Riot Points!",
                acc1.balanceRiotPointsDifference("Fiddlesticks"));
    }

    @Test
    void testToString() {
        acc2 = new LeagueOfLegendsAccount("North America (NA)", "Doublelift",
                3500, 800);
        assertEquals("[ region =" + "North America (NA)" + ", name = " + "Doublelift" + ", "
                                 + "blueEssenceBalance = " + 3500 + "BE" + ", "
                                 + "riotPointsBalance = " + 800 + "RP" + ", "
                                 + "0 Champions owned" + ", "
                                 + "Champions owned = []" + ", "
                                 + "0 Recommended Champions" + ", "
                                 + "Recommended Champions = []" + ", "
                                 + "0 Favourite Champions" + ", "
                                 + "Favourite Champions = []"
                                 +  "]", acc2.toString());
    }
}
