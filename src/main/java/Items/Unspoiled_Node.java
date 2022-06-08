package Items;

public class Unspoiled_Node extends Regular_Node implements Item {
    //The diff between this and Regular Node is the following:
    //Time, Item, Slot, Location, Cords, Level, Star, Add info
    private int slot;
    private int star;
    String time;

    /**
     * Default and (should be) only constructor for Unspoiled.
     * If no level is found, input -1 for the level argument.
     * todo check if null works instead of -1.
     * @param time
     * @param item
     * @param slot
     * @param location
     * @param cords
     * @param level
     * @param star
     * @param info
     */
    public Unspoiled_Node(String time, String item, int slot, String location,
                          String cords, int level, int star, String info) {
        super(level, location, cords, item, info);
        this.slot = slot;
        this.star = star;

    }
    //todo add construcotr that doesnt include level somehow?

    //Getters


    public String getTime() {
        return time;
    }

    public int getStar() {
        return star;
    }

    public int getSlot() {
        return slot;
    }
}
