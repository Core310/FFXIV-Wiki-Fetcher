package scrapper.items;

import java.util.LinkedHashMap;

/**
 * Only difference is an int, slot
 * extends FolkLoreFishing as its base class
 */
public class FolkLore_Node extends FolkLore_Fishing implements Item{

    private final int slot;
    public FolkLore_Node(
            String item,
            String location,
            String cords,
            String additionalInfo, //Base Item stuff

            String folkloreTome,
            String time,
            int slot
    ){
        super(item,location,cords,additionalInfo,folkloreTome,time);
        this.slot = slot;
    }

    public FolkLore_Node(String[] arr) {
        super(arr[1],arr[2],arr[3],arr[4],arr[5],arr[6]);
        slot = Integer.parseInt(arr[7]);
    }

    /**
     *
     * @return Output format as follows after standard items: time,folkloreTome, slot
     */
    @Override
    public String toString() {
        ItemOutputFormatter itemOutputFormatter = new ItemOutputFormatter(getItemName(),getZone(),getCords(),getExtra());
        itemOutputFormatter.addElement(getTime()); //this is from the extended class
        itemOutputFormatter.addElement(getFolkloreTome());//This is from the extended class
        itemOutputFormatter.addElement(String.valueOf(slot));
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
        lhm.put("Slot", String.valueOf(slot));
        return lhm;
    }
}
