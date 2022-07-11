package FuzzySearch;
import me.xdrop.fuzzywuzzy.FuzzySearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * After the main file has been loaded with data and formatted, this class is used to find a certain item.
 * It has a default constructor to assign a file.
 */
public class FindItem {
    private final File file;
    private final ArrayList<String> currentArray = new ArrayList<>();
    private int highestRatio =0;

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
            curItem = curLine.split("\t", -1)[0];//0 marks the position at which an item name should be at
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
}
