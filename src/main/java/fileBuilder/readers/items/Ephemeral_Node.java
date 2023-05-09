package fileBuilder.readers.items;

import fileBuilder.readers.items.baseNode.BaseItem;
import fileBuilder.readers.items.baseNode.Item;

import java.util.LinkedHashMap;

/**
 * <a href = "https://ffxiv.consolegameswiki.com/wiki/Ephemeral_Nodes">Ephemeral_Node page </a>
 *
 * @see BaseItem
 * @see Item
 */
public class Ephemeral_Node extends BaseItem implements Item {
    private final String time, tp;

    /**
     * Used for unsorted Items
     */
    public Ephemeral_Node(String itemName,
                          String zone,
                          String cords,
                          String extra, //End of baseItems

                          String time,
                          String tp
    ) {
        super(itemName, zone, cords, extra);
        this.time = time;
        this.tp = tp;
    }

    /**
     * Used when file is already formatted.
     *
     * @param arr array to input instead of manual input.
     */
    public Ephemeral_Node(String[] arr) {
        super(arr[1], arr[2], arr[3], arr[4]);
        time = arr[5];
        tp = arr[6];
    }

    /**
     * @return ItemType in LinkedHashmap form. Keys represent what the value is. For example, key = level, value = 5.
     */
    @Override
    public LinkedHashMap<String, String> toLinkedHashmap() {
        LinkedHashMap<String, String> lhm = new LinkedHashMap<>(BaseLinkedHashMap());
        lhm.remove("Extra Information");
        lhm.put("Items gained from aetherial reduction", getExtra());
        lhm.put("Time", time);
        lhm.put("Closest Teleport", tp);
        return lhm;
    }

    /**
     * @return Output format as follows after standard items: level,type
     */
    @Override
    public String toString() {
        ItemOutputFormatter itemOutputFormatter = new ItemOutputFormatter(getItemName(), getZone(), getCords(), getExtra());
        itemOutputFormatter.addElements(new String[]{time, tp});
        return itemOutputFormatter.toString();
    }

    public String getTime() {
        return time;
    }

    public String getTp() {
        return tp;
    }
}
