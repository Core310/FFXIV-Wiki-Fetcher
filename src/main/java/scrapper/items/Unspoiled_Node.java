package scrapper.items;

import java.util.LinkedHashMap;

public class Unspoiled_Node extends Regular_Node implements Item{
    //The diff between this and Regular Node is the following:
    //Time, Item, Slot, Location, Cords, Level, Star, Add info
    private final int slot,star;
    private final String time;

    /**
     *      * Default constructor for Unspoiled.
     *      * If no level is found, use alternate constructor
     */
    public Unspoiled_Node(
            String item,
            String location,
            String cords,
            String info,//Base Items

            String time,
            int slot,
            int level,
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

    public Unspoiled_Node(String[] arr) {
        super(arr[1],arr[2],arr[3],arr[4], Integer.parseInt(arr[5]));
        if(arr[6].equals("n/a"))
            slot = -1;
        else
            slot = Integer.parseInt(arr[6]);
        star = Integer.parseInt(arr[7]);
        time = arr[8];
    }

    @Override
    public String toString() {
        ItemOutputFormatter itemOutputFormatter = new ItemOutputFormatter(getItemName(),getZone(),getCords(),getExtra());
        itemOutputFormatter.addElement(String.valueOf(getLevel()));
        itemOutputFormatter.addElement(getType());
        itemOutputFormatter.addElement(String.valueOf(slot));
        itemOutputFormatter.addElement(String.valueOf(star));
        itemOutputFormatter.addElement(time);
        return itemOutputFormatter.toString();
    }

    /**
     * @return ItemType in LinkedHashmap form. Keys represent what the value is. For example, key = level, value = 5.
     */
    @Override
    public LinkedHashMap<String, String> toLinkedHashmap() {
        LinkedHashMap<String, String> lhm = new LinkedHashMap<>(BaseLinkedHashMap());
        lhm.put("Time",time);
        lhm.put("Slot", String.valueOf(slot));
        lhm.put("Star", String.valueOf(star));
        return lhm;
    }

}
