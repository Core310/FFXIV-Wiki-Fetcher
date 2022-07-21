import me.xdrop.fuzzywuzzy.FuzzySearch;
import scrapper.items.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


/**
 * FuzzySearch implementation to find an ITEM in the file.
 * After the main file has been loaded with data and formatted, this class is used to find a certain item.
 * It has a default constructor to assign a file.
 */
public class FindItem {
    private final File file;
    private final ArrayList<String> currentArray = new ArrayList<>();
    public FindItem(File file){
        this.file = file;
    }

    /**
     * Outputs each case neatly, with descriptors for each item argument. (eg. this is the folk lore tome)
     * Eg. (todo)
     * input: Lava tode
     * output:
     */
    public String findAllClosestValues(String itemName){
        ArrayList<ArrayList<String>> nestedFormattedReturn = new ArrayList<>();
        ArrayList<String> arrayList = HelperFindAllClosestValues(itemName);
        Item item = null;
        for(String curLine: arrayList){
            String[] delimLine = curLine.split("\t",-1);//Should split the current line into whatever is the cur item
            String curItem = delimLine[0];//0 is index where ItemName is stored
            switch (curItem){
                case "FolkLoreFishing":
                    item = new FolkLore_Fishing(delimLine[1],
                            delimLine[2],
                            delimLine[3],
                            delimLine[4],
                            delimLine[5],
                            delimLine[6]);
                // TODO: 7/20/2022 Make method that auto loads each argument (how would I do this)
            }
        }
        assert item != null;
        return item.toString();
    }

    /**
     * Loops through file finding the highest matching value and return all in array.
     * Called helper as returns the raw data values.
     * @param itemName The item that is being searched for.
     * @return All values which have the same ratio to ItemName.
     */
    protected ArrayList<String> HelperFindAllClosestValues(String itemName) {
        Scanner sc;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        int highestRatio =0;
        String curLine;
        String curItem;
        int currentRatio;
        while (sc.hasNextLine()){//Loop through file
            curLine = sc.nextLine();
            curItem = curLine.split("\t", -1)[1];//1 marks the position at which an item name should be at. So in this case the item name is always at position 1, position 0 is the type.
            currentRatio = FuzzySearch.ratio(curItem,itemName);
            if(curLine.equals("REGULAR_NODE\t Level 75 Miner Quest\tIl Mheg\t(x8,y20)\t\t75\tMineral Deposit")){}//Weird typo edge case. If more appear make an array and loop through to skip them.

            else if(currentRatio == highestRatio) {
                currentArray.add(curLine);
            }
            else if (currentRatio > highestRatio) {
                highestRatio = currentRatio;
                currentArray.clear();
                currentArray.add(curLine);
            }
        }//end of while
        //This code below removes any duplicates
        LinkedHashSet<String> tmp = new LinkedHashSet(currentArray);
        currentArray.clear();
        currentArray.addAll(tmp);
        tmp.clear();

        sc.close();
        return currentArray;
    }

    /**
     * Returns one random value from the file matching ItemName.
     * @param itemName Item searching for
     * @return Random value fetched from the method findAllClosestValues
     */
    protected String findAnyMatching(String itemName){
        Random rand = new Random();
        return HelperFindAllClosestValues(itemName).get(rand.nextInt(HelperFindAllClosestValues(itemName).size()));
    }


}
