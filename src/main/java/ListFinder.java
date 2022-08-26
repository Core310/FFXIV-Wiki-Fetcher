import java.util.*;

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
    private final HashMap<String, HashMap<String, ArrayList<StringBuilder>>> itemAndTpMap = new HashMap<>();
    /**
     * Stores all teleport sets without itemName
     * <br> tpList, occurrences
     */
    private final HashMap<String, Integer> tpMap = new HashMap<>(); //FIXME 8/24/2022 tp map isnt updating?
    private final Stack<String> searchKeys = new Stack<>();

    /**
     * Output all items added in addItem.
     * <br> Groups all elements by their respective teleports.
     * <br> For example, two items sharing two same teleport values will return only the sharing teleport locations.
     *
     * @return Items grouped by teleports
     * @see ListFinder addItem
     */
    public StringBuilder outPutList(){
        StringBuilder output = new StringBuilder();
        Map<String, Integer> sortedTpValuesAsc = sortByValue(tpMap);
        for(String item: searchKeys){//for all items
            for(String tp: sortedTpValuesAsc.keySet()){//For all tp's
                if((itemAndTpMap.get(item)!= null) && (itemAndTpMap.get(item).containsKey(tp))){//If there is a tp matching
                 for(StringBuilder sb: itemAndTpMap.get(item).get(tp)){
                     output.append(sb);//appends raw data
                     if(sb.toString().contains("Item: "))
                         output.append("\n");
                 }
                 break;//Breaks inner tp for loop to move onto next item
             }
            }
        }
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
            itemData = outputItems.toString().split("\n",-1);

            for(String curValue: itemData){
                //Load itemName
                if(curValue.contains("Item: ")){
                    itemName = curValue.split("Item: ")[1];
                }
                //Load TP/zonee
                if(curValue.contains("Zone: ")){
                    zone  = curValue.split("Zone: ")[1];//at [0] should be "Zone: " and [1] = actual zone
                    //Updates tpMap, puts a new zone in if one doesn't already exist.
                    if(tpMap.containsKey(zone))
                        tpMap.put(zone, tpMap.get(zone) + 1);
                    else
                        tpMap.put(zone, 1);
                }
            }
            if(!itemAndTpMap.containsKey(itemName))//If item not present make new hashset and then add later
                itemAndTpMap.put(itemName,new HashMap<>());

            itemAndTpMap.get(itemName).put(zone, inputArrayList);
        }
        //Get item name & tp and add it to global vars
    }

    /**
     * Credits for this method go to the following post: <a href="https://stackoverflow.com/a/2581754/9099611">...</a>
     * <br> None of this code is mine.
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}
