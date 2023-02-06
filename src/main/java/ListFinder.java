import java.util.*;
import java.util.stream.Stream;

/**
 * Exports a list of inputted items with the best teleport locations.
 * Works by grouping together teleports in the list.
 * Used on its own.
 *
 * @see FindItem
 */
public class ListFinder {
    private final FindItem findItem = new FindItem();
    /**
     * Container of formatted items where {@literal ArrayList<ArrayList<Item>>} aka {@literal ArrayList<ItemsUnderOneName<Data of one item>>}
     */
    private final ArrayList<ArrayList<String>> calledItems = new ArrayList<>();

    /**
     * Used by: {@link #sortByValue(LinkedHashMap)}
     * <br> Sorts groupedZones {@literal ArrayList<String[]>} value, moving its complementing key too.
     */
    static class descendingArraySize implements Comparator<ArrayList<String[]>> {
        @Override
        public int compare(ArrayList<String[]> o1, ArrayList<String[]> o2) {
            return Integer.compare(o2.size(), o1.size());
        }
    }

    /**
     * Obtains item data from {@link #calledItems} updated by {@link #addItem(String[])} which gets its data from essentialFindAllClosestAsMap from FindItem.java.
     * <br> Sorts the zones with the highest number of items using the method {@link #sortByValue(LinkedHashMap)}
     *
     * @return sorted zones with item data attached like so:
     * <br>{@literal Map<Zone,ArrayList<ItemData>>} where ItemData is split by "\n.
     */
    private LinkedHashMap<String, ArrayList<String[]>> buildGroupedZones() {
        LinkedHashMap<String, ArrayList<String[]>> groupedZones = new LinkedHashMap<>();
        int zoneIndex = 1;
        for (ArrayList<String> itemContainer : calledItems) {// strings inside the ArrayList is itemData
            for (String itemData : itemContainer) {//Represent one item's data where each value is separated by "\n"
                if (!groupedZones.containsKey(itemData.split("\n")[zoneIndex]))//FIXME 31/1/2023 Creates a new key for EVERY item.
                    //fixme continue, This is b/c of the .get(itemData.split... method expecting an array
                    groupedZones.put(itemData.split("\n")[zoneIndex], new ArrayList<>());//Make a new key and value(arraylist)
                groupedZones.get(itemData.split("\n")[zoneIndex]).add(itemData.split("\n", -1));//Add values into groupedZones.
            }
        }
        return sortByValue(groupedZones);
    }

    /**
     * Sorts the given lhm by value, helper to {@link #buildGroupedZones()}.
     * Credits to <a href = "https://stackoverflow.com/a/65917002/9099611"> Supreet Singh</a>
     *
     * @param map from {@link #buildGroupedZones()}
     * @return sorted map to go to {@link #formatGroupedZones()}
     * @see descendingArraySize
     */
    private static LinkedHashMap<String, ArrayList<String[]>> sortByValue(LinkedHashMap<String, ArrayList<String[]>> map) {
        Stream<Map.Entry<String, ArrayList<String[]>>> entryStream = map.entrySet().stream().sorted(Map.Entry.comparingByValue(new descendingArraySize()));
        List<Map.Entry<String, ArrayList<String[]>>> list = entryStream.toList();
        LinkedHashMap<String, ArrayList<String[]>> sortedMap = new LinkedHashMap<>();
        list.forEach(e -> sortedMap.put(e.getKey(), e.getValue()));
        return sortedMap;
    }

    /**
     * {@link #buildGroupedZones()} is always called first in this method.
     *
     * <br>
     *
     * @return deletes all duplicate items, only saving 1 copy instanced with the highest amount of items in one zone.
     * The method mergeDuplicate in FindItem.java removes any duplicate items that are in the SAME zone. Hence each zone has a maximum of one item.
     * @see descendingArraySize
     */
    private LinkedHashMap<String, ArrayList<String[]>> formatGroupedZones() {//UNFINISHED Not working rn debugging
        HashSet<String> itemsVisited = new HashSet<>();//Maybe put this in add item so I dont have to manually load everything?
        LinkedHashMap<String, ArrayList<String[]>> zoneGroups = buildGroupedZones();
        for (String zone : zoneGroups.keySet())
            for (int itemIndex =0;itemIndex< zoneGroups.get(zone).size();itemIndex++) {//An enhanced for-loop was not used because it did not work in the .remove method
                String[] item = zoneGroups.get(zone).get(itemIndex);
                String itemName = item[0];
                if (itemsVisited.contains(itemName))
                    zoneGroups.get(zone).remove(itemIndex);
                else
                    itemsVisited.add(itemName);
            }
        return zoneGroups;
    }

    /**
     * @return Input list items grouped by zone, with each item only occurring once. If an item occurs in a zone with many items, it will be deleted from the rest of the zones.
     */
    @Override
    public String toString() {
        if (formatGroupedZones().values().isEmpty())
            return "Please add an item to begin";
        StringBuilder sb = new StringBuilder();
        for (ArrayList<String[]> arr : formatGroupedZones().values()) {
            for (String[] st : arr)
                System.out.println(Arrays.toString(st)); //DELETEME
            //sb.append(Arrays.toString(st)).append("\n");
        }
        System.out.println(formatGroupedZones()); //DELETEME
        return sb.toString();
    }

    /**
     * Adds a search key to global list {@link #calledItems} to return using findItem.essentialFindAllClosestAsMap.
     *
     * @param searchKey item user is looking for. WIll be queued up to find.
     * @see FindItem
     */
    public void addItem(String searchKey) {
        calledItems.add(findItem.essentialFindAllClosestAsMap(searchKey));
    }//TODO 5/2/2023 Why can i not flatten the arrayList since every item will be iterated thru?

    /**
     * Bulk call several items (Mainly used for testing)
     *
     * @param items Array input of items a user is looking for
     */
    public void addItem(String[] items) {
        for (String str : items) {
            addItem(str);
        }
    }

    public void clearQueries() {
        calledItems.clear();
    }

    //TODO 4/12/2022 Add method to delete element from return list

}
