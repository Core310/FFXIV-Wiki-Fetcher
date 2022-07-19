package scrapper.items;

public class Unspoiled_Node extends Regular_Node  {
    //The diff between this and Regular Node is the following:
    //Time, Item, Slot, Location, Cords, Level, Star, Add info
    private final int slot;
    private final int star;
    private final String time;

    /**
     *      * Default constructor for Unspoiled.
     *      * If no level is found, use alternate constructor
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

    /**
     * Constructor used for ARR nodes
     */
    public Unspoiled_Node(
            String item,
            String location,
            String cords,
            String info,//Base Items

            String time,
            int slot,
            int star
    ) {
        super(item,location,cords,info,-1);
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
