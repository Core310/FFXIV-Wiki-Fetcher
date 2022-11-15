import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

public class DuplicateTest {
    FindItem fi = new FindItem();
    @org.junit.jupiter.api.Test
    void duplicateTest(){

        assertEquals(
                "[{Item=Shark Tuna, Zone=Eastern La Noscea , Coordinates=X:32, Y:29, Bait Used=Spoon Worm, Northern Krill, Yumizuno, Heavy Steel Jig, Herring Ball, Sinking Minnow, Steel Jig, Shrimp Cage Feeder, Crab Ball, Rat Tail, Saltwater Boilie, Versatile Lure, Type=Ocean fishing, Fishing Log=Fishing Log: Costa del Sol, Level=30, Fishing Hole=Costa del Sol, Time=7 PM to 9 PM, Weather=Clear Skies, Mooch=Fullmoon Sardine, Gathering=339+}]"
                ,
                fi.findAllClosestAsMap("Shark Tuna").toString());


        assertEquals(//TODO 14/10/2022
                "[{Item=Crayfish, Zone=Middle La Noscea , Coordinates=X:20, Y:18, Bait Used=Floating Minnow, Moth Pupa, Midge Basket, Spinnerbait, Bass Ball, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure, Type=Freshwater fishing, Fishing Log=Fishing Log: West Agelyss River, Level=1}, {Item=Crayfish, Zone=Lower La Noscea , Coordinates=X:24, Y:23, Bait Used=Floating Minnow, Moth Pupa, Midge Basket, Sinking Minnow, Butterworm, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure, Type=Freshwater fishing, Fishing Log=Fishing Log: The Mourning Widow, Level=1}, {Item=Crayfish, Zone=New Gridania , Coordinates=X:12, Y:13, Bait Used=Floating Minnow, Moth Pupa, Midge Basket, Stem Borer, Glowworm, Caddisfly Larva, Snurble Fly, Chocobo Fly, Bass Ball, Butterworm, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure, Type=Freshwater fishing, Fishing Log=Fishing Log: Jadeite Flood, Level=5}, {Item=Crayfish, Zone=Old Gridania , Coordinates=X:11, Y:8, Bait Used=Floating Minnow, Moth Pupa, Midge Basket, Glowworm, Caddisfly Larva, Spinnerbait, Chocobo Fly, Bass Ball, Butterworm, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure, Type=Freshwater fishing, Fishing Log=Fishing Log: Upper Black Tea Brook, Level=5}, {Item=Crayfish, Zone=Central Shroud , Coordinates=X:22, Y:21, Bait Used=Moth Pupa, Mythril Spoon Lure, Honey Worm, Sinking Minnow, Chocobo Fly, Crow Fly, Crayfish Ball, Versatile Lure, Type=Freshwater fishing, Fishing Log=Fishing Log: The Vein, Level=5}, {Item=Crayfish, Zone=Western Thanalan , Coordinates=X:17, Y:15, Bait Used=Floating Minnow, Moth Pupa, Midge Basket, Glowworm, Caddisfly Larva, Spinnerbait, Rainbow Spoon Lure, Wildfowl Fly, Chocobo Fly, Bass Ball, Butterworm, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure, Type=Freshwater fishing, Fishing Log=Fishing Log: The Footfalls, Level=10}]"
                ,fi.findAllClosestAsMap("crayon fish").toString());

    }

    /**
     * Test  method to search the file for certain values.
     */
    private  void searchFile(String itemnam) {
        FindItem findItem = new FindItem();
        ArrayList<StringBuilder> arr;
        //findItem.setNumberOfDuplicateItems(1);
        for(StringBuilder tmp: findItem.essentialFindAllClosestAsMap(itemnam)){
            System.out.println(tmp);
        };
    }
}
