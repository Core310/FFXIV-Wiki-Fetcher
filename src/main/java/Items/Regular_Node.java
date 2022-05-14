package Items;

/**
 * Ussed for regular node locations
 */
public class Regular_Node implements Item {
    private int Level;
    private String Type;
    private String Zone;
    private String Cords;
    private String ItemName;
    private String extra;

    /**
     * Default constructor. All arguments map to internal variables.
     * This covers the classes BNT and Miner
     * @param ItemName
     * @param TP
     * @param Level
     * @param WikiLink
     * @param extra
     */
    public Regular_Node(
            int Level,
            String Type,
            String Zone,
            String Cords,
            String Items,
            String Extra
            ){
        //            case "Level\tType\tZone\tCoordinate\tItems\tExtra\n": {
      this.ItemName = ItemName;
      this.Zone = Zone;
      this.Level = Level;
      this.WikiLink = WikiLink;
      this.extra = extra;
    }


    public int getLevel() {
        return Level;
    }

    public String getItemName() {
        return ItemName;
    }

    public String getZone() {
        return Zone;
    }

    public String getWikiLink() {
        return WikiLink;
    }

    public String getExtra() {
        return extra;
    }

}
