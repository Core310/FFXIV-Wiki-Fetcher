package fileBuilder.readers.items;

import fileBuilder.readers.items.baseNode.BaseItem;
import fileBuilder.readers.items.baseNode.Item;

import java.util.LinkedHashMap;

/**
 * <a href="https://ffxiv.consolegameswiki.com/wiki/Folklore_Nodes#Fisher">Folklore fishing link</a>
 *
 * @see BaseItem
 */
public class Fish_Big_Node extends BaseItem implements Item {
    private final String fishingHole, ezoraTime, weather, bait, mooch, gathering;

    /**
     * Base constructor that is used for inheritance on its children.
     */
    public Fish_Big_Node(String fish,
                         String zone,
                         String cords,
                         String desynthRewards,//aka extraEnd of base item super class

                         String fishingHole,
                         String ezoraTime,
                         String weather,
                         String bait,
                         String mooch,
                         String gathering
    ) {
        super(fish, zone, cords, desynthRewards);
        this.fishingHole = fishingHole;
        this.ezoraTime = ezoraTime;
        this.weather = weather;
        this.bait = bait;
        this.mooch = mooch;
        this.gathering = gathering;
    }

    /**
     * Used when file is already formatted.
     *
     * @param arr array to input instead of manual input.
     */
    public Fish_Big_Node(String[] arr) {
        super(arr[1], arr[2], arr[3], arr[4]);
        fishingHole = arr[5];
        ezoraTime = arr[6];
        weather = arr[7];
        bait = arr[8];
        mooch = arr[9];
        gathering = arr[10];

    }

    /**
     * @return ItemType in LinkedHashmap form. Keys represent what the value is. For example, key = level, value = 5.
     */
    @Override
    public LinkedHashMap<String, String> toLinkedHashmap() {
        LinkedHashMap<String, String> lhm = new LinkedHashMap<>(BaseLinkedHashMap());
        lhm.remove("Extra Information");//In fishing, this is called "bait used" instead of extra info
        //see here: https://ffxiv.consolegameswiki.com/wiki/Fishing_Locations
        lhm.put("Desynth Rewards", getExtra());
        lhm.put("Fishing Hole", fishingHole);
        lhm.put("Time", ezoraTime);
        lhm.put("Weather", weather);
        lhm.put("Bait", bait);
        lhm.put("Mooch", mooch);
        lhm.put("Gathering", gathering);
        return lhm;
    }

    @Override
    public String toString() {
        ItemOutputFormatter itemOutputFormatter = new ItemOutputFormatter(getItemName(), getZone(), getCords(), getExtra());
        itemOutputFormatter.addElements(new String[]{fishingHole, ezoraTime, weather, bait, mooch, gathering});
        return itemOutputFormatter.toString();
    }
}
