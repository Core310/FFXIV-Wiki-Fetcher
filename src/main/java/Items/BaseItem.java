package Items;

/**
 * The constructor should NEVER be called.
 * This class is only used for overriding and inheritance.
 */
public abstract class BaseItem {
       private String zone; //aka location
       private String cords;
       private String itemName;
       private String extra;

       /**
        * Base constructor that is used for inheritance on its children.
        * Used as an abstract class
        * @param zone
        * @param cords
        * @param itemName
        * @param extra
        */
       protected BaseItem(String zone, String cords, String itemName, String extra){
              this.zone = zone;
              this.cords = cords;
              this.itemName = itemName;
              this.extra = extra;
       }

       //Getters are only needed on parent classes for the toString methods
    public String getCords() {
        return cords;
    }

    public String getExtra() {
        return extra;
    }

    public String getItemName() {
        return itemName;
    }

    public String getZone() {
        return zone;
    }
}