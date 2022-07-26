package scrapper.items;

import java.util.LinkedHashMap;

public class Fishing extends BaseItem implements Item{
    /**
     * Base constructor that is used for inheritance on its children.
     *
     */
    protected Fishing(String itemName,
                      String zone,
                      String cords,
                      String extra //End of base Items

    ) {
        super(itemName, zone, cords, extra);


    }

    /**
     * @return ItemType in LinkedHashmap form. Keys represent what the value is. For example, key = level, value = 5.
     */
    @Override
    public LinkedHashMap<String, String> toLinkedHashmap() {
        return null;
    }// TODO: 7/26/2022


}
