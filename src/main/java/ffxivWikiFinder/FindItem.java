package ffxivWikiFinder;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import fileBuilder.readers.items.*;
import fileBuilder.readers.items.baseNode.Item;
import fileBuilder.readers.items.baseNode.StaticItemTypes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * FuzzySearch implementation to find an ITEM in the file.
 * <p>After the main file has been loaded with data and formatted, this class is used to find a certain item for listFinder</p>
 * It has a default constructor to assign a file.
 *
 * @see ListFinder
 * @see StaticItemTypes
 * @see fileBuilder.readers.items
 */
public class FindItem {
    /**
     * Instantiated in {@link #essentialFindAllClosestAsMap}
     * <br>
     * Main array being worked on in this class.
     */
    private ArrayList<LinkedHashMap<String, String>> itemContainer;//<ItemDescriptor,ActualItem>

    /**
     * Child (builds off of) of {@link #findAllClosestAsMap(String)} which is also a child of {@link #findAllClosest(String)}
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
     * </code>
     * </pre>
     *
     * @param itemName item to find
     * @return Item data separated by \n like so {@literal ArrayList <Individual items>}
     */
    public ArrayList<String> essentialFindAllClosestAsMap(String itemName) {
        itemContainer = new ArrayList<>();
        ArrayList<String> returnValue = new ArrayList<>();//Return value
        findAllClosestAsMap(itemName);//TODO 10/17/23 make run in constructor call?
        for (LinkedHashMap<String, String> lhm : itemContainer) {//Per item (represented as a stringBuilder)
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Item: ").append(lhm.get("Item")).append("\n");
            stringBuilder.append("Zone: ").append(lhm.get("Zone")).append("\n");
            stringBuilder.append("Coordinates: ").append(lhm.get("Coordinates")).append("\n");
            //No use in creating a helper method for the .append StringBuilder. I could create a small class but that would be overkill?
            if (lhm.get("Extra Information") != null & !Objects.equals(lhm.get("Extra Information"), ""))
                stringBuilder.append("Extra Information: ").append(lhm.get("Extra Information")).append("\n");
            if (lhm.get("Bait Used") != null)
                stringBuilder.append("Bait Used: ").append(lhm.get("Bait Used")).append("\n");
            if (lhm.get("Time") != null)
                stringBuilder.append("Time: ").append(lhm.get("Time")).append("\n");
            if (lhm.get("FolkLore Tome") != null)
                stringBuilder.append("FolkLore Tome: ").append(lhm.get("FolkLore Tome")).append("\n");
            returnValue.add(stringBuilder.toString());
        }
        return returnValue;
    }

    /**
     * Outputs each case neatly with ALL values, and descriptors for each item argument. (eg. this is the folk lore tome)
     * <p>Multiple items will be stored in an arraylist with arguments of the Map.</p>
     * <p>E.g. ffxivWikiFinder.FindItem.findAllClosestAsMap(input);</p>
     * <p>input: Lava toad</p>
     * output:[{Item=Lava Toad, Zone=Southern Thanalan, Coordinates=(x13,y31), Extra Information=, Level=50}]
     *
     */
    private void findAllClosestAsMap(String itemName) {
        Item item;
        final int itemNameIndex =0;
        for (String curLine : findAllClosest(itemName)) {
            String[] currentSplitItem = curLine.split("\t", -1);
            String curItem = currentSplitItem[itemNameIndex];

            StaticItemTypes itemType = StaticItemTypes.valueOf(curItem);
            switch (itemType) {
                case FOLK_LORE_FISH_NODE -> {
                    item = new FolkLore_Fish_Node(currentSplitItem);
                    itemContainer.add(item.toLinkedHashmap());
                }
                case FOLK_LORE_NODE -> {
                    item = new FolkLore_Node(currentSplitItem);
                    itemContainer.add(item.toLinkedHashmap());
                }
                case REGULAR_NODE -> {
                    item = new Regular_Node(currentSplitItem);
                    itemContainer.add(item.toLinkedHashmap());
                }
                case UNSPOILED_NODE, ARR_UNSPOILED_NODE -> {
                    item = new Unspoiled_Node(currentSplitItem);
                    itemContainer.add(item.toLinkedHashmap());
                }
                case FISH_NODE -> {
                    item = new Fish_Node(currentSplitItem);
                    itemContainer.add(item.toLinkedHashmap());
                }
                case FISH_BIG_NODE -> {
                    item = new Fish_Big_Node(currentSplitItem);
                    itemContainer.add(item.toLinkedHashmap());
                }
                case FISH_COLLECTABLES_NODE -> {
                    item = new Fish_Collectable_Node(currentSplitItem);
                    itemContainer.add(item.toLinkedHashmap());
                }
                default -> throw new RuntimeException("Wrong static item type assigned");
            }
        }
        searchForDuplicate();
    }

    /**
     * Parent method: {@link #findAllClosestAsMap(String)}
     * <br>Helper method: {@link #mergeDuplicate(int, String)}
     * <br> This method is a trigger for the actual merging method to take place.
     * <br>Merges any duplicate item with a time complexity of O(n^2) using {@link #mergeDuplicate(int, String)}.
     * <br> Searches each item by their item and zone.
     * <br> Basically, if two or more items share the same name and zone, this method will merge both items keeping one of their teleport values (at random).
     *
     */

    private void searchForDuplicate() {
        if (itemContainer.size() == 1)
            return ;
        else if (itemContainer.isEmpty()) {
            throw new RuntimeException("Current array should never be less than or equal to 0 here");
        }
        HashSet<String> duplicateItemTracker = new HashSet<>();

        for (int i = 0; i < itemContainer.size(); i++) {
            String item = itemContainer.get(i).get("Item"),
                    zone = itemContainer.get(i).get("Zone"),
                    itemAndZone = item + "\t" + zone;
            int duplicateItemCounter=0;
            for (String str : duplicateItemTracker)
                if (str.contains(itemAndZone)) {
                    mergeDuplicate(i, itemAndZone);
                    duplicateItemCounter++;
                    i--;
                    break;
                }

            if (duplicateItemCounter == 0)
                duplicateItemTracker.add(itemAndZone);

            /* todo replace w/ this
            LHS -> .contains(item&Zone) ? merge dupe : contiune;
            problems: Cant actually itr thru lhs right? Oso cant have same keys in it => problem



                            if(duplicateItemTracker.contains(itemAndZone)){
                mergeDuplicate(i, itemAndZone);
                i = i-1;
            }
            else
                duplicateItemTracker.add(itemAndZone);
             */
        }

    }

    /**
     * Helper method for {@link #searchForDuplicate()}. Has a lot of linked values to the method above, and runs inside a for loop.
     * <br> This method is to keep code clean.
     * <br> Does the actual merging of values
     *
     * @param i                         for loop iterator in main method.
     * @param itemAndTp                 item and teleport value in one string seperated by `\t`
     */
    private void mergeDuplicate(int i, String itemAndTp) {//TODO 9/12/23 clean me
        //Already contains key?
        LinkedHashMap<String, String> itemToMerge = itemContainer.get(i);//Item at current index that will be removed and merged into the item with the previous index.
        itemContainer.remove(i);
        int baseItemIndex = -1;
        for (int baseItemFinder = 0; baseItemFinder < itemContainer.size(); baseItemFinder++) {//Finds duplicate item
            if (itemContainer.get(baseItemFinder).get("Item").contains(itemAndTp.split("\t", -1)[0]) &&
                    itemContainer.get(baseItemFinder).get("Zone").contains(itemAndTp.split("\t", -1)[1])
            ) {//Does the current value contain both the item and the zone?
                baseItemIndex = baseItemFinder;
                break;
            }
        }//Grab index of other duplicate
        if (baseItemIndex == -1)//Same value? Then just delete one of them and keep another.
            return;
        LinkedHashMap<String, String> mergeBase = itemContainer.get(baseItemIndex);//Item that will receive new values. Is the first item come across, not the current index
        String[] mergeBaseKeySet = mergeBase.keySet().toArray(new String[0]);
        String[] itemToMergeKeySet = itemToMerge.keySet().toArray(new String[0]);

        String[] largerHeader, smallerHeader;
        if (mergeBaseKeySet.length > itemToMergeKeySet.length) {
            largerHeader = mergeBaseKeySet;
            smallerHeader = itemToMergeKeySet;
        } else {
            largerHeader = itemToMergeKeySet;
            smallerHeader = mergeBaseKeySet;
        }
        if (largerHeader.length < 4 || smallerHeader.length < 4) {
            throw new RuntimeException("Current Header size should never be less than 4. Current Header size: " + Arrays.toString(smallerHeader)
                    + "\n" + "Current Header Contents:" + itemContainer.get(i));
        }

        for (int currentItemHeader = 4; currentItemHeader < largerHeader.length; currentItemHeader++) {//At index 3 is the cords value. Cords value differs a ton so im not using it.
            if (!Arrays.toString(smallerHeader).contains(largerHeader[currentItemHeader])) {//Makes all of smaller header into one string. Compares it to larger header to find any of the same instances
                System.out.println(); //DELETEME
                mergeBase.put(itemToMergeKeySet[currentItemHeader], itemToMerge.get(itemToMergeKeySet[currentItemHeader]));
                //Takes whatever extra header values (and its data) and plops them in the baseData+Header
            }
        }//Loops through itemToMerge to see what values can be merged into the base value.
        itemContainer.remove(baseItemIndex);//Deletes the second value found, then replaces it with the new value
        itemContainer.add(baseItemIndex, mergeBase);
    }

    /**
     * Loops through file finding the highest matching value and return all in array.
     * <p> Called helper as returns the raw data values. <p> Acts as parent class for other 'find' methods.
     *
     * @param itemName The item that is being searched for.
     * @return All values which have the same ratio to ItemName.
     */
    private ArrayList<String> findAllClosest(String itemName) {
        ArrayList<String> rawItemContainer = new ArrayList<>();
        InputStream inputStream = getClass().getResourceAsStream("/XIVGather.TSV");assert inputStream != null;
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        int highestRatio = 0,fuzzySearchRatio,itemNameIndex = 1;
        String curLine,curItem;
        while (true) {
            try {
                if (((curLine = br.readLine()) == null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            curItem = curLine.split("\t", -1)[itemNameIndex];//itemNameIndex-1, aka position 0 is the type item.
            fuzzySearchRatio = FuzzySearch.ratio(curItem, itemName);
            if (fuzzySearchRatio == highestRatio) {
                rawItemContainer.add(curLine);
            } else if (fuzzySearchRatio > highestRatio) {
                highestRatio = fuzzySearchRatio;
                rawItemContainer.clear();
                rawItemContainer.add(curLine);
            }
        }
        LinkedHashSet<String> tmp = new LinkedHashSet<>(rawItemContainer);
        rawItemContainer.clear();
        rawItemContainer.addAll(tmp);

        try {
            inputStream.close();
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return rawItemContainer;
    }//feature If more than one itemName is found, should have an option to delete one
}