import me.xdrop.fuzzywuzzy.FuzzySearch;
import scrapper.readers.items.baseNode.StaticItemTypes;
import scrapper.readers.items.*;
import scrapper.readers.items.baseNode.Item;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.rmi.UnexpectedException;
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
     * return output of findItem()
     * <br> Holds all items (in raw data format).
     * <br> Each string container holds the raw data for ONE item.
     * @see FindItem findAllClosest()
     */
    private final ArrayList<String> currentArray = new ArrayList<>();
    /**
     * Default value of -1 for infinite number of duplicate.
     * <br>Shouldn't break if the value is below -1 but just in case.
     * <br>Using 0 OR 1 will produce no duplicate items.
     */
    private int numberOfDuplicateItems =-1;
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
    public ArrayList<StringBuilder> essentialFindAllClosestAsMap(String itemName){
        ArrayList<StringBuilder> rtrnArray = new ArrayList<>();//Return value
        for(LinkedHashMap<String,String> lhm: findAllClosestAsMap(itemName)){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Item: ").append(lhm.get("Item")).append("\n");
            stringBuilder.append("Zone: ").append(lhm.get("Zone")).append("\n");
            stringBuilder.append("Coordinates: ").append(lhm.get("Coordinates")).append("\n");

            if(lhm.get("Extra Information") != null & !Objects.equals(lhm.get("Extra Information"), ""))
                //todo refractor if statements and then appending part into a small method.
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
        return mergeDuplicate(outputList);
    }


    /**
     * <br> Helper method: {@link #mergeHelper(int, String, ArrayList)}
     * <br> Parent method: {@link #findAllClosestAsMap(String) findAllClosestAsMap}
     * <br>Merges any duplicate item with a time complexity of O(n^2)
     * <br> Searches each item by their abstract baseItem extension (excluding extra info).
     * <br> For example, the below should be merged into one item:
     * <pre>
     *     <code>
     * Item: Shark Tuna
     * Zone: Eastern La Noscea
     * Coordinates: X:32, Y:29
     * Bait Used: Spoon Worm, Northern Krill, Yumizuno, Heavy Steel Jig, Herring Ball, Sinking Minnow, Steel Jig, Shrimp Cage Feeder, Crab Ball, Rat Tail, Saltwater Boilie, Versatile Lure
     *
     * Item: Shark Tuna
     * Zone: Eastern La Noscea
     * Coordinates: (34,29)
     * Time: 7 PM to 9 PM
     *     </code>
     * </pre>
     */
    private ArrayList<LinkedHashMap<String,String>> mergeDuplicate(ArrayList<LinkedHashMap<String,String>> findAllClosestAsMapOutPut) {//TODO 14/9/2022 refactor to findAllClosestAsMap
        if (currentArray.size() == 1)//if there is only one item then don't do anything
            return findAllClosestAsMapOutPut;//base case
        ArrayList<String> arrayList = new ArrayList<>();

        for (int i =0;i< findAllClosestAsMapOutPut.size();i++) {
            String item = findAllClosestAsMapOutPut.get(i).get("Item: ");
            String tp =  findAllClosestAsMapOutPut.get(i).get("Coordinates: ");
            String itemAndTp = item + "\t" + tp;
            System.out.println(itemAndTp); //DELETEME
            int counter =0;

            for(String str: arrayList)
                if(str.contains(item) && str.contains(tp)){
                    findAllClosestAsMapOutPut = mergeHelper(i,itemAndTp, findAllClosestAsMapOutPut);//Performs the actual merging
                    counter++;
                    break;
                }
            if(counter ==0)
                arrayList.add(itemAndTp);
        }
        return findAllClosestAsMapOutPut;
    }//TODO 7/9/2022 Finish method!

    /**
     * Helper method for {@link #mergeDuplicate(ArrayList)}. Has a lot of linked values to the method above, and runs inside a for loop.
     * <br> This method is to keep code clean.
     * <br> Does the actual merging of values
     */
    private ArrayList<LinkedHashMap<String,String>> mergeHelper(int i, String itemAndTp,ArrayList<LinkedHashMap<String,String>> findAllClosestAsMapOutPut){
        //Already contains key?
        LinkedHashMap<String,String> itemToMerge = findAllClosestAsMapOutPut.get(i);//Item at current index that will be removed and merged into the item with the previous inex.
        findAllClosestAsMapOutPut.remove(i);
        //Firstly delete the duplicate key and store
        int baseItemIndex = -1;
        for(int baseItemFinder =0;baseItemFinder < findAllClosestAsMapOutPut.size();baseItemFinder++){

            if(findAllClosestAsMapOutPut.get(baseItemFinder).get("Item").contains(itemAndTp) &&
                    findAllClosestAsMapOutPut.get(baseItemFinder).get("Coordinates").contains(itemAndTp)
            ){
                System.out.println("Item and tp found");//DELETEME
                baseItemIndex = baseItemFinder;
                break;
            }
        }//Loops through all items
        if(baseItemIndex == -1) {
            try {
                throw new UnexpectedException("Value should always be updated in the baseItemFinder for loop");
            }
            catch (UnexpectedException e) {
                throw new RuntimeException(e);
            }
        }//debug case

        LinkedHashMap<String,String> mergeBase = findAllClosestAsMapOutPut.get(baseItemIndex);//Item that will receive new values. Is the first item come across, not the current index
        for(int currentItemValue = 4;currentItemValue < findAllClosestAsMapOutPut.get(i).size() ;currentItemValue++){//At index 3 is the cords value. Cords value differs a ton so im not using it.
            if(itemToMerge.contains(mergeBase)) continue;//FIXME 7/9/2022 weird stuff i dont wana think about
            mergeBase.append("\t").append(itemToMerge.split("\t",-1)[currentItemValue]);//i dont think this is giving the right output
        }//Loops through itemToMerge to see what values can be merged into the base value.
        currentArray.add(mergeBase.toString());
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
        //Uncomment this line if you want to remove duplicate item values at random (not recommended)
        //removeDuplicate();
        return currentArray;
    }
    /**
     * <br> This can be deleted, used for QOL currently
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
    /**
     * Set to return only the first item in the sequence and make sure it is the only item
     * (Basically check that their is only 1 item returned)
     * Set number of duplicates items allowed in all methods of this class.
     * @param numberOfDuplicateItems int form of max duplicates items allowed in return functions.
     */
    public void setNumberOfDuplicateItems(int numberOfDuplicateItems) {
        this.numberOfDuplicateItems = numberOfDuplicateItems;
    }
}