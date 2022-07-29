package scrapper.readers.items;

import scrapper.readers.items.baseNode.BaseItem;
import scrapper.readers.items.baseNode.Item;

import java.util.LinkedHashMap;

public class BigFish_Node extends BaseItem implements Item {
    private String fishingHole, ezoraTime, weather, bait, mooch, gathering;

    /**
     * Base constructor that is used for inheritance on its children.
     *
     */
    public BigFish_Node(String fish,
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

    public BigFish_Node(String[] arr) {
        super("a","b","c","d");//delete this line
        // TODO:7/26/22 once I find out how the item is exactly formatted.
    }

    /**
     * @return ItemType in LinkedHashmap form. Keys represent what the value is. For example, key = level, value = 5.
     */
    @Override
    public LinkedHashMap<String, String> toLinkedHashmap() {
        LinkedHashMap<String,String> lhm = new LinkedHashMap<>(BaseLinkedHashMap());
        lhm.remove("Extra Information");//In fishing, this is called "bait used" instead of extra info
        //see here: https://ffxiv.consolegameswiki.com/wiki/Fishing_Locations
        lhm.put("Desynth Rewards",getExtra());
        lhm.put("Fishing Hole", fishingHole);
        lhm.put("Ezora Time", ezoraTime);
        lhm.put("Weather",weather);
        lhm.put("Bait",bait);
        lhm.put("Mooch",mooch);
        lhm.put("Gathering",gathering);
        return lhm;
    }

    @Override
    public String toString() {
        ItemOutputFormatter itemOutputFormatter = new ItemOutputFormatter(getItemName(),getZone(),getCords(),getExtra());
        itemOutputFormatter.addElements(new String[]{fishingHole,ezoraTime,weather,bait,mooch,gathering});
        return itemOutputFormatter.toString();
    }
}
