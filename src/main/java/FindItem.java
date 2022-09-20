import me.xdrop.fuzzywuzzy.FuzzySearch;
import scrapper.readers.items.*;
import scrapper.readers.items.baseNode.Item;
import scrapper.readers.items.baseNode.StaticItemTypes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * FuzzySearch implementation to find an ITEM in the file.
 * <p>After the main file has been loaded with data and formatted, this class is used to find a certain item.</p>
 * It has a default constructor to assign a file.
 * @see StaticItemTypes
 * @see scrapper.readers.items
 */
public class FindItem {
    /**
     * Updated in {@link #findAllClosest(String)}
     * <br>Return output of findItem()
     * <br> Holds all items (in raw data format).
     * <br> Each string container holds the raw data for ONE item.
     */
    private final ArrayList<String> currentArray = new ArrayList<>();
    /**
     * Default value of -1 for infinite number of duplicate.
     * <br>Shouldn't break if the value is below -1 but just in case.
     * <br>Using 0 OR 1 will produce no duplicate items.
     */
    private int numberOfDuplicateItems =-1;
    /**
     * Child of {@link #findAllClosestAsMap(String)} which is also a child of {@link #findAllClosest(String)}
     * The main caller method for findItem. It will output the most important info. For example:
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
     * @param itemName item to find
     * @return Neatly outputted items
     */
    public ArrayList<StringBuilder> essentialFindAllClosestAsMap(String itemName){
        ArrayList<StringBuilder> rtrnArray = new ArrayList<>();//Return value
        for(LinkedHashMap<String,String> lhm: findAllClosestAsMap(itemName)){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Item: ").append(lhm.get("Item")).append("\n");
            stringBuilder.append("Zone: ").append(lhm.get("Zone")).append("\n");
            stringBuilder.append("Coordinates: ").append(lhm.get("Coordinates")).append("\n");

            if(lhm.get("Extra Information") != null & !Objects.equals(lhm.get("Extra Information"), ""))
                //todo refactor if statements and then appending part into a small method.
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
     * @return LinkedHashMap Item descriptor, Item data
     */
    public ArrayList<LinkedHashMap<String,String>> findAllClosestAsMap(String itemName){
        ArrayList<String> rawData = findAllClosest(itemName);//Used to loop through all values found.
        ArrayList<LinkedHashMap<String,String>> outputList = new ArrayList<>();//ArrayList that is outputted in the format: ItemDescriptor,ActualItem
        Item item;
        for(String curLine: rawData){
            String[] delimLine = curLine.split("\t",-1);//Should split the current line into whatever is the cur item
            String curItem = delimLine[0];//0 is index where ItemName is stored

            //(See below why this is if not switch/case) Loops through all possible item types and adds to lhm.
            if (StaticItemTypes.FOLK_LORE_FISH_NODE.toString().equals(curItem)) {
                //For this massive if block, I can't use a switch as a "constant expression required" error.
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
            } else
                throw new RuntimeException("Wrong static item type assigned");
        }
        /*
        Why is mergeDuplicate only available for maps? Easier to merge items since each values
        Has a key assigned to it.
         */
        return mergeDuplicate(outputList);
    }
    /**
     * Parent method: {@link #findAllClosestAsMap(String)}
     * <br>Helper method: {@link #mergeDuplicateHelper(int, String, ArrayList)}
     * <br> This method is a trigger for the actual merging method to take place.
     * <br>Merges any duplicate item with a time complexity of O(n^2) using {@link #mergeDuplicateHelper(int, String, ArrayList)}.
     * <br> Searches each item by their item and zone.
     * <br> Basically, if two or more items share the same name and zone, this method will merge both items keeping one of their teleport values (at random).
     * @param findAllClosestAsMapOutPut Only accepts the output ArrayList of findAllClosestAsMapOut.
     */
    private ArrayList<LinkedHashMap<String,String>> mergeDuplicate(ArrayList<LinkedHashMap<String,String>> findAllClosestAsMapOutPut) {
        if (currentArray.size() == 1)//if there is only one item then don't do anything
            return findAllClosestAsMapOutPut;//base case
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i =0;i< findAllClosestAsMapOutPut.size();i++) {
            String item = findAllClosestAsMapOutPut.get(i).get("Item");
            String tp =  findAllClosestAsMapOutPut.get(i).get("Zone");
            String itemAndTp = item + "\t" + tp;
            int counter =0;
            if(item == null || tp == null)
                throw new RuntimeException("These value should never be null.");
            /*
            Works by looping through an internal arrayList. If a duplicate value that contains the item and and zone value are found, proceeds to merge.
            Else add the current value to the internal arrayList.
             */
            for(String str: arrayList)
                if(str.contains(item) && str.contains(tp)){
                    mergeDuplicateHelper(i, itemAndTp, findAllClosestAsMapOutPut);//Performs the actual merging
                    counter++;
                    break;
                }
            if(counter ==0)
                arrayList.add(itemAndTp);
        }
        return findAllClosestAsMapOutPut;
    }

    /**
     * Helper method for {@link #mergeDuplicate(ArrayList)}. Has a lot of linked values to the method above, and runs inside a for loop.
     * <br> This method is to keep code clean.
     * <br> Does the actual merging of values
     * @param i for loop iterator in main method.
     * @param itemAndTp item and teleport value in one string seperated by `\t`
     * @param findAllClosestAsMapOutPut Only accepts from the main {@link #mergeDuplicate(ArrayList)} method. (May work with findAllClosestAsMap)
     * @return
     */
    private ArrayList<LinkedHashMap<String,String>> mergeDuplicateHelper(int i, String itemAndTp, ArrayList<LinkedHashMap<String,String>> findAllClosestAsMapOutPut){//FIXME 18/9/2022 Returns both items, as a baseItem without their additional stats for somee reason
        //Already contains key?
        LinkedHashMap<String,String> itemToMerge = findAllClosestAsMapOutPut.get(i);//Item at current index that will be removed and merged into the item with the previous index.
        findAllClosestAsMapOutPut.remove(i);i--; //Firstly delete the duplicate key and store. Lower the current index since we removed an element
        int baseItemIndex = -1;
        for(int baseItemFinder =0;baseItemFinder < findAllClosestAsMapOutPut.size();baseItemFinder++){//Finds duplicate item
            if(findAllClosestAsMapOutPut.get(baseItemFinder).get("Item").contains(itemAndTp.split("\t",-1)[0]) &&
                    findAllClosestAsMapOutPut.get(baseItemFinder).get("Zone").contains(itemAndTp.split("\t",-1)[1])
            ){
                baseItemIndex = baseItemFinder;
                break;
            }
        }//Loops through all items
        if(baseItemIndex == -1)//Same value? Then just delete one of them and keep another.
            return findAllClosestAsMapOutPut;

        LinkedHashMap<String,String> mergeBase = findAllClosestAsMapOutPut.get(baseItemIndex);//Item that will receive new values. Is the first item come across, not the current index
        String[] mergeBaseKeySet = mergeBase.keySet().toArray(new String[0]);
        String[] itemToMergeKeySet = itemToMerge.keySet().toArray(new String[0]);
        for(int currentItemHeader = 4;currentItemHeader < findAllClosestAsMapOutPut.get(i).size() ;currentItemHeader++){//At index 3 is the cords value. Cords value differs a ton so im not using it.
            //System.out.println(mergeBaseKeySet[currentItemHeader] + "\t" + itemToMergeKeySet[currentItemHeader]);//DELETEME
            if(!mergeBaseKeySet[currentItemHeader].contains(itemToMergeKeySet[currentItemHeader]))//FIXME 19/9/2022 This runs when `crayon fish` is called but not for shark tuna
            {
                throw new RuntimeException("I ran");//DELETEME
                //mergeBase.put(itemToMergeKeySet[currentItemHeader],itemToMerge.get(itemToMergeKeySet[currentItemHeader]));
            }
        }//Loops through itemToMerge to see what values can be merged into the base value.
        findAllClosestAsMapOutPut.remove(baseItemIndex);
        findAllClosestAsMapOutPut.add(mergeBase);
        return findAllClosestAsMapOutPut;
    }

    /**
     * Loops through file finding the highest matching value and return all in array.
     * <p> Called helper as returns the raw data values. <p> Acts as parent class for other 'find' methods.
     * @param itemName The item that is being searched for.
     * @return All values which have the same ratio to ItemName.
     */
    protected ArrayList<String> findAllClosest(String itemName) {
        BufferedReader br;
        InputStream inputStream = getClass().getResourceAsStream("/XIVGather.TSV");//Grabs file from resource
        assert inputStream != null;
        br =  new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));//Puts into stream

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
            if(curLine.equals("REGULAR_NODE\t Level 75 Miner Quest\tIl Mheg\tx8,y20\t\t75\tMineral Deposit"))
            {continue;}//Weird typo edge case. If more appear make an array and loop through to skip them.
            else if(currentRatio == highestRatio) {
                currentArray.add(curLine);
            }
            else if (currentRatio > highestRatio) {
                highestRatio = currentRatio;
                currentArray.clear();
                currentArray.add(curLine);
            }
        }//end of while

        //This code below removes any exact duplicates
        LinkedHashSet<String> tmp = new LinkedHashSet<>(currentArray);
        currentArray.clear();
        currentArray.addAll(tmp);

        try {
            inputStream.close();
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return currentArray;
    }
    @Deprecated
    private void removeDuplicate(){
        if(currentArray.size() ==1 || numberOfDuplicateItems == -1)
            return;//base case
        HashMap<String,Integer> hmap = new HashMap<>();
        String curItem;
        for(int i =0;i<currentArray.size();i++){
            curItem = currentArray.get(i).split("\t",-1)[1];// should grab the item name (i hope)

            if(!hmap.containsKey(curItem)){
                hmap.put(curItem,1);
            }
            else {
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
@Deprecated
    public void setNumberOfDuplicateItems(int numberOfDuplicateItems) {
        this.numberOfDuplicateItems = numberOfDuplicateItems;
    }
}