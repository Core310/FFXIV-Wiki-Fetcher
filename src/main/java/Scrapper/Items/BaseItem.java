package Scrapper.Items;

/**
 * The constructor should NEVER be called.
 * This class is only used for overriding and inheritance.
 *
 * The first 3 columns should be as follows: itemName,zone,cords
 *
 * All methods/vars are either private or protected.
 */
@SuppressWarnings("all")
public abstract class BaseItem {
       private final String zone; //aka location
       private final String cords;
       private final String itemName;
       private final String extra;

       /**
        * Base constructor that is used for inheritance on its children.
        * Used as an abstract class
        * @param zone
        * @param cords
        * @param itemName
        * @param extra
        */
       protected BaseItem(String itemName, String zone, String cords, String extra){
           this.itemName = itemName;
           this.zone = zone;
           this.cords = cords;
           this.extra = extra;
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