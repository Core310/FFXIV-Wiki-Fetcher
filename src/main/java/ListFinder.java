import java.util.*;
import java.util.stream.Stream;

/**
 * Exports a list of inputted items with the best teleport locations.
 * Works by grouping together teleports in the list.
 */
public class ListFinder {
    private final FindItem findItem = new FindItem();
    /**
     * Container of formatted items where {@literal ArrayList<ArrayList<Item>>} aka {@literal ArrayList<ItemGroup<Data of one item>>}
     */
    private final ArrayList<ArrayList<String>> allItems = new ArrayList<>();
    public Object outPutList() {
        return null;
    }//TODO 25/1/2023 DELETE

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
    private LinkedHashMap<String,ArrayList<String[]>> buildGroupedZones(){//TODO 25/1/2023 update comment^
        LinkedHashMap<String,ArrayList<String[]>> groupedZones = new LinkedHashMap<>();
        for(ArrayList<String> itemContainer: allItems){//Represents one query where the strings inside the ArrayList is itemData
            for(String itemData: itemContainer) {//Represent one item's data where each value is seperated by "\n"
                if(!groupedZones.containsKey(itemData.split("\n")[1]))
                    groupedZones.put(itemData.split("\n")[1], new ArrayList<>());//Make a new key and value(arraylist)
                groupedZones.get(itemData.split("\n")[1]).add(itemData.split("\n",-1));//Add values into groupedZones.
            }
        }
        return sortByValue(groupedZones);
    }

    /**
     * Sorts the given lhm (hopefully works) by value.
     * Credits to <a href = "https://stackoverflow.com/a/65917002/9099611"> Supreet Singh</a>
     * @param map from {@link #buildGroupedZones()}
     * @return sorted map to go to {@link #formatGroupedZones()}
     */
    private static LinkedHashMap<String,ArrayList<String[]>> sortByValue(LinkedHashMap<String,ArrayList<String[]>> map) {
        Stream<Map.Entry<String, ArrayList<String[]>>> entryStream = map.entrySet().stream().sorted(Map.Entry.comparingByValue(new descendingArraySize()));
        List<Map.Entry<String,ArrayList<String[]>>> list = entryStream.toList();
        LinkedHashMap<String,ArrayList<String[]>> sortedMap = new LinkedHashMap<>();
        list.forEach(e -> sortedMap.put(e.getKey(), e.getValue()));
        return sortedMap;
    }

    /**
     * {@link #buildGroupedZones()} is always called first in this method.
     *
     * <br>
     * @return deletes all duplicate items, only saving 1 copy instanced with the highest amount of items in one zone.
     * The method mergeDuplicate in FindItem.java removes any duplicate items that are in the SAME zone. Hence each zone has a maximum of one item.
     * @see descendingArraySize
     */
    private List<Map.Entry<String, ArrayList<String[]>>> formatGroupedZones(){//UNFINISHED Rebuild this whole method

        ArrayList<String> itemsMet = new ArrayList<>();//TODO 4/12/2022 Should I make this a global array and implement it in addItem?  //Counter of items that are visited.
        LinkedHashMap<String,ArrayList<String[]>> groupedZones = buildGroupedZones();
        //Then build the sortedGroupedZone Below
        Stream<Map.Entry<String, ArrayList<String[]>>> sortedStreamGroup = groupedZones.entrySet().stream().sorted(Map.Entry.comparingByValue(new descendingArraySize()));
        LinkedHashMap<String,ArrayList<String[]>> sortedZones = new LinkedHashMap<>();

        /*^
        create a list of values,
         sort it,
          then itr thru each value.
           when reach said value,
          put first into a new LHM with corresponding key in the original map.
        Once mapEntry is changed to map, reflect changes here.
         Itr thru map keys -> values (Arraylist) -> Strings inside arraylist (value = itemData).
        If item is present in the items array, do not add it to the final return map (basically skip adding the value)
        */

        /*
        for(Map.Entry<String,ArrayList<String[]>> entry: sortedStreamGroup)
        for () {//Traverse through keys
            for (){ //Traverse through the values (which are ArrayLists)
                String itemName = keyIndex.getValue().get(itemContainerIndex)[0].split("Item: ")[1];
                if (itemsMet.contains(itemName)) {
                    //TODO 4/12/2022 Delete the current value

                    continue;
                }
                itemsMet.add(keyIndex.getValue().get(itemContainerIndex)[0].split("Item: ")[1]); //Add item name to array of items.
            }
        }
         *///UNFINISHED
            return null;
    }


    /**
     * This has one line of code using 2 private methods,{@link #buildGroupedZones()} is called first, which is to stream data into {@link #formatGroupedZones()}.
     * <br> Builds an internal container grouping all items together by their respective zone and deletes any duplicate items from most to the least sized zones.
     * <ul> Some notes
     * <li> The reason this is not merged with the addItem method is so we can group the zones first, find the highest number of zone items, THEN remove the duplicates.</li>
     * <li> This method is called inside the {@link #toString()}</li>
     * <li> Time complexity of O(n^2)</li>
     * </ul>
     *
     * @return {@link #formatGroupedZones()} which is zones sorted by group, with removed duplicate items.
     */
    public LinkedHashMap<String, ArrayList<String[]>> makeGroupedZones(){
        return buildGroupedZones();
        }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ArrayList<String[]> arr : makeGroupedZones().values()) {
            for(String[] st: arr)
                sb.append(Arrays.toString(st)).append("\n");
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
    }
    /**
     * Bulk call several items (Mainly used for testing)
     * @param items Array input of items a user is looking for
     */
    public void addItem(String[] items){
        for(String str: items){
            addItem(str);
        }
    }

    public void clearQueries(){
        allItems.clear();
    }

    //TODO 4/12/2022 Add method to delete element from return list

}
