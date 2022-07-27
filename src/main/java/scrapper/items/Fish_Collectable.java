package scrapper.items;

import java.util.LinkedHashMap;

public class Fish_Collectable extends BaseItem implements Item{
    private String minCollectability,catchMethod, timeWeather,scripts;

    /**
     * Base constructor that is used for inheritance on its children.
     *
     */
    protected Fish_Collectable(String itemName,
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
}
