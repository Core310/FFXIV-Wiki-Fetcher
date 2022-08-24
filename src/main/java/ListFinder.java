import java.util.*;
/**
 * Exports a list of inputted items with the best teleport locations.
 * Works by grouping together teleports in the list.
 */
public class ListFinder {
    private final FindItem findItem = new FindItem();
    /**
     * Stores the itemInput as well as its corresponding teleport zone
     * <br> ItemName,tpList (hashset)
     */
    private final HashMap<String, HashSet<String>> itemAndTpMap = new HashMap<>();
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
    public StringBuilder outPutList(){
        StringBuilder output = new StringBuilder();
        for(String item: searchKeys){
            //then another for loop over the tp list, start for greatest -> least
            //Output greatest amnt of tp if found to a stringBuilder

        }

        return null;
    }

    /**
     * Adds a search key to the list to return.
     * @param searchKey item user is looking for. WIll be queued up to find.
     */
    public void addItem(String searchKey){
        searchKeys.add(searchKey);
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
                itemAndTpMap.put(itemName,new HashSet<>());
            itemAndTpMap.get(itemName).add(zone);
        }
        //Get item name & tp and add it to global vars
    }


}
