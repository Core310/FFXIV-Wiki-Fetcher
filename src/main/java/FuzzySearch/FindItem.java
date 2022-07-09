package FuzzySearch;
import me.xdrop.fuzzywuzzy.FuzzySearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Takes the first item in a TSV file to compare it to in findAllClosestValues
 */
public class FindItem {
    private final File file;
    private final ArrayList<String> currentArray = new ArrayList<>();
    private int highestRatio =0;

    public FindItem(File file){
        this.file = file;
    }

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
        while (scanner.hasNext()){
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
