package Items;

public class UnspoiledNode extends Regular_Node implements Item {


    /**
     * Default constructor. All arguments map to internal variables.
     * This covers the classes BNT and Miner
     *
     * @param Level
     * @param Type
     * @param Zone
     * @param Cords
     * @param Items
     * @param Extra
     */
    public UnspoiledNode(int Level, String Type, String Zone, String Cords, String Items, String Extra) {
        super(Level, Type, Zone, Cords, Items, Extra);
    }
}
