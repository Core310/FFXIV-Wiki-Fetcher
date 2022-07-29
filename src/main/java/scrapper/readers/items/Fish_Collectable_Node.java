package scrapper.readers.items;

import scrapper.readers.items.baseNode.BaseItem;
import scrapper.readers.items.baseNode.Item;

import java.util.LinkedHashMap;

public class Fish_Collectable_Node extends BaseItem implements Item {
    private final String minCollectability,catchMethod, timeWeather,scripts;

    /**
     * Base constructor that is used for inheritance on its children.
     *
     */
    public Fish_Collectable_Node(String itemName,
                                 String zone,
                                 String cords,
                                 String extra, //End of base item super class)

                                 String minCollectability,
                                 String catchMethod,
                                 String timeWeather,
                                 String scripts)
    {
        super(itemName, zone, cords, extra);
        this.minCollectability = minCollectability;
        this.catchMethod = catchMethod;
        this.timeWeather = timeWeather;
        this.scripts = scripts;
    }

    public Fish_Collectable_Node(String[] arr) {
        super(arr[1],arr[2],arr[3],arr[4]);
        minCollectability = arr[5];
        catchMethod = arr[6];
        timeWeather = arr[7];
        scripts = arr[8];
    }

    /**
     * @return ItemType in LinkedHashmap form. Keys represent what the value is. For example, key = level, value = 5.
     */
    @Override
    public LinkedHashMap<String, String> toLinkedHashmap() {
        LinkedHashMap<String,String> lhm = new LinkedHashMap<>(BaseLinkedHashMap());
        lhm.put("Minimum Collectability",minCollectability);
        lhm.put("Catch Method",catchMethod);
        lhm.put("Time and Weather",timeWeather);
        lhm.put("Scripts",scripts);
        return lhm;
    }

    @Override
    public String toString() {
        ItemOutputFormatter itemOutputFormatter = new ItemOutputFormatter(getItemName(),getZone(),getCords(),getExtra());
        itemOutputFormatter.addElements(new String[]{minCollectability,catchMethod,timeWeather,scripts});
        return itemOutputFormatter.toString();
    }
}
