package scrapper.readers.items;

import scrapper.readers.items.baseNode.BaseItem;
import scrapper.readers.items.baseNode.Item;

import java.util.LinkedHashMap;

/**
 * @see scrapper.readers.items.Ephemeral_Node 
 * @see scrapper.readers.items.baseNode.BaseItem 
 */
public class Ephemeral_Fish_Node extends BaseItem implements Item {
    private final String conditions,tp,weather, bait;
    
    public Ephemeral_Fish_Node(String fish, String zone, String cords, String extra,
                               String tp, String weather, String bait, String conditions
                                  ) {
        super(fish, zone, cords, extra);
        this.tp = tp;
        this.weather = weather;
        this.bait = bait;
        this.conditions = conditions;
    }

    /**
     * Used when file is already formatted.
     * @param arr array to input instead of manual input.
     */
    public Ephemeral_Fish_Node(String[] arr) {
        super(arr[1],arr[2],arr[3],arr[4]);
        tp = arr[5];
        weather = arr[6];
        bait = arr[7];
        conditions = arr[8];
    }
    

    /**
     * @return ItemType in LinkedHashmap form. Keys represent what the value is. For example, key = level, value = 5.
     */
    @Override
    public LinkedHashMap<String, String> toLinkedHashmap() {
        LinkedHashMap<String,String> lhm = new LinkedHashMap<>(BaseLinkedHashMap());
        lhm.remove("Extra Information");
        lhm.put("Items gained from aetherial reduction",getExtra());
        lhm.put("Closest Teleport",tp);
        lhm.put("Weather",weather);
        lhm.put("Bait",bait);
        lhm.put("Conditions",conditions);
        return lhm;
    }

    /**
     *
     * @return Output format as follows after standard items: level,type
     */
    @Override
    public String toString() {
        ItemOutputFormatter itemOutputFormatter = new ItemOutputFormatter(getItemName(),getZone(),getCords(),getExtra());
        itemOutputFormatter.addElements(new String[]{tp,weather,bait,conditions});
        return itemOutputFormatter.toString();
    }
}
