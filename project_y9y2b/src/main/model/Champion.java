package model;

// Represents a champion having a name, title, role, difficulty, and costs in both Blue Essence and Riot Points
public class Champion {
    public String name;       // Name of the Champion
    public String title;      // Title of the Champion
    public String role;       // Champion's role/class
    public int difficulty;    // Champion's difficulty out of 10 (10 being hardest 1 being easiest)
    public int blueEssenceCost;        // The Champion's cost in Blue Essence
    public int riotPointsCost;        // The Champion's cost in Riot Points

    // REQUIRES: championName has a non-zero length
    //           championTitle has a non-zero length
    //           championRole is one of: Controller, Fighter, Marksmen, Mage, Slayer, Tank, Specialist
    //           10>= difficulty >= 1
    //           blueEssenceCost >= 0
    //           riotPointsCost >= 0
    // EFFECTS: name of Champion is set to championName, title of Champion is set to championTitle,
    //          role of Champion is set to championRole, championDifficulty is set to difficulty
    //          blueEssenceCost is set to costInBlueEssence, riotPointsCost is set to costInRiotPoints
    public Champion(String championName, String championTitle, String championRole,
                    int championDifficulty, int costInBlueEssence, int costInRiotPoints) {
        name = championName;                        // The name of the champion
        title = championTitle;                      // The champion's title
        role = championRole;                        // The role the champion plays
        difficulty = championDifficulty;            // The difficulty of the champion
        blueEssenceCost = costInBlueEssence;        // Champion's cost in Blue Essence
        riotPointsCost = costInRiotPoints;          // Champion's cost in Riot Points
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getRole() {
        return role;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getBlueEssenceCost() {
        return blueEssenceCost;
    }

    public int getRiotPointsCost() {
        return riotPointsCost;
    }

}
