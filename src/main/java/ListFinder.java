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
    /**
     * LinkedHashMap{@literal <Zone,GroupedItemData<IndividualItemData>>}
     * <br>A container to built by groupedZones to be sorted by value.
     * <br> (This method is used since the time complexity of having to iterate over every item
     */
    private final LinkedHashMap<String,ArrayList<String[]>> groupedZones = new LinkedHashMap<>();
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
     * Builds an internal container grouping all items together by their respective zone (sortedGroupedZones)
     * <br> The reason this is not merged with the addItem method is so we can group the zones first, find the highest number, THEN remove the duplicates.
     * <br> Should be called right before return the final output of this method.
     * <br> Time complexity of O(n^2)
     * @return Zones sorted by group, with removed duplicate items.
     */
    private List<Map.Entry<String, ArrayList<String[]>>> makeGroupedZones(){
        for(ArrayList<String> arr: allItems){//Represents one item search group
            for(String rawGroupData: arr) {//Represent the raw data of the search group
                if(!groupedZones.containsKey(rawGroupData.split("\n")[1]))
                    groupedZones.put(rawGroupData.split("\n")[1], new ArrayList<>());//Make a new key and value(arraylist)
                groupedZones.get(rawGroupData.split("\n")[1]).add(rawGroupData.split("\n",-1));//Add values in
            }
        }//Builds groupedZones ^^
        //Then build the sortedGroupedZone
        Stream<Map.Entry<String, ArrayList<String[]>>> sortedStreamGroup = groupedZones.entrySet().stream().sorted(Map.Entry.comparingByValue(new descendingArraySize()));
        List<Map.Entry<String, ArrayList<String[]>>> sortedGroupedZones = sortedStreamGroup.toList();
        ArrayList<String> items = new ArrayList<>();
        for (Map.Entry<String, ArrayList<String[]>> keyIndex : sortedGroupedZones) {//Traverse thru keys
            for (int itemContainerIndex = 0; itemContainerIndex < keyIndex.getValue().size(); itemContainerIndex++){ //Traverse through the values (which are ArrayLists)
                    String itemName = keyIndex.getValue().get(itemContainerIndex)[0].split("Item: ")[1];
                    if (items.contains(itemName)) {
                        keyIndex.getValue().remove(itemContainerIndex);
                        //TODO 1/12/2022 Figure out how to check if the key's value is empty and remvoe the key from the map
                        continue;
                    }
                    items.add(keyIndex.getValue().get(itemContainerIndex)[0].split("Item: ")[1]); //Add item name to array of items.
                }
        }
        return sortedGroupedZones;
        }
    /**
     * Last method to call of this class used to get the output.
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
     * Adds a search key to the list to return using findItem.essentialFindAllClosestAsMap.
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

    //TODO 16/11/2022 Add a new method, clear specific item (using fuzzy matching?) It should return the item cleared.
    /**
     * Clears all internal containers
     */
    public void clearSavedQueries(){
        groupedZones.clear();
        allItems.clear();
    }

    /**
     * {@link #outPutList()}
     * @return outPutList as a string
     */
    @Override
    public String toString() {
        return outPutList().toString();
    }
}
