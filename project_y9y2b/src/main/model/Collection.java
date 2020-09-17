package model;

import java.util.ArrayList;

// Represents a collection that has a category, and a list of names of champions that have been added to the collection
public class Collection {
    public String category;                               // Name of the collection
    public ArrayList<String> championCollection;          // List of champions names added to the collection

    // REQUIRES: categoryName has a non-zero length
    // EFFECTS: constructs a collection with a specified category
    //          and a list of names of champions that fall under said category
    public Collection(String categoryName) {
        category = categoryName;
        championCollection = new ArrayList<String>();
    }

    public String getCategoryName() {
        return category;
    }

    // EFFECTS: return the number of champions in the championCollection (the length of the list)
    public int length() {
        return championCollection.size();
    }

    // EFFECTS: returns a list of strings representing he names of all the champions in the collection
    public ArrayList<String> getListOfChampionName(Collection collection) {
        ArrayList<String> listOfNames = new ArrayList<String>();

        return championCollection;
    }

    // EFFECTS: returns a string representation of a collection
    public String championCollectionToString(Collection collection) {

        return "[ Collection " + collection.getCategoryName()
                + " contains" + collection.getListOfChampionName(collection)
                + " for a total of " + collection.length()
                + " champions ]";
    }

    // REQUIRES: a champion name to add
    // MODIFIES: this
    // EFFECTS: add the champion name to the collection
    public void addChampionName(String championName) {
        championCollection.add(championName);
    }

}
