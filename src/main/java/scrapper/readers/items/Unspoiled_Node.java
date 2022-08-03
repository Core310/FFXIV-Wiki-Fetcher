package scrapper.readers.items;

import scrapper.readers.items.baseNode.Item;

import java.util.LinkedHashMap;

/**
 * <a href="https://ffxiv.consolegameswiki.com/wiki/Unspoiled_Nodes">Link to all Unspoiled Nodes</a>
 *
 * @see scrapper.readers.items.baseNode.BaseItem
 * @see Regular_Node
 */
public class Unspoiled_Node extends Regular_Node implements Item {
    //The diff between this and Regular Node is the following:
    //Time, Item, Slot, Location, Cords, Level, Star, Add info
    private final int slot,star;
    private final String time;

    /**
     * Default constructor for Unspoiled.
     * If no level is found, use alternate constructor
     */
    public Unspoiled_Node(
            String item,
            String location,
            String cords,
            String info,//Base Items

            int level,
            String time,
            int slot,
            int star
    ) {
        super(item,location,cords,info,level);
        this.slot = slot;
        this.star = star;
        this.time = time;
    }

    /**
     * Constructor used for ARR nodes
     */
    public Unspoiled_Node(
            String item,
            String location,
            String cords,
            String info,//Base Items

            String time,
            int slot,
            int star
    ) {
        super(item,location,cords,info,-1);
        this.slot = slot;
        this.star = star;
        this.time = time;
    }
    /**
     * Used when file is already formatted.
     * @param arr array to input instead of manual input.
     */
    public Unspoiled_Node(String[] arr) {
        super(arr[1],arr[2],arr[3],arr[4], Integer.parseInt(arr[5]));
        time = arr[6];
        slot = Integer.parseInt(arr[7]);
        star = Integer.parseInt(arr[8]);
    }

    @Override
    public String toString() {
        ItemOutputFormatter itemOutputFormatter = new ItemOutputFormatter(getItemName(),getZone(),getCords(),getExtra());
        itemOutputFormatter.addElements(new String[]{String.valueOf(getLevel()),time, String.valueOf(slot), String.valueOf(star)});
        return itemOutputFormatter.toString();
    }

    /**
     * @return ItemType in LinkedHashmap form. Keys represent what the value is. For example, key = level, value = 5.
     */
    @Override
    public LinkedHashMap<String, String> toLinkedHashmap() {
        LinkedHashMap<String, String> lhm = new LinkedHashMap<>(BaseLinkedHashMap());
        lhm.put("Level", String.valueOf(getLevel()));
        lhm.put("Time",time);
        lhm.put("Slot", String.valueOf(slot));
        lhm.put("Star", String.valueOf(star));
        return lhm;
    }

}
