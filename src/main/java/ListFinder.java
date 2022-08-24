import java.util.*;
import java.util.stream.Collectors;

/**
 * Exports a list of inputted items with the best teleport locations.
 * Works by grouping together teleports in the list.
 */
public class ListFinder {
    private final FindItem findItem = new FindItem();
    /**
     * Stores the itemInput as well as its corresponding teleport zone
     * <br> ItemName, Hmap - tp with raw data-
     */
    private final HashMap<String, HashMap<String, String>> itemAndTpMap = new HashMap<>();
    /**
     * Stores all teleport sets without itemName
     * <br> tpList, occurrences
     */
    private final HashMap<String, Integer> tpMap = new HashMap<>();
    private final Stack<String> searchKeys = new Stack<>();

    /**
     * Output all items added in addItem.
     * <br> Groups all elements by their respective teleports.
     * <br> For example, two items sharing two same teleport values will return only the sharing teleport locations.
     *
     * @see ListFinder addItem
     * @return Items grouped by teleports
     */
    public StringBuilder outPutList(){ //TODO 8/24/2022
        StringBuilder output = new StringBuilder();
        Map<String, Integer> sortedTpValuesAsc = sortMapByValue(tpMap, true);
        for(String item: searchKeys){//for all items
            for(String tp: sortedTpValuesAsc.keySet()){//For all tp's
             if(itemAndTpMap.get(item).containsKey(tp)){//If there is a tp matching
                 output.append(itemAndTpMap.get(item).get(tp));//FIXME 8/24/2022 ?
                 break;//Breaks inner tp for loop to move onto next item
             }
            }
        }
            //then another for loop over the tp list, start for greatest -> least
            //Output greatest amnt of tp if found to a stringBuilder
            return output;
        }

    /**
     * Adds a search key to the list to return.
     * @param searchKey item user is looking for. WIll be queued up to find.
     */
    public void addItem(String searchKey){
        searchKeys.add(searchKey);
        addItemInfo(findItem.essentialFindAllClosestAsMap(searchKey));
    }

    /**
     * Updates 2 internal Hashmaps. One stores all teleports currently stored, other stores itemName and teleports assigned to it.
     * <br>
     * Once user is ready to search for all items, this will search for each item using a essentialFindAllClosestAsMap and upload it to an internal array
     * @param inputArrayList The output from the method essentialFindAllClosestAsMap
     * @see FindItem essentialFindAllClosestAsMap
     */
    private void addItemInfo(ArrayList<StringBuilder> inputArrayList){
        String[] itemData;
        String zone = null;
        String itemName = null;
        for(StringBuilder outputItems: inputArrayList){
            itemData = outputItems.toString().split("\t",-1);

            for(String curValue: itemData){
                //Load itemName
                if(curValue.contains("Item: ")){
                    itemName = curValue.split("Item: ")[1];
                    System.out.println(itemName); //todo delete me
                }
                //Load TP/zonee
                else if(curValue.contains("Zone")){
                    zone  = curValue.split("Zone: ",-1)[1];//at [0] should be "Zone: " and [1] = actual zone
                    System.out.println(zone); //todo delete me

                    //Updates tpMap, puts a new zone in if one doesn't already exist.
                    if(tpMap.containsKey(zone))
                        tpMap.put(zone, tpMap.get(zone) + 1);
                    else
                        tpMap.put(zone, 1);
                }
            }
            if(!itemAndTpMap.containsKey(itemName))//If item not present make new hashset and then add later
                itemAndTpMap.put(itemName,new HashMap<>());
            itemAndTpMap.get(itemName).put(zone, Arrays.toString(itemData));//FIXME 8/24/2022
        }
        //Get item name & tp and add it to global vars
    }

    /**
     * Credits for this method go to the following post: <a href="https://stackoverflow.com/a/13913206/9099611">...</a>
     * <br> None of this code is mine.
     * @param unsortMap Unsorted map with a string and int argument
     * @param order Ascending = true, descending = false
     * @return Sorted map
     */
    private static Map<String, Integer> sortMapByValue(Map<String, Integer> unsortMap, final boolean order)
    {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(unsortMap.entrySet());

        // Sorting the list based on values
        list.sort((o1, o2) -> order ? o1.getValue().compareTo(o2.getValue()) == 0
                ? o1.getKey().compareTo(o2.getKey())
                : o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue()) == 0
                ? o2.getKey().compareTo(o1.getKey())
                : o2.getValue().compareTo(o1.getValue()));
        return list.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));


    }
}
