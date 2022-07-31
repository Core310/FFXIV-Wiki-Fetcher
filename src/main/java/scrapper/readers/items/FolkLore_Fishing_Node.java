package scrapper.readers.items;


import scrapper.readers.items.baseNode.BaseItem;
import scrapper.readers.items.baseNode.Item;

import java.util.LinkedHashMap;

/**
 * <a href="https://ffxiv.consolegameswiki.com/wiki/Folklore_Nodes#Fisher">wiki link</a>
 * @see BaseItem
 * This class does not have additional info and slot. All else is the same as regularNode class
 */
public class FolkLore_Fishing_Node extends BaseItem implements Item { //FolkLore_Toem, Time, Item, Slot, Location, Cords, Added info -wiki stuff
    private final String folkloreTome;
    private final String time;

    public FolkLore_Fishing_Node(
            String item,
            String location,
            String cords,
            String additionalInfo,//Base Item stuff
            String folkloreTome,
            String time
    ){
        super(item,location,cords,additionalInfo);
        this.folkloreTome = folkloreTome;
        this.time = time;
    }

    /**
     * Used when file is already formatted.
     * @param arr array to input instead of manual input.
     */
    public FolkLore_Fishing_Node(String[] arr){
        super(arr[1],arr[2],arr[3],arr[4]);
        folkloreTome = arr[5];
        time = arr[6];
    }

    protected String getFolkloreTome() {
        return folkloreTome;
    }

    protected String getTime() {
        return time;
    }
    /**
     *
     * @return Output format as follows after standard items: time,folkLore
     */
    @Override
    public String toString() {
        //in the toString of itemOutputFormatter, it should autoload the following: itemName,location,cords,addedInfo. Figure out how 2 do this?
        ItemOutputFormatter itemOutputFormatter = new ItemOutputFormatter(getItemName(),getZone(),getCords(),getExtra());
        itemOutputFormatter.addElements(new String[]{time,folkloreTome});
        return itemOutputFormatter.toString();
    }

    /**
     * @return ItemType in LinkedHashmap form. Keys represent what the value is. For example, key = level, value = 5.
     */
    @Override
    public LinkedHashMap<String, String> toLinkedHashmap() {
        LinkedHashMap<String,String> lhm = new LinkedHashMap<>(BaseLinkedHashMap());
        lhm.put("FolkLore Tome",getFolkloreTome());
        lhm.put("Time",getTime());
        return lhm;
    }
}
