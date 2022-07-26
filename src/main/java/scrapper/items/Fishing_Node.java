package scrapper.items;

import java.util.LinkedHashMap;

public class Fishing_Node extends BaseItem implements Item{
    private String fishingLog, type, fish;

    /**
     * Base constructor that is used for inheritance on its children.
     *
     */
    public Fishing_Node(String itemName,
                        String zone,
                        String cords,
                        String extra,//In the case of fishing called Bait Used
                        //End of base items
                        String type,
                        String fishingLog,
                        String fish

    ) {
        super(itemName, zone, cords, extra);
        this.type = type;
        this.fishingLog = fishingLog;
        this.fish = fish;
    }

    /**
     * Used when file is already formatted.
     * @param arr array to input instead of manual input.
     */
    public Fishing_Node(String[] arr) {
        super("a","b","c","d");//delete this line
        // TODO: 7/26/22 once I find out how the item is exactly formatted.
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
        lhm.remove("Fish",fish);
        return lhm;
    }

    /**
     *
     * @return Output format as follows after standard items: time,folkloreTome, slot
     */
    @Override
    public String toString() {
        ItemOutputFormatter itemOutputFormatter = new ItemOutputFormatter(getItemName(),getZone(),getCords(),getExtra());
        itemOutputFormatter.addElement(type);
        itemOutputFormatter.addElement(fishingLog);
        itemOutputFormatter.addElement(fishingLog);
        return itemOutputFormatter.toString();
    }
}
