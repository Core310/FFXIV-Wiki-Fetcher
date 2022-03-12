package Items;

/**
 * Ussed for regular node locations
 */
public class Regular_Node implements Item {
    private String ItemName;
    private String TP;
    private int Level;
    private String WikiLink;//Official wiki
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
            String ItemName,
            String TP,
            int Level,
            String WikiLink,
            String extra
    ){
      this.ItemName = ItemName;
      this.TP = TP;
      this.Level = Level;
      this.WikiLink = WikiLink;
      this.extra = extra;
    }

    /**
     * @return all internal private values
     */
    @Override
    public String toString() {
        return "Items.Regular_Node{" +
                "ItemName='" + ItemName + '\'' +
                ", TP='" + TP + '\'' +
                ", Level=" + Level +
                ", WikiLink='" + WikiLink + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }

    public int getLevel() {
        return Level;
    }

    public String getItemName() {
        return ItemName;
    }

    public String getTP() {
        return TP;
    }

    public String getWikiLink() {
        return WikiLink;
    }

    public String getExtra() {
        return extra;
    }

}
