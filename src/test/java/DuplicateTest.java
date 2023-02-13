import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * See if any duplicate items appear when searching for a singular item.
 */
public class DuplicateTest {
    private final FindItem fi = new FindItem();

    private String formatter(String str) {
        return fi.findAllClosestAsMap(str).toString();
    }

    @org.junit.jupiter.api.Test
    void duplicateTest() {
        assertEquals(
                "[{Item=Shark Tuna, Zone=Eastern La Noscea , Coordinates=X:32, Y:29, Bait Used=Spoon Worm, Northern Krill, Yumizuno, Heavy Steel Jig, Herring Ball, Sinking Minnow, Steel Jig, Shrimp Cage Feeder, Crab Ball, Rat Tail, Saltwater Boilie, Versatile Lure, Type=Ocean fishing, Fishing Log=Fishing Log: Costa del Sol, Level=30, Fishing Hole=Costa del Sol, Time=7 PM to 9 PM, Weather=Clear Skies, Mooch=Fullmoon Sardine, Gathering=339+}]"
                ,
                formatter("Shark Tuna"));

        assertEquals(
                "[{Item=Crayfish, Zone=Middle La Noscea , Coordinates=X:20, Y:18, Bait Used=Floating Minnow, Moth Pupa, Midge Basket, Spinnerbait, Bass Ball, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure, Type=Freshwater fishing, Fishing Log=Fishing Log: West Agelyss River, Level=1}, {Item=Crayfish, Zone=Lower La Noscea , Coordinates=X:24, Y:23, Bait Used=Floating Minnow, Moth Pupa, Midge Basket, Sinking Minnow, Butterworm, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure, Type=Freshwater fishing, Fishing Log=Fishing Log: The Mourning Widow, Level=1}, {Item=Crayfish, Zone=New Gridania , Coordinates=X:12, Y:13, Bait Used=Floating Minnow, Moth Pupa, Midge Basket, Stem Borer, Glowworm, Caddisfly Larva, Snurble Fly, Chocobo Fly, Bass Ball, Butterworm, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure, Type=Freshwater fishing, Fishing Log=Fishing Log: Jadeite Flood, Level=5}, {Item=Crayfish, Zone=Old Gridania , Coordinates=X:11, Y:8, Bait Used=Floating Minnow, Moth Pupa, Midge Basket, Glowworm, Caddisfly Larva, Spinnerbait, Chocobo Fly, Bass Ball, Butterworm, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure, Type=Freshwater fishing, Fishing Log=Fishing Log: Upper Black Tea Brook, Level=5}, {Item=Crayfish, Zone=Central Shroud , Coordinates=X:22, Y:21, Bait Used=Moth Pupa, Mythril Spoon Lure, Honey Worm, Sinking Minnow, Chocobo Fly, Crow Fly, Crayfish Ball, Versatile Lure, Type=Freshwater fishing, Fishing Log=Fishing Log: The Vein, Level=5}, {Item=Crayfish, Zone=Western Thanalan , Coordinates=X:17, Y:15, Bait Used=Floating Minnow, Moth Pupa, Midge Basket, Glowworm, Caddisfly Larva, Spinnerbait, Rainbow Spoon Lure, Wildfowl Fly, Chocobo Fly, Bass Ball, Butterworm, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure, Type=Freshwater fishing, Fishing Log=Fishing Log: The Footfalls, Level=10}]"
                , formatter("crayon fish"));

        assertEquals(
                "[{Item=Waterfowl Feather (Rare), Zone=Western La Noscea, Coordinates=(x34,y28), Level=-1, Time=8:00 AM, Slot=1, Star=2}]",
                formatter("Waterfowl Feather (Rare)")
        );
        assertEquals("[{Item=Inkfish, Zone=The Sea of Clouds, Coordinates=(x29,y35), FolkLore Tome=Abalathian, Time=2PM-4PM, Fishing Hole=Voor Sian Siran, Weather=Any, Bait=Brute Leech, Mooch=, Gathering=}, {Item=Cupfish, Zone=Lower La Noscea , Coordinates=X:27, Y:15, Bait Used=Moth Pupa, Glowworm, Spinnerbait, Honey Worm, Sinking Minnow, Syrphid Basket, Chocobo Fly, Butterworm, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure, Type=Freshwater fishing, Fishing Log=Fishing Log: Blind Iron Mines, Level=20, Fishing Hole=Blind Iron Mines, Time=5 PM to 7 PM, Weather=Clear Skies, Mooch=, Gathering=340+}, {Item=Sunfish, Zone=Western La Noscea , Coordinates=X:15, Y:29, Bait Used=Spoon Worm, Floating Minnow, Lugworm, Glowworm, Northern Krill, Yumizuno, Krill Cage Feeder, Heavy Steel Jig, Herring Ball, Sinking Minnow, Steel Jig, Rat Tail, Goby Ball, Pill Bug, Versatile Lure, Type=Ocean fishing, Fishing Log=Fishing Log: Isles of Umbra Northshore, Level=40}, {Item=Skyfish, Zone=Coerthas Central Highlands , Coordinates=X:13, Y:14, Bait Used=Balloon Bug, Hoverworm, Versatile Lure, Type=Cloudfishing, Fishing Log=Fishing Log: Sea of Clouds, Level=45}]",
                formatter("fishie")
        );
        assertEquals("[{Item=Coriander, Zone=Coerthas Western Highlands, Coordinates=(x12,y14), Level=60}, {Item=Blood Orange, Zone=Upper La Noscea, Coordinates=(x28,y25), Level=-1, Time=7:00 AM, Slot=2, Star=2}]",
                formatter("orange"));
        assertEquals("[{Item=Ice Shard, Zone=Central Shroud, Coordinates=(x23,y18), Level=5}, {Item=Ice Shard, Zone=Western La Noscea, Coordinates=(x26,y23), Level=20}, {Item=Ice Shard, Zone=South Shroud, Coordinates=(x23,y21), Level=25}, {Item=Ice Shard, Zone=Coerthas Central Highlands, Coordinates=(x23,y17), Level=45}]",
                formatter("ic shrd")
        );
        assertEquals("[{Item=Fire Shard, Zone=Middle La Noscea, Coordinates=(x22,y19), Level=15}, {Item=Fire Shard, Zone=Eastern Thanalan, Coordinates=(x16,y27), Level=20}, {Item=Fire Shard, Zone=Western Thanalan, Coordinates=(x26,y25), Level=5}]",
                formatter("Fire sh"));
        assertEquals("[{Item=Shark Tuna, Zone=Eastern La Noscea , Coordinates=X:32, Y:29, Bait Used=Spoon Worm, Northern Krill, Yumizuno, Heavy Steel Jig, Herring Ball, Sinking Minnow, Steel Jig, Shrimp Cage Feeder, Crab Ball, Rat Tail, Saltwater Boilie, Versatile Lure, Type=Ocean fishing, Fishing Log=Fishing Log: Costa del Sol, Level=30, Fishing Hole=Costa del Sol, Time=7 PM to 9 PM, Weather=Clear Skies, Mooch=Fullmoon Sardine, Gathering=339+}]",
                formatter("Shark tuna"));
    }

    /**
     * Test  method to search the file for certain values.
     */
    private void searchFile(String itemnam) {
        FindItem findItem = new FindItem();
        ArrayList<StringBuilder> arr;
        //findItem.setNumberOfDuplicateItems(1);
        for (String tmp : findItem.essentialFindAllClosestAsMap(itemnam)) {
            System.out.println(tmp);
        }
    }
}
