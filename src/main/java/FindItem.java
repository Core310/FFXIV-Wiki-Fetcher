import me.xdrop.fuzzywuzzy.FuzzySearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

/**
 * FuzzySearch implementation to find an ITEM in the file.
 * After the main file has been loaded with data and formatted, this class is used to find a certain item.
 * It has a default constructor to assign a file.
 */
public class FindItem {
    private final File file;
    private final ArrayList<String> currentArray = new ArrayList<>();
    private int highestRatio =0;

    private final int itemCsvValue = 1;//1 marks the position at which an item name should be at. So in this case the item name is always at position 1, position 0 is the type.

    public FindItem(File file){
        this.file = file;
    }

    /**
     * @param ItemName The item that is being searched for.
     * @return All values which have the same ratio to ItemName
     */
    public ArrayList<String> findAllClosestValues(String ItemName) {
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String curLine;
        String curItem;
        int currentRatio;
        while (scanner.hasNextLine()){
            curLine = scanner.nextLine();
            if(Objects.equals(curLine, "")) continue; //base case, if the line is null
            curItem = curLine.split("\t", -1)[itemCsvValue];
            currentRatio = FuzzySearch.ratio(curItem,ItemName);
            if(currentRatio == highestRatio) {
                currentArray.add(curLine);
            }
            else if (currentRatio > highestRatio) {
                highestRatio = currentRatio;
                currentArray.clear();
                currentArray.add(curLine);
            }
        }
        return currentArray;
    }

    /**
     * Returns one random value from the file matching ItemName.
     * @param ItemName Item searching for
     * @return Random value fetched from the method findAllClosestValues
     */
    public String findAnyMatching(String ItemName){
        Random rand = new Random();
        return findAllClosestValues(ItemName).get(rand.nextInt(findAllClosestValues(ItemName).size()));
    }

}
