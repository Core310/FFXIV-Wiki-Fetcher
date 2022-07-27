package scrapper.items;

import java.util.LinkedHashMap;

/**
 * Ussed for regular node locations
 */
public class Regular_Node extends BaseItem implements Item{
    private final int level;
    private final String type;
    /**
     * Default constructor. All arguments map to internal variables.
     * This covers the classes BNT and Miner.
     * When there is more than 1 item, make another of this item (creates mass duplicates).
     */
    public Regular_Node(
            String item,//When Item is loaded in, if a comma
            //is detected, the Formatter should make several items
            String zone,
            String cords,
            String extra, //Base Items

            int level,
            String type
            ){
        super(item,zone,cords,extra);
        this.level = level;
        this.type = type;
    }

    /**
     * Used as a super constructor for Unspoiled node (as it doesn't take Type as a param)
     * So only diff than public constructor is that has no Type argument.
     * Type argument is set to "n/a" by default since there is no type
     */
    protected Regular_Node(
            String item,//When Item is loaded in, if a comma
            //is detected, the Formatter should make several items
            String zone,
            String cords,
            String extra, //Base Items

            int level
    ){
        super(item,zone,cords,extra);
        this.level = level;
        type = "n/a";
    }

    public Regular_Node(String[] arr) {
        super(arr[1],arr[2],arr[3],arr[4]);
        if(arr[5].equals(""))
            level = -1;
        else
            level = Integer.parseInt(arr[5]);
        type = arr[6];
    }


    /**
     *
     * @return Output format as follows after standard items: level,type
     */
    @Override
    public String toString() {
        ItemOutputFormatter itemOutputFormatter = new ItemOutputFormatter(getItemName(),getZone(),getCords(),getExtra());
        itemOutputFormatter.addElements(new String[]{String.valueOf(level),type});
        return itemOutputFormatter.toString();
    }

    public int getLevel() {
        return level;
    }

    public String getType() {
        return type;
    }

    /**
     * @return ItemType in LinkedHashmap form. Keys represent what the value is. For example, key = level, value = 5.
     */
    @Override
    public LinkedHashMap<String, String> toLinkedHashmap() {
        LinkedHashMap<String,String> lhm = new LinkedHashMap<>(BaseLinkedHashMap());
        lhm.put("Level", String.valueOf(getLevel()));
        return lhm;
    }
}
