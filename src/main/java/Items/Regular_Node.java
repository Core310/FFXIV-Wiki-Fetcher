package Items;

/**
 * Ussed for regular node locations
 */
public class Regular_Node extends BaseItem {
    private int level;
    private String type;

    /**
     * Default constructor. All arguments map to internal variables.
     * This covers the classes BNT and Miner.
     * When there is more than 1 item, make another of this item (creates mass duplicates).
     * @param level
     * @param type
     * @param zone
     * @param cords
     * @param item
     * @param extra
     */
    public Regular_Node(
            int level,
            String type,
            String zone,
            String cords,
            String item,//When Item is loaded in, if a comma
            //is detected, the Formatter should make several items
            String extra
            ){
        super(zone,cords,item,extra);
        this.level = level;
        this.type = type;
    }

    /**
     * Used as a super constructor for Unspoiled node (as it doesn't take Type as a param)
     * So only diff than public constructor is that has no Type argument.
     *
     * @param zone
     * @param cords
     * @param item
     * @param extra
     */
    protected Regular_Node(
            String zone,
            String cords,
            String item,//When Item is loaded in, if a comma
            //is detected, the Formatter should make
            int level,
            String extra
    ){
        super(zone,cords,item,extra);
        this.level = level;
    }

}
