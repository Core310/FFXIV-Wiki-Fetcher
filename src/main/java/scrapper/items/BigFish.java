package scrapper.items;

import java.util.LinkedHashMap;

public class BigFish extends BaseItem implements Item{
    private String fishingHole, ezoraTime, weather, bait, mooch, gathering;

    /**
     * Base constructor that is used for inheritance on its children.
     *
     */
    protected BigFish(String fish,
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
    }// TODO: 7/28/2022 when mapping out, extra = desynthRewards

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
}
