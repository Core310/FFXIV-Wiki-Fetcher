import ffxivWikiFinder.FindItem;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for FindItem.java
 */
class FindItemTest {
    private final ffxivWikiFinder.FindItem FindItem = new FindItem();
    @org.junit.jupiter.api.Test
    void essentialFindAllClosestAsMap() {
        assertEquals(
                """
                        [Item: Crayfish
                        Zone: Middle La Noscea\s
                        Coordinates: X:20, Y:18
                        Bait Used: Floating Minnow, Moth Pupa, Midge Basket, Spinnerbait, Bass Ball, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure
                        , Item: Crayfish
                        Zone: Lower La Noscea\s
                        Coordinates: X:24, Y:23
                        Bait Used: Floating Minnow, Moth Pupa, Midge Basket, Sinking Minnow, Butterworm, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure
                        , Item: Crayfish
                        Zone: New Gridania\s
                        Coordinates: X:12, Y:13
                        Bait Used: Floating Minnow, Moth Pupa, Midge Basket, Stem Borer, Glowworm, Caddisfly Larva, Snurble Fly, Chocobo Fly, Bass Ball, Butterworm, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure
                        , Item: Crayfish
                        Zone: Old Gridania\s
                        Coordinates: X:11, Y:8
                        Bait Used: Floating Minnow, Moth Pupa, Midge Basket, Glowworm, Caddisfly Larva, Spinnerbait, Chocobo Fly, Bass Ball, Butterworm, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure
                        , Item: Crayfish
                        Zone: Central Shroud\s
                        Coordinates: X:22, Y:21
                        Bait Used: Moth Pupa, Mythril Spoon Lure, Honey Worm, Sinking Minnow, Chocobo Fly, Crow Fly, Crayfish Ball, Versatile Lure
                        , Item: Crayfish
                        Zone: Western Thanalan\s
                        Coordinates: X:17, Y:15
                        Bait Used: Floating Minnow, Moth Pupa, Midge Basket, Glowworm, Caddisfly Larva, Spinnerbait, Rainbow Spoon Lure, Wildfowl Fly, Chocobo Fly, Bass Ball, Butterworm, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure
                        ]""",
                FindItem.essentialFindAllClosestAsMap("crayon fish").toString()
        );

        assertEquals("""
                [Item: Lava Toad
                Zone: Southern Thanalan
                Coordinates: (x13,y31)
                ]""", FindItem.essentialFindAllClosestAsMap("guava tode").toString());

        assertEquals("""
                [Item: Inkfish
                Zone: The Sea of Clouds
                Coordinates: (x29,y35)
                Time: 2PM-4PM
                FolkLore Tome: Abalathian
                , Item: Cupfish
                Zone: Lower La Noscea\s
                Coordinates: X:27, Y:15
                Bait Used: Moth Pupa, Glowworm, Spinnerbait, Honey Worm, Sinking Minnow, Syrphid Basket, Chocobo Fly, Butterworm, Crow Fly, Bloodworm, Crayfish Ball, Versatile Lure
                Time: 5 PM to 7 PM
                , Item: Sunfish
                Zone: Western La Noscea\s
                Coordinates: X:15, Y:29
                Bait Used: Spoon Worm, Floating Minnow, Lugworm, Glowworm, Northern Krill, Yumizuno, Krill Cage Feeder, Heavy Steel Jig, Herring Ball, Sinking Minnow, Steel Jig, Rat Tail, Goby Ball, Pill Bug, Versatile Lure
                , Item: Skyfish
                Zone: Coerthas Central Highlands\s
                Coordinates: X:13, Y:14
                Bait Used: Balloon Bug, Hoverworm, Versatile Lure
                ]""", FindItem.essentialFindAllClosestAsMap("fishie").toString());
    }
}