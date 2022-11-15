package scrapper.readers.items;

import scrapper.readers.items.baseNode.BaseItem;
import scrapper.readers.items.baseNode.Item;

import java.util.LinkedHashMap;

/**
 * <a href="https://ffxiv.consolegameswiki.com/wiki/Fishing_Locations">Fishing wiki link</a>
 * @see BaseItem
 */
public class Fish_Node extends BaseItem implements Item {
    private final String type, fishingLog;
    private int level;

    /**
     * Base constructor that is used for inheritance on its children.
     *
     */
    public Fish_Node(String fish,
                     String zone,
                     String cords,
                     String baitUsed,//aka extra
                     //End of base items
                     String type,
                     String fishingLog,
                     int level

    ) {
        super(fish, zone, cords, baitUsed);
        this.type = type;
        this.fishingLog = fishingLog;
        this.level = level;
    }

    /**
     * Used when file is already formatted.
     * @param arr array to input instead of manual input.
     */
    public Fish_Node(String[] arr) {
        super(arr[1],arr[2],arr[3],arr[4]);
        type = arr[5];
        fishingLog = arr[6];
        level = Integer.parseInt(arr[7]);
    }

    /**
     * @return ItemType in LinkedHashmap form. Keys represent what the value is. For example, key = level, value = 5.
     */
    @Override
    public LinkedHashMap<String, String> toLinkedHashmap() {
        LinkedHashMap<String,String> lhm = new LinkedHashMap<>(BaseLinkedHashMap());
        lhm.remove("Extra Information");//In fishing, this is called "bait used" instead of extra info
        //see here: https://ffxiv.consolegameswiki.com/wiki/Fishing_Locations
        lhm.put("Bait Used",getExtra());

        lhm.put("Type",type);
        lhm.put("Fishing Log",fishingLog);
        lhm.put("Level", String.valueOf(level));
        return lhm;
    }

    /**
     *
     * @return Output format as follows after standard items: time,folkloreTome, slot
     */
    @Override
    public String toString() {
        ItemOutputFormatter itemOutputFormatter = new ItemOutputFormatter(getItemName(),getZone(),getCords(),getExtra());
        itemOutputFormatter.addElements(new String[]{type,fishingLog, String.valueOf(level)});
        return itemOutputFormatter.toString();
    }
}
