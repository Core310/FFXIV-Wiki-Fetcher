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
     * @param Level
     * @param Type
     * @param Zone
     * @param Cords
     * @param Item
     * @param Extra
     */
    public Regular_Node(
            int Level,
            String Type,
            String Zone,
            String Cords,
            String Item,//When Item is loaded in, if a comma
            //is detected, the Formatter should make
            String Extra
            ){
        this.Level = Level;
        this.Type = Type;
        this.Zone = Zone;
        this.Cords = Cords;
        this.ItemName = Item;
        this.extra = Extra;
    }

    /**
     * Used as a super constructor for Unspoiled node (as it doesn't take Type as a param)
     * So only diff than public constructor is that has no Type argument.
     *
     * @param Level
     * @param Zone
     * @param Cords
     * @param Item
     * @param Extra
     */
    protected Regular_Node(
            int Level,
            String Zone,
            String Cords,
            String Item,//When Item is loaded in, if a comma
            //is detected, the Formatter should make
            String Extra
    ){
        this.Level = Level;
        this.Zone = Zone;
        this.Cords = Cords;
        this.ItemName = Item;
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
