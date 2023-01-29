import java.util.ArrayList;
/**
 * This is a replacement for the following container: {@literal List<Map.Entry<String, ArrayList<String[]>>>} where {@literal Map<Zone,ItemContainer<ItemData>>}.
 * <br> This class partially replaces it by doing the following: {@literal Arraylist<ZoneContainer>}
 * <br> Why couldn't I use this container?
 * <br> - Sorting issues (I could not sort by index from high to low of the VALUE part b/c LHM does not hv
 *
 */
public class ZoneContainer {//TODO 23/1/2023 Find a better name for this
    private final ArrayList<String[]> itemContainer = new ArrayList<>();//ArrayList<Item[ItemData]>}
    private final String zone;
    public ZoneContainer(String zone){
        this.zone = zone;
    }

    /**
     * Add a new ITEM
     * @param itemData Item in format of
     */
    public void addItem(String[] itemData){
        itemContainer.add(itemData);
    }

    public ArrayList<String[]> getItemContainer() {
        return itemContainer;
    }

    public String getZone() {
        return zone;
    }
}
