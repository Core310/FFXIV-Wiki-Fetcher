package scrapper.readers.items;

import scrapper.readers.items.baseNode.Item;

import java.util.LinkedHashMap;

/**
 * @see scrapper.readers.items.Ephemeral_Node 
 * @see scrapper.readers.items.baseNode.BaseItem 
 */
public class Ephemeral_Fish_Node extends Ephemeral_Node implements Item {
    private final String weather, bait;
    
    public Ephemeral_Fish_Node(String fish, String zone, String cords, String extra,
                                  String time, String tp, String weather, String bait
                                  ) {
        super(fish, zone, cords, extra,time,tp);
        this.weather = weather;
        this.bait = bait;
    }

    /**
     * Used when file is already formatted.
     * @param arr array to input instead of manual input.
     */
    public Ephemeral_Fish_Node(String[] arr) {
        super(arr[1],arr[2],arr[3],arr[4], arr[5],arr[6]);
        weather = arr[7];
        bait = arr[8];
    }
    

    /**
     * @return ItemType in LinkedHashmap form. Keys represent what the value is. For example, key = level, value = 5.
     */
    @Override
    public LinkedHashMap<String, String> toLinkedHashmap() {
        LinkedHashMap<String,String> lhm = new LinkedHashMap<>(BaseLinkedHashMap());
        lhm.remove("Extra Information");
        lhm.put("Items gained from aetherial reduction",getExtra());
        lhm.put("Time", getTime());
        lhm.put("Closest Teleport",getTp());
        lhm.put("Weather",weather);
        lhm.put("Bait",bait);
        return lhm;
    }

    /**
     *
     * @return Output format as follows after standard items: level,type
     */
    @Override
    public String toString() {
        ItemOutputFormatter itemOutputFormatter = new ItemOutputFormatter(getItemName(),getZone(),getCords(),getExtra());
        itemOutputFormatter.addElements(new String[]{getTime(),getTp(),weather,bait});
        return itemOutputFormatter.toString();
    }
}
