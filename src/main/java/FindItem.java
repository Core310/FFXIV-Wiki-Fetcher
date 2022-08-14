import me.xdrop.fuzzywuzzy.FuzzySearch;
import scrapper.readers.items.baseNode.StaticItemTypes;
import scrapper.readers.items.*;
import scrapper.readers.items.baseNode.Item;

import java.io.*;
import java.rmi.UnexpectedException;
import java.util.*;

@SuppressWarnings("ALL")
/**
 * FuzzySearch implementation to find an ITEM in the file.
 * <p>After the main file has been loaded with data and formatted, this class is used to find a certain item.</p>
 * It has a default constructor to assign a file.
 * @see StaticItemTypes
 * @see scrapper.readers.items
 */
public class FindItem {
    private final File file;
    private final ArrayList<String> currentArray = new ArrayList<>();
    private int numberOfDuplicateItems =-1;//Use the value -1 to set for
    // infinite number of duplicate item name. Using the values 0 or 1 will produce no duplicate items

    public FindItem(File file){
        this.file = file;

    }
    /**
     * The main helper method to findItem. It will output the most important info. For example:
     * <p>Item: Inkfish</p>
     * <p>Zone: The Sea of Clouds</p>
     * <p>Coordinates: (x29,y35)</p>
     * <p>Time: 2PM-4PM</p>
     * <p>FolkLore Tome: Abalathian</p>
     * If looking for raw or full data, look at findAllClosestAsMap and findAllClosest
     * <p>Recommended setup to output this method:</p>
     * <pre>
     * <code>
     * arr = findItem.essentialFindAllClosestAsMap(itemnam);
     * for (StringBuilder a : arr) {
     *    System.out.println(a);
     *         }
     *</code>
     * </pre>
     *
     * @see FindItem findAllClosestAsMap
     * @see FindItem findAllClosest
     * @param itemName item to find
     * @return Neatly outputted items
     */
    public ArrayList<StringBuilder > essentialFindAllClosestAsMap(String itemName){
        ArrayList<StringBuilder> rtrnArray = new ArrayList<>();//Return value
        for(LinkedHashMap<String,String> lhm: findAllClosestAsMap(itemName)){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Item: ").append(lhm.get("Item")).append("\n");
            stringBuilder.append("Zone: ").append(lhm.get("Zone")).append("\n");
            stringBuilder.append("Coordinates: ").append(lhm.get("Coordinates")).append("\n");

            if(lhm.get("Extra Information") != null & !Objects.equals(lhm.get("Extra Information"), ""))
                //todo make a small class that appends \n to the end of each call?
                // It would contain 2 args, first item type ("Item"), and second actual (lhm.get("Item").
                // After done then just append \n
                stringBuilder.append("Extra Information: ").append(lhm.get("Extra Information")).append("\n");
            if(lhm.get("Bait Used") != null)
                stringBuilder.append("Bait Used: ").append(lhm.get("Bait Used")).append("\n");
            if(lhm.get("Time") != null)
                stringBuilder.append("Time: ").append(lhm.get("Time")).append("\n");
            if(lhm.get("FolkLore Tome") != null)
                stringBuilder.append("FolkLore Tome: ").append(lhm.get("FolkLore Tome")).append("\n");
            rtrnArray.add(stringBuilder);
        }
        return rtrnArray;
    }

    /**
     * Outputs each case neatly with ALL values, and descriptors for each item argument. (eg. this is the folk lore tome)
     * <p>Multiple items will be stored in an arraylist with arguments of the Map.</p>
     * <p>E.g. FindItem.findAllClosestAsMap(input);</p>
     * <p>input: Lava toad</p>
     * output:[{Item=Lava Toad, Zone=Southern Thanalan, Coordinates=(x13,y31), Extra Information=, Level=50}]
     */
    public ArrayList<LinkedHashMap<String,String>> findAllClosestAsMap(String itemName){
        ArrayList<String> rawData = findAllClosest(itemName);//Used to loop through all values found.
        ArrayList<LinkedHashMap<String,String>> outputList = new ArrayList<>();//ArrayList that is outputted
        Item item;
        for(String curLine: rawData){
            String[] delimLine = curLine.split("\t",-1);//Should split the current line into whatever is the cur item
            String curItem = delimLine[0];//0 is index where ItemName is stored

            //(See below why this is if not switch/case) Loops through all possible item types and adds to lhm.
            if (StaticItemTypes.FOLK_LORE_FISH_NODE.toString().equals(curItem)) {//For this massive if block, I can't use a switch as a "constant expression required" error.
                //When java 18 stable version comes out, then I think this can be switched over to a switch/case block
                item = new FolkLore_FISH_NODE(delimLine);
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
            else if (StaticItemTypes.FISH_NODE.toString().equals(curItem)) {
                item = new Fish_Node(delimLine);
                outputList.add(item.toLinkedHashmap());
            } else if (StaticItemTypes.FISH_BIG_NODE.toString().equals(curItem)) {
                item = new Fish_Big_Node(delimLine);
                outputList.add(item.toLinkedHashmap());
            } else if (StaticItemTypes.FISH_COLLECTABLES_NODE.toString().equals(curItem)) {
                item = new Fish_Collectable_Node(delimLine);
                outputList.add(item.toLinkedHashmap());
            } else try {
                    throw new UnexpectedException("Wrong static item type assigned");
                } catch (UnexpectedException e) {
                    throw new RuntimeException(e);
                }
        }
        return outputList;
    }

    /**
     * Loops through file finding the highest matching value and return all in array.
     * <p> Called helper as returns the raw data values. <p> Acts as parent class for other 'find' methods.
     * @param itemName The item that is being searched for.
     * @return All values which have the same ratio to ItemName.
     */
    protected ArrayList<String> findAllClosest(String itemName) {
        BufferedReader br;
        try {
            br =  new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        int highestRatio =0;
        String curLine;
        String curItem;
        int currentRatio;
        while (true){
            try {
                if (((curLine = br.readLine()) == null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }//Loop through file

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
        //todo after removing duplicate, search for duplicate item name. If same, which length is longer for array?
        // psC: Loop thru, same item name? -> Hv bool method inside -> if True, means 1 arr len longer than other, replace. False? -> continue

        try {
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        removeDuplicate();

        return currentArray;
    }

    /**
     * Helper method for findAllClosest
     *<p>Creates a hashmap w/ key = duplicate & value = no. times found in currentArray</p>
     * @see FindItem setNumberOfDuplicateItems();
     * @see FindItem NumberOfDuplicateItems
     */
    private void removeDuplicate(){
        if(currentArray.size() ==1 || numberOfDuplicateItems == -1)
            return;//base case
        HashMap<String,Integer> hmap = new HashMap<>();
        String curItem;
        for(int i =0;i<currentArray.size();i++){
            curItem = currentArray.get(i).split("\t",-1)[1];// should grab  the item name (i hope)

            if(!hmap.containsKey(curItem)){
                hmap.put(curItem,1);
            }
            else if (hmap.containsKey(curItem)) {
                if(hmap.get(curItem) >= numberOfDuplicateItems){
                    currentArray.remove(i);
                    i--;
                    continue;
                }
                hmap.put(curItem,hmap.get(curItem)+1);
            }
        }
    }


    /**
     * Used for testing
     * Returns one random value from the file matching ItemName.
     * @param itemName Item searching for
     * @return Random value fetched from the method findAllClosestAsMap
     */
    protected String findAnyMatching(String itemName){
        Random rand = new Random();
        return findAllClosest(itemName).get(rand.nextInt(findAllClosest(itemName).size()));
    }

    public void setNumberOfDuplicateItems(int numberOfDuplicateItems) {
        this.numberOfDuplicateItems = numberOfDuplicateItems;
    }
}