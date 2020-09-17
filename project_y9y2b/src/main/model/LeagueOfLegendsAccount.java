package model;

import exceptions.ImpossibleValue;
import exceptions.NegativeValue;
import exceptions.PurchaseFail;
import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;

// Represents a LeagueOfLegends account having a region, in-game name, in-game currency balance (in Blue Essence),
// premium currency balance (in Riot Points), a collection of all owned champions, a collection of all recommended
// champions, and a collection of favourite champions
public class LeagueOfLegendsAccount implements Saveable {
    public String region;                  // The region the account is created in
    public String ingamename;              // The in-game name of the account's owner
    public int blueEssenceBalance;         // The current free in-game currency (Blue Essence) balance of the account
    public int riotPointsBalance;          // The current premium in-game currency (Riot Points) balance of the account
    public Collection myCollection;        // The names of all current champions owned by the Account
    public Collection myRecommended;       // The names of all current champions recommended to the Account
    public Collection myFavourites;        // The names of all current champions that are favourites


    // REQUIRES: region must be one of BRAZIL (BR), EUROPE NORDIC & EAST (EUNE), EUROPE WEST (EUW),
    //                                 LATIN AMERICA NORTH (LAN), LATIN AMERICA SOUTH (LAS), NORTH AMERICA (NA),
    //                                 OCEANIA (OCE), RUSSIA (RU), TURKEY (TR), JAPAN (JP), GARENA SOUTH EAST ASIA (SEA)
    //                                 , REPUBLIC OF KOREA (KR), PEOPLE'S REPUBLIC OF CHINA (CN),
    //                                 OR PUBLIC BETA ENVIRONMENT (PBE)
    //                                 - inGameName has a non zero length
    //                                 - blueEssenceBalance >= 0
    //                                 - riotPointsBalance >= 0
    //                                 - collection must be empty with category name "Owned"
    // EFFECTS: region is set to accountRegion, in game name is set to inGameName, if initialBlueEssenceBalance is > 0
    //          then Blue Essence on account is set to initialBlueEssenceBalance otherwise Blue Essence is zero, if
    //          initialRiotPointsBalance is > 0 then Riot Points on account is set to initialRiotPointsBalance
    //          otherwise Riot Points is zero, collection is set to an empty Collection with default category "Owned"
    public LeagueOfLegendsAccount(String accountRegion, String inGameName, int initialBlueEssenceBalance,
                                  int initialRpBalance) {
        region = accountRegion;
        ingamename = inGameName;
        blueEssenceBalance = Math.max(initialBlueEssenceBalance, 0);
        riotPointsBalance = Math.max(initialRpBalance, 0);
        myCollection = new Collection("Owned");
        myRecommended = new Collection("Recommended");
        myFavourites = new Collection("Favourites");
    }


    public String getRegion() {
        return region;
    }

    public void setRegion(String newRegion) {
        this.region = newRegion;
    }

    public String getInGameName() {
        return ingamename;
    }

    public void setInGameName(String newName) {
        this.ingamename = newName;
    }

    public int getBlueEssenceBalance() {
        return blueEssenceBalance;
    }

    public int getRiotPointsBalance() {
        return riotPointsBalance;
    }

    public int getCollectionSize() {
        return myCollection.length();
    }

    public int getRecommendedSize() {
        return myRecommended.length();
    }

    public int getFavouritesSize() {
        return myFavourites.length();
    }

    // MODIFIES: this
    // EFFECTS: amount is added to beBalance and updated beBalance is returned
    public String earnInGame(int amount) throws NegativeValue {
        if (amount < 0) {
            throw new NegativeValue();
        } else {
            blueEssenceBalance = blueEssenceBalance + amount;
        }
        return "New balance is " + blueEssenceBalance + " Blue Essence";
    }

    // MODIFIES: this
    // EFFECTS: corresponding amount of rp is added to riotPointsBalance and updated riotPointsBalance is returned
    public String purchasePremium(int amount) throws ImpossibleValue {
        if (amount == 5) {
            riotPointsBalance = riotPointsBalance + 490;
        } else if (amount == 10) {
            riotPointsBalance = riotPointsBalance + 1020;
        } else if (amount == 20) {
            riotPointsBalance = riotPointsBalance + 2075;
        } else if (amount == 35) {
            riotPointsBalance = riotPointsBalance + 3700;
        } else if (amount == 50) {
            riotPointsBalance = riotPointsBalance + 5350;
        } else if (amount == 100) {
            riotPointsBalance = riotPointsBalance + 11000;
        } else {
            throw new ImpossibleValue();
        }
        return "New balance is " + riotPointsBalance + " Riot Points";
    }

    // MODIFIES: This
    // EFFECTS: If there is adequate Blue Essence on the LeagueOfLegendsAccount
    //          - subtract the required Blue Essence for the purchase of the champion
    //          - add the champion to the account's collection
    //          - return "Excellent purchase accountName!, we await you on the fields of justice Summoner."
    //          - otherwise, return "Not so quick accountName!, you don't have the means for such a purchase just yet."
    public String makeBlueEssencePurchase(String championName) throws PurchaseFail {
        if (((championName == "Blitzcrank") || (championName == "Akali")) && blueEssenceBalance >= 3150) {
            blueEssenceBalance = blueEssenceBalance - 3150;
            myCollection.addChampionName(championName);
            return "Excellent purchase " + ingamename + "!, we await you on the the fields of justice Summoner.";
        } else if (((championName == "Darius") || (championName == "Syndra")) && blueEssenceBalance >= 4800) {
            blueEssenceBalance = blueEssenceBalance - 4800;
            myCollection.addChampionName(championName);
            return "Excellent purchase " + ingamename + "!, we await you on the the fields of justice Summoner.";
        } else if (((championName == "Lucian") || (championName == "Sejuani")) && blueEssenceBalance >= 4800) {
            blueEssenceBalance = blueEssenceBalance - 4800;
            myCollection.addChampionName(championName);
            return "Excellent purchase " + ingamename + "!, we await you on the the fields of justice Summoner.";
        } else if (championName == "Fiddlesticks" && blueEssenceBalance >= 1350) {
            blueEssenceBalance = blueEssenceBalance - 1350;
            myCollection.addChampionName(championName);
            return "Excellent purchase " + ingamename + "!, we await you on the the fields of justice Summoner.";
        } else {
            throw new PurchaseFail();
        }
    }


    // MODIFIES: This
    // EFFECTS: If there is adequate Riot Points on the LeagueOfLegendsAccount
    //          - subtract the required Riot Points for the purchase of the champion
    //          - add the champion to the account's collection
    //          - return "Excellent purchase accountName!, we await you on the fields of justice Summoner."
    //          - otherwise, return "Not so quick accountName!, you don't have the means for such a purchase just yet."
    public String makeRiotPointsPurchase(String championName) throws PurchaseFail {
        if (((championName == "Blitzcrank") || (championName == "Akali")) && riotPointsBalance >= 790) {
            riotPointsBalance -= 790;
            myCollection.addChampionName(championName);
            return "Excellent purchase " + ingamename + "!, we await you on the the fields of justice Summoner.";
        } else if (((championName == "Darius") || (championName == "Syndra")) && riotPointsBalance >= 880) {
            riotPointsBalance -= 880;
            myCollection.addChampionName(championName);
            return "Excellent purchase " + ingamename + "!, we await you on the the fields of justice Summoner.";
        } else if (((championName == "Lucian") || (championName == "Sejuani")) && riotPointsBalance >= 880) {
            riotPointsBalance -= 880;
            myCollection.addChampionName(championName);
            return "Excellent purchase " + ingamename + "!, we await you on the the fields of justice Summoner.";
        } else if (championName == "Fiddlesticks" && riotPointsBalance >= 585) {
            riotPointsBalance -= 585;
            myCollection.addChampionName(championName);
            return "Excellent purchase " + ingamename + "!, we await you on the the fields of justice Summoner.";
        } else {
            throw new PurchaseFail();
        }
    }

    // REQUIRES: Champion name is one of the 7 possible names
    // EFFECTS: If there is adequate Blue Essence on the LeagueOfLegendsAccount
    //          - return "Enough to Purchase with Blue Essence!"
    //          - otherwise, return "Only (Champion Cost in Blue Essence - blueEssenceBalance) Blue Essence to go!"
    public String balanceBlueEssenceDifference(String championName) {
        if (((championName == "Blitzcrank") || (championName == "Akali")) && blueEssenceBalance < 3150) {
            return "Only " + (3150 - blueEssenceBalance) + " Blue Essence to go!";
        } else if (((championName == "Darius") || (championName == "Syndra")) && blueEssenceBalance < 4800) {
            return "Only " + (4800 - blueEssenceBalance) + " Blue Essence to go!";
        } else if (((championName == "Lucian") || (championName == "Sejuani")) && blueEssenceBalance < 4800) {
            return "Only " + (4800 - blueEssenceBalance) + " Blue Essence to go!";
        } else if (championName == "Fiddlesticks" && blueEssenceBalance < 1350) {
            return "Only " + (1350 - blueEssenceBalance) + " Blue Essence to go!";
        } else {
            return "Enough to Purchase with Blue Essence!";
        }
    }

    // REQUIRES: Champion name is one of the 7 possible names
    // EFFECTS: If there is adequate Riot Points on the LeagueOfLegendsAccount
    //          - return "Enough to Purchase with Riot Points!"
    //          - otherwise, return "Only (Champion Cost in Riot Points - riotPointsBalance) Riot Points to go!"
    public String balanceRiotPointsDifference(String championName) {
        if (((championName == "Blitzcrank") || (championName == "Akali")) && riotPointsBalance < 790) {
            return "Only " + (790 - riotPointsBalance) + " Riot Points to go!";
        } else if (((championName == "Darius") || (championName == "Syndra")) && riotPointsBalance < 880) {
            return "Only " + (880 - riotPointsBalance) + " Riot Points to go!";
        } else if (((championName == "Lucian") || (championName == "Sejuani")) && riotPointsBalance < 880) {
            return "Only " + (880 - riotPointsBalance) + " Riot Points to go!";
        } else if (championName == "Fiddlesticks" && riotPointsBalance < 585) {
            return "Only " + (585 - riotPointsBalance) + " Riot Points to go!";
        } else {
            return "Enough to Purchase with Riot Points!";
        }
    }

    // EFFECTS: returns a string representation of LeagueOfLegendAccount
    @Override
    public String toString() {
        return "[ region =" + region + ", name = " + ingamename + ", "
                + "blueEssenceBalance = " + blueEssenceBalance + "BE" + ", "
                + "riotPointsBalance = " + riotPointsBalance + "RP" + ", "
                + getCollectionSize() + " Champions owned" + ", "
                + "Champions owned = " + myCollection.getListOfChampionName(myCollection) + ", "
                + getRecommendedSize() + " Recommended Champions" + ", "
                + "Recommended Champions = " + myRecommended.getListOfChampionName(myRecommended) + ", "
                + getFavouritesSize() + " Favourite Champions" + ", "
                + "Favourite Champions = " + myFavourites.getListOfChampionName(myFavourites) + "]";
    }

    // EFFECTS: save accounts
    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(region + Reader.DELIMITER);
        printWriter.print(ingamename + Reader.DELIMITER);
        printWriter.print(blueEssenceBalance + Reader.DELIMITER);
        printWriter.print(riotPointsBalance + Reader.DELIMITER);
        for (String s : myCollection.getListOfChampionName(myCollection)) {
            printWriter.print(s + Reader.DELIMITER);
        }
        printWriter.print(myCollection.getCategoryName() + Reader.DELIMITER);
        for (String s : myRecommended.getListOfChampionName(myRecommended)) {
            printWriter.print(s + Reader.DELIMITER);
        }
        printWriter.print(myRecommended.getCategoryName() + Reader.DELIMITER);
        for (String s : myFavourites.getListOfChampionName(myFavourites)) {
            printWriter.print(s + Reader.DELIMITER);
        }
        printWriter.print(myFavourites.getCategoryName() + Reader.DELIMITER);
        printWriter.print("\n");
    }

}