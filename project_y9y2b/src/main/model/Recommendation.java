//package model;
//
//import java.util.ArrayList;
//
// Represents a recommendation having: an integer representing the difficulty grouping of the recommendation, and a list
////                                     of champions within the recommendation
//public class Recommendation {
//    public int difficultyGrouping;               // The difficulty grouping of the recommendation
//    public ArrayList<String> recommendations;    // The list of names of champions that fall under the grouping
//
//    // REQUIRES: grouping has a non-zero length,
//    //           difficulty:
//    //           - 10>= difficulty >= 1
//    //           all champions in recommendations have:
//    //           - all the same difficulty
//    // EFFECTS: sets difficultyGrouping to the shared difficulty, adds list of champion names to Recommendation
//    public Recommendation(int commonDifficulty) {
//        difficultyGrouping = commonDifficulty;
//        recommendations = new ArrayList<String>();
//    }
//
//    public int getDifficultyGrouping() {
//        return difficultyGrouping;
//    }
//
//    public ArrayList<String> getRecommendations() {
//        return recommendations;
//    }
//
//    // REQUIRES: a champion name to add
//    // MODIFIES: this
//    // EFFECTS: add the champion name to the recommendations
//    public void addRecommendedChampion(String championName) {
//        recommendations.add(championName);
//    }
//
//    // EFFECTS: return the number of champions in the recommendations (the length of the list)
//    public int length() {
//        return recommendations.size();
//    }
//}




