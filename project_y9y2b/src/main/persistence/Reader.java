package persistence;

import model.LeagueOfLegendsAccount;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read account data from a file
public class Reader {
    public static final String DELIMITER = ",";

    // EFFECTS: returns a list of accounts parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<LeagueOfLegendsAccount> readAccounts(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of accounts parsed from list of strings
    // where each string contains data for one account
    private static List<LeagueOfLegendsAccount> parseContent(List<String> fileContent) {
        List<LeagueOfLegendsAccount> accounts = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            accounts.add(parseLeagueOfLegendsAccount(lineComponents));
        }

        return accounts;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has an arbitrary size >= 3 where element 0 represents
    // the region of the next account to be constructed, element 1 represents
    // the name of the account, element 2 represents
    // the Blue Essence Balance, element 3 represents
    // the Riot Points balance, element i->j-1 represents
    // the collection of owned champions, element j->k-1 represents
    // the collection of recommended champions, and element k->end represents
    // the collection of favourite champions
    // EFFECTS: returns a League Of Legends account constructed from components
    private static LeagueOfLegendsAccount parseLeagueOfLegendsAccount(List<String> components) {
        String region = components.get(0);
        String ingamename = components.get(1);
        int blueEssence = Integer.parseInt(components.get(2));
        int riotPoints = Integer.parseInt(components.get(3));
        LeagueOfLegendsAccount account = new LeagueOfLegendsAccount(region, ingamename, blueEssence, riotPoints);
        parseCollections(account, components);
        return account;
    }

    private static void parseCollections(LeagueOfLegendsAccount account, List<String> components) {
        int i = 4;
        int j = 5;
        int k = 6;
        while (!(components.get(i).equals("Owned"))) {
            account.myCollection.addChampionName(components.get(i));
            i++;
            j++;
            k++;
        }
        while (!(components.get(j).equals("Recommended"))) {
            account.myRecommended.addChampionName(components.get(j));
            j++;
            k++;
        }
        while (!(components.get(k).equals("Favourites"))) {
            account.myFavourites.addChampionName(components.get(k));
            k++;
        }
    }
}

