package fileBuilder.readers.items.baseNode;

import java.util.LinkedHashMap;

/**
 * The constructor should NEVER be called directly.
 * <p>This class is only used for overriding and inheritance.
 *
 * <p>The first 4 columns/inputs should be as follows: itemName,zone,cords,extra
 *
 * <p>All methods/vars are either private or protected.
 *
 * @see fileBuilder.fileCreator.Formatter
 */
@SuppressWarnings("all")
public abstract class BaseItem {
    private final String zone, cords, itemName, extra; //zone aka location

    /**
     * Base constructor that is used for inheritance on its children.
     * Used as an abstract class
     *
     * @param zone
     * @param cords
     * @param itemName
     * @param extra
     */
    protected BaseItem(String itemName, String zone, String cords, String extra) {
        this.itemName = itemName;
        this.zone = zone;
        this.cords = cords;
        this.extra = extra;
    }

    /**
     * Used to support ITEM classes (such as Regular_Node)
     *
     * @return Constructor arguments with a header for value name.
     */
    protected LinkedHashMap<String, String> BaseLinkedHashMap() {
        LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
        lhm.put("Item", getItemName());
        lhm.put("Zone", getZone());
        lhm.put("Coordinates", getCords());
        if (getExtra() != "")
            lhm.put("Extra Information", getExtra());
        return lhm;
    }

    //Getters are only needed on parent classes for the toString methods
    protected String getCords() {
        return cords;
    }

    protected String getExtra() {
        return extra;
    }

    protected String getItemName() {
        return itemName;
    }

    protected String getZone() {
        return zone;
    }
}