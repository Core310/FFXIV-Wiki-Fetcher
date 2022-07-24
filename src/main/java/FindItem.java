import me.xdrop.fuzzywuzzy.FuzzySearch;
import scrapper.StaticItemTypes;
import scrapper.items.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.UnexpectedException;
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
     * Multiple items will be stored in an arraylist with arguments of the Map.
     * Eg. (todo)
     * input: Lava tode
     * output:
     */
    public ArrayList<LinkedHashMap<String,String>> findAllClosestAsMap(String itemName){
        ArrayList<String> rawData = FindAllClosest(itemName);//Used to loop thru all values found.
        ArrayList<LinkedHashMap<String,String>> outputList = new ArrayList<>();//ArrayList that is outputted
        Item item;
        for(String curLine: rawData){
            String[] delimLine = curLine.split("\t",-1);//Should split the current line into whatever is the cur item
            String curItem = delimLine[0];//0 is index where ItemName is stored

            //(See below why this is if not switch/case) Loops through all possible item types and adds to lhm.
            if (StaticItemTypes.FOLK_LORE_FISHING.toString().equals(curItem)) {//For this massive if block, I can't use a switch as a "constant expression required" error.
                //When java 18 stable version comes out, then I think this can be switched over to a switch/case block
                item = new FolkLore_Fishing(delimLine);
                outputList.add(item.toLinkedHashmap());
            }
            else if (StaticItemTypes.FOLK_LORE_NODE.toString().equals(curItem)) {
                item = new FolkLore_Node(delimLine);
                outputList.add(item.toLinkedHashmap());
            }
            else if (StaticItemTypes.REGULAR_NODE.toString().equals(curItem)) {
                item = new Regular_Node(delimLine);
                outputList.add(item.toLinkedHashmap());
            }
            else if (
                    StaticItemTypes.UNSPOILED_NODE.toString().equals(curItem)
                    ||
                    StaticItemTypes.ARR_UNSPOILED_NODE.toString().equals(curItem)
            ) {
                item = new Unspoiled_Node(delimLine);
                outputList.add(item.toLinkedHashmap());
            }
            else try {
                    throw new UnexpectedException("Wrong static item type assigned");
                } catch (UnexpectedException e) {
                    throw new RuntimeException(e);
                }
        }
        return outputList;
    }

    /**
     * Loops through file finding the highest matching value and return all in array.
     * Called helper as returns the raw data values.
     * @param itemName The item that is being searched for.
     * @return All values which have the same ratio to ItemName.
     */
    protected ArrayList<String> FindAllClosest(String itemName) {
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
        LinkedHashSet<String> tmp = new LinkedHashSet<>(currentArray);
        currentArray.clear();
        currentArray.addAll(tmp);
        tmp.clear();

        sc.close();
        return currentArray;
    }

    /**
     * Returns one random value from the file matching ItemName.
     * @param itemName Item searching for
     * @return Random value fetched from the method findAllClosestAsMap
     */
    protected String findAnyMatching(String itemName){
        Random rand = new Random();
        return FindAllClosest(itemName).get(rand.nextInt(FindAllClosest(itemName).size()));
    }


}
