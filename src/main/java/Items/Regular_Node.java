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

        this.Level = Level;
        this.Type = Type;
        this.Zone = Zone;
        this.Cords = Cords;
        this.ItemName = Items;
        this.extra = Extra;
    }

    public String getType() {
        return Type;
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

    @Override
    public String getCords() {
        return Cords;
    }

    @Override
    public String toString() {
        return "Regular_Node{" +
                "Level=" + Level +
                ", Type='" + Type + '\'' +
                ", Zone='" + Zone + '\'' +
                ", Cords='" + Cords + '\'' +
                ", ItemName='" + ItemName + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }

    public String getExtra() {
        return extra;
    }

}
