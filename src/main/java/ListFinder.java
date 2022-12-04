import java.util.*;
import java.util.stream.Stream;

/**
 * Exports a list of inputted items with the best teleport locations.
 * Works by grouping together teleports in the list.
 */
public class ListFinder {
    private final FindItem findItem = new FindItem();//We don't want to create a new FindItem every addItem call
    /**
     * Container of formatted items where {@literal ArrayList<ArrayList<Item>>} aka {@literal ArrayList<ItemGroup<Data of one item>>}
     */
    private final ArrayList<ArrayList<String>> allItems = new ArrayList<>();
    /**
     * A Comparator to sort an internal map by its value.
     * <br> Sorts groupedZones {@literal ArrayList<String[]>} value, moving its complementing key too.
     */
    static class descendingArraySize implements Comparator<ArrayList<String[]>> {
        @Override
        public int compare(ArrayList<String[]> o1, ArrayList<String[]> o2) {
            return o1.size() - o2.size();
        }
    }

    /**
     * Obtains item data from {@link #allItems} updated by {@link #addItem(String[])} which gets its data from essentialFindAllClosestAsMap from FindItem.java.
     * @return unsorted zones with item data attached like so:
     * <br>{@literal Map<Zone,ItemContainer<ItemData>>} where ItemData is split by "\n.
     */
    private LinkedHashMap<String,ArrayList<String[]>> buildGroupedZones(){
        LinkedHashMap<String,ArrayList<String[]>> groupedZones = new LinkedHashMap<>();
        for(ArrayList<String> itemContainer: allItems){//Represents one query where the strings inside the ArrayList is itemData
            for(String itemData: itemContainer) {//Represent one item's data where each value is seperated by "\n"
                if(!groupedZones.containsKey(itemData.split("\n")[1]))
                    groupedZones.put(itemData.split("\n")[1], new ArrayList<>());//Make a new key and value(arraylist)
                groupedZones.get(itemData.split("\n")[1]).add(itemData.split("\n",-1));//Add values into groupedZones.
            }
        }
        return groupedZones;
    }

    /**
     * {@link #buildGroupedZones()} is always called first in this method.
     * <br> Uses a stream to sort the entries with a comparator (linked in @see).
     * <br> Then iterates through TODO after done with the converting to a map
     * @return deletes all duplicate items, only saving 1 copy instanced with the highest amount of items in one zone.
     * @see descendingArraySize
     */
    private List<Map.Entry<String, ArrayList<String[]>>> formatGroupedZones(){ //TODO 4/12/2022 In every zone, is every item guaranteed to be unique? If not how to catch, what to do?
        LinkedHashMap<String,ArrayList<String[]>> groupedZones = buildGroupedZones();
        //Then build the sortedGroupedZone Below
        Stream<Map.Entry<String, ArrayList<String[]>>> sortedStreamGroup = groupedZones.entrySet().stream().sorted(Map.Entry.comparingByValue(new descendingArraySize()));
        List<Map.Entry<String, ArrayList<String[]>>> sortedGroupedZones = sortedStreamGroup.toList();//FIXME FIRST ISSUE TO ADDRESS 1/12/2022 stream->map and not mapEntry (See above)
        /*^ See this post: https://www.digitalocean.com/community/tutorialsx/sort-hashmap-by-value-java
        Basically, create a list of the values, sort it, then itr thru each value. when reach said value,
         just put that in first into a new LHM with the corresponding key in the original map.
        Once mapEntry is changed to map, reflect changes here. Itr thru map keys -> values (Arraylist) -> Strings inside arraylist (value = itemData).
        If item is present in the items array, do not add it to the final return map (basically skip adding the value)
        */

        ArrayList<String> items = new ArrayList<>();//TODO 4/12/2022 Should I make this a global array and implement it in addItem?  //Counter of items that are visited.
        for (Map.Entry<String, ArrayList<String[]>> keyIndex : sortedGroupedZones) {//Traverse through keys
            for (int itemContainerIndex = 0; itemContainerIndex < keyIndex.getValue().size(); itemContainerIndex++){ //Traverse through the values (which are ArrayLists)
                String itemName = keyIndex.getValue().get(itemContainerIndex)[0].split("Item: ")[1];
                if (items.contains(itemName)) {
                    //TODO 4/12/2022 Delete the current value
                    continue;
                }
                items.add(keyIndex.getValue().get(itemContainerIndex)[0].split("Item: ")[1]); //Add item name to array of items.
            }
        }
        return sortedGroupedZones;
    }//UNFINISHED see fixmes


    /**
     * This has one line of code using 2 private methods,{@link #buildGroupedZones()} is called first, which is to stream data into {@link #formatGroupedZones()}.
     * <br> Builds an internal container grouping all items together by their respective zone and deletes any duplicate items from most to the least sized zones.
     * <ul> Some notes
     * <li> The reason this is not merged with the addItem method is so we can group the zones first, find the highest number of zone items, THEN remove the duplicates.</li>
     * <li> This method is called inside the {@link #outPutList()}</li>
     * <li> Time complexity of O(n^2)</li>
     * </ul>
     * @return {@link #formatGroupedZones()} which is zones sorted by group, with removed duplicate items.
     */
    private List<Map.Entry<String, ArrayList<String[]>>> makeGroupedZones(){
        return formatGroupedZones();
        }
    /**
     * Last method to call of this class used to get the output.
     * <br>Stems from the final method call {@link #makeGroupedZones()}.
     * @return Neatly returns list
     */
    public String outPutList(){
        StringBuilder sb = new StringBuilder();
        StringBuilder value = new StringBuilder();
        for(Map.Entry<String, ArrayList<String[]>> map: makeGroupedZones()) {
            for (String[] val : map.getValue())
                value.append(Arrays.toString(val));
            sb.append("Key: ").append(map.getKey()).append("\nValue: ").append(value).append("\n");
            value = new StringBuilder();
        }
        return sb.toString();
        }

    /**
     * Adds a search key to global list {@link #allItems} to return using findItem.essentialFindAllClosestAsMap.
     * @see FindItem
     * @param searchKey item user is looking for. WIll be queued up to find.
     */
    public void addItem(String searchKey){
        allItems.add(findItem.essentialFindAllClosestAsMap(searchKey));
    }//TODO 4/12/2022 refactor so that it will constantly update the internal map to give the most up to date data? It would have horrible time complexity/efficiency

    //TODO 4/12/2022 Add method to delete search key from the current final map

    /**
     * Bulk call several items (Mainly used for testing)
     * @param items Array input of items a user is looking for
     */
    public void addItem(String[] items){
        for(String str: items){
            addItem(str);
        }
    }
}
