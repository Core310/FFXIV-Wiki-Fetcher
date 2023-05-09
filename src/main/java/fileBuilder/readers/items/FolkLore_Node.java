package fileBuilder.readers.items;

import fileBuilder.readers.items.baseNode.Item;

import java.util.LinkedHashMap;

/**
 * Only difference is an int, slot
 * extends FolkLoreFishing as its base class
 * <a href="https://ffxiv.consolegameswiki.com/wiki/Big_Fishing">wiki link</a>
 *
 * @see FolkLore_Fish_Node
 * @see fileBuilder.readers.items.baseNode.BaseItem
 */
public class FolkLore_Node extends FolkLore_Fish_Node implements Item {

    private final int slot;

    public FolkLore_Node(
            String item,
            String location,
            String cords,
            String additionalInfo, //Base Item stuff

            String folkloreTome,
            String time,
            int slot
    ) {
        super(item, location, cords, additionalInfo, folkloreTome, time);
        this.slot = slot;
    }

    /**
     * Used when file is already formatted.
     *
     * @param arr array to input instead of manual input.
     */
    public FolkLore_Node(String[] arr) {
        super(arr[1], arr[2], arr[3], arr[4], arr[5], arr[6]);
        slot = Integer.parseInt(arr[7]);
    }

    /**
     * @return Output format as follows after standard items: time,folkloreTome, slot
     */
    @Override
    public String toString() {
        ItemOutputFormatter itemOutputFormatter = new ItemOutputFormatter(getItemName(), getZone(), getCords(), getExtra());
        itemOutputFormatter.addElements(new String[]{getFolkloreTome(), getTime(), String.valueOf(slot)}); //this is from the extended class
        return itemOutputFormatter.toString();
    }

    /**
     * @return ItemType in LinkedHashmap form. Keys represent what the value is. For example, key = level, value = 5.
     */
    @Override
    public LinkedHashMap<String, String> toLinkedHashmap() {
        LinkedHashMap<String, String> lhm = new LinkedHashMap<>(BaseLinkedHashMap());
        lhm.remove("Extra Information");//In folklore, this is called used to make instead of extra info
        //see here: https://ffxiv.consolegameswiki.com/wiki/Folklore_Nodes#Miner
        lhm.put("Used to make", getExtra());
        lhm.put("FolkLore Tome", getFolkloreTome());
        lhm.put("Time", getTime());
        lhm.put("Slot", String.valueOf(slot));
        return lhm;
    }
}
