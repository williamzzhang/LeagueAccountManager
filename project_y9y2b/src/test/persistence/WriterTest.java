package persistence;

import model.LeagueOfLegendsAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class WriterTest {
    private static final String TEST_FILE = "./data/testAccounts.txt";
    private Writer testWriter;
    private LeagueOfLegendsAccount main;
    private LeagueOfLegendsAccount alt;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        main = new LeagueOfLegendsAccount("North America (NA)", "Doublelift",
                                          10000, 1000);
        alt = new LeagueOfLegendsAccount("Europe West (EUW)", "Caps",
                                         50000, 10000);
        main.myCollection.addChampionName("Blitzcrank");
        main.myCollection.addChampionName("Akali");
        main.myRecommended.addChampionName("Darius");
        main.myRecommended.addChampionName("Syndra");
        main.myFavourites.addChampionName("Lucian");
        main.myFavourites.addChampionName("Sejuani");
        main.myFavourites.addChampionName("Fiddlesticks");
    }

    @Test
    void testWriteAccountMain() {
        // save main and alt accounts to file
        testWriter.write(main);
        testWriter.write(alt);
        testWriter.close();

        ArrayList<String> collectionOwnedMain = new ArrayList<>();
        ArrayList<String> collectionRecommendedMain = new ArrayList<>();
        ArrayList<String> collectionFavouriteMain = new ArrayList<>();
        collectionOwnedMain.add("Blitzcrank");
        collectionOwnedMain.add("Akali");
        collectionRecommendedMain.add("Darius");
        collectionRecommendedMain.add("Syndra");
        collectionFavouriteMain.add("Lucian");
        collectionFavouriteMain.add("Sejuani");
        collectionFavouriteMain.add("Fiddlesticks");

        ArrayList<String> collectionOwnedAlt = new ArrayList<>();
        ArrayList<String> collectionRecommendedAlt = new ArrayList<>();
        ArrayList<String> collectionFavouriteAlt = new ArrayList<>();

        // now read them back in and verify that the accounts have the expected values
        try {
            List<LeagueOfLegendsAccount> accounts = Reader.readAccounts(new File(TEST_FILE));
            LeagueOfLegendsAccount main = accounts.get(0);
            assertEquals("North America (NA)", main.getRegion());
            assertEquals("Doublelift", main.getInGameName());
            assertEquals(10000, main.getBlueEssenceBalance());
            assertEquals(1000, main.getRiotPointsBalance());
            assertEquals(collectionOwnedMain, main.myCollection.getListOfChampionName(main.myCollection));
            assertEquals(collectionRecommendedMain, main.myRecommended.getListOfChampionName(main.myRecommended));
            assertEquals(collectionFavouriteMain, main.myFavourites.getListOfChampionName(main.myFavourites));

            LeagueOfLegendsAccount alt = accounts.get(1);
            assertEquals("Europe West (EUW)", alt.getRegion());
            assertEquals("Caps", alt.getInGameName());
            assertEquals(50000, alt.getBlueEssenceBalance());
            assertEquals(10000, alt.getRiotPointsBalance());
            assertEquals(collectionOwnedAlt, alt.myCollection.getListOfChampionName(alt.myCollection));
            assertEquals(collectionRecommendedAlt, alt.myRecommended.getListOfChampionName(alt.myRecommended));
            assertEquals(collectionFavouriteAlt, alt.myFavourites.getListOfChampionName(alt.myFavourites));

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
