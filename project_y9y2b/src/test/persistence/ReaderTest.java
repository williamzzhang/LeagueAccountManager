package persistence;

import model.LeagueOfLegendsAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ReaderTest {

    @BeforeEach
    void runBefore() {
        new Reader();

    }

    @Test
    void testParseAccountsFile1main() {
        try {
            List<LeagueOfLegendsAccount> accounts =
                    Reader.readAccounts(new File("./data/testLeagueOfLegendsAccountFile1.txt"));
            LeagueOfLegendsAccount main = accounts.get(0);
            assertEquals("North America (NA)", main.getRegion());
            assertEquals("Doublelift", main.getInGameName());
            assertEquals(10000, main.getBlueEssenceBalance());
            assertEquals(1000, main.getRiotPointsBalance());

            ArrayList<String> collectionOwned = new ArrayList<>();
            ArrayList<String> collectionRecommended = new ArrayList<>();
            ArrayList<String> collectionFavourite = new ArrayList<>();
            collectionOwned.add("Lucian");
            collectionOwned.add("Syndra");
            collectionRecommended.add("Darius");
            collectionRecommended.add("Fiddlesticks");
            collectionFavourite.add("Lucian");
            collectionFavourite.add("Syndra");

            assertEquals(collectionOwned, main.myCollection.getListOfChampionName(main.myCollection));
            assertEquals(collectionRecommended, main.myRecommended.getListOfChampionName(main.myRecommended));
            assertEquals(collectionFavourite, main.myFavourites.getListOfChampionName(main.myFavourites));

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testParseAccountsFile1alt() {
        try {
            List<LeagueOfLegendsAccount> accounts =
                    Reader.readAccounts(new File("./data/testLeagueOfLegendsAccountFile1.txt"));
            LeagueOfLegendsAccount alt = accounts.get(1);
            assertEquals("Republic of Korea (KR)", alt.getRegion());
            assertEquals("Faker", alt.getInGameName());
            assertEquals(10000, alt.getBlueEssenceBalance());
            assertEquals(10000, alt.getRiotPointsBalance());

            ArrayList<String> collectionOwned = new ArrayList<>();
            ArrayList<String> collectionRecommended = new ArrayList<>();
            ArrayList<String> collectionFavourite = new ArrayList<>();
            collectionOwned.add("Akali");
            collectionOwned.add("Sejuani");
            collectionRecommended.add("Syndra");
            collectionRecommended.add("Blitzcrank");
            collectionFavourite.add("Akali");
            collectionFavourite.add("Sejuani");

            assertEquals(collectionOwned, alt.myCollection.getListOfChampionName(alt.myCollection));
            assertEquals(collectionRecommended, alt.myRecommended.getListOfChampionName(alt.myRecommended));
            assertEquals(collectionFavourite, alt.myFavourites.getListOfChampionName(alt.myFavourites));

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testParseAccountsFile2main() {
        try {
            List<LeagueOfLegendsAccount> accounts =
                    Reader.readAccounts(new File("./data/testLeagueOfLegendsAccountFile2.txt"));
            LeagueOfLegendsAccount main = accounts.get(0);
            assertEquals("Europe West (EUW)", main.getRegion());
            assertEquals("Caps", main.getInGameName());
            assertEquals(50000, main.getBlueEssenceBalance());
            assertEquals(10000, main.getRiotPointsBalance());

            ArrayList<String> collectionOwned = new ArrayList<>();
            ArrayList<String> collectionRecommended = new ArrayList<>();
            ArrayList<String> collectionFavourite = new ArrayList<>();
            collectionOwned.add("Blitzcrank");
            collectionOwned.add("Akali");
            collectionRecommended.add("Darius");
            collectionRecommended.add("Syndra");
            collectionFavourite.add("Lucian");
            collectionFavourite.add("Sejuani");
            collectionFavourite.add("Fiddlesticks");

            assertEquals(collectionOwned, main.myCollection.getListOfChampionName(main.myCollection));
            assertEquals(collectionRecommended, main.myRecommended.getListOfChampionName(main.myRecommended));
            assertEquals(collectionFavourite, main.myFavourites.getListOfChampionName(main.myFavourites));

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testParseAccountsFile2alt() {
        try {
            List<LeagueOfLegendsAccount> accounts =
                    Reader.readAccounts(new File("./data/testLeagueOfLegendsAccountFile2.txt"));
            LeagueOfLegendsAccount alt = accounts.get(1);
            assertEquals("People's Republic of China (CN)", alt.getRegion());
            assertEquals("Uzi", alt.getInGameName());
            assertEquals(30000, alt.getBlueEssenceBalance());
            assertEquals(30000, alt.getRiotPointsBalance());

            ArrayList<String> collectionOwned = new ArrayList<>();
            ArrayList<String> collectionRecommended = new ArrayList<>();
            ArrayList<String> collectionFavourite = new ArrayList<>();

            assertEquals(collectionOwned, alt.myCollection.getListOfChampionName(alt.myCollection));
            assertEquals(collectionRecommended, alt.myRecommended.getListOfChampionName(alt.myRecommended));
            assertEquals(collectionFavourite, alt.myFavourites.getListOfChampionName(alt.myFavourites));

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            Reader.readAccounts(new File("./path/does/not/exist/testAccount.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}