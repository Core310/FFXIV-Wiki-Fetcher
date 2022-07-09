package Items;

public class Unspoiled_Node extends Regular_Node  {
    //The diff between this and Regular Node is the following:
    //Time, Item, Slot, Location, Cords, Level, Star, Add info
    private int slot;
    private int star;
    private String time;

    /**
     *      * Default and (should be) only constructor for Unspoiled.
     *      * If no level is found, input -1 for the level argument.
     *      * todo check if null works instead of -1.
     * @param item
     * @param location
     * @param cords
     * @param info
     * @param time
     * @param slot
     * @param level
     * @param star
     */
    public Unspoiled_Node(
            String item,
            String location,
            String cords,
            String info,//Base Items

            String time,
            int slot,
            int level,
            int star
    ) {
        super(item,location,cords,info,level);
        this.slot = slot;
        this.star = star;
        this.time = time;
    }

    @Override
    public String toString() {
        ItemOutputFormatter itemOutputFormatter = new ItemOutputFormatter(getItemName(),getZone(),getCords(),getExtra());
        itemOutputFormatter.addElement(String.valueOf(getLevel()));
        itemOutputFormatter.addElement(getType());
        itemOutputFormatter.addElement(String.valueOf(slot));
        itemOutputFormatter.addElement(String.valueOf(star));
        itemOutputFormatter.addElement(time);
        return itemOutputFormatter.toString();
    }
}
