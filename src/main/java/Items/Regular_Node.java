package Items;

/**
 * Ussed for regular node locations
 */
public class Regular_Node extends BaseItem {
    private final int level;
    private String type;
    /**
     * Default constructor. All arguments map to internal variables.
     * This covers the classes BNT and Miner.
     * When there is more than 1 item, make another of this item (creates mass duplicates).
     * @param item
     * @param zone
     * @param cords
     * @param extra
     * @param level
     * @param type
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
     * @param item
     * @param zone
     * @param cords
     * @param extra
     * @param level
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


    /**
     *
     * @return Output format as follows after standard items: level,type
     */
    @Override
    public String toString() {
        ItemOutputFormatter itemOutputFormatter = new ItemOutputFormatter(getItemName(),getZone(),getCords(),getExtra());
        itemOutputFormatter.addElement(String.valueOf(level));
        itemOutputFormatter.addElement(type);
        return itemOutputFormatter.toString();
    }

    public int getLevel() {
        return level;
    }

    public String getType() {
        return type;
    }
}
