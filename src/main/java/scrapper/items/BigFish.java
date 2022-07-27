package scrapper.items;

import java.util.LinkedHashMap;

public class BigFish extends BaseItem implements Item{


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
    }// TODO: 7/28/2022 when mapping out, extra = desynthRewards

    /**
     * @return ItemType in LinkedHashmap form. Keys represent what the value is. For example, key = level, value = 5.
     */
    @Override
    public LinkedHashMap<String, String> toLinkedHashmap() {
        return null;
    }
}
