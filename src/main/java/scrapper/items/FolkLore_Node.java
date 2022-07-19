package scrapper.items;

/**
 * Only difference is an int, slot
 * extends FolkLoreFishing as its base class
 */
public class FolkLore_Node extends FolkLore_Fishing implements Item{

    private final int slot;
    public FolkLore_Node(
            String item,
            String location,
            String cords,
            String additionalInfo, //Base Item stuff

            String folkloreTome,
            String time,
            int slot
    ){
        super(folkloreTome,time,item,location,cords,additionalInfo);
        this.slot = slot;

    }

    /**
     *
     * @return Output format as follows after standard items: time,folkloreTome, slot
     */
    @Override
    public String toString() {
        ItemOutputFormatter itemOutputFormatter = new ItemOutputFormatter(getItemName(),getZone(),getCords(),getExtra());
        itemOutputFormatter.addElement(getTime()); //this is from the extended class
        itemOutputFormatter.addElement(getFolkloreTome());//This is from the extended class
        itemOutputFormatter.addElement(String.valueOf(slot));
        return itemOutputFormatter.toString();
    }
}
